package com.snapdeal.gohack.core;

public class Idea {
	
	public int ideaNumber;
	
	public String ideaOverView;
	
	public String section;
	
	public IdeaStatus ideaStatus;
	
	public String objective;
	
	public String requirements;
	
	public String description;
	
	public String speakerBio;

	public int getIdeaNumber() {
		return ideaNumber;
	}

	public void setIdeaNumber(int ideaNumber) {
		this.ideaNumber = ideaNumber;
	}

	public String getIdeaOverView() {
		return ideaOverView;
	}

	public void setIdeaOverView(String ideaOverView) {
		this.ideaOverView = ideaOverView;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public IdeaStatus getIdeaStatus() {
		return ideaStatus;
	}

	public void setIdeaStatus(IdeaStatus ideaStatus) {
		this.ideaStatus = ideaStatus;
	}

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
		return "Idea [ideaNumber=" + ideaNumber + ", ideaOverView="
				+ ideaOverView + ", section=" + section + ", ideaStatus="
				+ ideaStatus + ", objective=" + objective + ", requirements="
				+ requirements + ", description=" + description
				+ ", speakerBio=" + speakerBio + "]";
	}
	
	
	
	

}
