document.addEventListener('DOMContentLoaded', () => {
    const fileUploaders = document.querySelectorAll('.file-uploader-button');
    fileUploaders.forEach(u => u.addEventListener('click', uploadCV))
    const employeeCreatorButton = document.getElementById('employee-submit');
    employeeCreatorButton.addEventListener('click', createEmployee);
});

let DBFile = null;

function uploadCV(event) {
    event.preventDefault();
    let formData = new FormData();
    const file = document.querySelector('#cv-input').files[0];
    const token = document.querySelector('#csrf_token').getAttribute('content');

    formData.append("file", file);
    let xhttpreq = new XMLHttpRequest();
    xhttpreq.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            DBFile = xhttpreq.response;
        }
    }
    xhttpreq.open("POST", '/upload-file', true);
    xhttpreq.setRequestHeader('X-CSRF-TOKEN', token);
    xhttpreq.send(formData);
}


function createEmployee(event) {
    event.preventDefault();
    let formData = new FormData();
    const token = document.querySelector('#csrf_token').getAttribute('content');
    if(DBFile != null) {
        formData.append('firstName', document.getElementById('first-name-input').value);
        formData.append('lastName', document.getElementById('last-name-input').value);
        formData.append('birthPlace', document.getElementById('birthplace-input').value);
        formData.append('mail', document.getElementById('mail-input').value);
        formData.append('birthName', document.getElementById('birth-name-input').value);
        formData.append('mother', document.getElementById('mother-input').value);
        formData.append('birthDay', document.getElementById('date-input').value);
        formData.append('phoneNumber', document.getElementById('phone-number-input').value);
        formData.append('status', document.getElementById('status-input').value);
        formData.append('type', document.getElementById('job-name-select').value);
        formData.append('level', document.getElementById('job-level-select').value);
        formData.append('location', document.getElementById('job-location-select').value);
        formData.append('CVFile', DBFile);

        let xhttpreq = new XMLHttpRequest();
        xhttpreq.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                console.log("Működik!");
            }
        }
        xhttpreq.open("POST", '/create-employee', true);
        xhttpreq.setRequestHeader('X-CSRF-TOKEN', token);
        xhttpreq.send(formData);
    }
}
