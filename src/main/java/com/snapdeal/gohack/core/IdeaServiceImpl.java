package com.snapdeal.gohack.core;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class IdeaServiceImpl implements IdeaService{


	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public String doSubmit(Idea idea) {
		String ideaNumber=UUID.randomUUID().toString();
		jdbcTemplate.update("insert into user_ideas (ideaNumber,email,ideaOverview,section,objective,description)"
				+ "VALUES (?,?,?,?,?,?) ",ideaNumber,idea.getEmail(),idea.getIdeaOverview(),idea.getSection(),idea.getObjective(),
				idea.getDescription());
       return ideaNumber;

	}


	@Override
	public List<Idea> getListOfIdeas() {
		List<Idea> listofIdeas= jdbcTemplate.query("select * from user_ideas",
				new BeanPropertyRowMapper(Idea.class));
		return listofIdeas;
	}


	@Override
	public Idea getIdeaDetail(String ideaNumber) {
		List<Idea> ideas = jdbcTemplate.query("select * from user_ideas where ideaNumber= ?",new Object[]{ideaNumber},
				new BeanPropertyRowMapper(Idea.class));
		return ideas.get(0);
	}
}
