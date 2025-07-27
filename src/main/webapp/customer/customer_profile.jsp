<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.entity.Customer"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Customer Profile</title>
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #eef2f7;
	margin: 0;
}

.container {
	max-width: 500px;
	margin: 30px auto;
	padding: 30px;
	background-color: #ffffff;
	border-radius: 12px;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

h2 {
	text-align: center;
	background-color: #007bff;
	color: white;
	padding: 10px;
	border-radius: 10px;
	margin-bottom: 25px;
}

.info-section {
	margin-bottom: 20px;
}

label {
	display: block;
	margin-top: 15px;
	font-weight: 600;
	color: #333;
}

input[type="text"], input[type="email"] {
	width: 100%;
	padding: 10px;
	border-radius: 6px;
	border: 1px solid #ccc;
	margin-top: 5px;
	font-size: 15px;
	background-color: #f9f9f9;
}

input[readonly], input:disabled {
	background-color: #f0f0f0;
}

.button-group {
	display: flex;
	justify-content: space-between;
	gap: 15px;
	margin-top: 30px;
}

button {
	flex: 1;
	padding: 12px;
	font-size: 15px;
	border: none;
	border-radius: 6px;
	cursor: pointer;
}

#editBtn {
	background-color: #17a2b8;
	color: #fff;
}

#editBtn:hover {
	background-color: #138496;
}

#saveBtn {
	background-color: #28a745;
	color: #fff;
	display: none;
}

#saveBtn:hover {
	background-color: #218838;
}

#cancelBtn {
	background-color: #dc3545;
	color: #fff;
	display: none;
}

#cancelBtn:hover {
	background-color: #c82333;
}

.cust-id-heading {
	display: flex;
	justify-content: space-between;
	align-items: center;
	text-align: center;
	font-size: 18px;
	color: #444;
	margin-top: -10px;
	margin-bottom: 20px;
}
</style>
</head>
<body>

	<%@ include file="navbar.html"%>

	<%
	if(session==null || session.getAttribute("customer")==null){
		response.sendRedirect("clogin.jsp");
		return;
	}
	
		Customer customer = (Customer) session.getAttribute("customer");
		String updateMessage = (String)session.getAttribute("data_update_message");
		session.removeAttribute("data_update_message");
	%>

	<div class="container">
		<h2>👤 Customer Profile</h2>

		<div class="cust-id-heading">
			<span> Customer ID: <strong><%=customer.getCustomerId()%></strong>
			</span> <span> Account No: <strong><%=customer.getAccountNo() %></strong>
			</span>
		</div>

		<form action="/Bank_Management_System/customerProfile"
			method="post" id="editForm">
			<input type="hidden" name="customerId"
				value="<%=customer.getCustomerId()%>" />

			<div class="info-section">
				<label>Name</label> 
				<input type="text" name="name" value="<%=customer.getFirstName() + " " + customer.getLastName()%>"
					readonly /> 
					
				<label>Email</label> 
				<input type="email" name="email" value="<%=customer.getEmail()%>" disabled /> 
					
				<label>Contact</label>
				<input type="text" name="contact"value="<%=customer.getContact()%>" disabled /> 
				
				<label>Address</label>
				<input type="text" name="address" value="<%=customer.getAddress()%>" disabled />
			</div>
				<% if (updateMessage != null) { %>
				  <div style="text-align: center;color: red;font-size:20px;">
				    <p><%= updateMessage %></p>
				  </div>
				<% } %>
				<div class="button-group">
				<button type="button" id="editBtn" onclick="enableEditing()">✏
					Edit</button>
				<button type="submit" id="saveBtn">💾 Save</button>
				<button type="button" id="cancelBtn" onclick="disableEditing()">❌
					Cancel</button>
			</div>
		</form>
	</div>

	<script>
		let originalValues = {};

		window.onload = function() {
			const form = document.getElementById("editForm");
			originalValues.contact = form
					.querySelector('input[name="contact"]').value;
			originalValues.address = form
					.querySelector('input[name="address"]').value;
			originalValues.email = form
					.querySelector('input[name="email"]').value;
		};

		function enableEditing() {
			const form = document.getElementById("editForm");
			form.querySelector('input[name="contact"]').disabled = false;
			form.querySelector('input[name="address"]').disabled = false;
			form.querySelector('input[name="email"]').disabled = false;

			document.getElementById("saveBtn").style.display = "inline-block";
			document.getElementById("cancelBtn").style.display = "inline-block";
			document.getElementById("editBtn").style.display = "none";
		}

		function disableEditing() {
			const form = document.getElementById("editForm");
			form.querySelector('input[name="contact"]').value = originalValues.contact;
			form.querySelector('input[name="address"]').value = originalValues.address;
			form.querySelector('input[name="email"]').value = originalValues.email;

			form.querySelector('input[name="contact"]').disabled = true;
			form.querySelector('input[name="address"]').disabled = true;
			form.querySelector('input[name="email"]').disabled = true;

			document.getElementById("saveBtn").style.display = "none";
			document.getElementById("cancelBtn").style.display = "none";
			document.getElementById("editBtn").style.display = "inline-block";
		}
	</script>

</body>
</html>
