<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
      max-width: 450px;
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
      width: 100%;
      font-size: 20px;
    }

    button:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>

  <div id="navbar"><%@ include file="enavbar.html" %></div>

  <div class="container">
    
    <!-- New Loan Form -->
    <h2>Add new Loan</h2>
    <form action="/Bank_Management_System/AddNewLoan" method="post" class="form-section">
      <label for="customerId">Customer ID</label>
      <input type="text" name="customerId" required>

      <label for="customerName">Customer Name</label>
      <input type="text" name="customerName" required>

      <label for="amount">Loan Amount</label>
      <input type="number" name="amount" step="0.01" required>

      <label for="status">Status</label>
      <input type="text" name="status" value="pending" readonly disabled>

<div>
      <button type="submit">Submit Loan Application</button>
</div>
    </form>
  </div>

  <div id="footer"></div>

  <script>
    

    fetch("footer.html")
      .then(res => res.text())
      .then(data => document.getElementById("footer").innerHTML = data);
  </script>

</body>
</html>
