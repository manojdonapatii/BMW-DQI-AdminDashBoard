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
        <title>DQI </title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" content="no-cache" />

        <!-- App favicon -->
        <link rel="shortcut icon" href="<%=UI_PATH %>assets/images/favicon.ico">

        <!-- App css -->
        <link href="<%=UI_PATH %>assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH %>assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH %>assets/css/metismenu.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH %>assets/css/style.css" rel="stylesheet" type="text/css" />

        <script src="<%=UI_PATH %>assets/js/modernizr.min.js"></script>
        
        <style>
.gm-style-mtc,.gm-svpc
{
display: none;
}

</style>

    </head>

    <body onload="">
		<div id="wrapper">
        <!-- Navigation Bar-->
        <jsp:include page="include/header.jsp"></jsp:include>
        <jsp:include page="include/sidemenu.jsp"></jsp:include>
        
        <!-- End Navigation Bar-->


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
                                    <h4 class="page-title float-left">Dashboard</h4>

                                    <ol class="breadcrumb float-right">
                                        <li class="breadcrumb-item"><a href="<%=dashboardURL%>home">DQI</a></li>
                                        <li class="breadcrumb-item active">Dashboard</li>
                                    </ol>

                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </div>
                        <!-- end row -->


                        <div class="row">
                            <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>createDealership';">
                                <div class="card-box tilebox-one">
                                   <!--  <i class="fi-box float-right"></i> -->
                                    <h6 class="text-muted text-uppercase mb-3">Total Dealers</h6>
                                    <h4 class="mb-3" data-plugin="counterup">${dealers_count }</h4>
                                     <span class="text-muted ml-2 vertical-middle">View total dealers</span>
                                </div>
                            </div>

                            <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>viewOutlet';">
                                <div class="card-box tilebox-one">
                                   <!--  <i class="fi-layers float-right"></i> -->
                                    <h6 class="text-muted text-uppercase mb-3">Total Outlets</h6>
                                    <h4 class="mb-3"><span data-plugin="counterup">${outlets_count }</span></h4>
                                     <span class="text-muted ml-2 vertical-middle">View total outlets</span>
                                </div>
                            </div>

                            <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>viewAudit';">
                                <div class="card-box tilebox-one">
                                   <!--  <i class="fi-tag float-right"></i> -->
                                    <h6 class="text-muted text-uppercase mb-3">Scheduled Audits</h6>
                                    <h4 class="mb-3"><span data-plugin="counterup">${audits_count }</span></h4>
                                     <span class="text-muted ml-2 vertical-middle">View total audits scheduled</span>
                                </div>
                            </div>

                            <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3" onclick="location.href='<%=dashboardURL%>viewClosedAudit';">
                                <div class="card-box tilebox-one">
                                    <!-- <i class="fi-briefcase float-right"></i> -->
                                    <h6 class="text-muted text-uppercase mb-3">Closed Audits</h6>
                                    <h4 class="mb-3" data-plugin="counterup">${closed_count }</h4>
                                    <span class="text-muted ml-2 vertical-middle">View total audits closed</span>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-xl-7" style="display: none">
                                <div class="card-box">
									  <canvas id="transactions-chart" height="350" class="mt-4"></canvas>
                                </div>
                            </div>
                            <div class="col-xl-6">
                                <div id="googleMap" style="width:100%;height:600px;"></div>
                            </div>
                            <%-- <div class="col-xl-6">
                                <div class="card-box">
                                    <h4 class="header-title">Audits History</h4>

                                    

                                    <canvas id="sales-history" height="350" class="mt-4"></canvas>
                                </div>
                            </div> --%>
                        </div>
                        <!-- end row -->

                        


                    </div> <!-- container -->

                </div> <!-- content -->

                  <!-- Footer -->
        <jsp:include page="include/footer.jsp"></jsp:include>
        <!-- End Footer -->
				</div>
            </div>
        <!-- end wrapper -->


      
		

        <!-- jQuery  -->
        <script src="<%=UI_PATH %>assets/js/jquery.min.js"></script>
        <script src="<%=UI_PATH %>assets/js/popper.min.js"></script>
        <script src="<%=UI_PATH %>assets/js/bootstrap.min.js"></script>
        <script src="<%=UI_PATH %>assets/js/metisMenu.min.js"></script>
        <script src="<%=UI_PATH %>assets/js/waves.js"></script>
        <script src="<%=UI_PATH %>assets/js/jquery.slimscroll.js"></script>

        <!-- Counter number -->
        <script src="<%=UI_PATH %>plugins/waypoints/lib/jquery.waypoints.min.js"></script>
        <script src="<%=UI_PATH %>plugins/counterup/jquery.counterup.min.js"></script>

        <!-- Chart JS -->
        <script src="<%=UI_PATH %>plugins/chart.js/chart.bundle.js"></script>
        


        <!-- init dashboard -->
        

        <!-- App js -->
        <script src="<%=UI_PATH %>assets/js/jquery.core.js"></script>
        <script src="<%=UI_PATH %>assets/js/jquery.app.js"></script>
        <script>
        $.ajax({
            url: "",
            context: document.body,
            success: function(s,x){

                $('html[manifest=saveappoffline.appcache]').attr('content', '');
                    $(this).html(s);
            }
        });
        var locations = [];
        var locations2 = [];
       
  
