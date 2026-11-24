package analysis.app.AM5298_5224_5206.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import analysis.app.AM5298_5224_5206.domain.CRCCard;
import analysis.app.AM5298_5224_5206.domain.Project;
import analysis.app.AM5298_5224_5206.domain.UseCase;
import analysis.app.AM5298_5224_5206.service.CRCCardService;
import analysis.app.AM5298_5224_5206.service.ProjectService;
import analysis.app.AM5298_5224_5206.service.UseCaseService;

@Controller
public class CRCCardController {
	@Autowired
	private CRCCardService crcCardService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UseCaseService useCaseService;
	
	@RequestMapping("/crccard/list")
	public String listCRCCards(@RequestParam("projectId") int projectId, Model model) {
		List<CRCCard> crcCards = crcCardService.findByProjectId(projectId);
		Project project = projectService.findById(projectId);
		model.addAttribute("crcCards", crcCards);
		model.addAttribute("project", project);
		model.addAttribute("activePage", "project");
		return "crccards/list-crccards";
	}
	
	@RequestMapping("/crccard/showFormForAdd")
	public String showFormForAdd(@RequestParam("projectId") int projectId, Model model) {
		CRCCard crcCard = new CRCCard();
		List<UseCase> projectUseCases = useCaseService.findByProjectId(projectId);
		model.addAttribute("crcCard", crcCard);
		model.addAttribute("projectId", projectId);
		model.addAttribute("projectUseCases", projectUseCases);
		model.addAttribute("selectedUseCaseIds", new ArrayList<Integer>());
		model.addAttribute("activePage", "project");
		return "crccards/crccard-form";
	}
	
	@RequestMapping("/crccard/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("crcCardId") int crcCardId, @RequestParam("projectId") int projectId, Model model) {
		CRCCard crcCard = crcCardService.findById(crcCardId);
		List<UseCase> projectUseCases = useCaseService.findByProjectId(projectId);
		List<Integer> selectedUseCaseIds = new ArrayList<>();
		
		for (int i = 0; i < crcCard.getUseCases().size(); i++) {
			selectedUseCaseIds.add(crcCard.getUseCases().get(i).getId());
		}
		
		model.addAttribute("crcCard", crcCard);
		model.addAttribute("projectId", projectId);
		model.addAttribute("projectUseCases", projectUseCases);
		model.addAttribute("selectedUseCaseIds", selectedUseCaseIds);
		model.addAttribute("activePage", "project");
		return "crccards/crccard-form";
	}
	
	@RequestMapping("/crccard/save")
	public String saveCRCCard(CRCCard crcCard, @RequestParam("projectId") int projectId, @RequestParam(value = "useCaseIds", required = false) List<Integer> useCaseIds) {
		crcCardService.save(crcCard, projectId, useCaseIds);
		return "redirect:/crccard/list?projectId=" + projectId;
	}
	
	@RequestMapping("/crccard/delete")
	public String delete(@RequestParam("crcCardId") int crcCardId, @RequestParam("projectId") int projectId) {
		crcCardService.deleteById(crcCardId);
		return "redirect:/crccard/list?projectId=" + projectId;
	}
}
