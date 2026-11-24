package analysis.app.AM5298_5224_5206.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import analysis.app.AM5298_5224_5206.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
	public List<Project> findByMembersUserId(long userId);
}
