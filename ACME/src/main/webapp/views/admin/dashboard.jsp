<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 19/12/16
  Time: 23:24
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

<spring:message code="dashboard.q1" var="q1b"/>
<h1><jstl:out value="${q1b}"/></h1>
<jstl:out value="${q1}"/>

<spring:message code="dashboard.q2" var="q2b"/>
<h1><jstl:out value="${q2b}"/></h1>
<jstl:out value="${q2}"/>

<spring:message code="dashboard.q3" var="q3b"/>
<h1><jstl:out value="${q3b}"/></h1>
<jstl:out value="${q3}"/>