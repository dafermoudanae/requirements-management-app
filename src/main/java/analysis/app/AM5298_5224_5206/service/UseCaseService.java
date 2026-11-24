package analysis.app.AM5298_5224_5206.service;

import java.util.List;

import analysis.app.AM5298_5224_5206.domain.UseCase;

public interface UseCaseService {
	public List<UseCase> findByProjectId(int projectId);
	public UseCase findById(int useCaseId);
	public void save(UseCase useCase, int projectId, List<Integer> actorIds);
	void deleteById(int useCaseId);
}
