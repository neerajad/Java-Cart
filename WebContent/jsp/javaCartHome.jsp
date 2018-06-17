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
<title>Java Cart Home</title>

<style type="text/css">
#menuBar ul {
list-style: none;
/* height: 30px; */
}

#menuBar ul li{
float: left;
background-color: #fdf9f9d9;
border: 1px solid white;
text-align: center;
line-height: 45px;
width: 150px;
height: 40px;
position: relative;
}

#menuBar ul li:HOVER{
background-color: white;

}

#menuBar ul li a {
    text-decoration: none;
    display: block;
}

/* #menuBar ul ul {
    position: absolute;
    display: none;
} */

</style>


<script language="JavaScript" type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript">

$( function() {
    $( "#slider-range" ).slider({
      range: true,
      min: 0,
      max: 500,
      values: [ 0, 500 ],
      slide: function( event, ui ) {
        $( "#minPrice" ).val( "" + ui.values[ 0 ]);
        $( "#maxPrice" ).val( "" + ui.values[ 1 ] );
      }
    });
  } );
  
	function showSubMenu(menuId) {
		var childId = '#child' + menuId;
		$.ajax({
			type : 'POST',
			url : '<%= request.getContextPath()%>/login.do?operation=showSubMenu',
			data : { deptId : menuId },
			success : function(data) {
				$(".innerDivClass").empty();
				$('#child'+menuId).html(data);
			}
		})
	}
	
	var selDept;
	function fnSortItems(selected) {
		
		$.ajax({
			type : 'POST',
			url : '<%= request.getContextPath()%>/login.do?operation=showItems',
			data : { subDep : selDept, sortby : selected},
			success : function(data) {
				$('#itemsBody').show();
				$("#items").html(data);
			}
		})
	}
	
	function fnShowCart() {
		$.ajax({
			type : 'POST',
			url : '<%= request.getContextPath()%>/login.do?operation=showCart',
			data : {},
			success :function(data) {
				$('#itemsBody').hide();
				$(".innerDivClass").empty();
				$('#cartDiv').show();
				$("#cartDiv").html(data);
			}
		})
	}
	
	function fnAddToCart(itemId) {
		
		$.ajax({
			type : 'POST',
			url : '<%= request.getContextPath()%>/login.do?operation=addToCart',
			data : { item  : itemId },
			success :function(data) {
				alert('Product added to cart');
			}
		})
	}
	
	function fnApplyFilter() {
		
		var exclude = $('#excludeid').is(':checked');
		var condition = $('input[name=condition]:checked').val();
		var rating = $('input[name=rating]:checked').val();
		var minPrice = $('#minPrice').val();
		var maxPrice = $('#maxPrice').val();

		 $.ajax({
			type : 'POST',
			url : '<%= request.getContextPath()%>/login.do?operation=showItems',
			data : { subDep:selDept, filter:'yes', exclude:exclude, condition:condition, rating:rating, minPrice:minPrice, maxPrice:maxPrice},
			success : function(data) {
				$('#itemsBody').show();
				$("#items").html(data);
				$('span.stars').stars();
			}
		})
	}
	function fnReset() {
		$('input[name=rating],input[name=condition],#excludeid').prop('checked', false);
		$('#minPrice').val("");
		$('#maxPrice').val("");
	}
	
	function fnDeleteFromCart(itemId) {
		if(window.confirm('Are you sure you want to remove this ite from cart?')) {
			$.ajax({
				type : 'POST',
				url : '<%= request.getContextPath()%>/userCart.do?operation=deleteFromCart',
				data : { itemId : itemId},
				success : function(data) {
					$(".innerDivClass").empty();
					$('#cartDiv').show();
					$("#cartDiv").html(data);
				}
			})
		}
	}
	
	function fnUpdateCart(itemsCount) {
		var i;
		var arr = [];
		for (i = 1; i < 3; i++) {
			var itemid = '#' + i +'_item';
			var quantity = '#' + i +'_quantity';
			var obj = {itemId: $(itemid).val(), quantity: $(quantity).val()};
		    arr.push(obj);
			//alert($(itemid).val());
		   // alert($(quantity).val());
		}
		alert(JSON.stringify(arr));
	}
	
	function fnShowItems(subDept) {
		selDept = subDept;
		$.ajax({
			type : 'POST',
			url : '<%= request.getContextPath()%>/login.do?operation=showItems',
			data : { subDep : subDept,sortby : 0 },
			success : function(data) {
				$(".innerDivClass").empty();
				$('#itemsBody').show();
				$('#cartDiv').hide();
				$("#items").html(data);
				$('span.stars').stars();
			}
		})
	}
	
	$.fn.stars = function() {
	    return $(this).each(function() {
	        // Get the value
	        var val = parseFloat($(this).html());
	        // Make sure that the value is in 0 - 5 range, multiply to get width
	        val = Math.round(val * 4) / 4; /* To round to nearest quarter */
			val = Math.round(val * 2) / 2; /* To round to nearest half */
	        var size = Math.max(0, (Math.min(5, val))) * 16;
	        // Create stars holder
	        var $span = $('<span />').width(size);
	        // Replace the numerical value with stars
	        $(this).html($span);
	    });
	}
	
	$( document ).ready(function() {
		$('#itemsBody').hide();
		$('#menuBar').mouseleave(function(){
			$(".innerDivClass").empty();
		});
	});
	
