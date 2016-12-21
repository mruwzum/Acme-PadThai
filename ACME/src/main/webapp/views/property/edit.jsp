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


<form:form action="nutritionist/property/save.do" modelAttribute="property">

    <form:hidden path="id"/>
    <form:hidden path="version"/>


    <form:label path="name">
        <spring:message code="p.name"/>
    </form:label>
    <form:input path="name"/>
    <form:errors cssClass="error" path="name"/>
    <br/>

    <%--TODO ponerlo como desplegable--%>
    <form:label path="quantity">
        <spring:message code="p.quantity"/>
    </form:label>
    <form:select path="quantity">
        <form:options items="${quantity}" itemValue="id" />
    </form:select>
    <form:errors cssClass="error" path="quantity"/>

    <input type="submit" name="save"
           value="<spring:message code="ingredient.send" />"/>&nbsp;


    <input type="button" name="cancel"
           value="<spring:message code="ingredient.cancel" />"
           onclick="relativeRedir('/nutritionist/ingredient/list.do');"/>
    <br/>

</form:form>