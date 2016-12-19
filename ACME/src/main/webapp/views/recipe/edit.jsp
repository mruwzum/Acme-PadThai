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


<%--<jsp:useBean id="recipe" scope="request" type="domain.Recipe"/>--%>

    <security:authorize access="hasRole('USER')">


        <form:form action="recipes/user/edit/save.do" modelAttribute="recipe">
    <form:hidden path="id"/>
    <form:hidden path="version"/>
    <form:hidden path="user"/>



    <form:label path="title">
        <spring:message code="recipe.tittle"/>
    </form:label>
    <form:input path="title"/>
    <form:errors cssClass="error" path="title"/>
    <br/>

    <form:label path="summary">
        <spring:message code="recipe.summary"/>
    </form:label>
    <form:input path="summary"/>
    <form:errors cssClass="error" path="summary"/>
    <br/>

    <form:label path="creationDate">
        <spring:message code="recipe.creationDate"/>
    </form:label>
    <form:input path="creationDate"/>
    <form:errors cssClass="error" path="creationDate"/>
    <br/>

    <form:label path="updateDate">
        <spring:message code="recipe.updateDate"/>
    </form:label>
    <form:input path="updateDate"/>
    <form:errors cssClass="error" path="updateDate"/>
    <br/>

            <%--TODO ponerlo como desplegable--%>

            <form:label path="pictures">
                <spring:message code="recipe.pictures" />
            </form:label>

            <form:select path="pictures">

                <jstl:forEach var="pictures" items="${pictures}">
                    <jstl:out value="-----"/>
                    <form:option value="${recipe.id}"><jstl:out value="${contractor.name }" ></jstl:out> </form:option>
                    <br>
                </jstl:forEach>
            </form:select>
            <form:errors cssClass="error" path="pictures" />
            <br />
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
