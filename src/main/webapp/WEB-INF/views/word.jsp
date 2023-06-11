<%--
  Created by IntelliJ IDEA.
  User: sulta
  Date: 6/10/2023
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>">
    <title>Add New Word</title>
</head>
<body>
    <div class="margin-container">
        <div class="flashcard-header">
            <a class="go-to-group-list" href="${pageContext.request.contextPath}/groups/"> ← Go To Group List</a>
        </div>
        <div class="form-group">
            <div class="add-new-word-header">
                ADDING A NEW WORD
            </div>
            <sf:form method="post" acceptCharset="UTF-8" cssClass="form-group" modelAttribute="wordRequestDTO">
                <div class="form-tile">
                    <label for="word" style="font-size: 15px"><strong>Word:</strong> </label>
                    <div>
                        <sf:textarea type="text" cssClass="form-word-input" path="word" placeholder="Enter new word"></sf:textarea>
                    </div>
                </div>
                <c:forEach items="${wordRequestDTO.meanings}" varStatus="status" var="meaning">
                    <c:set var="index" value="${status.index}" />
                    <div class="form-tile">
                        <label for="meaning[${index}].meaning">Meaning: </label>
                        <div>
                            <sf:textarea type="text" path="meanings[${index}].meaning" cssClass="form-input" placeholder="Enter word meaning" name="meaning${status.index}"></sf:textarea>
                        </div>
                    </div>
                    <div class="form-tile">
                        <label for="meaning[${status.index}].example">Example:</label>
                        <div>
                            <sf:textarea type="text" path="meanings[${index}].example" cssClass="form-input-example"
                                         placeholder="Enter example sentence or phrase of the word usage"></sf:textarea>
                        </div>
                    </div>
                    <div class="form-tile">
                        <label for="meaning[${index}].category">Category:</label>
                        <div>
                            <sf:select path="meanings[${index}].category">
                                <sf:option value="" label="Select an option" />
                                <sf:option value="adjective" label="adjective" />
                                <sf:option value="verb" label="verb" />
                                <sf:option value="noun" label="noun" />
                                <sf:option value="adverb" label="adverb" />
                            </sf:select>

                        </div>
                    </div>
                    <div class="form-tile">
                        <label for="meaning[${index}].translation">Translation:</label>
                        <div>
                            <sf:textarea type="text" path="meanings[${index}].translation" cssClass="form-input" name="translation${status.index}" placeholder="Enter translation"></sf:textarea>
                        </div>
                    </div>
                </c:forEach>

                <div class="form-submit-button-container">
                    <input class="form-submit-button" type="submit" value="SUBMIT">
                </div>
            </sf:form>
        </div>
    </div>
</body>
</html>
