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
        <title>DQI-Questionnaire</title>
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
                                    <li class="breadcrumb-item"><a href="#">Questionnaire</a></li>
                                </ol>
                            </div>
                            <h4 class="page-title">View Questionnaires</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                
               
                    <div class="col-md-12">
                    
                    <input type="hidden" id="bname" value="${bname }">
					<input type="hidden" id="type" value="${type }">
                        <div class="card-box table-responsive">
                        <ul class="nav nav-tabs">
                                        <li class="nav-item bmw_sales">
                                            <a href="#home" data-toggle="tab" aria-expanded="false" class="nav-link">
                                                BMW Sales 
                                            </a>
                                        </li>
                                        <li class="nav-item bmw_service">
                                            <a href="#profile" data-toggle="tab" aria-expanded="true" class="nav-link">
                                                BMW Service
                                            </a>
                                        </li>
                                        <li class="nav-item mini_sales">
                                            <a href="#profile" data-toggle="tab" aria-expanded="true" class="nav-link">
                                                MINI Sales
                                            </a>
                                        </li>
                                        <li class="nav-item mini_service">
                                            <a href="#profile" data-toggle="tab" aria-expanded="true" class="nav-link">
                                               MINI Service
                                            </a>
                                        </li>
                                        
                                    </ul>
                            <h4 class="m-t-0 header-title"><b> </b></h4>
                           

                            <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                    <th>Section</th>
                                   <!--  <th>Brand</th> -->
                                   <!--  <th>Type</th> -->
                                    <th>Standard Name</th>
                                   <th>Standard No.</th> 
                                   <th>Image Mandate</th>
                                    <th>Requirement</th>
                                    <!-- <th>Type Of Standard</th> -->
                                    <th>Question</th>
                                    <th>Essential</th>
                                    <th>Reference Image</th>
                                    <!-- <th>Suggested Person</th> -->
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="qBean" items="${questionnaireList}">
                                <tr>
                                    <td>${qBean.section }</td>
                                  <%--   <td>${qBean.brand }</td>
                                    <td>${qBean.type }</td> --%>
                                      <td>${qBean.standard }</td>
                                      <td>${qBean.number }</td>
                                      <td>${qBean.image_manadatory }</td>
                                      <td >${qBean.requirement }</td>
                                      <%-- <td>${qBean.essential }</td> --%>
                                      <td >${qBean.question }</td>
                                      <td>${qBean.essential }</td>
                                      <td><img id="" src="<%=UI_PATH%>uploads/questionnary/${qBean.pic }" style="width: 69px;border-radius: 140px; height: 69px;"></td>
                                     <%--  <td>${qBean.person }</td>           --%>                              
                                    <td><a href="<%=dashboardURL%>editQuestionnaire/${qBean.questionnaire_id}" class="fa fa-edit"></a>&nbsp;&nbsp;&nbsp;<a href="<%=dashboardURL%>deleteQuestionnaire/${qBean.questionnaire_id}" class="fa fa-trash" onclick="return confirm('Are you sure you want to delete this item?');"></a></td>
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
        
        
        $(".nav-item.bmw_sales").click(function (){
         	location.replace("<%=dashboardURL %>viewQuestionnaire/BMW/Sales");          	
        });
        $(".nav-item.bmw_service").click(function (){
        	location.replace("<%=dashboardURL %>viewQuestionnaire/BMW/Service"); 
        });
        $(".nav-item.mini_sales").click(function (){
        	location.replace("<%=dashboardURL %>viewQuestionnaire/MINI/Sales"); 
        });
        $(".nav-item.mini_service").click(function (){
        	location.replace("<%=dashboardURL %>viewQuestionnaire/MINI/Service"); 
        });
        
        
        
        
            $(document).ready(function() {
                $('#datatable').DataTable();

                //Buttons examples
                var table = $('#datatable-buttons').DataTable({
                    lengthChange: false,
                    buttons: ['copy', 'excel', 'pdf']
                });

                table.buttons().container()
                        .appendTo('#datatable-buttons_wrapper .col-md-6:eq(0)');
                
                
                var brand= $("#bname").val();
                var type= $("#type").val();
                if(brand=="BMW" && type== "Sales")
                	{
                	$(".nav-item.bmw_sales").find(".nav-link").addClass("active");
                	}
                if(brand=="BMW" && type== "Service")
	            	{
	            	$(".nav-item.bmw_service").find(".nav-link").addClass("active");
	            	}
                if(brand=="MINI" && type== "Sales")
	            	{
	            	$(".nav-item.mini_sales").find(".nav-link").addClass("active");
	            	}
                if(brand=="MINI" && type== "Service")
	            	{
	            	$(".nav-item.mini_service").find(".nav-link").addClass("active");
	            	}
                
                
                
            } );

        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('form').parsley();
            });
        </script>
        
      
</body>
</html>