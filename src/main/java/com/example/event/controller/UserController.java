package com.example.event.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
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

	// ユーザー登録GET
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String addGet(Model model, HttpSession session) throws Exception {
		User user = new User();
		model.addAttribute("user", user);
		List<Group> group = groupDao.findAll();
		model.addAttribute("group", group);
		List<Type> type = typeDao.findAll();
		model.addAttribute("type", type);
		return "addUser";
	}

	// ユーザー登録POST
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addPost(
			@Valid User user,
			Errors errors,
			Model model) throws Exception {
		List<Group> group = groupDao.findAll();
		model.addAttribute("group", group);
		List<Type> type = typeDao.findAll();
		model.addAttribute("type", type);
		String has = BCrypt.hashpw(user.getPass(), BCrypt.gensalt());
		user.setPass(has);
		if (!errors.hasErrors()) {
			userDao.insert(user);
			return "addUserDone";
		} else {
			model.addAttribute("user", user);
			return "addUser";
		}
	}

	// ユーザー詳細
	@RequestMapping(value = "/detailsUser/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer userId, Model model) throws Exception {
		User user = userDao.findById(userId);
		model.addAttribute("user", user);
		return "detailsUser";
	}

	//ユーザー削除
	@RequestMapping(value = "/delUser/{id}")
	public String del(@PathVariable("id") Integer id, Model model, HttpSession session) throws Exception {
		// 削除するユーザーを読み込む
		User user = userDao.findById(id);
		// ユーザーを削除
		userDao.delete(user);
		return "delUser";
	}

	// ユーザー編集GET
	@RequestMapping(value = "/editUser/{id}", method = RequestMethod.GET)
	public String editGet(@PathVariable("id") Integer userId, Model model, HttpSession session) throws Exception {
		User user = userDao.findById(userId);
		User loginUser = userDao.findById((Integer) session.getAttribute("userId"));
		if (loginUser.getType().getTypeId() != Type.ADMIN) {
			return "redirect:/eventList";
		}
		model.addAttribute("user", user);
		List<Group> group = groupDao.findAll();
		model.addAttribute("group", group);
		List<Type> type = typeDao.findAll();
		model.addAttribute("type", type);
		return "editUser";
	}

	// ユーザー編集POST
	@RequestMapping(value = "/editUser/{userId}", method = RequestMethod.POST)
	public String editPost(
			@RequestParam("passNew") String passNew,
			@Valid User user,
			Errors errors,
			HttpSession session,
			Model model) throws Exception {
		User loginUser = userDao.findById((Integer) session.getAttribute("userId"));
		if (loginUser.getType().getTypeId() != Type.ADMIN) {
			return "redirect:/eventList";
		}
		List<Group> group = groupDao.findAll();
		model.addAttribute("group", group);
		List<Type> type = typeDao.findAll();
		model.addAttribute("type", type);

		if (!passNew.isEmpty()) {
			if (BCrypt.checkpw(passNew, user.getPass())) {
				errors.rejectValue("pass", "error.passEditForSame");
				return "editUser";
			} else {
				user.setPass(BCrypt.hashpw(passNew, BCrypt.gensalt()));
			}
		}
		if (!errors.hasErrors()) {
			userDao.update(user);
			if (session.getAttribute("userId").equals(user.getUserId())) {
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("loginId", user.getLoginId());
				session.setAttribute("userName", user.getUserName());
				session.setAttribute("typeId", user.getType().getTypeId());
			}
			return "editUserDone";
		} else {
			model.addAttribute("user", user);
			System.out.println(errors);
			return "editUser";
		}
	}
}