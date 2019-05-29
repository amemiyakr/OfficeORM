package com.example.event.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.event.domain.Type;

@Transactional
@Repository
public class TypeDaoImpl extends BaseDao implements TypeDao {

	@SuppressWarnings("unchecked") //ワーニングを出ないようにするアノテーション
	@Override
	public List<Type> findAll() throws Exception {
		return getSession().createCriteria(Type.class).list();
	}

}
