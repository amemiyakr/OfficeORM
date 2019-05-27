package com.example.event.dao;

import java.util.List;

import com.example.event.domain.Group;

public interface GroupDao {

	List<Group> findAll() throws Exception;

}
