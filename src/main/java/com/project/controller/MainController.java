package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String loginPage()
	{
		return "login";
	}
	
	@RequestMapping("/main")
	public String mainPage()
	{
		return "main";
	}
	@RequestMapping("/manager")
	public String managerPage()
	{
		return "manager";
	}
	@RequestMapping("/login")
	public String login()
	{
		return "login";
	}
}
