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
                                    <li class="breadcrumb-item"><a href="#">Schedule MYS Visit</a></li>
                                </ol>
                            </div>
                            <h4 class="page-title">Schedule MYS Visit</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                
                <div class="col-lg-12">

                        <div class="card-box ">
                            <h4 class="header-title m-t-0">  </h4>
                            <form method="POST" action="<%=dashboardURL %>scheduleMysteryShopper" enctype="multipart/form-data" autocomplete="off">
                               <div class="row">
                              
                               <div class="form-group col-md-6">
                                    <label for="Address">Name<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="first_name" name="first_name" placeholder="Enter full name">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="Address">Age<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="age" name="age" placeholder="Enter age">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="Address">Email given to dealership<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="email" name="email" placeholder="Enter email">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="Address">Contact Number<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="mobile" name="mobile" placeholder="Enter contact number">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="pass1">Gender<span class="text-danger">*</span></label>
                                    <select id="gender" name="gender" required class="form-control" >
                                    <option value="">Select gender</option>
                                    <option value="Male">Male</option>
                                    <option value="FeMale">FeMale</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="pass1">Quarter<span class="text-danger">*</span></label>
                                    <select id="quarter" name="quarter" required class="form-control" >
                                    <option value="">Select quarter</option>
                                    <option value="Q1">Q1</option>
                                    <option value="Q2">Q2</option>
                                    <option value="Q3">Q3</option>
                                    <option value="Q4">Q4</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="pass1">Year<span class="text-danger">*</span></label>
                                    <select id="year" name="year" required class="form-control" >
                                    <option value="">Select year</option>
                                    <option value="2018">2018</option>
                                    <option value="2019">2019</option>
                                    <option value="2020">2020</option>
                                    <option value="2021">2021</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="pass1">Mode of Contact<span class="text-danger">*</span></label>
                                    <select id="mode_of_contact" name="mode_of_contact" required class="form-control" >
                                    <option value="">Select Mode of Contact</option>
                                  	<option value="E-Mail/Website">E-Mail/Website</option>
                                  	<option value="Telephonic">Telephonic</option>
                                  	<option value="Walkin">Walkin</option>
                                    </select>
                                </div>
                               <div class="form-group col-md-3">
                                    <label for="Address">Visit Date<span class="text-danger">*</span></label>
                                    <input type="text" id="visit_date" name="visit_date" placeholder="DD/MM/YYYY" required data-mask="99/99/9999" value="${uBean.audit_date }" class="form-control">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="pass1">Brand<span class="text-danger">*</span></label>
                                    <select id="brand" name="brand" required class="form-control" onchange="getModels(this.value);">
                                    <option value="">Select brand</option>
                                    <option value="BMW">BMW</option>
                                    <option value="MINI">MINI</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="pass1">Type<span class="text-danger">*</span></label>
                                    <select id="type" name="type" required class="form-control" onchange="getOutlets(this.value);">
                                    <option value="">Select type</option>
                                    <option value="Sales">Sales</option>
                                    <option value="Service">Service</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="pass1">Outlet<span class="text-danger">*</span></label>
                                    <select id="outlets" name="outlets" class="select2 form-control select2-multiple" required>
                                     <option value="">Select Outlet</option>
                                </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="pass1">Model Shopped<span class="text-danger">*</span></label>
                                    <select id="model" name="model" required class="select2 form-control select2-multiple" >
                                    <option value="">Select model</option>
                                  
                                    </select>
                                </div>
                                
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
                    </div>
                    
                 
                </div>
                <!-- end row -->
				</div>
				</div>
            </div> <!-- end container -->
        </div>
        
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
        <script>
        function getOutlets(type) 
        {
        	var brand=$("#brand").val();
        	var type=type;
        	$("#outlets").empty();
        	  $.ajax({
        	        url: "<%=dashboardURL%>getOutletsByBrand",
        	        type: "GET", 
                    data: { 'type': type,'brand':brand },
        	        success: function(response)
        	                    {
        	        			 var dealerList=JSON.stringify(response);
        	        			var jsonString=JSON.parse(dealerList);
	        	        	   		for(var i=0;i<jsonString.length;i++)
	        	        	 	    {
	        	        	 	 	  $("#outlets").append("<option value='"+jsonString[i].outlet_id+"'>"+jsonString[i].outlets+"</option>");
	        	        	 	    }           
        	                    }
        	        });
      
        	
        }
        function getModels(brand) 
        {
        	var brand=brand;
        	$("#model").empty();
        	  $.ajax({
        	        url: "<%=dashboardURL%>getModelsByBrand",
        	        type: "GET", 
                    data: { 'brand':brand },
        	        success: function(response)
        	                    {
        	        			 var dealerList=JSON.stringify(response);
        	        			 var jsonString=JSON.parse(dealerList);
	        	        	   		for(var i=0;i<jsonString.length;i++)
	        	        	 	    {
	        	        	 	 	  $("#model").append("<option value='"+jsonString[i].model_id+"'>"+jsonString[i].model+"</option>");
	        	        	 	    }           
        	                    }
        	        });
      
        	
        }
        </script>
        
      
</body>
</html>