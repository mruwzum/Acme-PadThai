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


<display:table name="curricula" id="row" requestURI="http://localhost:8080/nutritionist/list_curricula.do" pagesize="5"
               class="displaytag">

    <spring:message code="curricula.education" var="edusecheader"/>
    <display:column property="educationSection" title="${edusecheader}"/>

    <spring:message code="curricula.experience" var="expsecheader"/>
    <display:column property="experienceSection" title="${expsecheader}"/>

    <spring:message code="curricula.hobbies" var="hobsecheader"/>
    <display:column property="hobbiesSection" title="${edusecheader}"/>

    <spring:message code="curricula.references" var="referenceheader"/>
    <display:column property="referencias" title="${referenceheader}"/>

    <spring:message code="curricula.photo" var="photoheader"/>
    <display:column property="photo" title="${photoheader}"/>

    <display:column>
        <a href="/curricula/edit.do?id=${row.id}">
            <spring:message code="curricula.edit"/>
        </a>
    </display:column>
</display:table>
