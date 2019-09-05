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
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>  
<link href="<%=UI_PATH %>assets/reports/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=UI_PATH %>assets/reports/css/style.css">  
<style>
@media print
{
  table { page-break-after:auto }
  tr    { page-break-inside:avoid; page-break-after:auto }
  td    { page-break-inside:avoid; page-break-after:auto }
  thead { display:table-header-group }
  tfoot { display:table-footer-group }
}
</style>
</head>
  <body onload="getReports();">
    <div class="container">    	
      	<div class="col-md-12">
      		<h2 class="head-text"><strong>Retail standards<br>${dealership_name }</strong></h2>
        	<table class="table table-bordered">
		        <tr>
		            <th colspan="12" class="text-center bg-dark-grey">RETAIL STANDARDS SCORE - SALES</th>
		        </tr>
		        <tr class="text-center bg-lite">            
		            <td>Sales</td>
		            <td>After Sales</td>
		        </tr>
		        <tr class="text-center bg-grey">            
		            <td>Contractual</td>
		            <td>Contractual</td>
		        </tr>
          		<tr>                    
              		<td>
	                  	<div class="col-md-12 center-block">                      
                      		<div id="chartContainer1"></div>                          
                          	<h6 class="text-center">Contractual</h6>
                          	<ul class="bar-text text-center">
                              	<li><div class="sqr-dot"></div>${dealership_name }</li>
                              	<li><div class="sqr-dot1"></div>Highest in Network</li>
                              	<li><div class="sqr-dot2"></div>Lowest in Network</li>
                          	</ul>                    	
                  		</div>
              		</td>
	            	<td>    
	              		<div class="col-md-12">                      	    
	              			<div id="chartContainer2"></div>                                            
	                  		<h6 class="text-center">Contractual</h6>
		                  	<ul class="bar-text text-center">
		                      	<li><div class="sqr-dot"></div>${dealership_name }</li>
		                      	<li><div class="sqr-dot1"></div>Highest in Network</li>
		                      	<li><div class="sqr-dot2"></div>Lowest in Network</li>
		                  	</ul>                      
		             	</div>
	            	</td>
          		</tr>        
        	</table>
          	<div class="col-md-6">
            	<div class="col-md-3 bg-grey pull-right text-center ">
              		<h6>Rank: 7/16</h6>
              		<h6>Score: 97.8%</h6>
            	</div>
            	<div class="clearfix"></div>             
              	<div id="chartContainer3"></div>
          	</div>
          	<div class="col-md-6">
            	<div class="col-md-3 bg-grey pull-right text-center">
              		<h6>Rank: 12/17</h6>
              		<h6>Score: 97.3%</h6>
            	</div>
            	<div class="clearfix"></div>              
              	<div id="chartContainer4"></div>
          	</div>
      	</div>      
    </div>    
    <input type="hidden" id="did" value="${did}">
    <input type="hidden" id="year" value="${year}">
    <input type="hidden" id="phase" value="${phase}">
   
<script src="<%=UI_PATH %>assets/reports/js/jquery.min.js"></script>    
<script src="<%=UI_PATH %>assets/reports/js/bootstrap.min.js"></script>    
<script src="<%=UI_PATH %>assets/reports/js/canvasjs.min.js"></script>
<script src="<%=UI_PATH %>assets/reports/js/charts.js"></script>
 <script>
