package com.example.event.dao;

import java.util.List;

import com.example.event.domain.User;

public interface UserDao {

	List<User> findAll() throws Exception;

	User findById(Integer id) throws Exception;

	void insert(User user) throws Exception;

	void update(User user) throws Exception;

	void delete(User user) throws Exception;

	User findByLoginIdAndLoginPass(String loginId, String loginPass ) throws Exception;

	List<User> needpage(int page) throws Exception;

}
