<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.entity.Customer"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Transfer Money</title>
<style>
body {
	font-family: Arial, sans-serif;
	background: #f4f4f4;
	margin: 0;
}

.container {
	max-width: 600px;
	margin: 40px auto;
	background: white;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
	margin-bottom: 25px;
}

label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

input {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border-radius: 4px;
	border: 1px solid #ccc;
}

button {
	width: 100%;
	background: #007bff;
	color: white;
	padding: 12px;
	border: none;
	font-size: 16px;
	border-radius: 4px;
	cursor: pointer;
}

button:hover {
	background: #0056b3;
}

.success {
	color: green;
	text-align: center;
	font-size: 18px;
}
</style>
</head>
<body>

	<div id="navbar"><%@ include file="navbar.html"%></div>
	<%
	if (session == null || session.getAttribute("customer") == null) {
		response.sendRedirect("clogin.jsp");
		return;
	}
	%>
	<%
	Customer customer = (Customer) session.getAttribute("customer");
	String message = (String) request.getAttribute("message");
	String msg="";
	if(message != null){
		msg = message;
	}
	%>

	<div class="container">
		<h1>Transfer Money</h1>
		<form id="transferForm" action="/Bank_Management_System/transferMoney"
			method="post" onSubmit="return validateForm();">

			<label for="fromAccount">Sender Account Number</label> <input
				type="text" id="fromAccount" value="<%=customer.getAccountNo()%>"
				readonly> <label for="toAccount">Sender Name</label> <input
				type="text" id="customer"
				value="<%=customer.getFirstName() + " " + customer.getLastName()%>"
				readonly> <label for="toAccount">Recipient Account
				Number</label> <input type="text" id="toAccount" placeholder="ACC1234" name="receiverAccount"
				required> <label for="amount">Amount (₹)</label> <input
				type="number" id="amount" min="1" name="amount" required> <label
				for="remark">Remark</label> <input type="text" id="remark">

			<button type="submit">Send Money</button>
			<p class="success" id="msg"><%=msg%></p>
		</form>
	</div>

	<div id="footer"><%@ include file="footer.html"%></div>

<script>

function validateForm(){
	const amount = document.getElementById("amount").value;
	const senderAccount = document.getElementById("toAccount").value;
	if(confirm("Your want to send amount: "+ amount +"\n To account: "+ senderAccount+"?")){
		return true;
	}
	return false;
}

</script>

</body>
</html>