$.ajax({
    url: "<%=dashboardURL%>getMapData",
    type: "GET", 
    success: function(response)
                {
 
                 $.each(response,function(k,v){
                 	
                 	if(v.status=='active')
                 		{
                 	
                 		innerArray=[];
                 		innerArray[0] = 'delhi',
                 		innerArray[1] = v.lat,
                 		innerArray[2] = v.lang,
                 		locations2.push(innerArray);
                 		}
                 
                 });
                 
                 
                 $.each(response,function(k,v){
                  	
                  	if(v.status=='closed' || v.status=='completed')
                  		{
                  		innerArray=[];
                  		innerArray[0] = 'delhi',
                  		innerArray[1] = v.lat,
                  		innerArray[2] = v.lang,
                  		locations.push(innerArray);
                  		}
                  
                  });
                 
                 
               //  myMap()
                 
                 
                } 
    });  
        function myMap() {
        	
       	 console.log('success')
       var mapProp= {
         center:new google.maps.LatLng(21.5937, 81.9629),
         zoom:4.8,
       };
       var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);



       var marker, i;

       for (i = 0; i < locations.length; i++) {  
         marker = new google.maps.Marker({
           position: new google.maps.LatLng(locations[i][1], locations[i][2]),
           icon: {
               path: google.maps.SymbolPath.CIRCLE,
               strokeColor: '#FF0000',
               strokeOpacity: 2,
               strokeWeight: 5,
               fillColor: '#FF0000',
               fillOpacity: 1,
             },
        
           map: map
           
         });

         google.maps.event.addListener(marker, 'click', (function(marker, i) {
           return function() {
             infowindow.setContent(locations[i][0]);
             infowindow.open(map, marker);
           }
         })(marker, i));
       }
       for (i = 0; i < locations2.length; i++) {  
       	  marker = new google.maps.Marker({
       	    position: new google.maps.LatLng(locations2[i][1], locations2[i][2]),
       	    icon: {
       	        path: google.maps.SymbolPath.CIRCLE,
       	        strokeColor: '#0000FF',
                   strokeOpacity: 2,
                   strokeWeight: 5,
                   fillColor: '#0000FF',
                   fillOpacity: 1,
       	      },
       	 
       	    map: map
       	    
       	  });

       	  google.maps.event.addListener(marker, 'click', (function(marker, i) {
       	    return function() {
       	      infowindow.setContent(locations2[i][0]);
       	      infowindow.open(map, marker);
       	    }
       	  })(marker, i));
       	}
       }
       
        


</script>
<script  language="JavaScript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&callback=myMap"></script>

