package analysis.app.AM5298_5224_5206.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import analysis.app.AM5298_5224_5206.domain.CRCCard;
import analysis.app.AM5298_5224_5206.domain.Project;
import analysis.app.AM5298_5224_5206.domain.UseCase;
import analysis.app.AM5298_5224_5206.repositories.CRCCardRepository;
import analysis.app.AM5298_5224_5206.repositories.ProjectRepository;
import analysis.app.AM5298_5224_5206.repositories.UseCaseRepository;

@Service
public class CRCCardServiceImpl implements CRCCardService {
	@Autowired
	private CRCCardRepository crcCardRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UseCaseRepository useCaseRepository;

	@Override
	public List<CRCCard> findByProjectId(int projectId) {
		return crcCardRepository.findByProjectId(projectId);
	}

	@Override
	public CRCCard findById(int crcCardId) {
		return crcCardRepository.findById(crcCardId).get();
	}

	@Override
	public void save(CRCCard crcCard, int projectId, List<Integer> useCaseIds) {
		CRCCard crcCardDatabase;
		
		if (crcCard.getId() != 0) {
			crcCardDatabase = crcCardRepository.findById(crcCard.getId()).get();
		} else {
			crcCardDatabase = new CRCCard();
		}
		
		Project project = projectRepository.findById(projectId).get();
		crcCardDatabase.setProject(project);
		crcCardDatabase.setClassName(crcCard.getClassName());
		crcCardDatabase.setResponsibilities(crcCard.getResponsibilities());
		crcCardDatabase.setCollaborations(crcCard.getCollaborations());
		
		List<UseCase> selectedUseCases = new ArrayList<>();
		if (useCaseIds != null) {
			for (Integer useCaseId : useCaseIds) {
				UseCase useCase = useCaseRepository.findById(useCaseId).get();
				if (useCase != null) {
					selectedUseCases.add(useCase);
				}
			}
		}
		crcCardDatabase.setUseCases(selectedUseCases);
		crcCardRepository.save(crcCardDatabase);
	}

	@Override
	public void deleteById(int crcCardId) {
		crcCardRepository.deleteById(crcCardId);		
	}

}
