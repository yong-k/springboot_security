let usernameFlag = false;
let pwFlag = false;
let emailFlag = false;

$(document).ready(function(){
    /**
     * 수정폼에서 호출되면 usernameFlag, emailFlag는 true로 세팅
     * 수정폼: /users/update/{id}
     * 등록폼: /users/register
     */
    let hostUrl = document.location.href.split("/");
    let endOfHostUrl = hostUrl.at(-1);
    if (!isNaN(endOfHostUrl)) {
        usernameFlag = true;
        emailFlag = true;
    }

    submitClose();
    defaultInputCheck();

    $("#saveBtn").click(function() {
        submitClose();
        if (usernameFlag && pwFlag && emailFlag) {
            $('form[name="saveForm"]').submit();
        }
    });
});

function defaultInputCheck() {
    $("#username").blur(function() {
        usernameFlag = false;
        checkUsername();
        submitCheck();
    });

    $("#password").blur(function() {
        pwFlag = false;
        checkPassword();
        submitCheck();
    });

    $("#password-check").blur(function() {
        pwFlag = false;
        checkPasswordCheck();
        submitCheck();
    });

    $("#email").blur(function() {
        emailFlag = false;
        checkEmail();
        submitCheck();
    });
}

function checkUsername() {
    let id = $("#id").val();
    let username = $("#username").val().replace(/\s/g, "");

    $("#username").val(username);

    $("#username").removeClass("is-invalid");
    hideErrorMsg($("#usernameErrMsg"));

    if ($("#username").val() == "") {
        $("#username").addClass("is-invalid");
        showErrorMsg($("#usernameErrMsg"), "필수 정보입니다.");
    } else {
        $.ajax({
            method: "GET",
            url: "/checkusername",
            data: {"id" : id, "username" : username}
        })
        .done(function (response) {
            if (response > 0) {
                $("#username").addClass("is-invalid");
                showErrorMsg($("#usernameErrMsg"), "사용할 수 없는 닉네임입니다.");
            } else {
                usernameFlag = true;
                submitCheck();
            }
        })
        .fail(function (e) {
            console.log(e.status);
            console.log(e.responseText);
        });
    }
}

function checkPassword() {
    $("#password").removeClass("is-invalid");
    hideErrorMsg($("#passwordErrMsg"));

    if ($("#password").val() == "") {
        $("#password").addClass("is-invalid");
        showErrorMsg($("#passwordErrMsg"), "필수 정보입니다.");
    } else if ($("#password").val() == $("#password-check").val()) {
        $("#password-check").removeClass("is-invalid");
        hideErrorMsg($("#password-checkErrMsg"));
        pwFlag = true;
    } else if ($("#password-check").val() != ""
                && $("#password").val() != $("#password-check").val()) {
        $("#password").addClass("is-invalid");
        $("#password-check").addClass("is-invalid");
        showErrorMsg($("#password-checkErrMsg"), "비밀번호가 일치하지 않습니다.");
    }
}

function checkPasswordCheck() {
    $("#password-check").removeClass("is-invalid");
    hideErrorMsg($("#password-checkErrMsg"));

    if ($("#password-check").val() == "") {
        $("#password-check").addClass("is-invalid");
        showErrorMsg($("#password-checkErrMsg"), "필수 정보입니다.");
    } else if ($("#password").val() == $("#password-check").val()) {
        $("#password").removeClass("is-invalid");
        $("#password-check").removeClass("is-invalid");
        hideErrorMsg($("#password-checkErrMsg"));
        pwFlag = true;
    } else if ($("#password").val() != $("#password-check").val()) {
        $("#password").addClass("is-invalid");
        $("#password-check").addClass("is-invalid");
        showErrorMsg($("#password-checkErrMsg"), "비밀번호가 일치하지 않습니다.");
    }
}

function checkEmail() {
    let id = $("#id").val();
    let email = $("#email").val().replace(/\s/g, "");

    $("#email").val(email);

    $("#email").removeClass("is-invalid");
    hideErrorMsg($("#emailErrMsg"));

    if ($("#email").val() == "") {
        $("#email").addClass("is-invalid");
        showErrorMsg($("#emailErrMsg"), "필수 정보입니다.");
    } else if (!isEmailFormat(email)) {
        $("#email").addClass("is-invalid");
        showErrorMsg($("#emailErrMsg"), "이메일 형식이 올바르지 않습니다.");
    } else {
        $.ajax({
            method: "GET",
            url: "/checkemail",
            data: {"id" : id, "email" : email}
        })
        .done(function (response) {
            if (response > 0) {
                $("#email").addClass("is-invalid");
                showErrorMsg($("#emailErrMsg"), "사용할 수 없는 이메일입니다.");
            } else {
                emailFlag = true;
                submitCheck();
            }
        })
        .fail(function (e) {
            console.log(e.status);
            console.log(e.responseText);
        });
    }
}

function oninputPhone(phone) {
    phone.value = phone.value
            .replace(/[^0-9]/g, '')
            .replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3");
}

function isEmailFormat(email) {
    let format = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-za-z0-9\-]+/;
    if (format.test(email))
        return true;
    return false;
}

function submitCheck() {
    if (!usernameFlag || !pwFlag || !emailFlag) {
        submitClose();
    } else if (usernameFlag && pwFlag && emailFlag) {
        submitOpen();
    }
}

function submitClose() {
    $("#saveBtn").attr("disabled", true);
}

function submitOpen() {
    $("#saveBtn").attr("disabled", false);
}

function showErrorMsg(obj, msg) {
    obj.html(msg);
    obj.show();
}

function hideErrorMsg(obj) {
    obj.hide();
}