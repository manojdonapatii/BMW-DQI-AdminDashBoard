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
   
   
   
   
   
   
   
   
   
   <base href="https://demos.telerik.com/kendo-ui/pdf-export/index">
    <style>html { font-size: 14px; font-family: Arial, Helvetica, sans-serif; }</style>
    <title></title>
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2019.1.220/styles/kendo.common-material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2019.1.220/styles/kendo.material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2019.1.220/styles/kendo.material.mobile.min.css" />

    <script src="https://kendo.cdn.telerik.com/2019.1.220/js/jquery.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2019.1.220/js/jszip.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2019.1.220/js/kendo.all.min.js"></script>
   
   
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title></title>
      <link href="<%=UI_PATH %>assets/reports/css/bootstrap.min.css" rel="stylesheet">
      <link rel="stylesheet" type="text/css" href="<%=UI_PATH %>assets/reports/css/style.css">
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      
     <!--   <script type="text/javascript" src="//cdn.rawgit.com/MrRio/jsPDF/master/dist/jspdf.min.js">
    </script> -->
    <script type="text/javascript" src="//cdn.rawgit.com/niklasvh/html2canvas/0.5.0-alpha2/dist/html2canvas.min.js">
    </script>
    <script type="text/javascript" src="app.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.4.1/jspdf.min.js"></script>
      <style>
         .col-md-2.pull-right{
         width: 19.666667%;
         }
         .left{margin-left:20px;}
         .out-round{border-radius: 10px; padding: 15px 25px 15px 15px;}
         

.table-bordered>tbody>tr>td:first-child,.table-bordered>tbody>tr:first-child>th:first-child,.table-bordered>tbody>tr>td:nth-child(2), .table-bordered>tbody>tr:first-child>th:nth-child(2), .table-bordered>tfoot>tr>td:not(:last-child), .table-bordered>tfoot>tr>th, .table-bordered>thead>tr>td, .table-bordered>thead>tr>th {
    border-right: 3px solid white!important;position:relative;
}

.list-style{list-style:none;}
#bmw-img{
float: right;
    margin-top: -38px;
}
#mini-img{
float: right;
    margin-top: -38px;
        width: 178px;
    height: 113px;
}
.red-dot-1{
    width: 10px;
    height: 10px;
    border-radius: 50%;
    background-color: red;
    position: absolute;
    top: 3px;
    left: 40px;}
    .org-dot-1{
    width: 10px;
    height: 10px;
    border-radius: 50%;
    background-color: orange;
    position: absolute;
    top: 3px;
    left: 40px;}
    .sub-style-2{padding: 0 0 0 20px;    font-size: 12px;}
.head-text.main
{
font-size: 26px;
}

.head-text.left.annexure
{
font-size: 25px;
text-align: center;
}
#ad-comment
{
font-size: 15px;
    color: black;
    font-family: BMWGroupCondensed-Regular;
    font-weight:100;
    
}
.standard-class{
font-size: 16px;
    color: black;
    font-family: BMWGroupCondensed-Regular;
}
#to-be-bar{
    color: #d0d0d0;
    font-size: 24pt;
    -moz-transform: rotate(-45deg);
  
} 
/* 
.cone-shape-1::after {
    background-image: url(../images/white.jpg);
    border-top: 47px solid transparent;
    border-bottom: 63px solid transparent;
    border-left: 44px solid #cccccc;
    content: ' ';
    position: absolute;
    top: 2px;
    left: 100%;
    z-index: 1;
    margin: 0 0 0 -3px;
    
}

.cone-shape-1::before {
    background-image: url(../images/white.jpg);
    border-top: 49px solid transparent;
    border-bottom: 65px solid transparent;
    border-left: 45px solid #fff;
    content: ' ';
    position: absolute;
    top: 0px;
    left: 100%;
    z-index: 1;
    margin: 0 0 0 0px;
} */


.cone-shape-1::after {
    background-image: url(../images/white.jpg);
   background: #cccccc;
    content: ' ';
    position: absolute;
    top: 1px;
    left: 100%;
    z-index: 1;
    margin: 0 0 0 -1px;
    bottom:1px;width:45px;
    -webkit-clip-path: polygon(0 0, 0% 100%, 100% 50%);
    clip-path: polygon(0 0, 0% 100%, 100% 50%);
    
}

.cone-shape-1::before {
    background-image: url(../images/white.jpg);
  background: #fff;
    content: ' ';
    position: absolute;
    top: 0px;
    left: 100%;
    z-index: 1;
    margin: 0 0 0 2px;
   bottom:0px;
    width:45px;
    -webkit-clip-path: polygon(0 0, 0% 100%, 100% 50%);
    clip-path: polygon(0 0, 0% 100%, 100% 50%);
    
}

#bottom2{
	width: 100%;
	    font-family: BMWGroupCondensed-Regular;
    font-weight: 100;
    font-size: 15px;
    
}
#bottom{
    font-family: BMWGroupCondensed-Regular;
    font-weight: 100;
    font-size: 15px;
    
}
#hidden{
visibility: hidden;
}

    /*  body{
       font-family: BMWGroupCondensed-Regular;
   }   */
   
  /*  .btn {
  background-color:#31708f;
  border: none;
  color: white;
  padding: 10px 10px;
  cursor: pointer; 
  font-size: 12px;
  position: relative;
  top: 84px;
  left: 131px;
} */

.v-aligin{vertical-align:middle !important;}
.v-aligin .org-dot{margin:0 auto;}

 .background-image, html {
        height: 100vh;
        background-position: center;
        background-repeat: no-repeat;
        background-size: cover;
        background-image: url("<%=UI_PATH%>assets/images/image.png");  
        position:relative;
    }
    .main-text{padding: 10px 30px;}
.main-text h1{text-transform: uppercase;font-size: 40px;font-weight: 500;margin-bottom: 25px;letter-spacing: 2px;font-family:BMWGroupCondensed-Bold;}
.main-text p{text-transform: uppercase;font-size: 20px;font-weight: 500;letter-spacing: 1px;font-family:BMWGroupCondensed-Bold;}
.last-txt{position: absolute;bottom: 25px;font-family: BMWGroup-Regular;}


