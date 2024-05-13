document.getElementById('statusForm').addEventListener('submit', function (event) {
    event.preventDefault();

    var statusSelect = document.getElementById('statusSelect');
    var selectedStatus = statusSelect.options[statusSelect.selectedIndex].value;

    fetch('/courier/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: 'status=' + encodeURIComponent(selectedStatus)
    })
        .then(function (response) {
            if (response.ok) {
                document.getElementById('status').textContent = selectedStatus;
            } else {
                console.error('Failed to update delivery status');
            }
        })
        .catch(function (error) {
            console.error('Error:', error);
        });
});
