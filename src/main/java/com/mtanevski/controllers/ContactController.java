package com.mtanevski.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactController extends AbstractController{
	
	@RequestMapping(value="/contact", method=RequestMethod.GET)
	public String doContact(Model model) {
		return "contact";
	}
}
