<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 18/12/16
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
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
    <spring:message code="recipe.view.titulazo" var="titulazo"/>
    <title>${titulazo}</title>
</head>
<body>

<spring:message code="recipe.view.title" var="titleTitle"/>
<h1><jstl:out value="${titleTitle}"/></h1>
<jstl:out value="${title}"/>
<spring:message code="recipe.view.summary" var="summaryTitle"/>
<h1><jstl:out value="${summaryTitle}"/></h1>
<jstl:out value="${summary}"/>
<spring:message code="recipe.view.creationDate" var="creationDateTitle"/>
<h1><jstl:out value="${creationDateTitle}"/></h1>
<jstl:out value="${creationDate}"/>
<spring:message code="recipe.view.updateDate" var="updateDateTitle"/>
<h1><jstl:out value="${updateDateTitle}"/></h1>
<jstl:out value="${updateDate}"/>
<spring:message code="recipe.view.categorie" var="categorieTitle"/>
<h1><jstl:out value="${categorieTitle}"/></h1>
<jstl:out value="${categorie}"/>
<spring:message code="recipe.view.user" var="userTitle"/>
<h1><jstl:out value="${userTitle}"/></h1>
<jstl:out value="${user}"/>
<spring:message code="recipe.view.rate" var="rateTitle"/>
<h1><jstl:out value="${rateTitle}"/></h1>
<jstl:out value="${rate}"/>


</body>
</html>
