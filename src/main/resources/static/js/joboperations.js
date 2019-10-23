document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.delete-job-button').forEach(b => {
        b.addEventListener('click', deleteJob);
    })
});

function deleteJob() {
    const row = this.parentElement.parentElement;
    const rowId = row.getAttribute('data-job-id');
    let xhttpreq = new XMLHttpRequest();
    xhttpreq.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            /*window.location.reload(true);*/
            window.location.replace("/job-operations/1");
         }
    }
    xhttpreq.open("POST", `/delete-job/${rowId}`, true);
    xhttpreq.send();
}