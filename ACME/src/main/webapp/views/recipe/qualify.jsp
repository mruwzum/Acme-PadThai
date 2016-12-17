<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 14/12/16
  Time: 22:51
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


<%--<jsp:useBean id="recipe" scope="request" type="domain.Recipe"/>--%>

<security:authorize access="hasRole('USER')">

    <form:form action="/user/recipe/qualify.do" modelAttribute="recipe">
        <form:hidden path="id"/>
        <form:hidden path="version"/>

        <%--TODO: hacer que funcione el desplegable--%>
        <form:label path="rate">
            <spring:message code="recipe.rate"/>
        </form:label>
        <form:select path="rate">
            <jstl:forEach var="rate" items="${Boolean}">
                <jstl:out value="-----"/>
                <form:option value="${recipe.id}"><jstl:out value="${rate}"></jstl:out></form:option>
                <br>
            </jstl:forEach>
        </form:select>
        <form:errors cssClass="error" path="rate"/>


        <input type="submit" name="save"
               value="<spring:message code="actor.save" />"/>&nbsp;
        <jstl:if test="${id} != 0">
            <input type="submit" name="delete"
                   value="<spring:message code="actor.delete" />"
                   onclick="return confirm('<spring:message code="actor.confirm.delete"/>')"/>&nbsp;
        </jstl:if>
        <input type="button" name="cancel"
               value="<spring:message code="actor.cancel" />"
               onclick="relativeRedir('user/recipes/list.do');"/>
        <br/>

    </form:form>
</security:authorize>
