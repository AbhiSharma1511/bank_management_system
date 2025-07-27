<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Employee Login</title>
  <style>
    body {
      background: #5379ED;
		background: radial-gradient(circle, rgba(83, 121, 237, 1) 0%, rgba(0, 80, 150, 1) 100%);
	}
    .container {
      width: 350px;
      margin: 30px auto;
      background: white;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
      text-align: center;
    }

    svg {
      width: 50px;
      height: 50px;
      margin-bottom: 10px;
      color: #28a745;
    }

    h2 {
      margin-bottom: 20px;
      color: #333;
    }

    form {
      text-align: left;
    }

    label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
    }

    input {
      width: 100%;
      padding: 8px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    button {
      width: 100%;
      padding: 10px;
      background-color: #28a745;
      border: none;
      color: white;
      font-size: 16px;
      border-radius: 4px;
      cursor: pointer;
    }

    button:hover {
      background-color: #218838;
    }

    .error {
      color: red;
      font-size: 14px;
      margin-top: -10px;
      margin-bottom: 10px;
    }

    .link {
      display: block;
      margin-top: 15px;
      text-align: center;
      color: #007bff;
      text-decoration: none;
    }

    .link:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="div1" style="margin-left: 20px; margin-top: 20px;">
<a href="/Bank_Management_System"><svg xmlns="http://www.w3.org/2000/svg" fill="none"
         viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor">
      <path stroke-linecap="round" stroke-linejoin="round"
            d="M12 21v-8.25M15.75 21v-8.25M8.25 21v-8.25M3 9l9-6 9 6m-1.5 12V10.332A48.36 48.36 0 0012 9.75c-2.551 0-5.056.2-7.5.582V21M3 21h18M12 6.75h.008v.008H12V6.75z" />
    </svg></a>
</div>

  <div class="container">
    <svg xmlns="http://www.w3.org/2000/svg" fill="none"
         viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor">
      <path stroke-linecap="round" stroke-linejoin="round"
            d="M12 21v-8.25M15.75 21v-8.25M8.25 21v-8.25M3 9l9-6 9 6m-1.5 12V10.332A48.36 48.36 0 0012 9.75c-2.551 0-5.056.2-7.5.582V21M3 21h18M12 6.75h.008v.008H12V6.75z" />
    </svg>

    <h2>Employee Login</h2>
    
    <!-- Login Form Starts -->
    <form action="/Bank_Management_System/elogin_servlet" id="loginForm" method="Post">
      <label for="id">Employee ID</label>
      <input type="text" id="id" name="id" required placeholder="12345">

      <label for="password">Password</label>
      <input type="password" id="password" name="password" required>

	<% String error = (String) request.getAttribute("errorMessage"); %>
	<% if (error != null) { %>
	      <div id="loginError" class="error"><%= error %></div>
	<% } %>

      <button type="submit">Login</button>
    </form>

    <!-- Optional: Link to registration or home -->
    <a href="/Bank_Management_System/employeeRegisterServlet" class="link">Don't have an account? Register here</a>
    <a href="ResetPassword.jsp" class="link">Forgot Password</a>
  </div>

</body>
</html>