package com.tigra.ats.controller;


import com.tigra.ats.domain.Role;
import com.tigra.ats.domain.User;
import com.tigra.ats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		List<User> users=userService.findByFullNameIsContaining(name);
		model.addAttribute("users", users);
		return "admin";
	}


	@PostMapping("/update/{email}")
	public String updateUser(@PathVariable("email") String email, @RequestParam("role") String role, Model model) {
		Map<String,String> forFront=new HashMap();
		forFront.put("HR vezető","HRVEZETO");
		forFront.put("HR munkatárs","HRMUNKATARS");
		forFront.put("Szakmaivezető","SZAKMAIVEZETO");
		forFront.put("Szakmaimunkatárs","SZAKMAIMUNKATARS");
		forFront.put("Semmi jog","NONE");
		User user = userService.findByEmail(email);

		if(role.equals("None")){
            user.setRole(null);
            userService.save(user);
        }else {
            Role existRole = userService.findRoleByName("ROLE_"+forFront.get(role));
            user.setRole(existRole);
        }
		userService.save(user);
		return "redirect:/admin";
	}
}
