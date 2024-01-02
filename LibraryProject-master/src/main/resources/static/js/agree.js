var agree1 = document.getElementById("checkbox1");
var agree2 = document.getElementById("checkbox2");
var agree3 = document.getElementById("checkbox3");
var agree4 = document.getElementById("checkbox4");
const signupButton = document.getElementById("signup");

signupButton.addEventListener('click', function (event) {
    if (agree1 === true && agree2 === true && agree3 === true && agree4 === true) {
        return null;
    }
    alert('동의를 하셔야합니다.');
    return window.location.href = '/signup';
})


