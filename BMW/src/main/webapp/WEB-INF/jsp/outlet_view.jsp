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
        <title>DQI-Outlet</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!-- App favicon -->
        <link rel="shortcut icon" href="<%=UI_PATH %>assets/images/lugma_favicon.png">
		
		<link href="<%=UI_PATH %>plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <!-- DataTables --> 
        <link href="<%=UI_PATH %>plugins/datatables/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH %>plugins/datatables/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css" />
         <!-- Responsive datatable examples -->
        <link href="<%=UI_PATH %>plugins/datatables/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        
        <!-- Custom box css -->
        <link href="<%=UI_PATH %>plugins/custombox/css/custombox.min.css" rel="stylesheet">

        <!-- App css -->
        <link href="<%=UI_PATH %>assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH %>assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH %>assets/css/metismenu.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH %>assets/css/style.css" rel="stylesheet" type="text/css" />

        <script src="<%=UI_PATH %>assets/js/modernizr.min.js"></script>
		<link rel="stylesheet" href="<%=UI_PATH %>css/nice-select.css" type="text/css" />
		
		<style>
		#userType{
		position: relative;
    	left: 20px;
		}
		#icon{
		position: relative;
	    top: 31px;
	    font-size: 20px;
		}
		</style>
		
    </head>

    <body>
    <div id="wrapper">
        <!-- Navigation Bar-->
        <jsp:include page="include/header.jsp"></jsp:include>
         <jsp:include page="include/sidemenu.jsp"></jsp:include>
        <!-- End Navigation Bar-->

      <div class="content-page">
                <!-- Start content -->
                <div class="content">
                    <div class="container-fluid">

                        <div class="row">
                    <div class="col-sm-12">
                        <div class="page-title-box">
                            <div class="btn-group pull-right">
                                <ol class="breadcrumb hide-phone p-0 m-0">
                                    <li class="breadcrumb-item"><a href="#">DQI</a></li>
                                    <li class="breadcrumb-item"><a href="#">Outlets</a></li>
                                </ol>
                            </div>
                            <h4 class="page-title">View Outlets</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                
                <%-- <div class="col-lg-4">

                        <div class="card-box ">
                            <h4 class="header-title m-t-0">  </h4>
                            <form method="POST" action="<%=dashboardURL %>offers_add" enctype="multipart/form-data" autocomplete="off">
                               <div class="row">
                               <div class="form-group col-md-10" id="userType">
                                    <label for="pass1">User Type<span class="text-danger">*</span></label>
                                    <select id="user_type_id" name="user_type_id" required class="form-control" >
                                    <option value="">Select user type</option>
                                    <c:forEach var="uBean" items="${userTypeList}">
                                    	<option value="${uBean.user_type_id }">${uBean.user_type }</option>
                                    </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-md-2" id="icon">
                                    <label for="Icon"><span class="text-danger"></span></label>
                                    <span class="logo-large"><i class="mdi mdi-account-plus" title="add user type" data-toggle="modal" data-target="#login-modal"></i> </span>
                                </div>
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="pass1">Outlets<span class="text-danger">*</span></label>
                                    <select id="outlets" name="outlets" class="select2 form-control select2-multiple" required multiple="multiple" multiple data-placeholder="Choose ...">
                                     <option value="Outlet 1">Outlet 1</option>
                                     <option value="Outlet 2">Outlet 2</option>
                                     <option value="Outlet 3">Outlet 3</option>
                                </select>
                                </div>
                              
                                <div class="form-group col-md-12">
                                    <label for="Address">First Name<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="first_name" name="first_name" placeholder="Enter first name">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="Address">Last Name<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="last_name" name="last_name" placeholder="Enter last name">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="Address">Email<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="email" name="email" placeholder="Enter email">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="Address">Mobile<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="mobile" name="mobile" placeholder="Enter mobile">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="Address">Password<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="password" name="password" placeholder="Enter password">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="pass1">Role<span class="text-danger">*</span></label>
                                    <select id="role_id" name="role_id" required class="form-control" >
                                    <option value="">Select role</option>
                                    </select>
                                </div>
                                
                                 
                                <div class="form-group text-right m-b-0">
                                    <button class="btn btn-gradient waves-effect waves-light" type="submit">
                                        Submit
                                    </button>
                                    <button type="reset" class="btn btn-light waves-effect m-l-5" >
                                        Reset
                                    </button>
                                    
                                </div>

                            </form>
                        </div> <!-- end card-box -->
                    </div> --%>
                    
                    <div class="col-md-12">
                        <div class="card-box table-responsive">
                            <h4 class="m-t-0 header-title"><b> </b></h4>
                           

                            <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                 <th>Dealership</th>
                                 	<th>Brand</th>
                                    <th>Outlet Name</th>
                                    <th>Contact Person</th>
                                    <th>Outlet Size</th>
                                    <th>Outlet Type</th>
                                    <th>Mobile</th>
                                    <th>Email</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="uBean" items="${outletList}">
                                <tr>
                                 <td>${uBean.dealership_name }</td>
                                 <td>${uBean.brand }</td>
                                    <td>${uBean.outlets }</td>
                                      <td>${uBean.contact_person }</td>
                                      <td>${uBean.outlet_size }</td>
                                      <td>${uBean.outlet_type }</td>
                                      <td>${uBean.mobile }</td>
                                      <td>${uBean.email }</td>                                        
                                    <td><a href="<%=dashboardURL%>editOutlet/${uBean.outlet_id}" class="fa fa-edit"></a>&nbsp;&nbsp;&nbsp;<a href="<%=dashboardURL%>deleteOutlet/${uBean.outlet_id}" class="fa fa-trash" onclick="return confirm('Are you sure you want to delete this item?');"></a>&nbsp;&nbsp;&nbsp;<a href="<%=dashboardURL%>addOutletImage/${uBean.outlet_id}" class="fa fa-file-image-o"></a></td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- end row -->

            </div> <!-- end container -->
        </div>
        </div></div>
       
        <!-- end wrapper -->


        <!-- Footer -->
        <jsp:include page="include/footer.jsp"></jsp:include>
        <!-- End Footer -->


        <!--  jQuery  -->
        <script src="<%=UI_PATH %>assets/js/jquery.min.js"></script>
        <script src="<%=UI_PATH %>assets/js/popper.min.js"></script>
        <script src="<%=UI_PATH %>assets/js/bootstrap.min.js"></script>
        <script src="<%=UI_PATH %>assets/js/metisMenu.min.js"></script>
        <script src="<%=UI_PATH %>assets/js/waves.js"></script>
        <script src="<%=UI_PATH %>assets/js/jquery.slimscroll.js"></script>
         <!-- Modal-Effect -->
        <script src="<%=UI_PATH %>plugins/custombox/js/custombox.min.js"></script>
        <script src="<%=UI_PATH %>plugins/custombox/js/legacy.min.js"></script>
        
		 <!-- plugin Js -->
        <script src="<%=UI_PATH %>plugins/switchery/switchery.min.js"></script>
        <script src="<%=UI_PATH %>plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js"></script>
        <script src="<%=UI_PATH %>plugins/select2/js/select2.min.js" type="text/javascript"></script>
        <script src="<%=UI_PATH %>plugins/bootstrap-select/js/bootstrap-select.js" type="text/javascript"></script>
        <!--  Required datatable js -->
        <script src="<%=UI_PATH %>plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="<%=UI_PATH %>plugins/datatables/dataTables.bootstrap4.min.js"></script>
        <!-- Buttons examples -->
        <script src="<%=UI_PATH %>plugins/datatables/dataTables.buttons.min.js"></script>
        <script src="<%=UI_PATH %>plugins/datatables/buttons.bootstrap4.min.js"></script>
        <script src="<%=UI_PATH %>plugins/datatables/jszip.min.js"></script>
        <script src="<%=UI_PATH %>plugins/datatables/pdfmake.min.js"></script>
        <script src="<%=UI_PATH %>plugins/datatables/vfs_fonts.js"></script>
        <script src="<%=UI_PATH %>plugins/datatables/buttons.html5.min.js"></script>
        <script src="<%=UI_PATH %>plugins/datatables/buttons.print.min.js"></script>
        <!-- Responsive examples -->
        <script src="<%=UI_PATH %>plugins/datatables/dataTables.responsive.min.js"></script>
        <script src="<%=UI_PATH %>plugins/datatables/responsive.bootstrap4.min.js"></script>

        <!--  App js -->
        <script src="<%=UI_PATH %>assets/js/jquery.core.js"></script>
        <script src="<%=UI_PATH %>assets/js/jquery.app.js"></script>
        <script src="<%=UI_PATH %>js/script.js"></script>
          
        <!-- Init Js file -->
        <script type="text/javascript" src="<%=UI_PATH %>assets/pages/jquery.form-advanced.init.js"></script>
        <!-- Parsley js -->
        <script type="text/javascript" src="<%=UI_PATH %>plugins/parsleyjs/parsley.min.js"></script>

        <script type="text/javascript">
            $(document).ready(function() {
                $('#datatable').DataTable();

                //Buttons examples
                var table = $('#datatable-buttons').DataTable({
                    lengthChange: false,
                    buttons: ['copy', 'excel', 'pdf']
                });

                table.buttons().container()
                        .appendTo('#datatable-buttons_wrapper .col-md-6:eq(0)');
            } );

        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('form').parsley();
            });
        </script>
        
      
</body>
</html>