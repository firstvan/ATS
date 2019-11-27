var DBFile = null;

document.addEventListener('DOMContentLoaded', () => {
    const employeeCreatorButton = document.getElementById('employee-submit');
    employeeCreatorButton.addEventListener('click', validateRequiredInputs);
    employeeCreatorButton.addEventListener('click', createEmployee);
    let requiredFields = document.getElementById("employee-form").querySelectorAll("[required]");
    requiredFields.forEach(f => f.addEventListener('change', listenChangeInput));
    const cvInput = document.querySelector("#cv-input");
    cvInput.addEventListener("change", uploadCV);
    const emailInput = document.getElementById('mail-input');
    emailInput.addEventListener('change', validateMail);

    $('#form-modal').on('hidden.bs.modal', function () {
        $(this).find('form').trigger('reset');
        DBFile = null;
    });
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
        let allInput = document.getElementById("employee-form").querySelectorAll(".form-control");
        let formData = getFormData(allInput);;
        formData.append('CVFile', DBFile);

        if (DBFile != null) {
            let fileType = JSON.parse(DBFile).contentType;
            if(!isInvalidDocumentFormat(fileType)) {
                const token = document.querySelector('#csrf_token').getAttribute('content');
                let xhttpreq = new XMLHttpRequest();
                xhttpreq.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        alert("Sikeresen felvetted a rendszerbe az új jelöltet!");
                        window.location.replace("/employee-creator/1");
                    }
                }
                xhttpreq.open("POST", '/create-employee', true);
                xhttpreq.setRequestHeader('X-CSRF-TOKEN', token);
                xhttpreq.send(formData);
            }
            else {
                alert("Kérlek megfelelő formátumú önéletrajzot tölts fel!");
            }
        }
        else {
            alert("Kérlek tölts fel egy érvényes önéletrajzot!");
        }
    }   
}

function getFormData(inputs) {
    let formData = new FormData();
    inputs.forEach(i => {
        formData.append(i.name, i.value);
    });
    return formData;
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

function isInvalidDocumentFormat(fileType) {
    let validTypes = ['application/vnd.openxmlformats-officedocument.wordprocessingml.document', 'application/pdf', '.doc', '.docx'];
    return !validTypes.includes(fileType);
}

function validateMail() {
    if(!isValidMail(this.value) && !this.classList.contains('is-invalid')) {
        this.classList.toggle('is-invalid');
    }
}

function isValidMail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}