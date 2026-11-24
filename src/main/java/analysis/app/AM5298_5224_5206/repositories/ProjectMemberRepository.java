package analysis.app.AM5298_5224_5206.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import analysis.app.AM5298_5224_5206.domain.ProjectMember;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Integer>{
	@Modifying
    @Query("DELETE FROM ProjectMember pm WHERE pm.project.id = :projectId")
	public void deleteByProjectId(@Param("projectId") int projectId);
	
	public ProjectMember getFirstByProjectIdAndOwnerIsTrue(int projectId);
}
