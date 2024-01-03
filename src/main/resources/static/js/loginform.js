/**
 * [[ code ]]
 * 1: 회원가입이 완료되었습니다.
 * -1: 아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해주세요.
 * -2: 시스템 문제로 로그인 요청을 처리할 수 없습니다. 관리자에게 문의하세요.
 * -99: 예상치 못한 오류가 발생했습니다.
 * */
let queryString = window.location.search;
let urlParams = new URLSearchParams(queryString);
let code = urlParams.get("code");
let errMsg = document.getElementById("errMsg");

if (code == 1) {
    alert("회원가입이 완료되었습니다.");
} else if (code == -1) {
    errMsg.style.display = "inline-block";
    errMsg.innerHTML = "아이디 혹은 비밀번호가 일치하지 않습니다.<br>입력한 내용을 다시 확인해주세요."
} else if (code == -2) {
    errMsg.style.display = "inline-block";
    errMsg.innerHTML = "시스템 문제로 로그인 요청을 처리할 수 없습니다.<br>관리자에게 문의하세요."
} else if (code == -99) {
    errMsg.style.display = "inline-block";
    errMsg.innerHTML = "예상치 못한 오류가 발생했습니다."
}