<script>
		var month_list = new Array();
		var now = new Date();
		var months = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
		for (var i = 0; i <= 11; i++) {
		    var past = new Date(now); // @talemyn ;)
		    past.setMonth(now.getMonth() - i);
		    console.log(months[past.getMonth()] + ' ' + past.getFullYear());
		    month_list.push(months[past.getMonth()] + ' ' + past.getFullYear());
		}
		console.log(month_list.length);
		</script>
		<script>
		var month_count = new Array();
       // function getMonthlyAudits() 
        { 
         
            $.ajax({
                  url: "<%=dashboardURL%>getMonthlyAudits",
                  type: "GET", 
                  success: function(response)
                              {
		                        var MonthlyAudits=JSON.stringify(response);
		                        var jsonString=JSON.parse(MonthlyAudits);
		                             for(var i=0;i<jsonString.length;i++)
		                             {
		                              	//alert(jsonString[i].score);
		                              	month_count.push(jsonString[i].score);
		                              
		                             }
		                             


		                             !function($) {
		                                 "use strict";

		                                 var ChartJs = function() {};

		                                 ChartJs.prototype.respChart = function(selector,type,data, options) {
		                                     // get selector by context
		                                     var ctx = selector.get(0).getContext("2d");
		                                     // pointing parent container to make chart js inherit its width
		                                     var container = $(selector).parent();

		                                     // enable resizing matter
		                                     $(window).resize( generateChart );

		                                     // this function produce the responsive Chart JS
		                                     function generateChart(){
		                                         // make chart width fit with its container
		                                         var ww = selector.attr('width', $(container).width() );
		                                         switch(type){
		                                             case 'Line':
		                                                 new Chart(ctx, {type: 'line', data: data, options: options});
		                                                 break;
		                                             case 'Doughnut':
		                                                 new Chart(ctx, {type: 'doughnut', data: data, options: options});
		                                                 break;
		                                             case 'Pie':
		                                                 new Chart(ctx, {type: 'pie', data: data, options: options});
		                                                 break;
		                                             case 'Bar':
		                                                 new Chart(ctx, {type: 'bar', data: data, options: options});
		                                                 break;
		                                             case 'Radar':
		                                                 new Chart(ctx, {type: 'radar', data: data, options: options});
		                                                 break;
		                                             case 'PolarArea':
		                                                 new Chart(ctx, {data: data, type: 'polarArea', options: options});
		                                                 break;
		                                         }
		                                         // Initiate new chart or Redraw

		                                     };
		                                     // run function - render chart at first load
		                                     generateChart();
		                                 },

		                                     //init
		                                     ChartJs.prototype.init = function() {
		                                         //creating lineChart
		                                         var lineChart = {
		                                             labels: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October"],
		                                             datasets: [{
		                                                 label: "Conversion Rate",
		                                                 fill: false,
		                                                 backgroundColor: '#5d6dc3',
		                                                 borderColor: '#5d6dc3',
		                                                 data: [44,60,-33,58,-4,57,-89,60,-33,58]
		                                             }, {
		                                                 label: "Average Sale Value",
		                                                 fill: false,
		                                                 backgroundColor: '#3ec396',
		                                                 borderColor: "#3ec396",
		                                                 borderDash: [5, 5],
		                                                 data: [-68,41,86,-49,2,65,-64,86,-49,2]
		                                             }]
		                                         };

		                                         var lineOpts = {
		                                             responsive: true,
		                                             // title:{
		                                             //     display:true,
		                                             //     text:'Chart.js Line Chart'
		                                             // },
		                                             tooltips: {
		                                                 mode: 'index',
		                                                 intersect: false,
		                                             },
		                                             hover: {
		                                                 mode: 'nearest',
		                                                 intersect: true
		                                             },
		                                             scales: {
		                                                 xAxes: [{
		                                                     display: true,
		                                                     // scaleLabel: {
		                                                     //     display: true,
		                                                     //     labelString: 'Month'
		                                                     // },
		                                                     gridLines: {
		                                                         color: "rgba(0,0,0,0.1)"
		                                                     }
		                                                 }],
		                                                 yAxes: [{
		                                                     gridLines: {
		                                                         color: "rgba(255,255,255,0.05)",
		                                                         fontColor: '#fff'
		                                                     },
		                                                     ticks: {
		                                                         max: 100,
		                                                         min: -100,
		                                                         stepSize: 20
		                                                     }
		                                                 }],
		                                             }
		                                         };

		                                         this.respChart($("#transactions-chart"),'Line',lineChart, lineOpts);
		                                        
		                                         //barchart
		                                         var barChart = {
		                                             labels: month_list,
		                                             datasets: [
		                                                 {
		                                                     label: "Month Wise Audit Report",
		                                                     backgroundColor: "#3ec396",
		                                                     borderColor: "#3ec396",
		                                                     borderWidth: 1,
		                                                     hoverBackgroundColor: "#3ec396",
		                                                     hoverBorderColor: "#3ec396",
		                                                     data:  month_count
		                                                 }
		                                             ]
		                                         };
		                                         var barOpts = {
		                                             scales: {
		                                                 yAxes: [{
		                                                     gridLines: {
		                                                         color: "rgba(255,255,255,0.05)",
		                                                         fontColor: '#fff'
		                                                     },
		                                                     ticks: {
		                                                         max: 100,
		                                                         min: 0,
		                                                         stepSize: 10
		                                                     }
		                                                 }],
		                                                 xAxes: [{
		                                                     gridLines: {
		                                                         color: "rgba(0,0,0,0.1)"
		                                                     }
		                                                 }]
		                                             }
		                                         };

		                                         this.respChart($("#sales-history"),'Bar',barChart, barOpts);
		                                     },
		                                     $.ChartJs = new ChartJs, $.ChartJs.Constructor = ChartJs

		                             }(window.jQuery),

		                             //initializing
		                                 function($) {
		                                     "use strict";
		                                     $.ChartJs.init()
		                                 }(window.jQuery);

		                                 
		                             console.log(month_count.length);
                                                 
                              }
                  });  
          
        }

        </script>



    </body>
</html>