<%--
  Created by IntelliJ IDEA.
  User: sulta
  Date: 6/8/2023
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style>
    li {
        list-style: none;
    }
</style>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>">
    <title>Title</title>
</head>
<body>
    <c:forEach items="${integerList}" var="groupNumber">
    <a class="container-text" href="<c:url value="/group/${groupNumber}"/>">
        <li class="container-button" id="group_<c:out value="${groupNumber}"/>">
            <div class="container-text">
                <div>
                    GROUP # ${groupNumber} WORDS
                </div>
            </div>
        </li>
    </a>
    </c:forEach>
</body>
</html>
