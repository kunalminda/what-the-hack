package com.snapdeal.gohack.core;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;


@Component
@PropertySource("classpath:ideas.properties")
public class IdeaServiceImpl implements IdeaService{


	@Resource
	private Environment environment;

	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;


	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public String doSubmit(final Idea idea,final String hostName) {
		final String ideaNumber=UUID.randomUUID().toString();
		jdbcTemplate.update("insert into user_ideas (ideaNumber,email,ideaOverview,section,objective,description)"
				+ "VALUES (?,?,?,?,?,?) ",ideaNumber,idea.getEmail(),idea.getIdeaOverview(),idea.getSection(),idea.getObjective(),
				idea.getDescription());
		jdbcTemplate.update("insert into idea_status(ideaNumber) VALUES (?)" ,ideaNumber);
		new Thread(new Runnable() {

			@Override
			public void run() {
				shootIdeaSubmissionEmail(idea.getEmail().trim(),hostName,ideaNumber);

			}
		}).start();

		return ideaNumber;

	}


	@Override
	public List<Idea> getListOfIdeas() {
		List<Idea> listofIdeas= jdbcTemplate.query("SELECT *  FROM user_ideas AS t1 INNER JOIN idea_status AS t2 ON t1.ideaNumber = t2.ideaNumber order by submittedOn desc ",
				new BeanPropertyRowMapper<Idea>(Idea.class));
		return listofIdeas;
	}


	@Override
	public Idea getIdeaDetail(String ideaNumber) {
		List<Idea> ideas = jdbcTemplate.query("SELECT *  FROM user_ideas AS t1 INNER JOIN idea_status AS t2 ON t1.ideaNumber = t2.ideaNumber"+""
				+ " where t1.ideaNumber=?",new Object[]{ideaNumber},
				new BeanPropertyRowMapper<Idea>(Idea.class));
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



	public void shootIdeaSubmissionEmail(final String email,final String hostName,final String ideaNumber){
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				int randdomQuotes = (int) (Math.random() * (5 - 0)) + 0;
				String idea=environment.getProperty("idea"+"."+randdomQuotes,"idea.2");

				String ideaPageLink="http://"+hostName+"/"+"ideaDetail"+"?idea="+ideaNumber;
				HashMap<String,String> templateValues= new HashMap<String, String>();
				templateValues.put("ideaPageLink", ideaPageLink);
				templateValues.put("ideaQuotes",idea);
				mimeMessage.setRecipient(Message.RecipientType.TO,
						new InternetAddress(email));
				mimeMessage.setFrom(new InternetAddress(email));
				mimeMessage.setSubject("Thank you for your submission");
				TemplateLoader loader = new ClassPathTemplateLoader();
				loader.setPrefix("/templates");
				loader.setSuffix(".html");
				Handlebars handlebars = new Handlebars(loader);

				Template template = handlebars.compile("submission");
				String text = template.apply(templateValues);
				mimeMessage.setText(text, "utf-8", "html");
			}
		};

		try {
			this.javaMailSenderImpl.send(preparator);
		}
		catch (MailException ex) {
			// simply log it and go on...
			System.err.println(ex.getMessage());
		}
	}


	@Override
	public List<Idea> exportExcel() {
		List<Idea> listofIdeas= jdbcTemplate.query("SELECT *  FROM user_ideas AS t1 INNER JOIN idea_status "
				+ "AS t2 ON t1.ideaNumber = t2.ideaNumber ",
				new BeanPropertyRowMapper<Idea>(Idea.class));
		return listofIdeas;
	}





	@Override
	public boolean registerIdeaVote(String email, String ideaNumber) {
		boolean status=true;
		try{
			jdbcTemplate.update("insert into idea_vote (ideaNumber,user_email) "
					+ "values (?,?)",new Object[]{ideaNumber,email} );
		}
		catch(Exception e){
			status=false;
		}
		return status;
	}
}
