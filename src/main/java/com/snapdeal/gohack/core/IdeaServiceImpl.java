package com.snapdeal.gohack.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class IdeaServiceImpl implements IdeaService{


	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public void doSubmit(Idea idea) {
		jdbcTemplate.update("insert into user_ideas (email,idea_overview,section,objective,requirements,description,speakerBio)"
				+ "VALUES (?,?,?,?,?,?,?) ",idea.getEmail(),idea.getIdeaOverView(),idea.getSection(),idea.getObjective(),
				idea.getRequirements()
				,idea.getDescription(),idea.getSpeakerBio());


	}


	@Override
	public List<Idea> getListOfIdeas() {
		List<Idea> listofIdeas= jdbcTemplate.query("select * from user_ideas",
				new BeanPropertyRowMapper(Idea.class));
		return listofIdeas;
	}
}
