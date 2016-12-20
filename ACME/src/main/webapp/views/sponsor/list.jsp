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


<display:table name="sponsor" id="row" requestURI="http://localhost:8080/admin/sponsor/list.do" pagesize="5"
               class="displaytag">

    <spring:message code="sponsor.name" var="name"/>
    <display:column property="name" title="${name}"/>

    <spring:message code="sponsor.surname" var="surname"/>
    <display:column property="surname" title="${surname}"/>

    <spring:message code="sponsor.nameOfCompany" var="nameOfCompany"/>
    <display:column property="nameOfCompany" title="${nameOfCompany}"/>

    <spring:message code="sponsor.compute" var="recipeHeader"/>
    <display:column title="${recipeHeader}">
        <a href="admin/sponsor/monthly.do?id=${row.id}">
            <spring:message code="sponsor.compute"/>
        </a>
    </display:column>

</display:table>
