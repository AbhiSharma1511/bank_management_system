<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.entity.Customer"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Customer Dashboard</title>
<style>
body {
	font-family: Arial, sans-serif;
	background: #f4f4f4;
	margin: 0;
}

.container {
	max-width: 800px;
	margin: 40px auto;
	background: white;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
	margin-bottom: 5px;
}

.card1 {
	padding: 20px;
	background: #e9f0ff;
	border-radius: 6px;
	margin-bottom: 20px;
}

.card1 h2 {
	margin: 0 0 10px;
	color: #007bff;
}

.card2 {
	display: flex;
	flex-direction: column;
	gap: 20px;
	margin-top: 30px;
}
.inside-card2{
display:flex;
justify-content: space-around;
align-items: center;
}

.quick-link-card {
  text-align: center;
  border-left: none;
  border-top: 4px solid #003366;
  transition: 0.3s ease;
  background: #f1f6ff;
  width: 40%; 
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.quick-link-card h2 {
	font-size: 30px;
	margin-bottom: 10px;
}

.quick-link-card p a {
	text-decoration: none;
	font-weight: bold;
	font-size: 16px;
	color: #003366;
}

.quick-link-card:hover {
	background-color: #e0ebf8;
	transform: translateY(-5px);
	box-shadow: 0 6px 12px rgba(0, 0, 0, 0.08);
}
</style>
</head>
<body>
		<%
		Customer customer = (Customer) session.getAttribute("customer");
		if (session == null || session.getAttribute("customer") == null) {
	        response.sendRedirect("customer/clogin.jsp"); // redirect to login if no session
	        return;
	    }
		%>

	<%@ include file="navbar.html" %>
	<div class="container">

		<div 
		style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
			<h1>
				Welcome,
				<%=customer.getFirstName() + " " + customer.getLastName()%></h1>
			<div style="display: flex; align-items: center; gap: 10px;">
				<h3>Customer Id(SSN Id):</h3>
				<p><%=customer.getCustomerId()%></p>
			</div>
		</div>

		<div class="card1">
			<div
				style="display: flex; justify-content: space-around; align-items: center;">
				<div style="display: flex; align-items: center; gap: 10px;">
					<h3>Account No:</h3>
					<p><%=customer.getAccountNo()%></p>
				</div>
				<div style="display: flex; align-items: center; gap: 10px;">
					<h3>Balance:</h3>
					<p><%=customer.getBalance()%></p>
				</div>
			</div>
		</div>

		<div class="card2">
			<div class="inside-card2">
				<div class="card quick-link-card">
					<h2>✅</h2>
					<p>
						<a href="/Bank_Management_System/customerProfile">View Profile</a>
					</p>
				</div>

				<div class="card quick-link-card">
					<h2>💸</h2>
					<p>
						<a href="transfer_money.jsp">Transfer Money</a>
					</p>
				</div>
			</div>
			<div class="inside-card2">
				<div class="card quick-link-card">
					<h2>📄</h2>
					<p>
						<a href="/Bank_Management_System/transactionHistory">View Transactions</a>
					</p>
				</div>

				<div class="card quick-link-card">
					<h2>🛠</h2>
					<p>
						<a href="/Bank_Management_System/customer_loan">Manage Loan</a>
					</p>
				</div>
			</div>
		</div>

	</div>
	<%@ include file="footer.html" %>

</body>
</html>