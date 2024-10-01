<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Company Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .profile-header { background-color: #28a745; color: white; padding: 2rem 0; margin-bottom: 2rem; }
        .company-logo { width: 150px; height: 150px; object-fit: contain; background-color: white; padding: 10px; }
    </style>
</head>
<body>
	<div class="profile-header">
	        <div class="container">
	            <div class="row align-items-center">
	                <div class="col-md-3 text-center">
	                    <img src="/api/placeholder/150/150" alt="Company Logo" class="rounded company-logo">
	                </div>
	                <div class="col-md-6">
	                    <h1>${company.name}</h1>
	                </div>
	                <div class="col-md-3 text-end">
	                    <button class="btn btn-outline-light">Follow</button>
	                </div>
	            </div>
	        </div>
	    </div>

    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title">About the Company</h5>
                        <p class="card-text">${company.about}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title">Company Statistics</h5>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                Posts
                                <span class="badge bg-primary rounded-pill">${company.postsCount}</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                Jobs
                                <span class="badge bg-primary rounded-pill">${company.jobsCount}</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container mt-4">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Edit Company Profile</h5>
                <form action="/company-profile" method="post">
                    <div class="mb-3">
                        <label for="name" class="form-label">Company Name</label>
                        <input type="text" class="form-control" id="name" name="name" value="${company.name}" required>
                    </div>
                    <div class="mb-3">
                        <label for="about" class="form-label">About the Company</label>
                        <textarea class="form-control" id="about" name="about" rows="5">${company.about}</textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Update Profile</button>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>