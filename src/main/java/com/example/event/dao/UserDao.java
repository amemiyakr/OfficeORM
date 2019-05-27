package com.example.event.dao;

import java.util.List;

import com.example.event.domain.User;

public interface UserDao {

	List<User> findAll() throws Exception;

	User findById(Integer id) throws Exception;

	void insert(User member) throws Exception;

	void update(User member) throws Exception;

	void delete(User member) throws Exception;

	User findByLoginIdAndLoginPass(String loginId, String loginPass ) throws Exception;

}
