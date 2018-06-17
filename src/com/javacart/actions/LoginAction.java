package com.javacart.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.javacart.beans.ItemBean;
import com.javacart.daos.JavaCartDAO;
import com.javacart.forms.LoginForm;

public class LoginAction extends DispatchAction{
	
	public String message;
	
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LoginForm loginForm = (LoginForm)form;
		HttpSession session = request.getSession();
		
		System.out.println("coming to login method");
		JavaCartDAO dao = new JavaCartDAO();
		if (getErrors(request).size() == 0 ) {
			String title = dao.getUserDetails(loginForm.getUserName(), loginForm.getPassword());
			if (title == null) {
				loginForm.setSuccess(false);
				message = "fail";
			} else {
				getMasterData(loginForm);
				
				session.setAttribute("user", loginForm.getUserName());
				message = "success";
			}
		}
		
		System.out.println(message);
		return mapping.findForward(message);
	}
	
	public ActionForward register(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LoginForm loginForm = (LoginForm)form;
		
		System.out.println("coming to register method");
		JavaCartDAO dao = new JavaCartDAO();
		
		Boolean status = dao.registerUser(loginForm.getUserName(), loginForm.getPassword(), loginForm.getGender());
		if (status == true) {
			loginForm.setRegister(true);
		}
		return mapping.findForward("register");
	}
	
	public ActionForward showSubMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LoginForm loginForm = (LoginForm)form;
		System.out.println("coming to show submenu method");
		String dept = request.getParameter("deptId");
		
		getMasterData(loginForm);
		
		ArrayList<String> arSubDept = loginForm.getHmSubDept().get(Integer.parseInt(dept));
		
		loginForm.setArSubDept(arSubDept);
		return mapping.findForward("showSubMenu");
	}
	
	public ActionForward showCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("coming to show mycart method");
		LoginForm loginForm = (LoginForm)form;
		
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		ArrayList<ItemBean> arItemBean = new ArrayList<ItemBean>();
		
		JavaCartDAO dao = new JavaCartDAO();
		arItemBean = dao.getUserCart(user);
		
		loginForm.setArItemBean(arItemBean);
		
		return mapping.findForward("showCart");
	}
	
	public ActionForward showItems(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LoginForm loginForm = (LoginForm)form;
		
		ArrayList<ItemBean> arItemBean = new ArrayList<ItemBean>();
		String subDept = request.getParameter("subDep");
		String sortby = request.getParameter("sortby");
		String exclude = request.getParameter("exclude");
		String condition = request.getParameter("condition");
		String rating = request.getParameter("rating");
		String minPrice = request.getParameter("minPrice");
		String maxPrice = request.getParameter("maxPrice");
		//filter : 'yes', exclude : exclude, condition : condition, rating : rating
		
		JavaCartDAO dao = new JavaCartDAO();
		arItemBean = dao.getItems(subDept, sortby, exclude, condition, rating, minPrice, maxPrice);
		
		loginForm.setArItemBean(arItemBean);
		
		return mapping.findForward("showItems");
	}
	
	public ActionForward addToCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("coming to add to cart");
		HttpSession session = request.getSession();
		JavaCartDAO dao = new JavaCartDAO();
		String user = (String) session.getAttribute("user");
		String item = request.getParameter("item");
		System.out.println("user is " + user);
		System.out.println("item is " + item);
		
		boolean status = dao.addToCart(user, item);
		
		System.out.println("status is " + status);
		if (status) {
			System.out.println("status is " + status);
		}
		return mapping.findForward("addedToCart");
	}
	
	public void getMasterData(LoginForm form) {
		JavaCartDAO dao = new JavaCartDAO();
		HashMap<Integer, String> hmDept = new HashMap<Integer, String>();
		HashMap<Integer, ArrayList<String>> hmSubDept = new HashMap<Integer, ArrayList<String>>();
		ArrayList<String> arDept = new ArrayList<String>();
		hmDept = dao.getDepartmentData();
		
		for (Map.Entry<Integer, String> dept : hmDept.entrySet()) {
			arDept.add(dept.getValue());
		}
		form.setArDept(arDept);
		
		hmSubDept = dao.getSubDepartmentData();
		form.setHmSubDept(hmSubDept);
	}
}
