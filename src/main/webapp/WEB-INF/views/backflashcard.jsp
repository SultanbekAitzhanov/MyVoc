<%--
  Created by IntelliJ IDEA.
  User: sulta
  Date: 6/10/2023
  Time: 1:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>">
    <title><c:out value="${answerResponseDTO.word}"/></title>
</head>
<body>
    <div class="margin-container">
        <div class="row">
            <div class="column-offset">
                <div class="flashcard-container">
                    <div class="flashcard">
                        <div class="label-flashcard">
                            <c:out value="${answerResponseDTO.state}"/>
                        </div>
                        <h3 class="flashcard-word-text"><c:out value="${answerResponseDTO.word}"/></h3>
                        <c:forEach items="${answerResponseDTO.meanings}" var="wordmeaning">
                            <div class="meaning-container">
                                <div>
                                    <p class="word-category-text">
                                        <strong>
                                            <c:out value="${wordmeaning.category}"/>
                                        </strong> : <c:out value="${wordmeaning.meaning}"/>
                                    </p>
                                </div>
                                <em>
                                    <p>
                                        <c:out value="${wordmeaning.example}"/>
                                    </p>
                                </em>
                            </div>
                        </c:forEach>
                        <a class="card-footer-success" href="/group/${answerResponseDTO.code}/backflashcard/${answerResponseDTO.id}/answer?known=true" data-method="post">
                            I knew this word
                        </a>
                        <a class="card-footer-danger" href="/group/${answerResponseDTO.code}/backflashcard/${answerResponseDTO.id}/answer?known=false" data-method="post">
                            I didn't know this word
                        </a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</body>
</html>
