<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 8/12/16
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form:form action="admin/edit.do" modelAttribute="admin">
    <form:hidden path="id"/>
    <form:hidden path="version"/>

    <br>
    <form:label path="name">
        <spring:message code="label.user.firstName"/>:
    </form:label>
    <form:input path="name"/>
    <form:errors cssClass="error" path="name"/>
    <br/>
    <form:label path="surname">
        <spring:message code="label.user.lastName"/>:
    </form:label>
    <form:input path="surname"/>
    <form:errors cssClass="error" path="surname"/>
    <br/>
    <form:label path="emailAddress">
        <spring:message code="label.user.email"/>:
    </form:label>
    <form:input path="emailAddress"/>
    <form:errors cssClass="error" path="emailAddress"/>
    <br/>
    <form:label path="phone">
        <spring:message code="label.user.phone"/>:
    </form:label>
    <form:input path="phone"/>
    <form:errors cssClass="error" path="phone"/>
    <br/>

    <form:label path="postalAddress">
        <spring:message code="label.user.postalAddress"/>:
    </form:label>
    <form:input path="postalAddress"/>
    <form:errors cssClass="error" path="postalAddress"/>
    <br/>



        <spring:url value="http://localhost:8080/actor/userPersonalData/save.do" var="url">

            <spring:param name="name" value="${name}"/>
            <spring:param name="surname" value="${surname}"/>
            <spring:param name="emailAddress" value="${emailAddress}"/>
            <spring:param name="phone" value="${phone}"/>
            <spring:param name="postalAddress" value="${postalAddress}"/>
            <%--TODO a partir del nikcname peta porque no le puedo meter una variable con un punto--%>


        </spring:url>

        <input type="button" name="save"
               value="<spring:message code="actor.save" />"
               onclick="window.location.replace('<jstl:out value="http://localhost:8080/"/>')"/>

        <input type="button" name="cancel"
               value="<spring:message code="actor.cancel" />"
               onclick="window.location.replace('http://localhost:8080/')"/>



</form:form>

