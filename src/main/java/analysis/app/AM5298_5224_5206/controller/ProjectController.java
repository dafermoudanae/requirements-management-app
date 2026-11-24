package analysis.app.AM5298_5224_5206.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import analysis.app.AM5298_5224_5206.domain.Project;
import analysis.app.AM5298_5224_5206.domain.ProjectMember;
import analysis.app.AM5298_5224_5206.domain.User;
import analysis.app.AM5298_5224_5206.service.ProjectService;
import analysis.app.AM5298_5224_5206.service.UserService;

@Controller
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/project/list")
	public String listProjects(Model model, Principal principal) {
		String username=principal.getName();
		List<Project> projects=projectService.findAllByUser(username);
		model.addAttribute("projects", projects);
		model.addAttribute("activePage","project");
		return "projects/list-projects";
	}
	
	@RequestMapping("/project/showFormForAdd")
	public String showFormForAddProject(Model model, Principal principal) {
		String username=principal.getName();
		Project project=new Project();
		model.addAttribute("project", project);
		
		User user = userService.findByUsername(username);
		ProjectMember owner = new ProjectMember();
		owner.setUser(user);
		List<User> allUsers=userService.findByUsernameNot(username);
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("owner", owner);
		model.addAttribute("memberIds", new ArrayList<Integer>());
		model.addAttribute("activePage","project");
		return "projects/project-form";
	}
	
	@RequestMapping("/project/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("projectId") int projectId, Model model, Principal principal) {
		Project project=projectService.findById(projectId);
		ProjectMember owner = null;
		
		for(int i=0;i<project.getMembers().size();i++) {
			if(project.getMembers().get(i).isOwner()==true) {
				owner=project.getMembers().get(i);
			}
		}
		
		List<User> allUsers=userService.findByUsernameNot(owner.getUser().getUsername());
		List<Integer> memberIds=new ArrayList<Integer>();
		
		for(int i=0;i<project.getMembers().size();i++) {
			memberIds.add(project.getMembers().get(i).getUser().getId());
		}
		
		model.addAttribute("memberIds", memberIds);
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("owner", owner);
		model.addAttribute("project", project);
		model.addAttribute("activePage","project");
		return "projects/project-form";
	}
	
	@RequestMapping("/project/save")
	public String saveProject(Project project, Principal principal, @RequestParam(name="teammateIds", required = false) List<Integer> teammateIds) {
		String username=principal.getName();
		projectService.save(project, username, teammateIds);
		return "redirect:/project/list";
	}
	
	
	@RequestMapping("/project/delete")
	public String delete(@RequestParam("projectId") int projectId) {
		projectService.deleteById(projectId);
		return "redirect:/project/list";
	}
}
