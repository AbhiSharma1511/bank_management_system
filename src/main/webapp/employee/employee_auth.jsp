
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<%-- employee_auth.jsp --%>
<%
if(session == null || session.getAttribute("employee") == null){
  response.sendRedirect("/Bank_Management_System/employee/login.jsp");
  return;
}
%>
</body>
</html>