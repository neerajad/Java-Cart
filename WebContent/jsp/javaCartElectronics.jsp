<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java Cart Electronics</title>
</head>
<script type="text/javascript">
function fnAddToCart(itemId) {
	alert('adding item 1');
}
</script>
<body>
<jsp:include page="/jsp/header.jsp" />
Welcome <%=session.getAttribute("user") %> to Electronics section.
<table border="1" cellspacing="0" align="center">
	<tr>
		<td>
			<img alt="img1" src="<%= request.getContextPath()%>/img/elec_apple.jpg"><br />
			<input type="button" value="Add to cart" onclick="fnAddToCart('1');">
		</td>
		<td> <img alt="img2" src="<%= request.getContextPath()%>/img/elec_fon_2.jpg"> </td>
		<td> <img alt="img3" src="<%= request.getContextPath()%>/img/elec_fon_3.jpg"> </td>
	</tr>
	<tr>
		<td> <img alt="img4" src="<%= request.getContextPath()%>/img/elec_lap_2.jpg"> </td>
		<td> <img alt="img5" src="<%= request.getContextPath()%>/img/elec_lap.jpg"> </td>
		<td> <img alt="img6" src="<%= request.getContextPath()%>/img/elec_tab.jpg"> </td>
	</tr>
</table>
<div class="footer">
	<jsp:include page="/jsp/footer.jsp" />
</div>
</body>
</html>