function getReports()
{
	var did = $("#did").val();
	var year = $("#year").val();
	var phase = $("#phase").val(); 
	
	$.ajax({
        url: "<%=dashboardURL%>getReportsOfDealers",
        type: "GET", 
          data: { 'did': did,'year':year,'phase':phase },
        success: function(response)
                    {
        	 var dataPoints = [];
              var DealerList=JSON.stringify(response);
              var jsonString=JSON.parse(DealerList);
                   for(var i=0;i<jsonString.length;i++)
                   {
                	   if(jsonString[i].score=="100")
                		   {
                		   dataPoints.push({
                    		   y: 100,
                    		   percent: jsonString[i].score,
                   	           label: jsonString[i].section,
                   	     	   color: "#737373"
                   	      });
                		   }
                	   else
                	{
                	   dataPoints.push({
                		   y: 100,
                		   percent: jsonString[i].score,
               	           label: jsonString[i].section,
               	     	   color: "#f7e727"
               	      });
                	}
                   //$(".abc").append(jsonString[i].section+"#"); 
                   var chart3 = new CanvasJS.Chart("chartContainer3", {
                		animationEnabled: true,
                		title:{
                			//text: "Email Categories",
                			horizontalAlign: "left"
                		},
                		data: [{
                			type: "doughnut",
                			startAngle: 80,
                			//innerRadius: 60,
                			
                			indexLabelFontSize: 11,
                			indexLabel: "{label}({percent}%)",
                			toolTipContent: "<b>{label}:</b>({percent}%)",
                			dataPoints:dataPoints /*  [
                				{ y: 100, label: jsonString[0].section, color: "#737373" },
                				{ y: 100, label: jsonString[1].section, color: "#737373" },
                				{ y: 100, label: jsonString[2].section, color: "#737373" },
                				{ y: 100, label: jsonString[3].section, color: "#737373"},
                				{ y: 100, label: jsonString[4].section, color: "#737373"},
                				{ y: 100, label: jsonString[5].section, color: "#737373"},
                				{ y: 100, label: jsonString[6].section, color: "#f7e727"},
                				{ y: 100, label: jsonString[7].section, color: "#f7e727"},
                				{ y: 100, label: jsonString[8].section, color: "#737373"},
                				{ y: 100, label: jsonString[9].section, color: "#737373"}
                			] */
                		}]
                	});

                	chart3.render();
                   }
                                       
                    }
        });  
	
}
</script>
<script>
function getReportsByService()
{
	var did = $("#did").val();
	var year = $("#year").val();
	var phase = $("#phase").val(); 
	
	$.ajax({
        url: "<%=dashboardURL%>getReportsOfDealersByService",
        type: "GET", 
          data: { 'did': did,'year':year,'phase':phase },
        success: function(response)
                    {
        	//alert(response);
        	 var dataPoints = [];
              var ServiceList=JSON.stringify(response);
              var jsonString=JSON.parse(ServiceList);
                   for(var i=0;i<jsonString.length;i++)
                   {
                	   if(jsonString[i].score=="100")
                		   {
                		   dataPoints.push({
                    		   y: 100,
                    		   percent: jsonString[i].score,
                   	           label: jsonString[i].section,
                   	     	   color: "#737373"
                   	      });
                		   }
                	   else
                	{
                	   dataPoints.push({
                		   y: 100,
                		   percent: jsonString[i].score,
               	           label: jsonString[i].section,
               	     	   color: "#f7e727"
               	      });
                	}
                   //$(".abc").append(jsonString[i].section+"#"); 
        	var chart4 = new CanvasJS.Chart("chartContainer4", {
        	animationEnabled: true,
        	title:{
        		//text: "Email Categories",
        		horizontalAlign: "left"
        	},
        	data: [{
        		type: "doughnut",
        		startAngle: 80,
        		//innerRadius: 60,
        		indexLabelFontSize: 11,
    			indexLabel: "{label}({percent}%)",
    			toolTipContent: "<b>{label}:</b>({percent}%)",
    			dataPoints:dataPoints
    		}]
    	});
        chart4.render();

        }
                                       
                    }
        });  
	
}

</script>
<script>
function getContractualBySales()
{
  var did = $("#did").val();
  var year = $("#year").val();
  var phase = $("#phase").val(); 
  
  $.ajax({
        url: "<%=dashboardURL%>getContractualBySales",
        type: "GET", 
          data: { 'did': did,'year':year,'phase':phase },
        success: function(response)
                    {
            var dataPoints = [];
            var av;
            var mx;
            var mn;
              var ContractualList=JSON.stringify(response);
              var jsonString=JSON.parse(ContractualList);
                   for(var i=0;i<jsonString.length;i++)
                   {
                     av=jsonString[i].avg;
                     mx=jsonString[i].max;
                     mn=jsonString[i].min;
                     
                    /*  alert(av);
                     alert(jsonString[0].avg+jsonString[0].max+jsonString[i].min); */
                     var chart1 = new CanvasJS.Chart("chartContainer1", {
                      animationEnabled: true,
                      exportEnabled: true,  
                      data: [{
                        type: "column", //change type to bar, line, area, pie, etc
                        //indexLabel: "{y}", //Shows y value on all Data Points
                        indexLabelFontColor: "#5A5757",
                        indexLabelPlacement: "outside",
                        dataPoints:[
                    { x: 5 , y: parseFloat(av)},
                    { x: 10, y: parseFloat(mx)},
                    { x: 15, y: parseFloat(mn)} 
                        ] 
                      }]
                    });
                    chart1.render();
                    
        }
                                        
                    }
        });  
  
}
</script>
<script>
function getContractualByServices()
{
  var did = $("#did").val();
  var year = $("#year").val();
  var phase = $("#phase").val(); 
  
  $.ajax({
        url: "<%=dashboardURL%>getContractualByServices",
        type: "GET", 
          data: { 'did': did,'year':year,'phase':phase },
        success: function(response)
                    {
            var dataPoints = [];
            var av;
            var mx;
            var mn;
              var ContractualList1=JSON.stringify(response);
              var jsonString=JSON.parse(ContractualList1);
                   for(var i=0;i<jsonString.length;i++)
                   {
                     av=jsonString[i].avg;
                     mx=jsonString[i].max;
                     mn=jsonString[i].min;
                     
                     /* alert(av);
                     alert(jsonString[0].avg+jsonString[0].max+jsonString[i].min);  */
                     var chart2 = new CanvasJS.Chart("chartContainer2", {
                      animationEnabled: true,
                      exportEnabled: true,  
                      data: [{
                        type: "column", //change type to bar, line, area, pie, etc
                        //indexLabel: "{y}", //Shows y value on all Data Points
                        indexLabelFontColor: "#5A5757",
                        indexLabelPlacement: "outside",
                        dataPoints:[
                    { x: 5 , y: parseFloat(av)},
                    { x: 10, y: parseFloat(mx)},
                    { x: 15, y: parseFloat(mn)} 
                        ] 
                      }]
                    });
                    chart2.render();
                    
        }
                                        
                    }
        });  
  
}
getReports();
getReportsByService();
getContractualBySales();
getContractualByServices();

</script>

</body>
</html>