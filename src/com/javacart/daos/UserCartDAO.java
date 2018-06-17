package com.javacart.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.javacart.connection.ConnectionManager;

public class UserCartDAO {
	
	public void deleteFromCart(String user, String itemId) {
		Connection connection = null;
		PreparedStatement ps = null;
		StringBuffer deleteItem = new StringBuffer("delete from tb_cart where username = ? and ITEMID = ?");
		
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(deleteItem.toString());
			ps.setString(1, user);
			ps.setInt(2, Integer.parseInt(itemId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
