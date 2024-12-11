<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Complaint Registered - Electricity Billing System</title>
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
            padding: 10px;
            background-color: #e8f5e9;
            border-radius: 4px;
        }
        .complaint-details {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 4px;
            margin: 20px 0;
            text-align: left;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin: 10px;
        }
    </style>
</head>
<body>
    <div class="topbar">
        <div class="menu">
            <a href="home.jsp">Home</a>
            <a href="viewBill">Pay Bill</a>
            <a href="billHistory">Bill History</a>
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

    <div class="success-container">
        <div class="success-message">
            Complaint Registered Successfully!
        </div>

        <div class="complaint-details">
            <h3>Complaint Details:</h3>
            <p><strong>Complaint ID:</strong> ${complaintId}</p>
            <p><strong>Status:</strong> Open</p>
            <p>You can track your complaint status using the Complaint ID.</p>
        </div>

        <a href="complaintStatus.jsp" class="btn">Check Status</a>
        <a href="home.jsp" class="btn">Back to Home</a>
    </div>
</body>
</html>