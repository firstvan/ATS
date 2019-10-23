package com.tigra.ats.controller;


import com.tigra.ats.domain.Role;
import com.tigra.ats.domain.User;
import com.tigra.ats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RoleController {
    private UserService userService;

	@Autowired
	public RoleController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/")
	public String home(Model model){
		return "index";
	}

	@GetMapping("/admin")
	public String listOfUsers(Model model,@RequestParam(defaultValue = "") String name  ){

	    List<User> users = userService.findAllUserExceptAdmin();
		model.addAttribute("users",users);
		return "admin";
	}
    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user =  userService.findById(id);
        userService.delete(user);
        model.addAttribute("users", userService.findAllUserExceptAdmin());
        return "admin";
    }

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		User user = userService.findById(id);
		List<Role> roles = userService.findAllRole();
		roles = roles.stream()
			.filter(role -> !role.getRole().equals("ROLE_ADMIN"))
			.collect(Collectors.toList());
		model.addAttribute("user", user);
		model.addAttribute("roles",roles );
		return "update-user";
	}

	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @RequestParam("role") String role, Model model) {
	    if(role.equals("None")){
            User user = userService.findById(id);
            user.deleteRole();
            userService.save(user);
        }else {
            User user = userService.findById(id);
            Role existRole = userService.findRoleByName(role);
            user.setRole(existRole);
            userService.save(user);
        }
		return "redirect:/admin";
	}
}
