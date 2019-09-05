<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ page import = "java.util.ResourceBundle" %> 
    
<%
ResourceBundle resource =  ResourceBundle.getBundle("resources/web");
String UI_PATH=resource.getString("UiPath");
String title=resource.getString("dashboardTitle");
String dashboardURL=resource.getString("dashboardURL");
%>

		<%String user_id= (String)request.getSession().getAttribute("user_id");
  		  if(user_id==null){response.sendRedirect(dashboardURL);}%>
<div class="left side-menu">
                <div class="slimscroll-menu" id="remove-scroll">
				<div id="sidebar-menu">
                        <!-- Left Menu Start -->
                        <ul class="metismenu" id="side-menu">
                            <li class="menu-title">Navigation</li>
                            <% int i=1; %>
							<c:forEach var="mBean" items="${sessionScope.mainMenuList}" varStatus="status">
							<% if(i==1){ %>
							<li>
                                <a href="<%=dashboardURL %>${mBean.menu_link}"><i class="fi-briefcase"></i> <span> ${mBean.menu_name} </span></a>
                            </li>
							<% }else{ %>
                            <li>
                                <a href="javascript: void(0);"><i class="${mBean.menu_icon}"></i> <span> ${mBean.menu_name} </span> <span class="menu-arrow"></span></a>
                                <ul class="nav-second-level" aria-expanded="false">
                                	<c:forEach var="count" items="${sessionScope.subMenuList.get(status.index)}">
                                    <li><a href="<%=dashboardURL %>${count.menu_link}">${count.menu_name}</a></li>
                                    </c:forEach>
                                </ul>
                            </li>
                            <% }i++; %>
							</c:forEach>
                        </ul>
                    </div>
                    </div>
                    </div>