package com.example.event.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import com.example.event.dao.UserDao;
import com.example.event.domain.Event;
import com.example.event.domain.Group;
import com.example.event.domain.Join;
import com.example.event.domain.Type;
import com.example.event.domain.User;

@Controller
public class EventController {

	@Autowired
	private EventDao eventDao;
	@Autowired
	private JoinDao joinDao;
	@Autowired
	private GroupDao groupDao;
	@Autowired
	private UserDao userDao;

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
		if (page == null) {
			page = "1";
		}
		model.addAttribute("page", page);//現在のページをjspに返す
		int numdb = Integer.parseInt(page);

		List<Event> needList = eventDao.page(numdb);
		List<Event> eventList = eventDao.findAll();
		double numEvents = eventList.size();
		int numPages = (int) Math.ceil(numEvents / 5);
		model.addAttribute("numPages", numPages);//必要なページ数を返す

		model.addAttribute("needList", needList);//必要なEventtableDBの中身をjspに返す
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
		Group g = new Group();
		g.setGroupId(null);
		g.setGroupName("全員");
		group.add(0, g); //対象グループの「全員」選択リスト
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
		Date start = event.getStartdate();
		Date end = event.getEnddate();
		if (start != null && end != null) {
			if (end.before(start)) {
				errors.rejectValue("enddate", "error.enddate.id");
				Group g = new Group();
				g.setGroupId(null);
				g.setGroupName("全員");
				group.add(0, g); //対象グループの「全員」選択リスト
				model.addAttribute("group", group);
				return "addEvent";
			}
		}
		if (!errors.hasErrors()) {
			if (event.getGroup().getGroupId() == null) {
				event.setGroup(null);
			}
			eventDao.insert(event);
			return "addEventDone";
		} else {
			Group g = new Group();
			g.setGroupId(null);
			g.setGroupName("全員");
			group.add(0, g); //対象グループの「全員」選択リスト
			model.addAttribute("group", group);
			return "addEvent";
		}
	}

	//イベント編集
	@RequestMapping(value = "/editEvent/{id}", method = RequestMethod.GET)
	public String editGet(@PathVariable("id") Integer eventId, Model model, HttpSession session) throws Exception {
		// 編集するイベントを読み込む
		Event event = eventDao.findById(eventId);
		// ログインユーザーをセッションから取得
		User loginUser = userDao.findById((Integer) session.getAttribute("userId"));
		// イベント登録者でも管理ユーザーでもない場合、イベント詳細ページにリダイレクトする
		if (loginUser.getUserId() != event.getUser().getUserId() && loginUser.getType().getTypeId() != Type.ADMIN) {
			return "redirect:/detailsEvent/{id}";
		}

		model.addAttribute("event", event);
		List<Group> group = groupDao.findAll();
		Group g = new Group();
		g.setGroupId(null);
		g.setGroupName("全員");
		group.add(0, g); //対象グループの「全員」選択リスト
		model.addAttribute("group", group);
		return "editEvent";
	}

	@RequestMapping(value = "/editEvent/{eventId}", method = RequestMethod.POST)
	public String editPost(
			@Valid Event event,
			Errors errors,
			Model model,
			HttpSession session) throws Exception {
		// ログインユーザーをセッションから取得
		User loginUser = userDao.findById((Integer) session.getAttribute("userId"));
		// イベント登録者でも管理ユーザーでもない場合、イベント詳細ページにリダイレクトする
		if (loginUser.getUserId() != event.getUser().getUserId() && loginUser.getType().getTypeId() != Type.ADMIN) {
			return "redirect:/detailsEvent/{id}";
		}

		List<Group> group = groupDao.findAll();
		model.addAttribute("group", group);
		Date start = event.getStartdate();
		Date end = event.getEnddate();
		if (start != null && end != null) {
			if (end.before(start)) {
				errors.rejectValue("enddate", "error.enddate.id");
				Group g = new Group();
				g.setGroupId(null);
				g.setGroupName("全員");
				group.add(0, g); //対象グループの「全員」選択リスト
				model.addAttribute("group", group);
				return "editEvent";
			}
		}
		if (!errors.hasErrors()) {
			if (event.getGroup().getGroupId() == null) {
				event.setGroup(null);
			}
			eventDao.update(event);
			return "editEventDone";
		} else {

			Group g = new Group();
			g.setGroupId(null);
			g.setGroupName("全員");
			group.add(0, g); //対象グループの「全員」選択リスト
			model.addAttribute("group", group);
			return "editEvent";
		}
	}

	// 本日のイベント
	@RequestMapping(value = "/todayEvent")
	public String todaylist(
			@RequestParam(name = "p", defaultValue = "1") String page,
			Model model) throws Exception {
		Date today = new Date();
		Date dayOfEnd = (Date) eventDao.findMaxEndDateOfEvent();

		model.addAttribute("page", page);//現在のページをjspに返す
		int numdb = Integer.parseInt(page);

		List<Event> eventList = eventDao.findEventOfToday(today, dayOfEnd);
		double numEvents = eventList.size();
		int numPages = (int) Math.ceil(numEvents / 5);
		model.addAttribute("numPages", numPages);//必要なページ数を返す

		List<Event> todayList = eventDao.findEventOfTodaypage(today, dayOfEnd, numdb);
		model.addAttribute("todayList", todayList);//必要なEventotableの中身をjspに返す
		List<Join> joinList = joinDao.findAll();
		model.addAttribute("joinList", joinList);//すべてのJointableDBの中身をjspに返す

		return "todayEvent";
	}

	// イベント詳細
	@RequestMapping(value = "/detailsEvent/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer eventId, Model model, HttpSession session) throws Exception {
		Event event = eventDao.findById(eventId);
		model.addAttribute("event", event);
		// そのイベントの参加者リストを取得
		List<Join> joinList = joinDao.findByEvent(event);
		model.addAttribute("joinList", joinList);
		// ログインユーザーをセッションから取得
		User loginUser = userDao.findById((Integer) session.getAttribute("userId"));
		model.addAttribute("loginUser", loginUser);
		// イベント参加データを取得（参加していない場合null）
		Join yourJoin = joinDao.findByUserAndEvent(loginUser, event);
		model.addAttribute("yourJoin", yourJoin);
		// 管理ユーザーのタイプIDを取得
		model.addAttribute("adminTypeId", Type.ADMIN);
		return "detailsEvent";
	}

	//イベント詳細削除
	@RequestMapping(value = "/delEvent/{id}")
	public String del(@PathVariable("id") Integer id, Model model, HttpSession session) throws Exception {
		// 削除するイベントを読み込む
		Event event = eventDao.findById(id);
		// ログインユーザーをセッションから取得
		User loginUser = userDao.findById((Integer) session.getAttribute("userId"));
		// イベント登録者でも管理ユーザーでもない場合、イベント詳細ページにリダイレクトする
		if (loginUser.getUserId() != event.getUser().getUserId() && loginUser.getType().getTypeId() != Type.ADMIN) {
			return "redirect:/detailsEvent/{id}";
		}

		// 予め、削除するイベントの参加者情報を全て削除しておく
		List<Join> joinList = joinDao.findByEvent(event);
		for (Join join : joinList) {
			joinDao.delete(join);
		}
		// イベントを削除
		eventDao.delete(event);
		return "delEvent";
	}

}