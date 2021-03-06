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
                                    <li class="breadcrumb-item"><a href="#">Outlet</a></li>
                                </ol>
                            </div>
                            <h4 class="page-title">Create / Add Outlet</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                
                <div class="col-lg-12">

                        <div class="card-box ">
                            <h4 class="header-title m-t-0">  </h4>
                            <form method="POST" action="<%=dashboardURL %>editOutlet/${oid }" enctype="multipart/form-data" autocomplete="off">
                               <div class="row">
                              
                               <div class="form-group col-md-6" >
                                    <label for="pass1">Dealer Name<span class="text-danger">*</span></label>
                                    <select id="dealership_id" name="dealership_id" required class="form-control select2" >
                                   <option value="${did }">${dname }</option>
                                   <c:forEach var="uBean" items="${dealerList}">
                                    	<option value="${uBean.dealership_id }">${uBean.dealership_name }</option>
                                    </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="Address">Outlet Name<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="outlets" name="outlets" value="${outlets }" placeholder="Enter outlet name">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="Address">Outlet ID<span class="text-danger"></span></label>
                                    <input type="text"  class="form-control" id="oid" name="oid" value="${oids }" placeholder="Enter outlet ID">
                                </div>
                                <div class="form-group col-md-6">
                                   <label for="pass1">Brand<span class="text-danger">*</span></label>
                                    <select id="brand" name="brand" required class="form-control" >
                                    <option value="${brand }">${brand }</option>
                                    	<option value="BMW">BMW</option>
                                    	<option value="MINI">MINI</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                   <label for="pass1">Outlet Size<span class="text-danger">*</span></label>
                                    <select id="outlet_size" name="outlet_size" required class="form-control" >
                                    <option value="${outlet_size }">${outlet_size }</option>
                                    	<option value="XS">XS</option>
                                    	<option value="S">S</option>
                                    	<option value="M">M</option>
                                    	<option value="L">L</option>
                                    	<option value="XL">XL</option>
                                    	<option value="XXL">XXL</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                   <label for="pass1">Outlet Type<span class="text-danger">*</span></label>
                                    <select id="outlet_type" name="outlet_type" required class="form-control" >
                                    <option value="${outlet_type }">${outlet_type }</option>
                                    	<option value="Sales">Sales</option>
                                    	<option value="Service">Service</option>
                                    	<!-- <option value="Sales & Service">Sales & Service</option> -->
                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                   <label for="pass1">State<span class="text-danger">*</span></label>
                					  <select id="state" name="state" required class="form-control select2" onchange="getCity();" >
                                     <option value="${state_id }">${state }</option>
                                    <c:forEach var="uBean" items="${stateList}">
                                     <option value="${uBean.state_id }">${uBean.state_name }</option>
                                     </c:forEach>
                                     </select>   
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="pass1">City<span class="text-danger">*</span></label>
                                    <select id="city" name="city" required class="form-control select2" >
                                    <option value="${city_id }">${city }</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="pass1">Region<span class="text-danger">*</span></label>
                                    <select id="region" name="region" required class="form-control select2" >
                                    <option value="${region_id }">${region }</option>
                                    	<c:forEach var="uBean" items="${regionList}">
                                    	<option value="${uBean.region_id }">${uBean.region }</option>
                                    </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="Address">Latitude<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="lat" name="lat" placeholder="Enter latitude" value="${lat }">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="Address">Longitude<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="lang" name="lang" placeholder="Enter longitude" value="${lang }">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="Address">Contact Person<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="contact_person" name="contact_person" value="${contact_person }" placeholder="Enter contact person">
                                </div>
                                
                                <div class="form-group col-md-6">
                                    <label for="Address">Email<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="email" name="email" value="${email }" placeholder="Enter email">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="Address">Mobile<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="mobile" name="mobile" value="${mobile }" placeholder="Enter mobile">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="Address">Password<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="password" name="password" value="${password }" placeholder="Enter password">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="Address">Address<span class="text-danger">*</span></label>
                                    <textarea class="form-control"  name="address" id="address" required>${address }</textarea>
                                </div>
                                </div>
                                 
                                <div class="form-group text-right m-b-0">
                                    <button class="btn btn-gradient waves-effect waves-light" type="submit">
                                        Update
                                    </button>
                                    <a href="<%=dashboardURL%>viewOutlet"><button type="reset" class="btn btn-light waves-effect m-l-5" >
                                        Cancel
                                    </button></a>
                                    
                                </div>
							
                            </form>
                        </div> <!-- end card-box -->
                    </div>
                    
                    <%-- <div class="col-md-8">
                        <div class="card-box table-responsive">
                            <h4 class="m-t-0 header-title"><b> </b></h4>
                           

                            <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>User Type</th>
                                    <th>Role</th>
                                    <th>Mobile</th>
                                    <th>Email</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="cmsBean" items="${offersList}">
                                <tr>
                                    <td>${cmsBean.restaurant_name }</td>
                                      <td>${cmsBean.branch_name }</td>
                                      <td>${cmsBean.offer_name }</td>
                                        
                                    <td><a href="<%=dashboardURL%>offers_edit/${cmsBean.sk_offer_id}" class="fa fa-edit"></a>&nbsp;&nbsp;&nbsp;<a href="<%=dashboardURL%>offer_delete/${cmsBean.sk_offer_id}" class="fa fa-trash" onclick="return confirm('Are you sure you want to delete this item?');"></a></td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div> --%>
                </div>
                <!-- end row -->

            </div> <!-- end container -->
        </div></div></div>
        
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
        <script type="text/javascript">
            $(document).ready(function() {
                $('form').parsley();
            });
        </script>
        <script>
        function getCity() 
        { 
         
           $("#city option").remove();  
          var state_id=$("#state").val();
          
            $.ajax({
                  url: "<%=dashboardURL%>getCities",
                  type: "GET", 
                    data: { 'state_id': state_id },
                  success: function(response)
                              {
                        var CityList=JSON.stringify(response);
                        var jsonString=JSON.parse(CityList);
                             for(var i=0;i<jsonString.length;i++)
                             {
                              $("#city").append("<option value=''>select city</option><option value='"+jsonString[i].city_id+"'>"+jsonString[i].city_name+"</option>");
                             }
                                                 
                              }
                  });  
          
        }
        </script>
        
      
</body>
</html>