$(document).ready(function() {
    loadRecentJobs();

    $('#jobPostForm').submit(function(e) {
        e.preventDefault();
        createJobPost();
    });

    function loadRecentJobs() {
        $.ajax({
            url: '/api/jobs/recent',
            method: 'GET',
            success: function(response) {
                displayJobs(response);
            },
            error: function(xhr, status, error) {
                console.error("Error loading recent jobs:", error);
            }
        });
    }

    function createJobPost() {
        var jobData = {
            title: $('#jobTitle').val(),
            description: $('#jobDescription').val(),
            location: $('#jobLocation').val(),
            salary: $('#salary').val()
        };

        $.ajax({
            url: '/api/jobs',
            method: 'POST',
            data: JSON.stringify(jobData),
            contentType: 'application/json',
            success: function(response) {
                $('#jobPostForm')[0].reset();
                loadRecentJobs();
            },
            error: function(xhr, status, error) {
                console.error("Error creating job post:", error);
            }
        });
    }

    function displayJobs(jobs) {
        var container = $('#jobsContainer');
        container.empty();

        jobs.forEach(function(job) {
            var jobHtml = `
                <div class="card job-card">
                    <div class="card-body">
                        <h5 class="card-title">${job.title}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">${job.company.name}</h6>
                        <p class="card-text">${job.description}</p>
                        <p><strong>Location:</strong> ${job.location}</p>
                        <p><strong>Salary:</strong> $${job.salary}</p>
                    </div>
                </div>
            `;
            container.append(jobHtml);
        });
    }
});