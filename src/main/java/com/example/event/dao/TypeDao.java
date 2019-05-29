package com.example.event.dao;

import java.util.List;

import com.example.event.domain.Type;

public interface TypeDao {
	List<Type> findAll() throws Exception;
	
}