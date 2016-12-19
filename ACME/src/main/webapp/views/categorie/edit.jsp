<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 19/12/16
  Time: 11:22
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


<form:form action="admin/categorie/create/save.do?=${name}" modelAttribute="categorie">

    <form:hidden path="id"/>
    <form:hidden path="version"/>


    <form:label path="name">
        <spring:message code="categorie.name"/>
    </form:label>
    <form:input path="name"/>
    <form:errors cssClass="error" path="name"/>
    <br/>

    <form:label path="description">
        <spring:message code="categorie.description"/>
    </form:label>
    <form:input path="description"/>
    <form:errors cssClass="error" path="description"/>
    <br/>

    <form:label path="picture">
        <spring:message code="categorie.picture"/>
    </form:label>
    <form:input path="picture"/>
    <form:errors cssClass="error" path="picture"/>
    <br/>

    <input type="submit" name="save"
           value="<spring:message code="categorie.save" />"/>&nbsp;

    <input type="button" name="cancel"
           value="<spring:message code="categorie.cancel" />"
           onclick="relativeRedir('/actor/folder/list.do');"/>
    <br/>
</form:form>