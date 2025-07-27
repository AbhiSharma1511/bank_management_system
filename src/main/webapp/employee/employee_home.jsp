<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.entity.Employee"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
   <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
  <meta http-equiv="Pragma" content="no-cache" />
  <meta http-equiv="Expires" content="0" />
  <title>Employee Dashboard</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <style>
    body {
      margin: 0;
      font-family: Arial, sans-serif;
      background: #f0f4f8;
    }

    
    .hero {
      background-color: rgb(23, 116, 255);
      text-align: center;
      padding: 30px 10px;
      color:white;
    }

    .hero h2 {
      margin-bottom: 10px;
      font-size: 28px;
    }

    .hero p {
      font-size: 16px;
      color: white;
    }

    .dashboard {
      max-width: 1100px;
      margin: 30px auto;
      padding: 0 20px;
      display: flex;
      flex-direction: column;
      grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
      gap: 40px;
    }
    
    .card1{
    display: flex;
    justify-content: space-around;
    }

    .card {
      background: white;
      padding: 20px;
      width: 40%;
      border-radius: 8px;
      box-shadow: 0 2px 10px rgba(0,0,0,0.1);
      transition: 0.3s;
      text-align: center;
    }

    .card:hover {
      transform: translateY(-4px);
    }

    .card h1 {
      margin-bottom: 10px;
      color: #003366;
    }

    .card p {
      font-size: 14px;
      color: #555;
    }

    .card a {
      display: inline-block;
      margin-top: 12px;
      padding: 8px 16px;
      background: #007bff;
      color: white;
      text-decoration: none;
      border-radius: 4px;
    }

    .card a:hover {
      background: #0056b3;
    }

    .footer {
      background: #003366;
      color: white;
      text-align: center;
      padding: 15px;
      margin-top: 40px;
    }
  </style>
  <script>
  window.addEventListener("pageshow", function (event) {
    if (event.persisted || performance.getEntriesByType("navigation")[0].type === "back_forward") {
      location.reload(); // Recheck session and reload page
    }
  });
</script>
</head>
<body>
<%@ include file="employee_auth.jsp" %>
<% Employee emp = (Employee) session.getAttribute("employee"); %>
  <!-- Navbar -->
  <div id="navbar"></div>

  <!-- Hero Section -->
  <div class="hero">
    <h2>Welcome, <span id="empName"><%= emp.getName() %></span></h2>
    <p>Employee ID: <strong id="empId"><%= emp.getEmpId() %></strong></p>
  </div>

  <!-- Dashboard Grid -->
  <div class="dashboard">
  <div class="card1">
    <div class="card">
      <h1>Customer Management</h1>
      <p>View and update customer details.</p>
      <a href="/Bank_Management_System/manage_customers">Go</a>
    </div>
    <div class="card">
      <h1>Loan Handling</h1>
      <p>Manage loan approvals and records.</p>
      <a href="/Bank_Management_System/loan_management">Go</a>
    </div>
  </div>
  <div class="card1">
  
    <div class="card">
      <h1>Transaction Management</h1>
      <p>Register a new customer account.</p>
      <a href="/Bank_Management_System/viewTransactions">Go</a>
    </div>
    <div class="card">
      <h1>Profile</h1>
      <p>Update your contact and profile info.</p>
      <a href="${pageContext.request.contextPath}/employee/profile.jsp">Manage</a>
    </div>
  </div>
  </div>

  <!-- Footer -->
  <div class="footer">
    &copy; 2025 Bank Management System. All rights reserved.
  </div>

  <!-- Optional: JS to inject session data -->
  <script>
    // Simulate fetching from session (replace with AJAX if needed)
   
    
    
    fetch("enavbar.html")
      .then(res => res.text())
      .then(data => document.getElementById("navbar").innerHTML = data);

    
  </script>


</body>
</html>