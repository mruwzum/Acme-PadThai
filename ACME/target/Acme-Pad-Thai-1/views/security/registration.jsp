<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 8/12/16
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form:form action="user/user/registration.do" modelAttribute="user">
    <form:hidden path="id"/>
    <form:hidden path="version"/>
    <form:hidden path="folders"/>

    <br>
    <form:label path="name">
        <spring:message code="label.user.firstName"/>:
    </form:label>
    <form:input path="name"/>
    <form:errors cssClass="error" path="name"/>
    <br/>
    <form:label path="surname">
        <spring:message code="label.user.lastName"/>:
    </form:label>
    <form:input path="surname"/>
    <form:errors cssClass="error" path="surname"/>
    <br/>
    <form:label path="emailAddress">
        <spring:message code="label.user.email"/>:
    </form:label>
    <form:input path="emailAddress"/>
    <form:errors cssClass="error" path="emailAddress"/>
    <br/>
    <form:label path="phone">
        <spring:message code="label.user.phone"/>:
    </form:label>
    <form:input path="phone"/>
    <form:errors cssClass="error" path="phone"/>
    <br/>

    <form:label path="postalAddress">
        <spring:message code="label.user.postalAddress"/>:
    </form:label>
    <form:input path="postalAddress"/>
    <form:errors cssClass="error" path="postalAddress"/>
    <br/>



    <jsp:useBean id="SocialIdentity" scope="request" type="domain.SocialIdentity"/>
    <h1>Social Identity</h1>
    <jstl:set var="nickname" value="${SocialIdentity.nickname}"/>
    <form:label path="socialIdentity.nickname">
        <spring:message code="socialIdentity.nickName"/>:
    </form:label>
    <form:input path="socialIdentity.nickname"/>
    <form:errors cssClass="error" path="socialIdentity.nickname"/>
    <%--TODO DUDA PARA EL VIERNES--%>

    <br/>
    <jstl:set var="socialNet" value="${SocialIdentity.socialNet}"/>
    <form:label path="socialIdentity.socialNet">
        <spring:message code="socialIdentity.socialNet"/>:
    </form:label>
    <form:input path="socialIdentity.socialNet"/>
    <form:errors cssClass="error" path="socialIdentity.socialNet"/>


    <br/>
    <jstl:set var="link" value="${SocialIdentity.link}"/>
    
    <form:label path="socialIdentity.link">
        <spring:message code="socialIdentity.link"/>:
    </form:label>
    <form:input path="socialIdentity.link"/>
    <form:errors cssClass="error" path="socialIdentity.socialNet"/>


    <br/>




    <security:authorize access="isAnonymous()">
        <h1>User Account</h1>
        <form:label path="UserAccount.username">
            <spring:message code="actor.username"/>:
        </form:label>
        <form:input path="UserAccount.username"/>
        <form:errors cssClass="error" path="UserAccount.username"/>
        <br/>

        <form:label path="UserAccount.password">
            <spring:message code="actor.password"/>:
        </form:label>
        <form:password path="UserAccount.password"/>
        <form:errors cssClass="error" path="UserAccount.password"/>
        <br/>


        <input type="submit" name="save" value="<spring:message code="actor.save" />"/>
        &nbsp;

        <jstl:if test="${user.id != 0}">
            <input type="submit" name="delete"
                   value="<spring:message code="actor.delete" />"
                   onclick="return confirm('<spring:message code="actor.confirm.delete"/>')"/>&nbsp;
        </jstl:if>

        <input type="button" name="cancel"
               value="<spring:message code="actor.cancel" />"
               onclick="window.location.replace('anonymus/users.do')"/>

    </security:authorize>

    <security:authorize access="isAuthenticated()">

        <spring:url value="http://localhost:8080/actor/userPersonalData/save.do" var="url">

            <spring:param name="name" value="${name}"/>
            <spring:param name="surname" value="${surname}"/>
            <spring:param name="emailAddress" value="${emailAddress}"/>
            <spring:param name="phone" value="${phone}"/>
            <spring:param name="postalAddress" value="${postalAddress}"/>
            <spring:param name="name" value="${socialIdentity.nickname}"/>
            <spring:param name="name" value="${socialNet}"/>
            <spring:param name="name" value="${link}"/>
            <%--TODO a partir del nikcname peta porque no le puedo meter una variable con un punto--%>


        </spring:url>

        <input type="button" name="save"
               value="<spring:message code="actor.save" />"
               onclick="window.location.replace('<jstl:out value="${url}"/>')"/>

        <input type="button" name="cancel"
               value="<spring:message code="actor.cancel" />"
               onclick="window.location.replace('http://localhost:8080/')"/>

    </security:authorize>


</form:form>

