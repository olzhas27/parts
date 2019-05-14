<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>PARTS</title>
</head>
<body>

<h2>Компьютерные детали</h2>
<table>
    <tr>
        <th>Название</th>
        <th>Необходимость</th>
        <th>Количество</th>
        <th>action</th>
    </tr>
    <c:forEach var="part" items="${partsList}">
        <tr>
            <td>${part.name}</td>
            <td>${part.need}</td>
            <td>${part.num}</td>
            <td>
                <a href="/edit/${part.id}">edit</a>
                <a href="/delete/${part.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Add</h2>
<c:url value="/add" var="add"/>
<a href="${add}">Add new part</a>
</body>
</html>