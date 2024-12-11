<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register Complaint - Electricity Billing System</title>
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
        .complaint-container {
            width: 60%;
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
        input, select, textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        textarea {
            height: 100px;
            resize: vertical;
        }
        .btn {
            padding: 10px 20px;
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

    <div class="complaint-container">
        <h2>Register Complaint/Service</h2>
        
        <form action="registerComplaint" method="post" onsubmit="return validateForm()">
            <div class="form-group">
                <label>Complaint/Service Type:</label>
                <select name="complaintType" id="complaintType" required onchange="updateCategories()">
                    <option value="">Select Type</option>
                    <option value="billing">Billing Related</option>
                    <option value="voltage">Voltage Related</option>
                    <option value="disruption">Frequent Disruption</option>
                    <option value="streetlight">Street Light Related</option>
                    <option value="pole">Pole Related</option>
                </select>
            </div>
            
            <div class="form-group">
                <label>Category:</label>
                <select name="category" id="category" required>
                    <option value="">Select Category</option>
                </select>
            </div>
            
            <div class="form-group">
                <label>Contact Person:</label>
                <input type="text" name="contactPerson" required>
            </div>

            <div class="form-group">
                <label>Contact Number:</label>
                <input type="text" name="contactNumber" required pattern="\d{10}">
            </div>

            <div class="form-group">
                <label>Description:</label>
                <textarea name="description" required></textarea>
            </div>

            <div class="form-group">
                <label>Address:</label>
                <textarea name="address" required></textarea>
            </div>

            <button type="submit" class="btn btn-primary">Submit Complaint</button>
            <button type="reset" class="btn btn-secondary">Reset</button>
        </form>
    </div>

    <script>
        const categoryMap = {
            billing: ['Incorrect Bill', 'Payment Not Updated', 'Double Billing'],
            voltage: ['High Voltage', 'Low Voltage', 'Voltage Fluctuation'],
            disruption: ['Frequent Power Cuts', 'Unscheduled Outage', 'Regular Disconnection'],
            streetlight: ['Light Not Working', 'Dim Light', 'Broken Fixture'],
            pole: ['Leaning Pole', 'Damaged Pole', 'Wire Hanging']
        };

        function updateCategories() {
            const complaintType = document.getElementById('complaintType').value;
            const categorySelect = document.getElementById('category');
            categorySelect.innerHTML = '<option value="">Select Category</option>';

            if (complaintType && categoryMap[complaintType]) {
                categoryMap[complaintType].forEach(category => {
                    const option = document.createElement('option');
                    option.value = category;
                    option.textContent = category;
                    categorySelect.appendChild(option);
                });
            }
        }

        function validateForm() {
            const contactNumber = document.getElementsByName('contactNumber')[0].value;
            if (!/^\d{10}$/.test(contactNumber)) {
                alert('Please enter a valid 10-digit contact number');
                return false;
            }
            return true;
        }
    </script>
</body>
