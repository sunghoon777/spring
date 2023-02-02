<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title><spring:message code="changePasswordTitle"/></title>
</head>
<body>
<%--@elvariable id="changePasswordCommand" type="login.ChangePasswordCommand"--%>
<form:form modelAttribute="changePasswordCommand">
    <p>
        <label><spring:message code="currentPassword"/></label>
        <br>
        <form:input path="currentPassword"/>
        <form:errors path="currentPassword" delimiter=","/>
    </p>
    <p>
        <label><spring:message code="newPassword"/></label>
        <br>
        <form:input path="newPassword"/>
        <form:errors path="newPassword" delimiter=","/>
    </p>
    <input type="submit" value="<spring:message code="changeBtn"/>"/>
</form:form>
</body>
</html>
