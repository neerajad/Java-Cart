<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld" %>
<%@taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld" %>
<%@taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table frame=void rules="all" class = "itemClass" align="center"  bordercolor="white" width="1000px" style="margin-left: 20px; border-radius: 25px;" border="1">
			<logic:notEmpty  name="loginForm" property="arItemBean">
			<%int i = 0; %>
					<logic:iterate id="itemBean" name="loginForm" property="arItemBean">
					
					<% if (i == 0 || i % 4 == 0) { %>
					<tr>
					<% } %> <% i++; %>
						<td>
							<bean:define id="item" name="itemBean" property="itemId"></bean:define>
							<html:img action="/showImage.do" paramName="item" paramId="image" height="300px" />
							<div align="left" style="font-family: Sitka Subheading; font-weight: bold; font-size: 18px">
								<bean:write name="itemBean" property="itemName"/>
							</div>
							<div style="display:inline;float: left;color: #9a0808">
								$<bean:write name="itemBean" property="price"/>
							</div>
							
							<div style="display:inline;float: right;">
								<logic:equal value="Y" name="itemBean" property="availability">
									<div style="color: green; font-style: italic;">Available</div>
								</logic:equal>
								<logic:notEqual value="Y" name="itemBean" property="availability">
									<div style="color: red; font-style: italic;">Out of Stock</div>
								</logic:notEqual>
							</div>
							<%-- <div align="left">
								<logic:equal value="0" name="itemBean" property="condition">Used
								</logic:equal>
								<logic:notEqual value="0" name="itemBean" property="condition"> New
								</logic:notEqual>
							</div> --%>
							<br><div align="left">
								<span class="stars">
									<bean:write name="itemBean" property="rating"/>
								</span>
							</div>
							<div align="left">
							<a title="Add To Cart" onclick="fnAddToCart('<bean:write name="item"/>')"><img src="<%= request.getContextPath()%>/img/addtocart.png" style="border-radius: 50%;" height="40px"/></a>
								<%-- <input type="button" style="border-radius: 50%;background-image: '<%= request.getContextPath()%>/img/javacart.png'" name="cart" value="Add To Cart" onclick="fnAddToCart('<bean:write name="item"/>')"> --%>
							</div>
						</td>
					</logic:iterate>
			</logic:notEmpty>
		</table>