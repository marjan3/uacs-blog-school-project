package com.mtanevski.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtanevski.models.About;

@Controller
public class AboutController extends AbstractController{

	@RequestMapping(value="/about", method=RequestMethod.GET)
	public String doAbout(Model model) {
		About about = service.getAbout();
		model.addAttribute(about);
		return "about";
	}
}
