package analysis.app.AM5298_5224_5206.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import analysis.app.AM5298_5224_5206.domain.Role;
import analysis.app.AM5298_5224_5206.domain.User;
import analysis.app.AM5298_5224_5206.service.UserService;

@Controller
public class AuthController {
	
    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login(){
        return "auth/signin";
    }

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "auth/signup";
    }

    @RequestMapping("/save")
    public String registerUser(@ModelAttribute("user") User user, Model model){
    	user.setRole(Role.DEVELOPER);
    	
        if(userService.isUserPresent(user)){
            model.addAttribute("successMessage", "User already registered!");
            return "auth/signin";
        }

        userService.saveUser(user);
        model.addAttribute("successMessage", "User registered successfully!");

        return "auth/signin";
    }
    
    @RequestMapping("/homepage")
    public String homepage(){
        return "homepage";
    }
    
    @RequestMapping("/auth/profile")
    public String profile(Model model, Principal principal) {
    	String username=principal.getName();
    	User user=userService.findByUsername(username);
    	
    	model.addAttribute("user",user);
    	model.addAttribute("activePage","profile");
        return "auth/profile";
    }
    
    @RequestMapping("/auth/showFormForUpdate")
	public String showFormForUpdate(Model model, Principal principal) {
    	String username=principal.getName();
    	User user=userService.findByUsername(username);
    	user.setPassword("");
		
    	model.addAttribute("user",user);
    	model.addAttribute("activePage","profile");
		
		return "auth/profile-form";			
	}
    
    @RequestMapping("auth/saveProfile")
    public String saveProfile(@ModelAttribute("user") User user, Model model, Principal principal) {
    	String username=principal.getName();
    	userService.saveUserForProfile(username,user);
    	return "redirect:/auth/profile";
    }
}
