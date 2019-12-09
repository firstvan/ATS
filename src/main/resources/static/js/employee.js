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

    $('#form-modal').on('hidden.bs.modal', function (event) {
        $(this).find('form').trigger('reset');
        DBFile = null;
        employeeCreatorButton.hidden = false;

        let allInput = document.getElementById("employee-form").querySelectorAll(".form-control");
        allInput.forEach(i => {
            i.readOnly = false;
        });
    });

    $('#form-modal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var parent = button.parent().parent();
        var recipient = button.data('function');
        if(recipient == 'edit') {
            var rowId = parent.find(".employee-id").text();
            let xhttpreq = new XMLHttpRequest();
            xhttpreq.onreadystatechange = function (ev) {
                if (this.readyState == 4 && this.status == 200) {
                    let obj = JSON.parse(xhttpreq.responseText);
                    document.getElementById("employee-id").value = obj.id;
                    document.getElementById("first-name-input").value = obj.firstName;
                    document.getElementById("last-name-input").value = obj.lastName;
                    document.getElementById("birth-name-input").value = obj.birthName;
                    document.getElementById("birthplace-input").value = obj.birthPlace;
                    document.getElementById("mother-input").value = obj.mother;
                    document.getElementById("date-input").value = obj.birthDay;
                    document.getElementById("mail-input").value = obj.mail;
                    document.getElementById("status-input").value = obj.status;
                    document.getElementById("phone-number-input").value = obj.phoneNumber;
                    document.getElementById("job-name-select").value = obj.preferredJob.type.id;
                    document.getElementById("job-level-select").value = obj.preferredJob.level.id;
                    document.getElementById("job-location-select").value = obj.preferredJob.location.id;
                    DBFile = JSON.stringify({id : obj.cv.id, fileName : obj.cv.fileName, content : obj.cv.content, contentType: obj.cv.contentType});
                }
            }
            let href = `/getOne?id=${rowId}`;
            xhttpreq.open("GET", href, true);
            xhttpreq.send();
        }
    });

    $('#employee-viewer-modal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var parent = button.parent().parent();
        var rowId = parent.find(".employee-id").text();
        let xhttpreq = new XMLHttpRequest();
        xhttpreq.onreadystatechange = function (ev) {
            if (this.readyState == 4 && this.status == 200) {
                let obj = JSON.parse(xhttpreq.responseText);
                document.getElementById("employee-name").innerHTML = obj.firstName + " " + obj.lastName;
                document.getElementById("birth-name-dd").innerHTML = obj.birthName;
                document.getElementById("birthplace-dd").innerHTML = obj.birthPlace;
                document.getElementById("mother-dd").innerHTML = obj.mother;
                document.getElementById("birthday-dd").innerHTML = obj.birthDay;
                document.getElementById("mail-dd").innerHTML = obj.mail;
                document.getElementById("status-dd").innerHTML = obj.status;
                document.getElementById("phone-number-dd").innerHTML = obj.phoneNumber;
                document.getElementById("preferred-job-name-dd").value = obj.preferredJob.type.name;
                document.getElementById("preferred-job-level-dd").value = obj.preferredJob.level.level;
                document.getElementById("preferred-job-location-dd").value = obj.preferredJob.location.city;
                document.getElementById("cv-downloader").onclick = function () {
                    download(obj.cv.fileName, obj.cv.content);
                }
            }
        }
        let href = `/getOne?id=${rowId}`;
        xhttpreq.open("GET", href, true);
        xhttpreq.send();
    });
});

function download(filename, text) {
    var element = document.createElement('a');
    element.setAttribute('href', 'data:application/pdf;base64,' + text);
    element.setAttribute('download', filename);

    element.style.display = 'none';
    document.body.appendChild(element);

    element.click();

    document.body.removeChild(element);
}

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

function deleteEmployee(button) {
    let parent = button.parentElement.parentElement;
    let empId = parent.querySelector(".employee-id").innerHTML;
    const token = document.querySelector('#csrf_token').getAttribute('content');
    let ok = confirm("Biztosan törölni szeretnéd a jelöltet?");
    if(ok) {
        let xhttpreq = new XMLHttpRequest();
        xhttpreq.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                alert("Sikeres törlés!");
                window.location.replace("/employee-creator/1");
            }
        }
        xhttpreq.open("POST", `/delete/${empId}`, true);
        xhttpreq.setRequestHeader('X-CSRF-TOKEN', token);
        xhttpreq.send();
    }
}