package com.example.event.domain;

public class Join {
	private Integer joinId;
	private User user;
	private Event event;

	public Integer getJoinId() {
		return joinId;
	}

	public void setJoinId(Integer joinId) {
		this.joinId = joinId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
