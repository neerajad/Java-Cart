<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld" %>
<%@taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld" %>
<%@taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% int itemcount = 0; %>
<% int outOfStockcount = 0; %>
<table frame=void rules="rows" class = "cartClass" align="left"  bordercolor="white" width="70%" style="margin-left: 20px; margin-right: 20px; margin-top: 20px; margin-bottom: 50px; border-radius: 25px;" border="1">
	<logic:notEmpty  name="loginForm" property="arItemBean">
		<tr>
			<td colspan="2" style="font-size: 23px; font-weight: bold;font-family: cursive;">My Cart</td>
			<td style="color: #72787b">Price</td>
			<td style="color: #72787b">Quantity</td>
		</tr>
		<logic:iterate id="itemBean" name="loginForm" property="arItemBean">
		<%itemcount++; %>
			<tr>
				<td>
					<bean:define id="item" name="itemBean" property="itemId"></bean:define>
					<html:img action="/showImage.do" paramName="item" paramId="image" width="50px" height="50px" />
				</td>
				<td style="color: #0404bb;">
					<bean:write name="itemBean" property="itemName"/> <br>
					<logic:equal value="Y" name="itemBean" property="availability">
						<div style="color: green; font-style: italic;">Available</div>
					</logic:equal>
					<logic:notEqual value="Y" name="itemBean" property="availability">
					<%outOfStockcount++; %>
						<div style="color: red; font-style: italic;">Out of Stock</div>
					</logic:notEqual>
				</td>
				<td>
					$<bean:write name="itemBean" property="price"/>
				</td>
				<td style="padding-right: 0px">
					<input type="hidden" id="<%=itemcount %>_item" value='<bean:write name="itemBean" property="itemId"/>' />
					<input type="text" style="text-align: center;"  size="5" id="<%=itemcount %>_quantity" value='<bean:write name="itemBean" property="quantityinCart"/>'/>
				</td>
				<td align="left" style="padding-left: 0px">
					<a title="Delete from Cart" onclick="fnDeleteFromCart('<bean:write name="item"/>')"><img src="<%= request.getContextPath()%>/img/delete.png" style="border-radius: 50%;" height="20px"/></a>
				</td>
			</tr>	
		</logic:iterate>
		<tr>
		<td colspan="5" align="center"><input type="button" onclick="fnUpdateCart(<%=itemcount++%>);"  value ="Update Cart" style="background-color: #fb641b;color: white"/></td>
	</tr>
	</logic:notEmpty>
</table>
<table width="20%" class="carttotalClass" align="right" style="border-spacing: 10px;">
	<tr>
		<td style="font-style: italic;">
			Price (<%=itemcount %> items)
		</td>
		<td style="color: #9a0808">
			$<bean:write name="itemBean" property="totalPrice"/>
		</td>
	</tr>
	<tr>
		<td style="font-style: italic;">
			Delivery Charges
		</td>
		<td style="color: green">
			Free
		</td>
	</tr>
	<tr style="border-top: 1px dashed;" >
		<td style="font-style: italic;">
			Subtotal
		</td>
		<td style="color: #9a0808">
			$<bean:write name="itemBean" property="totalPrice"/>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2"><input type="button" value ="Proceed To Checkout" style="background-color: #fb641b;color: white"/></td>
	</tr>
</table>
<% if (outOfStockcount > 0) { %>
<table width="20%" align="right">
	<tr>
		<td style="font-style: italic;color: red; font-size: 12px;">
			Note: Some items in your cart are out of stock. Please remove them to proceed with checkout
		</td>
	</tr>
</table>
<% } %>