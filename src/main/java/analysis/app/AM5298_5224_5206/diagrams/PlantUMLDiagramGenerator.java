package analysis.app.AM5298_5224_5206.diagrams;

import analysis.app.AM5298_5224_5206.domain.Actor;
import analysis.app.AM5298_5224_5206.domain.CRCCard;
import analysis.app.AM5298_5224_5206.domain.Project;
import analysis.app.AM5298_5224_5206.domain.UseCase;

public class PlantUMLDiagramGenerator implements DiagramGenerator {

	@Override
	public String generateUseCaseDiagram(Project project) {
		StringBuilder sb = new StringBuilder();
		sb.append("@startuml\n");
		sb.append("left to right direction\n");
		sb.append("skinparam packageStyle rectangle\n\n");
		
		for (Actor actor : project.getActors()) {
			sb.append("actor \"").append(actor.getName()).append("\"\n");
		}
		
		sb.append("\nrectangle \"").append(project.getTitle()).append("\" {\n");
		
		for (UseCase uc : project.getUseCases()) {
			sb.append("  usecase \"").append(uc.getTitle()).append("\" as UC_").append(sanitize(uc.getTitle())).append("\n");
		}
		
		for (UseCase uc : project.getUseCases()) {
			for (Actor actor : uc.getActors()) {
				sb.append("  \"").append(actor.getName()).append("\" --> UC_").append(sanitize(uc.getTitle())).append("\n");
			}
		}
		
		sb.append("}\n");
		sb.append("@enduml");
		return sb.toString();
	}

	@Override
	public String generateClassDiagram(Project project) {
		StringBuilder sb = new StringBuilder();
		sb.append("@startuml\n\n");
		
		for (CRCCard card : project.getCrcCards()) {
			sb.append("class \"").append(card.getClassName()).append("\" {\n");
			if (card.getResponsibilities() != null && !card.getResponsibilities().isBlank()) {
				for (String line : card.getResponsibilities().split("\n")) {
					sb.append("  ").append(line.trim()).append("\n");
				}
			}
			sb.append("}\n\n");
		}
		
		for (CRCCard card : project.getCrcCards()) {
			if (card.getCollaborations() != null && !card.getCollaborations().isBlank()) {
				for (String collab : card.getCollaborations().split("\n")) {
					String target = collab.trim();
					if (!target.isEmpty()) {
						sb.append("\"").append(card.getClassName()).append("\" --> \"").append(target).append("\"\n");
					}
				}
			}
		}
		
		sb.append("\n@enduml");
		return sb.toString();
	}
	
	private String sanitize(String name) {
		return name.replaceAll("[^a-zA-Z0-9]", "_");
	}
}
