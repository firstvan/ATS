function openModal(a, b, c, d){
    console.log(a)
    console.log(b)
    console.log(c)
    console.log(d)

    $.ajax({
        url: "/jelentkezesek/get-applicant/" + a + "/" + b + "/" + c + "/" + d,
        success: function(data) {

            console.log(data);

            $('#form-modal-applicant').modal('show')

            document.getElementById('first-name-input').value = data.firstName;
            document.getElementById('last-name-input').value = data.lastName;
            document.getElementById('birth-name-input').value = data.birthName;
            document.getElementById('birth-date-input').value = data.birthDate;
            document.getElementById('birthplace-input').value = data.birthPlace;
            document.getElementById('mother-input').value = data.mother;
            document.getElementById('mail-input').value = data.mail;
            document.getElementById('phone-number-input').value = data.phoneNumber;
            document.getElementById('status-input').value = data.status;
            document.getElementById('type-input').value = data.type.name;
            document.getElementById('level-input').value = data.level.level;
            document.getElementById('location-input').value = data.location.city;
            //ne égj ki ez igy sikerült

        }
    });



}