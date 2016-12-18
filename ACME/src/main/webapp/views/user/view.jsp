<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 17/12/16
  Time: 18:26
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
<html>
<head>
    <spring:message code="user.view.title" var="title"/>
    <title>${tittle}</title>
</head>
<body>

<spring:message code="user.view.name" var="nameTitle"/>
<h1><jstl:out value="${nameTitle}"/></h1>
<jstl:out value="${name}"/>
<spring:message code="user.view.surname" var="surnameTitle"/>
<h1><jstl:out value="${surnameTitle}"/></h1>
<jstl:out value="${surname}"/>
<spring:message code="user.view.emailAddress" var="emailAddressTitle"/>
<h1><jstl:out value="${emailAddressTitle}"/></h1>
<jstl:out value="${emailAddress}"/>
<spring:message code="user.view.phone" var="phoneTitle"/>
<h1><jstl:out value="${phoneTitle}"/></h1>
<jstl:out value="${phone}"/>
<spring:message code="user.view.postalAddress" var="postalAddressTitle"/>
<h1><jstl:out value="${postalAddressTitle}"/></h1>
<jstl:out value="${postalAddress}"/>

<jstl:if test="${isFollowing}">
    <a href="user/follow.do?userID=${row.id}">
        <spring:message code="actor.unfollow"/>
    </a>
</jstl:if>

<a href="user/follow.do?userID=${row.id}">
    <spring:message code="actor.follow"/>
</a>


</body>
</html>
