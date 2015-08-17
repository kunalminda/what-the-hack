package com.snapdeal.gohack.core;

import java.util.List;

public interface IdeaService {
	
	public String doSubmit(Idea idea);

	public List<Idea> getListOfIdeas();

	public Idea getIdeaDetail(String ideaNumber);

}
