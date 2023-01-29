<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>설문조시</title>
</head>
<body>
<h2>설문조사</h2>
<form action="/survey" method="post">
    <c:forEach var="q" items="${questions}" varStatus="status">
        <p>${status.count}. ${q.title}</p>
        <c:choose>
            <c:when test="${!(empty q.options)}">
                <c:forEach var="option" items="${q.options} ">
                    <label><input type="radio" name="responses[${status.index}]" value="${option}">${option}</label>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <input type="text" name="responses[${status.index}]">
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <p>
        <label>응답자 위치 : <br>
            <input type="text" name="res.location">
        </label>
    </p>
    <p>
        <label>응답자 나이 : <br>
            <input type="text" name="res.age">
        </label>
    </p>
    <input type="submit" value="전송">
</form>
</body>
</html>
