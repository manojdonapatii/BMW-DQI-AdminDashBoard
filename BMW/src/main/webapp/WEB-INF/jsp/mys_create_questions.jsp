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
        <title>Abstack - Responsive Web App Kit</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!-- App favicon -->
        <link rel="shortcut icon" href="<%=UI_PATH %>MYS/assets/images/favicon.ico">

        <!-- App css -->
        <link href="<%=UI_PATH %>MYS/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH %>MYS/assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH %>MYS/assets/css/metismenu.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH %>MYS/assets/css/style.css" rel="stylesheet" type="text/css" />
		<style>
		    .remove_field{
		    height: 35px;
		    }
		    #formul .remove_field{
		    height:30px; width:25px;
		    }
		</style>
        <script src="<%=UI_PATH %>MYS/assets/js/modernizr.min.js"></script>

    </head>


    <body>

        <!-- Begin page -->
        <div id="wrapper">

            <!-- Top Bar Start -->
            <jsp:include page="include/header.jsp"></jsp:include>
            <jsp:include page="include/sidemenu.jsp"></jsp:include>
            <!-- Left Sidebar End -->



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
                                    <!-- <h4 class="page-title float-left">Form Validation</h4>

                                    <ol class="breadcrumb float-right">
                                        <li class="breadcrumb-item"><a href="#">Abstack</a></li>
                                        <li class="breadcrumb-item"><a href="#">Forms</a></li>
                                        <li class="breadcrumb-item active">Form Validation</li>
                                    </ol> -->

                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </div>
                        <!-- end row -->


                        <div class="row">
                            <div class="col-lg-12">

                                <div class="card-box">
                                    <h4 class="header-title m-t-0">Add Questions</h4>
                                    <p class="text-muted font-14 m-b-20">
                                    </p>

                                    <form method="POST" action="<%=dashboardURL %>MYS_createQuestionnaire">
	                                     <div class="form-row">
	                                     
	                                     
	                                     		<div  class="form-group col-md-3">
		                                            <label for="userName">Mode Of Contact<span class="text-danger">*</span></label>
		                                            <select name="modeOfContact" parsley-trigger="change"  class="form-control" id="modeOfContact">
		                                            	<option value="">Select Mode Of Contact</option> 
		                                            	<option value="All">All</option>      
		                                            	<option value="Email/Website">Email/Website</option>
		                                            	<option value="Telephone">Telephone</option>
		                                            	<option value="Walk In">Walk In</option>
		                                            	
		                                            </select>
		                                        </div>
		                                        
		                                        <div  class="form-group col-md-3">
		                                            <label for="userName">Section<span class="text-danger">*</span></label>
		                                            <select name="section" parsley-trigger="change" required class="form-control" id="section">
		                                            	<option value="">Select Section</option>       
		                                            	<c:forEach var="qBean" items="${sectionList}">
					                                            	<option value="${qBean.sectionId}">${qBean.section}</option> 
					                                    </c:forEach>
		                                            	
		                                            </select>
		                                        </div>
		                                        
		                                        <div  class="form-group col-md-3">
		                                            <label for="userName">Sub Section<span class="text-danger">*</span></label>
		                                            <select name="subSection" parsley-trigger="change" required class="form-control" id="subSection">
		                                            	<option value="">Select Sub Section</option>       
		                                            	<!-- <option value="1">Sub Section</option> -->
		                                            	
		                                            </select>
		                                        </div>
		                                        
		                                        <div  class="form-group col-md-3">
		                                            <label for="userName">Standard Number<span class="text-danger">*</span></label>
		                                            <input type="text" name="standardNumber" id="standardNumber" parsley-trigger="change" placeholder="Enter Standard Number" class="form-control">
		                                        </div>
	                                     
	                                     
		                                        <div  class="form-group col-md-3">
		                                            <label for="userName">Question Type<span class="text-danger">*</span></label>
		                                            <select name="questionType" parsley-trigger="change"  class="form-control" id="questionType" onchange="checkQuestion(this.value)">
		                                            	<option value="">Select Question Type</option>       
		                                            	<option value="Main Question">Main Question</option>
		                                            	<option value="Main Question With Set Of SubQuestions">Main Question With Set Of SubQuestions</option>
		                                            	<option value="Dependent Question">Dependent Question</option>
		                                            	<option value="Dependent Question With Set Of SubQuestions">Dependent Question With Set Of SubQuestions</option>
		                                            	
		                                            </select>
		                                        </div>
		                                        
		                                        <div  class="form-group col-md-2">
		                                            <label for="userName">Question Mandatory<span class="text-danger">*</span></label>
		                                            <select name="questionOptionType" parsley-trigger="change"  class="form-control" id="questionOptionType">
		                                            	<option value="Mandatory">Mandatory</option>   
		                                            	<option value="Optional">Optional</option>   
		                                            </select>
		                                        </div>
		                                         <div class="form-group col-md-1">
		                                          <label for="userName">Points<span class="text-danger">*</span></label>
			                                            <input type="text" name="questionPoints" id="questionPoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">
			                                        </div>
			                                        <div class="form-group col-md-2">
			                                        <label for="userName">Code<span class="text-danger">*</span></label>
			                                            <input type="text" name="questionCode" id="questionCode" parsley-trigger="change" placeholder="Enter Code" class="form-control">
			                                        </div>
			                                        <div class="form-group col-md-2">
			                                        <label for="userName">Response<span class="text-danger">*</span></label>
			                                            <input type="text" name="questionResponse" id="questionResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">
			                                        </div>
			                                         <div class="form-group col-md-2">
			                                        <label for="userName">Routing Type<span class="text-danger">*</span></label>
			                                            <input type="text" name="questionRouting" id="questionRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">
			                                        </div>
	                                       </div>
                                        
                                      
                                        <div class=" " id='super_question_list' >
                                        <button class="add_field_button1 btn btn-info">+</button>
                                        
                                        <!-- <div class="form-row" >
		                                        <div  class="form-group col-md-8 pull-left">
		                                            <label for="userName">Super Question<span class="text-danger">*</span></label>
		                                        </div>
		                                        <div  class="form-group col-md-3 pull-left">
		                                            <label for="userName">Answer<span class="text-danger">*</span></label>
		                                        </div>
		                                        
		                                        <div  class="form-group col-md-1 pull-left">
		                                            <label for="userName">Add More<span class="text-danger"></span></label>
		                                        </div>
		                                    </div> -->
		                                    
		                                    
                                        <%-- <% for(int k=1;k<=10;k++){ %>
                                         <div class="form-row" id='super_question_<%=k%>' style="display: none">
		                                        <div  class="form-group col-md-8 pull-left">
		                                            <select name="superQuestion" id="super_question<%=k %>" parsley-trigger="change" class="form-control">
		                                            	<option value="">Select Question</option>       
		                                            	<option value="1">Select Question</option>
		                                            	<option value="2">Select Question</option>
		                                            </select>
		                                        </div>
		                                        <div  class="form-group col-md-3 pull-left">
		                                            <select name="superQuestionAnswer" parsley-trigger="change" class="form-control" id="super_answer<%=k %>">
		                                            	<option value="Yes">Yes</option>     
		                                            	<option value="No">No</option>         
		                                            	
		                                            </select>
		                                        </div>
		                                        
		                                        <div  class="form-group col-md-1 pull-left">
		                                            <input type="button" class="form-control bth" value=" + " onclick="openSuperQuestion(<%=k+1%>)">
		                                        </div>
		                                    </div>
		                                   <% }%> --%>
		                                </div>
		                                
                                        <div class="form-group">
                                            <label for="emailAddress">Main Question<span class="text-danger">*</span></label>
                                            <input type="text" name="question" parsley-trigger="change" 
                                                   placeholder="Enter question" class="form-control" id="question">
                                        </div>
                                        <!-- ---------------------------------------------------MAIN QUESTION-------------------------------------------- -->
                                        <div class="form-row" id='main_question'>
		                                        <div  class="form-group col-md-12">
		                                            <label for="userName">Answer Type<span class="text-danger">*</span></label>
		                                            <select name="answerType" parsley-trigger="change" class="form-control" id="answerType" onchange="checkQuestionType(this.value)">
		                                            	<option value="">Select Answer Type</option>       
		                                            	<option value="Paragraph">Paragraph</option>
		                                            	<option value="Date">Date</option>
		                                            	<option value="Date & Time">Date & Time</option>
		                                            	<option value="Select/List">Single Select/List</option>
		                                            	<option value="Select/List">Multiple Select/List</option>
		                                            </select>
		                                        </div>
		                                        
		                                        <div class="form-row col-md-12" id='questiontype_list'>
		                                        <button class="add_field_button btn btn-info">+</button>
		                                        
		                                        <%-- <div class="form-group col-md-2 pull-left">
			                                            <label for="emailAddress">Answer<span class="text-danger">*</span></label>
			                                        </div>
			                                        <div class="form-group col-md-2 pull-left">
			                                            <label for="emailAddress">Points<span class="text-danger">*</span></label>
			                                        </div>
			                                        <div class="form-group col-md-1 pull-left">
			                                            <label for="emailAddress">Code<span class="text-danger">*</span></label>
			                                         </div>
			                                        <div class="form-group col-md-1 pull-left">
			                                            <label for="emailAddress">Response<span class="text-danger">*</span></label>
			                                        </div>
			                                        <div class="form-group col-md-2 pull-left">
			                                            <label for="emailAddress">Mandatory<span class="text-danger">*</span></label>
			                                        </div>
			                                        <div class="form-group col-md-2 pull-left">
			                                            <label for="emailAddress">Comment<span class="text-danger">*</span></label>
			                                        </div>
			                                        <div class="form-group col-md-2 pull-left">
			                                            <label for="emailAddress"><span class="text-danger">*</span></label>
			                                        </div>
		                                        <% for(int i=1;i<=10;i++){ String display="";if(i>1){display="none";}%>
		                                        <div class="form-row col-md-12" id='main_answer<%=i%>' style="display:<%=display%>">
			                                        <div class="form-group col-md-2 pull-left">
			                                            <input type="text" name="answer" id="answer_main<%=i %>" parsley-trigger="change"  placeholder="Enter Answer" class="form-control">
			                                        </div>
			                                        <div class="form-group col-md-2 pull-left">
			                                            <input type="text" name="answerPoints" id="points_main<%=i %>" parsley-trigger="change"  placeholder="Enter Points" class="form-control">
			                                        </div>
			                                        <div class="form-group col-md-1 pull-left">
			                                            <input type="text" name="answerCode" id="code_main<%=i %>" parsley-trigger="change"  placeholder="Enter Code" class="form-control">
			                                        </div>
			                                        <div class="form-group col-md-1 pull-left">
			                                            <input type="text" name="answerResponse" id="response_main<%=i %>" parsley-trigger="change"  placeholder="Enter Response" class="form-control">
			                                        </div>
			                                        <div class="form-group col-md-2 pull-left">
			                                            <select name="answerOptionType" id="mandatory_main<%=i %>" parsley-trigger="change"  class="form-control">
			                                            	<option value="Yes">Yes</option>
			                                            	<option value="No">No</option>
			                                            </select>
			                                        </div>
			                                        <div class="form-group col-md-2 pull-left">
			                                            <select name="answerComment" id="comment_main<%=i %>" parsley-trigger="change"  class="form-control">
			                                            	<option value="Yes">Yes</option>
			                                            	<option value="No">No</option>
			                                            </select>
			                                        </div>
			                                        <div class="form-group col-md-2 pull-left">
			                                            <input type="button" value=" + "  class="btn" onclick="openMainAnswer(<%=i+1%>)">
			                                        </div>
			                                       </div>
			                                   <% }%> --%>
			                                       
                                        	</div>
                                        	<div class="form-row col-md-12" id='questiontypedate_list'>
                                        	<div class="row">
                                        	<div class="form-group col-md-2">
		                                          <label for="userName">Date:<span class="text-danger"></span></label>
			                                        </div>
                                        	<div class="form-group col-md-2">
		                                          <label for="userName">Points<span class="text-danger">*</span></label>
			                                            <input type="text" name="datePoints" id="datePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">
			                                        </div>
			                                        <div class="form-group col-md-2">
			                                        <label for="userName">Code<span class="text-danger">*</span></label>
			                                            <input type="text" name="dateCode" id="dateCode" parsley-trigger="change" placeholder="Enter Code" class="form-control">
			                                        </div>
			                                        <div class="form-group col-md-2">
			                                        <label for="userName">Response<span class="text-danger">*</span></label>
			                                            <input type="text" name="dateResponse" id="dateResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">
			                                        </div>
			                                         <div class="form-group col-md-3">
			                                        <label for="userName">Routing Type<span class="text-danger">*</span></label>
			                                            <input type="text" name="dateRouting" id="dateRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">
			                                        </div>
			                                        <br>
			                                        <div class="form-group col-md-2">
		                                          <label for="userName">Time:<span class="text-danger"></span></label>
			                                        </div>
			                                        <div class="form-group col-md-2">
		                                          <label for="userName">Points<span class="text-danger">*</span></label>
			                                            <input type="text" name="timePoints" id="timePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">
			                                        </div>
			                                        <div class="form-group col-md-2">
			                                        <label for="userName">Code<span class="text-danger">*</span></label>
			                                            <input type="text" name="timeCode" id="timeCode" parsley-trigger="change" placeholder="Enter Code" class="form-control">
			                                        </div>
			                                        <div class="form-group col-md-2">
			                                        <label for="userName">Response<span class="text-danger">*</span></label>
			                                            <input type="text" name="timeResponse" id="timeResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">
			                                        </div>
			                                         <div class="form-group col-md-3">
			                                        <label for="userName">Routing Type<span class="text-danger">*</span></label>
			                                            <input type="text" name="timeRouting" id="timeRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">
			                                        </div>
                                        	</div>
                                        	</div> 
	                                       </div>
	                                    <!-- ---------------------------------------------------MAIN QUESTION-------------------------------------------- -->   
	                                    <!-- ---------------------------------------------------MAIN QUESTION WITH SET OF SUBQUESTIONS-------------------------------------------- -->
                                        
                                        
                               <div class="col-lg-12" id='main_question_sub'>
                                <div id="accordion" class="m-b-30 subquestions">
                                <button class="add_field_button2 btn btn-info">+</button>
                                 <%-- <% for(int j=1;j<=10;j++){ String display="";if(j>1){display="none";}%>
                                    <div class="card"   id='main_answer_<%=j %>' style="display:<%=display%>;">
                                        <div class="card-header" id="heading<%=j %>">
                                            <h6 class="m-0">
                                                <a href="#collapse<%=j %>" class="text-dark" data-toggle="collapse" aria-expanded="true" aria-controls="collapse<%=j %>">
                                                    Sub Question #<%=j %> <span class='pull-right' id="subquestion<%=j %>" onclick="openMainSetSubQuestion(<%=j+1 %>)">Add More</span>
                                                </a>
                                            </h6>
                                        </div>
                            
                                        <div id="collapse<%=j %>" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                                            <div class="card-body">
                                            
                                                <div class="form-group col-md-1 pull-left">
		                                            <label for="emailAddress">SLNo<span class="text-danger">*</span></label>
		                                            <input type="text" name="question_sl_<%=j %>" parsley-trigger="change" 
	                                                   placeholder="Enter question" class="form-control" id="question_sl_<%=j %>" value="<%=j%>">
	                                        	</div>
	                                        	<div class="form-group col-md-5 pull-left">
		                                            <label for="emailAddress">Sub Question <%=j %><span class="text-danger">*</span></label>
		                                            <input type="text" name="subQuestion" parsley-trigger="change" 
		                                                   placeholder="Enter question" class="form-control" id="question_<%=j %>">
		                                        </div>
                                        		<div  class="form-group col-md-4 pull-left">
		                                            <label for="userName">Answer Type<span class="text-danger">*</span></label>
		                                            <select name="subQuestionAnswerType" parsley-trigger="change" class="form-control" id="question_type_<%=j %>" onchange="checkSubQuestionType(this.value,<%=j %>)">
		                                            	<option value="">Select Answer Type</option>       
		                                            	<option value="Paragraph">Paragraph</option>
		                                            	<option value="Date">Date</option>
		                                            	<option value="Date & Time">Date & Time</option>
		                                            	<option value="Select/List">Select/List</option>
		                                            </select>
		                                        </div>
		                                        
		                                        
		                                      <div class="form-row col-md-12" id='subquestiontype_list<%=j%>' style="display: none">
		                                        
		                                        
		                                        <div class="form-group col-md-2 pull-left">
			                                            <label for="emailAddress">Answer<span class="text-danger">*</span></label>
			                                        </div>
			                                        <div class="form-group col-md-2 pull-left">
			                                            <label for="emailAddress">Points<span class="text-danger">*</span></label>
			                                        </div>
			                                        <div class="form-group col-md-1 pull-left">
			                                            <label for="emailAddress">Code<span class="text-danger">*</span></label>
			                                         </div>
			                                        <div class="form-group col-md-1 pull-left">
			                                            <label for="emailAddress">Response<span class="text-danger">*</span></label>
			                                        </div>
			                                        <div class="form-group col-md-2 pull-left">
			                                            <label for="emailAddress">Mandatory<span class="text-danger">*</span></label>
			                                        </div>
			                                        <div class="form-group col-md-2 pull-left">
			                                            <label for="emailAddress">Comment<span class="text-danger">*</span></label>
			                                        </div>
			                                        <div class="form-group col-md-2 pull-left">
			                                            <label for="emailAddress"><span class="text-danger">*</span></label>
			                                        </div>
		                                        <% for(int i=1;i<=10;i++){ display="";if(i>1){display="none";}%>
		                                        <div class="form-row col-md-12" id='sub_answer_<%=j %>_<%=i%>' style="display:<%=display%>">
			                                        <div class="form-group col-md-2 pull-left">
			                                            <input type="text" name="SubQuestionAnswer" id="answer_sub_<%=j %>_<%=i %>" parsley-trigger="change"  placeholder="Enter Answer" class="form-control">
			                                        </div>
			                                        <div class="form-group col-md-2 pull-left">
			                                            <input type="text" name="SubQuestionAnswerPoints" id="points_sub_<%=j %>_<%=i %>" parsley-trigger="change"  placeholder="Enter Points" class="form-control">
			                                        </div>
			                                        <div class="form-group col-md-1 pull-left">
			                                            <input type="text" name="SubQuestionAnswerCode" id="code_sub_<%=j %>_<%=i %>" parsley-trigger="change"  placeholder="Enter Code" class="form-control">
			                                        </div>
			                                        <div class="form-group col-md-1 pull-left">
			                                            <input type="text" name="SubQuestionAnswerResponse" id="response_sub_<%=j %>_<%=i %>" parsley-trigger="change"  placeholder="Enter Response" class="form-control">
			                                        </div>
			                                        <div class="form-group col-md-2 pull-left">
			                                            <select name="SubQuestionAnswerOptionType" parsley-trigger="change"  class="form-control">
			                                            	<option value="Yes">Yes</option>
			                                            	<option value="No">No</option>
			                                            </select>
			                                        </div>
			                                        <div class="form-group col-md-2 pull-left">
			                                            <select name="SubQuestionAnswerComment" parsley-trigger="change"  class="form-control">
			                                            	<option value="Yes">Yes</option>
			                                            	<option value="No">No</option>
			                                            </select>
			                                        </div>
			                                        <div class="form-group col-md-2 pull-left">
			                                            <input type="button" value=" + "  class="btn" onclick="openSubAnswer(<%=j %>,<%=i+1%>)">
			                                        </div>
			                                       </div>
			                                   <% }%>
			                                       
                                        	</div>
                                        	
                                            </div>
                                        </div>
                                    </div>
                                    <input type="text" value=<%=j %>>
                                    <% } %> --%>
                                    
                                </div>
                                
                                <div class="form-group col-lg-6" id="formula">
		                        <button class="add_field_button3 btn btn-info">+</button>                  
	                            <%-- <% for(int j=1;j<=10;j++){ String display="";if(j>1){display="none";}%>
	                             <div class="card" id='formul<%=j %>' style="width: 500px; display:<%=display%>">
                                        <div class="card-header" role="tab" id="headingOne<%=j %>">
                                            <h6 class="mb-0 mt-0">
                                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne<%=j %>" class="text-dark" aria-expanded="true" aria-controls="collapseOne">
                                                    Add Formula<%=j %><span class='pull-right' onclick="openformulae(<%=j+1 %>)">Add More</span>
                                                </a>
                                            </h6>
                                        </div>

                                        <div id="collapseOne<%=j %>" class="collapse" role="tabpanel" aria-labelledby="headingOne">
                                            <div class="card-body formulae" id="formulae">
                                            <div class="row">
                                            <div class="form-group col-md-6">
                                              <div class="checkbox1">
                                                    <input id="checkbox0" class="checkbox0" type="checkbox">
                                                    <label for="checkbox0">
                                                        Question 1
                                                    </label>
                                                </div>
                                            </div>
                                             <div  class="form-group col-md-6">
		                                            <select name="formulaetype<%=j %>" class="form-control" id="formulaetype<%=j %>" >
		                                            	<option value="">Select</option>       
		                                            	<option value="Yes">Yes</option>
		                                            	<option value="No">No</option>
		                                            	
		                                            </select>
		                                        </div>
                                              </div>  
                                            </div>
                                            <div class="pull-right  row">
                                            <!-- <div  class="pull-right form-group col-md-6">
                                            <label>Result</label>
                                             <input style="width:200px;border: 1px solid #dadada;border-radius: 4px;margin: 10px;" class="pull-right" type="text" id="formula" name="formula" class="form-control">
 											</div>   -->
 											<div class="form-group col-md-10">
                                            <label for="emailAddress">Result</label>
                                            <input type="text" name="question" parsley-trigger="change" 
                                                   placeholder="Enter Result" class="form-control" id="result<%=j %>">
                                            </div>
 											</div>                                    
                                        </div>
                                    </div>
                                    <% } %> --%>
                                </div>
	                             
                            </div>
                                        
                                        
                                        
	                                    <!-- ---------------------------------------------------MAIN QUESTION WITH SET OF SUBQUESTIONS-------------------------------------------- -->
                                        
                                         <div class="form-row col-lg-6">
	                                        <div class="form-group col-lg-12">
	                                            <div class="checkbox checkbox-purple">
	                                                <input id="checkbox6a" type="checkbox" onclick="calCommentMandate()">
	                                                <label for="checkbox6a">
	                                                    Add Comment Box
	                                                </label>
	                                            </div>
	
	                                        </div>
                                        
                                        
                                        <div class="form-group col-lg-6" id='comment_box'>
		                                        <div class="form-group col-lg-3">
		                                            <div class="checkbox checkbox-purple">
		                                                <input id="checkbox6b" type="checkbox" name="commentMandatory" value="Yes" onclick="calCommentCriteria()">
		                                                <label for="checkbox6b">
		                                                    Mandarory
		                                                </label>
		                                            </div>
												</div>
		                                        
		                                         <div class="form-group col-lg-6" id="criteria_box">
		                                          <label for="emailAddress">Criteria<span class="text-danger">*</span></label>
	                                                <div class="custom-control custom-radio">
	                                                    <input type="radio" id="customRadio1" name="commentCriteria" value="Yes" class="custom-control-input" checked="checked">
	                                                    <label class="custom-control-label" for="customRadio1">Yes</label>
	                                                </div>
	                                                <div class="custom-control custom-radio">
	                                                    <input type="radio" id="customRadio2" name="commentCriteria" value="No" class="custom-control-input">
	                                                    <label class="custom-control-label" for="customRadio2">No</label>
	                                                </div>
	                                                <div class="custom-control custom-radio">
	                                                    <input type="radio" id="customRadio3" name="commentCriteria" value="Any" class="custom-control-input">
	                                                    <label class="custom-control-label" for="customRadio3">Any</label>
	                                                </div>
	                                            </div>
	                                        </div>
	                                        
                                        </div>
                                        

                                        <div class="form-group text-right m-b-0">
                                            <button class="btn btn-gradient waves-effect waves-light" type="submit">
                                                Submit
                                            </button>
                                            <button type="reset" class="btn btn-light waves-effect m-l-5">
                                                Cancel
                                            </button>
                                        </div>

                                    </form>
                                </div> <!-- end card-box -->
                            </div>
                            <!-- end col -->

                            
                            
                            
                            
                            
                        </div>
                        <!-- end row -->

                        

                    </div> <!-- container -->

                </div> <!-- content -->

                <footer class="footer text-right">
                    2017 - 2018 Â© Abstack. - Coderthemes.com
                </footer>

            </div>


            <!-- ============================================================== -->
            <!-- End Right content here -->
            <!-- ============================================================== -->


        </div>
        <!-- END wrapper -->



        <!-- jQuery  -->
        <script src="<%=UI_PATH %>MYS/assets/js/jquery.min.js"></script>
        <script src="<%=UI_PATH %>MYS/assets/js/popper.min.js"></script>
        <script src="<%=UI_PATH %>MYS/assets/js/bootstrap.min.js"></script>
        <script src="<%=UI_PATH %>MYS/assets/js/metisMenu.min.js"></script>
        <script src="<%=UI_PATH %>MYS/assets/js/waves.js"></script>
        <script src="<%=UI_PATH %>MYS/assets/js/jquery.slimscroll.js"></script>

        <!-- Parsley js -->
        <script type="text/javascript" src="<%=UI_PATH %>MYS/plugins/parsleyjs/parsley.min.js"></script>

        <!-- App js -->
        <script src="<%=UI_PATH %>MYS/assets/js/jquery.core.js"></script>
        <script src="<%=UI_PATH %>MYS/assets/js/jquery.app.js"></script>

        <script type="text/javascript">
            $(document).ready(function() {
                $('form').parsley();
            });
        </script>
        <script type="text/javascript">
         document.getElementById("comment_box").style.display="none";
        
         function calCommentMandate(){
         	
         	if(document.getElementById("checkbox6a").checked==true){
         		document.getElementById("comment_box").style.display="block";
         		$('#checkbox6b').val("No");
         	}else{
         		document.getElementById("comment_box").style.display="none";
         	}
         }
         
         document.getElementById("criteria_box").style.display="none";
         
         function calCommentCriteria(){
         	
         	if(document.getElementById("checkbox6b").checked==true){
         		document.getElementById("criteria_box").style.display="block";
         		$('#checkbox6b').val('Yes');
         		$('#customradio1').prop("checked",true);
         	}else{
         		document.getElementById("criteria_box").style.display="none";
         	}
         }
         
         
         document.getElementById("questiontype_list").style.display="none";
         document.getElementById("questiontypedate_list").style.display="none";
         function  checkQuestionType(val){
          	
          	if(val=="Select/List"){
          		document.getElementById("questiontype_list").style.display="block";
          	}else{
          		document.getElementById("questiontype_list").style.display="none";
          	}
          	if(val=="Date & Time"){
          		document.getElementById("questiontypedate_list").style.display="block";
          	}else{
          		document.getElementById("questiontypedate_list").style.display="none";
          	}
          }
         
         
         function  checkSubQuestionType(val,row){
           	
           	if(val=="Select/List"){
           		document.getElementById("subquestiontype_list"+row).style.display="";
           	}else{
           		document.getElementById("subquestiontype_list"+row).style.display="none";
           	}
           }
        
         document.getElementById("main_question").style.display="none";
         document.getElementById("main_question_sub").style.display="none";
         document.getElementById("super_question_list").style.display="none";
         function  checkQuestion(val){
          	
        	 
        	 document.getElementById("main_question").style.display="none";
        	 document.getElementById("main_question_sub").style.display="none";
        	 document.getElementById("super_question_list").style.display="none";
       		document.getElementById("main_question_sub").style.display="none";
        	 
        	 
          	if(val=="Main Question"){
          		document.getElementById("main_question").style.display="block";
          	}else{
          		//document.getElementById("main_question").style.display="none";
          	}
          	
          	if(val=="Main Question With Set Of SubQuestions"){
          		document.getElementById("main_question_sub").style.display="block";
          	}else{
          		//document.getElementById("main_question_sub").style.display="none";
          	}
          	
        	if(val=="Dependent Question"){
          		document.getElementById("super_question_list").style.display="block";
          		document.getElementById("main_question").style.display="block";
          	}else{
          		//document.getElementById("super_question_list").style.display="none";
          	}
          	
          	
        	if(val=="Dependent Question With Set Of SubQuestions"){
          		document.getElementById("super_question_list").style.display="block";
          		document.getElementById("main_question").style.display="block";
          		document.getElementById("main_question_sub").style.display="block";
          	}else{
          		//document.getElementById("super_question_list").style.display="none";
          		//document.getElementById("main_question_sub").style.display="none";
          	}
          	
          	
          }
        
         
         document.getElementById("super_question_1").style.display="";
         function openSuperQuestion(row){
      	   document.getElementById("super_question_"+row).style.display="";
         }
         
         function openMainSetSubQuestion(row){
        	 var row1=row-1;
        	 document.getElementById("main_answer_"+row).style.display="";
        	 document.getElementById("subquestion"+row1).style.display="none";
        	 //$("#formulae").append('<div class="checkbox'+row+'"><input id="checkbox0" class="checkbox0" type="checkbox"><label for="checkbox0">&nbsp;Question '+row+'</label></div>');
        	 //$("#collapseOne"+row).find('#formulae').append('<div class="checkbox'+row+'"><input id="checkbox0" class="checkbox0" type="checkbox"><label for="checkbox0">&nbsp;Question '+row+'</label></div>');
        	 $('.formulae').each(function(){
        		 $(this).append('<div class="row"><div class="form-group col-md-6"><div class="checkbox'+row+'"><input id="checkbox0" class="checkbox0" type="checkbox"><label for="checkbox0">&nbsp;Question '+row+'</label></div></div><div  class="form-group col-md-6"><select name="formulaetype'+row+'" class="form-control" id="formulaetype'+row+'" ><option value="">Select</option> <option value="Yes">Yes</option><option value="No">No</option></select></div></div>');
        	 });
         }
         
         function openformulae(row){
        	 document.getElementById("formul"+row).style.display=""; 
        	 
         }
         
         function openMainAnswer(row){
        	 document.getElementById("main_answer"+row).style.display="";
         }
         function openSubAnswer(q,row){
        	 document.getElementById("sub_answer_"+q+"_"+row).style.display="";
         }
         $("#clickingg").click(function(){
        	    //alert("The paragraph was clicked.");
        	});
         </script>
         
         <script>
    
    $(document).ready(function() {
        var max_fields      = 10; //maximum input boxes allowed
        var wrapper         = $("#questiontype_list"); //Fields wrapper
        var add_button      = $(".add_field_button"); //Add button ID
    	$(wrapper).append('<div class="form-group col-md-2 pull-left"><label for="emailAddress">Answer<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Points<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Code<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Response<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Mandatory<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Routing Type<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Comment<span class="text-danger">*</span></label></div>'); //add //on add input button click
    	$(wrapper).append('<div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="answerdata[0].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control"></div><div class="form-group col-md-2 pull-left"><input type="text" name="answerdata[0].answerPoints" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="answerdata[0].answerCode" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="answerdata[0].answerResponse" id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control"></div><div class="form-group col-md-1 pull-left"><select name="answerdata[0].answerOptionType" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><input type="text" name="answerdata[0].routingType" id="routing_id" parsley-trigger="change"  placeholder="Enter Routing Type" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="answerdata[0].answerComment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div></div></div>');     	
        var x = 0; 
        $(add_button).on("click",function(e){
			
             e.preventDefault();
            if(x < max_fields){ //max input box allowed
                x++; //text box increment
                
                $(wrapper).append('<div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="answerdata['+x+'].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control"></div><div class="form-group col-md-2 pull-left"><input type="text" name="answerdata['+x+'].answerPoints" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="answerdata['+x+'].answerCode" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="answerdata['+x+'].answerResponse" id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control"></div><div class="form-group col-md-1 pull-left"><select name="answerdata['+x+'].answerOptionType" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><input type="text" name="answerdata['+x+'].routingType" id="routing_id" parsley-trigger="change"  placeholder="Enter Routing Type" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="answerdata['+x+'].answerComment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field">-</button></div></div>'); //add input box
                
            }
            
        });
        
        $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
            e.preventDefault(); 
        $(this).parent('#main_answer').remove(); 
        x--;
        })
    });
    </script>
    
    <script>
    
    $(document).ready(function() {
        var max_fields      = 10; //maximum input boxes allowed
        var wrapper         = $("#super_question_list"); //Fields wrapper
        var add_button      = $(".add_field_button1"); //Add button ID
    	$(wrapper).append('<div class="form-row" ><div  class="form-group col-md-3 pull-left"><label for="userName">Super Question<span class="text-danger">*</span></label></div><div  class="form-group col-md-3 pull-left"></div><div  class="form-group col-md-3 pull-left"></div><div  class="form-group col-md-3 pull-left"><label for="userName">Answer<span class="text-danger">*</span></label></div></div>'); //add //on add input button click
    	$(wrapper).append('<div class="form-row" id="super_question"><div  class="form-group col-md-3 pull-left"><select name="section0" parsley-trigger="change" onchange="call(0);" class="form-control" id="section0"><option value="">Select Section</option><c:forEach var="qBean" items="${sectionList}"><option value="${qBean.sectionId}">${qBean.section}</option></c:forEach></select></div><div  class="form-group col-md-3 pull-left"><select name="subSection0" parsley-trigger="change" class="form-control" onchange="callstandardnumber(0);" id="subSection0"><option value="">Select Sub Section</option></select></div><div  class="form-group col-md-3 pull-left"><select name="standardName" parsley-trigger="change" class="form-control" onchange="callQuestion(0);" id="standardNumber0"><option value="">Select Standard Number</option></select><input type="hidden" name="questiondata[0].superQuestion" value="" id="super_question0"></div><div  class="form-group col-md-3 pull-left"><select name="questiondata[0].superQuestionAnswer" parsley-trigger="change" class="form-control" id="super_answer0"><option value="Yes">Yes</option><option value="No">No</option></select></div></div>');     	
        var x = 0; 
        $(add_button).on("click",function(e){
			
             e.preventDefault();
            if(x < max_fields){ //max input box allowed
                x++; //text box increment
                
                $(wrapper).append('<div class="form-row" id="super_question"><div  class="form-group col-md-3 pull-left"><select name="section'+x+'" parsley-trigger="change" onchange="call('+x+');" class="form-control" id="section'+x+'"><option value="">Select Section</option><c:forEach var="qBean" items="${sectionList}"><option value="${qBean.sectionId}">${qBean.section}</option></c:forEach></select></div><div  class="form-group col-md-3 pull-left"><select name="subSection'+x+'" parsley-trigger="change" class="form-control" onchange="callstandardnumber('+x+');" id="subSection'+x+'"><option value="">Select Sub Section</option></select></div><div  class="form-group col-md-3 pull-left"><select name="standardName" parsley-trigger="change" class="form-control" onchange="callQuestion('+x+');" id="standardNumber'+x+'"><option value="">Select Standard Number</option></select><input type="hidden" name="questiondata['+x+'].superQuestion" value="" id="super_question'+x+'"></div><div  class="form-group col-md-3 pull-left"><select name="questiondata['+x+'].superQuestionAnswer" parsley-trigger="change" class="form-control" id="super_answer'+x+'"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field">-</button></div>'); //add input box
                
            }
            
        });
        
        $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
            e.preventDefault(); 
        $(this).parent('#super_question').remove(); 
        x--;
        })
    });
    </script>
    
    
    
    
 <script>
    
    $(document).ready(function() {
        var max_fields      = 10; //maximum input boxes allowed
        var wrapper         = $(".subquestions"); //Fields wrapper
        var add_button      = $(".add_field_button2"); //Add button ID
    	//$(wrapper).append('<div class="form-row" ><div  class="form-group col-md-8 pull-left"><label for="userName">Super Question<span class="text-danger">*</span></label></div><div  class="form-group col-md-3 pull-left"><label for="userName">Answer<span class="text-danger">*</span></label></div><div  class="form-group col-md-1 pull-left"><label for="userName">Add More<span class="text-danger"></span></label></div></div>'); //add //on add input button click
    	$(wrapper).append('<div class="card"   id="main_answer_1" ><div class="card-header" id="heading"><h6 class="m-0"><a href="#collapse1" class="text-dark" data-toggle="collapse" aria-expanded="true" aria-controls="collapse1">Sub Question #1 </a></h6></div><div id="collapse1" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion"><div class="card-body"><div class="form-group col-md-1 pull-left"><label for="emailAddress">SLNo<span class="text-danger">*</span></label><input type="text" name="question_sl_1" parsley-trigger="change" placeholder="Enter question" class="form-control" id="question_sl_1" value="1"></div><div class="form-group col-md-5 pull-left"><label for="emailAddress">Sub Question 1<span class="text-danger">*</span></label><input type="text" name="subquestiondata[0].subQuestion" parsley-trigger="change" placeholder="Enter question" class="form-control" id="question_1"></div><div  class="form-group col-md-4 pull-left"><label for="userName">Answer Type<span class="text-danger">*</span></label><select name="subquestiondata[0].subQuestionAnswerType" parsley-trigger="change" class="form-control" id="question_type_1" onchange="checkSubQuestionType(this.value,1)"><option value="">Select Answer Type</option><option value="Paragraph">Paragraph</option><option value="Date">Date</option><option value="Date & Time">Date & Time</option><option value="Select/List">Single Select/List</option><option value="Select/List">Multiple Select/List</option></select></div><div class="form-row col-md-12" id="subquestiontype_list1" style="display: none"><button class="add_field_button2 btn btn-info" id="buttonanswer1">+</button>         </div></div></div></div>');     	
    	var answerwrapper   = $("#subquestiontype_list1");
    	$(answerwrapper).append('<div class="form-group col-md-2 pull-left"><label for="emailAddress">Answer<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Points<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Code<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Response<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Mandatory<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Comment<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress"><span class="text-danger">*</span></label></div>');
    	$(answerwrapper).append('<div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata[0].subquestionanswers[0].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata[0].subquestionanswers[0].answerPoints" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata[0].subquestionanswers[0].answerCode" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata[0].subquestionanswers[0].answerResponse" id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata[0].subquestionanswers[0].answerOptionType" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata[0].subquestionanswers[0].answerComment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div></div></div>');
    	var x = 0; 
        var y=0;
        var alphabets=65;
        $(add_button).on("click",function(e){
			//alert(1);
             e.preventDefault();
            if(x < max_fields){ //max input box allowed
                x++; //text box increment
                y=x+1;
                u=0;
                $(wrapper).append('<div class="card" id="main_answer_'+y+'" ><div class="card-header" id="heading"><h6 class="m-0"><a href="#collapse'+y+'" class="text-dark" data-toggle="collapse" aria-expanded="true" aria-controls="collapse'+y+'">Sub Question #'+y+' </a></h6></div><button class="remove_field pull-right" style="height:30px; width:25px">-</button><div id="collapse'+y+'" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion"><div class="card-body"><div class="form-group col-md-1 pull-left"><label for="emailAddress">SLNo<span class="text-danger">*</span></label><input type="text" name="question_sl_'+y+'" parsley-trigger="change" placeholder="Enter question" class="form-control" id="question_sl_'+y+'" value="'+y+'"></div><div class="form-group col-md-5 pull-left"><label for="emailAddress">Sub Question '+y+'<span class="text-danger">*</span></label><input type="text" name="subquestiondata['+x+'].subQuestion" parsley-trigger="change" placeholder="Enter question" class="form-control" id="question_'+y+'"></div><div  class="form-group col-md-4 pull-left"><label for="userName">Answer Type<span class="text-danger">*</span></label><select name="subquestiondata['+x+'].subQuestionAnswerType" parsley-trigger="change" class="form-control" id="question_type_'+y+'" onchange="checkSubQuestionType(this.value,'+y+')"><option value="">Select Answer Type</option><option value="Paragraph">Paragraph</option><option value="Date">Date</option><option value="Date & Time">Date & Time</option><option value="Select/List">Single Select/List</option><option value="Select/List">Multiple Select/List</option></select></div><div class="form-row col-md-12 subque" id="subquestiontype_list'+y+'" style="display: none"><button class="add_field_button2 buttonanswer btn btn-info" id="buttonanswer'+y+'">+</button>         </div></div></div></div>'); //add input box
                var answerwrapper   = $("#subquestiontype_list"+y);
                $(answerwrapper).append('<div class="form-group col-md-2 pull-left"><label for="emailAddress">Answer<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Points<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Code<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Response<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Mandatory<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Comment<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress"><span class="text-danger">*</span></label></div>');
                $(answerwrapper).append('<div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+x+'].subquestionanswers['+u+'].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+x+'].subquestionanswers['+u+'].answerPoints" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+x+'].subquestionanswers['+u+'].answerCode" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+x+'].subquestionanswers['+u+'].answerResponse" id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+x+'].subquestionanswers['+u+'].answerOptionType" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+x+'].subquestionanswers['+u+'].answerComment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div></div></div>');
                $('.formulae').each(function(){
                  
                	if(alphabets<=90){
                		alphabets++;
           		 $(this).append('<div class="row"><div class="form-group col-md-6"><div class="checkbox1"><input id="checkbox'+x+'" name="formuladata[0].subQuestionId" value="'+String.fromCharCode(alphabets)+'" class="checkbox'+x+'" type="checkbox"><label for="checkbox'+x+'">&nbsp;Question '+y+'</label></div></div><div  class="form-group col-md-6"><select name="formuladata[0].formulaResult" class="form-control" id="formulaetype'+y+'" ><option value="">Select</option> <option value="Yes">Yes</option><option value="No">No</option></select></div></div>');
                	
                	}
                	});
            
                var a=0;
                $('#buttonanswer2').on("click",function(e){
                	
                	/* alert(a);
                	alert(b); */
                	var que=1;
                	e.preventDefault();
                	if(a < max_fields){ //max input box allowed
                        a++; //text box increment
                	var answerwrapper   = $("#subquestiontype_list2");
                	$(answerwrapper).append('<div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+a+'].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+a+'].answerPoints" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+a+'].answerCode" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+a+'].answerResponse" id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+a+'].answerOptionType" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+a+'].answerComment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field2">-</button></div></div>');
            		//alert(1);
                }
               });
                
                var b=0;
                $('#buttonanswer3').on("click",function(e){
                	/* alert(b); */
                
                	var que=2;
                	e.preventDefault();
                	if(b < max_fields){ //max input box allowed
                        b++; //text box increment
                	var answerwrapper   = $("#subquestiontype_list3");
                	$(answerwrapper).append('<div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+b+'].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+b+'].answerPoints" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+b+'].answerCode" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+b+'].answerResponse" id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+b+'].answerOptionType" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+b+'].answerComment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field2">-</button></div></div>');
            		//alert(1);
                }
               });
                
                var c=0;
                $('#buttonanswer4').on("click",function(e){
                	/* alert(b); */
                	
                	var que=3;
                	e.preventDefault();
                	if(c < max_fields){ //max input box allowed
                        c++; //text box increment
                	var answerwrapper   = $("#subquestiontype_list4");
                	$(answerwrapper).append('<div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+c+'].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+b+'].answerPoints" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+c+'].answerCode" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+c+'].answerResponse" id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+c+'].answerOptionType" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+c+'].answerComment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field2">-</button></div></div>');
            		//alert(1);
                }
               });
                
                var d=0;
                $('#buttonanswer5').on("click",function(e){
                	/* alert(b); */
                	
                	var que=4;
                	e.preventDefault();
                	if(d < max_fields){ //max input box allowed
                        d++; //text box increment
                	var answerwrapper   = $("#subquestiontype_list5");
                	$(answerwrapper).append('<div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+d+'].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+b+'].answerPoints" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+d+'].answerCode" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+d+'].answerResponse" id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+d+'].answerOptionType" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+d+'].answerComment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field2">-</button></div></div>');
            		//alert(1);
                }
               });
                
                var e=0;
                $('#buttonanswer6').on("click",function(a){
                	/* alert(b); */
                	
                	var que=6;
                	a.preventDefault();
                	if(e < max_fields){ //max input box allowed
                        e++; //text box increment
                	var answerwrapper   = $("#subquestiontype_list6");
                	$(answerwrapper).append('<div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+e+'].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+e+'].answerPoints" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+e+'].answerCode" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+e+'].answerResponse" id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+e+'].answerOptionType" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+e+'].answerComment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field2">-</button></div></div>');
            		//alert(1);
                }
               });
                
                var f=0;
                $('#buttonanswer7').on("click",function(e){
                	/* alert(b); */
                	
                	var que=6;
                	e.preventDefault();
                	if(f < max_fields){ //max input box allowed
                        f++; //text box increment
                	var answerwrapper   = $("#subquestiontype_list7");
                	$(answerwrapper).append('<div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+f+'].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+f+'].answerPoints" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+f+'].answerCode" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+f+'].answerResponse" id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+f+'].answerOptionType" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+f+'].answerComment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field2">-</button></div></div>');
            		//alert(1);
                }
               });
                
                var g=0;
                $('#buttonanswer8').on("click",function(e){
                	/* alert(b); */
                	
                	var que=7;
                	e.preventDefault();
                	if(g < max_fields){ //max input box allowed
                        g++; //text box increment
                	var answerwrapper   = $("#subquestiontype_list8");
                	$(answerwrapper).append('<div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+g+'].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+g+'].answerPoints" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+g+'].answerCode" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+g+'].answerResponse" id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+g+'].answerOptionType" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+g+'].answerComment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field2">-</button></div></div>');
            		//alert(1);
                }
               }); 
                
                var h=0;
                $('#buttonanswer9').on("click",function(e){
                	/* alert(b); */
                	
                	var que=8;
                	e.preventDefault();
                	if(h < max_fields){ //max input box allowed
                        h++; //text box increment
                	var answerwrapper   = $("#subquestiontype_list9");
                	$(answerwrapper).append('<div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+h+'].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+h+'].answerPoints" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+h+'].answerCode" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+h+'].answerResponse" id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+h+'].answerOptionType" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+h+'].answerComment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field2">-</button></div></div>');
            		//alert(1);
                }
               });
                
                var l=0;
                $('#buttonanswer10').on("click",function(e){
                	/* alert(b); */
                	
                	var que=9;
                	e.preventDefault();
                	if(l < max_fields){ //max input box allowed
                        l++; //text box increment
                	var answerwrapper   = $("#subquestiontype_list10");
                	$(answerwrapper).append('<div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+l+'].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+l+'].answerPoints" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+l+'].answerCode" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+l+'].answerResponse" id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+l+'].answerOptionType" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+l+'].answerComment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field2">-</button></div></div>');
            		//alert(1);
                }
               });
                
                var m=0;
                $('#buttonanswer11').on("click",function(e){
                	/* alert(b); */
                	
                	var que=10;
                	e.preventDefault();
                	if(m < max_fields){ //max input box allowed
                        m++; //text box increment
                	var answerwrapper   = $("#subquestiontype_list11");
                	$(answerwrapper).append('<div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+m+'].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+m+'].answerPoints" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+m+'].answerCode" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+m+'].answerResponse" id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+m+'].answerOptionType" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+m+'].answerComment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field2">-</button></div></div>');
            		//alert(1);
                }
               });
                
                var n=0;
                $('#buttonanswer12').on("click",function(e){
                	/* alert(b); */
                	
                	var que=11;
                	e.preventDefault();
                	if(n < max_fields){ //max input box allowed
                        n++; //text box increment
                	var answerwrapper   = $("#subquestiontype_list12");
                	$(answerwrapper).append('<div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+n+'].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+n+'].answerPoints" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+n+'].answerCode" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata['+que+'].subquestionanswers['+n+'].answerResponse" id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+n+'].answerOptionType" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata['+que+'].subquestionanswers['+n+'].answerComment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field2">-</button></div></div>');
            		//alert(1);
                }
               });
                
            }
            
            $(answerwrapper).on("click",".remove_field2", function(e){ //user click on remove text
                e.preventDefault(); 
            $(this).parent('#main_answer').remove();
            
            q--;
            })
            
            
            
            
           
        });
        
        $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
        
        e.preventDefault(); 
        $(this).parent('.card').remove();
        $('.formulae .row:last-child').remove();
        x--;
        //alert(x);
        });
      
        
        $("#subquestiontype_list1").on("click",".remove_field2", function(e){ //user click on remove text
            e.preventDefault(); 
        $(this).parent('#main_answer').remove();
        
        z--;
        })
       
        var z=0;
        $('#buttonanswer1').on("click",function(e){
        	e.preventDefault();
        	if(z < max_fields){ //max input box allowed
                z++; //text box increment
        	var answerwrapper   = $("#subquestiontype_list1");
        	$(answerwrapper).append('<div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata[0].subquestionanswers['+z+'].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestiondata[0].subquestionanswers['+z+'].answerPoints" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata[0].subquestionanswers['+z+'].answerCode" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestiondata[0].subquestionanswers['+z+'].answerResponse" id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata[0].subquestionanswers['+z+'].answerOptionType" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestiondata[0].subquestionanswers['+z+'].answerComment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field2">-</button></div></div>');
    		//alert(1);
        }
       });
        
       
     
    
    });
    
    
   
    </script>   
    
    
    <script>
    
    $(document).ready(function() {
        var max_fields      = 10; //maximum input boxes allowed
        var wrapper         = $("#formula"); //Fields wrapper
        var add_button      = $(".add_field_button3"); //Add button ID
    	$(wrapper).append('<div class="card" id="formul" style="width: 500px; display:"><div class="card-header" role="tab" id="headingOne0"><h6 class="mb-0 mt-0"><a data-toggle="collapse" data-parent="#accordion" href="#collapseOne0" class="text-dark" aria-expanded="true" aria-controls="collapseOne'+x+'">Add Formula 1</a></h6></div><div id="collapseOne0" class="collapse" role="tabpanel" aria-labelledby="headingOne"><div class="card-body formulae" id="formulae1"><div class="row"><div class="form-group col-md-6"><div class="checkbox1"><input id="checkbox0" name="formuladata[0].subQuestionId" class="checkbox0" value="A" type="checkbox"><label for="checkbox0">Question 1</label></div></div><div  class="form-group col-md-6"><select name="formuladata[0].formulaResult" class="form-control" id="formulaetype" ><option value="">Select</option><option value="Yes">Yes</option><option value="No">No</option></select></div></div></div><div class="pull-right  row"><div class="form-group col-md-10"><label for="emailAddress">Result</label><input type="text" name="formuladata[0].formulaFinalResult" parsley-trigger="change" placeholder="Enter Result" class="form-control" id="result"></div></div></div></div>'); //add //on add input button click
    	var x = 0; 
        
        $(add_button).on("click",function(e){
        	var div= document.getElementById("formulae1").innerHTML;
            
             e.preventDefault();
            if(x < max_fields){ //max input box allowed
                x++; //text box increment
                
                $(wrapper).append('<div class="card" id="formul" style="width: 500px; display:"><div class="card-header" role="tab" id="headingOne'+x+'"><h6 class="mb-0 mt-0"><a data-toggle="collapse" data-parent="#accordion" href="#collapseOne'+x+'" class="text-dark" aria-expanded="true" aria-controls="collapseOne'+x+'">Add Formula '+(x+1)+'</a></h6></div><button class="remove_field removefields'+x+'">-</button><div id="collapseOne'+x+'" class="collapse" role="tabpanel" aria-labelledby="headingOne"><div class="card-body formulae" id="formulae'+(x+1)+'"></div><div class="pull-right  row"><div class="form-group col-md-10"><label for="emailAddress">Result</label><input type="text" name="formuladata['+x+'].formulaFinalResult" parsley-trigger="change" placeholder="Enter Result" class="form-control" id="result"></div></div></div></div>'); //add input box
               $('.removefields'+(x-1)).hide();
              
            }
            //alert(div);
            
           var div1=div.replace(/\[0\]/g,"["+x+"]")
           //alert("hello"+div1);
            	document.getElementById("formulae"+(x+1)).innerHTML=div1;	 
            
            
            
        });
        
        $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
            e.preventDefault(); 
        $(this).parent('#formul').remove(); 
        x--;
        })
    });
    </script>
    
 <script>
 $('#section').on('change', function () {
 	var sectionId=$('#section').val();
 	//alert(sectionId);
 	$.ajax({
         url: "<%=dashboardURL %>getSubSectionBySectionId",
         type: "GET", 
         data: {'sectionId': sectionId},
         success: function(response)
                     {
         	//alert(response.length);
         	 $( "#subSection" ).empty();
         	$( "#subSection" ).append('<option value="">Select Sub Section</option>')
         	for(var i=0;i<response.length;i++){
         		$("#subSection").append('<option value="'+response[i].subSectionId+'">'+response[i].subSection+'</option>')
         	}
         	//$('#subSectionId2').val(response.subSectionId);
            }
       });
 });
 </script>   
    
  <script>
  function call(x){
  
  
  var count=x;
  var sectionId=$('#section'+count).val();
	//alert(sectionId);
	$.ajax({
       url: "<%=dashboardURL %>getSubSectionBySectionId",
       type: "GET", 
       data: {'sectionId': sectionId},
       success: function(response)
                   {
       	//alert(response.length);
       	 $( "#subSection"+count ).empty();
       	$( "#subSection"+count ).append('<option value="">Select Sub Section</option>')
       	for(var i=0;i<response.length;i++){
       		$("#subSection"+count).append('<option value="'+response[i].subSectionId+'">'+response[i].subSection+'</option>')
       	}
       	//$('#subSectionId2').val(response.subSectionId);
          }
     });
  }
  </script>
  
  <script>
  function callstandardnumber(x){
  
  var count=x;
  var subSection=$('#subSection'+count).val();
	$.ajax({
       url: "<%=dashboardURL %>getStandardNumberBySubSectionId",
       type: "GET", 
       data: {'subSection': subSection},
       success: function(response)
                   {
       	 $( "#standardNumber"+count ).empty();
       	$( "#standardNumber"+count ).append('<option value="">Select Sub Section</option>')
       	for(var i=0;i<response.length;i++){
       		$("#standardNumber"+count).append('<option value="'+response[i].standardNumber+'">'+response[i].standardNumber+'</option>')
       	}
       	//$('#subSectionId2').val(response.subSectionId);
          }
     });
  }
  </script>
  
  <script>
  function callQuestion(x){
	
  var count=x;
  
  var standardNumber=$('#standardNumber'+count).val();
	
	$.ajax({
       url: "<%=dashboardURL %>getQuestionByStandNumber",
       type: "GET", 
       data: {'standardNumber': standardNumber},
       success: function(response)
                   {
    	   
    	  
    	   var qid = response.questionId;
    	   var type=response.questionType;
    	   if(type=="Select/List")
    		   {
    		   $("#super_answer"+count).empty();
    		   
    	   $.ajax({
    	       url: "<%=dashboardURL %>getAnswerList",
    	       type: "GET", 
    	       data: {'qid': qid,'type':type},
    	       success: function(response)
    	                   {
			    	    	 
    	    	   			/*  var answerList=JSON.stringify(response);
			       			 var jsonString=JSON.parse(answerList); */
			       			
    	    	  			 for(var i=0;i<response.length;i++)
				       	 	    {
				       	 	 	  $("#super_answer"+count).append("<option value='"+response[i].answer+"'>"+response[i].answer+"</option>");
				       	 	    }
    	         		   }
    	     });
    		   }
    	   else{
    		   $("#super_answer"+count).empty();
    		   $("#super_answer"+count).append('<option value="Yes">Yes</option><option value="No">No</option>');
    	   }
    	  
       $('#super_question'+count).val(response.questionId);
          }
     });
  }
  </script>


    </body>
</html>