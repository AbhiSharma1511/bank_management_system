<%@ page import="java.util.List, com.entity.Loan"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.dao.LoanDAO, com.dao.LoanDAOImpl, com.entity.Loan"%>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
  <meta http-equiv="Pragma" content="no-cache" />
  <meta http-equiv="Expires" content="0" />
<title>Edit Loan Status</title>
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #f0f2f5;
	margin: 0;
	padding: 0;
}

.container {
	max-width: 400px;
	margin: 60px auto;
	padding: 10px 50px 30px 50px;
	background-color: #ffffff;
	border-radius: 10px;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
	font-size: 20px;
}

h2 {
	text-align: center;
	color: white;
}

label {
	font-weight: bold;
	display: block;
	margin-bottom: 10px;
	color: #555;
}

select {
	width: 100%;
	padding: 10px;
	margin-bottom: 20px;
	border-radius: 6px;
	border: 1px solid #ccc;
	font-size: 16px;
}

p {
	font-size: 18px;
	margin: 12px 0;
	color: #333;
}

input[type="hidden"] {
	display: none;
}

button {
	background-color: #28a745;
	color: white;
	padding: 10px 20px;
	font-size: 16px;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	transition: background-color 0.3s;
}

button:hover {
	background-color: #218838;
}

.cancel-btn {
	margin-left: 20px;
	text-decoration: none;
	font-size: 16px;
	color: #007bff;
	transition: color 0.3s;
	border: 2px solid gray;
	padding: 5px;
	border-radius: 8px;
}

.cancel-btn:hover {
	color: #0056b3;
}
</style>
</head>
<body>
<%
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	%>
<%@ include file="employee_auth.jsp" %>
<div>
<%@ include file="enavbar.html" %>
</div>


	<%
	Loan loan = (Loan) session.getAttribute("loan");
	%>

	<div class="container">
		<div style="width: 100%; background: blue; text-align: center;border-radius: 8px;">
			<h2>Loan Status</h2>
		</div>

		<form action="/Bank_Management_System/viewEditLoanServlet"
			method="post">
			<input type="hidden" name="loanId" value="<%=loan.getLoanId()%>" />
			<div style="display: flex; justify-content: space-between;">
			<p>
				Customer Id: <strong><%=loan.getCustomerId()%></strong>
			</p>
			<p>
				Loan Id: <strong> <%=loan.getLoanId()%> </strong>
			</p>
			
			</div>
			<div style="display: flex; justify-content: space-between;">
			<p>
				Amount: <strong> ₹<%=loan.getLoanAmount()%></strong></p>
			<p>Applied Date: <strong><%= dateFormat.format(loan.getCreatedAt()) %></strong></p>
			</div>

			<label for="status">Change Status:</label> <select name="status"
				id="status">
				<%
				String currentStatus = loan.getStatus().toLowerCase();
				if (currentStatus.equals("approved") || currentStatus.equals("hold")) {
				%>
				<option value="Approved" selected>Approved</option>
				<option value="Hold"
					<%=currentStatus.equals("hold") ? "selected" : ""%>>Hold</option>
				<%
				} 
				else {
				%>
				<option value="Pending"
					<%=currentStatus.equals("pending") ? "selected" : ""%>>Pending</option>
				<option value="Approved"
					<%=currentStatus.equals("approved") ? "selected" : ""%>>Approved</option>
				<option value="Rejected"
					<%=currentStatus.equals("rejected") ? "selected" : ""%>>Rejected</option>
				<option value="Hold"
					<%=currentStatus.equals("hold") ? "selected" : ""%>>Hold</option>
				<%
				}
				%>
			</select>
			<div style="display: flex; justify-content: space-between; align-items: center;">
			<button type="submit">✅ Update</button>
			<a class="cancel-btn" href="loan_management.jsp">🔙 Cancel</a>
			</div>
		</form>
	</div>
<div>
<%@ include file="footer.html" %>
</div>
</body>
</html>
