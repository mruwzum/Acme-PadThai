<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 22/12/16
  Time: 12:13
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
    <spring:message code="mclass.title" var="title"/>
    <title>${title}</title>
</head>
<body>


<spring:message code="mclass.title" var="titleTitle"/>
<h1><jstl:out value="${titleTitle}"/></h1>
<jstl:out value="${title}"/>


<spring:message code="mclass.description" var="descriptionTitle"/>
<h1><jstl:out value="${descriptionTitle}"/></h1>
<jstl:out value="${description}"/>


<spring:message code="mclass.cook" var="cookTitle"/>
<h1><jstl:out value="${cookTitle}"/></h1>
<jstl:out value="${cook}"/>


<spring:message code="mclass.material" var="materialsTitle"/>
<h1><jstl:out value="${materialsTitle}"/></h1>
<jstl:out value="${materials}"/>


<spring:message code="mclass.registers" var="registersTitle"/>
<h1><jstl:out value="${registersTitle}"/></h1>
<jstl:out value="${registers}"/>



</body>
</html>