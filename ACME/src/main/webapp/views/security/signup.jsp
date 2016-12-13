<%--
* login.jsp
*
* Copyright (C) 2016 Universidad de Sevilla
*
* The use of this project is hereby constrained to the conditions of the
* TDG Licence, a copy of which you may download from
* http://www.tdg-seville.info/License.html
--%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>


<form:form action="security/singup.do" modelAttribute="actor">

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

    <form:button id="role">
        <spring:message code="actor.user"/>
    </form:button>
    <form:input path="user"/>
    <form:errors cssClass="error" path="user"/>
    <form:button id="nutritionist">
        <spring:message code="actor.nutritionist"/>
    </form:button>
    <form:input path="nutritionist"/>
    <form:errors cssClass="error" path="nutritionist"/>
    <form:button id="cook">
        <spring:message code="actor.cook"/>
    </form:button>
    <form:input path="cook"/>
    <form:errors cssClass="error" path="cook"/>
    <br/>

    <form:label path="password">
        <spring:message code="actor.password"/>
    </form:label>
    <form:input path="password"/>
    <form:errors cssClass="error" path="password"/>
    <br/>
    <form:label path="confirmPassword">
        <spring:message code="actor.confirm.password"/>
    </form:label>
    <form:input path="confirmPassword"/>
    <form:errors cssClass="error" path="confirmPassword"/>
    <br/>


    <%--<input type="submit" name="save"
           value="<spring:message code="actor.save" />"/>&nbsp;
    <jstl:if test="${actor.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="actor.delete" />"
               onclick="return confirm('<spring:message code="actor.confirm.delete"/>')"/>&nbsp;
    </jstl:if>
    <input type="button" name="cancel"
           value="<spring:message code="actor.cancel" />"
           onclick="relativeRedir('sponsor/mclass/list.do');"/>
    <br/>--%>

</form:form>