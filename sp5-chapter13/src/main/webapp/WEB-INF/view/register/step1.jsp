<%@ page import="org.springframework.context.MessageSource" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="memberRegister" message=""/></title>
</head>
<body>
<h2><spring:message code="term" /></h2>
<p><spring:message code="termContext"/></p>
<form action="step2" method="post">
    <label>
        <input type="radio" name="agree" value="true">
        <spring:message code="termAgree"/>
    </label>
    <input type="submit" value="<spring:message code="nextButton"/>"/>
</form>
</body>
</html>
