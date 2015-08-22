package com.snapdeal.gohack.model;

public class IdeaStatus {
	
	public String ideaDownvote;
	
	public String ideaUpVote;
	
	public String status;

	public String getIdeaDownvote() {
		return ideaDownvote;
	}

	public void setIdeaDownvote(String ideaDownvote) {
		this.ideaDownvote = ideaDownvote;
	}

	public String getIdeaUpVote() {
		return ideaUpVote;
	}

	public void setIdeaUpVote(String ideaUpVote) {
		this.ideaUpVote = ideaUpVote;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "IdeaStatus [ideaDownvote=" + ideaDownvote + ", ideaUpVote="
				+ ideaUpVote + ", status=" + status + "]";
	}
	
	

}
