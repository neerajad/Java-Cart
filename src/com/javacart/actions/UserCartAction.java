package com.javacart.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.javacart.beans.ItemBean;
import com.javacart.daos.JavaCartDAO;
import com.javacart.daos.UserCartDAO;
import com.javacart.forms.LoginForm;

public class UserCartAction extends DispatchAction{

	public ActionForward deleteFromCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LoginForm loginForm = (LoginForm)form;
		
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		String itemId = (String) request.getParameter("itemId");
		ArrayList<ItemBean> arItemBean = new ArrayList<ItemBean>();
		UserCartDAO usercartdao = new UserCartDAO();
		usercartdao.deleteFromCart(user, itemId);
		
		JavaCartDAO dao = new JavaCartDAO();
		arItemBean = dao.getUserCart(user);
		
		loginForm.setArItemBean(arItemBean);
		
		return mapping.findForward("showCart");
	}
}
