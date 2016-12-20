<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 19/12/16
  Time: 23:24
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

<spring:message code="dashboard.q1" var="q1b"/>
<h3><jstl:out value="${q1b}"/></h3>
<jstl:out value="${q1}"/>

<spring:message code="dashboard.q2" var="q2b"/>
<h3><jstl:out value="${q2b}"/></h3>
<jstl:out value="${q2}"/>

<spring:message code="dashboard.q3" var="q3b"/>
<h3><jstl:out value="${q3b}"/></h3>
<jstl:out value="${q3}"/>

<spring:message code="dashboard.q4" var="q4b"/>
<h3><jstl:out value="${q4b}"/></h3>
<jstl:out value="${q4}"/>

<spring:message code="dashboard.q5" var="q5b"/>
<h3><jstl:out value="${q5b}"/></h3>
<jstl:out value="${q5}"/>

<spring:message code="dashboard.q6" var="q6b"/>
<h3><jstl:out value="${q6b}"/></h3>
<jstl:out value="${q6}"/>

<spring:message code="dashboard.q7" var="q7b"/>
<h3><jstl:out value="${q7b}"/></h3>
<jstl:out value="${q7}"/>

<spring:message code="dashboard.q8" var="q8b"/>
<h3><jstl:out value="${q8b}"/></h3>
<display:table name="q8" id="row" requestURI="http://localhost:8080/admin/dashboard.do" pagesize="5"
               class="displaytag">

    <spring:message code="q8.title" var="titleheader"/>
    <display:column property="title" title="${titleheader}" sortable="true"/>

    <spring:message code="q8.oppenig" var="openheader"/>
    <display:column property="oppeningDate" title="${openheader}" sortable="true"/>

    <spring:message code="q8.closing" var="closeheader"/>
    <display:column property="closingDate" title="${closeheader}"/>

</display:table>

<spring:message code="dashboard.q9" var="q9b"/>
<h3><jstl:out value="${q9b}"/></h3>
<jstl:out value="${q9}"/>

<spring:message code="dashboard.q10" var="q10b"/>
<h3><jstl:out value="${q10b}"/></h3>
<jstl:out value="${q10}"/>


<spring:message code="dashboard.q11" var="q11b"/>
<h3><jstl:out value="${q11b}"/></h3>
<jstl:out value="${q11}"/>


<spring:message code="dashboard.q12" var="q12b"/>
<h3><jstl:out value="${q12b}"/></h3>
<jstl:out value="${q12}"/>

<spring:message code="dashboard.q13" var="q13b"/>
<h3><jstl:out value="${q13b}"/></h3>
<display:table name="q13" id="row" requestURI="http://localhost:8080/admin/dashboard.do" pagesize="5"
               class="displaytag">

    <spring:message code="q13.name" var="nameheader"/>
    <display:column property="name" title="${nameheader}"/>

    <spring:message code="q13.surname" var="surnameheader"/>
    <display:column property="surname" title="${surnameheader}"/>

    <spring:message code="q13.EmailAddress" var="Emailheader"/>
    <display:column property="emailAddress" title="${Emailheader}"/>

    <spring:message code="q13.phone" var="phoneheader"/>
    <display:column property="phone" title="${phoneheader}"/>

</display:table>


<spring:message code="dashboard.q14" var="q14b"/>
<h3><jstl:out value="${q14b}"/></h3>
<display:table name="q14" id="row" requestURI="http://localhost:8080/admin/dashboard.do" pagesize="5"
               class="displaytag">

    <spring:message code="q13.name" var="nameheader"/>
    <display:column property="name" title="${nameheader}" sortable="true"/>

    <spring:message code="q13.surname" var="surnameheader"/>
    <display:column property="surname" title="${surnameheader}"/>

    <spring:message code="q13.EmailAddress" var="Emailheader"/>
    <display:column property="emailAddress" title="${Emailheader}"/>

    <spring:message code="q13.phone" var="phoneheader"/>
    <display:column property="phone" title="${phoneheader}"/>

</display:table>

<%--AQUI EMPIEZA A MOSTRARSE EL B--%>

<spring:message code="dashboard.q15" var="q15b"/>
<h3><jstl:out value="${q15b}"/></h3>
<jstl:out value="${q15}"/>

<spring:message code="dashboard.q16" var="q16b"/>
<h3><jstl:out value="${q16b}"/></h3>
<jstl:out value="${q16}"/>


<spring:message code="dashboard.q17" var="q17b"/>
<h3><jstl:out value="${q17b}"/></h3>
<jstl:out value="${q17}"/>


<spring:message code="dashboard.q18" var="q18b"/>
<h3><jstl:out value="${q18b}"/></h3>
<jstl:out value="${q18}"/>


<spring:message code="dashboard.q19" var="q19b"/>
<h3><jstl:out value="${q19b}"/></h3>
<jstl:out value="${q19}"/>


<spring:message code="dashboard.q20" var="q20b"/>
<h3><jstl:out value="${q20b}"/></h3>
<jstl:out value="${q20}"/>


<spring:message code="dashboard.q21" var="q21b"/>
<h3><jstl:out value="${q21b}"/></h3>
<jstl:out value="${q21}"/>


<spring:message code="dashboard.q22" var="q22b"/>
<h3><jstl:out value="${q22b}"/></h3>
<jstl:out value="${q22}"/>


<spring:message code="dashboard.q23" var="q23b"/>
<h3><jstl:out value="${q23b}"/></h3>
<jstl:out value="${q23}"/>
