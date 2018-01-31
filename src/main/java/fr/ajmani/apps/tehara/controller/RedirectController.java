package fr.ajmani.apps.tehara.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirectToIndex() {
		//return "redirect:pages/countries.xhtml";
		return "redirect:pages/countries.xhtml";
	}
	
	@RequestMapping(value = "/maps", method = RequestMethod.GET)
	public String redirectMaps() {
		return "redirect:pages/maps.xhtml";
	}
	
}
