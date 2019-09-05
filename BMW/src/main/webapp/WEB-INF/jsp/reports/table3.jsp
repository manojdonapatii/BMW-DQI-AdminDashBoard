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
                  <li>2. Mathura Road</li>
                  <li>3. Moti Nagar</li>
                  <li>4. Noida</li>
                </ul>
            </div>
            <h2 class="head-text"><strong>Retail standards : sales Observations<br>DEUTSCHE MOTOREN</strong></h2>
            <div class="clearfix"></div>
            <div class="col-md-7">
                <table class="table table-bordered">
                    <tr>
                        <td class="bg-grey txt-grey">Section</td>
                        <th class="text-center bg-dark-grey">Areas for improvement</th>
                    </tr>
                    <tr class="bg-grey">
                        <td class="txt-grey size">Outdoor<br>Communication<br>System</td>
                        <td class="size"><span><span class="big-txt mr-ryt">2.03<sup>1</sup></span>Colour of BMW logo on faÃ§ade banner has been faded. Further, the tree is hiding the maximum part of dealership faÃ§ade banner from front side</span></td>
                    </tr>
                    <tr class="bg-grey">
                        <td class="txt-grey size">IT</td>
                        <td class="size"><span><span class="big-txt mr-ryt">5.01<sup>2</sup></span>The dealer has subscribed to a speed of 10 Mbps. The download speed was 4.88 Mbps and the upload was 5.56 Mbps which is below the required standard speed of 10Mbps</span></td>
                    </tr>
                    <tr class="bg-grey">
                        <td class="txt-grey size">Sales Area</td>
                        <td class="size"><span><span class="big-txt mr-ryt">3.05<sup>1</sup></span>2+2 cars were not arranged in roadway position in 2 lanes. Cars were standing in face to face position. Further, M car required as per the standard requirement was not available for display</span></td>
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
                        <th colspan="2" class="text-center">Mathura Rd</th>
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
                        <th>H1â18</th>
                        <th>H2â18</th>
                    </tr>
                    <tr class="bg-grey">
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><div class="org-dot"></div></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr class="bg-grey">
                        <td></td>
                        <td></td>
                        <td><div class="org-dot"></div></td>
                        <td></td>
                        <td><div class="org-dot"></div></td>
                        <td></td>
                        <td><div class="org-dot"></div></td>
                        <td></td>
                    </tr>
                    <tr class="bg-grey">
                        <td></td>
                        <td></td>
                        <td><div class="org-dot"></div></td>
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
                        <th colspan="12" class="text-center bg-dark-grey">RETAIL STANDARDS SCORE â SALES</th>
                    </tr>
                    <tr class="bg-light-grey">
                        <td class="text-center"></td>
                        <td class="text-center">ESSENTIAL (Target value = 100%)</td>
                        <td class="text-center">CONTRACTUAL (Target value â¥ 85%)</td>
                    </tr>
                    <tr class="bg-dark">
                        <td class="text-center">Overall Score  Deutsche Motoren</td>
                        <td class="text-center">97%</td>
                        <td class="text-center">97.8%</td>
                    </tr>
                    <tr class="text-center bg-lite">
                        <td>1. Faridabad</td>
                        <td>100%</td>
                        <td>97.5%</td>
                    </tr>
                    <tr class="text-center bg-grey">
                        <td>2.Mathura Road</td>
                        <td>96.3%</td>
                        <td>97.5%</td>
                    </tr>
                    <tr class="text-center bg-lite">
                        <td>3.Moti Nagar</td>
                        <td>96.3%</td>
                        <td>97.5%</td>
                    </tr>
                    <tr class="text-center bg-grey">
                        <td>4.Noida</td>
                        <td>96.3%</td>
                        <td>98.7%</td>
                    </tr>
                </table>
            </div>
        </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>    
<script src="<%=UI_PATH %>assets/reports/js/bootstrap.min.js"></script>
</body>
</html>