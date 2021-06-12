<%--
  Created by IntelliJ IDEA.
  User: Laifengting
  Date: 2021-06-11
  Time: 8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login Page</title>
    </head>

    <body>
        <h4>Login Page</h4> <br/>

        <form action="shiro/login" method="post">
            username: <input type="text" name="username">
            <br/>
            password: <input type="text" name="password">
            <input type="submit" value="提交">
        </form>

    </body>
</html>
