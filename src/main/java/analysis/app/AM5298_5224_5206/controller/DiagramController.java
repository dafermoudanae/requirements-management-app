package analysis.app.AM5298_5224_5206.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import analysis.app.AM5298_5224_5206.diagrams.DiagramGenerator;
import analysis.app.AM5298_5224_5206.diagrams.DiagramGeneratorFactory;
import analysis.app.AM5298_5224_5206.domain.Project;
import analysis.app.AM5298_5224_5206.service.ProjectService;

@Controller
public class DiagramController {
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("/diagram/view")
	public String viewDiagram(@RequestParam("projectId") int projectId, Model model) {
		Project project = projectService.findById(projectId);
		model.addAttribute("project", project);
		model.addAttribute("activePage", "project");
		return "diagrams/diagram-view";
	}
	
	@RequestMapping("/diagram/generate")
	public String generateDiagram(@RequestParam("projectId") int projectId, @RequestParam("tool") String tool, @RequestParam("type") String type, Model model) {
		Project project = projectService.findById(projectId);
		DiagramGenerator generator = DiagramGeneratorFactory.getGenerator(tool);
		
		
		String script;
		if (type.equals("usecase")) {
			script = generator.generateUseCaseDiagram(project);
		} else {
			script = generator.generateClassDiagram(project);
		}
		
		model.addAttribute("project", project);
		model.addAttribute("script", script);
		model.addAttribute("tool", tool);
		model.addAttribute("type", type);
		model.addAttribute("activePage", "project");
		return "diagrams/diagram-result";
	}
}
