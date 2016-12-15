<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 26/11/16
  Time: 11:12
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


<display:table name="messages" id="row" requestURI="http://localhost:8080/actor/folder/view.do" pagesize="5"
               class="displaytag">

    <spring:message code="message.subject" var="subject"/>
    <display:column property="subject" title="${subject}"/>

    <spring:message code="message.sentDate" var="sentDate"/>
    <display:column property="sentDate" title="${sentDate}"/>

    <spring:message code="message.recipient" var="recipient"/>
    <display:column property="recipient" title="${recipient}"/>

    <spring:message code="message.priority" var="priority"/>
    <display:column property="priority" title="${priority}"/>


</display:table>
