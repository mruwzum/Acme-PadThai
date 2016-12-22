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


<form:form action="sponsor/creditcard/save.do" modelAttribute="creditCard">

    <form:hidden path="id"/>
    <form:hidden path="version"/>

    <form:label path="holderName">
        <spring:message code="credit-card.holderName"/>
    </form:label>
    <form:input path="holderName"/>
    <form:errors cssClass="error" path="holderName"/>
    <br/>

    <form:label path="brandName">
        <spring:message code="credit-card.brandName"/>
    </form:label>
    <form:input path="brandName"/>
    <form:errors cssClass="error" path="brandName"/>
    <br/>

    <form:label path="number">
        <spring:message code="credit-card.number"/>
    </form:label>
    <form:input path="number"/>
    <form:errors cssClass="error" path="number"/>
    <br/>

    <form:label path="expirationYear">
        <spring:message code="credit-card.expirationYear"/>
    </form:label>
    <form:input path="expirationYear"/>
    <form:errors cssClass="error" path="expirationYear"/>
    <br/>

    <form:label path="expirationMonth">
        <spring:message code="credit-card.expirationMonth"/>
    </form:label>
    <form:input path="expirationMonth"/>
    <form:errors cssClass="error" path="expirationMonth"/>
    <br/>

    <form:label path="CVV">
        <spring:message code="credit-card.CVV"/>
    </form:label>
    <form:input path="CVV"/>
    <form:errors cssClass="error" path="CVV"/>
    <br/>



    <input type="submit" name="save"
           value="<spring:message code="credit-card.save" />"/>&nbsp;
    <jstl:if test="${curricula.id != 0}">
        <a href="/sponsor/creditcard/delete.do?creditcardID=${creditCard.id}">
            <spring:message code="credit-card.delete"/>
        </a>
    </jstl:if>
</form:form>