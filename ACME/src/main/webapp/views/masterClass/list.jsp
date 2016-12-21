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


<display:table name="masterClass" id="row" requestURI="http://localhost:8080/cook/masterClass/list.do" pagesize="5"
               class="displaytag">

    <spring:message code="mclass.title" var="titleheader"/>
    <display:column property="title" title="${titleheader}"/>

    <spring:message code="mclass.description" var="descheader"/>
    <display:column property="description" title="${descheader}"/>

    <spring:message code="mclass.cook" var="cookheader"/>
    <display:column property="cook" title="${cookheader}"/>


    <security:authorize access="hasRole('ADMIN')">

    <display:column>
        <a href="admin/masterClass/promote.do?mcID=${row.id}">
            <spring:message code="masterClass.promote"/>
        </a>
    </display:column>

        <display:column>
            <a href="admin/masterClass/demote.do?mcID=${row.id}">
                <spring:message code="masterClass.demote"/>
            </a>
        </display:column>

    </security:authorize>


    <!--Solo los usuarios registrados para la clase pueden ver los materiales-->
    <security:authorize access="isAuthenticated()">

    <display:column>
        <jstl:if test="${registered==true}">
            <a href="user/materials/list.do?masterClass=${row.id}">
                <spring:message code="materials.list"/>
            </a>
        </jstl:if>
    </display:column>

        <%--TODO Enlace a view--%>
    </security:authorize>


    <!--Los cocineros solo pueden editar si se trata de clases propias-->
    <security:authorize access="hasRole('COOK')">
        <display:column>
                    <a href="cook/masterClass/edit.do?id=${row.id}"
                       onclick="return confirm(' <spring:message code="mclass.edit"/>')">
                        <spring:message code="mclass.edit"/>
                    </a>
        </display:column>
    </security:authorize>

    <!--Registros a las clases-->

    <security:authorize access="isAuthenticated()">
        <display:column>
            <a href="admin/masterClass/attend.do?mcID=${row.id}">
                <spring:message code="masterClass.attend"/>
            </a>
        </display:column>

        <display:column>
            <a href="admin/masterClass/unattend.do?mcID=${row.id}">
                <spring:message code="masterClass.unattend"/>
            </a>
        </display:column>
    </security:authorize>

    <%--<security:authorize access="hasRole('NUTRITIONIST')">--%>

        <%--<display:column>--%>
            <%--<a href="admin/masterClass/attend.do?mcID=${row.id}">--%>
                <%--<spring:message code="masterClass.attend"/>--%>
            <%--</a>--%>
        <%--</display:column>--%>
    <%--</security:authorize>--%>

    <%--<security:authorize access="hasRole('SPONSOR')">--%>
        <%--<display:column>--%>
            <%--<a href="admin/masterClass/attendSpon.do?mcID=${row.id}">--%>
                <%--<spring:message code="masterClass.attend"/>--%>
            <%--</a>--%>
        <%--</display:column>--%>

    <%--</security:authorize>--%>


</display:table>
