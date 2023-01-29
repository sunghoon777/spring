<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>테스트</title>
</head>
<body>
<p>request 에서 데이터 존재 ? : ${data}</p>

<%--@elvariable id="coffee" type="Coffee"--%>
<form:form method="post" modelAttribute="coffee">
    <label>커피 이름 : <form:input path="name"/></label>
    <br>
    <label>커피 가격 : <form:input path="cost"/></label>
    <br>
    <label>커피 제조사 : <form:input path="manufacturer"/></label>
    <br>
    하루에 마시는 커피 횟수 :
    <form:select path="drinkCount" items="${drinkCountList}">
    </form:select>
    <br>
    <form:checkboxes path="competitors" items="${competitors}"/>
    <br>
    <input type="submit" value="제출하기">
</form:form>
</body>
</html>
