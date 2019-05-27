package com.example.event.dao;

import java.util.List;

import com.example.event.domain.Join;

public interface JoinDao {


	List<Join>findAll() throws Exception;

	Join findById(Integer id)throws Exception;
	void insert (Join join)throws Exception;
	void update (Join join)throws Exception;
	void delete (Join join)throws Exception;


}
