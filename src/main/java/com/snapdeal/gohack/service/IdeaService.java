package com.snapdeal.gohack.service;

import java.util.List;

import com.snapdeal.gohack.model.CountInsight;
import com.snapdeal.gohack.model.Idea;
import com.snapdeal.gohack.model.Status;

public interface IdeaService {
	
	public String doSubmit(Idea idea, String hostName);

	public List<Idea> getListOfIdeas(String ideaOrFeature);

	public Idea getIdeaDetail(String ideaNumber);

	public Status upVote(Idea idea);
	
	public Status downVote(Idea idea);

	public List<Idea> exportExcel();

	public int collabarateIdea(String email, String ideaNumber);

	public boolean  updateIdea(Idea idea);

	public List<Idea> getListOfTrendingIdeas();

	public CountInsight getCount();

}
