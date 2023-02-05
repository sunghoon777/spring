<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title><spring:message code="loginTitle"/></title>
</head>
<body>
<%--@elvariable id="loginCommand" type="login.LoginCommand"--%>
<form:form modelAttribute="loginCommand">
    <form:errors delimiter=","/>
    <p>
        <label><spring:message code="loginEmail"/></label>
        <br>
        <form:input path="email"/>
        <form:errors path="email" delimiter=","/>
    </p>
    <p>
        <label><spring:message code="loginPassword"/></label>
        <br>
        <form:input path="password"/>
        <form:errors path="password" delimiter=","/>
    </p>
    <p>
        <label>
            <spring:message code="rememberEmail"/>
            <form:checkbox path="rememberEmail"/>
        </label>
    </p>
    <input type="submit" value="<spring:message code="loginBtn"/>"/>
</form:form>
</body>
</html>
