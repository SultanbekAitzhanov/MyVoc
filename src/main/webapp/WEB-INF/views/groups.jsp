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
    <div class="margin-container">
        <div class="row">
            <c:forEach items="${groupDTOList}" var="groupNumber">
                <div class="group-column">
                    <div class="word-group-container">
                        <a class="go-to-group" href="<c:url value="/group/${groupNumber.code}"/>">
                            <div class="word-group-text">
                                <div>
                                    GROUP # ${groupNumber.code} WORDS
                                </div>
                            </div>
                            <div class="word-group-progress-text">
                                <c:out value="${groupNumber.mastered}"/> out of <c:out value="${groupNumber.total}"/> mastered
                            </div>
                            <div class="progress-bar">
                                <c:set var="total" value="${groupNumber.total}" />
                                <c:set var="mastered_success" value="${groupNumber.mastered}"/>
                                <c:set var="mastered_progress" value="${(mastered_success/total)*100}"/>
                                <div class="progress" style="width: ${mastered_progress}%"></div>
                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
