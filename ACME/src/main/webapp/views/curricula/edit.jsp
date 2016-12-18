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


<form:form action="nutritionist/curricula/save.do" modelAttribute="curricula">

    <form:hidden path="id"/>
    <form:hidden path="version"/>

    <form:label path="hobbiesSection">
        <spring:message code="curricula.hobbiesSection"/>
    </form:label>
    <form:input path="hobbiesSection"/>
    <form:errors cssClass="error" path="hobbiesSection"/>
    <br/>

    <form:label path="educationSection">
        <spring:message code="curricula.educationSection"/>
    </form:label>
    <form:input path="educationSection"/>
    <form:errors cssClass="error" path="educationSection"/>
    <br/>

    <form:label path="experienceSection">
        <spring:message code="curricula.experienceSection"/>
    </form:label>
    <form:input path="experienceSection"/>
    <form:errors cssClass="error" path="hobbiesSection"/>
    <br/>

    <form:label path="referencias">
        <spring:message code="curricula.referencias"/>
    </form:label>
    <form:input path="referencias"/>
    <form:errors cssClass="error" path="referencias"/>
    <br/>

    <form:label path="photo">
        <spring:message code="curricula.photo"/>
    </form:label>
    <form:input path="photo"/>
    <form:errors cssClass="error" path="photo"/>
    <br/>

    <input type="submit" name="save"
           value="<spring:message code="curricula.save" />"/>&nbsp;

    <br/>

</form:form>