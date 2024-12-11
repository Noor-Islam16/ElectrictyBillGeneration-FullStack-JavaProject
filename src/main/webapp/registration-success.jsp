<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Success</title>
    <style>
        .container {
            width: 60%;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .success-message {
            color: #4CAF50;
            font-size: 24px;
            text-align: center;
            margin-bottom: 30px;
            padding: 15px;
            background-color: #f1f8e9;
            border-radius: 4px;
        }
        .customer-details {
            margin-top: 20px;
            padding: 15px;
            background-color: #f5f5f5;
            border-radius: 4px;
        }
        .detail-item {
            margin: 10px 0;
            font-size: 16px;
        }
        .next-step {
            margin-top: 30px;
            text-align: center;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="success-message">
            Consumer Registration successful
        </div>
        
        <div class="customer-details">
            <div class="detail-item">
                <strong>Customer ID:</strong> ${customerId}
            </div>
            <div class="detail-item">
                <strong>Customer Name:</strong> ${customerName}
            </div>
            <div class="detail-item">
                <strong>Email:</strong> ${email}
            </div>
        </div>
        
        <div class="next-step">
            <a href="login.jsp" class="btn">Proceed to Login</a>
        </div>
    </div>
</body>
</html>