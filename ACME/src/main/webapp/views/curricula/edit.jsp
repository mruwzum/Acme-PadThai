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


<form:form action="curricula/edit.do" modelAttribute="curricula">

    <form:hidden path="id"/>
    <form:hidden path="version"/>

    <form:label path="Education Section">
        <spring:message code="curricula.education"/>
    </form:label>
    <form:input path="educationSection"/>
    <form:errors cssClass="error" path="Education Section"/>
    <br/>

    <form:label path="Experience Section">
        <spring:message code="curricula.experience"/>
    </form:label>
    <form:input path="experienceSection"/>
    <form:errors cssClass="error" path="Experience Section"/>
    <br/>

    <form:label path="Hobbies Section">
        <spring:message code="curricula.hobbies"/>
    </form:label>
    <form:input path="hobbiesSection"/>
    <form:errors cssClass="error" path="Hobbies Section"/>
    <br/>

    <form:label path="references">
        <spring:message code="curricula.education"/>
    </form:label>
    <form:input path="references"/>
    <form:errors cssClass="error" path="References"/>
    <br/>

    <form:label path="Photo">
        <spring:message code="curricula.photo"/>
    </form:label>
    <form:input path="photo"/>
    <form:errors cssClass="error" path="Photo"/>
    <br/>


    <input type="submit" name="save"
           value="<spring:message code="curricula.save" />"/>&nbsp;
    <jstl:if test="${curricula.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="curricula.delete" />"
               onclick="return confirm('<spring:message code="curricula.delete"/>')"/>&nbsp;
    </jstl:if>
    <input type="button" name="cancel"
           value="<spring:message code="curricula.cancel" />"
           onclick="relativeRedir('/curricula/list.do');"/>
    <br/>
</form:form>