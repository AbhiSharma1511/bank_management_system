<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
  <meta http-equiv="Pragma" content="no-cache" />
  <meta http-equiv="Expires" content="0" />
<title>Employee Registration</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background: radial-gradient(circle, rgba(60, 70, 166, 1) 0%, rgba(124, 136, 204, 1) 50%, rgba(83, 121, 237, 1) 100%);
        margin: 0;
        padding: 0;
    }
    .container {
        width: 450px;
        margin: 50px auto;
        background: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    h2 {
        text-align: center;
        margin-bottom: 20px;
        color: #333;
    }
    label {
        display: block;
        margin-top: 15px;
        font-weight: bold;
    }
    input[type="text"],
    input[type="email"],
    input[type="password"],
    input[type="tel"],
    textarea {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 6px;
    }
    button {
        width: 100%;
        padding: 12px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 6px;
        font-size: 16px;
        margin-top: 20px;
        cursor: pointer;
    }
    button:hover {
        background-color: #0056b3;
    }
    .message {
        color: green;
        text-align: center;
        margin-top: 10px;
        font-weight: bold;
    }
    .error {
        color: red;
        text-align: center;
        margin-top: 10px;
        font-weight: bold;
    }
</style>
</head>
<body>

<%
Integer employeeId = (Integer) session.getAttribute("employeeId");
String success = (String) request.getAttribute("success");
String error = (String) request.getAttribute("error");
%>

<div class="container">
    <h2>Employee Registration</h2>

    <% if (success != null) { %>
        <div class="message"><%= success %></div>
    <% } else if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>

    <form action="/Bank_Management_System/employeeRegisterServlet" method="post">

        <label for="id">Employee ID:</label>
        <input type="text" id="id" name="id" value="<%= employeeId != null ? employeeId : "" %>" readonly>

        <label for="name">Full Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="email">Email ID:</label>
        <input type="email" id="email" name="email" required>

        <label for="mobile">Mobile Number:</label>
        <input type="tel" id="mobile" name="mobile" pattern="[0-9]{10}" required>

        <label for="address">Address:</label>
        <textarea id="address" name="address" rows="3" required></textarea>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Register</button>

        <div style="margin-top: 10px; display: flex; justify-content: center;">
            <a href="login.jsp" style="text-decoration: none">Already have an account? Login here 🙂</a>
        </div>
    </form>
</div>

</body>
</html>
