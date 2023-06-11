<%--
  Created by IntelliJ IDEA.
  User: sulta
  Date: 6/8/2023
  Time: 8:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>">
    <title><c:out value="${wordResponseDTO.word}"/></title>
</head>
<body>
    <div class="margin-container">
        <div class="row">
            <div class="add-new-word-container">
                <div class="add-new-word-button">
                    <a href="${pageContext.request.contextPath}/word">+ Add New Word</a>
                </div>
            </div>
            <div class="flashcard-header">
                <a class="go-to-group-list" href="${pageContext.request.contextPath}/groups/"> ← Go To Group List</a>
            </div>
            <div class="column-offset">
                <div class="flashcard-container">
                    <a class="flashcard-link" href="/backflashcard/<c:out value="${wordResponseDTO.id}"/>">
                        <div class="flashcard">
                            <div class="label-flashcard">
                                <c:out value="${wordResponseDTO.state}"/>
                            </div>
                            <h1 class="word-text"><c:out value="${wordResponseDTO.word}"/></h1>
                        </div>
                    </a>
                </div>
                <div class="text-white">
                    You have mastered <c:out value="${wordResponseDTO.statistics.masteredCount}"/> out of <c:out value="${wordResponseDTO.statistics.total}"/>
                </div>
                <div class="progress-bar">
                    <%-- Get the total and success values from the model --%>
                    <c:set var="index" value="${wordResponseDTO.statistics.total}" />
                    <c:set var="mastered_success" value="${wordResponseDTO.statistics.masteredCount}" />

                    <%-- Calculate the progress percentage --%>
                    <c:set var="mastered_progress" value="${(mastered_success / index) * 100}" />
                    <div class="progress" style="width: ${mastered_progress}%"></div>
                </div>
                <div class="text-white">
                    You are learning <c:out value="${wordResponseDTO.statistics.learningCount}"/> out of <c:out value="${wordResponseDTO.statistics.total}"/>
                </div>
                <div class="progress-bar">
                    <c:set var="learning_success" value="${wordResponseDTO.statistics.learningCount}" />

                    <%-- Calculate the progress percentage --%>
                    <c:set var="learning_progress" value="${(learning_success / index) * 100}" />
                    <div class="progress" style="width: ${learning_progress}%"></div>
                </div>
                <div class="text-white">
                    You are reviewing <c:out value="${wordResponseDTO.statistics.reviewingCount}"/> out of <c:out value="${wordResponseDTO.statistics.total}"/>
                </div>
                <div class="progress-bar">
                    <c:set var="reviewing_success" value="${wordResponseDTO.statistics.reviewingCount}" />

                    <%-- Calculate the progress percentage --%>
                    <c:set var="reviewing_progress" value="${(reviewing_success / index) * 100}" />
                    <div class="progress" style="width: ${reviewing_progress}%"></div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
