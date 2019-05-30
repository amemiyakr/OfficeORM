package com.example.event.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.event.domain.Type;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AuthFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		String uri = req.getRequestURI();

		System.out.println(uri);
		System.out.println((Integer)session.getAttribute("typeId"));


		if (!uri.endsWith("/login") &&
				!uri.contains("/css/") &&
				!uri.contains("/js/") &&
				!uri.contains("/fonts/")) {
			if (session.getAttribute("userName") == null) {
				res.sendRedirect(req.getContextPath() + "/login");
				return;
			}
		} else if (uri.endsWith("/login")) {
			if (session.getAttribute("userName") != null) {
				res.sendRedirect(req.getContextPath() + "/eventList");
				return;
			}
		}
		if (uri.endsWith("/userList") ||
			uri.endsWith("/addUser") ||
			uri.endsWith("/addUserDone") ||
			uri.endsWith("/editUser") ||
			uri.endsWith("/editUserDone") ||
			uri.endsWith("/detailsUser") ||
			uri.endsWith("/delUser") ||
			uri.endsWith("/deleteUserDone")) {
			if ((Integer)session.getAttribute("typeId") != Type.ADMIN) {
				res.sendRedirect(req.getContextPath() + "/eventList");
				return;
			}
		}



		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
