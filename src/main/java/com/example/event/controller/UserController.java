package com.example.event.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.event.dao.GroupDao;
import com.example.event.dao.TypeDao;
import com.example.event.dao.UserDao;
import com.example.event.domain.Group;
import com.example.event.domain.Type;
import com.example.event.domain.User;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private TypeDao typeDao;

	//ユーザー一覧(管理者)
	@RequestMapping(value = { "/userList" })
	public String userlist(
			@RequestParam(name = "p", defaultValue = "1") String page,
			Model model) throws Exception {
		model.addAttribute("page", page); //現在のページをjspに返す

		int numdb = Integer.parseInt(page); //現在のページから必要なDBを取り出すためintにする
		List<User> needList = userDao.needpage(numdb); //必要なDBを取り出す
		model.addAttribute("needList", needList); //必要なuserTableDBの中身をjspに返す(modelに格納)

		List<User> userList = userDao.findAll(); //すべてのuserTbaleDBを取り出す
		double numUsers = userList.size(); //userTableDB内のデータ数を計る
		int numPages = (int) Math.ceil(numUsers / 5); //ページ番号計算
		model.addAttribute("numPages", numPages); //ページ番号をjspに返す(modelに格納)

		return "userList"; //userListにmodelに返す

	}

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String addGet(Model model, HttpSession session) throws Exception {
		User user = new User();
		//		user.setUserId((Integer) session.getAttribute("userId"));
		model.addAttribute("user", user);
		//		User.setUserId(user);
		List<Group> group = groupDao.findAll();
//		Group g = new Group();
//		group.add(0, g); //対象グループの「全員」選択リスト
		model.addAttribute("group", group);
		List<Type> type = typeDao.findAll();
		model.addAttribute("type", type);

		return "addUser";
	}

	// イベント情報登録 POST
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addPost(
			@Valid User user,
			Errors errors,
			Model model) throws Exception {
		List<Group> group = groupDao.findAll();
		model.addAttribute("group", group);
		List<Type> type = typeDao.findAll();
		model.addAttribute("type", type);

		if (!errors.hasErrors()) {
			userDao.insert(user);
			return "addUserDone";
		} else {
			model.addAttribute("user", user);
			return "addUser";
		}
	}
}
