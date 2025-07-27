<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.entity.Transaction"%>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
  <meta http-equiv="Pragma" content="no-cache" />
  <meta http-equiv="Expires" content="0" />
<title>Manage Transactions</title>
<style>
body {
	font-family: Arial;
	padding:0px;
	margin:0px
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
	
}

th {
	background: green;
	color:white;
}

form {
	margin-bottom: 20px;
}

label, input, select {
	margin-right: 10px;
}

.filter-box {
	padding: 15px;
	border: 1px solid #ccc;
	margin-bottom: 20px;
}
</style>
</head>
<body>
<%@ include file="employee_auth.jsp" %>
<%
SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm a");
%>
	<div><%@ include file="enavbar.html"%></div>
	
	<div style="padding: 20px">
	
	<h2>Transaction Management</h2>

	<div class="filter-box">
		<form action="FilterTransactionServlet" method="get">
			<label>Account No:</label> <input type="text" name="accountNo"
				placeholder="Enter account number" /> <label>Amount > </label> <input
				type="number" name="amountGreaterThan" placeholder="5000" /> <label>Time
				Filter:</label> <select name="timeFilter">
				<option value="">--Select--</option>
				<option value="last1hr">Last 1 Hour</option>
				<option value="last24hr">Last 24 Hours</option>
			</select> <input type="submit" value="Apply Filters" />
		</form>
	</div>

	<table>
		<thead>
			<tr>
				<th>Transaction ID</th>
				<th>Sender Account</th>
				<th>Receiver Account</th>
				<th>Amount</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<Transaction> transactions = (List<Transaction>) session.getAttribute("transactions");
			session.removeAttribute("transactions");
			if (transactions != null && !transactions.isEmpty()) {
				for (Transaction txn : transactions) {
			%>
			<tr>
				<td><%=txn.getTransactionId()%></td>
				<td><%=txn.getSenderAccountNo()%></td>
				<td><%=txn.getReceiverAccountNo()%></td>
				<td><%=txn.getAmount()%></td>
				<td><%=dateFormat.format(txn.getTransactionTime())%></td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="5">No transactions found.</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	</div>
<div><%@ include file="footer.html"%></div>
</body>
</html>
