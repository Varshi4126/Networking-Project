<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Company Dashboard - ConnectEd</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <style>
        :root {
            --primary-color: #4e73df;
            --secondary-color: #858796;
            --background-color: #f8f9fc;
        }
        body {
            font-family: 'Nunito', sans-serif;
            background-color: var(--background-color);
        }
        .sidebar {
            position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            z-index: 100;
            padding: 48px 0 0;
            box-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);
            background-color: var(--primary-color);
        }
        .sidebar-sticky {
            position: relative;
            top: 0;
            height: calc(100vh - 48px);
            padding-top: .5rem;
            overflow-x: hidden;
            overflow-y: auto;
        }
        .sidebar .nav-link {
            font-weight: 500;
            color: #fff;
        }
        .sidebar .nav-link:hover {
            background-color: rgba(255,255,255,0.1);
        }
        .sidebar .nav-link.active {
            color: #fff;
            background-color: rgba(255,255,255,0.2);
        }
        .main-content {
            margin-left: 200px;
        }
        .navbar {
            background-color: #fff;
            box-shadow: 0 .15rem 1.75rem 0 rgba(58,59,69,.15);
        }
        .card {
            border: none;
            box-shadow: 0 .15rem 1.75rem 0 rgba(58,59,69,.15);
            transition: all 0.3s ease;
        }
        .card:hover {
            transform: translateY(-5px);
        }
        .btn-primary {
            background-color: var(--primary-color);
            border-color: var(--primary-color);
        }
        .btn-primary:hover {
            background-color: #2e59d9;
            border-color: #2e59d9;
        }
        .job-card {
            margin-bottom: 20px;
        }
        .job-actions {
            display: flex;
            justify-content: space-around;
            padding: 10px 0;
            border-top: 1px solid #eee;
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light fixed-top">
        <div class="container-fluid">
            <a class="navbar-brand fw-bold text-primary" href="#">ConnectEd</a>
            <div class="d-flex">
                <span class="navbar-text me-3">Welcome, ${company.name}</span>
                <a href="#" id="logoutBtn" class="btn btn-outline-primary">Logout</a>
            </div>
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row">
            <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block sidebar collapse">
        <div class="position-sticky pt-3 sidebar-sticky">
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link active" href="#" id="dashboard-link">
                        <i class="bi bi-house-door"></i> Dashboard
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#" id="statistics-link">
                        <i class="bi bi-graph-up"></i> Statistics
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#" id="update-profile-link">
                        <i class="bi bi-person"></i> Update Profile
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#" id="delete-account-link">
                        <i class="bi bi-trash"></i> Delete Account
                    </a>
                </li>
            </ul>
        </div>
    </nav>

            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 main-content">
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2">Company Dashboard</h1>
                </div>

                <div id="dashboard-content">
                    <div class="row mb-4">
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="card-title">Search</h3>
                                    <form id="searchForm">
                                        <div class="input-group mb-3">
                                            <input type="text" class="form-control" placeholder="Search for users..." id="searchInput" required>
                                            <button class="btn btn-primary" type="submit">Search</button>
                                        </div>
                                    </form>
                                    <div id="searchResults" class="mt-4" style="display: none;">
                                        <h4>Search Results</h4>
                                        <div id="searchResultsContent" class="row row-cols-1 row-cols-md-2 g-4"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="card-title">Create a Job Posting</h3>
                                    <form id="jobPostForm">
                                        <div class="mb-3">
                                            <input type="text" class="form-control" id="jobTitle" placeholder="Job Title" required>
                                        </div>
                                        <div class="mb-3">
                                            <textarea class="form-control" id="jobDescription" rows="3" placeholder="Job Description" required></textarea>
                                        </div>
                                        <div class="mb-3">
                                            <input type="text" class="form-control" id="jobLocation" placeholder="Job Location" required>
                                        </div>
                                        <div class="mb-3">
                                            <input type="number" class="form-control" id="salary" placeholder="Salary" required>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Post Job</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h3 class="card-title">Recent Job Postings</h3>
                                <button id="viewAllJobs" class="btn btn-primary">View All Jobs</button>
                            </div>
                            <div id="jobsContainer" class="row row-cols-1 row-cols-md-2 g-4"></div>
                        </div>
                    </div>
                </div>
                
                <div id="statistics-content" style="display: none;">
				    <div class="row mb-4">
				        <div class="col-md-6">
				            <div class="card">
				                <div class="card-body">
				                    <h3 class="card-title">Your Company Statistics</h3>
				                    <canvas id="companyStatsChart"></canvas>
				                </div>
				            </div>
				        </div>
				        <div class="col-md-6">
				            <div class="card">
				                <div class="card-body">
				                    <h3 class="card-title">All Companies Job Count</h3>
				                    <canvas id="allCompaniesJobCountChart"></canvas>
				                </div>
				            </div>
				        </div>
				    </div>
				    <div class="row">
				        <div class="col-md-12">
				            <div class="card">
				                <div class="card-body">
				                    <h3 class="card-title">All Companies Post Count</h3>
				                    <canvas id="allCompaniesPostCountChart"></canvas>
				                </div>
				            </div>
				        </div>
				    </div>
				</div>
                
                

                <div id="update-profile-content" style="display: none;">
                    <div class="card">
                        <div class="card-body">
                            <h2 class="card-title">Update Company Profile</h2>
                            <div id="companyProfileContent"></div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
    $(document).ready(function() {
        var currentUserId;
        var currentUserName = "${company.name}";
        var jobCount = ${company.jobsCount};
        var postCount = jobCount; // Initialize postCount to be equal to jobCount
        console.log(currentUserName);
        var isViewingAllJobs = false;

        function showDashboard() {
            $('#dashboard-content').show();
            $('#update-profile-content').hide();
            $('#statistics-content').hide();
            loadCompanyJobs(currentUserName);
        }

        function showProfileUpdate() {
            $('#dashboard-content').hide();
            $('#update-profile-content').show();
            $('#statistics-content').hide();
            loadCompanyProfile();
        }

        function showStatistics() {
            $('#dashboard-content').hide();
            $('#update-profile-content').hide();
            $('#statistics-content').show();
            fetchCompanyStatistics();
        }

        $('#dashboard-link').click(function(e) {
            e.preventDefault();
            showDashboard();
        });

        $('#update-profile-link').click(function(e) {
            e.preventDefault();
            showProfileUpdate();
        });

        $('#statistics-link').click(function(e) {
            e.preventDefault();
            showStatistics();
        });

        $('#delete-account-link').click(function(e) {
            e.preventDefault();
            if (confirm("Are you sure you want to delete your account? This action cannot be undone.")) {
                alert("Account deletion request submitted. An administrator will contact you shortly.");
            }
        });

        $('#logoutBtn').click(function(e) {
            e.preventDefault();
            alert("Logged out successfully. Redirecting to login page...");
            window.location.href = '/login1';
        });

        $('#searchForm').submit(function(e) {
            e.preventDefault();
            var query = $('#searchInput').val();
            $.ajax({
                url: '/search',
                method: 'GET',
                data: { query: query },
                success: function(response) {
                    $('#searchResults').show();
                    $('#searchResultsContent').html(response);
                },
                error: function(xhr, status, error) {
                    console.error("Search error:", error);
                    alert("An error occurred while searching. Please try again.");
                }
            });
        });

        $('#jobPostForm').submit(function(e) {
            e.preventDefault();
            createJob();
        });

        function createJob() {
            var jobData = {
                username: currentUserName,
                title: $('#jobTitle').val(),
                desc: $('#jobDescription').val(),
                location: $('#jobLocation').val(),
                salary: parseFloat($('#salary').val())
            };

            $.ajax({
                url: '/api/jobs',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(jobData),
                success: function(response) {
                    $('#jobTitle').val('');
                    $('#jobDescription').val('');
                    $('#jobLocation').val('');
                    $('#salary').val('');
                    alert('Job posted successfully!');
                    jobCount++;
                    postCount++;
                    loadCompanyJobs(currentUserName);
                    updateCharts();
                },
                error: function(xhr, status, error) {
                    console.error("Job creation error:", error);
                    alert("Error occurred while creating the job. Please try again.");
                }
            });
        }

        function loadRecentJobs() {
            $.ajax({
                url: '/api/jobs/recent',
                method: 'GET',
                success: function(response) {
                    displayJobs(response);
                },
                error: function(xhr, status, error) {
                    console.error("Error loading jobs:", error);
                    alert("Error occurred while loading recent jobs. Please refresh the page.");
                }
            });
        }

        function loadCompanyJobs(username) {
            $.ajax({
                url: '/api/jobs/company/' + username,
                method: 'GET',
                success: function(response) {
                    displayJobs(response);
                    jobCount = response.length;
                    updateCharts();
                },
                error: function(xhr, status, error) {
                    console.error('Error loading jobs for company ' + username + ':', error);
                    alert('Error occurred while loading jobs for ' + username + '. Please try again.');
                }
            });
        }

        function displayJobs(jobs) {
                    console.log("Displaying jobs:", jobs); // Debug log
                    var container = $('#jobsContainer');
                    if (!container.length) {
                        console.error("Job container not found!");
                        return;
                    }
                    container.empty();
                    
                    if (Array.isArray(jobs) && jobs.length > 0) {
                        jobs.forEach(function(job, index) {
                            console.log(`Processing job ${index}:`, job); // Debug log
                            
                            if (job && typeof job === 'object') {
                                // Create the job HTML with spans for title, description, location, and salary
                                var jobHtml = `
                                <div class="col">
                                    <div class="card job-card">
                                        <div class="card-body">
                                            <h5 class="card-title"><span class="job-title"></span></h5>
                                            <p class="card-text"><span class="job-description"></span></p>
                                            <p class="card-text"><small class="text-muted">Location: <span class="job-location"></span></small></p>
                                            <p class="card-text"><small class="text-muted">Salary: $<span class="job-salary"></span></small></p>
                                            <div class="job-actions mt-3">
                                                <button class="btn btn-outline-primary btn-sm"><i class="bi bi-pencil"></i> Edit</button>
                                                <button class="btn btn-outline-danger btn-sm"><i class="bi bi-trash"></i> Delete</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>`;
                                
                                // Append the job HTML to the container
                                container.append(jobHtml);
                                
                                // Find the last spans with the appropriate classes and set their content
                                container.find('.job-title').last().text(job.title || 'No title');
                                container.find('.job-description').last().text(job.description || 'No description');
                                container.find('.job-location').last().text(job.location || 'No location');
                                container.find('.job-salary').last().text(job.salary ? job.salary.toFixed(2) : 'N/A');
                            } else {
                                console.error(`Invalid job object at index ${index}:`, job);
                            }
                        });
                    } else {
                        console.log("No jobs to display or jobs is not an array");
                        container.html('<p>No jobs to display.</p>');
                    }
                }

        $('#viewAllJobs').click(function() {
            if (isViewingAllJobs) {
                $(this).text('View All Jobs');
                loadCompanyJobs(currentUserName);
            } else {
                $(this).text('View My Jobs');
                loadRecentJobs();
            }
            isViewingAllJobs = !isViewingAllJobs;
        });

        function loadCompanyProfile() {
            $.ajax({
                url: '/company-profile',
                method: 'GET',
                success: function(response) {
                    $('#companyProfileContent').html(response);
                }
            });
        }

        $(document).on('submit', '#updateProfileForm', function(e) {
            e.preventDefault();
            var updatedData = {
                username: $('#username').val(),
                email: $('#email').val()
                // Add other fields as necessary
            };

            $.ajax({
                url: `/api/companies/${currentUserId}`,
                method: 'PUT',
                data: JSON.stringify(updatedData),
                contentType: 'application/json',
                success: function() {
                    alert("Profile updated successfully.");
                    showDashboard();
                },
                error: function(xhr, status, error) {
                    console.error("Profile update error:", error);
                    alert("Error occurred while updating profile. Please try again.");
                }
            });
        });

        function fetchCompanyStatistics() {
            $.ajax({
                url: '/api/company-statistics',
                method: 'GET',
                success: function(data) {
                    updateCharts(data);
                },
                error: function(xhr, status, error) {
                    console.error("Error fetching company statistics:", error);
                    alert("An error occurred while fetching statistics. Please try again.");
                }
            });
        }

        function updateCharts(companiesData) {
            updateCompanyStatsChart(companiesData.find(company => company.company_name === currentUserName));
            updateAllCompaniesJobCountChart(companiesData);
            updateAllCompaniesPostCountChart(companiesData);
        }

        function updateCompanyStatsChart(companyData) {
            if (!companyData) {
                console.error("Current company data not found");
                return;
            }
            var ctx = document.getElementById('companyStatsChart').getContext('2d');
            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ['Job Count', 'Post Count'],
                    datasets: [{
                        label: 'Count',
                        data: [companyData.job_count, companyData.post_count],
                        backgroundColor: [
                            'rgba(75, 192, 192, 0.6)',
                            'rgba(255, 99, 132, 0.6)'
                        ],
                        borderColor: [
                            'rgba(75, 192, 192, 1)',
                            'rgba(255, 99, 132, 1)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: {
                            beginAtZero: true,
                            title: {
                                display: true,
                                text: 'Count'
                            }
                        }
                    },
                    plugins: {
                        title: {
                            display: true,
                            text: 'Your Company Statistics'
                        }
                    }
                }
            });
        }

        function updateAllCompaniesJobCountChart(companiesData) {
            var ctx = document.getElementById('allCompaniesJobCountChart').getContext('2d');
            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: companiesData.map(company => company.company_name),
                    datasets: [{
                        label: 'Job Count',
                        data: companiesData.map(company => company.job_count),
                        backgroundColor: 'rgba(54, 162, 235, 0.6)',
                        borderColor: 'rgba(54, 162, 235, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: {
                            beginAtZero: true,
                            title: {
                                display: true,
                                text: 'Job Count'
                            }
                        }
                    },
                    plugins: {
                        title: {
                            display: true,
                            text: 'All Companies Job Count'
                        }
                    }
                }
            });
        }

        function updateAllCompaniesPostCountChart(companiesData) {
            var ctx = document.getElementById('allCompaniesPostCountChart').getContext('2d');
            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: companiesData.map(company => company.company_name),
                    datasets: [{
                        label: 'Post Count',
                        data: companiesData.map(company => company.post_count),
                        backgroundColor: 'rgba(255, 206, 86, 0.6)',
                        borderColor: 'rgba(255, 206, 86, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: {
                            beginAtZero: true,
                            title: {
                                display: true,
                                text: 'Post Count'
                            }
                        }
                    },
                    plugins: {
                        title: {
                            display: true,
                            text: 'All Companies Post Count'
                        }
                    }
                }
            });
        }

        // Initialize the dashboard
        showDashboard();
    });
    </script>
</body>
</html>