<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.entity.Transaction"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	background-color: #f2f2f2;
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
			List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
			if (transactions != null && !transactions.isEmpty()) {
				for (Transaction txn : transactions) {
			%>
			<tr>
				<td><%=txn.getTransactionId()%></td>
				<td><%=txn.getSenderAccountNo()%></td>
				<td><%=txn.getReceiverAccountNo()%></td>
				<td><%=txn.getAmount()%></td>
				<td><%=txn.getTransactionDate()%></td>
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
