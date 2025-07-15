<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.entity.Customer" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Manage Customers</title>
<style>
body {
	font-family: Arial, sans-serif;
	background: #f4f4f4;
	margin: 0;
}

.container {
	max-width: 1000px;
	margin: 40px auto;
	background: #fff;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1, h2 {
	text-align: center;
	margin-bottom: 30px;
}

input[type="text"] {
	padding: 8px;
	width: 100%;
	margin-bottom: 20px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 40px;
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

.add-button {
	margin-top: 10px;
	display: inline-block;
	padding: 10px 15px;
	background-color: #28a745;
	color: white;
	text-decoration: none;
	border-radius: 4px;
}

.add-button:hover {
	background-color: #218838;
}
</style>
</head>
<body>
<div id="navbar"></div>

<div class="container">
  <h1>Customer Account Management</h1>

  <input type="text" placeholder="Search by name or email..." id="searchInput" onkeyup="searchTable()">

  <h2>🟢 Active Customers</h2>
  <table id="activeCustomerTable">
    <thead>
      <tr>
        <th>Customer ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Balance</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
    <%
      List<Customer> activeCustomers = (List<Customer>) request.getAttribute("activeCustomers");
      if (activeCustomers != null) {
        for (Customer cust : activeCustomers) {
    %>
      <tr>
        <td><%= cust.getCustomerId() %></td>
        <td><%= cust.getFirstName() + " " + cust.getLastName() %></td>
        <td><%= cust.getEmail() %></td>
        <td>₹<%= cust.getBalance() %></td>
        <td>
          <a href="ViewCustomerServlet?customerId=<%= cust.getCustomerId() %>" class="btn-view">👁 View</a>
          <button onclick="confirmDelete(<%= cust.getCustomerId() %>)" class="btn-delete">🗑 Delete</button>
        </td>
      </tr>
    <%
        }
      }
    %>
    </tbody>
  </table>

  <h2>🔴 Inactive Customers</h2>
  <table id="inactiveCustomerTable">
    <thead>
      <tr>
        <th>Customer ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Balance</th>
      </tr>
    </thead>
    <tbody>
    <%
      List<Customer> inactiveCustomers = (List<Customer>) request.getAttribute("inactiveCustomers");
      if (inactiveCustomers != null) {
        for (Customer cust : inactiveCustomers) {
    %>
      <tr>
        <td><%= cust.getCustomerId() %></td>
        <td><%= cust.getFirstName() + " " + cust.getLastName() %></td>
        <td><%= cust.getEmail() %></td>
        <td>₹<%= cust.getBalance() %></td>
      </tr>
    <%
        }
      }
    %>
    </tbody>
  </table>

  <a class="add-button" href="add_customer.jsp">➕ Add New Customer</a>
</div>

<div id="footer"></div>

<script>
fetch("employee/navbar.html")
  .then(res => res.text())
  .then(data => document.getElementById("navbar").innerHTML = data);

fetch("employee/footer.html")
  .then(res => res.text())
  .then(data => document.getElementById("footer").innerHTML = data);

function searchTable() {
  let input = document.getElementById("searchInput").value.toLowerCase();
  let rows = document.querySelectorAll("tbody tr");
  rows.forEach(row => {
    row.style.display = row.innerText.toLowerCase().includes(input) ? "" : "none";
  });
}

function confirmDelete(customerId) {
  if (confirm("Are you sure you want to delete this customer?")) {
    window.location.href = 'DeleteCustomerServlet?customerId=' + customerId;
  }
}
</script>

</body>
</html>