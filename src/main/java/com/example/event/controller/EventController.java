package com.example.event.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.event.dao.EventDao;
import com.example.event.dao.GroupDao;
import com.example.event.dao.JoinDao;
import com.example.event.domain.Event;
import com.example.event.domain.Group;
import com.example.event.domain.Join;
import com.example.event.domain.User;

@Controller
public class EventController {

	@Autowired
	private EventDao eventDao;
	@Autowired
	private JoinDao joinDao;
	@Autowired
	private GroupDao groupDao;

	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}

	// イベント一覧
	@RequestMapping(value = { "/", "/eventList" })
	public String list(Model model, HttpServletRequest request) throws Exception {
		String page = request.getParameter("p");
		if(page==null) {
			page="1";
		}
		model.addAttribute("page",page);
		int numdb =Integer.parseInt(page);

		List<Event> needList =eventDao.page(numdb);

		List<Event> eventList = eventDao.findAll();

		double numEvents = eventList.size();
		int numPages=(int)Math.ceil(numEvents/5);
		model.addAttribute("numPages",numPages);//必要なページ数を返す


		model.addAttribute("needList", needList);//すべてのEventtableDBの中身をjspに返す
		List<Join> joinList = joinDao.findAll();
		model.addAttribute("joinList", joinList);//すべてのJointableDBの中身をjspに返す

		return "eventList";
	}

	// イベント情報登録 GET
	@RequestMapping(value = "/addEvent", method = RequestMethod.GET)
	public String addGet(Model model, HttpSession session) throws Exception {
		Event event = new Event();
		User user = new User();
		user.setUserId((Integer) session.getAttribute("userId"));
		model.addAttribute("event", event);
		event.setUser(user);
		List<Group> group = groupDao.findAll();
		model.addAttribute("group", group);
		return "addEvent";
	}

	// イベント情報登録 POST
	@RequestMapping(value = "/addEvent", method = RequestMethod.POST)
	public String addPost(
			@Valid Event event,
			Errors errors,
			Model model) throws Exception {
		List<Group> group = groupDao.findAll();
		model.addAttribute("group", group);
		if (!errors.hasErrors()) {
			System.out.println("1");
			eventDao.insert(event);
			return "addEventDone";
		} else {
			System.out.println("2");
			return "addEvent";
		}
	}



	// 本日のイベント
	@RequestMapping(value = "/todayEvent")
	public String todaylist(@RequestParam(value = "p", defaultValue = "0") String p, Model model) throws Exception {
		List<Event> todayList = eventDao.findEventOfToday();
		PagedListHolder<Event> pagedListHolder = new PagedListHolder<>(todayList);
		pagedListHolder.setSource(todayList);
		pagedListHolder.setPageSize(2);
		pagedListHolder.setPage(Integer.parseInt(p));
		model.addAttribute("pagedListHolder", pagedListHolder);
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
