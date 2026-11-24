package analysis.app.AM5298_5224_5206.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import analysis.app.AM5298_5224_5206.domain.UseCase;

public interface UseCaseRepository extends JpaRepository<UseCase, Integer> {
	public List<UseCase> findByProjectId(int projectId);
}
