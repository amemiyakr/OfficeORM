package com.example.event.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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
		page = (page - 1) * 5;
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
		getSession().update(event);
	}

	@Override
	public void delete(Event event) throws Exception {
		getSession().delete(event);
	}

	@SuppressWarnings("unchecked") //ワーニングを出ないようにするアノテーション
	@Override
	public List<Event> findEventOfTodaypage(Date todayOfStart, Date todayOfEnd, int page) throws Exception {
		Date today = todayOfStart;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(todayOfStart);
		calendar.add(Calendar.DATE, 1);
		todayOfStart = calendar.getTime();
		page = (page - 1) * 5;
		return getSession().createCriteria(Event.class)
				.setFirstResult(page)
				.setMaxResults(5)
				.setFetchMode("group", FetchMode.JOIN)
				.addOrder(Order.desc("startdate"))
				.add(Restrictions.between("enddate", today, todayOfEnd))
				.add(Restrictions.le("startdate", todayOfStart))
				.list();
	}

	@SuppressWarnings("unchecked") //ワーニングを出ないようにするアノテーション
	@Override
	public List<Event> findEventOfToday(Date todayOfStart, Date todayOfEnd) throws Exception {
		Date today = todayOfStart;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(todayOfStart);
		calendar.add(Calendar.DATE, 1);
		todayOfStart = calendar.getTime();
		return getSession().createCriteria(Event.class)
				.setFetchMode("group", FetchMode.JOIN)
				.addOrder(Order.desc("startdate"))
				.add(Restrictions.between("enddate", today, todayOfEnd))
				.add(Restrictions.le("startdate", todayOfStart))
				.list();
	}

	@Override
	public Date findMaxEndDateOfEvent() throws Exception {
		Date maxEndDay = (Date) getSession().createCriteria(Event.class)
				.setProjection(Projections.max("enddate"))
				.uniqueResult();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(maxEndDay);
		calendar.add(Calendar.DATE, 1);
		maxEndDay = calendar.getTime();
		return maxEndDay;
	}

}
