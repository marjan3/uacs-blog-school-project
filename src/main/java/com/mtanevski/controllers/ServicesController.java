package com.mtanevski.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ServicesController extends AbstractController{

	@RequestMapping(value="/services", method=RequestMethod.GET)
	public String doServices(Model model) {
		return "services";
	}
}
