let pwFlag = false;
let emailFlag = true;
let changePwFlag = true;

$(document).ready(function(){
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    defaultInputCheck();

    $("#saveBtn").click(function() {
        if ($("#nowPassword").val() == "" && $("#password").val() == "" && $("#password-check").val() == "") {
            pwFlag = true;
            changePwFlag = true;
        }

        if (changePwFlag && !pwFlag) {
            alert("비밀번호 변경 시, 현재 비밀번호를 입력해주세요.");
            return false;
        }

        if (pwFlag && changePwFlag && emailFlag) {
            $('form[name="saveForm"]').submit();
        }
        return false;
    });
});

function defaultInputCheck() {
    $("#nowPassword").blur(function() {
        if ($("#nowPassword").val() != "") {
            pwFlag = false;
            checkNowPassword();
        } else {
            $("#nowPassword").removeClass("is-invalid");
            hideErrorMsg($("#nowPasswordErrMsg"));
        }
    });

    $("#password").blur(function() {
        changePwFlag = false;
        checkPassword();
    });

    $("#password-check").blur(function() {
        changePwFlag = false;
        checkPasswordCheck();
    });

    $("#email").blur(function() {
        emailFlag = false;
        checkEmail();
    });
}

function checkNowPassword() {
    let nowPassword = $("#nowPassword").val();

    $("#nowPassword").removeClass("is-invalid");
    hideErrorMsg($("#nowPasswordErrMsg"));

    $.ajax({
        method: "POST",
        url: "/checknowpw",
        data: {"nowPassword" : nowPassword}
    })
        .done(function (response) {
            if (response === false) {
                $("#nowPassword").addClass("is-invalid");
                $("#nowPasswordErrMsg").show();
            } else {
                pwFlag = true;
            }
        })
        .fail(function (e) {
            console.log(e.status);
            console.log(e.responseText);
        });
}

function checkPassword() {
    if ($("#password").val() == $("#password-check").val()) {
        $("#password").removeClass("is-invalid");
        $("#password-check").removeClass("is-invalid");
        hideErrorMsg($("#password-checkErrMsg"));
        changePwFlag = true;
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

    if ($("#password").val() == $("#password-check").val()) {
        $("#password").removeClass("is-invalid");
        $("#password-check").removeClass("is-invalid");
        hideErrorMsg($("#password-checkErrMsg"));
        changePwFlag = true;
    } else if ($("#password").val() != $("#password-check").val()) {
        $("#password").addClass("is-invalid");
        $("#password-check").addClass("is-invalid");
        showErrorMsg($("#password-checkErrMsg"), "비밀번호가 일치하지 않습니다.");
    }
}

function checkEmail() {
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
            data: {"email" : email}
        })
            .done(function (response) {
                if (response > 0) {
                    $("#email").addClass("is-invalid");
                    showErrorMsg($("#emailErrMsg"), "사용할 수 없는 이메일입니다.");
                } else {
                    emailFlag = true;
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

function showErrorMsg(obj, msg) {
    obj.html(msg);
    obj.show();
}

function hideErrorMsg(obj) {
    obj.hide();
}