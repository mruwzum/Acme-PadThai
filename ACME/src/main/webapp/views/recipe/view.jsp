<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 18/12/16
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 17/12/16
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<html>
<head>
    <spring:message code="recipe.view.titulazo" var="titulazo"/>
    <title>${titulazo}</title>
</head>
<body>

<spring:message code="recipe.view.title" var="titleTitle"/>
<h1><jstl:out value="${titleTitle}"/></h1>
<jstl:out value="${title}"/>
<spring:message code="recipe.view.summary" var="summaryTitle"/>
<h1><jstl:out value="${summaryTitle}"/></h1>
<jstl:out value="${summary}"/>
<spring:message code="recipe.view.creationDate" var="creationDateTitle"/>
<h1><jstl:out value="${creationDateTitle}"/></h1>
<jstl:out value="${creationDate}"/>
<spring:message code="recipe.view.updateDate" var="updateDateTitle"/>
<h1><jstl:out value="${updateDateTitle}"/></h1>
<jstl:out value="${updateDate}"/>
<spring:message code="recipe.view.categorie" var="categorieTitle"/>
<h1><jstl:out value="${categorieTitle}"/></h1>
<jstl:out value="${categorie}"/>
<spring:message code="recipe.view.user" var="userTitle"/>
<h1><jstl:out value="${userTitle}"/></h1>
<jstl:out value="${user}"/>
<spring:message code="recipe.view.rate.likes" var="rateTitle"/>
<h1><jstl:out value="${rateTitle}"/></h1>
<jstl:out value="${likes}"/>
<spring:message code="recipe.view.rate.dislikes" var="rateTitle"/>
<h1><jstl:out value="${rateTitle}"/></h1>
<jstl:out value="${dislikes}"/>
<br/>
<security:authorize access="isAuthenticated()">

<jstl:if test="${liked}">
<input type="button" name="like"
       value="<spring:message code="recipe.rate.like.button" />"
       onclick="relativeRedir('actor/recipe/like.do?recipeID=${id}');"/>
</jstl:if>
<jstl:if test="${notLiked}">
<input type="button" name="dislike"
       value="<spring:message code="recipe.rate.dislike.button" />"
       onclick="relativeRedir('actor/recipe/dislike.do?recipeID=${id}');"/>
</jstl:if>
</security:authorize>
<br>
<br/>
<display:table name="comments" id="row" requestURI="http://localhost:8080/user/recipes/view.do" pagesize="10"
               class="displaytag">

    <%--Parámetros--%>
    <spring:message code="comment.tittle" var="titleheader"/>
    <display:column property="title" title="${titleheader}" sortable="true"/>

    <spring:message code="comment.text" var="text"/>
    <display:column property="text" title="${text}"/>

    <spring:message code="comment.number" var="headnumberOfStars"/>
    <display:column property="numberOfStars" title="${headnumberOfStars}" sortable="numberOfStars"/> of 5

</display:table>

<spring:message code="recipe.view.write.comment" var="rateTitle"/>
<h1><jstl:out value="${rateTitle}"/></h1>
<security:authorize access="isAuthenticated()">


    <%--TODO no se como meterle a esra url la receta que se está mostrando--%>
    <form:form action="actor/comment/write.do" modelAttribute="comment">
        <form:hidden path="id"/>
        <form:hidden path="version"/>

        <form:label path="title">
            <spring:message code="comment.tittle"/>
        </form:label>
        <form:input path="title"/>
        <form:errors cssClass="error" path="title"/>
        <br/>

        <form:label path="text">
            <spring:message code="comment.text"/>
        </form:label>
        <form:input path="text"/>
        <form:errors cssClass="error" path="text"/>
        <br/>

        <form:label path="numberOfStars">
            <spring:message code="comment.numberOfStars"/>
        </form:label>
        <form:input path="numberOfStars"/>
        <form:errors cssClass="error" path="numberOfStars"/>


        <input type="submit" name="save"
               value="<spring:message code="comment.send" />"/>&nbsp



    </form:form>

</security:authorize>

</body>
</html>
