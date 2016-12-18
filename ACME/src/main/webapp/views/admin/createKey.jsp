<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 18/12/16
  Time: 16:18
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


<display:table name="admin" id="row" requestURI="http://localhost:8080/admin/key/create.do" pagesize="5"
               class="displaytag">

    <spring:message code="admin.key" var="key"/>
    <display:column property="keywor" title="${key}"/>

</display:table>
<br>
<br/>
<form:form action="admin/key/save.do" modelAttribute="admin">

    <form:label path="keywor">
        <spring:message code="admin.key"/>
    </form:label>
    <form:input path="keywor"/>
    <form:errors cssClass="error" path="keywor"/>
    <br/>
    <input type="submit" name="save"
           value="<spring:message code="actor.save" />"/>


</form:form>