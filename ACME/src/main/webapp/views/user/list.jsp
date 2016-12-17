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


<display:table name="user" id="row" requestURI="http://localhost:8080/anonymus/users.do" pagesize="10"
               class="displaytag">


    <spring:message code="user.name" var="nameheader"/>
    <display:column property="name" title="${nameheader}" sortable="true"/>

    <spring:message code="user.surname" var="surnameheader"/>
    <display:column property="surname" title="${surnameheader}"/>

    <spring:message code="user.EmailAddress" var="Emailheader"/>
    <display:column property="emailAddress" title="${Emailheader}"/>

    <spring:message code="user.phone" var="phoneheader"/>
    <display:column property="phone" title="${phoneheader}"/>
    <spring:message code="user.recipes" var="recipeHeader"/>
    <display:column title="${recipeHeader}">
        <a href="anonymus/userRecipes.do?userID=${row.id}">
            <spring:message code="recipe.list"/>
        </a>
    </display:column>

</display:table>
