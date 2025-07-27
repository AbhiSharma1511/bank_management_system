<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.entity.Customer" %>

<jsp:useBean id="customer" scope="request" class="com.entity.Customer" />

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
   <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
  <meta http-equiv="Pragma" content="no-cache" />
  <meta http-equiv="Expires" content="0" />
  <title>Edit Customer</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
    }

    .container {
      max-width: 600px;
      margin: 40px auto;
      padding: 30px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
    }

    h2 {
      text-align: center;
      color: #333;
      margin-bottom: 25px;
      background-color: #007bff; 
      color: white;
      padding: 5px 0px 5px 0px;
      border-radius: 20px
    }

    label {
      display: block;
      margin-top: 15px;
      font-weight: 600;
      color: #555;
    }

    input[type="text"],
    input[type="email"],
    input[type="number"],
    input[type="checkbox"] {
      width: 100%;
      padding: 10px;
      border-radius: 6px;
      border: 1px solid #ccc;
      margin-top: 5px;
      box-sizing: border-box;
      font-size: 15px;
    }

    input[readonly],
    input:disabled {
      background-color: #f1f1f1;
    }

    .checkbox-label {
      display: flex;
      align-items: center;
      justify-content: center;
      gap: 10px;
      margin: 20px 0px 10px 0px
    }

    button {
      display: inline-block;
      width: 48%;
      padding: 12px;
      font-size: 15px;
      border: none;
      border-radius: 6px;
      cursor: pointer;
      margin-top: 25px;
    }

    #editBtn {
      background-color: #17a2b8;
      color: #fff;
    }

    #editBtn:hover {
      background-color: #138496;
    }

    #saveBtn {
      background-color: #28a745;
      color: #fff;
      float: right;
      display: none;
    }

    #saveBtn:hover {
      background-color: #218838;
    }

    .button-group {
      display: flex;
      justify-content: space-between;
      gap:10px
    }
  </style>
</head>
<body>
<%@ include file="employee_auth.jsp" %>
<div><%@ include file="enavbar.html"  %></div>
<div class="container">
  <h2 style="">🔍 Customer Profile</h2>
  <form action="ViewEditCustomerServlet" method="post" id="editForm" onsubmit="return validateForm()">

	<h2 style="background-color: white; color: black; margin-bottom: 5px">Customer Id: ${customer.customerId}</h2>
	<h2 style="background-color: white; color: black;">Account No: ${customer.accountNo}</h2>
	
	<input type="hidden" name="customerId" value="${customer.customerId}" />

    <label>First Name</label>
    <input type="text" name="firstName" value="${customer.firstName}" readonly />

    <label>Last Name</label>
    <input type="text" name="lastName" value="${customer.lastName}" readonly />

    <label>Email</label>
	<input type="email" id="email" name="email" value="${customer.email}" disabled />
	<span id="emailError" style="color:red; font-size: 14px;"></span>
	
	<label>Contact</label>
	<input type="text" id="contact" name="contact" value="${customer.contact}" disabled />
	<span id="contactError" style="color:red; font-size: 14px;"></span>
	
    <label>Date Of Birth</label>
    <input type="text" name="dob" value="${customer.dob}" disabled />
    
    <label>Aadhar Number</label>
    <input type="text" name="aadhar" value="${customer.aadhar}" disabled />
    
    <label>PAN Number</label>
    <input type="text" name="pan" value="${customer.pan}" disabled />

    <label>Address</label>
    <input type="text" name="address" value="${customer.address}" disabled />

    <label>Balance (₹)</label>
    <input type="number" name="balance" value="${customer.balance}" readonly />

    <div class="checkbox-label">
      <label style="margin-top: 0px; font-size: 20px; color: black;">Active Account</label>
		<input type="checkbox" name="active" style="width:20px; height:20px;"
		<% if (customer.isActiveAccount()) { %> checked <% } %> disabled />
    </div>

    <div class="button-group">
  <button type="button" id="editBtn" onclick="enableEditing()">✏ Edit</button>
  <button type="submit" id="saveBtn" style="display: none;">💾 Save</button>
  <button type="button" id="cancelBtn" onclick="disableEditing()" style="display: none;">Cancel</button>
</div>

  </form>
</div>

<script>
  let originalValues = {};

  window.onload = function () {
    const form = document.getElementById("editForm");
    originalValues.email = form.querySelector('input[name="email"]').value;
    originalValues.contact = form.querySelector('input[name="contact"]').value;
    originalValues.address = form.querySelector('input[name="address"]').value;
    originalValues.active = form.querySelector('input[name="active"]').checked;
  };

  function enableEditing() {
    const form = document.getElementById("editForm");
    form.querySelector('input[name="email"]').disabled = false;
    form.querySelector('input[name="contact"]').disabled = false;
    form.querySelector('input[name="address"]').disabled = false;
    form.querySelector('input[name="active"]').disabled = false;

    document.getElementById("saveBtn").style.display = "inline-block";
    document.getElementById("cancelBtn").style.display = "inline-block";
    document.getElementById("editBtn").style.display = "none";
  }

  function disableEditing() {
    const form = document.getElementById("editForm");
    form.querySelector('input[name="email"]').value = originalValues.email;
    form.querySelector('input[name="contact"]').value = originalValues.contact;
    form.querySelector('input[name="address"]').value = originalValues.address;
    form.querySelector('input[name="active"]').checked = originalValues.active;

    form.querySelector('input[name="email"]').disabled = true;
    form.querySelector('input[name="contact"]').disabled = true;
    form.querySelector('input[name="address"]').disabled = true;
    form.querySelector('input[name="active"]').disabled = true;

    document.getElementById("saveBtn").style.display = "none";
    document.getElementById("cancelBtn").style.display = "none";
    document.getElementById("editBtn").style.display = "inline-block";
  }
  
  	function isValidEmail(email) {
	  const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+\.(com|in|org|net|gov|edu|co\.in|info)$/i;
	  return regex.test(email);
	}

	function isValidContact(contact) {
	  const regex = /^[6-9][0-9]{9}$/;
	  return regex.test(contact);
	}

	function validateForm() {
	  const emailInput = document.getElementById("email").value.trim();
	  const contactInput = document.getElementById("contact").value.trim();
	  const emailError = document.getElementById("emailError");
	  const contactError = document.getElementById("contactError");

	  let isValid = true;

	  // Email validation
	  if (!isValidEmail(emailInput)) {
	    emailError.textContent = "❌ Please enter a valid email like abc@example.com";
	    isValid = false;
	  } else {
	    emailError.textContent = "";
	  }

	  // Contact validation
	  if (!isValidContact(contactInput)) {
	    contactError.textContent = "❌ Enter a 10-digit mobile number starting with 6, 7, 8, or 9";
	    isValid = false;
	  } else {
	    contactError.textContent = "";
	  }

	  return isValid;
	}

</script>



</body>
</html>
