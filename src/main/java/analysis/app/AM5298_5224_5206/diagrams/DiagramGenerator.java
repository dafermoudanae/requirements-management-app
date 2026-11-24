package analysis.app.AM5298_5224_5206.diagrams;

import analysis.app.AM5298_5224_5206.domain.Project;

public interface DiagramGenerator {
	String generateUseCaseDiagram(Project project);
	String generateClassDiagram(Project project);
}