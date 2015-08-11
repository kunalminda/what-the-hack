package com.snapdeal.gohack.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class IdeaServiceImpl implements IdeaService{


	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public void doSubmit(Idea idea, String userEmail) {
		jdbcTemplate.update("insert into user_ideas (email,idea_overview,section,objective,requirements,description,speakerBio)"
				+ "VALUES (?,?,?,?,?,?,?) ",userEmail,idea.getIdeaOverView(),idea.getSection(),idea.getObjective(),idea.getRequirements()
				,idea.getDescription(),idea.getSpeakerBio());
		
		
	}

}
