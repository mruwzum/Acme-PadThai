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
<br>
<spring:message code="dashboard.q1" var="q1b"/>
<jstl:out value="${q1b}"/>:
<jstl:out value="${q1}"/>
<br/>
<br>
<spring:message code="dashboard.q2" var="q2b"/>
<jstl:out value="${q2b}"/>:
<jstl:out value="${q2}"/>
<br>
<br/>
<spring:message code="dashboard.q3" var="q3b"/>
<jstl:out value="${q3b}"/>:
<jstl:out value="${q3}"/>
<br>
<br/>
<spring:message code="dashboard.q4" var="q4b"/>
<jstl:out value="${q4b}"/>:
<jstl:out value="${q4}"/>
<br>
<br/>
<spring:message code="dashboard.q5" var="q5b"/>
<jstl:out value="${q5b}"/>:
<jstl:out value="${q5}"/>
<br>
<br/>
<spring:message code="dashboard.q6" var="q6b"/>
<jstl:out value="${q6b}"/>:
<jstl:out value="${q6}"/>
<br>
<br/>
<spring:message code="dashboard.q7" var="q7b"/>
<jstl:out value="${q7b}"/>:
<jstl:out value="${q7}"/>
<br>
<br/>
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
<br>
<br/>
<spring:message code="dashboard.q9" var="q9b"/>
<jstl:out value="${q9b}"/>:
<jstl:out value="${q9}"/>
    <br>
    <br/>
<spring:message code="dashboard.q10" var="q10b"/>
<jstl:out value="${q10b}"/>:
<jstl:out value="${q10}"/>
<br>
<br/>

<spring:message code="dashboard.q11" var="q11b"/>
<jstl:out value="${q11b}"/>:
<jstl:out value="${q11}"/>
    <br>
    <br/>

<spring:message code="dashboard.q12" var="q12b"/>
<jstl:out value="${q12b}"/>:
<jstl:out value="${q12}"/>
    <br>
    <br/>
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
<br>
<br/>
<%--AQUI EMPIEZA A MOSTRARSE EL B--%>

<spring:message code="dashboard.q15" var="q15b"/>
<jstl:out value="${q15b}"/>:
<jstl:out value="${q15}"/>
<br>
<br/>
<spring:message code="dashboard.q16" var="q16b"/>
<jstl:out value="${q16b}"/>:
<jstl:out value="${q16}"/>

<br>
<br/>
<spring:message code="dashboard.q17" var="q17b"/>
<jstl:out value="${q17b}"/>:
<jstl:out value="${q17}"/>
<br>
<br/>

<spring:message code="dashboard.q18" var="q18b"/>
<jstl:out value="${q18b}"/>:
<jstl:out value="${q18}"/>
<br>
<br/>

<spring:message code="dashboard.q19" var="q19b"/>
<jstl:out value="${q19b}"/>:
<jstl:out value="${q19}"/>
<br>
<br/>

<spring:message code="dashboard.q20" var="q20b"/>
<jstl:out value="${q20b}"/>:
<jstl:out value="${q20}"/>

<br>
<br/>
<spring:message code="dashboard.q21" var="q21b"/>
<jstl:out value="${q21b}"/>:
<jstl:out value="${q21}"/>

<br>
<br/>
<spring:message code="dashboard.q22" var="q22b"/>
<jstl:out value="${q22b}"/>:
<jstl:out value="${q22}"/>

<br>
<br/>
<spring:message code="dashboard.q23" var="q23b"/>
<jstl:out value="${q23b}"/>:
<jstl:out value="${q23}"/>
<br>
<br/>

<spring:message code="dashboard.q24" var="q24b"/>
<jstl:out value="${q24b}"/>:
<jstl:out value="${q24}"/>
<br>
<br/>

<spring:message code="dashboard.q25" var="q25b"/>
<jstl:out value="${q25b}"/>:
<jstl:out value="${q25}"/>
<br>
<br/>

<spring:message code="dashboard.q26" var="q26b"/>
<jstl:out value="${q26b}"/>:
<jstl:out value="${q26}"/>
<br>
<br/>



<spring:message code="dashboard.q27" var="q27b"/>
<jstl:out value="${q27b}"/>:
<jstl:out value="${q27}"/>
<br>
<br/>
<spring:message code="dashboard.q28" var="q28b"/>
<jstl:out value="${q28b}"/>:
<jstl:out value="${q28}"/>
<br>
<br/>
<spring:message code="dashboard.q29" var="q29b"/>
<jstl:out value="${q29b}"/>:
<jstl:out value="${q29}"/>
<br>
<br/>



<spring:message code="dashboard.q30" var="q30b"/>
<h3><jstl:out value="${q30b}"/></h3>
<display:table name="q30" id="row" requestURI="http://localhost:8080/admin/dashboard.do" pagesize="5"
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
<br>
<br/>
<spring:message code="dashboard.q31" var="q31b"/>
<jstl:out value="${q31b}"/>:
<jstl:out value="${q31}"/>
<br>
<br/>