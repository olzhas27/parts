<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="<c:url value="/res/css/mystyles.css"/>" rel="stylesheet" type="text/css"/>
<title>PARTS</title>
</head>
<body>

<div class="tableContainer">

    <table class="table table-bordered table-hover">
        <caption>Компьютерные детали</caption>
        <thead>
        <tr>
            <th>Название</th>
            <th>Необходимость</th>
            <th>Количество</th>
            <th>Действие</th>
        </tr>
        </thead>
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
                    <a href="${edit}/${part.id}">
                        <img src="<c:url value="/res/images/edit.svg"/>" width="30" height="30" title="Изменить деталь">
                    </a>

                    <c:url value="/delete" var="delete"/>
                    <a href="${delete}/${part.id}">
                        <img src="<c:url value="/res/images/delete.svg"/>" width="30" height="30" title="Удалить деталь">
                    </a>
                </td>
            </tr>
        </c:forEach>
        <tr>
                <td>Можно собрать</td>
                <td>${machinesCount}</td>
                <td>компьютеров</td>
                <td>
                    <c:url value="/add" var="add"/>
                    <a href="${add}">
                        <img src="<c:url value="/res/images/add.svg"/>" width="30" height="30" title="Добавить деталь">
                    </a>
                </td>
        </tr>

        <tr>
            <td colspan="4" style="text-align: center;">

                <c:forEach begin="${1}" end="${pagesCount}" step="1" varStatus="i">
                                <c:url value="/" var="url">
                                    <c:param name="page" value="${i.index}"/>
                                </c:url>
                                <a href="${url}">${i.index}</a>
                </c:forEach>
            </td>
        </tr>
    </table>
</div>
</body>
</html>