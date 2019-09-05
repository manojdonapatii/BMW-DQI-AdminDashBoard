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
        <title>Abstack - Responsive Web App Kit</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!-- App favicon -->
        <link rel="shortcut icon" href="<%=UI_PATH %>MYS/assets/images/favicon.ico">
        
		<!-- DataTables -->
        <link href="<%=UI_PATH %>MYS/plugins/datatables/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH %>MYS/plugins/datatables/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <!-- Responsive datatable examples -->
        <link href="<%=UI_PATH %>MYS/plugins/datatables/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        
        <!-- App css -->
        <link href="<%=UI_PATH %>MYS/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH %>MYS/assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH %>MYS/assets/css/metismenu.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH %>MYS/assets/css/style.css" rel="stylesheet" type="text/css" />
		<style>
		    .remove_field{
		    height: 35px;
		    }
		    #formul .remove_field{
		    height:30px; width:25px;
		    }
		</style>
        <script src="<%=UI_PATH %>MYS/assets/js/modernizr.min.js"></script>

    </head>


    <body>

        <!-- Begin page -->
        <div id="wrapper">

             <!-- Top Bar Start -->
            <jsp:include page="include/header.jsp"></jsp:include>
            <jsp:include page="include/sidemenu.jsp"></jsp:include>
            <!-- Left Sidebar End -->



            <!-- ============================================================== -->
            <!-- Start right Content here -->
            <!-- ============================================================== -->
            <div class="content-page">
                <!-- Start content -->
                <div class="content">
                    <div class="container-fluid">

                        <div class="row">
                            <div class="col-12">
                                <div class="page-title-box">
                                    <h4 class="page-title float-left">MYS Sections/Sub Sections</h4>

                                    <ol class="breadcrumb float-right">
                                        <li class="breadcrumb-item"><a href="#">DQI</a></li>
                                        <li class="breadcrumb-item"><a href="#">Settings</a></li>
                                        <li class="breadcrumb-item active">MYS Sections/Sub Sections</li>
                                    </ol>

                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </div>
                        <!-- end row -->


                        <div class="row">
                            <div class="col-md-6">
                                <div class="card-box">
                                    <h4 class="m-t-0 m-b-30 header-title">Add Section</h4>

                                   <form method="POST" action="<%=dashboardURL %>MYS_addSection">
                                        <div class="form-group">
                                            <label for="section">Section</label>
                                            <input type="text" name="section"  class="form-control" id="section" placeholder="Enter Section">
                                        </div>
                                        
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                    </form>
                                </div>
                            </div> 
                            <div class="col-6">
                                <div class="card-box table-responsive">
                                    <h4 class="m-t-0 header-title"><b>View Questions</b></h4>
                                    <!-- <p class="text-muted font-14 m-b-30">
                                        The Buttons extension for DataTables provides a common set of options, API methods and styling to display buttons on a page that will interact with a DataTable. The core library provides the based framework upon which plug-ins can built.
                                    </p> -->

                                    <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                        <thead>
                                        <tr>
                                            <th>Section Name</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>


                                        <tbody>
                                        <c:forEach var="qBean" items="${sectionList}">
                                        <tr>
                                            <td>${qBean.section}</td>
                                            <td><a href="#" data-toggle="modal" data-target=".bs-example-modal-sm" onclick="callme(${qBean.sectionId})">edit</a> &nbsp; <a href="<%=dashboardURL %>MYS_deleteSection/${qBean.sectionId}" >Delete</a></td>
                                        </tr>
                                         </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                           
                        </div>
                        <!-- end row -->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="card-box">
                                    <h4 class="m-t-0 m-b-30 header-title">Add SubSection</h4>

                                   <form method="POST" action="<%=dashboardURL %>MYS_addSubSection">
                                        <div class="form-group">
                                            <label for="section">Sub Section</label>
                                            <select name="sectionId" class="form-control" id="sectionId" >
                                        				<option value="">Select</option> 
		                                            	<c:forEach var="qBean" items="${sectionList}">
		                                            	<option value="${qBean.sectionId}">${qBean.section}</option> 
		                                            	</c:forEach>
		                                            	
		                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="section">Sub Section</label>
                                            <input type="text" name="subSection"  class="form-control" id="subSection" placeholder="Enter Section">
                                        </div>
                                        
                                        
                                        
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                    </form>
                                </div>
                            </div> 
                            <div class="col-6">
                                <div class="card-box table-responsive">
                                    <h4 class="m-t-0 header-title"><b>View Questions</b></h4>
                                    <!-- <p class="text-muted font-14 m-b-30">
                                        The Buttons extension for DataTables provides a common set of options, API methods and styling to display buttons on a page that will interact with a DataTable. The core library provides the based framework upon which plug-ins can built.
                                    </p> -->

                                    <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                        <thead>
                                        <tr>
                                        	<th>Section Name</th>
                                            <th>Sub Section Name</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>


                                        <tbody>
                                        <c:forEach var="qBean" items="${subsectionList}">
                                        <tr>
                                            <td>${qBean.section}</td>
                                            <td>${qBean.subSection}</td> 
                                            <td><a href="#" data-toggle="modal" data-target="#myModal" onclick="callme1(${qBean.subSectionId})">edit</a> &nbsp; <a href="<%=dashboardURL %>MYS_deleteSubSection/${qBean.subSectionId}" >Delete</a></td>
                                        </tr>
                                         </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                           
                        </div>
                        <!-- end row -->
                         <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" style="display: none;">
                                        <div class="modal-dialog modal-sm">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                    <h5 class="modal-title" id="mySmallModalLabel">Edit Section</h5>
                                                </div>
                                                <div class="modal-body">
                                                  <form method="POST" action="<%=dashboardURL %>MYS_updateSection">
                                        <div class="form-group">
                                            <label for="section">Section</label>
                                            <input type="text" name="section"  class="form-control" id="section1" placeholder="Enter Section">
                                            <input type="hidden" name="sectionId"  class="form-control" id="sectionid1">
                                        </div>
                                        
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                    </form>
                                                </div>
                                            </div><!-- /.modal-content -->
                                        </div><!-- /.modal-dialog -->
                                    </div><!-- /.modal -->
                                    
                                    <!-- sample modal content -->
                                    <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                    <h5 class="modal-title" id="myModalLabel">Update Sub Section</h5>
                                                </div>
                                                <div class="modal-body">
                                                <form method="POST" action="<%=dashboardURL %>MYS_updateSubSection">
			                                        <div class="form-group">
			                                            <label for="section">Sub Section</label>
			                                            <select name="sectionId" class="form-control" id="sectionId2" >
			                                        				<option value="">Select</option> 
					                                            	<c:forEach var="qBean" items="${sectionList}">
					                                            	<option value="${qBean.sectionId}">${qBean.section}</option> 
					                                            	</c:forEach>
					                                            	
					                                            </select>
			                                        </div>
			                                        <div class="form-group">
			                                            <label for="section">Sub Section</label>
			                                            <input type="text" name="subSection"  class="form-control" id="subSection2" placeholder="Enter Section">
			                                            <input type="hidden" name="subSectionId"  class="form-control" id="subSectionId2" placeholder="Enter Section">
			                                        </div>
                                        
                                        
                                        
			                                        <button type="submit" class="btn btn-primary">Submit</button>
			                                    </form>
                                                </div>
                                            </div><!-- /.modal-content -->
                                        </div><!-- /.modal-dialog -->
                                    </div><!-- /.modal -->
                        

                    </div> <!-- container -->

                </div> <!-- content -->

                <footer class="footer text-right">
                    2017 - 2018 Â© Abstack. - Coderthemes.com
                </footer>

            </div>


            <!-- ============================================================== -->
            <!-- End Right content here -->
            <!-- ============================================================== -->


        </div>
        <!-- END wrapper -->



        <!-- jQuery  -->
        <script src="<%=UI_PATH %>MYS/assets/js/jquery.min.js"></script>
        <script src="<%=UI_PATH %>MYS/assets/js/popper.min.js"></script>
        <script src="<%=UI_PATH %>MYS/assets/js/bootstrap.min.js"></script>
        <script src="<%=UI_PATH %>MYS/assets/js/metisMenu.min.js"></script>
        <script src="<%=UI_PATH %>MYS/assets/js/waves.js"></script>
        <script src="<%=UI_PATH %>MYS/assets/js/jquery.slimscroll.js"></script>

        <!-- Parsley js -->
        <script type="text/javascript" src="<%=UI_PATH %>MYS/plugins/parsleyjs/parsley.min.js"></script>

        <!-- App js -->
        <script src="<%=UI_PATH %>MYS/assets/js/jquery.core.js"></script>
        <script src="<%=UI_PATH %>MYS/assets/js/jquery.app.js"></script>
        
        <!-- Required datatable js -->
        <script src="<%=UI_PATH %>MYS/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="<%=UI_PATH %>MYS/plugins/datatables/dataTables.bootstrap4.min.js"></script>
        <!-- Buttons examples -->
        <script src="<%=UI_PATH %>MYS/plugins/datatables/dataTables.buttons.min.js"></script>
        <script src="<%=UI_PATH %>MYS/plugins/datatables/buttons.bootstrap4.min.js"></script>
        <script src="<%=UI_PATH %>MYS/plugins/datatables/jszip.min.js"></script>
        <script src="<%=UI_PATH %>MYS/plugins/datatables/pdfmake.min.js"></script>
        <script src="<%=UI_PATH %>MYS/plugins/datatables/vfs_fonts.js"></script>
        <script src="<%=UI_PATH %>MYS/plugins/datatables/buttons.html5.min.js"></script>
        <script src="<%=UI_PATH %>MYS/plugins/datatables/buttons.print.min.js"></script>
        <!-- Responsive examples -->
        <script src="<%=UI_PATH %>MYS/plugins/datatables/dataTables.responsive.min.js"></script>
        <script src="<%=UI_PATH %>MYS/plugins/datatables/responsive.bootstrap4.min.js"></script>

        <script type="text/javascript">
            $(document).ready(function() {
                $('form').parsley();
            });
        </script>
  <script>
        function callme(sectionId)
        {
        	
        	$.ajax({
                url: "<%=dashboardURL %>getSectionById",
                type: "GET", 
                data: {'sectionId': sectionId},
                success: function(response)
                            {
                	$('#section1').val(response.section);
                	$('#sectionid1').val(response.sectionId);
                   }
              });
        }
        </script>
        
        <script>
        function callme1(subSectionId)
        {
        	
        	$.ajax({
                url: "<%=dashboardURL %>getSubSectionById",
                type: "GET", 
                data: {'subSectionId': subSectionId},
                success: function(response)
                            {
                	//alert(response.SubSection);
                	$('#sectionId2').val(response.sectionId);
                	$('#subSection2').val(response.subSection);
                	$('#subSectionId2').val(response.subSectionId);
                   }
              });
        }
        </script>
        
    </body>
</html>