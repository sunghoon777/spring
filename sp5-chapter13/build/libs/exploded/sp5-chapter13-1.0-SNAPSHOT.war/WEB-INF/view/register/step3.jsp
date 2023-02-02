<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spirng" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><spring:message code="memberRegister"/></title>
</head>
<body>
<p><spring:message code="registerDone" arguments="${registerRequest.name},${registerRequest.email}"/></p>
<a href="/main"><spring:message code="goMain"/></a>
</body>
</html>
