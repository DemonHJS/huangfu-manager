<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html  lang="zh">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

  <title>${applicationScope.systemConfig.system.title}</title>
  <meta name="description" content="${applicationScope.systemConfig.system.title}">
  <link href="${requestScope.contextPath}/resources/expand/bootstrap/css/bootstrap.min.css"  rel="stylesheet"/>
  <link href="${requestScope.contextPath}/resources/expand/bootstrap/css/font-awesome.min.css" rel="stylesheet"/>

  <link href="${requestScope.contextPath}/resources/system/css/style.css"  rel="stylesheet"/>
  <link href="${requestScope.contextPath}/resources/system/css/login.css" rel="stylesheet"/>
  <link href="${requestScope.contextPath}/resources/system/css/huangfu-ui.css"  rel="stylesheet"/>
  <!--[if lt IE 9]>
  <meta http-equiv="refresh" content="0;ie.html" />
  <![endif]-->
  <link rel="shortcut icon" href=".${requestScope.contextPath}/resources/system/favicon.ico" />

  <style type="text/css">label.error { position:inherit;  }</style>
  <!-- 判断登录页面是否是顶层打开，不是设置为顶层 -->
  <script>
    if(window.top!==window.self){window.top.location=window.location};

  </script>
</head>

<body class="signin">

<div class="signinpanel">
  <div class="row">
    <div class="col-sm-7">
      <div class="signin-info">
        <div class="logopanel m-b">
          <h1><img alt="[ 皇甫家胜 ]" src="${requestScope.contextPath}/resources/system/img/huangfu.png" ></h1>
        </div>
        <div class="m-b"></div>
        <h4>欢迎使用 <strong>${applicationScope.systemConfig.system.title}</strong></h4>
        <ul class="m-b">
          <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> SpringBoot</li>
          <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> Mybatis</li>
          <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> Shiro</li>
          <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> Thymeleaf</li>
          <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> Bootstrap</li>
        </ul>
        <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
      </div>
    </div>
    <div class="col-sm-5">
      <form id="signupForm">
        <h4 class="no-margins">登录：</h4>
        <p class="m-t-md">你若不离不弃，我必生死相依</p>
        <input type="text"     name="userName" class="form-control uname" placeholder="用户名" value=""    />
        <input type="password" name="password" class="form-control pword" placeholder="密码"   value="" />
        <c:if test="${applicationScope.systemConfig.system.captchaEnabled}">
          <div class="row m-t">
            <div class="col-xs-6">
              <input type="text" name="validateCode" class="form-control code" placeholder="验证码" maxlength="5" autocomplete="off">
            </div>
            <div class="col-xs-6">
              <a href="javascript:void(0);" title="点击更换验证码">
                <img src="${requestScope.contextPath}/login/LoginCode" class="imgcode" width="85%"/>
              </a>
            </div>
          </div>
        </c:if>
        <%--<div class="checkbox-custom" th:classappend="${applicationScope.systemConfig.system.captchaType==false} ? 'm-t'">
            <input type="checkbox" id="rememberme" name="rememberme"> <label for="rememberme">记住我</label>
        </div>--%>
        <button class="btn btn-success btn-block" id="btnSubmit"  data-loading="正在验证登录，请稍后...">登录</button>
      </form>
    </div>
  </div>
  <div class="signup-footer">
    <div class="pull-left">
      &copy; 2019 All Rights Reserved. HuangFu <br>
    </div>
  </div>
</div>
<script >
  var baseUrl = "${requestScope.contextPath}";
</script>
<!-- 全局js -->
<script src="${requestScope.contextPath}/resources/expand/jquery/jquery-3.2.1.min.js" ></script>
<script src="${requestScope.contextPath}/resources/expand/bootstrap/js/bootstrap.min.js" ></script>
<script src="${requestScope.contextPath}/resources/expand/layer/layer.min.js"></script>

<!-- 验证插件 -->
<script src="${requestScope.contextPath}/resources/expand/jquery/jquery.validate.min.js"></script>
<script src="${requestScope.contextPath}/resources/system/js/huangfu-extend.js"></script>
<script src="${requestScope.contextPath}/resources/system/js/huangfu-fn-extend.js"></script>

<script src="${requestScope.contextPath}/resources/system/js/login.js"></script>

<%--<script src="../static/ajax/libs/validate/jquery.validate.min.js" th:src="@{/ajax/libs/validate/jquery.validate.min.js}"></script>
<script src="../static/ajax/libs/validate/messages_zh.min.js" th:src="@{/ajax/libs/validate/messages_zh.min.js}"></script>
<script src="../static/ajax/libs/layer/layer.min.js" th:src="@{/ajax/libs/layer/layer.min.js}"></script>
<script src="../static/ajax/libs/blockUI/jquery.blockUI.js" th:src="@{/ajax/libs/blockUI/jquery.blockUI.js}"></script>
<script src="../static/ruoyi/js/ry-ui.js" th:src="@{/ruoyi/js/ry-ui.js?v=4.1.0}"></script>
<script src="../static/ruoyi/login.js" th:src="@{/ruoyi/login.js}"></script>--%>
</body>
</html>
