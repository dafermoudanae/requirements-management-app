package analysis.app.AM5298_5224_5206.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import analysis.app.AM5298_5224_5206.domain.Actor;


public interface ActorRepository extends JpaRepository<Actor, Integer> {
	public List<Actor> findByProjectId(int projectId);
}
