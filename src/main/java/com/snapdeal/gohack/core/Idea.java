package com.snapdeal.gohack.core;

import java.util.Date;

public class Idea {
	
	public String ideaNumber;
	
	public String ideaStatus;
	
	public int ideaUpVote;
	
	public int ideaDownVote;
	
	public Date submittedOn;
	
	public int count;
	
	
	
	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getSubmittedOn() {
		return submittedOn;
	}

	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}

	public String getIdeaStatus() {
		return ideaStatus;
	}

	public void setIdeaStatus(String ideaStatus) {
		this.ideaStatus = ideaStatus;
	}

	public int getIdeaUpVote() {
		return ideaUpVote;
	}

	public void setIdeaUpVote(int ideaUpVote) {
		this.ideaUpVote = ideaUpVote;
	}

	public int getIdeaDownVote() {
		return ideaDownVote;
	}

	public void setIdeaDownVote(int ideaDownVote) {
		this.ideaDownVote = ideaDownVote;
	}

	public String getIdeaNumber() {
		return ideaNumber;
	}

	public void setIdeaNumber(String ideaNumber) {
		this.ideaNumber = ideaNumber;
	}

	public String ideaOverview;
	
	public String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String section;

	public String objective;

	
	public String description;
	

	


	public String getIdeaOverview() {
		return ideaOverview;
	}

	public void setIdeaOverview(String ideaOverview) {
		this.ideaOverview = ideaOverview;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}


	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Idea [ideaNumber=" + ideaNumber + ", ideaOverview="
				+ ideaOverview + ", email=" + email + ", section=" + section
				+ ", objective=" + objective + ", description=" + description
				+ "]";
	}







	
	
	
	

}
