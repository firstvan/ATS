var DBFile = null;
var hasInvalid = true;

document.addEventListener('DOMContentLoaded', () => {
    const employeeCreatorButton = document.getElementById('employee-submit');
    employeeCreatorButton.addEventListener('click', validateRequiredInputs);
    employeeCreatorButton.addEventListener('click', createEmployee);
    let requiredFields = document.getElementById("employee-form").querySelectorAll("[required]");
    requiredFields.forEach(f => f.addEventListener('change', listenChangeInput));
    const cvInput = document.querySelector("#cv-input");
    cvInput.addEventListener("change", uploadCV);
});

function uploadCV() {
    let formData = new FormData();
    const file = this.files[0];
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

    let hasInvalid = hasInvalidInput();
    if(!hasInvalid) {
        let formData = new FormData();
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

        if (DBFile != null) {
            const token = document.querySelector('#csrf_token').getAttribute('content');
            let xhttpreq = new XMLHttpRequest();
            xhttpreq.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    alert("Sikeresen felvetted a rendszerbe az új jelöltet!");
                    $('#form-modal').modal('hide');
                }
            }
            xhttpreq.open("POST", '/create-employee', true);
            xhttpreq.setRequestHeader('X-CSRF-TOKEN', token);
            xhttpreq.send(formData);
        } else {
            alert("Kérlek tölts fel egy önéletrajzot!");
        }
    }   
}

function validateRequiredInputs() {
    let requiredFields = document.getElementById("employee-form").querySelectorAll("[required]");
    requiredFields.forEach(f => {
        if(f.value == '' && !f.classList.contains('is-invalid')) {
            f.classList.toggle("is-invalid");
        }
    });
}

function listenChangeInput(event) {
    let newVal = event.target.value;
    if(newVal != '' && event.target.classList.contains('is-invalid')) {
        event.target.classList.toggle('is-invalid');
    }
}

function hasInvalidInput() {
    let hasInvalid = false;
    let requiredFields = document.getElementById("employee-form").querySelectorAll("[required]");
    requiredFields.forEach(f => {
        if(f.classList.contains('is-invalid')) {
            hasInvalid = true;
        }
    });
    return hasInvalid;
}