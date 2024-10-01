<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Company Profile - ${profile.name}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">${profile.name}'s Profile</h1>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${profile.name}</h5>
                <p class="card-text">${profile.about}</p>
                <h6 class="mt-4">Posts Count</h6>
                <p>${profile.postsCount}</p>
                <h6 class="mt-4">Jobs Count</h6>
                <p>${profile.jobsCount}</p>
            </div>
        </div>
        <a href="${pageContext.request.contextPath}/" class="btn btn-primary mt-3">Back to Search</a>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>