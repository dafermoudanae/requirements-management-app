package analysis.app.AM5298_5224_5206.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import analysis.app.AM5298_5224_5206.domain.Project;
import analysis.app.AM5298_5224_5206.domain.ProjectMember;
import analysis.app.AM5298_5224_5206.domain.User;
import analysis.app.AM5298_5224_5206.repositories.ProjectMemberRepository;
import analysis.app.AM5298_5224_5206.repositories.ProjectRepository;
import analysis.app.AM5298_5224_5206.repositories.UserRepository;
import jakarta.persistence.EntityManager;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProjectMemberRepository projectMemberRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Project> findAllByUser(String username) {
		User user=userRepository.findByUsername(username).get();
		return projectRepository.findByMembersUserId(user.getId());
	}

	@Override
	public Project findById(int id) {
		return projectRepository.findById(id).get();
	}

	@Override
	@Transactional
	public void save(Project project, String username, List<Integer> teammateIds) {
		ProjectMember owner;
		Project projectDatabase;
		boolean isUpdate=projectRepository.existsById(project.getId());
		
		if (isUpdate) {
			owner=projectMemberRepository.getFirstByProjectIdAndOwnerIsTrue(project.getId());	
			
			projectMemberRepository.deleteByProjectId(project.getId());
			projectMemberRepository.flush();
			entityManager.clear();
			projectDatabase=projectRepository.findById(project.getId()).get();
		}else {
			projectDatabase=new Project();
			projectDatabase.setMembers(new ArrayList<>());
			
			User user=userRepository.findByUsername(username).get();
			owner=new ProjectMember();
			owner.setOwner(true);
			owner.setUser(user);
			owner.setProject(projectDatabase);
		}
		
		projectDatabase.setTitle(project.getTitle());
		projectDatabase.setDescription(project.getDescription());
		
		if (teammateIds!=null) {
			for (Integer memberId:teammateIds) {
				User user=userService.findById(memberId);
				ProjectMember member=new ProjectMember();
				member.setUser(user);
				member.setProject(projectDatabase);
				projectDatabase.getMembers().add(member);
			}
		}
		
		projectDatabase.getMembers().add(owner);
		projectRepository.save(projectDatabase);
	}

	@Override
	public void deleteById(int id) {
		projectRepository.deleteById(id);
	}	
}
