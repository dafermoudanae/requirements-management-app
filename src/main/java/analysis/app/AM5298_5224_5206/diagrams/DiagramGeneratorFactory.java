package analysis.app.AM5298_5224_5206.diagrams;

public class DiagramGeneratorFactory {
	public static DiagramGenerator getGenerator(String tool) {
		switch (tool) {
			case "plantuml":
				return new PlantUMLDiagramGenerator();
			case "nomnoml":
				return new NomnomlDiagramGenerator();
			default:
				return null;
		}
	}
}
