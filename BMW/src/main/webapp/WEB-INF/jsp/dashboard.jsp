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
<!DOCTYPE html>
<html>
    <head>
    <%String user_id= (String)request.getSession().getAttribute("user_id");
  		  if(user_id==null){response.sendRedirect(dashboardURL);}%>
        <meta charset="utf-8" />
        <title>Lugma-Dashboard</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!-- App favicon -->
        <link rel="shortcut icon" href="<%=UI_PATH %>assets/images/lugma_favicon.png">

        <!--Morris Chart CSS -->
        <link rel="stylesheet" href="<%=UI_PATH %>plugins/morris/morris.css">

        <!-- App css -->
        <link href="<%=UI_PATH %>assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH %>assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH %>assets/css/style.css" rel="stylesheet" type="text/css" />

        <script src="<%=UI_PATH %>assets/js/modernizr.min.js"></script>

    </head>

    <body>
    <%String admin_id= (String)request.getSession().getAttribute("first_name");
    if(admin_id==null){response.sendRedirect(dashboardURL);}%>

        <!-- Navigation Bar-->
        <jsp:include page="include/header.jsp"></jsp:include>
        <!-- End Navigation Bar-->


        <div class="wrapper">
            <div class="container-fluid">

                <!-- Page-Title -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="page-title-box">
                            <div class="btn-group pull-right">
                                <ol class="breadcrumb hide-phone p-0 m-0">
                                    <li class="breadcrumb-item"><a href="#">DQI</a></li>
                                    <li class="breadcrumb-item active">Dashboard</li>
                                </ol>
                            </div>
                            <h4 class="page-title">Welcome ${first_name}!</h4>
                        </div>
                    </div>
                </div>
                <!-- end page title end breadcrumb -->


                <div class="row" style="display:none">

                    <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>activeRestaurant_view';">
                        <div class="card-box tilebox-one">
                            <i class="fi-layers float-right"></i>
                            <h6 class="text-muted text-uppercase mb-3">Active Restaurants</h6>
                            <h4 id="active" class="mb-3" data-plugin="counterup">${getRestActiveCount}</h4>
                        </div>
                    </div>
                    
                     <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>inactiveRestaurants';">
                        <div class="card-box tilebox-one">
                            <i class="fi-box float-right"></i>
                            <h6 class="text-muted text-uppercase mb-3">InActive Restaurants</h6>
                            <h4 id="inactive" class="mb-3" data-plugin="counterup">${restInActiveCount}</h4>
                           <!--  <span class="badge badge-primary"> +11% </span> <span class="text-muted ml-2 vertical-middle">From previous period</span> -->
                        </div>
                    </div>

                   <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>pending_restaurants';">
                        <div class="card-box tilebox-one">
                            <i class="fi-tag float-right"></i>
                            <h6 class="text-muted text-uppercase mb-3">Pending Restaurants</h6>
                            <h4 id="pending" class="mb-3" data-plugin="counterup">${restPendingCount}</h4>
                        </div>
                    </div>

                    <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>rejected_restaurants';">
                        <div class="card-box tilebox-one">
                            <i class="fi-briefcase float-right"></i>
                            <h6 class="text-muted text-uppercase mb-3">Rejected Restaurants</h6>
                            <h4 id="reject" class="mb-3" data-plugin="counterup">${restRejectedCount}</h4>
                        </div>
                    </div>
                </div>
            <!-- ****************branches**************** -->
            <div class="row" style="display:none">

                    <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>active_branches_view';">
                        <div class="card-box tilebox-one">
                            <i class="fi-layers float-right"></i>
                            <h6 class="text-muted text-uppercase mb-3">Active Branches</h6>
                            <h4 id="b_active" class="mb-3" data-plugin="counterup">${getbranchActiveCount}</h4>
                        </div>
                    </div>
                    
                    <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>inactive_branches';">
                        <div class="card-box tilebox-one">
                            <i class="fi-box float-right"></i>
                            <h6 class="text-muted text-uppercase mb-3">InActive Branches</h6>
                            <h4 id="b_inactive" class="mb-3" data-plugin="counterup">${getInactiveBranchCount}</h4>
                           <!--  <span class="badge badge-primary"> +11% </span> <span class="text-muted ml-2 vertical-middle">From previous period</span> -->
                        </div>
                    </div>


                   <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>pending_branches';">
                        <div class="card-box tilebox-one">
                            <i class="fi-tag float-right"></i>
                            <h6 class="text-muted text-uppercase mb-3">Pending Branches</h6>
                            <h4 id="b_pending" class="mb-3" data-plugin="counterup">${branchPendingCount}</h4>
                        </div>
                    </div>

                    <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>rejected_branches';">
                        <div class="card-box tilebox-one">
                            <i class="fi-briefcase float-right"></i>
                            <h6 class="text-muted text-uppercase mb-3">Rejected Branches</h6>
                            <h4 id="b_reject" class="mb-3" data-plugin="counterup">${branchRejectedCount}</h4>
                        </div>
                    </div>
                </div>
                 <!-- ****************Business**************** -->
                  <div class="row">

					<div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>active_users';">
                        <div class="card-box tilebox-one">
                            <i class="fa fa-user-circle float-right"></i>
                            <h6 class="text-muted text-uppercase mb-4">Active Users</h6>
                            <h4 class="mb-3" data-plugin="counterup">${getactiveUserCount}</h4>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>inactive_users';">
                        <div class="card-box tilebox-one">
                            <i class="fa fa-user-o float-right"></i>
                            <h6 class="text-muted text-uppercase mb-4">InActive Users</h6>
                            <h4 class="mb-3" data-plugin="counterup">${getinActiveUserCount}</h4>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>business_restaurants';">
                        <div class="card-box tilebox-one">
                            <i class="fa fa-shopping-bag float-right"></i>
                            <h6 class="text-muted text-uppercase mb-4">Requested Business </h6>
                            <h4 class="mb-3" data-plugin="counterup">${getBusinessRestCount}</h4>
                        </div>
                    </div>
                   <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>reviews_report';">
                        <div class="card-box tilebox-one">
                            <i class="mdi mdi-account-edit float-right"></i>
                            <h6 class="text-muted text-uppercase mb-4">Reviews Report </h6>
                            <h4 class="mb-3" data-plugin="counterup">${reportCount}</h4>
                        </div>
                   </div> 
 			

			<%-- 		<div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>group_view';">
                        <div class="card-box tilebox-one">
                            <i class=" mdi mdi-account-multiple-outline float-right"></i>
                            <h6 class="text-muted text-uppercase mb-4">Total Groups</h6>
                            <h4 class="mb-3" data-plugin="counterup">${getgroupCount}</h4>
                        </div>
                    </div> --%>
