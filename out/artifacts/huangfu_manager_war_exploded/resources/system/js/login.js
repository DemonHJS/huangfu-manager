
$(function() {
    validateRule();
	$('.imgcode').click(function() {
		var url = baseUrl + "/login/LoginCode";
		$(".imgcode").attr("src", $.hf_randomUrl(url));
	});

});

$.validator.setDefaults({
    submitHandler: function() {
        login();
    }
});

function login(){
	var userName = $("input[name='userName']").val();
    var password = $("input[name='password']").val();
    var validateCode = $("input[name='validateCode']").val();
    var rememberMe = $("input[name='rememberme']").is(':checked');

    if($.hf_isEmpty(validateCode)){
        layer.msg("请输入验证码！");
    }else {
        $.hf_ajaxData_post(baseUrl + "/login/login", {
            "userName": userName,
            "password": password,
            "validateCode": validateCode,
            "rememberMe": rememberMe
        }, false, "json", function (res) {
            location.href = baseUrl + '/index.jsp';
        },function (res) {
            $('.imgcode').click();
            $(".code").val("");
        });
    }

}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            userName: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            userName: {
                required: icon + "请输入您的用户名",
            },
            password: {
                required: icon + "请输入您的密码",
            }
        }
    })
}
