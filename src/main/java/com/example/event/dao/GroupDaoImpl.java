package com.example.event.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.event.domain.Group;

@Transactional
@Repository
public class GroupDaoImpl extends BaseDao implements GroupDao {

	@SuppressWarnings("unchecked") //ワーニングを出ないようにするアノテーション
	@Override
	public List<Group> findAll() throws Exception {
		return getSession().createCriteria(Group.class).list();
	}

}
