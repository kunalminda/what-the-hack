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
		jdbcTemplate.update("insert into idea_status(ideaNumber) VALUES (?)" ,ideaNumber);
       return ideaNumber;

	}


	@Override
	public List<Idea> getListOfIdeas() {
		List<Idea> listofIdeas= jdbcTemplate.query("SELECT *  FROM user_ideas AS t1 INNER JOIN idea_status AS t2 ON t1.ideaNumber = t2.ideaNumber",
				new BeanPropertyRowMapper(Idea.class));
		return listofIdeas;
	}


	@Override
	public Idea getIdeaDetail(String ideaNumber) {
		List<Idea> ideas = jdbcTemplate.query("SELECT *  FROM user_ideas AS t1 INNER JOIN idea_status AS t2 ON t1.ideaNumber = t2.ideaNumber"+""
				+ " where t1.ideaNumber=?",new Object[]{ideaNumber},
				new BeanPropertyRowMapper(Idea.class));
		return ideas.get(0);
	}


	@Override
	public void upVote(String ideaNumber) {
		jdbcTemplate.update("UPDATE idea_status SET ideaUpVote=ideaUpVote+1 where ideaNumber =?",new Object[]{ideaNumber} );
	}


	@Override
	public void downVote(String ideaNumber) {
		jdbcTemplate.update("UPDATE idea_status SET ideaDownVote=ideaDownVote+1 where ideaNumber =?",new Object[]{ideaNumber} );
		
	}
}
