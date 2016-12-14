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


<display:table name="mclass" id="row" requestURI="http://localhost:8080/cook/masterClass/list.do" pagesize="5"
               class="displaytag">

    <spring:message code="mclass.title" var="titleheader"/>
    <display:column property="title" title="${titleheader}"/>

    <spring:message code="mclass.description" var="descheader"/>
    <display:column property="description" title="${descheader}"/>

    <spring:message code="mclass.cook" var="cookheader"/>
    <display:column property="cook" title="${cookheader}"/>


    <!--Solo los usuarios registrados para la clase pueden ver los materiales-->
    <display:column>
        <jstl:if test="${registered}">
            <a href="/materials/list.do?masterClass=${row.id}">
                <spring:message code="materials.list"/>
            </a>
        </jstl:if>
    </display:column>

    <!--Los cocineros solo pueden editar si se trata de clases propias-->
    <security:authorize access="hasRole('COOK')">
        <display:column>
            <jstl:choose>
                <jstl:when test="${cookMasterClass.contains(row)}">
                    <a href="cook/masterClass/edit.do?id=${row.id}"
                       onclick="return confirm(' <spring:message code="mclass.edit"/>')">
                        <spring:message code="mclass.edit"/>
                    </a>
                </jstl:when>
                <jstl:otherwise>
                    <a href="/security/login.do">
                        <spring:message code="mclass.login"/>
                    </a>
                </jstl:otherwise>
            </jstl:choose>
        </display:column>
    </security:authorize>

    <!--Registros a las clases-->

    <security:authorize access="hasRole('USER')">
        <display:column>
            <jstl:choose>
                <jstl:when test="${registered}">
                    <a href="mclass/user/unregister.do?mclassId=${row.id}"
                       onclick="return confirm('<spring:message code="mclass.confirm.unregister"/>')">
                        <spring:message code="mclass.unregister"/>
                    </a>
                </jstl:when>
                <jstl:otherwise>
                    <a href="mclass/user/register.do?mclassId=${row.id}">
                        <spring:message code="mclass.register"/>
                    </a>
                </jstl:otherwise>
            </jstl:choose>
        </display:column>
        <spring:message code="mclass.material" var="matheader"/>
    </security:authorize>

    <security:authorize access="hasRole('NUTRITIONIST')">
        <display:column>
            <jstl:choose>
                <jstl:when test="${registered}">
                    <a href="mclass/nutritionist/unregister.do?mclassId=${row.id}"
                       onclick="return confirm('<spring:message code="mclass.confirm.unregister"/>')">
                        <spring:message code="mclass.unregister"/>
                    </a>
                </jstl:when>
                <jstl:otherwise>
                    <a href="mclass/nutritionist/register.do?mclassId=${row.id}">
                        <spring:message code="mclass.register"/>
                    </a>
                </jstl:otherwise>
            </jstl:choose>
        </display:column>
        <spring:message code="mclass.material" var="matheader"/>
    </security:authorize>

    <security:authorize access="hasRole('SPONSOR')">
        <display:column>
            <jstl:choose>
                <jstl:when test="${registered}">
                    <a href="mclass/sponsor/unregister.do?mclassId=${row.id}"
                       onclick="return confirm('<spring:message code="mclass.confirm.unregister"/>')">
                        <spring:message code="mclass.unregister"/>
                    </a>
                </jstl:when>
                <jstl:otherwise>
                    <a href="mclass/sponsor/register.do?mclassId=${row.id}">
                        <spring:message code="mclass.register"/>
                    </a>
                </jstl:otherwise>
            </jstl:choose>
        </display:column>
        <spring:message code="mclass.material" var="matheader"/>
    </security:authorize>


</display:table>
