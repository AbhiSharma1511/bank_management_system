<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.entity.Loan"%>
<%
List<Loan> loanList = (List<Loan>) session.getAttribute("loanList");
String add_message = (String) session.getAttribute("loan_add_message");
String delete_message = (String) session.getAttribute("loan_delete_message");
session.removeAttribute("loan_delete_message");
session.removeAttribute("loan_add_message");
//session.removeAttribute("loanList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Loan Dashboard</title>
<style>
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: column;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f9f9f9;
	flex: 1;
}

.container {
	flex: 1;
	width: 80%;
	margin: 30px auto;
	padding: 20px;
	background-color: #ffffff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
}

h2 {
	color: #333;
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 30px;
}

th, td {
	padding: 12px 15px;
	border: 1px solid #ddd;
	text-align: center;
}

th {
	background-color: #4CAF50;
	color: white;
}

td {
	background-color: #fafafa;
}

.message {
	color: green;
	font-weight: bold;
	margin-bottom: 20px;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 16px;
	transition: background-color 0.3s ease;
}

button:hover {
	background-color: #45a049;
}

#loanForm {
	background-color: #f2f2f2;
	padding: 20px;
	border-radius: 8px;
	margin-top: 15px;
	box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

label {
	font-weight: bold;
}

input[type="number"], input[type="submit"] {
	width: 100%;
	padding: 10px;
	margin-top: 8px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type="submit"] {
	background-color: #4CAF50;
	color: white;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

input[type="submit"]:hover {
	background-color: #45a049;
}

.footer {
	text-align: center;
	padding: 15px 0;
}
</style>
</head>
<body>
	<%
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
	%>
	<div>
		<%@ include file="navbar.html"%>
		<div class="container">
			<h2>Loan History</h2>

			<%
			if (add_message != null) {
			%>
			<div style="color: green; margin-bottom: 10px;"><%=add_message%></div>
			<%
			}
			%>
			<%
			if (delete_message != null) {
			%>
			<div style="color: green; margin-bottom: 10px;"><%=delete_message%></div>
			<%
			}
			%>
			<table>
				<tr>
					<th>Loan ID</th>
					<th>Amount</th>
					<th>Status</th>
					<th>Applied On</th>
					<th>Last Updated</th>
					<th>Action</th>
				</tr>
				<%
				if (loanList != null && !loanList.isEmpty()) {
					for (Loan loan : loanList) {
				%>
				<tr id="row-<%=loan.getLoanId()%>">
					<td><%=loan.getLoanId()%></td>
					<td>₹<%=loan.getLoanAmount()%></td>
					<td><%=loan.getStatus()%></td>
					<td><%=dateFormat.format(loan.getCreatedAt())%></td>
					<td><%=dateFormat.format(loan.getUpdatedAt())%></td>
					<td>
						<%
						String status = loan.getStatus().toLowerCase();
						if (status.equals("pending")) {
						%>
						<button onclick="deleteLoan(<%=loan.getLoanId()%>)" style="background-color: red;">Delete</button>
						 <%
						} else {
						%>
						<div style="color: gray; font-size: 18px;">
							<%
							if (status.equals("approved")) {
							%>
							<strong style="color: green">Approved</strong> 
							
							<%
							} else if (status.equals("rejected")) {
							%>
							<strong style="color: red">Rejected</strong> 
							<%
							} else if (status.equals("hold")) {
							%>
							<strong style="color: #F7BD00">On Hold:</strong> 
							wait for further updates.
							<%
							} else {
							%>
							<strong>Status:</strong> Action not allowed on this loan.
							<%
							}
							%>
						</div> <%
 }
 %>
					</td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="6">No loans found.</td>
				</tr>
				<%
				}
				%>
			</table>


			<h2>Apply for New Loan</h2>
			<button onclick="toggleForm()">Apply New Loan</button>

			<div id="loanForm" style="display: none;">
				<form action="/Bank_Management_System/customer_loan" method="post"
					onSubmit="return validateLoan();">
					<label for="loanAmount">Loan Amount (₹):</label> <input
						type="number" name="loanAmount" id="loanAmount" min="1000"
						step="1000" placeholder="Min 1000 Loan " required> <input
						type="submit" value="Apply Now">
				</form>
			</div>
		</div>

		<div class="footer"><%@ include file="footer.html"%></div>
	</div>

	<script>
		function toggleForm() {
			const form = document.getElementById('loanForm');
			form.style.display = form.style.display === 'none' ? 'block' : 'none';
		}
		

		  function validateLoan() {
		    const loanAmount = document.getElementById("loanAmount").value;

		    if (confirm("You want to apply for loan amount: ₹" + loanAmount)) {
		      return true;
		    }
		    return false;
		  }

		
	
		  function deleteLoan(loanId) {
			  if (confirm("Are you sure you want to delete loan ID: " + loanId + "?\nThis action cannot be undone.")) {
			    window.location.href = "/Bank_Management_System/customer_loan?loanId=" + loanId;
			  }
			}

		</script>

</body>
</html>
