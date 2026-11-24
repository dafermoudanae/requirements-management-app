package analysis.app.AM5298_5224_5206.diagrams;

import analysis.app.AM5298_5224_5206.domain.Actor;
import analysis.app.AM5298_5224_5206.domain.CRCCard;
import analysis.app.AM5298_5224_5206.domain.Project;
import analysis.app.AM5298_5224_5206.domain.UseCase;

public class NomnomlDiagramGenerator implements DiagramGenerator {

	@Override
	public String generateUseCaseDiagram(Project project) {
		StringBuilder sb = new StringBuilder();
		sb.append("#direction: right\n\n");
		
		for (Actor actor : project.getActors()) {
			sb.append("[<actor> ").append(actor.getName()).append("]\n");
		}
		
		sb.append("\n");
		
		for (UseCase uc : project.getUseCases()) {
			sb.append("[<usecase> ").append(uc.getTitle()).append("]\n");
		}
		
		sb.append("\n");
		
		for (UseCase uc : project.getUseCases()) {
			for (Actor actor : uc.getActors()) {
				sb.append("[<actor> ").append(actor.getName()).append("] -> [<usecase> ").append(uc.getTitle()).append("]\n");
			}
		}
		
		return sb.toString();
	}

	@Override
	public String generateClassDiagram(Project project) {
		StringBuilder sb = new StringBuilder();
		
		for (CRCCard card : project.getCrcCards()) {
			sb.append("[").append(card.getClassName());
			if (card.getResponsibilities() != null && !card.getResponsibilities().isBlank()) {
				sb.append("|");
				String responsibilities = card.getResponsibilities().replace("\n", "; ");
				sb.append(responsibilities);
			}
			sb.append("]\n");
		}
		
		sb.append("\n");
		
		for (CRCCard card : project.getCrcCards()) {
			if (card.getCollaborations() != null && !card.getCollaborations().isBlank()) {
				for (String collab : card.getCollaborations().split("\n")) {
					String target = collab.trim();
					if (!target.isEmpty()) {
						sb.append("[").append(card.getClassName()).append("] -> [").append(target).append("]\n");
					}
				}
			}
		}
		
		return sb.toString();
	}

}
