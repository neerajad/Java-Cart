package com.javacart.forms;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.javacart.beans.ItemBean;

public class LoginForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	private String gender;
	private ArrayList<String> arDept = new ArrayList<String>();
	private ArrayList<String> arSubDept = new ArrayList<String>();
	private HashMap<Integer, ArrayList<String>> hmSubDept = new HashMap<Integer, ArrayList<String>>();
	private ArrayList<ItemBean> arItemBean = new ArrayList<ItemBean>();
	private boolean success = true;
	private boolean register;
	private String subDept;
	
	public String getSubDept() {
		return subDept;
	}
	public void setSubDept(String subDept) {
		this.subDept = subDept;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping, ServletRequest request) {
		ActionErrors errors = new ActionErrors();
		System.out.println("coming to validate method");
		System.out.println(userName);
		System.out.println(password);
		
		if (userName == null || password == null) {
			errors.add("error", new ActionMessage("Please enter username and password"));
		}
		return errors;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public boolean isRegister() {
		return register;
	}
	public void setRegister(boolean register) {
		this.register = register;
	}
	public ArrayList<String> getArDept() {
		return arDept;
	}
	public void setArDept(ArrayList<String> arDept) {
		this.arDept = arDept;
	}
	public HashMap<Integer, ArrayList<String>> getHmSubDept() {
		return hmSubDept;
	}
	public void setHmSubDept(HashMap<Integer, ArrayList<String>> hmSubDept) {
		this.hmSubDept = hmSubDept;
	}
	public ArrayList<String> getArSubDept() {
		return arSubDept;
	}
	public void setArSubDept(ArrayList<String> arSubDept) {
		this.arSubDept = arSubDept;
	}
	public ArrayList<ItemBean> getArItemBean() {
		return arItemBean;
	}
	public void setArItemBean(ArrayList<ItemBean> arItemBean) {
		this.arItemBean = arItemBean;
	}
}
