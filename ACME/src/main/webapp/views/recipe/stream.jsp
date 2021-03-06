<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 18/12/16
  Time: 19:22
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

<display:table name="recipe" id="row" requestURI="http://localhost:8080/user/recipes/steam.do" pagesize="10"
               class="displaytag">

    <%--Parámetros--%>
    <spring:message code="recipe.tittle" var="titleheader"/>
    <display:column property="title" title="${titleheader}" sortable="true"/>

    <spring:message code="recipe.summary" var="summ"/>
    <display:column property="summary" title="${summ}"/>

    <spring:message code="recipe.creationDate" var="creatDat"/>
    <display:column property="creationDate" title="${creatDat}" sortable="true"/>

    <spring:message code="recipe.updateDate" var="updatDat"/>
    <display:column property="updateDate" title="${updatDat}" sortable="true"/>

    <spring:message code="recipe.categorie" var="categorie"/>
    <display:column property="categorie" title="${categorie}" sortable="true"/>

    <%--Autor--%>
    <display:column>
        <a href="anonymus/author.do?recipeID=${row.id}">
            <spring:message code="recipe.users"/>
        </a>
    </display:column>
    <display:column>
        <a href="user/recipe/view.do?recipeID=${row.id}">
            <spring:message code="recipe.view"/>
        </a>
    </display:column>

</display:table>