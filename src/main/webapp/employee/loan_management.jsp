<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Loan Management</title>
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
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }

    h1, h2 {
      text-align: center;
      margin-bottom: 20px;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 30px;
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

    .pending { background-color: #ffc107; }
    .accepted { background-color: #28a745; }
    .hold { background-color: #17a2b8; }
    .rejected { background-color: #dc3545; }

    .form-section {
      padding: 20px;
      border: 1px solid #ccc;
      border-radius: 6px;
    }

    label {
      display: block;
      margin: 10px 0 5px;
    }

    input, select {
      padding: 8px;
      width: 100%;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    button {
      background-color: #007bff;
      color: white;
      padding: 10px 15px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }

    button:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>

  <div id="navbar"></div>

  <div class="container">
    <h1>Loan Management Dashboard</h1>

    <!-- Loan Status Table -->
    <table>
      <thead>
        <tr>
          <th>Loan ID</th>
          <th>Customer ID</th>
          <th>Customer Name</th>
          <th>Amount</th>
          <th>Status</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="loan" items="${loanList}">
          <tr>
            <td>${loan.loanId}</td>
            <td>${loan.customerId}</td>
            <td>${loan.customerName}</td>
            <td>₹${loan.amount}</td>
            <td><span class="status ${loan.status.toLowerCase()}">${loan.status}</span></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <!-- New Loan Form -->
    <h2>Apply for a Loan</h2>
    <form action="ApplyLoanServlet" method="post" class="form-section">
      <label for="customerId">Customer ID</label>
      <input type="text" name="customerId" required>

      <label for="customerName">Customer Name</label>
      <input type="text" name="customerName" required>

      <label for="amount">Loan Amount</label>
      <input type="number" name="amount" step="0.01" required>

      <label for="status">Status</label>
      <select name="status">
        <option value="Pending">Pending</option>
        <option value="Accepted">Accepted</option>
        <option value="Hold">Hold</option>
        <option value="Rejected">Rejected</option>
      </select>

      <button type="submit">Submit Loan Application</button>
    </form>
  </div>

  <div id="footer"></div>

  <script>
    fetch("navbar.html")
      .then(res => res.text())
      .then(data => document.getElementById("navbar").innerHTML = data);

    fetch("footer.html")
      .then(res => res.text())
      .then(data => document.getElementById("footer").innerHTML = data);
  </script>

</body>
</html>
