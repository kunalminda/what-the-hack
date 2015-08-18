package com.snapdeal.gohack.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class IdeaController {


	@Autowired
	private IdeaService ideaService;


	@RequestMapping(value="/idea", method=RequestMethod.POST,headers = 
			"content-type=application/x-www-form-urlencoded;charset=UTF-8" ,
			produces={"application/json"},
			consumes={"text/xml","application/json"})
	public ModelAndView submitIdea(@ModelAttribute Idea idea){
		String ideaNumber=ideaService.doSubmit(idea);
		return new ModelAndView("redirect:/ideaDetail?idea="+ideaNumber);
	}


	@RequestMapping(value="/ideas" ,method=RequestMethod.GET)
	public @ResponseBody List<Idea> getListofIdeas()
	{
		return ideaService.getListOfIdeas();
	}
	
	@RequestMapping(value="/ideas/exportExcel" ,method=RequestMethod.GET)
	public @ResponseBody List<Idea> exportToExcel()
	{
		return ideaService.exportExcel();
	}

	@RequestMapping(value="/idea/{ideaNumber}" ,method=RequestMethod.GET)
	public @ResponseBody Idea getIdeaDetail(@PathVariable("ideaNumber") String ideaNumber)
	{
		return ideaService.getIdeaDetail(ideaNumber);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/ideastatus/{ideaNumber}/upvote" ,method=RequestMethod.GET)
	public @ResponseBody ResponseEntity upVote(@PathVariable("ideaNumber") String ideaNumber)
	{
		ResponseEntity<HttpStatus> responseEntity = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)){
			if(authentication !=null && authentication.isAuthenticated()){
				ideaService.upVote(ideaNumber);
				responseEntity= new ResponseEntity(HttpStatus.OK);
			}
		}
		else{
			responseEntity= new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
		return responseEntity;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/ideastatus/{ideaNumber}/downvote" ,method=RequestMethod.GET)
	public @ResponseBody ResponseEntity downVote(@PathVariable("ideaNumber") String ideaNumber)
	{
		ideaService.downVote(ideaNumber);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping (value="idea/{ideaNumber}/email/{emailId}",method=RequestMethod.GET)
	public @ResponseBody boolean registerIdeaVote(@PathVariable ("emailId") String email,
			@PathVariable ("ideaNumber") String ideaNumber){
		 return ideaService.registerIdeaVote(email,ideaNumber);
	}
	
//	@RequestMapping (value="idea/email/{emailId}",method=RequestMethod.GET)
//	public @ResponseBody boolean checkVoteAgainstEmail(@PathVariable ("emailId") String email){
//		 return ideaService.checkIfVoted(email);
//	}
}
