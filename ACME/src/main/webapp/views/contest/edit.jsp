<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 19/12/16
  Time: 12:06
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


<form:form action="admin/contest/save.do" modelAttribute="contest">

    <form:hidden path="id"/>
    <form:hidden path="version"/>


    <form:label path="title">
        <spring:message code="contest.title"/>
    </form:label>
    <form:input path="title"/>
    <form:errors cssClass="error" path="title"/>
    <br/>

    <form:label path="oppeningDate">
        <spring:message code="contest.oppenig"/>
    </form:label>
    <form:input path="oppeningDate"/>
    <form:errors cssClass="error" path="oppeningDate"/>
    <br/>

    <form:label path="closingDate">
        <spring:message code="contest.closing"/>
    </form:label>
    <form:input path="closingDate"/>
    <form:errors cssClass="error" path="closingDate"/>
    <br/>

    <input type="submit" name="save"
           value="<spring:message code="contest.save" />"/>&nbsp;

    <input type="button" name="cancel"
           value="<spring:message code="contest.cancel" />"
           onclick="relativeRedir('/admin/contest/list.do');"/>
    <br/>
</form:form>