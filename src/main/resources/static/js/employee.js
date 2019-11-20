var DBFile = null;

document.addEventListener('DOMContentLoaded', () => {
    const employeeCreatorButton = document.getElementById('employee-submit');
    const showdata=document.getElementById('edit');
    showdata.addEventListener('click',myFunction);
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
        } else {
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
    if(!validTypes.includes(fileType)) {
        return true;
    }

    return false;
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
$(document).ready(function(){

        $('.table .btn').on('click',function (event) {
            var href= $(this).attr('href');
            event.preventDefault();
            console.log(href);
            $(function() {
               /* $.ajax({
                    url: href,
                    type: 'GET',
                    dataType: 'json',
                    success: function(jelolt) {
                        $('#first-name-input-edit').val(jelolt.firstName);                    }
                });*/

                $('#form-modal-edit').modal();
            });
        });
});
function myFunction()
{
    var obj;
    console.log("itt");
    var href= $(this).attr('href');
    console.log(href);
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open('GET',href, true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4) {
            if(xmlhttp.status == 200) {
                obj = JSON.parse(xmlhttp.responseText);
                document.getElementById("first-name-input-edit").value =obj.firstName;
                document.getElementById("last-name-input-edit").value =obj.lastName;
                document.getElementById("birth-name-input-edit").value =obj.birthName;
                document.getElementById("birthplace-input-edit").value =obj.birthPlace;
                document.getElementById("mother-input-edit").value =obj.mother;
                document.getElementById("date-input-edit").value =obj.birthDay;
                document.getElementById("mail-input-edit").value =obj.mail;
                document.getElementById("status-input-edit").value =obj.status;
                document.getElementById("phone-number-input-edit").value =obj.phoneNumber;

            }
        }
    };
    xmlhttp.send(null);

    return obj;
}

