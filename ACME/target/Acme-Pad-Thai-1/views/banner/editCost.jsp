<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 25/11/16
  Time: 16:07
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
        <a href="banner/editCost.do?bannerID=${row.id}">
            <spring:message code="mclass.edit"/>
        </a>
    </display:column>
</security:authorize>


<form:form action="banner/editCost.do" modelAttribute="banner">
    <form:hidden path="id"/>
    <form:hidden path="version"/>

    <form:label path="Banner Cost">
        <spring:message code="banner.bannerCost"/>
    </form:label>
    <form:input path="bannerCost"/>
    <form:errors cssClass="error" path="Banner Cost"/>
    <br/>

    <input type="submit" name="save"
           value="<spring:message code="banner.save" />"/>&nbsp;
    <jstl:if test="${banner.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="banner.delete" />"
               onclick="return confirm('<spring:message code="banner.confirm.delete"/>')"/>&nbsp;
    </jstl:if>
    <input type="button" name="cancel"
           value="<spring:message code="banner.cancel" />"
           onclick="relativeRedir('banner/editCost.do');"/>
    <br/>

</form:form>