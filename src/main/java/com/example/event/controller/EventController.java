package com.example.event.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.event.dao.EventDao;
import com.example.event.dao.JoinDao;
import com.example.event.domain.Event;
import com.example.event.domain.Join;

@Controller
public class EventController {

	@Autowired
	private EventDao eventDao;
	@Autowired
	private JoinDao joinDao;

	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}

	// イベント一覧
	@RequestMapping(value = { "/", "/eventList" })
	public String list(Model model) throws Exception {
		List<Event> eventList = eventDao.findAll();
		model.addAttribute("eventList", eventList);
		List<Join> joinList = joinDao.findAll();
		model.addAttribute("joinList", joinList);
		return "eventList";
	}

	// 本日のイベント
	@RequestMapping(value = "/todayEvent", method = RequestMethod.GET)
	public String todaylist(@PathVariable("p") Integer page, Model model) throws Exception {
		List<Event> todayList = eventDao.findEventOfToday();

		PagedListHolder<Event> pagedListHolder = new PagedListHolder<>(todayList);
		if (page == null) {
			page = 1;
		}
		//		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPageSize(2);
		pagedListHolder.setPage(page);
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("todayList", todayList);

		return "todayEvent";
	}

	// イベント詳細
	@RequestMapping(value = "/detailsEvent/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer eventId, Model model) throws Exception {
		Event event = eventDao.findById(eventId);
		model.addAttribute("event", event);
		return "detailsEvent";
	}

}
