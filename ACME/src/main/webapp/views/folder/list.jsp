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

<display:table name="folders" id="row" requestURI="http://localhost:8080/actor/folder/list.do" pagesize="5"
               class="displaytag">

    <spring:message code="folder.name" var="name"/>
    <display:column property="name" title="${name}" sortable="true"/>


    <display:column>
    <a href="actor/folder/view.do?folderID=${row.id}">
        <spring:message code="folder.open"/>
    </a>
</display:column>
    <display:column>
        <a href="actor/folder/edit.do?folderID=${row.id}">
            <spring:message code="folder.edit"/>
        </a>
    </display:column>
</display:table>

<input type="button" name="newmessage"
       value="<spring:message code="folder.new.message" />"
       onclick="relativeRedir('actor/mensaje/new.do');"/>
<input type="button" name="newfolder"
       value="<spring:message code="folder.new.folder" />"
       onclick="relativeRedir('actor/folder/new.do');"/>