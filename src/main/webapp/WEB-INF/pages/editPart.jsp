<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty part.name}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty part.name}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<c:if test="${empty part.name}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty part.name}">
    <c:url value="/edit" var="var"/>
</c:if>

<form action="${var}" method="POST">
    <c:if test="${!empty part.name}">
            <input type="hidden" name="id" value="${part.id}">
    </c:if>

    <label for="name">Название</label>
    <input type="text" name="name" id="name" value="${part.name}">

    <label for="need">Необходимость</label>
    <input type="checkbox" name="need" id="need" checked="${part.need}">

    <label for="num">Количество</label>
    <input type="text" name="num" id="num" value="${part.num}">

    <c:if test="${empty part.name}">
        <input type="submit" value="Добавить деталь">
    </c:if>
    <c:if test="${!empty part.name}">
        <input type="submit" value="Изменить деталь">
    </c:if>

</form>
</body>
</html>