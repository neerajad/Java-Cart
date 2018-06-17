package com.javacart.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.javacart.beans.ItemBean;
import com.javacart.connection.ConnectionManager;

public class JavaCartDAO {
	
	public String getUserDetails(String user, String password) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String title = null;
		StringBuffer getQuery = new StringBuffer("SELECT v_gender FROM TB_USERS WHERE V_NAME = ? AND V_PASSWORD = ?");
		
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(getQuery.toString());
			ps.setString(1, user);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println("in while loop in dao");
				String gender = rs.getString(1);
				if (gender.equalsIgnoreCase("M")) {
					System.out.println("gender is male");
					title = "";
				} else if (gender.equalsIgnoreCase("F")) {
					System.out.println("gender is female");
					title = "";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return title;
	}
	
	public boolean registerUser(String userName, String password, String gender) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int userId = 0;
		boolean status = false;
		
		StringBuffer getQuery = new StringBuffer(
				"select max(i_id) from `database`.`tb_users`;");
		StringBuffer insertQuery = new StringBuffer(
				"INSERT INTO `database`.`tb_users` (`i_id`, `v_name`, `v_gender`, `v_password`) VALUES (?, ?, ?, ?);");
		
		try {
			connection = ConnectionManager.getConnection();
			
			ps = connection.prepareStatement(getQuery.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				userId = rs.getInt(1) + 1;
			}
			
			ps = connection.prepareStatement(insertQuery.toString());
			ps.setInt(1, userId);
			ps.setString(2, userName);
			ps.setString(3, gender);
			ps.setString(4, password);
			int count = ps.executeUpdate();
			
			if (count > 0) {
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public HashMap<String, String> getMasterData() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		HashMap<String, String> subDepHM = new HashMap<String, String>();
		StringBuffer getQuery = new StringBuffer(
				"SELECT sd.v_sub_deptName, d.v_deptName FROM tb_sub_departments sd, tb_departments d where sd.i_deptId = d.i_deptId");
		
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(getQuery.toString());
			rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println("in while loop in master data dao");
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(1));
				subDepHM.put(rs.getString(2), rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subDepHM;
	}
	
	public HashMap<Integer, String> getDepartmentData() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		HashMap<Integer, String> hmDept = new HashMap<Integer, String>();
		StringBuffer getQuery = new StringBuffer(
				"SELECT i_deptId, v_deptName FROM tb_departments");
		
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(getQuery.toString());
			rs = ps.executeQuery();
			
			while (rs.next()) {
				hmDept.put(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hmDept;
	}
	
	public HashMap<Integer, ArrayList<String>> getSubDepartmentData() {
		Connection connection = null;
		PreparedStatement psDept = null;
		PreparedStatement psSubDept = null;
		ResultSet rsDept = null;
		ResultSet rsSubDept = null;
		HashMap<Integer, ArrayList<String>> hmSubDept = new HashMap<Integer, ArrayList<String>>();
		StringBuffer getDept = new StringBuffer(
				"SELECT i_deptId FROM tb_departments");
		StringBuffer getSubDept = new StringBuffer(
				"SELECT v_sub_deptName FROM tb_sub_departments where i_deptId = ?");
		
		try {
			connection = ConnectionManager.getConnection();
			psDept = connection.prepareStatement(getDept.toString());
			rsDept = psDept.executeQuery();
			
			while (rsDept.next()) {
				psSubDept = connection.prepareStatement(getSubDept.toString());
				psSubDept.setInt(1, rsDept.getInt(1));
				rsSubDept = psSubDept.executeQuery();
				
				ArrayList<String> arSubDept = new ArrayList<String>();
				
				while (rsSubDept.next()) {
					arSubDept.add(rsSubDept.getString(1));
				}
				hmSubDept.put(rsDept.getInt(1), arSubDept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hmSubDept;
	}
	
	public ArrayList<ItemBean> getItems(String subDept, String sortby, String exclude, String condition, String rating, String minPrice, String maxPrice) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ItemBean> arItemBean = new ArrayList<ItemBean>();
		String filterStr = "";
		
		if ("true".equals(exclude)) {
			filterStr = " V_AVAILABILITY = 'Y' ";
		}
		if (null != condition) {
			if(filterStr.length() > 0) {
				filterStr = filterStr + " and ";
			}
			filterStr = filterStr + " i_condition = " + condition;
		}
		if (null != rating) {
			if(filterStr.length() > 0) {
				filterStr = filterStr + " and ";
			}
			filterStr = filterStr +  " i_rating >= " + rating;
		}
		if (null != minPrice && minPrice.length() > 0) {
			if(filterStr.length() > 0) {
				filterStr = filterStr + " and ";
			}
			filterStr = filterStr +  " i_price >= " + Integer.parseInt(minPrice);
		}
		if (null != maxPrice && maxPrice.length() > 0) {
			if(filterStr.length() > 0) {
				filterStr = filterStr + " and ";
			}
			filterStr = filterStr +  " i_price <= " + Integer.parseInt(maxPrice);
		}
		if(filterStr.length() > 0) {
			filterStr = " and " + filterStr;
		}
		String sortStr = "asc";
		if ("1".equals(sortby)) {
			sortStr = "desc";
		}
		
		String getQuery = "SELECT i_itemId, v_itemName, i_price, b_image, v_availability, i_condition, i_rating FROM TB_ITEMS " +
				" where i_sub_deptId = (select i_sub_deptId from tb_sub_departments where v_sub_deptName = ? ) " + filterStr + " order by i_price " + sortStr;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(getQuery);
			ps.setString(1, subDept);
			rs = ps.executeQuery();
			
			ItemBean itemBean = null;
			while (rs.next()) {
				itemBean = new ItemBean();
				
				itemBean.setItemId(rs.getInt(1));
				itemBean.setItemName(rs.getString(2));
				itemBean.setPrice (rs.getInt(3));
				itemBean.setAvailability(rs.getString(5).charAt(0));
				itemBean.setCondition(rs.getInt(6));
				itemBean.setRating(rs.getInt(7));
				arItemBean.add(itemBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arItemBean;
	}
	
	public ArrayList<ItemBean> getUserCart(String user) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement psDetails = null;
		ResultSet rsDetails = null;
		ArrayList<ItemBean> arItemBean = new ArrayList<ItemBean>();
		StringBuffer getItems = new StringBuffer("select itemId,quantity from tb_cart where username = ?");
		
		StringBuffer getItemDetails = new StringBuffer("SELECT i_itemId, v_itemName, V_AVAILABILITY, i_price, b_image FROM TB_ITEMS ")
				.append(" where i_itemId = ? ");
		
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(getItems.toString());
			int totalPrice = 0;
			ps.setString(1, user);
			rs = ps.executeQuery();
			
			ItemBean itemBean = null;
			while (rs.next()) {
				itemBean = new ItemBean();
				
				psDetails = connection.prepareStatement(getItemDetails.toString());
				psDetails.setInt(1, rs.getInt(1));
				rsDetails = psDetails.executeQuery();
				
				int itemPrice = 0;
				while (rsDetails.next()) {
					itemBean.setItemId(rsDetails.getInt("i_itemId"));
					itemBean.setItemName(rsDetails.getString("v_itemName"));
					itemBean.setAvailability(rsDetails.getString("V_AVAILABILITY").charAt(0));
					itemPrice = rsDetails.getInt("i_price");
					itemBean.setPrice(itemPrice);
				}
				if (itemBean.getAvailability() == 'Y') {
					totalPrice = totalPrice + (rs.getInt(2) * itemPrice);
				}
				itemBean.setTotalPrice(totalPrice);
				itemBean.setQuantityinCart(rs.getInt(2));
				arItemBean.add(itemBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arItemBean;
	}
	
	public boolean addToCart(String user, String itemId) {
		Connection connection = null;
		PreparedStatement psInsert = null;
		PreparedStatement psUpdate = null;
		PreparedStatement psExist = null;
		boolean status = false;
		ResultSet rs = null;
		int stat = 0;
		StringBuffer checkIfExists = new StringBuffer("select * from tb_cart where username = ? and itemId = ?");
		StringBuffer insertQuery = new StringBuffer("insert into tb_cart (username, itemId, quantity) values (?, ?, ? )");
		StringBuffer updateQuery = new StringBuffer("update tb_cart set quantity = ? where username = ? and itemId = ?");
		
		try {
			connection = ConnectionManager.getConnection();
			psExist = connection.prepareStatement(checkIfExists.toString());
			psExist.setString(1, user);
			psExist.setInt(2, Integer.parseInt(itemId));
			rs = psExist.executeQuery();
			
			if (rs.next()) {
				psUpdate = connection.prepareStatement(updateQuery.toString());
				psUpdate.setInt(1, rs.getInt("QUANTITY") + 1);
				psUpdate.setString(2, user);
				psUpdate.setInt(3, Integer.parseInt(itemId));
				stat = psUpdate.executeUpdate();
			} else {
				psInsert = connection.prepareStatement(insertQuery.toString());
				psInsert.setString(1, user);
				psInsert.setInt(2, Integer.parseInt(itemId));
				psInsert.setInt(3, 1);
				stat = psInsert.executeUpdate();
			}
			if (stat > 0) {
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}
