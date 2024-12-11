<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Registration - Electricity Billing System</title>
    <style>
        .container {
            width: 60%;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
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
        input, select {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .mobile-group {
            display: flex;
            gap: 10px;
        }
        .mobile-group select {
            width: 120px;
        }
        .button-group {
            margin-top: 20px;
        }
        .btn {
            padding: 10px 20px;
            margin-right: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn-primary {
            background-color: #007bff;
            color: white;
        }
        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Customer Registration</h2>
        <form action="registerCustomer" method="post" onsubmit="return validateForm()">
            <div class="form-group">
                <label>Consumer ID:</label>
                <input type="text" name="consumerId" maxlength="13" required 
                       pattern="\d{13}" title="Please enter 13 digit number">
            </div>
            
            <div class="form-group">
                <label>Bill Number:</label>
                <input type="text" name="billNumber" maxlength="5" required 
                       pattern="\d{5}" title="Please enter 5 digit number">
            </div>
            
            <div class="form-group">
                <label>Title:</label>
                <select name="title" required>
                    <option value="">Select Title</option>
                    <option value="Mr">Mr</option>
                    <option value="Mrs">Mrs</option>
                    <option value="Ms">Ms</option>
                </select>
            </div>
            
            <div class="form-group">
                <label>Customer Name:</label>
                <input type="text" name="customerName" maxlength="50" required>
            </div>
            
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" required>
            </div>
            
            <div class="form-group">
                <label>Mobile Number:</label>
                <div class="mobile-group">
                    <select name="countryCode" required>
                        <option value="+91">+91 (India)</option>
                        <option value="+1">+1 (USA)</option>
                        <option value="+44">+44 (UK)</option>
                    </select>
                    <input type="text" name="mobileNumber" pattern="\d{10}" 
                           maxlength="10" required title="Please enter 10 digit number">
                </div>
            </div>
            
            <div class="form-group">
                <label>User ID:</label>
                <input type="text" name="userId" minlength="5" maxlength="20" required>
            </div>
            
            <div class="form-group">
                <label>Password:</label>
                <input type="password" name="password" maxlength="30" required id="password">
            </div>
            
            <div class="form-group">
                <label>Confirm Password:</label>
                <input type="password" name="confirmPassword" maxlength="30" required 
                       id="confirmPassword">
            </div>
            
            <div class="button-group">
                <button type="submit" class="btn btn-primary">Register</button>
                <button type="reset" class="btn btn-secondary">Reset</button>
            </div>
        </form>
    </div>

    <script>
    function validateForm() {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;
        
        if (password !== confirmPassword) {
            alert("Passwords do not match!");
            return false;
        }
        return true;
    }
    </script>
</body>
</html>