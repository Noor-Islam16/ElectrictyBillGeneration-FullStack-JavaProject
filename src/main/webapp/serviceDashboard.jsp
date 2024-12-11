<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Service Representative Dashboard</title>
    <style>
        .topbar {
            background-color: #2c3e50;
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
        .stats-container {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
            padding: 20px;
        }
        .stat-box {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 5px;
            text-align: center;
        }
        .stat-number {
            font-size: 24px;
            font-weight: bold;
            color: #2c3e50;
        }
        .complaints-table {
            width: 95%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        .complaints-table th, .complaints-table td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }
        .complaints-table th {
            background-color: #f5f5f5;
        }
        .search-bar {
            padding: 20px;
            display: flex;
            gap: 10px;
        }
        .search-input {
            padding: 8px;
            width: 300px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .status-open {
            color: #e74c3c;
            font-weight: bold;
        }
        .status-assigned {
            color: #f39c12;
            font-weight: bold;
        }
        .status-resolved {
            color: #27ae60;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="topbar">
        <div class="menu">
            <a href="serviceDashboard">Dashboard</a>
            <a href="activeComplaints">Active Complaints</a>
            <a href="feedback">Feedback</a>
        </div>
        <div class="welcome">
            Welcome, ${serviceRepName}
            <form action="logout" method="post" style="display: inline;">
                <button type="submit" class="logout-btn">Logout</button>
            </form>
        </div>
    </div>

    <div class="stats-container">
        <div class="stat-box">
            <div class="stat-number">${totalComplaints}</div>
            <div>Total Complaints</div>
        </div>
        <div class="stat-box">
            <div class="stat-number">${openComplaints}</div>
            <div>Open Complaints</div>
        </div>
        <div class="stat-box">
            <div class="stat-number">${assignedComplaints}</div>
            <div>Assigned</div>
        </div>
        <div class="stat-box">
            <div class="stat-number">${resolvedComplaints}</div>
            <div>Resolved</div>
        </div>
    </div>

    <div class="search-bar">
        <input type="text" id="searchInput" class="search-input" 
               placeholder="Search by customer name or complaint type...">
        <input type="date" id="dateFilter" class="search-input">
    </div>

    <table class="complaints-table">
        <thead>
            <tr>
                <th>Complaint ID</th>
                <th>Customer Name</th>
                <th>Type</th>
                <th>Description</th>
                <th>Contact Number</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody id="complaintsTableBody">
            <c:forEach items="${complaints}" var="complaint">
                <tr>
                    <td>${complaint.complaintId}</td>
                    <td>${complaint.contactPerson}</td>
                    <td>${complaint.complaintType}</td>
                    <td>${complaint.description}</td>
                    <td>${complaint.contactNumber}</td>
                    <td class="status-${complaint.status.toLowerCase()}">${complaint.status}</td>
                    <td>
                        <button onclick="updateStatus('${complaint.complaintId}')">Update</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <script>
        // Search and filter functionality
        const searchInput = document.getElementById('searchInput');
        
        function filterComplaints() {
            const searchTerm = searchInput.value.toLowerCase();
            const rows = document.querySelectorAll('#complaintsTableBody tr');

            rows.forEach(row => {
                const customerName = row.cells[1].textContent.toLowerCase();
                const complaintType = row.cells[2].textContent.toLowerCase();
                const matches = customerName.includes(searchTerm) || 
                              complaintType.includes(searchTerm);
                row.style.display = matches ? '' : 'none';
            });
        }

        searchInput.addEventListener('input', filterComplaints);

        function updateStatus(complaintId) {
            const newStatus = prompt('Enter new status (Open/Assigned/Resolved):');
            if (newStatus) {
                fetch('updateComplaintStatus', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: `complaintId=${complaintId}&status=${newStatus}`
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        location.reload();
                    } else {
                        alert('Error updating status');
                    }
                });
            }
        }
    </script>
</body>
</html>