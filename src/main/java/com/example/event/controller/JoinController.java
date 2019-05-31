package com.example.event.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.event.dao.EventDao;
import com.example.event.dao.JoinDao;
import com.example.event.dao.UserDao;
import com.example.event.domain.Event;
import com.example.event.domain.Join;
import com.example.event.domain.User;

@Controller
public class JoinController {

	@Autowired
	private JoinDao joinDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private EventDao eventDao;

	// イベントに参加する
	@RequestMapping(value = "/joinEvent/{id}")
	public String joinEvent(@PathVariable("id") Integer eventId, Model model, HttpSession session) throws Exception {
		// ログインユーザーをセッションから取得
		User loginUser = userDao.findById((Integer) session.getAttribute("userId"));
		// 参加するイベントを取得
		Event event = eventDao.findById(eventId);
		// まだ参加していない場合のみ、参加登録処理を行う
		if(joinDao.findByUserAndEvent(loginUser, event) == null) {
			// 新しい参加レコードを生成し、ログインユーザー・参加するイベントを設定
			Join join = new Join();
			join.setUser(loginUser);
			join.setEvent(event);
			// 参加レコードをイベント参加テーブルに登録
			joinDao.insert(join);
		}
		// イベント詳細ページにリダイレクト
		return "redirect:/detailsEvent/{id}";
	}

	// イベントの参加を取り消す
	@RequestMapping(value = "/cancelEvent/{id}")
	public String cancelEvent(@PathVariable("id") Integer eventId, Model model, HttpSession session) throws Exception {
		// ログインユーザーをセッションから取得
		User loginUser = userDao.findById((Integer) session.getAttribute("userId"));
		// 参加するイベントを取得
		Event event = eventDao.findById(eventId);
		// イベント参加テーブルから参加レコードを探す
		Join join = joinDao.findByUserAndEvent(loginUser, event);
		// 見つかった場合のみ、参加レコードを削除
		if(join != null) {
			joinDao.delete(join);
		}
		// イベント詳細ページにリダイレクト
		return "redirect:/detailsEvent/{id}";
	}

}
