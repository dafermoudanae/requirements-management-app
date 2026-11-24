package analysis.app.AM5298_5224_5206.service;

import java.util.List;

import analysis.app.AM5298_5224_5206.domain.Project;

public interface ProjectService {
	public List<Project> findAllByUser(String username);
	public Project findById(int id);
	public void save(Project project, String username, List<Integer> teammateIds);
	public void deleteById(int id);
}