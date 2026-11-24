package analysis.app.AM5298_5224_5206.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import analysis.app.AM5298_5224_5206.domain.Actor;
import analysis.app.AM5298_5224_5206.domain.Project;
import analysis.app.AM5298_5224_5206.domain.UseCase;
import analysis.app.AM5298_5224_5206.repositories.ActorRepository;
import analysis.app.AM5298_5224_5206.repositories.ProjectRepository;
import analysis.app.AM5298_5224_5206.repositories.UseCaseRepository;

@Service
public class UseCaseServiceImpl implements UseCaseService {
	
	@Autowired
	private UseCaseRepository useCaseRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ActorRepository actorRepository;
	
	@Override
	public List<UseCase> findByProjectId(int projectId) {
		return useCaseRepository.findByProjectId(projectId);
	}

	@Override
	public UseCase findById(int useCaseId) {
		return useCaseRepository.findById(useCaseId).get();
	}

	@Override
	public void save(UseCase useCase, int projectId, List<Integer> actorIds) {
		UseCase useCaseDatabase;
		
		if (useCase.getId()!=0) {
			useCaseDatabase=useCaseRepository.findById(useCase.getId()).get();
		}else {
			useCaseDatabase=new UseCase();
		}
		Project project=projectRepository.findById(projectId).get();
		useCaseDatabase.setProject(project);
		useCaseDatabase.setTitle(useCase.getTitle());
		useCaseDatabase.setPreconditions(useCase.getPreconditions());
		useCaseDatabase.setMainFlow(useCase.getMainFlow());
		useCaseDatabase.setPostconditions(useCase.getPostconditions());
		
		List<Actor> selectedActors=new ArrayList<>();
		if (actorIds != null) {
			for (Integer actorId : actorIds) {
				Actor actor=actorRepository.findById(actorId).get();
				if (actor!=null) {
					selectedActors.add(actor);
				}
			}
		}
		useCaseDatabase.setActors(selectedActors);
		useCaseRepository.save(useCaseDatabase);
	}

	@Override
	@Transactional
	public void deleteById(int useCaseId) {
	    useCaseRepository.deleteById(useCaseId);
	}

}
