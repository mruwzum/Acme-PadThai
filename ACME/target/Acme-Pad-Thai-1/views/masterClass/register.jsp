<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 25/11/16
  Time: 17:37
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


<form:form action="mclass/register.do" modelAttribute="masterclass">

    <form:hidden path="id"/>
    <form:hidden path="version"/>

    <form:label path="Title">
        <spring:message code="mclass.title"/>
    </form:label>
    <form:input path="title"/>
    <form:errors cssClass="error" path="Title"/>
    <br/>

    <form:label path="Description">
        <spring:message code="mclass.description"/>
    </form:label>
    <form:input path="description"/>
    <form:errors cssClass="error" path="Description"/>
    <br/>

    <form:label path="Cook">
        <spring:message code="mclass.cook"/>
    </form:label>
    <form:input path="cook"/>
    <form:errors cssClass="error" path="Cook"/>
    <br/>

    <display:column>
        <a href="/materials/edit.do?id=${row.id}">
            <spring:message code="materials.edit"/>
        </a>
    </display:column>
    <input type="submit" name="save"
           value="<spring:message code="mclass.save" />"/>&nbsp;
    <jstl:if test="${masterclass.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="mclass.delete" />"
               onclick="return confirm('<spring:message code="mclass.confirm.delete"/>')"/>&nbsp;
    </jstl:if>
    <input type="button" name="cancel"
           value="<spring:message code="mclass.cancel" />"
           onclick="relativeRedir('sponsor/mclass/list.do');"/>
    <br/>
</form:form>