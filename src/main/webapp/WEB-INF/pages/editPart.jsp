<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<c:url value="/edit" var="var"/>
<form action="${var}" method="POST">
    <input type="hidden" name="id" value="${part.id}">
    <label for="name">Название</label>
    <input type="text" name="name" id="name">

    <label for="need">Необходимость</label>
    <input type="text" name="need" id="need">

    <label for="num">Количество</label>
    <input type="text" name="num" id="num">

    <input type="submit" value="Изменить деталь">
</form>
</body>
</html>