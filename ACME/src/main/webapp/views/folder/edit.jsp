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


<form:form action="actor/folder/new/save.do?name=${name}" modelAttribute="folder">

    <form:hidden path="id"/>
    <form:hidden path="version"/>
    <form:hidden path="messages"/>

    <form:label path="name">
        <spring:message code="folder.name"/>
    </form:label>
    <form:input path="name"/>
    <form:errors cssClass="error" path="name"/>
    <br/>

    <input type="submit" name="save"
           value="<spring:message code="folder.save" />"/>

    <input type="button" name="cancel"
           value="<spring:message code="m.cancel" />"
           onclick="relativeRedir('/actor/folder/list.do');"/>
    <br/>
</form:form>