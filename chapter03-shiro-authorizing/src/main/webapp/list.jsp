<%--
  Created by IntelliJ IDEA.
  User: Laifengting
  Date: 2021-06-11
  Time: 8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>List Page</title>
    </head>

    <body>
        <h4>List Page</h4>

        <br/>

        Welcome:

        <shiro:authenticated>认证的：authenticated</shiro:authenticated> <br/>
        <shiro:notAuthenticated>未认证的：notAuthenticated</shiro:notAuthenticated> <br/>

        <shiro:principal/> <br/> <%-- 显示主体 --%>
        <shiro:user>用户：user</shiro:user> <br/> <%-- 所有的用户都能看 --%>
        <shiro:guest>访客：guest</shiro:guest> <br/>

        <shiro:hasPermission name="user">user hasPermission</shiro:hasPermission> <br/> <%-- 只有 user 有权限显示内容 --%>
        <shiro:hasAnyRoles name="user,admin">user,admin hasAnyRoles</shiro:hasAnyRoles> <br/> <%-- 只要是 user,admin 其中一个都显示 --%>
        <shiro:hasRole name="admin">admin hasRole</shiro:hasRole> <br/> <%-- 只有 admin 角色才显示 --%>

        <shiro:lacksPermission name="user">user lacksPermission</shiro:lacksPermission> <br/> <%-- 不是 user 有权限显示内容 --%>
        <shiro:lacksRole name="admin">admin lacksRole</shiro:lacksRole> <br/> <%-- 不是 admin 的角色才显示内容 --%>

        <br/>

        <a href="admin.jsp">Admin Page</a>

        <br/>

        <a href="user.jsp">User Page</a>

        <br/>

        <a href="shiro/testShiroAnnotation">Test Shiro Annotation</a>

        <br/>

        <a href="shiro/logout">退出</a>
    </body>
</html>
