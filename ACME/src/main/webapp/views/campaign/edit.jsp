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


<security:authorize access="hasRole('SPONSOR')">
    <display:column>
        <a href="/campaing/list.do?Id=${row.id}">
            <spring:message code="campaign.edit"/>
        </a>
    </display:column>
</security:authorize>

<form:form action="campaign/edit.do" modelAttribute="campaign">

    <form:hidden path="id"/>
    <form:hidden path="version"/>

    <form:label path="Start Date">
        <spring:message code="campaign.startDate"/>
    </form:label>
    <form:input path="startDate"/>
    <form:errors cssClass="error" path="Start Date"/>
    <br/>

    <form:label path="End Date">
        <spring:message code="campaign.endDate"/>
    </form:label>
    <form:input path="endDate"/>
    <form:errors cssClass="error" path="End Date"/>
    <br/>

    <form:label path="Number of banners">
        <spring:message code="campaign.numberOfBanners"/>
    </form:label>
    <form:input path="numberOfBanners"/>
    <form:errors cssClass="error" path="Number of banners"/>
    <br/>

    <form:label path="Maximum displayed">
        <spring:message code="campaign.maximunDisplayed"/>
    </form:label>
    <form:input path="maximumDisplayed"/>
    <form:errors cssClass="error" path="Maximum displayed"/>
    <br/>
    <input type="submit" name="save"
           value="<spring:message code="campaign.save" />"/>&nbsp;
    <jstl:if test="${masterclass.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="campaign.delete" />"
               onclick="return confirm('<spring:message code="mclass.confirm.delete"/>')"/>&nbsp;
    </jstl:if>
    <input type="button" name="cancel"
           value="<spring:message code="campaign.cancel" />"
           onclick="relativeRedir('sponsor/mclass/list.do');"/>
    <br/>
</form:form>