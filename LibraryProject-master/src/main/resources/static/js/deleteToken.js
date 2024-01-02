const logoutButton = document.getElementById("logout_button");
logoutButton.addEventListener('click', function (event) {
    window.localStorage.removeItem("access_token");
    alert("로그아웃 되었습니다.");
    window.location.href = '/';
})