<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, com.entity.Transaction" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transaction History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            padding:0px;
            margin: 0px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 12px 15px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .message {
            text-align: center;
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div><%@ include file="navbar.html" %></div>
<h2>Transaction History</h2>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
    <p class="message"><%= error %></p>
<%
    }
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
    if (transactions != null && !transactions.isEmpty()) {
%>

<table>
    <thead>
        <tr>
            <th>Transaction ID</th>
            <th>Sender Account</th>
            <th>Receiver Account</th>
            <th>Amount (₹)</th>
            <th>Date</th>
        </tr>
    </thead>
    <tbody>
    <%
        for (Transaction t : transactions) {
    %>
        <tr>
            <td><%= t.getTransactionId() %></td>
            <td><%= t.getSenderAccountNo() %></td>
            <td><%= t.getReceiverAccountNo() %></td>
            <td><%= t.getAmount() %></td>
            <td><%= t.getTransactionDate() %></td>
        </tr>
    <%
        }
    %>
    </tbody>
</table>

<%
    } else {
%>
    <p class="message">No transactions found.</p>
<%
    }
%>
<%@ include file="footer.html" %>
</body>
</html>
