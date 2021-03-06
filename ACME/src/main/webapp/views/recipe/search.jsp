<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 22/11/16
  Time: 11:44
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

    <form:form action="recipe/search.do" modelAttribute="recipe">

    <form:hidden path="id"/>
    <form:hidden path="version"/>

    <form:label path="ticker">
        <spring:message code="recipe.ticker"/>
    </form:label>
    <form:input path="ticker"/>
    <form:errors cssClass="error" path="ticker"/>
    <br/>

        <form:label path="title">
            <spring:message code="recipe.title"/>
        </form:label>
        <form:input path="title"/>
        <form:errors cssClass="error" path="title"/>
        <br/>
        <form:label path="summary">
            <spring:message code="recipe.summary"/>
        </form:label>
        <form:input path="summary"/>
        <form:errors cssClass="error" path="summary"/>
        <br/>



        <spring:url value="http://localhost:8080/actor/userPersonalData/save.do" var="url">

            <spring:param name="name" value="${name}"/>
            <spring:param name="title" value="${title}"/>
            <spring:param name="summary" value="${summary}"/>

        </spring:url>
    <input type="button" name="search"
           value="<spring:message code="recipe.search" />"
           onclick=" relativeRedir('anonymus/findRecipe.do?recipeTicker=${url}');"/>





</form:form>