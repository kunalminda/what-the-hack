package com.snapdeal.gohack.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.snapdeal.gohack.model.CountInsight;
import com.snapdeal.gohack.model.Idea;
import com.snapdeal.gohack.model.Status;
import com.snapdeal.gohack.service.IdeaService;
import com.snapdeal.gohack.serviceImpl.Comment;



@RestController
public class IdeaController {


	@Autowired
	private IdeaService ideaService;


	 private static final HttpTransport TRANSPORT = new NetHttpTransport();
	 
	 private static final JacksonFactory JSON_FACTORY = new JacksonFactory();
	
	GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(TRANSPORT, JSON_FACTORY)
   .setAudience(Arrays.asList("720978887997-5tvk3foplvbv42qpa652josapujtthjj.apps.googleusercontent.com"))
   .build();
	


	@RequestMapping(value="/tokensignin", method=RequestMethod.POST,headers = 
			"content-type=application/x-www-form-urlencoded;charset=UTF-8" ,
			produces={"application/json"},
			consumes={"text/xml","application/json"})
	public String googleConnect(HttpServletRequest request, @RequestParam("idtoken") String tokenId){
		System.out.println(tokenId);
		GoogleIdToken idToken = null;
		Object takeme = null;
		try {
			idToken = verifier.verify(tokenId);
			request.getSession().setAttribute("gtoken", true);
			takeme = request.getSession().getAttribute("takeme");
			request.getSession().removeAttribute("takeme");
			System.out.println("----takeme removed from session----");
			return (takeme==null)?"/":takeme.toString();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "/login";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "/login";
		}

	}
	
	@RequestMapping(value="/tokensignout", method=RequestMethod.POST,headers = 
			"content-type=application/x-www-form-urlencoded;charset=UTF-8" ,
			produces={"application/json"},
			consumes={"text/xml","application/json"})
	public String googleDisconnect(HttpServletRequest request){
		request.getSession().removeAttribute("gtoken");
		request.getSession().removeAttribute("takeme");
		return "/";
	}
	
	public  boolean isUserAuthorized(HttpServletRequest request){
		return (request.getSession().getAttribute("gtoken") == null)?false: true;
	}

	public ModelAndView authorizeUser(String takeme, String failureRedirect, HttpServletRequest request ){
		if(!isUserAuthorized(request)){
			request.getSession().setAttribute("takeme", takeme);
			return new ModelAndView("redirect:"+failureRedirect);
		}
		return null;
	}

	@RequestMapping(value="/idea", method=RequestMethod.POST,headers = 
			"content-type=application/x-www-form-urlencoded;charset=UTF-8" ,
			produces={"application/json"},
			consumes={"text/xml","application/json"})
	public ResponseEntity<Status> submitIdea(@RequestBody Idea idea,HttpServletRequest request){
		String hostName=request.getHeader("Host");
		
		if(!isUserAuthorized(request)){
			return new ResponseEntity<Status>(new Status(true, "home"), HttpStatus.UNAUTHORIZED);
		}
		System.out.println(idea);
		String ideaNumber=ideaService.doSubmit(idea,hostName);
		return new ResponseEntity<Status>(new Status(true, "/ideaDetail?idea="+ideaNumber), HttpStatus.OK);
		//return null;
	}

	@RequestMapping(value="/update", method=RequestMethod.POST,
			produces={"application/json"},
			consumes={"text/xml","application/json"})
	public ResponseEntity<Boolean> updateIdea(@RequestBody Idea idea, HttpServletRequest request){
		if(!isUserAuthorized(request)){
			return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
		}
		
		boolean  updateStatus=ideaService.updateIdea(idea);
		return new ResponseEntity<Boolean>(updateStatus, HttpStatus.OK);
	}


	@RequestMapping(value="/ideas" ,method=RequestMethod.GET)
	public @ResponseBody List<Idea> getListofIdeasOrFeatures(@RequestParam (value="iof",required=false) String ideaOrFeature)
	{
		return ideaService.getListOfIdeas(ideaOrFeature);
	}
	

	@RequestMapping(value="/ideas/trend" ,method=RequestMethod.GET)
	public @ResponseBody List<Idea> getListofTrendingIdeas()
	{
		return ideaService.getListOfTrendingIdeas();
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

	@RequestMapping(value="/ideastatus/upvote" ,method=RequestMethod.POST,produces={"application/json"})
	public ResponseEntity<Status> upVote(@RequestBody Idea idea, HttpServletRequest request)
	{
		if(!isUserAuthorized(request)){
			return new ResponseEntity<Status>(new Status(false, "UNAUTHORIZED"), HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<Status>(ideaService.upVote(idea), HttpStatus.OK);

	}

	@RequestMapping(value="/ideastatus/downvote" ,method=RequestMethod.POST)
	public ResponseEntity<Status> downVote(@RequestBody Idea idea, HttpServletRequest request)
	{
		
		if(!isUserAuthorized(request)){
			return new ResponseEntity<Status>(new Status(false, "UNAUTHORIZED"), HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Status>(ideaService.downVote(idea), HttpStatus.OK);


	}

	@RequestMapping (value="idea/{ideaNumber}/email/{emailId}",method=RequestMethod.POST)
	public ResponseEntity<Integer> collabarateIdea(@PathVariable ("emailId") String email,
			@PathVariable ("ideaNumber") String ideaNumber, HttpServletRequest request){
		
		if(!isUserAuthorized(request)){
			return new ResponseEntity<Integer>(-1, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Integer>(ideaService.collabarateIdea(email,ideaNumber), HttpStatus.OK);
	}
	

	@RequestMapping (value="/insightcount",method=RequestMethod.GET)
	public @ResponseBody CountInsight getCount(){
		return ideaService.getCount();
	}
	
	
	@RequestMapping (value="/idea/{ideaNumber}/comment",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Boolean> ideaComment(@PathVariable("ideaNumber") String ideaNumber,
			@RequestBody Comment comment,HttpServletRequest request){
		
		if(!isUserAuthorized(request)){
			return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Boolean>(ideaService.comment(ideaNumber,comment),HttpStatus.OK);
	}

}
