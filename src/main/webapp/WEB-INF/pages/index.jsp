<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/21
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
    <%--<script type="text/javascript"
            src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#post").click(function(){
                $.post("${pageContext.request.contextPath}/p",
                    {},
                    function(result){
                        alert(result);
                    });
            });
        });
    </script>--%>
</head>
<body>
<shiro:guest>
    欢迎游客访问，<a href="${pageContext.request.contextPath}/login">点击登录</a><br/>
</shiro:guest>
<shiro:user>
    欢迎[<shiro:principal/>]登录，<a href="${pageContext.request.contextPath}/logout">点击退出</a><br/>
    <a href="${pageContext.request.contextPath}/admin">检查role含admin</a><br/>
    <a href="${pageContext.request.contextPath}/p">试试查找权限</a><br/>
    <%--<button id="post">试试新增权限</button><br/> 用JQuery太丑了--%>
    <form action="${pageContext.request.contextPath}/p" method="post">
        <input type="submit" value="试试新增权限"/>
    </form>
</shiro:user>
</body>
</html>
