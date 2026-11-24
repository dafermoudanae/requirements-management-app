package analysis.app.AM5298_5224_5206.service;

import java.util.List;

import analysis.app.AM5298_5224_5206.domain.Actor;

public interface ActorService {
	public List<Actor> findByProjectId(int projectId);
	public Actor findById(int actorId);
	public void save(Actor actor, int projectId);
}
