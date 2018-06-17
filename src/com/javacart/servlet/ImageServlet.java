package com.javacart.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javacart.connection.ConnectionManager;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("coming to doget method---");
		System.out.println(request.getParameter("image"));
		
		String image = request.getParameter("image");
		// byte[] imgByt = Base64.decodeBase64(image);
		int itemId = Integer.parseInt(image);
		System.out.println(itemId);
		
		//BASE64Decoder decoder = new BASE64Decoder();
		//byte[]imgByt = decoder.decodeBuffer(image);
		
		byte[] imgByt = getItems(itemId);
		System.out.println(imgByt);
		response.setContentType("image/jpeg");
		OutputStream out = response.getOutputStream();
		out.write(imgByt);
	//	out.flush();
		//out.close();
		
	}
	
	public byte[] getItems(int itemId) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		byte[] binaryStream = null;
		ArrayList<InputStream> arImage = new ArrayList<InputStream>();
		StringBuffer getQuery = new StringBuffer("SELECT b_image FROM TB_ITEMS where i_itemId = ? ");
		
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(getQuery.toString());
			ps.setInt(1, itemId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				binaryStream = rs.getBytes(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return binaryStream;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("coming to do post method----");
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
