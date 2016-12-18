<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 18/12/16
  Time: 19:01
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


<form:form action="actor/comment/write.do" modelAttribute="comment">
    <form:hidden path="id"/>
    <form:hidden path="version"/>



    <form:label path="title">
        <spring:message code="comment.tittle"/>
    </form:label>
    <form:input path="title"/>
    <form:errors cssClass="error" path="title"/>
    <br/>

    <form:label path="text">
        <spring:message code="comment.text"/>
    </form:label>
    <form:input path="text"/>
    <form:errors cssClass="error" path="text"/>
    <br/>


    <%--//TODO meter aqui recetas--%>
    <form:label path="numberOfStars">
        <spring:message code="comment.numberOfStars"/>
    </form:label>
    <form:input path="numberOfStars"/>
    <form:errors cssClass="error" path="numberOfStars"/>
    <br/>
    <input type="submit" name="save"
           value="<spring:message code="comment.send" />"/>&nbsp



</form:form>