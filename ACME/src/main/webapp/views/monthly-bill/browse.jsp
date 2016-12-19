<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 25/11/16
  Time: 11:45
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


<security:authorize access="hasRole('SPONSOR')">m
    <display:column>
        <a href="sponsor/monthly-bill/browse.do?Id=${row.id}">
            <spring:message code="monthlybill.browse"/>
        </a>
    </display:column>
</security:authorize>


<display:table name="monthlybill" id="row" requestURI="http://localhost:8080/sponsor/browse_monthly.do" pagesize="5"
               class="displaytag">

    <spring:message code="monthlybill.createDate" var="createheader"/>
    <display:column property="createDate" title="${createheader}"/>

    <spring:message code="monthlybill.payDate" var="payheader"/>
    <display:column property="payDate" title="${payheader}"/>

    <spring:message code="monthlybill.cost" var="costheader"/>
    <display:column property="cost" title="${costheader}"/>

    <spring:message code="monthlybill.description" var="descriptionheader"/>
    <display:column property="description" title="${descriptionheader}"/>

    <spring:message code="monthlybill.sponsor" var="sponsorheader"/>
    <display:column property="sponsor" title="${sponsorheader}"/>


</display:table>
