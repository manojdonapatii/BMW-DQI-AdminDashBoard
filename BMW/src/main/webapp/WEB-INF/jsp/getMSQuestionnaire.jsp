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
                            <h4 class="page-title">Questionnaire</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                <div class="col-md-12">
                                <div class="card-box">
                                   <table class="table">
									<tr><th>Dealer Name: </th><td> ${dname }</td><th> Outlet Name: </th><td> ${outlet }</td><th> Outlet Type: </th><td> ${outlet_type }</td><th>Outlet Size: </th><td> ${outlet_size }</td><th>Brand: </th><td> ${brand }</td></tr>
									
                                   </table>
                                </div>
                            </div>
                
                <div class="col-md-12">
                                <div class="card-box">
                                   

                                    <ul class="nav nav-tabs">
                                        <li class="nav-item all">
                                            <a href="#home" data-toggle="tab" aria-expanded="false" class="nav-link ">
                                                NSC (${sessionScope.nsc_count })
                                            </a>
                                        </li>
                                        <li class="nav-item ms">
                                            <a href="#ms" data-toggle="tab" aria-expanded="false" class="nav-link active">
                                                MS (${sessionScope.msquestions_count })
                                            </a>
                                        </li>
                                        <li class="nav-item audit submissions">
                                            <a href="#profile" data-toggle="tab" aria-expanded="true" class="nav-link ">
                                                Auditor Submissions  (${sessionScope.audit_count })
                                            </a>
                                        </li>
                                        <li class="nav-item exception">
                                            <a href="#profile" data-toggle="tab" aria-expanded="true" class="nav-link ">
                                                Exceptions (${sessionScope.exception_count })
                                            </a>
                                        </li>
                                        <li class="nav-item pmoclosure">
                                            <a href="#profile" data-toggle="tab" aria-expanded="true" class="nav-link ">
                                               PMO Closure  (${sessionScope.closure_count })
                                            </a>
                                        </li>
                                        <li class="nav-item review">
                                            <a href="#profile" data-toggle="tab" aria-expanded="true" class="nav-link ">
                                               Review 
                                            </a>
                                        </li>
                                        <li class="nav-item publish">
                                            <a href="#profile" data-toggle="tab" aria-expanded="true" class="nav-link ">
                                                Scoring
                                            </a>
                                        </li>
                                       
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane fade show active" id="home">

											<form method="post" action="<%=dashboardURL%>getQuestionnaire">
                        <div class=" table-responsive">
                            <h4 class="m-t-0 header-title"><b> </b></h4>
                           
							
                            <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                    <th>Section</th>
                                     <th>Standard No.</th>
                                    <th>Standard</th>
                                    <th>Requirement</th>
                                    <th>Essential</th>
                                    <th>Question</th>
                                    <th>Observation</th>
                                    <th>Suggested Person</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="qBean" items="${questionnaireList}">
                                <tr>
                                    <td>${qBean.section }</td>
                                      <td>${qBean.number }</td>
                                      <td>${qBean.standard }</td>
                                      <td>${qBean.requirement }</td>
                                      <td>${qBean.essential }</td>
                                      <td>${qBean.question }</td>
                                      <td>${qBean.observation }</td>
                                      <td>${qBean.person }</td>                                        
                                    <td><select name="answer"><option value="${ qBean.answer }">${ qBean.answer_type}</option><option value="1" selected>YES</option><option value="0">NO</option></select><a href="#custom-modal" class="audit-id"  data-animation="fadein" data-plugin="custommodal"
                               data-overlaySpeed="200" data-overlayColor="#36404a" data-id="${qBean.audit_id}">Action</a><br><%-- <a href="#custom-modal" class="audit-id"  data-animation="fadein" data-plugin="custommodal"
                               data-overlaySpeed="200" data-overlayColor="#36404a" data-id="${qBean.audit_id}">Raise Exception</a> --%></td>
                                   <input type="hidden" value="${qBean.questionnaire_id }" name="questionnaire_id">
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                          		    <input type="hidden" value="${dealer_id }" name="dealer_id">
                                    <input type="hidden" value="${outlet_id }" name="outlet_id">
                                    <input type="hidden" value="${user_id }" name="user_id">
                                    <input type="hidden" value="${audit_schedule_id }" name="audit_schedule_id">
                            <div class="form-group text-right m-b-0">
                                    <button class="btn btn-gradient waves-effect waves-light" type="submit">
                                        Submit
                                    </button>
                                    <button type="reset" class="btn btn-light waves-effect m-l-5" >
                                        Reset
                                    </button>
                           </div>
                        </div>
                        </form>                                            

                                        </div>
                                      
                                         <!-- EXCEPTIONS -->
                                        <div class="tab-pane fade" id="profile">
                                            
                                        </div>
                                        
                                         <!-- EXCEPTIONS -->
                                        
                                    </div>
                                </div>
                            </div> <!-- end col -->
               
                    
                </div>
                <!-- end row -->

            </div> <!-- end container -->
        </div>
        </div></div>
        <!-- Signup modal content -->
                            <div id="custom-modal" class="modal-demo">
            <button type="button" class="close" onclick="Custombox.close();">
                <span>&times;</span><span class="sr-only">Close</span>
            </button>
            <h4 class="custom-modal-title">Comment</h4>
            <div class="custom-modal-text">
            <form action="<%=dashboardURL %>updateComments" method="Post">
             <div class="row">
             <div class="col-lg-12">
             <div class="row">
             <div class="form-group col-md-6">
              <label>Result</label>
            <select id="results" required class="form-control" name="answer">
            <option value="1">YES</option>
            <option value="0">NO</option>
            </select>
            </div>
            <div class="form-group col-md-6">
              <label>Badge</label>
            <select id="badge" required class="form-control" name="badge">
            <option value="NO">NO</option>
            <option value="YES">YES</option>
            </select>
            </div>
          	<div class="form-group col-md-6">
              <label>PMO Review Status</label>
            <select id="review_status" required class="form-control" name="review_status">
            <option value="Open">Open</option>
            <option value="Close">Close</option>
            </select>
            </div>
             <div class="form-group col-md-6">
            <label>Comments</label>
               <textarea required="required" name="comment" id="comments" class="form-control"></textarea>
               </div></div></div></div>
               <input type="hidden" name="audit_id" id="oidd" value="">
              <div class="form-group text-right m-b-0">
                                    <button class="btn btn-gradient waves-effect waves-light" type="submit">
                                        Submit
                                    </button>
                                    
                               </div>
               </form>
            </div>
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
        
        $(".nav-item.exception").click(function (){
        	var initial_url = window.location.href; 
        	var url = initial_url .split( '/' );
        	var updated_url= url[ url.length - 3 ] + '/' + url[ url.length - 2 ] + '/' + url[ url.length - 1 ];
         	location.replace("<%=dashboardURL %>getExceptionQuestionnaires"+"/"+updated_url); 
        });
        $(".nav-item.all").click(function (){
        	var initial_url = window.location.href; 
        	var url = initial_url .split( '/' );
        	var updated_url= url[ url.length - 3 ] + '/' + url[ url.length - 2 ] + '/' + url[ url.length - 1 ];
         	location.replace("<%=dashboardURL %>getQuestionnaire"+"/"+updated_url); 
        });
        $(".nav-item.ms").click(function (){
        	var initial_url = window.location.href; 
        	var url = initial_url .split( '/' );
        	var updated_url= url[ url.length - 3 ] + '/' + url[ url.length - 2 ] + '/' + url[ url.length - 1 ];
         	location.replace("<%=dashboardURL %>getMSQuestionnaire"+"/"+updated_url); 
        });
        $(".nav-item.audit.submissions").click(function (){
        	var initial_url = window.location.href; 
        	var url = initial_url .split( '/' );
        	var updated_url= url[ url.length - 3 ] + '/' + url[ url.length - 2 ] + '/' + url[ url.length - 1 ];
         	location.replace("<%=dashboardURL %>auditorSubmissions"+"/"+updated_url); 
        });
        $(".nav-item.pmoclosure").click(function (){
        	var initial_url = window.location.href; 
        	var url = initial_url .split( '/' );
        	var updated_url= url[ url.length - 3 ] + '/' + url[ url.length - 2 ] + '/' + url[ url.length - 1 ];
         	location.replace("<%=dashboardURL %>getPmoClosure"+"/"+updated_url); 
        });
        $(".nav-item.review").click(function (){
        	var initial_url = window.location.href; 
        	var url = initial_url .split( '/' );
        	var updated_url= url[ url.length - 3 ] + '/' + url[ url.length - 2 ] + '/' + url[ url.length - 1 ];
         	location.replace("<%=dashboardURL %>review"+"/"+updated_url); 
        });
        $(".nav-item.publish").click(function (){
        	var initial_url = window.location.href; 
        	var url = initial_url .split( '/' );
        	var updated_url= url[ url.length - 3 ] + '/' + url[ url.length - 2 ] + '/' + url[ url.length - 1 ];
         	location.replace("<%=dashboardURL %>publish"+"/"+updated_url); 
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
            } );

        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('form').parsley();
            });
        </script>
        <script>
        $(".audit-id").on("click",function(){
        var audit_id=$(this).attr('data-id');
        
     	//$('#location_id').select('refresh');
    	$("#comments").empty();
    	$.ajaxSetup ({
    	    // Disable caching of AJAX responses
    	    cache: false
    	});
    	  $.ajax({
    	        url: "<%=dashboardURL%>getQuestionnaireOptionById",
    	        type: "GET", 
                data: { 'audit_id': audit_id },
    	        success: function(response)
    	                    {
    	        			var qList=JSON.stringify(response);
    	        			var jsonString=JSON.parse(qList);
        	        	   		for(var i=0;i<jsonString.length;i++)
        	        	 	    {
        	        	 	 	  /* $("#results").append("<option value='"+jsonString[i].answer+"'>"+jsonString[i].answer_type+"</option>"); */
    							  $("#comments").append(jsonString[i].comment);    
        	        	 	 	  $("#badge").append("<option selected='' value='"+jsonString[i].badge+"'>"+jsonString[i].badge+"</option>");
        	        	 	 	$("#review_status").append("<option selected='' value='"+jsonString[i].review_status+"'>"+jsonString[i].review_status+"</option>");
 	       	        	 	    }
    	                                       
    	                    }
    	        });
        
        
        $("#oidd").val(audit_id);
        });
        </script>
      
</body>
</html>