package com.snapdeal.gohack.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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


	@Override
	public List<Idea> getListOfIdeas() {
	   List<Idea> listofIdeas= jdbcTemplate.query("select * from user_ideas",new BeanPropertyRowMapper(Idea.class));
		return listofIdeas;
	}
	
//	public class IdeaRowMapper implements RowMapper
//	{
//		
//		@Override
//		public Object mapRow(ResultSet resultSet, int arg1) throws SQLException {
//			Idea idea= new Idea();
//			idea.setDescription(resultSet.getString("description"));
//			idea.setEmail(resultSet.getString("email"));
//			idea.setIdeaNumber(resultSet.getInt("ideaNumber"));
//			idea.setIdeaOverView(resultSet.getString("ideaOverview"));
//			idea.setObjective(resultSet.getString("objective"));
//			idea.setDescription(resultSet.getString("description"));
//			return idea;
//			
////		}
//
//	}
}
