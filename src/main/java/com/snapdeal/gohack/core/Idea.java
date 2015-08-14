package com.snapdeal.gohack.core;

public class Idea {
	
	public int userIdeaId;
	
	public String ideaOverview;
	
	public String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String section;
	
	//public IdeaStatus ideaStatus;
	
	public String objective;
	
	public String requirements;
	
	public String description;
	
	public String speakerBio;

	

	public int getUserIdeaId() {
		return userIdeaId;
	}

	public void setUserIdeaId(int userIdeaId) {
		this.userIdeaId = userIdeaId;
	}

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

//	public IdeaStatus getIdeaStatus() {
//		return ideaStatus;
//	}
//
//	public void setIdeaStatus(IdeaStatus ideaStatus) {
//		this.ideaStatus = ideaStatus;
//	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpeakerBio() {
		return speakerBio;
	}

	public void setSpeakerBio(String speakerBio) {
		this.speakerBio = speakerBio;
	}

	@Override
	public String toString() {
		return "Idea [userIdeaId=" + userIdeaId + ", ideaOverview="
				+ ideaOverview + ", email=" + email + ", section=" + section
				+ ", objective=" + objective + ", requirements=" + requirements
				+ ", description=" + description + ", speakerBio=" + speakerBio
				+ "]";
	}




	
	
	
	

}
