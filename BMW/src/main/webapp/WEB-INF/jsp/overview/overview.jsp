<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ page import="java.util.ResourceBundle"%>

<%
	ResourceBundle resource = ResourceBundle.getBundle("resources/web");
	String UI_PATH = resource.getString("UiPath");
	String title = resource.getString("dashboardTitle");
	String dashboardURL = resource.getString("dashboardURL");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Abstack - Responsive Web App Kit</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta
	content="A fully featured admin theme which can be used to build CRM, CMS, etc."
	name="description" />
<meta content="Coderthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<!-- App favicon -->
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/resources/assets/images/favicon.ico">

<!-- jvectormap -->
<link href="<%=UI_PATH%>plugins/jvectormap/jquery-jvectormap-2.0.2.css"
	rel="stylesheet" />

<!-- App css -->
<link href="<%=UI_PATH%>assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="<%=UI_PATH%>assets/css/icons.css" rel="stylesheet"
	type="text/css" />
<link href="<%=UI_PATH%>assets/css/metismenu.min.css" rel="stylesheet"
	type="text/css" />
<link href="<%=UI_PATH%>assets/css/style.css" rel="stylesheet"
	type="text/css" />

<script src="<%=UI_PATH%>assets/js/modernizr.min.js"></script>

</head>


