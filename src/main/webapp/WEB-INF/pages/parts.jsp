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

    <form action="search" method="GET">
        <input name="name" type="text" value="${partName}">
        <input type="submit" value="Найти">
    </form>


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
    </table>
</div>
<div style="text-align: center;">
    <nav aria-label="All parts nagivation" style="display: inline-block; list-style-type: none;">
      <ul class="pagination">

      <c:choose>
        <c:when test="${empty partName}">
            <c:url value="/" var="url">
                <c:param name="page" value="${pageNum - 1}"/>
            </c:url>
        </c:when>
        <c:when test="${!empty partName}">
            <c:url value="/search" var="url">
                <c:param name="page" value="${pageNum - 1}"/>
                <c:param name="name" value="${partName}"/>
            </c:url>
        </c:when>
      </c:choose>

        <li class="page-item <c:if test="${pageNum == 1}">disabled</c:if>">
          <a class="page-link" href="${url}" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>

        <c:forEach begin="${1}" end="${pagesCount}" step="1" varStatus="i">

        <c:choose>
            <c:when test="${empty partName}">
                <c:url value="/" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
            </c:when>
            <c:when test="${!empty partName}">
                <c:url value="/search" var="url">
                    <c:param name="page" value="${i.index}"/>
                    <c:param name="name" value="${partName}"/>
                </c:url>
            </c:when>
        </c:choose>

            <li class="page-item <c:if test="${pageNum == i.index}">active</c:if>" <c:if test="${pageNum == i.index}">aria-current="page"</c:if>>
                <a class="page-link" href="${url}">
                    ${i.index}
                    <c:if test="${pageNum == i.index}"><span class="sr-only">(current)</span></c:if>
                </a>
            </li>
        </c:forEach>


        <c:choose>
            <c:when test="${empty partName}">
                <c:url value="/" var="url">
                    <c:param name="page" value="${pageNum + 1}"/>
                </c:url>
            </c:when>
            <c:when test="${!empty partName}">
                <c:url value="/search" var="url">
                    <c:param name="page" value="${pageNum + 1}"/>
                    <c:param name="name" value="${partName}"/>
                </c:url>
            </c:when>
        </c:choose>

        <li class="page-item <c:if test="${pageNum == pagesCount}">disabled</c:if>">
          <a class="page-link" href="${url}" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
</div>
</body>
</html>