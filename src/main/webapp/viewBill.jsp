<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Bills - Electricity Billing System</title>
    <style>
        .topbar {
            background-color: #003366;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: white;
        }
        .bills-container {
            padding: 20px;
            max-width: 800px;
            margin: 0 auto;
        }
        .bill-item {
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 10px;
            border-radius: 4px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #fff;
        }
        .bill-item:hover {
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .checkbox-container {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .total-section {
            margin-top: 20px;
            padding: 15px;
            background-color: #f8f9fa;
            border-radius: 4px;
        }
        .proceed-btn {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 20px;
            width: 100%;
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

    <div class="bills-container">
        <h2>Your Bills</h2>
        <form action="payment" method="post">
    <c:forEach items="${bills}" var="bill">
        <div class="bill-item">
            <div class="checkbox-container">
                <input type="checkbox" name="selectedBills" value="${bill.id}" 
                       onchange="updateTotal(this, ${bill.amount})">
                <div>
                    <div>Bill Date: ${bill.dueDate}</div>
                    <div>Amount: ₹${bill.amount}</div>
                </div>
            </div>
        </div>
    </c:forEach>
    
    <div class="total-section">
        <h3>Total Amount: ₹<span id="totalAmount">0</span></h3>
        <input type="hidden" name="totalAmountValue" id="totalAmountValue" value="0">
    </div>
    
    <button type="submit" class="proceed-btn">Proceed to Pay</button>
</form>

<script>
    let total = 0;
    function updateTotal(checkbox, amount) {
        if (checkbox.checked) {
            total += amount;
        } else {
            total -= amount;
        }
        document.getElementById('totalAmount').textContent = total.toFixed(2);
        document.getElementById('totalAmountValue').value = total.toFixed(2);
    }
</script>
</body>
</html>