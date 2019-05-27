package com.example.event.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.example.event.domain.Event;

@Transactional
@Repository
public class EventDaoImpl extends BaseDao implements EventDao {

	@SuppressWarnings("unchecked") //ワーニングを出ないようにするアノテーション
	@Override
	public List<Event> findAll() throws Exception {//Eventのテーブルを持ってくる
		return getSession().createCriteria(Event.class)
				.setFetchMode("group", FetchMode.JOIN)
				.addOrder(Order.desc("startdate"))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> page(int page) throws Exception {//Eventテーブルを5件分持ってくる
		// page = 1 -> 0
		// page = 2 -> 5
		// page = 3 -> 10
		// page = n -> (n - 1) * 5
		page=(page-1)*5;
		return getSession().createCriteria(Event.class)
				.setFirstResult(page)
				.setMaxResults(5)
				.setFetchMode("group", FetchMode.JOIN)
				.addOrder(Order.desc("startdate"))
				.list();
	}



	@Override
	public Event findById(Integer id) throws Exception {
		return (Event) getSession().createCriteria(Event.class)
				.setFetchMode("group", FetchMode.JOIN)
				.setFetchMode("user", FetchMode.JOIN)
				.setFetchMode("join", FetchMode.JOIN)
				.add(Restrictions.eq("eventId", id))
				.uniqueResult();
	}

	@Override
	public void insert(Event event) throws Exception {
		event.setCreatedate(new Date());
		getSession().save(event);
	}

	@Override
	public void update(Event event) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(Event event) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@SuppressWarnings("unchecked") //ワーニングを出ないようにするアノテーション
	@Override
	public List<Event> findEventOfToday() throws Exception {
		Date today = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(today);
		calendar.add(calendar.DATE, 1);
		Date todayOfStart = calendar.getTime();
		return getSession().createCriteria(Event.class)
				.setFetchMode("group", FetchMode.JOIN)
				.add(Restrictions.ge("enddate", today))
				.add(Restrictions.lt("startdate", todayOfStart))
				.list();
	}

}
