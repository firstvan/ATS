function createRegistration() {
    let rows = getActiveRows();
    let IDs = [];
    let type = document.getElementById("employeereg-name-select").value;
    let level = document.getElementById("employeereg-level-select").value;
    let location = document.getElementById("employeereg-location-select").value;

    let formData = new FormData();
    rows.forEach(
        row => IDs.push(getRowId(row))
    );
    formData.append("employee_ids", IDs);
    formData.append("type", type);
    formData.append("level", level);
    formData.append("location", location);
    sendData(formData);
}

function getActiveRows() {
    let allCheckBox = document.querySelectorAll(".operation-checkbox");
    let activeRows = [];
    allCheckBox.forEach(c => {
        if(c.checked)
    activeRows.push(c.parentElement.parentElement);
});
    return activeRows;
}

function getRowId(row) {
    return row.querySelector(".employee-id").innerHTML;
}

function sendData(formData) {
    const token = document.querySelector('#csrf_token').getAttribute('content');
    let xhttpreq = new XMLHttpRequest();
    xhttpreq.onreadystatechange = function()  {
        if (this.readyState == 4 && this.status == 200) {
            let obj = JSON.parse(xhttpreq.responseText);
            alert(obj.text);
            window.location.reload();
        }
    }
    xhttpreq.open("POST", '/create-job-registration', true);
    xhttpreq.setRequestHeader('X-CSRF-TOKEN', token);
    xhttpreq.send(formData);
}