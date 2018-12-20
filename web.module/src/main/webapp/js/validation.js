$(document).ready(function () {
    $(".email").on('change', function (e) {
        showError(".email", checkEmail(), ".email-error");
    });
    $(".password").on('change', function (e) {
        showError(".password", checkPassword(), ".password-error");
        showError(".password-conf", checkPasswordConf(), ".password-conf-error");
    });
    $(".password-conf").on('change', function (e) {
        showError(".password-conf", checkPasswordConf(), ".password-conf-error");
    });
    $(".submit").on("click", function (e) {
        var emailTest = checkEmail();
        var passwordTest = checkPassword();
        var passwordConfTest = checkPasswordConf();
        if(!emailTest || !passwordTest || !passwordConfTest) {
            e.preventDefault();
            showError(".email", emailTest, ".email-error");
            showError(".password", passwordTest, ".password-error");
            showError(".password-conf", passwordConfTest, ".password-conf-error");
            $(".submit").prop('disabled', true);
        }
    })
});
function showError(element, result, selector) {
    $(".submit").prop('disabled', !result);
    if (!result) {
        $(selector).removeClass("hidden");
        $(element).closest("div.form-group").addClass("has-error");
    } else {
        $(selector).addClass("hidden");
        $(element).closest("div.form-group").removeClass("has-error");
    }
}
function checkPasswordConf() {
    var conf = $(".password-conf").val();
    var password = $(".password").val();
    return conf === password;
}
function checkEmail() {
    var value = $(".email").val();
    var emailRegexp = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+\.([\.]*[a-zA-Z0-9-]+)+$/;
    return emailRegexp.test(value);
}
function checkPassword() {
    var password = $(".password").val();
    var passwordRegexp = /(?=.*[a-zA-Z])(?=.*[0-9])(?=.{6,})/;
    return passwordRegexp.test(password);
}