package analysis.app.AM5298_5224_5206.service;

import java.util.List;

import analysis.app.AM5298_5224_5206.domain.CRCCard;

public interface CRCCardService {
	public List<CRCCard> findByProjectId(int projectId);
	public CRCCard findById(int crcCardId);
	public void save(CRCCard crcCard, int projectId, List<Integer> useCaseIds);
	public void deleteById(int crcCardId);
}
