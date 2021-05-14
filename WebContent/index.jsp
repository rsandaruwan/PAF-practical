<%@page import="com.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery.min.js"></script>
<script src="Components/order.js"></script>
<script src="Components/jquery.min.js"></script>
<link rel="stylesheet" href="assets">
<link rel="stylesheet" href="Views/bootstrap.min.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

	<div class="row">
		<div class="col-lg-12">
			<div class="card m-b-32">
				<div class="card-body">

					<form id="formOrder" name="formOrder" method="post"
						action="index.jsp">

						<label>Order Id</label>
						<input id="orderId"name="orderId" type="text"class="form-control form-control-sm">
						<br>
							
						<label>User ID</label> 
						<input id="userId" name="userId"type="text" class="form-control form-control-sm"> 
							
						<br>
						<label>Funding Bodies ID</label>
						<input id="fundingBodiesId" name="fundingBodiesId"type="text" class="form-control form-control-sm"> 
						
						<br>

						<label>Research Project ID</label>
						<input id="researchProjectId" name="researchProjectId" type="text"class="form-control form-control-sm">
						<br>
							
						<label>Research Project Fund ID</label>
						<input id="researchProjectFundId" name="researchProjectFundId" type="text"class="form-control form-control-sm">
						<br>
							
						<label>Order Total Amount</label>
						<input id="orderTotalAmount" name="orderTotalAmount" type="text"class="form-control form-control-sm">
						<br>
						
						<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary"> <input type="hidden"
						id="hidoIdSave" name="hidoIdSave" value="">
					</form>
					<br>
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br> <br>
					
					<div id="divOrderGrid">
						<%
						Order ordObj = new Order();
											out.print(ordObj.readOrders());
						%>
					</div>
				</div>
</div>
</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>