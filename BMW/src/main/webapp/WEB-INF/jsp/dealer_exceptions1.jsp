<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ page import = "java.util.ResourceBundle" %> 
    
<%
ResourceBundle resource =  ResourceBundle.getBundle("resources/web");
String UI_PATH=resource.getString("UiPath");
System.out.println("ui path-"+UI_PATH);
String title=resource.getString("dashboardTitle");
String dashboardURL=resource.getString("dashboardURL");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>DQI-Audit</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!-- App favicon -->
        <link rel="shortcut icon" href="<%=UI_PATH %>assets/images/lugma_favicon.png">
		
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
		
		
		<style>
		.pic-label{
		width: 100px;
		height: 100px;
		
		position:relative;
		left:0;top:0;
		overflow:hidden;
		}
		img{
		width: 119px;
		height: 100px;
		}
		</style>
    </head>

    <body>
    <div id="wrapper">

                <div class="row">
                
               
                    <div class="col-md-12">
                        <div class="card-box table-responsive">
                            <h4 class="m-t-0 header-title"><b><center> ${header_text }</center> </b></h4>
                           
							<form method="post" action="">
                            <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                               <thead>
                                <tr>
                                    <th>Standard Number</th>
                                    <th>Standard Name</th>
                                    <th>Outlet</th>
                                   <th>Requirement</th>
                                     <th>Observation</th>
                                    <th>Actionable</th>
                                    <th>Timeline</th>
                                    <th>Dealer Comments</th>
                                    <th>Image</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                             
                                <c:forEach var="hBean" items="${questionnaireList}" varStatus="status">
                                <tr class="dd-comments">
                              <input type="hidden" value="${hBean.audit_id }" id="aid" class="aid">
                                 <input type="hidden" value="${manju }" id="didd" class="didd">
                                   	  <td>${hBean.number }</td>
                                      <td>${hBean.standard }</td>
                                      <td>${hBean.outlets } - ${hBean.outlet_type }</td>
                                      <td>${hBean.requirement }</td> 
                                      <td>${hBean.auditor_comment }</td> 
                                      <td>${hBean.exception_remarks }</td>
                                      <td>${hBean.time }</td>
                                      <td><textarea name="dealer_comments"  rows="5" id="dealer_comments" onkeyup="updateCommnets(this.value,'${hBean.audit_id }');">${hBean.dealer_remarks }</textarea></td>
                                      <td>
                                      		
                                      		<label  class="pic-label" for="pic1">
                                      		<input  onchange="readPhoto(this,'${hBean.audit_id }')"  style="position:absolute;font-size:100px;left:0px;top:0px;opacity:0;"  type="file" class="files" name="files[]" accept="image/*" id="pic1">
                                      			<img id="imagesss" class="imagesss" src="<%=UI_PATH %>uploads/${hBean.dealer_image}" width="100" height="100">
                                      		</label>
                                      </td>
                                       <%-- <td> <c:forEach var="count" items="${ImageList.get(status.index)}"><a target="_blank" href="<%=UI_PATH %>uploads/questionnairy_images/2018/H2/${count.reference_image }"><img src="<%=UI_PATH %>uploads/questionnairy_images/2018/H2/${count.reference_image }"></a></c:forEach></td>    --%>  
                                        <td><input type="checkbox" class="check"  onclick="callme(${hBean.questionnaire_id },${hBean.audit_schedule_id });"></td>                                
                                </tr>
                                
                                </c:forEach>
                                </tbody>
                            </table>
                            
                          
                            <!-- <div class="form-group text-right m-b-0">
                                    <button class="btn btn-gradient waves-effect waves-light" type="submit">
                                        Update Comments
                                    </button>
                                   
                                </div> -->
                                </form>
                        </div>
                    </div>
                </div>
                <!-- end row -->

            </div> <!-- end container -->
       
                          
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
		<script>
		function callme(qid,asid)
		{
			var did = $(".didd").val();
			 $.ajax({
	    	        url: "<%=dashboardURL%>updatePmoExceptiontoYes",
	    	        type: "GET", 
	                data: { 'did': did,'qid':qid,'asid':asid},
	    	        success: function(response)
	    	                    {
	    	        	location.reload();            
	    	                    }
	    	        });
			
		}
        </script>
        <script type="text/javascript">
        function readPhoto(input,aid) {
   		
               if (input.files && input.files[0]) {
                   var reader = new FileReader();

                   reader.onload = function (e) {
                       $(input).parent().find('img').attr('src', e.target.result)
                           .width(100)
                           .height(100);
                       var data={};
                      data["s"]=e.target.result;
                    
                      data["m"]=aid;
                      
                      $.ajax({
              	        url: "<%=dashboardURL%>saveproductdetails",
              	        type: "POST", 
              	      contentType: 'application/json; charset=utf-8',
              	      dataType: "json",
                          data: JSON.stringify(data),
              	        success: function(response)
              	                    {
              	        		
              	                    }
              	        }); 
   						
                   };
   				
                   reader.readAsDataURL(input.files[0]);
               }
          
           }

        
            $(document).ready(function() {
                $('#datatable').DataTable();

                //Buttons examples
                var table = $('#datatable-buttons').DataTable({
                    lengthChange: false,
                    paging:false,
                    buttons: ['copy', 'excel', 'pdf']
                });

                table.buttons().container()
                        .appendTo('#datatable-buttons_wrapper .col-md-6:eq(0)');
            } );

        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('form').parsley();
                
                $('.pic-label input').change(function(e){
                
                	var file_name = $(this).val();
                	$(this).parent().find('img').attr('src',file_name);
                	
                })
            });
            function updateCommnets(comments,id)
            {
            	var id = id;
            	var comments = comments;
            /* 	alert(comments); */
            	var aid = $(".dd-comments").find(".aid").val();
            	
            	 $.ajax({
        	        url: "<%=dashboardURL%>updateDealerComments",
        	        type: "GET", 
                    data: { 'comments': comments,'id':id},
        	        success: function(response)
        	                    {
        	        	

        	                    }
        	        }); 
            }
            
            
        </script>
     <%--    <script>
        $(".files").on('change',function(){
        	alert($('#files').attr('src'))
        	var xhr = new XMLHttpRequest();
            var fd = new FormData();
            fd.append("multipartFile", $('.files').attr('src'));
          /*   var file = document.getElementById('files').files[1];
            alert(JSON.stringify(file)); */
            fd.append("multipartFile", file);
            fd.append("aid",$("#aid").val());
            
/*             xhr.upload.addEventListener("progress", onUploadProgress, false);
            xhr.addEventListener("load", onUploadComplete, false);
            xhr.addEventListener("error", onUploadFailed, false); */ 
            xhr.open("GET", "<%=dashboardURL%>saveproduct");
           
            xhr.send(fd);
            alert();
        });
        
        </script> --%>
       </body>
</html>