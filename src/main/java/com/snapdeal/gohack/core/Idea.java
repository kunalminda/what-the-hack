package com.snapdeal.gohack.core;

public class Idea {
	
	public String ideaNumber;
	
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
