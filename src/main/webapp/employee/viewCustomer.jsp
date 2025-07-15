<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="com.entity.Customer" %>
<jsp:useBean id="customer" scope="request" class="com.entity.Customer" />

<h2>Customer Details</h2>
<form action="UpdateCustomerServlet" method="post" id="editForm">
  <input type="hidden" name="customerId" value="${customer.customerId}" />

  First Name: <input type="text" name="firstName" value="${customer.firstName}" disabled /><br/>
  Last Name: <input type="text" name="lastName" value="${customer.lastName}" disabled /><br/>
  Email: <input type="email" name="email" value="${customer.email}" disabled /><br/>
  Contact: <input type="text" name="contact" value="${customer.contact}" disabled /><br/>
  Address: <input type="text" name="address" value="${customer.address}" disabled /><br/>
  Balance: <input type="number" name="balance" value="${customer.balance}" disabled /><br/>

  <button type="button" onclick="enableEditing()">✏ Edit</button>
  <button type="submit" id="saveBtn" style="display:none;">💾 Save</button>
</form>

<script>
  function enableEditing() {
    const inputs = document.querySelectorAll("#editForm input:not([type='hidden'])");
    inputs.forEach(input => input.removeAttribute("disabled"));

    document.querySelector("#saveBtn").style.display = "inline-block";
  }
</script>

</body>
</html>