</script>

</head>
<body background="<%= request.getContextPath()%>/img/background.jpg">
	<jsp:include page="/jsp/header.jsp" />
	<jsp:useBean id="loginForm" class="com.javacart.forms.LoginForm" scope="request"></jsp:useBean>
	<form action="<%= request.getContextPath()%>/login.do?operation=showCart" id="home">
	<table><tr><td>
		<div id="menuBar">
			<ul>
				<%int i = 1; %>
					<logic:iterate id="dept" name="loginForm" property="arDept">
						<li>
							<a style="cursor: pointer; text-decoration: none;" onmouseover="showSubMenu(this.id);" id="<%=i%>"> <bean:write name="dept"/> </a>
							<div class="innerDivClass" id="child<%=i%>">
							</div>
						</li>
						<%i = i + 1; %>
					</logic:iterate>
			</ul>
		</div>
	</td></tr>
	<tr><td>
		<div id="itemsBody" align="left">
			<table align="right" style="margin-top: 50px; margin-right: 70px; margin-bottom: 30px">
	<tr>
		<td>Sort By : </td>
		<td>
			<select onchange="fnSortItems(this.value);">
				<option value="0" style="font-style: italic;">Price : Low To High</option>
				<option value="1" style="font-style: italic;">Price : High To Low</option>
			</select>
		</td>
	<tr>
</table>
<table frame=void rules=rows class = "filterClass" align="left" border="1" bordercolor="white" width="15%" style="margin-top: 90px; border-radius: 25px;" height="300px">
	<tr align="center">
		<td style="color: #6666ff; font-weight: bold; font-size: 14">Refine By</td>
	</tr>
	<tr>
		<td>
			<table>
				<tr>
					<td style="color: #6666ff; font-weight: bold;">Price</td>
				</tr>
				<tr>
					<td><div id="slider-range"></div></td>
				</tr>
				<tr>
					<td><input type="text" id = "minPrice" size="3"/> To <input type="text" id = "maxPrice" size="3"/></td>
				</tr>	
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table>
				<tr>
					<td style="color: #6666ff; font-weight: bold;" colspan="2" >Availability</td>
				</tr>
				<tr>
					<td><input type="checkbox" id = "excludeid"></td>
					<td style="width: 90%">Exclude out of stock</td>
				</tr>
					
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table>
				<tr>
					<td style="color: #6666ff; font-weight: bold;">Condition</td>
				</tr>
				<tr>
					<td>
						<input type="radio" name="condition" value="1">New<br>
  						<input type="radio" name="condition" value="0">Used<br>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table>
				<tr>
					<td style="color: #6666ff; font-weight: bold;">Avg. Customer Review</td>
				</tr>
				<tr>
					<td>
						<input type="radio" name="rating" value="4">4 Stars & above<br>
  						<input type="radio" name="rating" value="3">3 Stars & above<br>
  						<input type="radio" name="rating" value="2">2 Stars & above<br>
  						<input type="radio" name="rating" value="1">1 Stars & above<br>
					</td>
				</tr>
					
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table align="center">
				<tr>
					<td><input type="button" value="Apply" onclick="fnApplyFilter()"/>  </td>
					<td><input type="button" value="Reset" onclick="fnReset()"/>  </td>
				</tr>
			</table>
		</td>
	</tr>
</table>
		<div id="items" align="left" style="display: inline-block;"></div>
		</div>
		
		<!-- <div id="cartDiv">
		</div> -->
	</td></tr></table>
	<div id="cartDiv" style="width: 100%">
		</div>
	</form>
	<div class="footer">
		<jsp:include page="/jsp/footer.jsp" />
	</div>
</body>
</html>