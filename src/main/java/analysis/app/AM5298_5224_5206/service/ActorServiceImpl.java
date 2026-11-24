package analysis.app.AM5298_5224_5206.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import analysis.app.AM5298_5224_5206.domain.Actor;
import analysis.app.AM5298_5224_5206.domain.Project;
import analysis.app.AM5298_5224_5206.repositories.ActorRepository;

@Service
public class ActorServiceImpl implements ActorService {
	
	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private ProjectService projectService;
	
	@Override
	public List<Actor> findByProjectId(int projectId) {
		return actorRepository.findByProjectId(projectId);
	}

	@Override
	public Actor findById(int actorId) {
		return actorRepository.findById(actorId).get();
	}

	@Override
	public void save(Actor actor, int projectId) {
		Optional<Actor> actorDatabaseOptional=actorRepository.findById(actor.getId());
		Actor actorDatabase;
		
		if(actorDatabaseOptional.isPresent()) {
			actorDatabase=actorDatabaseOptional.get();
		}else {
			actorDatabase=new Actor();
			Project project=projectService.findById(projectId);
			actorDatabase.setProject(project);
		}
		
		actorDatabase.setName(actor.getName());
		actorRepository.save(actorDatabase);
	}
}
