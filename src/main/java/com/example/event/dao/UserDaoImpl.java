package com.example.event.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.example.event.domain.User;

@Transactional
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

	@SuppressWarnings("unchecked") //ワーニングを出ないようにするアノテーション
	@Override
	public List<User> findAll() throws Exception {//userのすべてのDBを取り出す
		return getSession().createCriteria(User.class)
				.setFetchMode("group", FetchMode.JOIN)
				.addOrder(Order.desc("userId"))
				.list();
	}

	@SuppressWarnings("unchecked") //ワーニングを出ないようにするアノテーション
	@Override
	public List<User> needpage(int page) throws Exception {//pで返された必要なDBを取り出す
		page = (page - 1) * 5;
		return getSession().createCriteria(User.class)
				.setFirstResult(page)
				.setMaxResults(5)
				.setFetchMode("group", FetchMode.JOIN)
				.addOrder(Order.desc("userId"))
				.list();
	}

	@Override
	public User findById(Integer id) throws Exception {
		return (User) getSession().createCriteria(User.class)
				.add(Restrictions.eq("userId", id))
				.uniqueResult();
	}

	@Override
	public void insert(User user) throws Exception {
		user.setCreatedate(new Date());
		getSession().save(user);

	}

	@Override
	public void update(User user) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(User user) throws Exception {
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
