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


<display:table name="campaign" id="row" requestURI="http://localhost:8080/sponsor/campaing/list.do" pagesize="5"
               class="displaytag">

    <spring:message code="campaign.startDate" var="startheader"/>
    <display:column property="startDate" title="${startheader}"/>

    <spring:message code="campaign.endDate" var="endheader"/>
    <display:column property="endDate" title="${endheader}"/>

    <spring:message code="campaign.numberOfBanners" var="numberheader"/>
    <display:column property="numberOfBanners" title="${numberheader}"/>

    <spring:message code="campaign.maximunDisplayed" var="maximunheader"/>
    <display:column property="maximumDisplayed" title="${maximunheader}"/>

    <spring:message code="campaign.bannerCost" var="bannerCostheader"/>
    <display:column property="bannerCost" title="${bannerCostheader}"/>

    <spring:message code="campaign.sponsor" var="sponsorheader"/>
    <display:column property="sponsor" title="${sponsorheader}"/>

    <display:column>
        <a href="sponsor/campaing/edit.do?recipeID=${row.id}">
            <spring:message code="campaign.edit"/>
        </a>
    </display:column>
</display:table>
