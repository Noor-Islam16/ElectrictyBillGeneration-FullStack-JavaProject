<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Card Payment - Electricity Billing System</title>
    <style>
        .topbar {
            background-color: #003366;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: white;
        }
        .payment-form {
            width: 500px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .card-details {
            display: flex;
            gap: 15px;
        }
        .expiry-cvv {
            flex: 1;
        }
        .payment-btn {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 20px;
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
    </div>
    <div class="payment-form">
        <h2>Credit Card Details</h2>
        <form action="processPayment" method="post" onsubmit="return validateForm()">
            <div class="form-group">
                <label>Card Number:</label>
                <input type="text" name="cardNumber" minlength="16" maxlength="16" 
                       required pattern="\d{16}" title="Please enter 16 digit card number">
            </div>
            
            <div class="form-group">
                <label>Card Holder Name:</label>
                <input type="text" name="cardHolderName" minlength="10" required>
            </div>
            
            <div class="card-details">
                <div class="form-group expiry-cvv">
                    <label>Expiry Date:</label>
                    <input type="text" name="expiryDate" placeholder="MM/YY" 
                           required pattern="\d{2}/\d{2}" title="Format: MM/YY">
                </div>
                
                <div class="form-group expiry-cvv">
                    <label>CVV:</label>
                    <input type="password" name="cvv" minlength="3" maxlength="3" 
                           required pattern="\d{3}" title="Please enter 3 digit CVV">
                </div>
            </div>
            
            <button type="submit" class="payment-btn">Make Payment</button>
        </form>
    </div>
    

    <script>
        function validateForm() {
            var cardNumber = document.getElementsByName("cardNumber")[0].value;
            var expiryDate = document.getElementsByName("expiryDate")[0].value;
            var cvv = document.getElementsByName("cvv")[0].value;

            // Basic validations
            if (!/^\d{16}$/.test(cardNumber)) {
                alert("Please enter valid 16-digit card number");
                return false;
            }
            
            if (!/^\d{2}\/\d{2}$/.test(expiryDate)) {
                alert("Please enter valid expiry date (MM/YY)");
                return false;
            }
            
            if (!/^\d{3}$/.test(cvv)) {
                alert("Please enter valid 3-digit CVV");
                return false;
            }

            return true;
        }
    </script>
</body>
