<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.Cookie" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Logging Out...</title>
  <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
  <meta http-equiv="Pragma" content="no-cache" />
  <meta http-equiv="Expires" content="0" />
  <script>
    // Clear browser storage
    localStorage.clear();
    sessionStorage.clear();

    // Reload on back-forward nav to prevent showing dashboard again
    window.addEventListener("pageshow", function (event) {
      if (event.persisted || performance.getEntriesByType("navigation")[0].type === "back_forward") {
        location.reload();
      }
    });
  </script>
</head>
<body>
<%
    // 🔐 Invalidate session
    if (session != null) {
        session.invalidate();
    }

    // 🍪 Clear cookies
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setPath("/");  // Make sure path matches how they were set
            response.addCookie(cookie);
        }
    }

    // Prevent caching
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    // 🔁 Redirect to login page
    response.sendRedirect("/Bank_Management_System/employee/login.jsp");
%>
</body>
</html>
