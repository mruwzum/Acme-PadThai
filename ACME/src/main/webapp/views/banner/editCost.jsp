<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 25/11/16
  Time: 16:07
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



<form:form action="admin/banner/editCost/save.do" modelAttribute="campaing">
    <form:hidden path="id"/>
    <form:hidden path="version"/>
    <form:hidden path="startDate"/>
    <form:hidden path="endDate"/>
    <form:hidden path="numberOfBanners"/>
    <form:hidden path="maximumDisplayed"/>
    <form:hidden path="sponsor"/>


    <form:label path="bannerCost">
        <spring:message code="banner.bannerCost"/>
    </form:label>
    <form:input path="bannerCost"/>
    <form:errors cssClass="error" path="bannerCost"/>
    <br/>

    <input type="submit" name="save"
           value="<spring:message code="banner.save" />"/>&nbsp;

    <input type="button" name="cancel"
           value="<spring:message code="banner.cancel" />"
           onclick="relativeRedir('admin/banner/editCost/save.do?cost=${bannerCost}&id2=${id2}');"/>
    <br/>
    <input type="button" name="cancel"
           value="<spring:message code="banner.cancel" />"
           onclick="relativeRedir('admin/campaign/list.do');"/>
    <br/>

</form:form>