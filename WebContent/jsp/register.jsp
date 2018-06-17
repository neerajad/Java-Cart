<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld" %>
<%@taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld" %>
<%@taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld" %>
<html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp" />
<jsp:useBean id="loginForm" class="com.javacart.forms.LoginForm" scope="request"> </jsp:useBean>
	<html:form action="/loginCheck.do?operation=register">
		<table align="center" style="margin-top: 100pt;" cellpadding="5" cellspacing="5">
			<tr>
				<td>
					<bean:message key="label.userName"/>
				</td>
				<td>
					<html:text property="userName" name="loginForm" />
				</td>
			</tr>
			<tr>
				<td>
					<bean:message key="label.password"/>
				</td>
				<td>
					<html:password property="password" name="loginForm" />
				</td>
			</tr>
			<tr>
				<td>
					<bean:message key="label.gender"/>
				</td>
				<td>
					<html:select property="gender">
						<html:option value="M">Male</html:option>
						<html:option value="F">Female</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<html:submit value="Register" title="Register" property="Register" />
				</td>
			</tr>
		</table>
	</html:form>
	<div class="footer">
		<jsp:include page="/jsp/footer.jsp" />
	</div>
</body>
</html>