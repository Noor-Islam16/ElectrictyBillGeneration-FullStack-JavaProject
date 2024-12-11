<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Payment Success - Electricity Billing System</title>
    <style>
        .topbar {
            background-color: #003366;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: white;
        }
        .success-container {
            width: 500px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            text-align: center;
        }
        .success-message {
            color: #28a745;
            font-size: 24px;
            margin: 20px 0;
        }
        .transaction-details {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 4px;
            margin: 20px 0;
            text-align: left;
        }
        .download-btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin-top: 20px;
        }
        .back-btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #6c757d;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="topbar">
    <div class="menu">
            <a href="home.jsp">Home</a>
            <a href="viewBill">Pay Bill</a>
            <a href="registerComplaint">Register Complaint</a>
            <a href="complaintStatus">Complaint Status</a>
        </div>
    
    <div class="success-container">
        <div class="success-message">
            Payment Successful!
        </div>

        <div class="transaction-details">
            <h3>Transaction Details</h3>
            <p><strong>Transaction ID:</strong> ${transactionId}</p>
            <p><strong>Amount Paid:</strong> ₹${amountPaid}</p>
            <p><strong>Date:</strong> ${transactionDate}</p>
        </div>

        <a href="downloadReceipt?transactionId=${transactionId}" class="download-btn">
            Download Receipt
        </a>
        <br>
        <a href="home.jsp" class="back-btn">Back to Home</a>
    </div>
    </div>
</body>
</html>