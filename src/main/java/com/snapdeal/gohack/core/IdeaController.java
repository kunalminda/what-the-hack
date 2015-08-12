package com.snapdeal.gohack.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class IdeaController {


	@Autowired
	private IdeaService ideaService;


	@RequestMapping(value="/user/idea", method=RequestMethod.POST,headers = 
			"content-type=application/x-www-form-urlencoded;charset=UTF-8" ,
			produces={"text/xml","application/json"},
			consumes={"text/xml","application/json"})
	public void submitIdea(@ModelAttribute Idea idea){
		ideaService.doSubmit(idea);

	}

	
	@RequestMapping(value="/user/ideas" ,method=RequestMethod.GET)
	
	public @ResponseBody List<Idea> getListofIdeas()
	{
      return ideaService.getListOfIdeas();
	}
}
