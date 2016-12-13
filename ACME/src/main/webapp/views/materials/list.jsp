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


<display:table name="materials" id="row" requestURI="http://localhost:8080/materials/list_materials.do" pagesize="5"
               class="displaytag">

    <spring:message code="materials.title" var="titleheader"/>
    <display:column property="title" title="${titleheader}"/>

    <spring:message code="materials.abstracts" var="abstractsheader"/>
    <display:column property="abstracts" title="${abstractsheader}"/>

    <spring:message code="materials.type" var="typeheader"/>
    <display:column property="type" title="${typeheader}"/>

    <spring:message code="materials.attachment" var="attachmentheader"/>
    <display:column property="attachment" title="${attachmentheader}"/>


</display:table>
