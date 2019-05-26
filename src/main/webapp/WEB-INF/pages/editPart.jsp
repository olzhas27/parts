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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="<c:url value="/res/css/mystyles.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>

<c:if test="${empty part.name}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty part.name}">
    <c:url value="/edit" var="var"/>
</c:if>

<div class="formContainer">

<form action="${var}" method="POST" class="form_price">
    <c:if test="${!empty part.name}">
            <input type="hidden" name="id" value="${part.id}">
    </c:if>

    <div>
    <label for="name">Название</label>
    <input type="text" name="name" id="name" value="${part.name}">
    </div>

    <div>
    <label for="need">Необходимость</label>
    <input type="checkbox" name="need" id="need" checked="${part.need}">
    </div>

    <div>
    <label for="num">Количество</label>
    <input type="number" name="num" id="num" value="${part.num}">
    </div>

    <div>
    <c:if test="${empty part.name}">
        <input type="submit" value="Добавить деталь">
    </c:if>
    <c:if test="${!empty part.name}">
        <input type="submit" value="Изменить деталь">
    </c:if>
    </div>

</form>

</div>

</body>
</html>