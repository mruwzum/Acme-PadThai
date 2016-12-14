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


<security:authorize access="hasRole('ADMIN')">
    <display:column>
        <a href="actor/edit.do?actor=${row.id}">
            <spring:message code="mclass.edit"/>
        </a>
    </display:column>
</security:authorize>

<form:form action="actor/edit.do" modelAttribute="actor">

    <form:hidden path="id"/>
    <form:hidden path="version"/>

    <form:label path="name">
        <spring:message code="actor.name"/>
    </form:label>
    <form:input path="name"/>
    <form:errors cssClass="error" path="name"/>
    <br/>

    <form:label path="surname">
        <spring:message code="actor.surname"/>
    </form:label>
    <form:input path="surname"/>
    <form:errors cssClass="error" path="surname"/>
    <br/>

    <form:label path="email">
        <spring:message code="actor.email"/>
    </form:label>
    <form:input path="email"/>
    <form:errors cssClass="error" path="email"/>
    <br/>

    <form:label path="phone">
        <spring:message code="actor.phone"/>
    </form:label>
    <form:input path="phone"/>
    <form:errors cssClass="error" path="phone"/>
    <br/>

    <form:label path="postalCode">
        <spring:message code="actor.postalCode"/>
    </form:label>
    <form:input path="postalCode"/>
    <form:errors cssClass="error" path="postalCode"/>
    <br/>

    <form:label path="username">
        <spring:message code="actor.username"/>
    </form:label>
    <form:input path="username"/>
    <form:errors cssClass="error" path="username"/>
    <br/>

    <input type="submit" name="save"
           value="<spring:message code="actor.save" />"/>&nbsp;
    <jstl:if test="${actor.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="actor.delete" />"
               onclick="return confirm('<spring:message code="actor.confirm.delete"/>')"/>&nbsp;
    </jstl:if>
    <input type="button" name="cancel"
           value="<spring:message code="actor.cancel" />"
           onclick="relativeRedir('actor/edit.do');"/>
    <br/>

</form:form>