.list-style{ list-style: none}
    .sub-style{font-size: 12px; color: #000;}
    ul small{font-size: 12px; color: #000;padding: 0 0 0 20px;}
    
    #barcontainer1{border:0 none;}
    .highcharts-container {border:0 none;-webkit-tap-highlight-color: transparent;}
    
    

      </style>
   </head>
   <body >
   <div id="example">
 
    <div class="box wide hidden-on-narrow row">
        <br><br>
        <div class="box-col col-md-offset-3 col-md-2">
            <button class='export-pdf k-button'>Export as PDF</button>
        </div>
        <div class="box-col col-md-2">
            <button class='export-img k-button'>Export as Image</button>
        </div>
        <div class="box-col col-md-2">
            <button class='export-svg k-button'>Export as SVG</button>
        </div>
    </div>

    <!-- <div class="demo-section k-content export-app wide hidden-on-narrow">
        <div class="demo-section content-wrapper wide">
          <div class="demo-section charts-wrapper wide">
            <div id="users"></div>
            <div id="applications"></div>
            <div id="referrals"></div>
          </div>
          <div id="grid"></div>
        </div>
    </div> -->
    
    <div class="responsive-message"></div>
</div>
   
   <!-- <a href="#" class="btn" id="create_pdf">create pdf</a>  -->
    <form class="ui form content-wrapper">
             
     
     <div class="container" style="margin-top: 70px;width: 21cm; margin: 3cm; width: 12in;margin: 1in;">   <div class="col-md-12">
        
       
        <div class="main-text background-image" >
            <!-- <label><span class="bmw-text">BMW</span><span class="connect-text">Connect</span></label> -->
            <!-- <form id="signinForm">
                
                  <div class="form-group">
                    <input  class="textbox"type="text" class="form-control" name="usename"placeholder="User name">
                  </div>
                  <div class="form-group">
                      <input  class="textbox margintop"type="password" class="form-control" name="password" placeholder="Password">
                  </div>       
                  <center>         
                  <a href="#">
                      <button type="submit" class="btn-primary loginbtn">Login<img class="arrow-point" src="assets/images/arrow-point.png"></button>
                  </a>
                  </center>
            </form>  -->
            <h1>Dealer Standards</h1>
            <p>${dealership_name } | Audit Results and Executive Summary ${phase } ${year }</p>
           
            <p class="last-txt">B3-IN-${phase } ${year }</p>
        </div></div></div>
        <div class="container" style="margin-top: 40px;width: 21cm; margin: 3cm; width: 12in;margin: 1in;">   <div class="col-md-12">
         <span class="head-text main"><strong>DEALER STANDARDS ${phase} - ${year}<br>${dealership_name }</strong></span><img src="<%=UI_PATH %>assets/images/bmw.svg" alt="BMW" id="bmw-img" height="80" width="80">
            
            <table class="table table-bordered">
               <tr>
                  <th colspan="12" class="text-center bg-dark-grey">DEALER STANDARDS ${phase} - ${year} SCORE</th>
               </tr>
               <tr class="text-center bg-lite">
                  
               </tr>
               <tr class="text-center bg-grey">
                 <td>Sales-Contractual</td>
                  <td>After Sales-Contractual</td>
               </tr>
               <tr>
                  <td style="border-left-color:transparent;">
                     <div class="col-md-12 center-block">
                        <!--<div id="chartContainer1"></div>    -->   
                        <div id="barcontainer1" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                        <img alt="" src="" id="canvasimg1">
                       <!--  <h6 class="text-center">	</h6> -->
                        <ul class="bar-text text-center">
                           <li>
                              <div class="sqr-dot"></div>
                              ${dealership_name }
                           </li>
                           <li>
                              <div class="sqr-dot1"></div>
                              Highest in Network
                           </li>
                           <li>
                              <div class="sqr-dot2"></div>
                              Lowest in Network
                           </li>
                        </ul>
                     </div>
                  </td>
                  <td>
                     <div class="col-md-12">
                        <!-- <div id="chartContainer2"></div>                                             -->
                        <div id="barcontainer2" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                        <img alt="" src="" id="canvasimg2">
                       <!--  <h6 class="text-center">Contractual</h6> -->
                        <ul class="bar-text text-center">
                           <li>
                              <div class="sqr-dot"></div>
                              ${dealership_name }
                           </li>
                           <li>
                              <div class="sqr-dot1"></div>
                              Highest in Network
                           </li>
                           <li>
                              <div class="sqr-dot2"></div>
                              Lowest in Network
                           </li>
                        </ul>
                     </div>
                  </td>
               </tr>
            </table>
      <div class="row">
             <div class="col-md-6" >
             
      <div style="border:1px solid #c6c0c0;">
               <div class="col-md-3 bg-grey pull-right text-center fonts">
                  <h6>Rank: ${sales_rank_bmw }/${dealer_sales_count_bmw }</h6>
                  <h6>Score: <span id="sales_score"></span></h6>
              
               </div>
               <div class="clearfix"></div>
               <div id="chartContainer1"></div>
            </div>
             </div>
            <div class="col-md-6" >
            <div  style="border:1px solid #c6c0c0;">
               <div class="col-md-3 bg-grey pull-right text-center fonts">
                  <h6>Rank: ${service_rank_bmw }/${dealer_service_count_bmw }</h6>
                  <h6>Score: <span id="service_score"></span></h6>
               </div>
              
               <div class="clearfix"></div>
               <div id="chartContainer2"></div>
            </div>
             </div>
      </div>
         </div>
      </div>
      <br><br>
       <c:if test="${BMWSalesOutletList!='[]'}"> 
          <div class="container" style="width: 21cm; margin: 3cm; width: 12in;margin: 1in;">
          
          <div class="col-md-2 pull-right">
            <ul class="bg-dark-grey list-type out-round">
               <h4>OUTLETS</h4>
               <c:forEach var="qBean" items="${BMWSalesOutletList}" varStatus="loop" >
                  <li><%-- ${loop.index+1}. --%> ${qBean.outlets }</li>
               </c:forEach>
            </ul>
         </div>
     <h2 class="head-text left"><strong>DEALER STANDARDS ${phase} - ${year} : sales Observations ${dealership_name }</strong></h2>
         <div class="clearfix"></div>
         <div class="col-md-12">
            <table class="table table-bordered">
               <tr>
                  <th colspan="12" class="text-center bg-dark-grey">DEALER STANDARDS SCORE - SALES</th>
               </tr>
               <tr class="bg-light-grey">
                  <td class="text-center"></td>
                  <td class="text-center">ESSENTIAL (Target value = 100%)</td>
                  <td class="text-center">CONTRACTUAL (Target value &ge; 85%)</td>
               </tr>
               <tr class="bg-dark">
                  <td class="text-center">Overall Score  ${dealership_name }</td>
                  <td class="text-center">${overall_ess_score_bmw }%</td>
                  <td class="text-center  ">${overall_cont_score_bmw }%</td>
               </tr>
               <c:forEach var="qBean" items="${TotalAvgreports}" varStatus="loop" >
                  <tr class="text-center bg-lite">
                     <td><%-- ${loop.index+1}. --%>${qBean.outlets }</td>
                     <td>${qBean.essential }</td>
                     <td>${qBean.contractual }</td>
                  </tr>
               </c:forEach>
            </table>
         </div>
         <div class="col-md-12 penal">
            <table class="table table-bordered">
               <tr>
                  <td class="bg-grey txt-grey cone-shape" style="width:15%;padding: 60px 20px 20px 50px;" rowspan="3"><span class=" ">Section</span></td>
                  <th class="text-center bg-dark-grey" rowspan="3" style="padding:60px 0 0 0;">Penalized Standards</th>
                  <th colspan="8"  class="text-center bg-dark-grey">Defaulting Outlet</th>
               </tr>
               <tr>
                  <c:forEach var="qBean" items="${BMWSalesOutletList}" varStatus="loop" >
                     <th colspan="2" class="text-center bg-dark-grey">${qBean.outlets }</th>
                  </c:forEach>
               <tr>
                  <c:forEach var="qBean" items="${BMWSalesOutletList}" varStatus="loop" >
                     <th  colspan="1">H1'${yr}</th>
                     <th colspan="1">H2'${yr}</th>
                  </c:forEach>
               </tr>
               <c:forEach var="qBean" items="${PenalizedList}" varStatus="loop">
               
                  <tr class="bg-grey">
                     <td class="txt-grey size  sec" style="width:10px;">${qBean.section}</td>
                     <td class="size"><span style="    padding-left: 35px;"><span class="mr-ryt"><span class="big-txt">${qBean.number}</span><sup>${qBean.essential}</sup></span></span><span class="bk-text"><span class="standard-class">${qBean.standard}</span> - <a id="ad-comment" href="#bottom">${qBean.auditor_comment}</a></span></td>
                     <c:forEach var="oBean" items="${BMWSalesOutletList}" varStatus="loop" >
                     <c:if test="${qBean.phase=='H1'}">
                     <c:choose>
                           <c:when test="${oBean.outlet_id==qBean.outlet_id || oBean.outlet_id==qBean.outlets || oBean.outlet_id==qBean.ph}">
                              <th style="width:20px;padding: 0 0px 0 19px;" colspan="1" align="center" valign="middle" class="v-aligin">
                                 <div class="red-dot"></div>
                              </th>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                           </c:when>
                           <c:otherwise>
                              <th style="width:20px;" colspan="1"  class="v-aligin"></th>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                           </c:otherwise>
                        </c:choose>
               		</c:if>
               		<c:if test="${qBean.phase=='H2'}">
               		<%-- <input type="text" value="${qBean.outlet_id}"> --%>
                     <c:choose>
                           <c:when test="${oBean.outlet_id==qBean.outlet_id || oBean.outlet_id==qBean.outlets || oBean.outlet_id==qBean.ph}">
                           <th style="width:20px;" colspan="1" align="center" valign="middle" class="v-aligin"></th>
                              <th style="width:20px;padding: 0px 0px 0 19px;" colspan="1" align="center" valign="middle" class="v-aligin">
                                 <div class="red-dot"></div>
                              </th>
                              
                           </c:when>
                           <c:otherwise>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                           </c:otherwise>
                        </c:choose>
               		</c:if>
                     
                        
                     </c:forEach>
                  </tr>
               </c:forEach>
            </table>
            <hr>
  <div class="row">
      <div class="col-md-4">
        <ul class="list-style">
            <li class="sub-style"><sup>X</sup> Essential Standards</li>
            <li class="sub-style"><sup>C</sup> Contractual Standards</li>
        </ul>
       
      </div>
      <div class="col-md-8">
            <ul class="list-style">
            	<div class="red-dot-1"></div>
                <li class="sub-style">Standard for which dealership has been</li>
                <li class="sub-style-2">penalized basis the audit observations</li>
            </ul>
           
        </div>
  </div>
         </div>
         
      </div>
       </c:if> 
      <br><br><br>
        <c:if test="${areaImpList!='[]'}">
     <div class="container aoiSales"  style="width: 21cm;width:12in; margin: 3cm;"> 
         <div class="col-md-2 pull-right">
            <ul class="bg-dark-grey list-type out-round">
               <h4>OUTLETS</h4>
               <c:forEach var="qBean" items="${BMWSalesOutletList}" varStatus="loop" >
                  <li ><%-- ${loop.index+1}. --%> ${qBean.outlets }</li>
               </c:forEach>
            </ul>
         </div>
         <h2 class="head-text left"><strong>DEALER STANDARDS ${phase} - ${year} : sales Observations<br>${dealership_name }</strong></h2>
         <div class="clearfix"></div>
         <div class="col-md-12">
            <table class="table table-bordered">
               
               <tr>
               		<th rowspan="3" style="padding:60px 20px 10px 50px;width: 15%;position: relative;left: 0px;" class="text-center bg-grey txt-grey cone-shape-1">Section</th>
               		<th class="text-center bg-dark-grey v-aligin" colspan="3">Areas for Improvements</th>
               		<th class="text-center bg-dark-grey v-aligin" colspan="8" height="74">Defaulting Outlet</th>
               </tr>
               <tr>
               		<th colspan="3" class="text-center bg-dark-grey">Action Items</th>   
               	 <c:forEach var="qBean" items="${BMWSalesOutletList}" varStatus="loop" >
                     <th colspan="2" class="text-center bg-dark-grey"> ${qBean.outlets }</th>
                  </c:forEach>      		
               </tr>
               <tr>
               		<th class="text-center bg-grey">Standard</th>
               		<th class="text-center bg-grey">Timeline remark</th>
               		<th class="text-center bg-grey"> Date </th>
               		<c:forEach var="qBean" items="${BMWSalesOutletList}" varStatus="loop" >
                     <th colspan="1">H1'${yr}</th>
                     <th colspan="1">H2'${yr}</th>
                  </c:forEach>
               </tr>
               <c:forEach var="qBean" items="${areaImpList}" varStatus="loop">
               <tr class="bg-grey">
               		<td class="txt-grey size aoiSection" style="width:10px;">${qBean.section}</td> 
               		<th><span class="mr-ryt"><span class="big-txt">${qBean.number}</span><sup>${qBean.essential}</sup></span>
               		<span class="bk-text">
               		<span id="ad-comment">${qBean.standard}</span></span></th>
               		<th id="ad-comment">${qBean.exception_remarks }</th>
               		<th id="ad-comment">${qBean.time }</th>
               		<c:forEach var="oBean" items="${BMWSalesOutletList}" varStatus="loop" >
               		<c:if test="${qBean.phase=='H1'}">
               		<c:choose>
                           <c:when test="${oBean.outlet_id==qBean.outlet_id}">
                             
                              <th style="width:20px;" colspan="1" align="center" valign="middle" class="v-aligin">
                                 <div class="org-dot"></div>
                              </th>
                               <th style="width:20px;"colspan="1" class="v-aligin"></th>
                           </c:when>
                           <c:otherwise>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                           </c:otherwise>
                        </c:choose>
               		</c:if>
               		<c:if test="${qBean.phase=='H2'}">
               		<c:choose>
                           <c:when test="${oBean.outlet_id==qBean.outlet_id}" >
                              <th style="width:20px;"colspan="1" align="center" valign="middle" class="v-aligin"></th>
                              <th style="width:20px;" colspan="1" align="center" align="center" valign="middle" class="v-aligin">
                                 <div class="org-dot"></div>
                              </th>
                              
                           </c:when>
                           <c:otherwise>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                           </c:otherwise>
                        </c:choose>
               		</c:if>
                        
                     </c:forEach>
				</tr>
				</c:forEach>
				</table>
				<hr>
  <div class="row">
      <div class="col-md-4">
        <ul class="list-style">
            <li class="sub-style"><sup>X</sup> Essential Standards</li>
            <li class="sub-style"><sup>C</sup> Contractual Standards</li>
        </ul>
       
      </div>
      <div class="col-md-8">
            <ul class="list-style">
            	<div class="org-dot-1"></div>
                <li class="sub-style">Standard for which dealership has been given</li>
                <li class="sub-style-2">implementation timeline of ${phase } ${year }</li>
            </ul>
           
        </div>
  </div>
				
         </div>
               
               
      </div>
      </c:if>
      <!-- --------------------------------------- After Sales --------------------------------- -->
      <c:if test="${BMWServiceOutletList!='[]'}"> 
      <div class="container" style="width: 21cm; width:12in; margin: 3cm;" >
         <div class="col-md-2 pull-right">
            <ul class="bg-dark-grey list-type out-round">
               <h4>OUTLETS</h4>
               <c:forEach var="qBean" items="${BMWServiceOutletList}" varStatus="loop" >
                  <li><%-- ${loop.index+1}. --%> ${qBean.outlets }</li>
               </c:forEach>
            </ul>
         </div>
         <h2 class="head-text left"><strong>DEALER STANDARDS ${phase} - ${year} :After sales Observations<br>${dealership_name }</strong></h2>
         <div class="clearfix"></div>
         <div class="col-md-12">
            <table class="table table-bordered">
               <tr>
                  <th colspan="12" class="text-center bg-dark-grey">DEALER STANDARDS SCORE - AFTER SALES</th>
               </tr>
               <tr class="bg-light-grey">
                  <td class="text-center"></td>
                  <td class="text-center">ESSENTIAL (Target value = 100%)</td>
                  <td class="text-center">CONTRACTUAL (Target value &ge; 85%)</td>
               </tr>
               <tr class="bg-dark">
                  <td class="text-center">Overall Score  ${dealership_name }</td>
                  <td class="text-center">${overall_ess_score_service_bmw }%</td>
                  <td class="text-center  ">${overall_cont_score_service_bmw }%</td>
               </tr>
               <c:forEach var="qBean" items="${TotalAvgreportsService}" varStatus="loop" >
                  <tr class="text-center bg-lite">
                     <td><%-- ${loop.index+1}. --%>${qBean.outlets }</td>
                     <td>${qBean.essential }</td>
                     <td>${qBean.contractual }</td>
                  </tr>
               </c:forEach>
            </table>
         </div>
         <div class="col-md-12 penservice">
            <table class="table table-bordered">
               <tr>
                  <td class="bg-grey txt-grey  cone-shape" rowspan="3" style="width:15%; padding: 60px 20px 20px 50px;">Section</td>
                  <th class="text-center bg-dark-grey" rowspan="3" style="padding:60px 0 0 0;">Penalized Standards</th>
                  <th colspan="8"  class="text-center bg-dark-grey">Defaulting Outlet</th>
               </tr>
               <tr>
                  <c:forEach var="qBean" items="${BMWServiceOutletList}" varStatus="loop" >
                     <th colspan="2" class="text-center bg-dark-grey"> ${qBean.outlets }</th>
                  </c:forEach>
               <tr>
                  <c:forEach var="qBean" items="${BMWServiceOutletList}" varStatus="loop" >
                     <th colspan="1">H1'${yr}</th>
                     <th colspan="1">H2'${yr}</th>
                  </c:forEach>
               </tr>
               <c:forEach var="qBean" items="${PenalizedListService}" varStatus="loop">
                  <tr class="bg-grey">
                     <td class="txt-grey size pensecservice" style="width:10px;">${qBean.section}</td>
                   <td class="size"><span style="padding-left:35px;"><span class=" mr-ryt"><span class="big-txt">${qBean.number}</span><sup>${qBean.essential}</sup></span></span><span class="bk-text" ><span class="standard-class">${qBean.standard}</span> - <a id="ad-comment" href="#bottom2">${qBean.auditor_comment}</a></span></td> 
                   
                     <c:forEach var="oBean" items="${BMWServiceOutletList}" varStatus="loop" >
                     <c:if test="${qBean.phase=='H1'}">
                     <c:choose>
                           <c:when test="${oBean.outlet_id==qBean.outlet_id || oBean.outlet_id==qBean.outlets || oBean.outlet_id==qBean.ph}">
                           <th colspan="1" align="center" style="width:20px;padding:0px 0px 0 19px;" align="center" valign="middle" class="v-aligin">
                                 <div class="red-dot"></div>
                              </th>
                              <th style="width:20px;"colspan="1" class="v-aligin"></th>
                           </c:when>
                           <c:otherwise> 
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                           </c:otherwise>
                        </c:choose>
                     </c:if>
                     <c:if test="${qBean.phase=='H2'}">
                     <c:choose>
                           <c:when test="${oBean.outlet_id==qBean.outlet_id || oBean.outlet_id==qBean.outlets || oBean.outlet_id==qBean.ph}">
                           <th style="width:20px;"colspan="1" align="center" valign="middle" class="v-aligin"></th>
                           <th colspan="1" align="center" style="width:20px;padding:0px 0px 0 19px;" align="center" valign="middle" class="v-aligin">
                                 <div class="red-dot"></div>
                              </th>
                              
                           </c:when>
                           <c:otherwise>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                           </c:otherwise>
                        </c:choose>
                     </c:if>
                        
                     </c:forEach>
                  </tr>
               </c:forEach>
            </table>
            <hr>
  <div class="row">
      <div class="col-md-4">
        <ul class="list-style">
            <li class="sub-style"><sup>X</sup> Essential Standards</li>
            <li class="sub-style"><sup>C</sup> Contractual Standards</li>
        </ul>
       
      </div>
      <div class="col-md-8">
            <ul class="list-style">
            	<div class="red-dot-1"></div>
                <li class="sub-style">Standard for which dealership has been</li>
                <li class="sub-style-2">penalized basis the audit observations</li>
            </ul>
           
        </div>
  </div>
         </div>
         
      </div>
     </c:if> 
     
     
      <c:if test="${areaImpListService!='[]'}">
      <div class="container aoi-service" style="width: 21cm;width:12in; margin: 3cm;"> 
         <div class="col-md-2 pull-right">
            <ul class="bg-dark-grey list-type out-round">
               <h4>OUTLETS</h4>
               <c:forEach var="qBean" items="${BMWServiceOutletList}" varStatus="loop" >
                  <li><%-- ${loop.index+1}. --%> ${qBean.outlets }</li>
               </c:forEach>
            </ul>
         </div>
         <h2 class="head-text left"><strong>DEALER STANDARDS ${phase} - ${year} :After sales Observations<br>${dealership_name }</strong></h2>
         <div class="clearfix"></div>
         <div class="col-md-12">
            <table class="table table-bordered">
               <!-- <tr>
                  <td class="bg-grey txt-grey cone-shape" style="padding:60px 20px 20px 50px;width: 15%;" rowspan="3">Section</td>
                  <th class="text-center bg-dark-grey" rowspan="3" style="padding:60px 0 0 0;">Areas for Improvements</th>                  
                  <th colspan="5"  class="text-center bg-dark-grey">Defaulting Outlet</th>
               </tr> -->
               <tr>
               		<th rowspan="3" style="padding:60px 20px 10px 50px;width: 15%;position: relative;left: 0px;" class="text-center bg-grey txt-grey cone-shape-1">Section</th>
               		<th class="text-center bg-dark-grey v-aligin" colspan="3">Areas for Improvements</th>
               		<th class="text-center bg-dark-grey v-aligin" colspan="8" height="74">Defaulting Outlet</th>
               </tr>
               <tr>
               		<th colspan="3" class="text-center bg-dark-grey">Action Items</th>   
               	 <c:forEach var="qBean" items="${BMWServiceOutletList}" varStatus="loop" >
                     <th colspan="2" class="text-center bg-dark-grey"> ${qBean.outlets }</th>
                  </c:forEach>      		
               </tr>
               <tr>
               		<th class="text-center bg-grey">Standard</th>
               		<th class="text-center bg-grey">Timeline remark</th>
               		<th class="text-center bg-grey"> Date </th>
               		<c:forEach var="qBean" items="${BMWServiceOutletList}" varStatus="loop" >
                     <th colspan="1">H1'${yr}</th>
                     <th colspan="1">H2'${yr}</th>
                  </c:forEach>
               </tr>
               <c:forEach var="qBean" items="${areaImpListService}" varStatus="loop">
               <tr class="bg-grey">
               		<td class="txt-grey size aoiservice" style="width:10px;">${qBean.section}</td> 
               		<th><span class="mr-ryt"><span class="big-txt">${qBean.number}</span><sup>${qBean.essential}</sup></span>
               		<span class="bk-text">
               		<span id="ad-comment">${qBean.standard}</span></span></th>
               		<th id="ad-comment">${qBean.exception_remarks }</th>
               		<th id="ad-comment">${qBean.time }</th>
               		<c:forEach var="oBean" items="${BMWServiceOutletList}" varStatus="loop" >
               		 <c:if test="${qBean.phase=='H1'}">
               		 <c:choose>
                           <c:when test="${oBean.outlet_id==qBean.outlet_id}">
                              <th style="width:20px;"colspan="1" align="center" valign="middle" class="v-aligin"></th>
                              <th style="width:20px;" colspan="1" align="center" class="v-aligin">
                                 <div class="org-dot"></div>
                              </th>
                           </c:when>
                           <c:otherwise>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                           </c:otherwise>
                        </c:choose>
               		 </c:if>
               		 <c:if test="${qBean.phase=='H2'}">
               		 <c:choose>
                           <c:when test="${oBean.outlet_id==qBean.outlet_id}">
                              <th style="width:20px;"colspan="1" align="center" valign="middle" class="v-aligin"></th>
                              <th style="width:20px;" colspan="1" align="center" class="v-aligin">
                                 <div class="org-dot"></div>
                              </th>
                           </c:when>
                           <c:otherwise>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                           </c:otherwise>
                        </c:choose>
               		 </c:if>
                        
                     </c:forEach>
				</tr>
				</c:forEach>
				</table>
				<hr>
  <div class="row">
      <div class="col-md-4">
        <ul class="list-style">
            <li class="sub-style"><sup>X</sup> Essential Standards</li>
            <li class="sub-style"><sup>C</sup> Contractual Standards</li>
        </ul>
       
      </div>
      <div class="col-md-8">
            <ul class="list-style">
            	<div class="org-dot-1"></div>
                 <li class="sub-style">Standard for which dealership has been given</li>
                <li class="sub-style-2">implementation timeline of ${phase } ${year }</li>
            </ul>
           
        </div>
  </div>
         </div>
        </div>
        </c:if>
         <br><br><br><br>
          <c:if test="${PenalizedList!='[]'}">
          <div class="container annexure-a"  style="margin-top:70px;margin-top: 70px; width: 21cm;width:12in; margin: 3cm;"> 
           <h2 class="head-text left annexure"><strong>Annexure "A" (Sales)<br></strong></h2> 
          <!--  <h2 class="head-text left annexure"><strong>Annexure "A" (Sales)</strong></h2> -->
         <div class="clearfix"></div>
       
         <div class="col-md-12">
           <table id="bottom">
         <tr>
         <th class="bg-grey txt-grey outline tt " style="padding: 1px 26px 0px 8px;">Std. No.</th>
         <th class="bg-grey txt-grey outline tt" style="width: 25%;">Std. Name</th>
         <th class="bg-grey txt-grey outline tt" style="width: 50%;">Question</th>
         <th class="bg-grey txt-grey outline tt" style="width: 40%;">Observation</th>
         <th class="bg-grey txt-grey outline tt" style="width: 12%;">Outlet </th>	
          <th class="bg-grey txt-grey outline tt" style="width: 12%;">Image </th>
         </tr>
         <c:forEach var="qBean" items="${BMWAnnexurePenalizedList}" varStatus="loop">
         <tr>
         <th colspan="1" class="bg-grey  outline family ann-sales">${qBean.number }</th>
         <th colspan="1" class="bg-grey  outline family">${qBean.standard }</th>
         <th class="bg-grey  outline family">${qBean.question }</th>
         <th class="bg-grey  outline family">${qBean.auditor_comment }</th>
         <th class="bg-grey  outline family">${qBean.outlets } </th>
         <th class="bg-grey  outline "><img src="<%=UI_PATH %>uploads/questionnairy_images/${year }/${phase }/${qBean.reference_image}" alt="Image unvailable" height="60" width="60"></th>
         </tr>
         </c:forEach>
         </table>
         </div> 
         </div>
        </c:if>
         <br><br><br><br>
         <c:if test="${PenalizedListService!='[]'}">
         <div class="container annexure-b"  style="margin-top:70px;margin-top: 70px; width: 21cm;width:12in; margin: 3cm;"> 
         <h2 class="head-text left annexure"><strong>Annexure "B" (After Sales)</strong></h2>
         <div class="clearfix"></div>
       	
         <div class="col-md-12">
           <table id="bottom2">
         <tr>
        <th class="bg-grey txt-grey outline tt " style="padding: 1px 26px 0px 8px;">Std. No.</th>
         <th class="bg-grey txt-grey outline tt" style="width: 25%;">Std. Name</th>
         <th class="bg-grey txt-grey outline tt" style="width: 50%;">Question</th>
         <th class="bg-grey txt-grey outline tt" style="width: 40%;">Observation</th>
         <th class="bg-grey txt-grey outline tt" style="width: 12%;">Outlet </th>	
         <th class="bg-grey txt-grey outline tt" style="width: 12%;">Image </th>	
         </tr>
         
         <c:forEach var="qBean" items="${BMWAnnexurePenalizedListSales}" varStatus="loop">
         <tr>
         <th colspan="1" class="bg-grey  outline family ann-service">${qBean.number }</th>
         <th colspan="1" class="bg-grey  outline family">${qBean.standard }</th>
         <th class="bg-grey  outline family">${qBean.question }</th>
         <th class="bg-grey  outline family">${qBean.auditor_comment }</th>
         <th class="bg-grey  outline family">${qBean.outlets } </th>
         <th class="bg-grey  outline "><img src="<%=UI_PATH %>uploads/questionnairy_images/${year }/${phase }/${qBean.reference_image}" alt="Image unvailable"  height="60" width="60"></th>
         </tr>
         </c:forEach>
         </table>
         </div> 
         
        
        
      </div>
      
      </c:if>
      <input type="hidden" value=${exist } id="mini_sales">
      <!-- MINI STARTS HERE -->
      
      <input type="hidden" id="minivalue" name="minivalue">
      
       <div class="container mini-container"  style="margin-top:70px;margin-top: 70px; width: 21cm;width:12in; margin: 3cm;"  >
         <div class="col-md-12">
         <span class="head-text main"><strong>DEALER STANDARDS ${phase} - ${year}<br>${dealership_name }</strong></span><img src="<%=UI_PATH %>assets/images/mini.svg" alt="BMW" id="mini-img" height="80" width="80">
            
            <table class="table table-bordered">
               <tr>
                  <th colspan="12" class="text-center bg-dark-grey">DEALER STANDARDS ${phase} - ${year} SCORE</th>
               </tr>
               <tr class="text-center bg-lite">
                  <!-- <td>Sales</td>
                  <td>After Sales</td> -->
               </tr>
               <tr class="text-center bg-grey">
                 <td>Sales-Contractual</td>
                  <td>After Sales-Contractual</td>
               </tr>
               <tr>
                  <td style="border-left-color:transparent;">
                     <div class="col-md-12 center-block">
                        <!--<div id="chartContainer1"></div>    -->   
                        <div id="barcontainer3" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                        <img alt="" src="" id="canvasimg3">
                       <!--  <h6 class="text-center">	</h6> -->
                        <ul class="bar-text text-center">
                           <li>
                              <div class="sqr-dot"></div>
                              ${dealership_name }
                           </li>
                           <li>
                              <div class="sqr-dot1"></div>
                              Highest in Network
                           </li>
                           <li>
                              <div class="sqr-dot2"></div>
                              Lowest in Network
                           </li>
                        </ul>
                     </div>
                  </td>
                  <td>
                     <div class="col-md-12">
                        <!-- <div id="chartContainer2"></div>                                             -->
                        <div id="" style="min-width: 310px; height: 400px; margin: 0 auto;padding-top: 180px;"><h6 class="text-center" id="to-be-bar">N/A</h6></div>
                       <!--  <h6 class="text-center">Contractual</h6> -->
                        <ul class="bar-text text-center" id="hidden">
                           <li>
                              <div class="sqr-dot"></div>
                              ${dealership_name }
                           </li>
                           <li>
                              <div class="sqr-dot1"></div>
                              Highest in Network
                           </li>
                           <li>
                              <div class="sqr-dot2"></div>
                              Lowest in Network
                           </li>
                        </ul>
                     </div>
                  </td>
               </tr>
            </table>
      <div class="row">
             <div class="col-md-6" >
             
      <div style="border:1px solid #c6c0c0;">
               <div class="col-md-3 bg-grey pull-right text-center fonts">
                  <h6>Rank: ${sales_rank_mini }/${dealer_sales_count_mini }</h6>
                  <h6>Score  <span id="sales_score_mini"></span></h6>
              
               </div>
               <div class="clearfix"></div>
               <div id="chartContainer3"></div>
            </div>
             </div>
            <div class="col-md-6">
            <!-- <div  style="border:1px solid #c6c0c0;">
               <div class="col-md-3 bg-grey pull-right text-center fonts">
                  <h6>Rank: -</h6>
                  <h6>Score: -<span id="service_score"></span></h6>
               </div>
              
               <div class="clearfix"></div>
               <div id="chartContainer2">
               <h6 class="text-center" id="to-be-bar">N/A</h6>
               </div> -->
           <div id="" style="min-width: 310px; height: 400px; margin: 0 auto;padding-top: 180px;"><h6 class="text-center" id="to-be-bar">N/A</h6></div>
               
            </div>
             </div>
      </div>
         </div>
      </div>
    
      
      
      <br><br>
       <c:if test="${MINISalesOutletList!='[]'}">
      <div class="container mini-container"  style="margin-top:70px;margin-top: 70px; width: 21cm;width:12in; margin: 3cm;" >
         <div class="col-md-2 pull-right">
            <ul class="bg-dark-grey list-type out-round">
               <h4>OUTLETS</h4>
               <c:forEach var="qBean" items="${MINISalesOutletList}" varStatus="loop" >
                  <li><%-- ${loop.index+1}. --%> ${qBean.outlets }</li>
               </c:forEach>
            </ul>
         </div>
         <h2 class="head-text left"><strong>DEALER STANDARDS ${phase} - ${year} : sales Observations<br>${dealership_name }</strong></h2>
         <div class="clearfix"></div>
         <div class="col-md-12">
            <table class="table table-bordered">
               <tr>
                  <th colspan="12" class="text-center bg-dark-grey">DEALER STANDARDS SCORE - SALES</th>
               </tr>
               <tr class="bg-light-grey">
                  <td class="text-center"></td>
                  <td class="text-center">ESSENTIAL (Target value = 100%)</td>
                  <td class="text-center">CONTRACTUAL (Target value &ge; 85%)</td>
               </tr>
               <tr class="bg-dark">
                  <td class="text-center">Overall Score  ${dealership_name }</td>
                  <td class="text-center">${overall_ess_score_mini }%</td>
                  <td class="text-center  ">${overall_cont_score_mini }%</td>
               </tr>
               <c:forEach var="qBean" items="${TotalAvgreportsMINI}" varStatus="loop" >
                  <tr class="text-center bg-lite">
                     <td><%-- ${loop.index+1}. --%>${qBean.outlets }</td>
                     <td>${qBean.essential }</td>
                     <td>${qBean.contractual }</td>
                  </tr>
               </c:forEach>
            </table>
         </div>
         <div class="col-md-12 mini-pen">
            <table class="table table-bordered">
               <tr>
                  <td class="bg-grey txt-grey cone-shape" style="width:15%;padding: 60px 20px 20px 50px;" rowspan="3"><span class=" ">Section</span></td>
                  <th class="text-center bg-dark-grey" rowspan="3" style="padding:60px 0 0 0;">Penalized Standards</th>
                  <th colspan="8"  class="text-center bg-dark-grey">Defaulting Outlet</th>
               </tr>
               <tr>
                  <c:forEach var="qBean" items="${MINISalesOutletList}" varStatus="loop" >
                     <th colspan="2" class="text-center bg-dark-grey">${qBean.outlets }</th>
                  </c:forEach>
               <tr>
                  <c:forEach var="qBean" items="${MINISalesOutletList}" varStatus="loop" >
                     <th  colspan="1">H1'${yr}</th>
                     <th colspan="1">H2'${yr}</th>
                  </c:forEach>
               </tr>
               <c:forEach var="qBean" items="${MINIPenalizedList}" varStatus="loop">
                  <tr class="bg-grey">
                     <td class="txt-grey size  mini-sec" style="width:10px;">${qBean.section}</td>
                     <td class="size"><span style="padding-left:35px;"><span class=" mr-ryt"><span class="big-txt">${qBean.number}</span><sup>1</sup></span></span><span class="bk-text" ><span class="standard-class">${qBean.standard}</span> - <a id="ad-comment" href="#bottom3">${qBean.auditor_comment}</a></span></td>
                     <%-- <td class="size"><span style="    padding-left: 35px;"><span class="mr-ryt"><span class="big-txt">${qBean.number}</span><sup>${qBean.essential}</sup></span></span><span class="bk-text">${qBean.auditor_comment}</span></td> --%>
                     <c:forEach var="oBean" items="${MINISalesOutletList}" varStatus="loop" >
                        <c:choose>
                           <c:when test="${oBean.outlet_id==qBean.outlet_id}">
                            <th style="width:20px;padding: 0px 0px 0 19px;" colspan="1" align="center" valign="middle" class="v-aligin">
                                 <div class="red-dot"></div>
                              </th>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                           </c:when>
                           <c:otherwise>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                           </c:otherwise>
                        </c:choose>
                     </c:forEach>
                  </tr>
               </c:forEach>
            </table>
            <hr>
  <div class="row">
      <div class="col-md-4">
        <ul class="list-style">
            <li class="sub-style"><sup>X</sup> Essential Standards</li>
            <li class="sub-style"><sup>C</sup> Contractual Standards</li>
        </ul>
       
      </div>
      <div class="col-md-8">
            <ul class="list-style">
            	<div class="red-dot-1"></div>
                <li class="sub-style">Standard for which dealership has been</li>
                <li class="sub-style-2">penalized basis the audit observations</li>
            </ul>
           
        </div>
  </div>
         </div>
         
      </div>
     </c:if>
      <br><br><br>
       <c:if test="${MINIareaImpList!='[]'}">
       
      <div class="container mini-aoi" style="width: 21cm;width:12in; margin: 3cm;">
         <div class="col-md-2 pull-right">
            <ul class="bg-dark-grey list-type out-round">
               <h4>OUTLETS</h4>
               <c:forEach var="qBean" items="${MINISalesOutletList}" varStatus="loop" >
                  <li><%-- ${loop.index+1}.  --%>${qBean.outlets }</li>
               </c:forEach>
            </ul>
         </div>
         <h2 class="head-text left"><strong>DEALER STANDARDS ${phase} - ${year} : sales Observations<br>${dealership_name }</strong></h2>
         <div class="clearfix"></div>
         
         <div class="col-md-12">
            <table class="table table-bordered">
               <!-- <tr>
                  <td class="bg-grey txt-grey cone-shape" style="padding:60px 20px 20px 50px;width: 15%;" rowspan="3">Section</td>
                  <th class="text-center bg-dark-grey" rowspan="3" style="padding:60px 0 0 0;">Areas for Improvements</th>                  
                  <th colspan="5"  class="text-center bg-dark-grey">Defaulting Outlet</th>
               </tr> -->
               <tr>
               		<th rowspan="3" style="padding:60px 20px 10px 50px;width: 15%;position: relative;left: 0px;" class="text-center bg-grey txt-grey cone-shape-1">Section</th>
               		<th class="text-center bg-dark-grey v-aligin" colspan="3">Areas for Improvements</th>
               		<th class="text-center bg-dark-grey v-aligin" colspan="8" height="74">Defaulting Outlet</th>
               </tr>
               <tr>
               		<th colspan="3" class="text-center bg-dark-grey">Action Items</th>   
               	 <c:forEach var="qBean" items="${MINISalesOutletList}" varStatus="loop" >
                     <th colspan="2" class="text-center bg-dark-grey"> ${qBean.outlets }</th>
                  </c:forEach>      		
               </tr>
               <tr>
               		<th class="text-center bg-grey">Standard</th>
               		<th class="text-center bg-grey">Timeline remark</th>
               		<th class="text-center bg-grey"> Date </th>
               		<c:forEach var="qBean" items="${MINISalesOutletList}" varStatus="loop" >
                     <th colspan="1">H1'${yr}</th>
                     <th colspan="1">H2'${yr}</th>
                  </c:forEach>
               </tr>
               <c:forEach var="qBean" items="${MINIareaImpList}" varStatus="loop">
               <tr class="bg-grey">
               		<td class="txt-grey size mini-secc" style="width:10px;">${qBean.section}</td> 
               		<th><span class="mr-ryt"><span class="big-txt">${qBean.number}</span><sup>${qBean.essential}</sup></span>
               		<span class="bk-text">
               		<span id="ad-comment">${qBean.standard}</span></span></th>
               		<th id="ad-comment">${qBean.exception_remarks }</th>
               		<th id="ad-comment">${qBean.time }</th>
               		<c:forEach var="oBean" items="${MINISalesOutletList}" varStatus="loop" >
                        <c:choose>
                           <c:when test="${oBean.outlet_id==qBean.outlet_id}">
                              <th style="width:20px;"colspan="1" align="center" valign="middle" class="v-aligin"></th>
                              <th style="width:20px;" colspan="1" align="center"  valign="middle" class="v-aligin">
                                 <div class="org-dot"></div>
                              </th>
                           </c:when>
                           <c:otherwise>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                              <th style="width:20px;" colspan="1" class="v-aligin"></th>
                           </c:otherwise>
                        </c:choose>
                     </c:forEach>
				</tr>
				</c:forEach>
				</table>
				<hr>
  <div class="row">
      <div class="col-md-4">
        <ul class="list-style">
            <li class="sub-style"><sup>X</sup> Essential Standards</li>
            <li class="sub-style"><sup>C</sup> Contractual Standards</li>
        </ul>
       
      </div>
      <div class="col-md-8">
            <ul class="list-style">
            	<div class="org-dot-1"></div>
                 <li class="sub-style">Standard for which dealership has been given</li>
                <li class="sub-style-2">implementation timeline of ${phase } ${year }</li>
            </ul>
           
        </div>
  </div>
         </div>
         </div>
       
       </c:if>
       
       <c:if test="${MINIPenalizedList!='[]'}">
       
        <div class="container annexure-mini-a"   style="width: 21cm;width:12in; margin: 3cm;" >
         <h2 class="head-text left annexure"><strong>Annexure "A" (Sales)</strong></h2>
         <div class="clearfix"></div>
       
         <div class="col-md-12">
           <table id="bottom3">
         <tr>
        <th class="bg-grey txt-grey outline tt "  style="padding: 1px 26px 0px 8px;;">Std. No.</th>
         <th class="bg-grey txt-grey outline tt" style="width: 25%;">Std. Name</th>
         <th class="bg-grey txt-grey outline tt" style="width: 50%;">Question</th>
         <th class="bg-grey txt-grey outline tt" style="width: 40%;">Observation</th>
         <th class="bg-grey txt-grey outline tt" style="width: 12%;">Outlet </th>	
         <th class="bg-grey txt-grey outline tt" style="width: 12%;">Image </th>	
         </tr>
         <c:forEach var="qBean" items="${MINIAnnexurePenalizedList}" varStatus="loop">
         <tr>
         <th colspan="1" class="bg-grey  outline family mini-ann">${qBean.number }</th>
         <th colspan="1" class="bg-grey  outline family">${qBean.standard }</th>
         <th class="bg-grey  outline family">${qBean.question }</th>
         <th class="bg-grey  outline family">${qBean.auditor_comment }</th>
         <th class="bg-grey  outline family">${qBean.outlets } </th>
         <th class="bg-grey  outline "><img src="<%=UI_PATH %>uploads/questionnairy_images/${year }/${phase }/${qBean.reference_image}" alt="Image unvailable" height="60" width="60"></th>
         </tr>
         </c:forEach>
         </table>
         </div>
         
      </div>
</c:if>     
      </div>
     </form> 
      
      
      
         
      <input type="hidden" id="did" value="${decrypted_country_id}">
      <input type="hidden" id="year" value="${year}">
      <input type="hidden" id="phase" value="${phase}">
      <script src="<%=UI_PATH %>assets/reports/js/jquery.min.js"></script>    
      <script src="<%=UI_PATH %>assets/reports/js/bootstrap.min.js"></script>    
      <script src="<%=UI_PATH %>assets/reports/js/canvasjs.min.js"></script>
      <script src="<%=UI_PATH %>assets/reports/js/charts.js"></script>
<%--       <script src="<%=UI_PATH %>assets/reports/js/barchart/highcharts.js"></script>
 --%>      <script src="<%=UI_PATH %>assets/reports/js/barchart/modules/data.js"></script>
      <script src="<%=UI_PATH %>assets/reports/js/barchart/modules/drilldown.js"></script>
      <script src="<%=UI_PATH %>assets/reports/js/barchart.js"></script>
      
      
<script src="https://code.highcharts.com/highcharts.js"></script>  
 <script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.5.0-beta4/html2canvas.svg.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>
    <script src="https://code.highcharts.com/modules/exporting.js"></script>
      
      
      <script>
      
      $(document).ready(function(){
    	  var min = $("#mini_sales").val();
    	  
    	  if(min=="0")
    		  {
    		  $(".mini-container").hide();
    		  }
    	  else
    		  {
    		  $(".mini-container").show();
    		  }
    	  
    	  if($(".mini-ann").html()=="" || $(".mini-ann").html()==undefined)
		  {
		  $(".annexure-mini-a").hide();
		  }
	  else
		  {
		  $(".annexure-mini-a").show();
		  }
    	  
    	  if($(".ann-service").html()=="" || $(".ann-service").html()==undefined)
		  {
		  $(".annexure-b").hide();
		  }
	  else
		  {
		  $(".annexure-b").show();
		  }
    	  if($(".ann-sales").html()=="" || $(".ann-sales").html()==undefined)
		  {
		  $(".annexure-a").hide();
		  }
	  else
		  {
		  $(".annexure-a").show();
		  }
    	  if($(".mini-secc").html()=="" || $(".mini-secc").html()==undefined)
		  {
		  $(".mini-aoi").hide();
		  }
	  else
		  {
		  $(".mini-aoi").show();
		  }
    	  
    	  if($(".aoiservice").html()=="" || $(".aoiservice").html()==undefined)
		  {
		  $(".aoi-service").hide();
		  }
	  else
		  {
		  $(".aoi-service").show();
		  }
    	  if($(".mini-sec").html()=="" || $(".mini-sec").html()==undefined)
		  {
		  $(".mini-pen").hide();
		  }
	  else
		  {
		  $(".mini-pen").show();
		  }
    	  
    	  if($(".pensecservice").html()=="" || $(".pensecservice").html()==undefined)
		  {
		  $(".penservice").hide();
		  }
	  else
		  {
		  $(".penservice").show();
		  }
    	  
    	  
    	  if($(".sec").html()=="" || $(".sec").html()==undefined)
    		  {
    		  $(".penal").hide();
    		  }
    	  else
    		  {
    		  $(".penal").show();
    		  }
    	  //alert($(".aoiSection").html());
    	  if($(".aoiSection").html()=="" || $(".aoiSection").html()==undefined )
		  {
		  $(".aoiSales").hide();
		  }
	  else
		  {
		  $(".aoiSales").show();
		  }
    	  
    	  
    	  var $link = $("#ad-comment");
    	  var $emailInput = $("#annexure-sales");

    	  $link.on("click", function(){
    	      $emailInput.focus();
    	  });
    	});
      </script>
      <script>
      // Import DejaVu Sans font for embedding

      // NOTE: Only required if the Kendo UI stylesheets are loaded
      // from a different origin, e.g. cdn.kendostatic.com
      kendo.pdf.defineFont({
          "DejaVu Sans"             : "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans.ttf",
          "DejaVu Sans|Bold"        : "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Bold.ttf",
          "DejaVu Sans|Bold|Italic" : "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Oblique.ttf",
          "DejaVu Sans|Italic"      : "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Oblique.ttf",
          "WebComponentsIcons"      : "https://kendo.cdn.telerik.com/2017.1.223/styles/fonts/glyphs/WebComponentsIcons.ttf"
      });
  </script>

  <!-- Load Pako ZLIB library to enable PDF compression -->
  <script src="https://kendo.cdn.telerik.com/2017.3.913/js/pako_deflate.min.js"></script>


  <script>
  $(document).ready(function() {



	  

	  $(".export-pdf").click(function() {
		  
	  save_chart1($('#barcontainer1').highcharts());
	    save_chart2($('#barcontainer2').highcharts());
	    save_chart3($('#barcontainer3').highcharts());
	  
	    
	    setTimeout(function(){ 
	    	call() }, 1000); 
  	  
	  });
	  
    	  function call(){
    		  
          // Convert the DOM element to a drawing using kendo.drawing.drawDOM
          kendo.drawing.drawDOM($(".content-wrapper"))
          .then(function(group) {
              // Render the result as a PDF file
              return kendo.drawing.exportPDF(group, {
                  paperSize: "auto",
                  margin: { left: "1cm", top: "1cm", right: "1cm", bottom: "1cm" }
              });
          })
          .done(function(data) {
              // Save the PDF file
              kendo.saveAs({
                  dataURI: data,
                  fileName: "Dealer_Report.pdf",
                  proxyURL: "https://demos.telerik.com/kendo-ui/service/export"
              });
          });
          
          setTimeout(function(){ 
     		 location.reload(); }, 2000); 
    	  }

      $(".export-img").click(function() {
          // Convert the DOM element to a drawing using kendo.drawing.drawDOM
          kendo.drawing.drawDOM($(".content-wrapper"))
          .then(function(group) {
              // Render the result as a PNG image
              return kendo.drawing.exportImage(group);
          })
          .done(function(data) {
              // Save the image file
              kendo.saveAs({
                  dataURI: data,
                  fileName: "Dealer_Report.png",
                  proxyURL: "https://demos.telerik.com/kendo-ui/service/export"
              });
          });
      });

      $(".export-svg").click(function() {
          // Convert the DOM element to a drawing using kendo.drawing.drawDOM
          kendo.drawing.drawDOM($(".content-wrapper"))
          .then(function(group) {
              // Render the result as a SVG document
              return kendo.drawing.exportSVG(group);
          })
          .done(function(data) {
              // Save the SVG document
              kendo.saveAs({
                  dataURI: data,
                  fileName: "Dealer_Report.svg",
                  proxyURL: "https://demos.telerik.com/kendo-ui/service/export"
              });
          });
      });


      var data = [{
        "source": "Approved",
        "percentage": 237
      }, {
        "source": "Rejected",
        "percentage": 112
      }];

      var refs = [{
        "source": "Dev",
        "percentage": 42
      }, {
        "source": "Sales",
        "percentage": 18
      }, {
        "source": "Finance",
        "percentage": 29
      }, {
        "source": "Legal",
        "percentage": 11
      }];

      $("#applications").kendoChart({
        legend: {
          position: "bottom"
        },
        dataSource: {
          data: data
        },
        series: [{
          type: "donut",
          field: "percentage",
          categoryField: "source"
        }],
        chartArea: {
            background: "none"
        },
        tooltip: {
          visible: true,
          template: "${ category } - ${ value } applications"
        }
      });

      $("#users").kendoChart({
          legend: {
              visible: false
          },
          seriesDefaults: {
              type: "column"
          },
          series: [{
              name: "Users Reached",
              data: [340, 894, 1345, 1012, 3043, 2013, 2561, 2018, 2435, 3012]
          }, {
              name: "Applications",
              data: [50, 80, 120, 203, 324, 297, 176, 354, 401, 349]
          }],
          valueAxis: {
              labels: {
                  visible: false
              },
              line: {
                  visible: false
              },
              majorGridLines: {
                  visible: false
              }
          },
          categoryAxis: {
              categories: [2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011],
              line: {
                  visible: false
              },
              majorGridLines: {
                  visible: false
              }
          },
          chartArea: {
              background: "none"
          },
          tooltip: {
              visible: true,
              format: "{0}",
              template: "#= series.name #: #= value #"
          }
      });

      $("#referrals").kendoChart({
        legend: {
          position: "bottom"
        },
        dataSource: {
          data: refs
        },
        series: [{
          type: "pie",
          field: "percentage",
          categoryField: "source"
        }],
        chartArea: {
            background: "none"
        },
        tooltip: {
          visible: true,
          template: "${ category } - ${ value }%"
        }
      });

      $("#grid").kendoGrid({
        dataSource: {
          type: "odata",
          transport: {
            read: "https://demos.telerik.com/kendo-ui/service/Northwind.svc/Customers"
          },
          pageSize: 15,
          group: { field: "ContactTitle" }
        },
        height: 450,
        groupable: true,
        sortable: true,
        selectable: "multiple",
        reorderable: true,
        resizable: true,
        filterable: true,
        pageable: {
          refresh: true,
          pageSizes: true,
          buttonCount: 5
        },
        columns: [
          {
            field: "ContactName",
            template: "<div class=\'customer-name\'>#: ContactName #</div>",
            title: "Contact",
            width: 200
          },{
            field: "ContactTitle",
            title: "Contact Title",
            width: 220
          },{
            field: "Phone",
            title: "Phone",
            width: 160
          },{
            field: "CompanyName",
            title: "Company Name"
          },{
            field: "City",
            title: "City",
            width: 160
          }
        ]
      });
     });
  
  
  
  EXPORT_WIDTH = 1;

  function save_chart1(chart) {
      var render_width = EXPORT_WIDTH;
      var render_height = render_width * chart.chartHeight / chart.chartWidth

      // Get the cart's SVG code
      var svg = chart.getSVG({
          exporting: {
              sourceWidth: chart.chartWidth,
              sourceHeight: chart.chartHeight
          }
      });

      // Create a canvas
      var canvas = document.createElement('canvas');
      canvas.height = render_height;
      canvas.width = render_width;
      document.body.appendChild(canvas);

      // Create an image and draw the SVG onto the canvas
      var image = new Image;
      image.onload = function() {
          canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
      };
     $("#barcontainer1").hide();
      $("#canvasimg1").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));
      
  }

  function save_chart2(chart) {
	  
      var render_width = EXPORT_WIDTH;
      if(chart){
      var render_height = render_width * chart.chartHeight / chart.chartWidth
      

      // Get the cart's SVG code
      var svg = chart.getSVG({
          exporting: {
              sourceWidth: chart.chartWidth,
              sourceHeight: chart.chartHeight
          }
      });

      // Create a canvas
      var canvas = document.createElement('canvas');
      canvas.height = render_height;
      canvas.width = render_width;
      document.body.appendChild(canvas);

      // Create an image and draw the SVG onto the canvas
      var image = new Image;
      image.onload = function() {
          canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
      };
     $("#barcontainer2").hide();
      $("#canvasimg2").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));
}else{
    	  
      }
      
  }
  function save_chart3(chart) {
      var render_width = EXPORT_WIDTH;
      if(chart){
          var render_height = render_width * chart.chartHeight / chart.chartWidth
        
      //var render_height = render_width * chart.chartHeight / chart.chartWidth

      // Get the cart's SVG code
      var svg = chart.getSVG({
          exporting: {
              sourceWidth: chart.chartWidth,
              sourceHeight: chart.chartHeight
          }
      });

      // Create a canvas
      var canvas = document.createElement('canvas');
      canvas.height = render_height;
      canvas.width = render_width;
      document.body.appendChild(canvas);

      // Create an image and draw the SVG onto the canvas
      var image = new Image;
      image.onload = function() {
          canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
      };
     $("#barcontainer3").hide();
      $("#canvasimg3").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));
      }else{
    	
      }
      
  }
  
  </script>

  <style>
  .export-app {
      display: table;
      width: 100%;
      height: 750px;
      padding: 0;
  }

  .export-app .demo-section {
      margin: 0 auto;
      border: 0;
  }

  .content-wrapper {
      display: table-cell;
      vertical-align: top;
  }

  .charts-wrapper {
      height: 250px;
      padding: 0 0 20px;
  }

  #applications,
  #users,
  #referrals {
      display: inline-block;
      width: 50%;
      height: 240px;
      vertical-align: top;
  }
  #applications,
  #referrals {
      width: 24%;
      height: 250px;
  }

  .customer-photo {
      display: inline-block;
      width: 40px;
      height: 40px;
      border-radius: 50%;
      background-size: 40px 44px;
      background-position: center center;
      vertical-align: middle;
      line-height: 41px;
      box-shadow: inset 0 0 1px #999, inset 0 0 10px rgba(0,0,0,.2);
  }
  .customer-name {
      display: inline-block;
      vertical-align: middle;
      line-height: 41px;
      padding-left: 10px;
  }
  .demo-section.content-wrapper,
  .demo-section.k-content.export-app {
      display: block !important;
  }
  .demo-section.export-app {
      height: auto;
  }
  </style>
      <script>
         function getReportsBySalesBMW()
         {
         	var did = $("#did").val();
         	var year = $("#year").val();
         	var phase = $("#phase").val(); 
         	var brand="BMW";
         	$.ajax({
                 url: "<%=dashboardURL%>getReportsOfDealers",
                 type: "GET", 
                   data: { 'did': did,'year':year,'phase':phase,'brand':brand },
                 success: function(response)
                             {
                 	//alert(response);
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
                            	     	   color: "#737373",
                            	     	  indexLabelFontWeight: "bold",
                            	     	 exploded: true
                            	      });
                         		   }
                         	   else
                         	{
                         	   dataPoints.push({
                         		   y: 100,
                         		   percent: jsonString[i].score,
                        	           label: jsonString[i].section,
                        	     	   color: "#f7e727",
                        	     	  indexLabelFontWeight: "bold",
                        	     	 exploded: true
                        	      });
                         	}
                            //$(".abc").append(jsonString[i].section+"#"); 
                            var chart1 = new CanvasJS.Chart("chartContainer1", {
                            	
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
         
                         	chart1.render();
                            }
                                                
                             }
                 });  
         	
         	
         	var brand="MINI";
         	$.ajax({
                 url: "<%=dashboardURL%>getReportsOfDealers",
                 type: "GET", 
                   data: { 'did': did,'year':year,'phase':phase,'brand':brand },
                 success: function(response)
                             {
                 	//alert(response);
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
                            	     	   color: "#737373",
                            	     	  exploded: true
                            	      });
                         		   }
                         	   else
                         	{
                         	   dataPoints.push({
                         		   y: 100,
                         		   percent: jsonString[i].score,
                        	           label: jsonString[i].section,
                        	     	   color: "#f7e727",
                        	     	  exploded: true
                        	      });
                         	}
                            //$(".abc").append(jsonString[i].section+"#"); 
                            var chart1 = new CanvasJS.Chart("chartContainer3", {
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
         
                         	chart1.render();
                            }
                                                
                             }
                 });
         	
         	
         }
         
         
      </script>
      <script>
         function getReportsByServiceBMW()
         {
         	var did = $("#did").val();
         	var year = $("#year").val();
         	var phase = $("#phase").val(); 
         	var brand="BMW";
         	
         	$.ajax({
                 url: "<%=dashboardURL%>getReportsOfDealersByService",
                 type: "GET", 
                   data: { 'did': did,'year':year,'phase':phase,'brand':brand },
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
                            	     	   color: "#737373",
                            	     	  indexLabelFontWeight: "bold",
                            	     	 exploded: true
                            	      });
                         		   }
                         	   else
                         	{
                         	   dataPoints.push({
                         		   y: 100,
                         		   percent: jsonString[i].score,
                        	           label: jsonString[i].section,
                        	     	   color: "#f7e727",
                        	     	  indexLabelFontWeight: "bold",
                        	     	 exploded: true
                        	      });
                         	}
                            //$(".abc").append(jsonString[i].section+"#"); 
                 	var chart2 = new CanvasJS.Chart("chartContainer2", {
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
                 chart2.render();
         
                 }
                                                
                             }
                 });  
         	
         }
         
         
      </script>
      <script>
         function getContractualBySalesBMW()
         {
           var did = $("#did").val();
           var year = $("#year").val();
           var phase = $("#phase").val(); 
           var brand = "BMW";
           
           $.ajax({
                 url: "<%=dashboardURL%>getContractualBySales",
                 type: "GET", 
                   data: { 'did': did,'year':year,'phase':phase,'brand':brand },
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
                            	
                         	   $("#sales_score").html(jsonString[i].avg+"%");
                              av=jsonString[i].avg;
                              mx=jsonString[i].max;
                              mn=jsonString[i].min;
                             
                              /*  alert(av);
                              alert(jsonString[0].avg+jsonString[0].max+jsonString[i].min);  */
                              Highcharts.chart('barcontainer1', {
                             	  chart: {
                             		    type: 'column' },
         
                             		  title: {
                             		    text: '' },
         
                             		  subtitle: {
                             		    text: '' },
                             		    
                             		   credits: {
                             		        enabled: false
                             		    },
         
                             		  xAxis: {
                             			 labels: {
                             	            enabled: false,
                             	           visible: false
                             	        },
                             		    type: 'category' },
         
                             		  yAxis: {
                             			
                             			visible: false,
                             		   labels: {
                       		            style: {
                       		                color: 'white'
                       		            }
                       		        }
                             		    },
                             		      
                             		      
                             		      legend: {
                             		    	    enabled: false },
         
                             		    	  plotOptions: {
                             		    	    series: {
                             		    	      borderWidth: 0,
                             		    	      dataLabels: {
                             		    	        enabled: true,
                             		    	        format: '{point.y}%' } } },
                             		    	        tooltip: {
                             		    	        	 pointFormat: '<b>{point.y}%</b><br/>' },
                             	 
         
           "series": [
           {
             
             "data": [
             {
               "y": parseFloat(av),
               "color": "#6e7d94" },
         
             {
               "y": parseFloat(mx),
               "color": "#dee4e6" },
         
             {
               "y": parseFloat(mn),
               "color": "#808080" }] }],
         
          }); 
                            }
                             }
           });
           
           
           var brand = "MINI";
           $.ajax({
               url: "<%=dashboardURL%>getContractualBySales",
               type: "GET", 
                 data: { 'did': did,'year':year,'phase':phase,'brand':brand },
               success: function(response)
                           {
            	  
                   var dataPoints = [];
                   var av;
                   var mx;
                   var mn;
                     var ContractualList=JSON.stringify(response);
                     var jsonString=JSON.parse(ContractualList);
                     console.log(jsonString);
                          for(var i=0;i<jsonString.length;i++)
                          {
                       	   $("#sales_score_mini").html(jsonString[i].avg+"%");
                            av=jsonString[i].avg;
                            mx=jsonString[i].max;
                            mn=jsonString[i].min;
                            $("#minivalue").val(mx);
                            
                            /*  alert(av);
                            alert(jsonString[0].avg+jsonString[0].max+jsonString[i].min);  */
                            Highcharts.chart('barcontainer3', {
                           	  chart: {
                           		    type: 'column' },
       
                           		  title: {
                           		    text: '' },
       
                           		  subtitle: {
                           		    text: '' },
                           		    
                           		 credits: {
                           	        enabled: false
                           	    },
       
                           		  xAxis: {
                           			 labels: {
                           	            enabled: false
                           	        },
                           		    type: 'category' },
       
                           		 yAxis: {
                         			 gridLineColor: 'transparent',
                         			visible: false,
                         		   labels: {
                   		            style: {
                   		                color: 'white'
                   		            }
                   		        }
                         		    },
                           		      legend: {
                           		    	    enabled: false },
       
                           		    	  plotOptions: {
                           		    	    series: {
                           		    	      borderWidth: 0,
                           		    	      dataLabels: {
                           		    	        enabled: true,
                           		    	        format: '{point.y}%' } } },
                           		    	        tooltip: {
                           		    	        	 pointFormat: '<b>{point.y}%</b><br/>' },
                           	 
       
         "series": [
         {
           
           "data": [
           {
             "y": parseFloat(av),
             "color": "#6e7d94" },
       
           {
             "y": parseFloat(mx),
             "color": "#dee4e6" },
       
           {
             "y": parseFloat(mn),
             "color": "#808080" }] }],
       
        }); 
                          }
                           }
         });
           
           
           }
      </script>
      <script>
         function getContractualByServicesBMW()
         {
           var did = $("#did").val();
           var year = $("#year").val();
           var phase = $("#phase").val(); 
           var brand = "BMW";
           
           $.ajax({
                 url: "<%=dashboardURL%>getContractualByServices",
                 type: "GET", 
                   data: { 'did': did,'year':year,'phase':phase,'brand':brand },
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
                         	   $("#service_score").html(jsonString[i].avg+"%");
                              av=jsonString[i].avg;
                              mx=jsonString[i].max;
                              mn=jsonString[i].min;
                              
                              Highcharts.chart('barcontainer2', {
                            	  chart: {
                            		    type: 'column' },
         
                            		  title: {
                            		    text: '' },
         
                            		  subtitle: {
                            		    text: '' },
                            		    
                            		    credits: {
                            		        enabled: false
                            		    },
         
                            		  xAxis: {
                            			  labels: {
                            		            enabled: false
                            		        },
                            		    type: 'category' },
         
                            		    yAxis: {
                            		    	gridLineColor: 'transparent',
                            		    	visible: false,
                            		        labels: {
                            		            style: {
                            		                color: 'white'
                            		            }
                            		        }
                            		    },
                            		      
                            		      
                            		      legend: {
                            		    	    enabled: false },
         
                            		    	  plotOptions: {
                            		    	    series: {
                            		    	      borderWidth: 0,
                            		    	      dataLabels: {
                            		    	        enabled: true,
                            		    	        format: '{point.y}%' } } },
                            		    	        tooltip: {
                            		    	        	 pointFormat: '<b>{point.y}%</b><br/>' },
                            	 
         
          "series": [
          {
            
            "data": [
            {
              "y": parseFloat(av),
              "color": "#6e7d94" },
         
            {
              "y": parseFloat(mx),
              "color": "#dee4e6" },
         
            {
              "y": parseFloat(mn),
              "color": "#808080" }] }],
         
         }); 
                           }
                   }
          });
           

          }
         
         getReportsBySalesBMW();
         getReportsByServiceBMW();
         getContractualBySalesBMW();
         getContractualByServicesBMW();
      </script>
      
      <!-- <script>
      var form = $('.form'),
      cache_width = form.width(),
    // a2= [1250 , 1825]; // for a4 size paper width and height
       a2= [1250, 1825];
  var canvasImage,
      winHeight = a2[1],
      formHeight = form.height(),
      formWidth  = form.width();

  var imagePieces = [];

  
  $('#create_pdf').on('click', function() {
	 
 
      $('body').scrollTop(0);
      imagePieces = [];
      imagePieces.length = 0;
      main();
  });

  // main code
  function main() {
      getCanvas().then(function(canvas){
          canvasImage = new Image();
          canvasImage.src= canvas.toDataURL('image/png', 1.0);
          canvasImage.onload = splitImage;
      });
  }
  
  

  // create canvas object
  function getCanvas() {
	  
      form.width((a2[0] * 1.333) - 280).css('max-width', 'none');
      return html2canvas(form, {
          imageTimeout: 500,
          removeContainer: true
      });
  }
 
  // chop image horizontally
  function splitImage(e) {
      var totalImgs = Math.round(formHeight/winHeight);
      
      for(var i = 0; i < totalImgs; i++) {
          var canvas = document.createElement('canvas'),
              ctx = canvas.getContext('2d');
          canvas.width = formWidth;
          canvas.height = winHeight;
          //                    source region                   dest. region
          ctx.drawImage(canvasImage, 0, i * winHeight, formWidth, winHeight, 0, 0, canvas.width, canvas.height,'FAST');

          imagePieces.push(canvas.toDataURL('image/png', 1.0));
      }
    //  alert(imagePieces.length);
      console.log(imagePieces.length);
      createPDF();
  }
  
  // crete pdf using chopped images
  function createPDF() {
	 
      var totalPieces = imagePieces.length - 1;
      
     
      var doc = new jsPDF({
          orientation:'portrait',
    	  unit: 'px',
          format: 'a3'
          
         
      });
      
      
      imagePieces.forEach(function(img){
        //  doc.addImage(img, 'JPEG', 20, 40);
        doc.addImage(img, 'JPEG', 20, 40);
          if(totalPieces)
              doc.addPage();
          totalPieces--;
          
      });
      doc.save('Graph.pdf');
      
      form.width(cache_width);      
      
  }
      </script> -->
      <style>
           fill: #fff;
      </style>
   </body>
</html>