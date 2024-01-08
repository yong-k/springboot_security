/**
 * [code]
 *  2   : 회원 탈퇴가 완료되었습니다.
 * -3   : 비밀번호가 일치하지 않습니다.
 * */

$(document).ready(function(){
    let queryString = window.location.search;
    let urlParams = new URLSearchParams(queryString);
    let code = urlParams.get("code");

    if (code == 2) {
        alert("회원탈퇴가 완료되었습니다.");
        $('form[name="logoutform"]').submit();
    } else if (code == -3) {
        alert("비밀번호가 일치하지 않습니다.");
    }
});