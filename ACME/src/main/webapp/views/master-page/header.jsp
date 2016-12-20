<%--
 * header.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div>
    <img src="images/logo.png" alt="Acme-Pad-Thai, Inc."/>
</div>

<div>
    <ul id="jMenu">
        <!-- Do not forget the "fNiv" class for the first level links !! -->
        <security:authorize access="hasRole('ADMIN')">
            <li><a class="fNiv"><spring:message code="master.page.administrator"/></a>
                <ul>
                    <li class="arrow"></li>
                    <li><a href="admin/computewinners.do"><spring:message
                            code="master.page.computewinners"/></a></li>
                    <li><a href="cook/signup.do"><spring:message code="master.page.createascook"/></a></li>
                    <li><a href="admin/key/create.do"><spring:message
                            code="master.page.admin.createKey.do"/></a></li>
                    <li><a href="actor/AdminPersonalData.do"><spring:message
                            code="master.page.actor.edit.do"/></a></li>
                </ul>
            </li>

            <li><a href="admin/categorie/list.do"><spring:message
                    code="master.page.admin.categorieList.do"/></a></li>
            <li><a href="admin/contest/list.do"><spring:message
                    code="master.page.admin.contestList.do"/></a></li>
            <li><a href="admin/campaing/listAll.do"><spring:message
                    code="master.page.campaign"/></a></li>
            <li><a href="admin/masterClass/list.do"><spring:message
                    code="master.page.maclass.list.do"/></a></li>

            <li><a href="admin/dashboard.do"><spring:message
                    code="master.page.dashboard.do"/></a></li>
        </security:authorize>


        <security:authorize access="hasRole('NUTRITIONIST')">
            <li><a class="fNiv"><spring:message code="master.page.nutritionist"/></a>
                <ul>
                    <li class="arrow"></li>
                    <li><a href="actor/NutritionistPersonalData.do"><spring:message
                            code="master.page.actor.edit.do"/></a></li>
                    <li><a href="curricula/edit.do"><spring:message
                            code="master.page.curricula.edit.do"/></a></li>
                    <li><a href="cook/masterClass/list.do"><spring:message
                            code="master.page.maclass.list.do"/></a></li>
                </ul>
            </li>
            <li><a href="nutritionist/ingredient/list.do"><spring:message
                    code="master.page.ingredient"/></a></li>
            <li><a href="nutritionist/property/list.do"><spring:message
                    code="master.page.property"/></a></li>
            <li><a href="nutritionist/curricula/edit.do"><spring:message
                    code="master.page.curricula.edit.do"/></a></li>
            <li><a href="actor/folder/list.do"><spring:message
                    code="master.page.actor.messages"/></a></li>
            <li><a href="actor/NutritionistPersonalData.do"><spring:message
                    code="master.page.actor.edit.do"/></a></li>
            <li><a href="actor/comment/write.do"><spring:message
                    code="master.page.write.comment.do"/></a></li>
            <li><a href="user/recipes/stream.do"><spring:message
                    code="master.page.stream.recipe.do"/></a></li>
        </security:authorize>

        <security:authorize access="hasRole('USER')">
            <li><a class="fNiv"><spring:message code="master.page.recipes"/></a>
                <ul>
                    <li class="arrow"></li>
                    <li><a href="user/recipes/list.do"><spring:message
                            code="master.page.recipes.my.list.do"/></a></li>
                    <li><a href="user/recipes/list/my.do"><spring:message
                            code="master.page.recipes.list.do"/></a></li>
                </ul>
            </li>

            <li><a href="actor/folder/list.do"><spring:message
                    code="master.page.actor.messages"/></a></li>
            <li><a href="anonymus/others.do"><spring:message code="master.page.users"/></a></li>
            <li><a href="actor/userPersonalData.do"><spring:message
                    code="master.page.actor.edit.do"/></a></li>
            <li><a href="user/recipes/stream.do"><spring:message
                    code="master.page.stream.recipe.do"/></a></li>
        </security:authorize>


        <security:authorize access="hasRole('COOK')">
            <li><a class="fNiv"><spring:message code="master.page.cook"/></a>
                <ul>
                    <li class="arrow"></li>
                    <li><a href="actor/CookPersonalData.do"><spring:message
                            code="master.page.actor.edit.do"/></a></li>
                    <li><a href="cook/masterClass/list.do"><spring:message
                            code="master.page.maclass.list.do"/></a></li>
                </ul>
            </li>
            <li><a href="actor/folder/list.do"><spring:message
                    code="master.page.actor.messages"/></a></li>
            <li><a href="actor/CookPersonalData.do"><spring:message
                    code="master.page.actor.edit.do"/></a></li>
        </security:authorize>

        <security:authorize access="hasRole('SPONSOR')">
            <li><a class="fNiv"><spring:message code="master.page.campaign"/></a>
                <ul>
                    <li class="arrow"></li>
                    <li><a href="sponsor/campaing/list.do"><spring:message
                            code="master.page.campaing.list.do"/></a></li>
                    <li><a href="sponsor/campaing/create.do"><spring:message
                            code="master.page.campaing.create"/></a></li>

                </ul>
            </li>
            <li><a class="fNiv" href="sponsor/creditcard/edit.do"><spring:message
                    code="master.page.sponsor.editCC"/></a></li>
            <li><a class="fNiv" href="sponsor/monthlyBillunpaid/list.do"><spring:message
                    code="master.page.monthlybill.list"/></a></li>
            <li><a href="actor/folder/list.do"><spring:message
                    code="master.page.actor.messages"/></a></li>
            <li><a href="actor/SponsorPersonalData.do"><spring:message
                    code="master.page.actor.edit.do"/></a></li>
        </security:authorize>

        <security:authorize access="isAnonymous()">
            <li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login"/></a></li>

            <li><a class="fNiv"><spring:message code="master.page.signup"/></a>
                <ul>
                    <li class="arrow"></li>
                    <li><a href="user/signup.do"><spring:message code="master.page.asuser"/></a></li>
                    <li><a href="nutritionist/signup.do"><spring:message code="master.page.asnutritionist"/></a></li>
                    <li><a href="sponsor/signup.do"><spring:message code="master.page.assponsor"/></a></li>

                </ul>
            </li>
            <li><a class="fNiv" href="anonymus/recipes.do"><spring:message code="master.page.recipes"/></a></li>
            <li><a class="fNiv" href="anonymus/searchRecipe.do"><spring:message code="master.page.seachRecipe"/></a>
            </li>
            <li><a class="fNiv" href="anonymus/users.do"><spring:message code="master.page.users"/></a></li>
            <li><a class="fNiv" href="anonymus/searchUser.do"><spring:message code="master.page.seachUser"/></a></li>
            <li><a class="fNiv" href="anonymus/contest.do"><spring:message code="master.page.contest"/></a></li>
            <li><a class="fNiv" href="anonymus/masterclass.do"><spring:message code="master.page.masterClasses"/></a></li>
        </security:authorize>


        <security:authorize access="isAuthenticated()">
            <li>
                <a class="fNiv">
                    <spring:message code="master.page.profile"/>
                    (<security:authentication property="principal.username"/>)
                </a>
                <ul>
                    <li class="arrow"></li>
                    <li><a href="j_spring_security_logout"><spring:message code="master.page.logout"/> </a></li>
                </ul>
            </li>
        </security:authorize>
    </ul>
</div>

<div>
    <a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

