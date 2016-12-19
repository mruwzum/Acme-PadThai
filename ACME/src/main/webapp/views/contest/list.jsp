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

<display:table name="contest" id="row" requestURI="http://localhost:8080/admin/contest/list.do" pagesize="5"
               class="displaytag">

    <spring:message code="contest.title" var="titleheader"/>
    <display:column property="title" title="${titleheader}" sortable="true"/>

    <spring:message code="contest.oppenig" var="openheader"/>
    <display:column property="oppeningDate" title="${openheader}" sortable="true"/>

    <spring:message code="contest.closing" var="closeheader"/>
    <display:column property="closingDate" title="${closeheader}"/>

    <display:column>
        <a href="admin/contest/edit.do?contestID=${row.id}">
            <spring:message code="contest.edit"/>
        </a>
    </display:column>
    <display:column>
        <a href="admin/contest/delete.do?contestID=${row.id}">
            <spring:message code="contest.delete"/>
        </a>
    </display:column>


</display:table>