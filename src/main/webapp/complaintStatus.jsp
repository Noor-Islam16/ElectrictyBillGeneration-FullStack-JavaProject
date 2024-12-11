<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Complaint Status - Electricity Billing System</title>
    <style>
        /* Keep your existing styles */
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
        .status-container {
            width: 60%;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .form-group {
            margin-bottom: 20px;
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
        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 10px;
        }
        .btn-primary {
            background-color: #007bff;
            color: white;
        }
        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }
        .status-details {
            margin-top: 20px;
            padding: 15px;
            background-color: #f8f9fa;
            border-radius: 4px;
            display: none;
        }
        .status-row {
            margin-bottom: 10px;
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

    <div class="status-container">
        <h2>Check Complaint Status</h2>
        
        <form id="statusForm">
            <div class="form-group">
                <label>Complaint Number:</label>
                <input type="text" name="complaintId" required 
                       pattern="[0-9]+" 
                       title="Please enter a valid complaint ID (e.g., 1, 2, 3)">
            </div>

            <button type="submit" class="btn btn-primary">Get Complaint Status</button>
            <button type="reset" class="btn btn-secondary">Reset</button>
        </form>

        <div class="status-details" id="statusDetails">
            <h3>Complaint Details</h3>
            <div class="status-row">
                <strong>Complaint ID:</strong> <span id="displayComplaintId"></span>
            </div>
            <div class="status-row">
                <strong>Type:</strong> <span id="complaintType"></span>
            </div>
            <div class="status-row">
                <strong>Category:</strong> <span id="category"></span>
            </div>
            <div class="status-row">
                <strong>Description:</strong> <span id="description"></span>
            </div>
            <div class="status-row">
                <strong>Contact Person:</strong> <span id="contactPerson"></span>
            </div>
            <div class="status-row">
                <strong>Contact Number:</strong> <span id="contactNumber"></span>
            </div>
            <div class="status-row">
                <strong>Address:</strong> <span id="address"></span>
            </div>
        </div>
    </div>

    <script>
        document.getElementById('statusForm').onsubmit = function(e) {
            e.preventDefault();
            const complaintId = document.getElementsByName('complaintId')[0].value;
            
            // Make AJAX call to check status
            fetch('checkStatus?complaintId=' + complaintId)
                .then(response => response.json())
                .then(data => {
                    if(data.found) {
                        document.getElementById('displayComplaintId').textContent = data.complaintId;
                        document.getElementById('complaintType').textContent = data.type;
                        document.getElementById('category').textContent = data.category;
                        document.getElementById('description').textContent = data.description;
                        document.getElementById('contactPerson').textContent = data.contactPerson;
                        document.getElementById('contactNumber').textContent = data.contactNumber;
                        document.getElementById('address').textContent = data.address;
                        document.getElementById('statusDetails').style.display = 'block';
                    } else {
                        alert('No complaint found with the given ID');
                        document.getElementById('statusDetails').style.display = 'none';
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error checking complaint status');
                });
        };
    </script>
</body>
</html>