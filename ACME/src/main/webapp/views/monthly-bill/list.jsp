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


<display:table name="mb" id="row" requestURI="http://localhost:8080/sponsor/monthlyBillunpaid/list.do" pagesize="5"
               class="displaytag">

    <spring:message code="mb.createheader" var="createheader"/>
    <display:column property="createDate" title="${createheader}"/>

    <spring:message code="mb.payDate" var="payDate"/>
    <display:column property="payDate" title="${payDate}"/>

    <spring:message code="mb.cost" var="cost"/>
    <display:column property="cost" title="${cost}"/>

    <spring:message code="mb.description" var="description"/>
    <display:column property="description" title="${description}"/>

    <spring:message code="mb.sponsor" var="sponsor"/>
    <display:column property="sponsor" title="${sponsor}"/>

    <spring:message code="mb.paid" var="paid"/>
    <display:column property="paid" title="${paid}"/>

    <display:column>
    <a href="sponsor/monthlyBill/pay.do?monthID=${row.id}">
    <spring:message code="mb.pay"/>
    </a>
    </display:column>
</display:table>
