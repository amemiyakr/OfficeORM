package com.example.event.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.event.domain.Join;

@Transactional
@Repository
public class JoinDaoImpl extends BaseDao implements JoinDao {

	@SuppressWarnings("unchecked") //ワーニングを出ないようにするアノテーション
	@Override
	public List<Join> findAll() throws Exception {
			return getSession().createCriteria(Join.class).list();
		}

	@Override
	public Join findById(Integer id) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void insert(Join join) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void update(Join join) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(Join join) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
