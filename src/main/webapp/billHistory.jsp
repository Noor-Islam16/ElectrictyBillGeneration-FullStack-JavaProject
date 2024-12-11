<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bill History - Electricity Billing System</title>
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
        .history-container {
            width: 90%;
            margin: 20px auto;
            padding: 20px;
        }
        .summary-box {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 30px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .summary-box h3 {
            color: #003366;
            margin-bottom: 15px;
        }
        .summary-stats {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
        }
        .stat-item {
            padding: 15px;
            background-color: white;
            border-radius: 6px;
            text-align: center;
        }
        .stat-value {
            font-size: 24px;
            font-weight: bold;
            color: #003366;
        }
        .usage-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .usage-table th, .usage-table td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        .usage-table th {
            background-color: #003366;
            color: white;
        }
        .usage-table tr:nth-child(even) {
            background-color: #f8f9fa;
        }
        .usage-table tr:hover {
            background-color: #f1f1f1;
        }
        .status-paid {
            color: #28a745;
            font-weight: bold;
        }
        .status-unpaid {
            color: #dc3545;
            font-weight: bold;
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
            <a href="billHistory.jsp">Bill History</a>
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

    <div class="history-container">
        <h2>Electricity Usage History</h2>
        
        <div class="summary-box">
            <h3>Usage Summary - Last 6 Months</h3>
            <div class="summary-stats">
                <div class="stat-item">
                    <div class="stat-value">${averageUsage}</div>
                    <div>Average Monthly Usage (Units)</div>
                </div>
                <div class="stat-item">
                    <div class="stat-value">${highestUsageMonth}</div>
                    <div>Highest Usage Month</div>
                </div>
                <div class="stat-item">
                    <div class="stat-value">${lowestUsageMonth}</div>
                    <div>Lowest Usage Month</div>
                </div>
            </div>
        </div>

        <table class="usage-table">
            <thead>
                <tr>
                    <th>Month</th>
                    <th>Units Consumed</th>
                    <th>Amount (₹)</th>
                    <th>Due Date</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${usageHistory}" var="usage">
                    <tr>
                        <td>${usage.month}</td>
                        <td>${usage.unitsConsumed}</td>
                        <td>₹${usage.amount}</td>
                        <td>${usage.dueDate}</td>
                        <td class="status-${usage.status.toLowerCase()}">${usage.status}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>