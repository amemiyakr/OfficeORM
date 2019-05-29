package com.example.event.dao;

import java.util.List;

import com.example.event.domain.Event;
import com.example.event.domain.Join;
import com.example.event.domain.User;

public interface JoinDao {

	List<Join> findAll() throws Exception;

	Join findById(Integer id) throws Exception;

	void insert(Join join) throws Exception;

	void update(Join join) throws Exception;

	void delete(Join join) throws Exception;

	List<Join> findByEvent(Event event) throws Exception;

	Join findByUserAndEvent(User user, Event event) throws Exception;

}
