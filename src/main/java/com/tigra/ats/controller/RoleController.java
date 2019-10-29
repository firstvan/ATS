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

	@GetMapping("/admin")
	public String listOfUsers(Model model,@RequestParam(defaultValue = "") String name  ){

	    List<User> users = userService.findAllUserExceptAdmin();
		model.addAttribute("users",users);
		return "admin";
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
