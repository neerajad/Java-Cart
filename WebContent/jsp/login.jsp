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
<title>Java Cart</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp" />
<jsp:useBean id="loginForm" class="com.javacart.forms.LoginForm" scope="request"></jsp:useBean>
<html:errors/>
	<html:form action="/loginCheck.do?operation=login" method="post">
		<table align="center" style="margin-top: 50px; ">
			<logic:equal value="false" property="success" name="loginForm">
				<tr>
					<td class="errMsg">
						<bean:message key="er.login.fail" /> <a href="<%=request.getContextPath()%>/jsp/register.jsp"> here</a>
				</td>
				</tr>
			</logic:equal>
			<logic:equal value="true" property="register" name="loginForm">
				<tr>
					<td class="success">
						<bean:message key="succ.register" />
					</td>
				</tr>
			</logic:equal>
		</table>
		<table align="center" style="margin-top: 70pt;" cellpadding="5">
			<tr>
				<td>
					<bean:message key="label.userName"/>
				</td>
				<td>
					<html:text property="userName" name="loginForm"></html:text>
				</td>
			</tr>
			<tr>
				<td>
					<bean:message key="label.password"/>
				</td>
				<td>
					<html:password property="password" name="loginForm"></html:password>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="login" name="login" />
				</td>
			</tr>
		</table>
	</html:form>
	<div class="footer">
		<jsp:include page="/jsp/footer.jsp" />
	</div>
</body>
</html>