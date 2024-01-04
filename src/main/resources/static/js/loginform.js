/**
 * [[ code ]]
 * 1: 회원가입이 완료되었습니다.
 * -4: 아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해주세요.
 * -5: 시스템 문제로 로그인 요청을 처리할 수 없습니다. 관리자에게 문의하세요.
 * -999: 예상치 못한 오류가 발생했습니다.
 * */
let queryString = window.location.search;
let urlParams = new URLSearchParams(queryString);
let code = urlParams.get("code");
let errMsg = document.getElementById("errMsg");

if (code == 1) {
    alert("회원가입이 완료되었습니다.");
} else if (code == -4) {
    errMsg.style.display = "inline-block";
    errMsg.innerHTML = "아이디 혹은 비밀번호가 일치하지 않습니다.<br>입력한 내용을 다시 확인해주세요."
} else if (code == -5) {
    errMsg.style.display = "inline-block";
    errMsg.innerHTML = "시스템 문제로 로그인 요청을 처리할 수 없습니다.<br>관리자에게 문의하세요."
} else if (code == -999) {
    errMsg.style.display = "inline-block";
    errMsg.innerHTML = "예상치 못한 오류가 발생했습니다."
}

document.getElementById("loginBtn").onclick = () => {
    if (document.getElementById("username").value === "") {
        errMsg.style.display = "inline-block";
        errMsg.innerHTML = "아이디를 입력해주세요.";
        return false;
    } else if (document.getElementById("password").value === "") {
        errMsg.style.display = "inline-block";
        errMsg.innerHTML = "비밀번호를 입력해주세요.";
        return false;
    }
    document.getElementById("loginform").submit();
};