package com.example.event.dao;

import java.util.List;

import com.example.event.domain.Event;

public interface EventDao {

	List<Event>findAll() throws Exception;
	List<Event> findEventOfToday() throws Exception;
	Event findById(Integer id)throws Exception;
	void insert (Event event)throws Exception;
	void update (Event event)throws Exception;
	void delete (Event event)throws Exception;
	List<Event> page(int page) throws Exception;

}
