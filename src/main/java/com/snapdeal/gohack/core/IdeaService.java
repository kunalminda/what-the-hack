package com.snapdeal.gohack.core;

import java.util.List;

public interface IdeaService {
	
	public void doSubmit(Idea idea, String userEmail);

	public List<Idea> getListOfIdeas();

}
