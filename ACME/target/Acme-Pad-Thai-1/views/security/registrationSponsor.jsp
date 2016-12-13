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

<form:form action="sponsor/registration.do" modelAttribute="sponsor">
    <form:hidden path="id"/>
    <form:hidden path="version" />
    <form:hidden path="folders"/>
    <form:hidden path="message"/>

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

    <h1>Credit Card</h1>
    <form:label path="CreditCard.holderName">
        <spring:message code="actor.holderName"/>:
    </form:label>
    <form:input path="CreditCard.holderName"/>
    <form:errors cssClass="error" path="CreditCard.holderName"/>
    <br />
    <form:label path="CreditCard.brandName">
        <spring:message code="actor.brandName"/>:
    </form:label>
    <form:input path="CreditCard.number"/>
    <form:errors cssClass="error" path="CreditCard.number"/>
    <br />
    <form:label path="CreditCard.number">
        <spring:message code="actor.number"/>:
    </form:label>

    <form:input path="CreditCard.expirationYear"/>
    <form:errors cssClass="error" path="CreditCard.expirationYear"/>
    <br/>

    <form:label path="CreditCard.expirationYear">
        <spring:message code="actor.expirationYear"/>:
    </form:label>
    <form:input path="CreditCard.CVV"/>
    <form:errors cssClass="error" path="CreditCard.CVV"/>
    <br/>

    <form:label path="CreditCard.CVV">
        <spring:message code="actor.CVV"/>:
    </form:label>
    <form:input path="CreditCard.expirationMonth"/>
    <form:errors cssClass="error" path="CreditCard.expirationMonth"/>
    <br/>

    <form:label path="CreditCard.expirationMonth">
        <spring:message code="actor.expirationMonth"/>:
    </form:label>





    <input type="submit" name="save" value="<spring:message code="actor.save" />" />
    &nbsp;
    <jstl:if test="${sponsor.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="actor.delete" />"
               onclick="return confirm('<spring:message code="actor.confirm.delete" />')" />&nbsp;
    </jstl:if>
    <input type="button" name="cancel"
           value="<spring:message code="actor.cancel" />"
           onclick="window.location.replace('anonymus/users.do')"/>
</form:form>
<br>
<a href="<c:url value="/security/login.do" />">
    <spring:message code="label.form.loginLink"> </spring:message>
</a>