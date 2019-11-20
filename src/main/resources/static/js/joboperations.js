document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.delete-job-button').forEach(b => {
        b.addEventListener('click', deleteJob);
    });
});

function deleteJob() {
    const row = this.parentElement.parentElement;
    const rowId = row.getAttribute('data-job-id');
    const token = document.querySelector('#csrf_token').getAttribute('content');

    let xhttpreq = new XMLHttpRequest();
    xhttpreq.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            alert("Sikeresen lezártad a pozíciót!");
            window.location.reload();
        }
    }
    xhttpreq.open("POST", `/delete-job/${rowId}`, true);
    xhttpreq.setRequestHeader('X-CSRF-TOKEN', token);
    xhttpreq.send();
}