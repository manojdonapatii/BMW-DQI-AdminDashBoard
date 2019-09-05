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
        <title>DQI-Audits</title>
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
		
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		
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
		#audit_date{
		position: relative;
    	width: 185px;
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
                                    <li class="breadcrumb-item"><a href="#">Audit</a></li>
                                </ol>
                            </div>
                            <h4 class="page-title">Schedule Audits</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                
                <%-- <div class="col-lg-5">

                        <div class="card-box ">
                            <h4 class="header-title m-t-0">  </h4>
                            <form method="POST" id="form-audit" action="<%=dashboardURL %>sheduleAudit" enctype="multipart/form-data" autocomplete="off">
                               <div class="row">
                              
                               <div class="form-group col-md-10" >
                                    <label for="pass1">Dealer<span class="text-danger">*</span></label>
                                    <select id="dealership_id" name="dealership_id" required class="form-control" onchange="getOutlets(this.value)">
                                    <option value="${did }">${dname }</option>
                                    <c:forEach var="uBean" items="${dealerList}">
                                    	<option value="${uBean.dealership_id }">${uBean.dealership_name }</option>
                                    </c:forEach>
                                    </select>
                                </div>
                               
                                </div>
                               
                            </form>
                        </div> <!-- end card-box -->
                    </div> --%>
                    
                     <div class="col-md-12">
                        <div class="card-box">
                        <div class="row">
                        <div class="form-group col-md-6" >
                        <label>Dealer<span class="text-danger">*</span></label>
                             <select id="dealership_id" name="dealership_id" required class="form-control" >
                                    <option value="${did }">${dname }</option>
                                    <c:forEach var="uBean" items="${dealerList}">
                                    	<option value="${uBean.dealership_id }">${uBean.dealership_name }</option>
                                    </c:forEach>
                                    </select>
                                    </div>
                                  
                         <%-- <div class="form-group col-md-6" >
                        <label>Select Type<span class="text-danger">*</span></label>
                            <select id="outlet_type" name="" required class="form-control" >
                                    <option value="${tid }">${type }</option>
                                    <option value="Sales">Sales</option>
                                    <option value="Service">Services</option>
                                    </select>
                                    </div> --%>
                                    </div>
                                    <form method="POST" action="<%=dashboardURL %>sheduleAudit/${did }">
                                    <div class="row">
                                    <div class="form-group col-md-6" >
                                     <label>Phase<span class="text-danger">*</span></label>
                            <select id="quarter" required name="quarter" class="form-control">
                             <option value="${qidd }">${qid }</option>
                            <option value="H1">H1</option>
                            <option value="H2">H2</option>
                            </select>
                            </div>
                            <div class="form-group col-md-6" >
                             <label>Year<span class="text-danger">*</span></label>
                            <select id="year" required name="year" class="form-control" >
                             <option value="${years }">${year }</option>
                            <option value="2017">2017</option>
                            <option value="2018">2018</option>
                            <option value="2019">2019</option>
                            <option value="2020">2020</option>
                            </select>
                            </div>
                            
                             </div>
                            <div class="form-group text-right m-b-0">
                                    <button class="btn btn-gradient waves-effect waves-light" type="button" onclick="getOutlets();">
                                        Get Audits
                                    </button>
                                  
                                </div>
                            
                            
                           
                            <p class="text-muted font-14 m-b-20">
                                
                            </p>
							
                         <table class="table">
                                <thead>
                                <tr>
                                	<th>Brand</th>
                                	<th>Type</th>
                                    <th>Outlet Name</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Auditor</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%int i=1;%>
                                <c:forEach var="uBean" items="${outletList}">
                                <% i=i+1; %>
                                <tr>
                                <input type="hidden" name="outlet_type" id="otype" value="${uBean.outlet_type }">
                                <input type="hidden" name="brand" id="brand" value="${uBean.brand }">
                                	<td>${uBean.brand }</td>
                                	<td>${uBean.outlet_type }</td>
                                    <td>${uBean.outlets }</td>
                                    <td><input type="text"  id="audit_date<%=i %>" name="audit_date" placeholder="DD/MM/YYYY" required data-mask="99/99/9999" value="${uBean.audit_date }" class="form-control auditDate"></td>
                                  	<td><input type="text" id="end_date<%=i %>" name="end_date" placeholder="DD/MM/YYYY" required data-mask="99/99/9999" value="${uBean.end_date }" class="form-control date"></td>
                                  	 <input type="hidden" name="outlet_id" id="outlet_id" value="${uBean.outlet_id }">
                                    <input type="hidden" name="outlets" id="outlets" value="${uBean.outlets }">
                                  	<td><select class="form-control select2" name="user_id" name="user_id" required><option value="${uBean.auditor_id }">${uBean.first_name}</option><c:forEach var="uBean" items="${auditorList}"><option value="${uBean.user_id }">${uBean.first_name}</option></c:forEach></select></td>
                                </tr>
                                
                                </c:forEach>
                                <input type="hidden" name="count" id="count" value="<%=i %>"/>
                                </tbody>
                            </table> 
                           <div class="form-group col-md-12" >
                            <label>Email Notifications</label>
                             <textarea placeholder="abc@gmail.com,xyz@gmail.com" class="form-control" name="email">${emails }</textarea>
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
                                 
                        </div>
                     
                    </div> 
                </div>
                <!-- end row -->

            </div> <!-- end container -->
        </div>
        
       </div></div>
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
        
        <script src="<%=UI_PATH %>plugins/bootstrap-inputmask/bootstrap-inputmask.min.js" type="text/javascript"></script>
        <script src="<%=UI_PATH %>plugins/autoNumeric/autoNumeric.js" type="text/javascript"></script>

        <!--  App js -->
        <script src="<%=UI_PATH %>assets/js/jquery.core.js"></script>
        <script src="<%=UI_PATH %>assets/js/jquery.app.js"></script>
        <script src="<%=UI_PATH %>js/script.js"></script>
          
        <!-- Init Js file -->
        <script type="text/javascript" src="<%=UI_PATH %>assets/pages/jquery.form-advanced.init.js"></script>
        <!-- Parsley js -->
        <script type="text/javascript" src="<%=UI_PATH %>plugins/parsleyjs/parsley.min.js"></script>
        
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

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
            
          function getOutlets()
          {
        	  var type=$("#outlet_type").val();
        	  var dealer_id=$("#dealership_id").val();
        	  var quarter=$("#quarter").val();
        	  var year=$("#year").val();
        	  location.replace("<%=dashboardURL%>sheduleAudit/"+dealer_id+"/"+quarter+"/"+year+"");
          }
        </script>
        <script>
        function getDealers(type) 
        {
        	$("#otype").val(type);
        	$("#dealership_id").empty();
        	  $.ajax({
        	        url: "<%=dashboardURL%>getDealersByOutlet",
        	        type: "GET", 
                    data: { 'type': type },
        	        success: function(response)
        	                    {
        	        			 var dealerList=JSON.stringify(response);
        	        			var jsonString=JSON.parse(dealerList);
	        	        	   		for(var i=0;i<jsonString.length;i++)
	        	        	 	    {
	        	        	 	 	  $("#dealership_id").append("<option value='"+jsonString[i].dealer_id+"'>"+jsonString[i].dealer+"</option>");
	        	        	 	    }
        	                                     
        	                    }
        	        });
      
        	
        }
        </script>
        
        <script>

    	
    for(var i=1;i<=$("#count").val();i++)
    	{
     $("#audit_date"+i).datepicker({
            dateFormat : 'dd/mm/yy',
            changeMonth : true,
            changeYear : true,
           /*  yearRange: '-100y:c+nn', */
           // minDate: '0d',
            onSelect:function(date){
              $(this).parsley().validate();
            }
            
        }); 
     $("#end_date"+i).datepicker({
         dateFormat : 'dd/mm/yy',
         changeMonth : true,
         changeYear : true,
        /*  yearRange: '-100y:c+nn', */
        // minDate: '0d',
         onSelect:function(date){
           $(this).parsley().validate();
         }
         
     }); 
     
     }

       
    </script>
        
      
</body>
</html>