package com.example.event.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.example.event.domain.User;

@Transactional
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public List<User> findAll() throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public User findById(Integer id) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void insert(User member) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void update(User member) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(User member) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public User findByLoginIdAndLoginPass(String loginId, String loginPass) throws Exception {

		User user = (User) getSession()
				.createCriteria(User.class)
				.add(Restrictions.eq("loginId", loginId))
				.uniqueResult();

		if (user == null) {
			return null;
		}

		if (BCrypt.checkpw(loginPass, user.getPass())) {
			return user;
		} else {
			return null;
		}
	}

}
