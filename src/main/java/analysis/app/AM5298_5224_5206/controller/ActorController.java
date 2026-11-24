package analysis.app.AM5298_5224_5206.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import analysis.app.AM5298_5224_5206.domain.Actor;
import analysis.app.AM5298_5224_5206.domain.Project;
import analysis.app.AM5298_5224_5206.service.ActorService;
import analysis.app.AM5298_5224_5206.service.ProjectService;

@Controller
public class ActorController {
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("/actor/list")
	public String listActors(@RequestParam("projectId") int projectId, Model model) {
		List<Actor> actors=actorService.findByProjectId(projectId);
		Project project=projectService.findById(projectId);
		model.addAttribute("actors", actors);
		model.addAttribute("project", project);
		model.addAttribute("activePage","project");
		return "actors/list-actors";
	}
	
	@RequestMapping("/actor/showFormForAdd")
	public String showFormForAddActor(@RequestParam("projectId") int projectId, Model model) {
		Actor actor = new Actor();
		Project project=projectService.findById(projectId);
		actor.setProject(project);
		model.addAttribute("actor", actor);
		model.addAttribute("projectId", projectId);
		model.addAttribute("activePage","project");
		return "actors/actor-form";
	}
	
	@RequestMapping("/actor/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("actorId") int actorId, @RequestParam("projectId") int projectId, Model model) {
		Actor actor = actorService.findById(actorId);
		model.addAttribute("actor", actor);
		model.addAttribute("projectId", projectId);
		model.addAttribute("activePage","project");
		return "actors/actor-form";
	}
	
	@RequestMapping("/actor/save")
	public String saveActor(Actor actor, @RequestParam("projectId") int projectId) {
		actorService.save(actor, projectId);
		return "redirect:/actor/list?projectId="+projectId;
	}
}
