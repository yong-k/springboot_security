/**
 * [[ code ]]
 * -4: 아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해주세요.
 * -5: 시스템 문제로 로그인 요청을 처리할 수 없습니다. 관리자에게 문의하세요.
 * -999: 예상치 못한 오류가 발생했습니다.
 * */

$(document).ready(function(){
    let queryString = window.location.search;
    let urlParams = new URLSearchParams(queryString);
    let code = urlParams.get("code");
    let errMsg = $("#errMsg");

    if (code == -4) {
        errMsg.css("display", "inline-block");
        errMsg.html("아이디 혹은 비밀번호가 일치하지 않습니다.<br>입력한 내용을 다시 확인해주세요.");
    } else if (code == -5) {
        errMsg.css("display", "inline-block");
        errMsg.html("시스템 문제로 로그인 요청을 처리할 수 없습니다.<br>관리자에게 문의하세요.");
    } else if (code == -999) {
        errMsg.css("display", "inline-block");
        errMsg.html("예상치 못한 오류가 발생했습니다.");
    }

    $("#loginBtn").click(function() {
        if ($("#username").val() == "") {
            errMsg.css("display", "inline-block");
            errMsg.html("아이디를 입력해주세요.");
            return false;
        } else if ($("#password").val() == "") {
            errMsg.css("display", "inline-block");
            errMsg.html("비밀번호를 입력해주세요.");
            return false;
        }
        $('form[name="loginform"]').submit();
    });
});
