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
</head>
    <body>
        <div class="container">
            <div class="col-md-2 pull-right">
                <ul class="bg-dark-grey list-type">
                  <h4>OUTLETS</h4>
                  <li>1. Faridabad</li>                  
                  <li>2. Moti Nagar</li>
                  <li>3. Noida</li>
                </ul>
            </div>
            <h2 class="head-text"><strong>Retail standards : after sales Observations<br>DEUTSCHE MOTOREN</strong></h2>
            <div class="clearfix"></div>
            <div class="col-md-7">
                <table class="table table-bordered">
                    <tr>
                        <td class="bg-grey txt-grey">Section</td>
                        <th class="text-center bg-dark-grey">Penalized Standards</th>
                    </tr>                    
                    <tr class="bg-grey">
                        <td class="txt-grey size">Warehouse Area</td>
                        <td class="size"><span><span class="big-txt mr-ryt">7.09<sup>2</sup></span>During sample review of 4 parts, noted that 2 out of 4 parts partially were not stored in assigned location</span></td>
                    </tr>                    
                </table>
            </div>
            <div class="col-md-5">
                <table class="table table-bordered">
                    <tr class="bg-dark-grey">
                        <th colspan="8" class="text-center">Defaulting Outlet</th>
                    </tr>
                    <tr class="bg-dark-grey">
                        <th colspan="2" class="text-center">Faridabad</th>                        
                        <th colspan="2" class="text-center">Moti Nagar</th>
                        <th colspan="2" class="text-center">Noida</th>
                    </tr>
                    <tr class="bg-dark-grey">                       
                        <th>H1â18</th>
                        <th>H2â18</th>
                        <th>H1â18</th>
                        <th>H2â18</th>
                        <th>H1â18</th>
                        <th>H2â18</th>
                    </tr>
                    <tr class="bg-grey">                        
                        <td><div class="red-dot"></div></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>                                                           
                </table>
            </div>
            <div class="col-md-12">
                <table class="table table-bordered">
                    <tr>
                        <th colspan="12" class="text-center bg-dark-grey">RETAIL STANDARDS SCORE â AFTER SALES</th>
                    </tr>
                    <tr class="bg-light-grey">
                        <td class="text-center"></td>
                        <td class="text-center">ESSENTIAL (Target value = 100%)</td>
                        <td class="text-center">CONTRACTUAL (Target value â¥ 85%)</td>
                    </tr>
                    <tr class="bg-dark">
                        <td class="text-center">Overall Score  Deutsche Motoren</td>
                        <td class="text-center">98.3%</td>
                        <td class="text-center">97.30%</td>
                    </tr>
                    <tr class="text-center bg-lite">
                        <td>1. Faridabad</td>
                        <td>97.40%</td>
                        <td>96.40%</td>
                    </tr>                  
                    <tr class="text-center bg-grey">
                        <td>2.Moti Nagar</td>
                        <td>100%</td>
                        <td>98.20%</td>
                    </tr>
                    <tr class="text-center bg-lite">
                        <td>3.Noida</td>
                        <td>97.40%</td>
                        <td>97.30%</td>
                    </tr>
                </table>
            </div>
        </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>    
<script src="<%=UI_PATH %>assets/reports/js/bootstrap.min.js"></script>
</body>
</html>