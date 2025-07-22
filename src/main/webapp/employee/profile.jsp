<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.entity.Employee" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Employee Profile</title>
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
<% Employee employee = (Employee) session.getAttribute("employee"); %>
<div><%@ include file="enavbar.html"  %></div>
<div class="container">
  <h2 style="">🔍 Employee Profile</h2>
  <form action="/Bank_Management_System/UpdateEmployeeDetails" method="post" id="editForm" onsubmit="return validateForm()">

	<h2 style="background-color: white; color: black; margin-bottom: 5px">Employee Id: <%= employee.getEmpId() %></h2>
	
	<input type="hidden" name="empId" value="<%= employee.getEmpId() %>" />

    <label>Name</label>
    <input type="text" name="name" value="<%= employee.getName() %>" readonly />

    <label>Email</label>
    <input type="email" name="email" value="<%= employee.getEmail() %>" disabled />
    
    <label>Contact</label>
	<input type="text" id="contact" name="contact" value="<%= employee.getContact()%>" disabled />
	<span id="contactError" style="color:red; font-size: 14px;"></span>
	
    <label>Address</label>
    <input type="text" name="address" value="<%= employee.getAddress() %>" disabled />


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
    originalValues.contact = form.querySelector('input[name="contact"]').value;
    originalValues.address = form.querySelector('input[name="address"]').value;
  };

  function enableEditing() {
    const form = document.getElementById("editForm");
    form.querySelector('input[name="contact"]').disabled = false;
    form.querySelector('input[name="address"]').disabled = false;

    document.getElementById("saveBtn").style.display = "inline-block";
    document.getElementById("cancelBtn").style.display = "inline-block";
    document.getElementById("editBtn").style.display = "none";
  }

  function disableEditing() {
    const form = document.getElementById("editForm");
    form.querySelector('input[name="contact"]').value = originalValues.contact;
    form.querySelector('input[name="address"]').value = originalValues.address;

    form.querySelector('input[name="contact"]').disabled = true;
    form.querySelector('input[name="address"]').disabled = true;

    document.getElementById("saveBtn").style.display = "none";
    document.getElementById("cancelBtn").style.display = "none";
    document.getElementById("editBtn").style.display = "inline-block";
  }
  

	function isValidContact(contact) {
	  const regex = /^[6-9][0-9]{9}$/;
	  return regex.test(contact);
	}

	function validateForm() {
	  
	  const contactInput = document.getElementById("contact").value.trim();
	  
	  const contactError = document.getElementById("contactError");

	  let isValid = true;

	  // Email validation
	 
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
