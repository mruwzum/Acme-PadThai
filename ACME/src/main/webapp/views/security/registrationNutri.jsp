<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 10/12/16
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 8/12/16
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form action="nutritionist/registration.do" modelAttribute="nutritionist">
    <form:hidden path="id" />
    <form:hidden path="version" />
    <form:hidden path="folders"/>
    <form:hidden path="message"/>
    <form:hidden path="followers"/>
    <form:hidden path="comments"/>


    <br>
    <form:label path="name">
        <spring:message code="label.user.firstName" />:
    </form:label>
    <form:input path="name" />
    <form:errors cssClass="error" path="name" />
    <br />
    <form:label path="surname">
        <spring:message code="label.user.lastName" />:
    </form:label>
    <form:input path="surname" />
    <form:errors cssClass="error" path="surname" />
    <br />
    <form:label path="emailAddress">
        <spring:message code="label.user.email" />:
    </form:label>
    <form:input path="emailAddress" />
    <form:errors cssClass="error" path="emailAddress" />
    <br />
    <form:label path="phone">
        <spring:message code="label.user.phone" />:
    </form:label>
    <form:input path="phone" />
    <form:errors cssClass="error" path="phone" />
    <br />

    <form:label path="postalAddress">
        <spring:message code="label.user.postalAddress" />:
    </form:label>
    <form:input path="postalAddress" />
    <form:errors cssClass="error" path="postalAddress" />
    <br />
    <h1>Social Identity</h1>
    <form:label path="SocialIdentity.nickname">
        <spring:message code="socialIdentity.nickName" />:
    </form:label>
    <form:input path="SocialIdentity.nickname" />
    <form:errors cssClass="error" path="SocialIdentity.nickname" />
    <br />
    <form:label path="SocialIdentity.socialNet">
        <spring:message code="socialIdentity.socialNet" />:
    </form:label>
    <form:input path="SocialIdentity.socialNet" />
    <form:errors cssClass="error" path="SocialIdentity.socialNet" />
    <br />
    <form:label path="socialIdentity.link">
        <spring:message code="socialIdentity.link"/>:
    </form:label>
    <form:input path="socialIdentity.link"/>
    <form:errors cssClass="error" path="socialIdentity.socialNet"/>
    <br/>


    <security:authorize access="isAnonymous()">
    <h1>User Account</h1>
    <form:label path="UserAccount.username">
        <spring:message code="actor.username" />:
    </form:label>
    <form:input path="UserAccount.username" />
    <form:errors cssClass="error" path="UserAccount.username" />
    <br />

    <form:label path="UserAccount.password">
        <spring:message code="actor.password" />:
    </form:label>
    <form:password path="UserAccount.password" />
    <form:errors cssClass="error" path="UserAccount.password" />
    <br />



    <input type="submit" name="save" value="<spring:message code="actor.save" />" />
    &nbsp;
    <jstl:if test="${nutritionist.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="actor.delete" />"
               onclick="return confirm('<spring:message code="actor.confirm.delete" />')" />&nbsp;
    </jstl:if>
    <input type="button" name="cancel"
           value="<spring:message code="actor.cancel" />"
           onclick="window.location.replace('anonymus/users.do')"/>
    </security:authorize>

    <%--TODO poner este bot�n y todos en condiciones--%>
    <security:authorize access="isAuthenticated()">

        <input type="button" name="save"
               value="<spring:message code="actor.save" />"
               onclick="window.location.replace('anonymus/users.do')"/>

        <input type="button" name="cancel"
               value="<spring:message code="actor.cancel" />"
               onclick="window.location.replace('anonymus/users.do')"/>

    </security:authorize>
</form:form>
