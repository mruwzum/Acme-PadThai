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


<form:form action="cook/masterClass/edit/save.do" modelAttribute="masterClass">

    <form:hidden path="id"/>
    <form:hidden path="version"/>


    <%--TODO desplegables--%>
    <form:label path="title">
        <spring:message code="mclass.title"/>
    </form:label>
    <form:input path="title"/>
    <form:errors cssClass="error" path="title"/>
    <br/>

    <form:label path="description">
        <spring:message code="mclass.description"/>
    </form:label>
    <form:input path="description"/>
    <form:errors cssClass="error" path="description"/>
    <br/>
    <form:label path="cook">
        <spring:message code="mclass.cook"/>
    </form:label>
    <form:input path="cook"/>
    <form:errors cssClass="error" path="cook"/>
    <br/>

    <br>


    <form:label path="registers">
        <spring:message code="masterClass.registers" />
    </form:label>
    <form:select path="registers">
        <form:options items="${registers}" itemValue="id" />
    </form:select>
    <form:errors cssClass="error" path="registers"/>

    <br/>
    <br>
    <form:label path="promoters">
        <spring:message code="masterClass.promoters" />
    </form:label>
    <form:select path="promoters">
        <form:options items="${promoters}" itemValue="id" />
    </form:select>
    <form:errors cssClass="error" path="promoters"/>
    <br/>
    <br>

    <form:label path="material">
        <spring:message code="masterClass.material"/>
    </form:label>
    <form:select path="material">
        <form:options items="${material}" itemValue="id" />
    </form:select>
    <form:errors cssClass="error" path="material"/>

    <br/>
    <br>
    <br/>
    <input type="submit" name="save"
           value="<spring:message code="mclass.save" />"/>&nbsp;
    <jstl:if test="${id} != 0">
        <input type="submit" name="delete"
               value="<spring:message code="mclass.delete" />"
               onclick="return confirm('<spring:message code="mclass.confirm.delete"/>')"/>&nbsp;
    </jstl:if>
    <input type="button" name="cancel"
           value="<spring:message code="mclass.cancel" />"
           onclick="relativeRedir('cook/masterClass/list.do');"/>
    <br/>
</form:form>