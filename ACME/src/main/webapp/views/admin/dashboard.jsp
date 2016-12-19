<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 19/12/16
  Time: 23:24
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


<display:table name="userRecipes" id="row" requestURI="http://localhost:8080/admin/dashboard.do" pagesize="5"
               class="displaytag">

    <spring:message code="user.minumum" var="minumum"/>
    <display:column property="minumum" title="${minumum}"/>

    <spring:message code="user.average" var="average"/>
    <display:column property="average" title="${average}"/>

    <spring:message code="user.maximum" var="maximum"/>
    <display:column property="maximum" title="${maximum}"/>

</display:table>
<display:table name="userMoreRecipes" id="row" requestURI="http://localhost:8080/admin/dashboard.do" pagesize="5"
               class="displaytag">

    <spring:message code="user.nameUserMore" var="nameUserMore"/>
    <display:column property="name" title="${nameUserMore}"/>


</display:table>