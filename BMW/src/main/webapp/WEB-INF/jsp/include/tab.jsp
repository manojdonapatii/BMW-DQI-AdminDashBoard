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
<style>
		
		.panel{display:none;}
		
		.panel.sub-menu
		{
		   display:none;
    position: relative;
    left: 49px;
    top: 9px;
		}
		#buttons{ 
position: relative;
left: 1px;
}

.button-width{
width: 224px;
text-align: left;
position: relative;
padding: .75rem 1.25rem;
 margin-bottom: 1px;
    border-radius: 0px;
}
.text-aligning{
    margin-left: 10px;
}
 .vertical-div{
        padding: 9px!important;
} 
.vertical-div2{
            padding: 26px;
            float: left;
}
			</style>
<%-- <div class="col-lg-3 col-md-3 col-sm-3">
			<div class="card">
                      
                          <div class="vertical-div">
                          <button onclick="location.href='<%=dashboardURL%>emailSettings';" class="btn btn-light button-width active"><i class="icon-user"></i><span class="text-aligning">Email</span></button>
                          <button onclick="location.href='<%=dashboardURL%>smsSettings';" class="btn btn-light button-width"><i class="icon-user"></i><span class="text-aligning"> SMS</span></button>
                          <button onclick="location.href='<%=dashboardURL%>menu';" class="btn btn-light button-width"><i class="icon-camera"></i><span class="text-aligning">Menu Management</span></button>
                          <button onclick="location.href='<%=dashboardURL%>clientSettings';" class="btn btn-light button-width"><i class="icon-camera"></i><span class="text-aligning">Client Settings</span></button>
                         </div>
                    </div>
			</div> --%>