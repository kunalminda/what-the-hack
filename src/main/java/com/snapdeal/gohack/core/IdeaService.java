package com.snapdeal.gohack.core;

import java.util.List;

public interface IdeaService {
	
	public String doSubmit(Idea idea, String hostName);

	public List<Idea> getListOfIdeas();

	public Idea getIdeaDetail(String ideaNumber);

	public void upVote(String ideaNumber);
	
	public void downVote(String ideaNumber);

	public List<Idea> exportExcel();

	public boolean registerIdeaVote(String email, String ideaNumber);

}
