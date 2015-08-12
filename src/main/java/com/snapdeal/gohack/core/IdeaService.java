package com.snapdeal.gohack.core;

import java.util.List;

public interface IdeaService {
	
	public void doSubmit(Idea idea);

	public List<Idea> getListOfIdeas();

}
