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
        <title>DQI-Users</title>
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
                                    <li class="breadcrumb-item"><a href="#">MYS</a></li>
                                    <li class="breadcrumb-item"><a href="#">View MYS Visit</a></li>
                                </ol>
                            </div>
                            <h4 class="page-title">View MYS Visit</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
              
                    <div class="col-md-12">
                        <div class="card-box table-responsive">
                            <h4 class="m-t-0 header-title"><b> </b></h4>
                           

                            <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Contact Number</th>
                                    <th>Quarter</th>
                                    <th>Year</th>
                                    <th>Mode of Contact</th>
                                    <th>Brand</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="uBean" items="${mysUserList}">
                                <tr>
                                    <td>${uBean.first_name }</td>
                                      <td>${uBean.email }</td>  
                                      <td>${uBean.mobile }</td>    
                                      <td>${uBean.quarter }</td>
                                      <td>${uBean.year }</td>
                                      <td>${uBean.mode_of_contact }</td>
                                      <td>${uBean.brand }</td>                                  
                                    <td><a href="<%=dashboardURL%>editMysteryShopper/${uBean.user_id}" class="fa fa-edit"></a>&nbsp;&nbsp;&nbsp;<a href="<%=dashboardURL%>deleteMysteryShopper/${uBean.user_id}" class="fa fa-trash" onclick="return confirm('Are you sure you want to delete this item?');"></a></td>
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
        <!-- Signup modal content -->
                            <div id="login-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="custom-width-modalLabel" aria-hidden="true" style="display: none;">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-body">
                                            <h4 class="text-uppercase text-center m-b-25">
                                                User Type
                                            </h4>

                                            <form class="form-horizontal" action="<%=dashboardURL %>user_type" method="POST">

                                                <div class="form-group m-b-25">
                                                    <div class="col-12">
                                                        <label for="emailaddress1">User Type</label>
                                                        <input class="form-control" type="text" id="user_type" name="user_type" required="" placeholder="Enter user type">
                                                    </div>
                                                </div>

                                                <div class="form-group account-btn text-center m-t-10">
                                                    <div class="col-12">
                                                        <button class="btn w-lg btn-rounded btn-custom waves-effect waves-light" type="submit">Submit</button>
                                                    </div>
                                                </div>

                                            </form>

                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->
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