<body>

	<!-- Begin page -->
	<div id="wrapper">

		<!-- Navigation Bar-->
		<jsp:include page="../include/header.jsp"></jsp:include>
		<jsp:include page="../include/sidemenu.jsp"></jsp:include>
		<!-- End Navigation Bar-->



		<!-- ============================================================== -->
		<!-- Start right Content here -->
		<!-- ============================================================== -->
		<div class="content-page">
			<!-- Start content -->
			<div class="content">
				<div class="container-fluid">
				<form method="POST" action="<%=dashboardURL %>overview">
					<div class="row">
						<div class="col-12">
							<div class="page-title-box">
								<div class="row">
									<div class="col-lg-4">
										<h4 class="page-title float-left">BMW</h4>
										<br>
										<h4 class="page-title">GROUP</h4>
									</div>
									<div class="col-lg-6">
										<h4>CRM Compliance (YTD 2018)</h4>
									</div>
									<div class="col-lg-2">
										
									</div>
									<div class="col-lg-2">
										<select id="year" name="year" class="form-control" >
											<option value="${year }">${year }</option>
											<option value="2018">2018</option>
											<option value="2019">2019</option>
											<option value="2020">2020</option>
										</select>
									</div>
									<div class="col-lg-2">
										<select id="phase" name="phase" class="form-control" >
											<option value="${phase }">${phase }</option>
											<option value="H2">H2</option>
											<option value="H1">H1</option>
										</select>
									</div>
									<div class="col-lg-2">
										<select id="brand" name="brand" class="form-control">
											<option value="${brand }">${brand }</option>
											<option value="ALL">ALL</option>
											<option value="BMW">BMW</option>
											<option value="MINI">MINI</option>
										</select>
									</div>
									<div class="col-lg-2">
										<select id="type" name="type" class="form-control" >
											<option value="ALL">ALL</option>
											<option value="Sales">Sales</option>
											<option value="Service">Service</option>
										</select>
									</div>
									<div class="col-lg-2">
										<select id="region" name="region" class="form-control" >
											<option value="ALL">ALL</option>
											<option value="1">East</option>
											<option value="2">West</option>
											<option value="3">North</option>
											<option value="4">South</option>
										</select>
									</div>
									<br>
									<div class="col-lg-2">
										<select id="dealer" name="dealer" class="form-control" onchange="getOutlets(this.value)">
										<option value="ALL">ALL</option>
										 <c:forEach var="qBean" items="${getDealerList}">
											<option value="${qBean.dealership_id}">${qBean.dealership_name }</option>
										</c:forEach>
										</select>
									</div>
									<br><br>
									<div class="col-lg-2">
										<select id="outlet" name="outlet" class="form-control">
										<option value="ALL">ALL</option>
										
										</select>
									</div>
									<div class="col-lg-2">
									<button type="submit">Get Reports</button>
									</div>
								</div>
								
								<div class="clearfix"></div>
								
					
							</div>
						</div>
					</div>
					
					<!-- end row -->
					</form>
					<div class="col-12">
						<div class="row">
							<div class="col-2">
								<div class="card-box">
									<h6>${audit_count }</h6>
									<h6>Audits Completed</h6>
								</div>
							</div>
							<div class="col-2">
								<div class="card-box">
									<h6>${audit_count_submit }</h6>
									<h6>Audits Submitted</h6>
								</div>
							</div>
							<div class="col-2">
								<div class="card-box">
									<h6>${audits_tobe_done }</h6>
									<h6>Audits to be done</h6>
								</div>
							</div>
							<div class="col-2">
								<div class="card-box">
									<h6>${dealer_avg }</h6>
									<h6>National Average</h6>
								</div>
							</div>
							<div class="col-2">
								<div class="card-box">
									<h6>${dealer_sales_avg }</h6>
									<h6>National Average-Sales</h6>
								</div>
							</div>
							<div class="col-2">
								<div class="card-box">
									<h6>${dealer_service_avg }</h6>
									<h6>National Average-After Sales</h6>
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="col-md-4">
							<div class="card-box">
								<h4 class="m-t-0 m-b-20 header-title">BMW September Visit
									Scheduling Status</h4>

								<div id="india" style="height: 400px"></div>

							</div>
						</div>
						<div class="col-8">
							<div class="card-box table-responsive">


								<table id="datatable" class="table table-bordered">
									<thead>
										<tr>
											<th>Rank</th>
											<th style="width: 200px;">Dealership</th>
											<th>H1 2018</th>
											<th>H2 2018</th>
											<th>Movement</th>

										</tr>
									</thead>


									<tbody>
									<c:forEach var="qBean" items="${getDealerListByRank}" varStatus="loop" >
										<tr>
											<th>${loop.index+1}</th>
											<td style="width: 200px;">${qBean.dealership_name }</td>
											<th>0%</th>
											<td>${qBean.score }%</td>
											<td>0.00%</td>

										</tr>
									</c:forEach>	

									</tbody>
								</table>
							</div>
						</div>
					</div>


				</div>
				<!-- end row -->

			</div>
			<!-- container -->

		</div>
		<!-- content -->


	</div>


	<!-- ============================================================== -->
	<!-- End Right content here -->
	<!-- ============================================================== -->


	</div>
	<!-- END wrapper -->



	<!-- jQuery  -->
	<script src="<%=UI_PATH%>assets/js/jquery.min.js"></script>
	<script src="<%=UI_PATH%>assets/js/popper.min.js"></script>
	<script src="<%=UI_PATH%>assets/js/bootstrap.min.js"></script>
	<script src="<%=UI_PATH%>assets/js/metisMenu.min.js"></script>
	<script src="<%=UI_PATH%>assets/js/waves.js"></script>
	<script src="<%=UI_PATH%>assets/js/jquery.slimscroll.js"></script>

	<!-- Jvector map -->
	<script
		src="<%=UI_PATH%>plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script
		src="<%=UI_PATH%>plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="<%=UI_PATH%>plugins/jvectormap/gdp-data.js"></script>
	<script
		src="<%=UI_PATH%>plugins/jvectormap/jquery-jvectormap-us-aea-en.js"></script>
	<script
		src="<%=UI_PATH%>plugins/jvectormap/jquery-jvectormap-uk-mill-en.js"></script>
	<script
		src="<%=UI_PATH%>plugins/jvectormap/jquery-jvectormap-au-mill.js"></script>
	<script
		src="<%=UI_PATH%>plugins/jvectormap/jquery-jvectormap-us-il-chicago-mill-en.js"></script>
	<script
		src="<%=UI_PATH%>plugins/jvectormap/jquery-jvectormap-ca-lcc.js"></script>
	<script
		src="<%=UI_PATH%>plugins/jvectormap/jquery-jvectormap-de-mill.js"></script>
	<script
		src="<%=UI_PATH%>plugins/jvectormap/jquery-jvectormap-in-mill.js"></script>
	<script
		src="<%=UI_PATH%>plugins/jvectormap/jquery-jvectormap-asia-mill.js"></script>
	<script src="<%=UI_PATH%>assets/pages/jquery.jvectormap.init.js"></script>
	<!-- App js -->
	<script src="<%=UI_PATH%>assets/js/jquery.core.js"></script>
	<script src="<%=UI_PATH%>assets/js/jquery.app.js"></script>
	
	
	<script>
	function callme()
	{
		var brand  =  $("#brand").val();
		var year  = $("#year").val();
		var phase = $("#phase").val();
		
		location.replace("<%=dashboardURL%>overview"+'/'+brand+'/'+phase+'/'+year);
	}
	
	function getOutlets(did)
	{
		  $("#outlet option").remove();  
          var did=did;
          var type = $("#type").val();
          
          
            $.ajax({
                  url: "<%=dashboardURL%>getOutletsByDealer",
                  type: "GET", 
                    data: { 'did': did,'type':type },
                  success: function(response)
                              {
                        var CityList=JSON.stringify(response);
                        var jsonString=JSON.parse(CityList);
                        $("#outlet").append("<option value='ALL'>ALL</option>");
                             for(var i=0;i<jsonString.length;i++)
                             {
                              $("#outlet").append("<option value='"+jsonString[i].outlet_id+"'>"+jsonString[i].outlets+"("+jsonString[i].brand+")</option>");
                             }
                                                 
                              }
                  });  
	}
	
	
	</script>


</body>
</html>