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

<display:table name="categorie" id="row" requestURI="http://localhost:8080/admin/categorie/list.do" pagesize="5"
               class="displaytag">

    <spring:message code="categorie.name" var="name"/>
    <display:column property="name" title="${name}" sortable="true"/>


    <spring:message code="categorie.description" var="description"/>
    <display:column property="description" title="${description}"/>

    <spring:message code="categorie.picture" var="picture"/>
    <display:column property="picture" title="${picture}"/>


    <display:column>
        <a href="admin/categorie/edit.do?categorieID=${row.id}">
            <spring:message code="categorie.edit"/>
        </a>
    </display:column>
    <display:column>
        <a href="admin/categorie/delete.do?contestID=${row.id}">
            <spring:message code="categorie.delete"/>
        </a>
    </display:column>
</display:table>

<input type="button" name="create"
       value="<spring:message code="categorie.new.categorie" />"
       onclick="relativeRedir('admin/categorie/create.do');"/>

