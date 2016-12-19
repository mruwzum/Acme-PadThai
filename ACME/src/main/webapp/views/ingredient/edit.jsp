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


<form:form action="nutritionist/ingredient/save.do" modelAttribute="ingredient">

    <form:hidden path="id"/>
    <form:hidden path="version"/>


    <form:label path="name">
        <spring:message code="ingredient.name"/>
    </form:label>
    <form:input path="name"/>
    <form:errors cssClass="error" path="name"/>
    <br/>

    <form:label path="description">
        <spring:message code="ingredient.description"/>
    </form:label>
    <form:input path="description"/>
    <form:errors cssClass="error" path="description"/>
    <br/>

    <form:label path="picture">
        <spring:message code="ingredient.picture"/>
    </form:label>
    <form:input path="picture"/>
    <form:errors cssClass="error" path="picture"/>
    <br/>

    <input type="submit" name="save"
           value="<spring:message code="ingredient.send" />"/>&nbsp;


    <input type="button" name="cancel"
           value="<spring:message code="ingredient.cancel" />"
           onclick="relativeRedir('/nutritionist/ingredient/list.do');"/>
    <br/>

    <display:table name="property" id="row" requestURI="http://localhost:8080/nutritionist/property/list.do" pagesize="20"
                   class="displaytag">

        <spring:message code="p.name" var="name"/>
        <display:column property="name" title="${name}"/>

        <spring:message code="p.quantity" var="quantity"/>
        <display:column property="quantity" title="${quantity}"/>

        <display:column>
            <a href="nutritionist/ingredient/deleteProperty.do?id=${ingredient.id}&propertyID=${row.id}">
                <spring:message code="ingredient.delete"/>
            </a>
        </display:column>


    </display:table>

    <display:table name="properties" id="row" requestURI="http://localhost:8080/nutritionist/property/list.do" pagesize="20"
                   class="displaytag">

        <spring:message code="p.name" var="name"/>
        <display:column property="name" title="${name}"/>

        <spring:message code="p.quantity" var="quantity"/>
        <display:column property="quantity" title="${quantity}"/>

        <display:column>
            <a href="nutritionist/ingredient/addProperty.do?id=${ingredient.id}&propertyID=${row.id}">
                <spring:message code="ingredient.add"/>
            </a>
        </display:column>


    </display:table>




</form:form>