</div>
             			<!-- ****************Restaurant Type**************** -->
           			 <div class="row" style="display:none">

					<div class="col-xs-12 col-md-6 col-lg-6 col-xl-4" onclick="location.href='<%=dashboardURL%>unclaimed_restaurants';">
                        <div class="card-box tilebox-one">
                            <i class="fi-layers float-right"></i>
                            <h6 class="text-muted text-uppercase mb-4">Unclaimed Restaurants</h6>
                            <h4 id="unclaimed" class="mb-4" data-plugin="counterup">${unclaimedCount}</h4>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 col-lg-6 col-xl-4" onclick="location.href='<%=dashboardURL%>claimed_restaurants';">
                        <div class="card-box tilebox-one">
                            <i class="fi-box float-right"></i>
                            <h6 class="text-muted text-uppercase mb-4">Claimed Restaurants</h6>
                            <h4 id="claimed"  class="mb-4" data-plugin="counterup">${claimedCount}</h4>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6 col-lg-6 col-xl-4" onclick="location.href='<%=dashboardURL%>premium_restaurants';">
                        <div class="card-box tilebox-one">
                            <i class="fi-tag float-right"></i>
                            <h6 class="text-muted text-uppercase mb-4">Premium Restaurants </h6>
                            <h4 id="premium" class="mb-4" data-plugin="counterup">${premiumCount}</h4>
                        </div>
                    </div>
 			</div>
 			       <div class="row" >
 						<div class="col-lg-4">
						
                        <div class="card-box" >
                        
                            <h4 class="header-title m-t-0">Restaurant Type</h4>
                        <button class="btn btn-outline-success btn-rounded waves-light waves-effect w-sm" onclick="location.href='<%=dashboardURL%>premium_restaurants';">Premium</button>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                        <button class="btn btn-outline-warning btn-rounded waves-light waves-effect w-sm" onclick="location.href='<%=dashboardURL%>claimed_restaurants';">Claimed</button>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                        <button class="btn btn-outline-danger btn-rounded waves-light waves-effect w-sm" onclick="location.href='<%=dashboardURL%>unclaimed_restaurants';">Unclaimed</button>
                        
                            <div id="morris-donut-example" style="height: 360px;" class="mt-4"></div>

                        </div>
                    </div>
                  
                <div class="row" style="display: none;">
                    <div class="col-lg-6">
                        <div class="card-box">
                            <h4 class="header-title">Stacked Bar Chart</h4>

                            <div id="morris-bar-stacked" style="height: 350px;" class="mt-4"></div>

                        </div>
                    </div>

                    <div class="col-lg-6">

                        <div class="card-box">
                            <h4 class="header-title">Area Chart</h4>

                            <div id="morris-area-example" style="height: 350px;" class="mt-4"></div>

                        </div>
                    </div>

                </div>
                <!-- end row -->


                <div class="row" style="display: none;">
                    <div class="col-lg-6">

                        <div class="card-box">
                            <h4 class="header-title">Line Chart</h4>

                            <div id="morris-line-example" style="height: 350px;" class="mt-4"></div>

                        </div> <!-- card-box -->

                    </div> <!-- end row -->

                    <div class="col-lg-6">
                        <div class="card-box">
                            <h4 class="header-title">Bar Chart</h4>

                            <div id="morris-bar-example" style="height: 350px;" class="mt-4"></div>

                        </div>
                    </div>
                </div>
                <!-- end row -->


                <div class="row" style="display: none;">

                    <div class="col-lg-6">

                        <div class="card-box">
                            <h4 class="header-title">Area Chart with Point</h4>

                            <div id="morris-area-with-dotted" style="height: 350px;" class="mt-4"></div>


                        </div>
                    </div>

                </div>
                <!-- end row -->
                    <div class="col-lg-4">
                        <div class="card-box">
                            <h4 class="header-title">Restaurants Details</h4>
                       <button class="btn btn-outline-success btn-rounded waves-light waves-effect w-sm" onclick="location.href='<%=dashboardURL%>active_restaurant_view';">Active Restaurants</button>&nbsp&nbsp&nbsp&nbsp&nbsp
                        <button class="btn btn-outline-danger btn-rounded waves-light waves-effect w-sm" onclick="location.href='<%=dashboardURL%>inactive_restaurants';">InActive Restaurnats</button>&nbsp&nbsp <br><br>
                        <button class="btn btn-outline-warning btn-rounded waves-light waves-effect w-sm" onclick="location.href='<%=dashboardURL%>pending_restaurants';">Pending Restaurants</button>&nbsp&nbsp
                        <button class="btn btn-outline-dark btn-rounded waves-light waves-effect w-sm" onclick="location.href='<%=dashboardURL%>rejected_restaurants';">Rejected Restaurants</button>
 
                            <canvas id="pie" height="300" class="mt-4"></canvas>

                        </div>
                    </div>
                    <div class="col-lg-4">

                        <div class="card-box">
                            <h4 class="header-title">Branches Details</h4>
                       <button class="btn btn-outline-success btn-rounded waves-light waves-effect w-sm" onclick="location.href='<%=dashboardURL%>active_branches_view';">Active Branches</button>&nbsp&nbsp&nbsp&nbsp&nbsp
                        <button class="btn btn-outline-danger btn-rounded waves-light waves-effect w-sm" onclick="location.href='<%=dashboardURL%>inactive_branches';">InActive Branches</button>&nbsp&nbsp <br><br>
                        <button class="btn btn-outline-warning btn-rounded waves-light waves-effect w-sm" onclick="location.href='<%=dashboardURL%>pending_branches';">Pending Branches</button>&nbsp&nbsp
                        <button class="btn btn-outline-dark btn-rounded waves-light waves-effect w-sm" onclick="location.href='<%=dashboardURL%>rejected_branches';">Rejected Branches</button>

                            <canvas id="doughnut" height="300" class="mt-4"></canvas>

                        </div>
                    </div>
			    <div class="row" style="display:none">
                    <div class="col-lg-6">
                        <div class="card-box">
                            <h4 class="header-title">Bar Chart</h4>

                            <canvas id="bar" height="350" class="mt-4"></canvas>
                        </div>
                    </div>

                    <div class="col-lg-6">
                        <div class="card-box">
                            <h4 class="header-title">Line Chart</h4>

                            <canvas id="lineChart" height="350" class="mt-4"></canvas>
                        </div>
                    </div>
                </div>
                <!-- end row -->

            </div> <!-- end container -->
            
        </div>
        </div>
        <!-- end wrapper -->


        <!-- Footer -->
        <footer class="footer">
          <jsp:include page="include/footer.jsp"></jsp:include>
        </footer>
        <!-- End Footer -->


        <!-- jQuery  -->
        <script src="<%=UI_PATH %>assets/js/jquery.min.js"></script>
        <script src="<%=UI_PATH %>assets/js/popper.min.js"></script>
        <script src="<%=UI_PATH %>assets/js/bootstrap.min.js"></script>
        <script src="<%=UI_PATH %>assets/js/waves.js"></script>
        <script src="<%=UI_PATH %>assets/js/jquery.slimscroll.js"></script>

        <!--Morris Chart-->
        <script src="<%=UI_PATH %>plugins/morris/morris.min.js"></script>
        <script src="<%=UI_PATH %>plugins/raphael/raphael-min.js"></script>
        <script src="<%=UI_PATH %>assets/pages/jquery.morris.init.js"></script>
        
          <!-- Chart JS -->
        <script src="<%=UI_PATH %>plugins/chart.js/chart.bundle.js"></script>
        <script src="<%=UI_PATH %>assets/pages/jquery.chartjs.init.js"></script>

        <!-- App js -->
        <script src="<%=UI_PATH %>assets/js/jquery.core.js"></script>
        <script src="<%=UI_PATH %>assets/js/jquery.app.js"></script>

    </body>
</html>