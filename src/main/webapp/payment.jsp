<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Payment - Electricity Billing System</title>
    <style>
        .topbar {
            background-color: #003366;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: white;
        }
        .menu {
            display: flex;
            gap: 20px;
        }
        .menu a {
            color: white;
            text-decoration: none;
            padding: 5px 10px;
        }
        .payment-container {
            width: 60%;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .bill-summary {
            background-color: #f8f9fa;
            padding: 15px;
            border-radius: 4px;
            margin-bottom: 20px;
        }
        .button-group {
            display: flex;
            gap: 10px;
            margin-top: 20px;
        }
        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn-primary {
            background-color: #28a745;
            color: white;
        }
        .btn-secondary {
            background-color: #6c757d;
            color: white;
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
        <div class="welcome">
            Welcome, ${username}
            <form action="logout" method="post" style="margin: 0;">
                <button type="submit" class="logout-btn">Logout</button>
            </form>
        </div>
    </div>

    <div class="payment-container">
    <h2>Payment Details</h2>
    <div class="bill-summary">
        <h3>Bill Summary</h3>
        <p>Total Amount: ₹${totalAmount}</p>
        <p>Selected Bill IDs: ${selectedBills}</p>
    </div>
    
    <div class="buttons">
        <button onclick="location.href='cardPayment.jsp'" class="btn-pay">Pay Now</button>
        <button onclick="location.href='home.jsp'" class="btn-cancel">Back to Home</button>
    </div>
</div>
</body>
</html>