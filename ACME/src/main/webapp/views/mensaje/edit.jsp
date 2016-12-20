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


<form:form action="actor/mensaje/send.do" modelAttribute="mensaje3">

    <form:hidden path="id"/>
    <form:hidden path="version"/>
    <%--<form:hidden path="sender"/>--%>
    <%--<form:hidden path="sentDate"/>--%>
    <%--<form:hidden path="folder"/>--%>


    <form:label path="recipient">
        <spring:message code="m.recipient"/>
    </form:label>
    <form:input path="recipient"/>
    <form:errors cssClass="error" path="recipient"/>
    <br/>

    <form:label path="subject">
        <spring:message code="m.subject"/>
    </form:label>
    <form:input path="subject"/>
    <form:errors cssClass="error" path="subject"/>
    <br/>

    <form:label path="body">
        <spring:message code="m.body"/>
    </form:label>
    <form:input path="body"/>
    <form:errors cssClass="error" path="body"/>
    <br/>

    <form:label path="priority">
        <spring:message code="m.priority" />
    </form:label>
    <form:select path="priority">
        <form:options/>
    </form:select>


    <input type="submit" name="send"
           value="<spring:message code="m.send" />"/>


    <input type="button" name="cancel"
           value="<spring:message code="m.cancel" />"
           onclick="relativeRedir('/actor/folder/list.do');"/>
    <br/>

    <%--<spring:url value="actor/mensaje/send2.do" var="url">--%>

        <%--<spring:param name="recipient" value="${recipient}"/>--%>
        <%--<spring:param name="subject" value="${subject}"/>--%>
        <%--<spring:param name="body" value="${body}"/>--%>
        <%--<spring:param name="priority" value="${priority}"/>--%>


    <%--</spring:url>--%>

    <%--<input type="button" name="cancel"--%>
           <%--value="<spring:message code="m.send" />"--%>
           <%--onclick="relativeRedir('<jstl:out value="${url}"/>');"/>--%>
    <%--<br/>--%>
</form:form>