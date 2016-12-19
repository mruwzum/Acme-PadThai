<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 26/11/16
  Time: 11:12
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


<display:table name="ingredient" id="row" requestURI="http://localhost:8080/nutritionist/ingredient/list.do" pagesize="5"
               class="displaytag">

    <spring:message code="ingredient.name" var="name"/>
    <display:column property="name" title="${name}"/>

    <spring:message code="i.description" var="description"/>
    <display:column property="description" title="${description}"/>

    <spring:message code="i.picture" var="picture"/>
    <display:column property="picture" title="${picture}"/>

    <display:column>
        <a href="nutritionist/ingredient/edit.do?ingredientID=${row.id}">
            <spring:message code="recipe.edit"/>
        </a>
    </display:column>

    <display:column>
        <a href="nutritionist/ingredient/delete.do?ingredientID=${row.id}">
            <spring:message code="recipe.delete"/>
        </a>
    </display:column>

</display:table>

<a href="nutritionist/ingredient/newIngredient.do">
    <spring:message code="i.newIngredient"/>
</a>
