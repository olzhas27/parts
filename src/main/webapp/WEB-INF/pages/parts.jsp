<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
<title>PARTS</title>
</head>
<body>

<table class="parts">
    <caption>Компьютерные детали</caption>
    <tr>
        <th>Название</th>
        <th>Необходимость</th>
        <th>Количество</th>
        <th>Действие</th>
    </tr>
    <c:forEach var="part" items="${partsList}">
        <tr>
            <td>${part.name}</td>

            <c:if test="${part.need}">
                    <td>Да</td>
            </c:if>

            <c:if test="${!part.need}">
                    <td>Нет</td>
            </c:if>

            <td>${part.num}</td>

            <td>
                <c:url value="/edit" var="edit"/>
                <a href="${edit}/${part.id}">Редактировать</a>
                <c:url value="/delete" var="delete"/>
                <a href="${delete}/${part.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
            <td>Можно собрать</td>
            <td>${machinesCount}</td>
            <td>компьютеров</td>
            <td></td>
    </tr>

    <tr>
        <td colspan="7">
            <c:url value="/add" var="add"/>
            <a href="${add}">Добавить новую деталь</a>

            <c:forEach begin="${1}" end="${pagesCount}" step="1" varStatus="i">
                            <c:url value="/" var="url">
                                <c:param name="page" value="${i.index}"/>
                            </c:url>
                            <a href="${url}">${i.index}</a>
            </c:forEach>
        </td>
    </tr>
</table>
</body>
</html>