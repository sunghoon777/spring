<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-01-25
  Time: 오후 8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>응답내용</title>
</head>
<body>
<p>응답 내용 : </p>
<ul>
    <c:forEach var="responsesList" items="${answeredData.responses}" varStatus="status">
        <li>${status.count}번 문항 : ${responsesList}</li>
    </c:forEach>
</ul>
<p>응답자 위치 : ${answeredData.res.location}</p>
<p>응답자 나이 : ${answeredData.res.age}</p>
</body>
</html>
