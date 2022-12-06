package com.uma.tfg.entities;

public class UsersForTaskRequest {

    private String input;
    private long projectId;

	public UsersForTaskRequest() {}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	
	
}
