<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Home - Electricity Billing System</title>
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
        .menu a:hover {
            background-color: #004d99;
            border-radius: 4px;
        }
        .welcome {
            display: flex;
            align-items: center;
            gap: 15px;
        }
        .logout-btn {
            background-color: #ff3333;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
        }
        .content {
            padding: 20px;
            max-width: 1200px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <%
        if (session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>

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

    <div class="content">
        <h2>Welcome to Electricity Billing System</h2>
        <p>Please select an option from the menu above to proceed.</p>
    </div>
</body>
</html>