package analysis.app.AM5298_5224_5206.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import analysis.app.AM5298_5224_5206.domain.Actor;
import analysis.app.AM5298_5224_5206.domain.Project;
import analysis.app.AM5298_5224_5206.domain.UseCase;
import analysis.app.AM5298_5224_5206.service.ActorService;
import analysis.app.AM5298_5224_5206.service.ProjectService;
import analysis.app.AM5298_5224_5206.service.UseCaseService;

@Controller
public class UseCaseController {
	
	@Autowired
    private UseCaseService useCaseService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ActorService actorService;
	
	@RequestMapping("/usecase/list")
	public String listUseCases(@RequestParam("projectId") int projectId, Model model) {
		List<UseCase> theUseCases=useCaseService.findByProjectId(projectId);
		Project theProject=projectService.findById(projectId);
		model.addAttribute("useCases", theUseCases);
		model.addAttribute("project", theProject);
		model.addAttribute("activePage","project");
		return "usecases/list-usecases";
	}
	
	@RequestMapping("/usecase/showFormForAdd")
	public String showFormForAdd(@RequestParam("projectId") int projectId, Model model) {
		UseCase useCase=new UseCase();
		List<Actor> projectActors=actorService.findByProjectId(projectId);
		model.addAttribute("useCase", useCase);
		model.addAttribute("projectId", projectId);
		model.addAttribute("projectActors", projectActors);
		model.addAttribute("selectedActorIds", new ArrayList<Integer>());
		model.addAttribute("activePage","project");
		return "usecases/usecase-form";
	}
	
	@RequestMapping("/usecase/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("useCaseId") int useCaseId, @RequestParam("projectId") int projectId, Model model) {
		UseCase useCase=useCaseService.findById(useCaseId);
		List<Actor> projectActors=actorService.findByProjectId(projectId);
		List<Integer> selectedActorIds=new ArrayList<>();
		
		for(int i=0;i<useCase.getActors().size();i++) {
			selectedActorIds.add(useCase.getActors().get(i).getId());
		}
		model.addAttribute("useCase", useCase);
		model.addAttribute("projectId", projectId);
		model.addAttribute("projectActors", projectActors);
		model.addAttribute("selectedActorIds", selectedActorIds);
		model.addAttribute("activePage","project");
		return "usecases/usecase-form";
	}
	
	@RequestMapping("/usecase/save")
	public String saveUseCase(UseCase useCase, @RequestParam("projectId") int projectId, @RequestParam(value = "actorIds") List<Integer> actorIds) {
		useCaseService.save(useCase, projectId, actorIds);
		return "redirect:/usecase/list?projectId=" + projectId;
	}
	
	@RequestMapping("/usecase/delete")
	public String delete(@RequestParam("useCaseId") int theId, @RequestParam("projectId") int projectId) {
		useCaseService.deleteById(theId);
		return "redirect:/usecase/list?projectId=" + projectId;
	}
}
