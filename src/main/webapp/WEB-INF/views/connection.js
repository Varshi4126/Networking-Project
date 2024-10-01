$(document).ready(function() {
    // Connect button click handler
    $(document).on('click', '.connect-btn', function() {
        var button = $(this);
        var targetUserId = button.data('user-id');
        var url = button.data('url');
        var initiatorUserId = getCurrentUserId();

        $.ajax({
            url: url,
            type: 'POST',
            data: {
                initiatorUserId: initiatorUserId,
                targetUserId: targetUserId
            },
            success: function(response) {
                button.html('<i class="bi bi-hourglass-split"></i> Pending').prop('disabled', true).removeClass('btn-outline-success').addClass('btn-outline-secondary');
                showNotification('Connection request sent successfully!');
            },
            error: function(xhr, status, error) {
                showNotification('Error sending connection request: ' + xhr.responseText, 'error');
            }
        });
    });

    // Follow button click handler
    $(document).on('click', '.follow-btn', function() {
        var button = $(this);
        var targetUserId = button.data('user-id');
        var url = button.data('url');
        var followerId = getCurrentUserId();

        $.ajax({
            url: url,
            type: 'POST',
            data: {
                followerId: followerId,
                followedId: targetUserId
            },
            success: function(response) {
                button.html('<i class="bi bi-person-check"></i> Following').prop('disabled', true).removeClass('btn-outline-secondary').addClass('btn-outline-primary');
                showNotification('You are now following this user!');
            },
            error: function(xhr, status, error) {
                showNotification('Error following user: ' + xhr.responseText, 'error');
            }
        });
    });

    // Helper function to show notifications
    function showNotification(message, type = 'success') {
        // Implement this function to show notifications to the user
        // You can use a library like toastr or create your own notification system
        alert(type + ': ' + message);
    }

    // Helper function to get the current user's ID
    function getCurrentUserId() {
        // Implement this function to return the current user's ID
        // You might get this from a global variable, a data attribute on the body, etc.
        return window.currentUserId || 1; // Fallback to 1 if not set
    }

    // Function to update connection status
    function updateConnectionStatus() {
        $('.connect-btn, .follow-btn').each(function() {
            var button = $(this);
            var targetUserId = button.data('user-id');
            var currentUserId = getCurrentUserId();

            $.ajax({
                url: '/connections/status',
                type: 'GET',
                data: {
                    userId1: currentUserId,
                    userId2: targetUserId
                },
                success: function(status) {
                    if (status === 'CONNECTED') {
                        button.html('<i class="bi bi-person-check-fill"></i> Connected').prop('disabled', true).removeClass('btn-outline-success').addClass('btn-outline-secondary');
                    } else if (status === 'PENDING') {
                        button.html('<i class="bi bi-hourglass-split"></i> Pending').prop('disabled', true).removeClass('btn-outline-success').addClass('btn-outline-secondary');
                    } else if (status === 'FOLLOWING') {
                        button.html('<i class="bi bi-person-check"></i> Following').prop('disabled', true).removeClass('btn-outline-secondary').addClass('btn-outline-primary');
                    }
                }
            });
        });
    }

    // Call updateConnectionStatus when the page loads
    updateConnectionStatus();
});