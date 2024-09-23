package com.kodbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodbook.entities.Post;
import com.kodbook.entities.User;
import com.kodbook.services.PostService;
import com.kodbook.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavigationController {
	@Autowired
	UserService service;
	@Autowired
	PostService postService;
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/openSignUp")
	public String openSignUp() {
		return "signUp";
	}
	@GetMapping("/openCreatePost")
	public String openCreatePost(HttpSession session) {
		if(session.getAttribute("username")!=null)
			return "createPost";
			else 
				return "index";
		
	}
	@GetMapping("/goHome")
	public String login(Model model , HttpSession session)	{
			List<Post> allPosts = postService.fetchAllPosts();
			model.addAttribute("allPosts", allPosts);
			if(session.getAttribute("username")!=null)
				return "home";
				else 
					return "index";
			
	}
	@GetMapping("/openMyProfile")
	public String openMyProfile(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		User user = service.getUser(username);
		model.addAttribute("user", user);
		List<Post>myPosts=user.getPosts();
		model.addAttribute("myPosts",myPosts);
		//model.addAttribute("myPosts", postService.fetchPostsByUser(user));
		if(session.getAttribute("username")!=null)
			return "myProfile";
			else 
				return "index";
		
	}
	@GetMapping("/openEditProfile")
	public String openEditProfile(HttpSession session) {
		if(session.getAttribute("username")!=null)
		return "editProfile";
		else 
			return "index";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	@GetMapping("/openUserProfile")
	public String openUserProfile(@RequestParam String username, Model model, HttpSession session) {
	    User user = service.getUser(username);
	    if (user != null) {
	        model.addAttribute("user", user);
	        model.addAttribute("myPosts", user.getPosts());
	        return "myProfile"; // Return the view for the user's profile
	    } else {
	        return "redirect:/goHome"; // Redirect if user not found
	    }
	}
	
}
