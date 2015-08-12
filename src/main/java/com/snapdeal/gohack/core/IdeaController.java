package com.snapdeal.gohack.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class IdeaController {
	
	
	@Autowired
	private IdeaService ideaService;
	
	
	@RequestMapping(value="/user/idea", method=RequestMethod.POST,headers = "content-type=application/x-www-form-urlencoded;charset=UTF-8" ,
			produces={"text/xml","application/json"},
			consumes={"text/xml","application/json"})
	public void submitIdea(@ModelAttribute Idea idea){
		 String userEmail="saloni.jain@snapdeal.com";
		 idea.setSection("Seller Ecosystem");
		 ideaService.doSubmit(idea,userEmail);
		
	}

}
