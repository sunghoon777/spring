<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>회원 조회</title>
</head>
<body>
<%--@elvariable id="cmd" type="info.ListCommand"--%>
<form:form modelAttribute="cmd">
    <p>
        <label>from: <form:input path="from" /></label>
        <form:errors path="from" />
        ~
        <label>to:<form:input path="to" /></label>
        <form:errors path="to" />
        <input type="submit" value="조회">
    </p>
</form:form>

<c:if test="${! empty members}">
    <table>
        <tr>
            <th>아이디</th><th>이메일</th>
            <th>이름</th><th>가입일</th>
        </tr>
        <c:forEach var="mem" items="${members}">
            <tr>
                <td>${mem.id}</td>
                <td><a href="<c:url value="/members/${mem.id}"/>">${mem.email}</a></td>
                <td>${mem.name}</td>
                <td><tf:formatDateTime value="${mem.registerDateTime }" pattern="yyyy-MM-dd" /></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
