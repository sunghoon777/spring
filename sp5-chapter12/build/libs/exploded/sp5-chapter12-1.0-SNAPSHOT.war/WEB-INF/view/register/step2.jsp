<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title><spring:message code="memberRegister"/></title>
</head>
<body>
<h1><spring:message code="memberInformation"/></h1>
<%--@elvariable id="registerRequest" type="register.RegisterRequest"--%>
<form:form action="step3" modelAttribute="registerRequest" >
    <p>
      <label><spring:message code="memberEmail"/> : <br>
          <form:input path="email"/>
          <form:errors path="email"/>
      </label>
    </p>
    <p>
        <label><spring:message code="memberName"/>  : <br>
            <form:input path="name"/>
            <form:errors path="name"/>
        </label>
    </p>
    <p>
        <label><spring:message code="memberPassword"/>  : <br>
            <form:input path="password"/>
            <form:errors path="password"/>
        </label>
    </p>
    <p>
        <label><spring:message code="memberPasswordConfirm"/>  : <br>
            <form:input path="confirmPassword"/>
            <form:errors path="confirmPassword"/>
        </label>
    </p>
    <input type="submit" value="<spring:message code="registerButton"/>">
</form:form>
</body>
</html>
