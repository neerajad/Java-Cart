<%@taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld" %>
<%@taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld" %>
<%@taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld" %>

<ul>
	<logic:iterate id="subDept" name="loginForm" property="arSubDept">
		<li>
				<a class="subDeptClass" style="cursor: pointer;" onclick="fnShowItems('<bean:write name="subDept"/>')"> <bean:write name="subDept"/> </a>
		</li>	
	</logic:iterate>
</ul>