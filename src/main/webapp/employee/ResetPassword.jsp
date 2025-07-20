<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset Password</title>
<style>
body {
	font-family: Arial, sans-serif;
	background: #3c46a6;
background: radial-gradient(circle,rgba(60, 70, 166, 1) 0%, rgba(124, 136, 204, 1) 50%, rgba(83, 121, 237, 1) 100%);
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
}

.container {
	margin: auto;
	margin-top:100px;
	display: flex;
	flex-direction: column;
	max-width: 400px;
	background-color: white;
	border-radius: 10px;
	
}

form {
	padding: 20px;
}

input[type="password"], input[type="text"] {
	width: 100%;
	margin: 10px 0;
	font-size: 18px;
	padding-top: 5px;
	padding-bottom: 5px;
	border-radius: 5px;
	font-weight: 500;
}
button{
background-color: #293AD6;
color:white;
width:100%;
padding: 5px;
text-align: center;
font-size: 20px;
border-radius: 10px
}
</style>
</head>
<body>


	<div class="container">
		<h2 align="center">🔒 Reset Password</h2>
		<form action="/Bank_Management_System/ResetPasswordServlet"
			method="post" onsubmit="return validatePassword();">
			<label>Employee ID:</label> <input type="text" name="empId" placeHolder="Enter 6 digit Id: 123456" required />

			<label>New Password:</label> 
			<input id="password" type="password" name="newPassword" required /> 
				
			<label>Confirm New Password:</label>
			<input id="confirmPassword" type="password" name="confirmPassword" required /> 
			
			<button type="submit" value="Submit">Submit</button>
			
			<%
				String msg = (String) request.getAttribute("msg");
			String success_msg = (String) request.getAttribute("success_msg");
				if (msg != null) {
					
				%>
				<p style="text-align: center; color: red;"><%=msg%></p>
				<%
				}else if(success_msg!=null){
					%>
					<p style="text-align: center; color: blue;"><%=success_msg%></p>
					<%
				}
			%>
			<div style="margin-top: 20px; text-align: center;">
				<a href="login.jsp" style="text-decoration: none">Login here!</a>
			</div>
		</form>
	</div>


 <script>

 function validatePassword() {
	 console.log("validatePassword() called");
     const password = document.getElementById("password").value;
     const confirmPassword = document.getElementById("confirmPassword").value;

     const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$%^&*!])[A-Za-z\d@#$%^&*!]{6,10}$/;

     if (!regex.test(password)) {
         alert("Password must be 6-10 characters and include uppercase, lowercase, number, and special character.");
         return false;
     }

     if (password !== confirmPassword) {
         alert("New Password and Confirm Password do not match.");
         return false;
     }

     return true;
 }
 </script>

	

</body>
</html>
