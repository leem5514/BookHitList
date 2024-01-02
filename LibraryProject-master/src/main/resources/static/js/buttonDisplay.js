const writingButton = document.getElementById("writing_button");
writingButton.addEventListener('click', function (event) {
    if (localStorage.length === 0) {
        alert('로그인을 하셔야합니다.');
        return;
    }
    window.location.href = '/board/save';
})