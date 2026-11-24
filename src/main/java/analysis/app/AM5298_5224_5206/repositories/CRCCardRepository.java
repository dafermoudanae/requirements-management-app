package analysis.app.AM5298_5224_5206.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import analysis.app.AM5298_5224_5206.domain.CRCCard;

public interface CRCCardRepository extends JpaRepository<CRCCard, Integer> {
	public List<CRCCard> findByProjectId(int projectId);
}