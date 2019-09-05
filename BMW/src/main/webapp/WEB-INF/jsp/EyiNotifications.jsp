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
        <title>DQI-Notifications</title>
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
                                    <li class="breadcrumb-item"><a href="#">Notifications</a></li>
                                     <li class="breadcrumb-item"><a href="#">Eyi</a></li>
                                </ol>
                            </div>
                            <h4 class="page-title">Eyi Notifications</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                  
<!-- tab menu-->
        <jsp:include page="include/tab.jsp"></jsp:include>
        <div class="col-lg-3 col-md-3 col-sm-3">
			<div class="card">
                      
                          <div class="vertical-div">
                          <button onclick="location.href='<%=dashboardURL%>dealerNotifications';" class="btn btn-light button-width "><i class="icon-user"></i><span class="text-aligning">Dealer Notifications</span></button>
                          <button onclick="location.href='<%=dashboardURL%>auditorNotifications';" class="btn btn-light button-width "><i class="icon-user"></i><span class="text-aligning">Auditor Notifications</span></button>
                          <button onclick="location.href='<%=dashboardURL%>regionalManagerNotifications';" class="btn btn-light button-width "><i class="icon-camera"></i><span class="text-aligning">Reg. Manager Notifications</span></button>
                          <button onclick="location.href='<%=dashboardURL%>clientNotifications';" class="btn btn-light button-width "><i class="icon-camera"></i><span class="text-aligning">Client Notifications</span></button>
                          <button onclick="location.href='<%=dashboardURL%>EyiNotifications';" class="btn btn-light button-width active"><i class="icon-camera"></i><span class="text-aligning">Eyi Notifications</span></button>
                         </div>
                    </div>
			</div>
        <!-- End tab menu-->
				<%-- <div class="col-lg-8 col-md-8 col-sm-8">                        
                       
                      <div class="card-box">
                       <div class="header">
                          <!--   <h6>Add Client</h6> -->
                            
                        </div>
                        <div class="body">
                        
                        <form id="basic-form" method="POST" action="<%=dashboardURL %>clientSettings" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                    <div class="form-group">
                                      <label for="Address">Client Name<span class="text-danger">*</span></label>
                                     <input type="text" required class="form-control" id="client_name" name="client_name" placeholder="Enter Client Name">
                                   
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                    <div class="form-group">
                                      <label for="Address">Clinet Logo<span class="text-danger">*</span></label>
                                    <input type="file" required class="form-control" id="file" name="files" >
                                </div>
                            </div>
                            
                            </div>
                            <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                    <div class="form-group">
                                        <label for="Address">Primary Mobile<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="mobile" name="mobile" placeholder="Enter Mobile ">
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                    <div class="form-group">
                                        <label for="Address">Primary Email<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="email" name="email" placeholder="Enter Email ">
                                </div>
                            </div>
                            </div>
                            
                            <div id="buttons" class="col-lg-12 col-md-12 col-sm-12">
                            <button type="submit" class="btn btn-primary">Save</button>
                            <button type="reset" class="btn btn-danger">Reset</button>
                            </div>
                        </form>
                        </div>
                    </div>
                </div> --%>

                        </div> <!-- end card-box -->
                    </div>
                    
                   
                </div>
                <!-- end row -->

            </div> <!-- end container -->
        </div>
        
       
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
            
            /* $(".checkbox").click(function() {            
                if ($(this).prop("checked")) {
                	 
                    $(".childrens input[type='checkbox']").each(function() {
                        $(this).attr("checked", "checked");
                    }); 
                }else{
                    $(".childrens input[type='checkbox']").each(function() {
                        $(this).removeAttr("checked");
                    });        
                }
            });  */
            $('.checkbox').on('change',function(){
            	if($(this).prop("checked") == true){
            	 $(this).parent().siblings('.panel.sub-menu').find('.checkbox-sub').each(function(){$(this).prop('checked',true);});

            	            }else{
            	 $(this).parent().siblings('.panel.sub-menu').find('.checkbox-sub').each(function(){$(this).prop('checked',false);});
            	            }
            	 
            	});
            
            $("button").click(function(){
                var favorite = [];
                $.each($("input[class='checkbox']:checked"), function(){            
                    favorite.push($(this).val());
                });
                $.each($("input[class='checkbox-sub']:checked"), function(){            
                    favorite.push($(this).val());
                });
                $("#menu_id").val(favorite.join(","));
               console.log(favorite.join(","));
            });

        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('form').parsley();
            });
        </script>
        
<script>
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
    acc[i].addEventListener("click", function() {
        this.classList.toggle("active");
        var panel = this.nextElementSibling;
        if (panel.style.display === "block") {
            panel.style.display = "none";
        } else {
            panel.style.display = "block";
        }
    });
}


</script>
      
</body>
</html>