<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<table width="100%" cellpadding="2" cellspacing="0">
		<tr bgcolor="#0174DF">
			<td align="left" width="30%">
				<img alt="image" src="<%= request.getContextPath()%>/img/javacart.png" height="50px" width="70px">
			</td>
			<td align="left" style="font-family: algerian; font-size: 30px; color: white" width="50%">
				Java Shopping Cart
			</td>
			<td align="left" style="font-family: Arial Black; font-size: 13px; color: white" >
				Login
			</td>
			<td align="left" style="font-family: Arial Black; font-size: 13px; color: white" >
				<a title="Show Cart" onclick="fnShowCart()"><img src="<%= request.getContextPath()%>/img/addtocart.png" style="border-radius: 50%;" height="40px"/></a>
			</td>
			<td align="left" style="font-family: Arial Black; font-size: 13px; color: white" >
				Contact Us
			</td>
		</tr>
	</table>
</body>
</html>