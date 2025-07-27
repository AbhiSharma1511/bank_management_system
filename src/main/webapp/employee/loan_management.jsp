<%@ page import="java.util.List, com.entity.Loan"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.dao.LoanDAO, com.dao.LoanDAOImpl"%>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>

<title>Loan Management</title>
<style>
body {
	font-family: Arial, sans-serif;
	background: #f4f4f4;
	margin: 0;
} 

.container {
	max-width: 900px;
	margin: 40px auto;
	background: #fff;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
	margin-bottom: 30px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 12px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #007bff;
	color: white;
}

.status {
	font-weight: bold;
	padding: 6px 10px;
	border-radius: 4px;
	color: white;
}

.approved {
	background-color: #28a745;
}

.pending {
	background-color: #ffc107;
}

.rejected {
	background-color: #dc3545;
}

.add-button {
	display: inline-block;
	padding: 10px 15px;
	background-color: #28a745;
	color: white;
	text-decoration: none;
	border-radius: 4px;
	margin-top: 20px;
}

.add-button:hover {
	background-color: #218838;
}
</style>
</head>
<body>
<%@ include file="employee_auth.jsp" %>
	<%
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	%>
	<%
	List<Loan> loanList = (List<Loan>) request.getAttribute("loanList");
	if (loanList == null) {
		LoanDAO loanDao = new LoanDAOImpl();
		loanList = loanDao.getAllLoans();
	}
	%>

	<div id="navbar"></div>

	<div class="container">
		<h1>Loan Application Dashboard</h1>

		<table>
			<thead>
				<tr>
					<th>Loan ID</th>
					<th>Customer</th>
					<th>Amount</th>
					<th>Status</th>
					<th>Applied Date</th>
					<th>Last Update </th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (loanList != null && !loanList.isEmpty()) {
					for (Loan loan : loanList) {
						String statusClass = "";
						String status = loan.getStatus().toLowerCase();
						switch (status) {
					case "approved" :
						statusClass = "approved";
						break;
					case "rejected" :
						statusClass = "rejected";
						break;
					default :
						statusClass = "pending";
						break;
						}
				%>
				<tr>
					<td><%=loan.getLoanId()%></td>
					<td><%=loan.getCustomerId()%></td>
					<td>₹<%=String.format("%,.2f", loan.getLoanAmount())%></td>
					<td><span class="status <%=statusClass%>"><%=loan.getStatus()%></span></td>
					<td><%= dateFormat.format(loan.getCreatedAt()) %></td>
					<td><%= dateFormat.format(loan.getUpdatedAt()) %></td>
					<td><a href="/Bank_Management_System/viewEditLoanServlet?loanId=<%=loan.getLoanId()%>"
					style="border: 2px solid blue; border-radius:5px; padding:4px; text-decoration: none">✏️
							View</a></td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="5" style="text-align: center;">No loan
						applications found.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

	<div id="footer"></div>

	<script>
  fetch("enavbar.html")
    .then(res => res.text())
    .then(data => document.getElementById("navbar").innerHTML = data);

  fetch("footer.html")
    .then(res => res.text())
    .then(data => document.getElementById("footer").innerHTML = data);
  
  window.addEventListener("pageshow", function (event) {
	  if (event.persisted || performance.getEntriesByType("navigation")[0].type === "back_forward") {
	    window.location.reload(); // Force session re-check
	  }
	});
</script>

</body>
</html>
