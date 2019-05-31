package com.example.event.domain;

import javax.validation.constraints.Size;

public class Group {
	private Integer groupId;
	@Size(max=45)
	private String groupName;

	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
