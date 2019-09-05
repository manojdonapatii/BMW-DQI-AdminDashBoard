package com.bmw.beans;

import java.util.List;

public class QuestionBean {

	private String section;
	private String section_id;
	private String standard;
	private String requirement;
	private String comment;
	private String number;
	private String xs;
	private String s;
	private String m;
	private String l;
	private String xl;
	private String xxl;
	private String check;
	private String essential;
	private String question;
	private String observation;
	private String person;
	private String questionnaire_id;
	private String user_id;
	private String outlet_size;
	private String outlet_type;
	private String outlets;
	private String outlet_id;
	private String answer;
	private String dealer_id;
	private String audit_schedule_id;
	private String answer_type;
	private String audit_id;
	private String img;
	private String badge;
	private String brand;
	private String type;
	private String pic;
	private String best_practice;
	private String default_logo;
	private String dealer;
	private String review_status;
	private String end_date;
	private String auditor_comment;
	private String dealer_comment;
	private String exception_remarks;
	private String image_manadatory;
	private String model;
	private String model_id;
	private String sectionId;
	private String result;
	private String score_type;
	private String avg;
	private String min;
	private String max;
	private String sales_rank;
	private String service_rank;
	private String dealer_sales_count;
	private String dealer_service_count;
	private String year;
	private String phase;
	private String region;
	private String email;
	private String ph;

	//
	private String modeOfContact;
	private String subSectionId;
	private String subSection;
	private String standardNumber;
	private String questionType;
	private String questionOptionType;
	private String questionPoints;
	private String questionCode;
	private String questionResponse;
	private String questionRouting;
	private String answerType;
	private String questionId;
	private String subQuestionId;
	private String answerId;
	private String answerPoints;
	private String answerCode;
	private String answerResponse;
	private String answerOptionType;
	private String answerComment;
	private String SubQuestionAnswer;
	private String SubQuestionAnswerId;
	private String SubQuestionAnswerPoints;
	private String SubQuestionAnswerCode;
	private String SubQuestionAnswerResponse;
	private String SubQuestionAnswerOptionType;
	private String SubQuestionAnswerComment;
	private String subQuestion;
	private String subQuestionAnswerType;
	private String superQuestion;
	private String superQuestionAnswer;
	private String formulaId;
	private String formulaResult;
	private String formulaFinalResult;
	private String datePoints;
	private String dateCode;
	private String dateResponse;
	private String dateRouting;
	private String commentMandatory;
	private String commentCriteria;
	private String nsc_count;
	private String nsc_count_ans;
	private String ms_count;
	private String ms_count_ans;
	private String audit_count;
	private String audit_count_ans;
	private String one_count;
	private String zero_count;
	private String essential_count;
	private String question_count;
	private String answer_count;
	private String total_qns_count;
	private String total_ans_count;
	private String total_zero_count;
	private String score;
	private String audit_qn_count;
	private String nsc_qn_count;
	private String ms_qn_count;
	private String audit_ans_count;
	private String nsc_ans_count;
	private String ms_ans_count;
	private String contractual;
	private String dealer_avg;
	private String dealer_sales_avg;
	private String dealer_service_avg;

	/*
	 * Manju - 02-11-2018
	 */
	private String date;
	private String time;
	private String timePoints;
	private String timeCode;
	private String timeResponse;
	private String timeRouting;
	private String routingType;
	/*
	 * Manju - 02-11-2018
	 */

	/*
	 * Vamshi - 02-11-2018
	 */
	private String sk_model_id;
	private String serial_number;
	private String sk_serial_no;

	private String sk_shopper_id;
	private String name;
	private String age;
	private String email_id;
	private String contact_no;
	private String gender;
	private String reference_image;
	/*
	 * Vamshi - 02-11-2018
	 */

	private List<QuestionBean> answerDetails;
	private List<QuestionBean> subQuestionAnswerDetails;
	private List<QuestionBean> dependentDetails;

	private List<AnswerBean> subquestionanswers;
	private String dealer_remarks;
	private String dealer_image;
	//

	public String getSectionId() {
		return sectionId;
	}

	public String getDealer_image() {
		return dealer_image;
	}

	public void setDealer_image(String dealer_image) {
		this.dealer_image = dealer_image;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getModel_id() {
		return model_id;
	}

	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}

	public String getDealer_avg() {
		return dealer_avg;
	}

	public void setDealer_avg(String dealer_avg) {
		this.dealer_avg = dealer_avg;
	}

	public String getReference_image() {
		return reference_image;
	}

	public void setReference_image(String reference_image) {
		this.reference_image = reference_image;
	}

	public String getDealer_sales_avg() {
		return dealer_sales_avg;
	}

	public void setDealer_sales_avg(String dealer_sales_avg) {
		this.dealer_sales_avg = dealer_sales_avg;
	}

	public String getDealer_service_avg() {
		return dealer_service_avg;
	}

	public void setDealer_service_avg(String dealer_service_avg) {
		this.dealer_service_avg = dealer_service_avg;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getNsc_count() {
		return nsc_count;
	}

	public void setNsc_count(String nsc_count) {
		this.nsc_count = nsc_count;
	}

	public String getTotal_qns_count() {
		return total_qns_count;
	}

	public void setTotal_qns_count(String total_qns_count) {
		this.total_qns_count = total_qns_count;
	}

	public String getTotal_ans_count() {
		return total_ans_count;
	}

	public void setTotal_ans_count(String total_ans_count) {
		this.total_ans_count = total_ans_count;
	}

	public String getTotal_zero_count() {
		return total_zero_count;
	}

	public void setTotal_zero_count(String total_zero_count) {
		this.total_zero_count = total_zero_count;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getNsc_count_ans() {
		return nsc_count_ans;
	}

	public void setNsc_count_ans(String nsc_count_ans) {
		this.nsc_count_ans = nsc_count_ans;
	}

	public String getMs_count() {
		return ms_count;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDealer_remarks() {
		return dealer_remarks;
	}

	public void setDealer_remarks(String dealer_remarks) {
		this.dealer_remarks = dealer_remarks;
	}

	public void setMs_count(String ms_count) {
		this.ms_count = ms_count;
	}

	public String getMs_count_ans() {
		return ms_count_ans;
	}

	public void setMs_count_ans(String ms_count_ans) {
		this.ms_count_ans = ms_count_ans;
	}

	public String getAudit_count() {
		return audit_count;
	}

	public String getOne_count() {
		return one_count;
	}

	public void setOne_count(String one_count) {
		this.one_count = one_count;
	}

	public String getZero_count() {
		return zero_count;
	}

	public void setZero_count(String zero_count) {
		this.zero_count = zero_count;
	}

	public void setAudit_count(String audit_count) {
		this.audit_count = audit_count;
	}

	public String getAudit_count_ans() {
		return audit_count_ans;
	}

	public void setAudit_count_ans(String audit_count_ans) {
		this.audit_count_ans = audit_count_ans;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSection_id() {
		return section_id;
	}

	public void setSection_id(String section_id) {
		this.section_id = section_id;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getXs() {
		return xs;
	}

	public void setXs(String xs) {
		this.xs = xs;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getL() {
		return l;
	}

	public void setL(String l) {
		this.l = l;
	}

	public String getXl() {
		return xl;
	}

	public void setXl(String xl) {
		this.xl = xl;
	}

	public String getXxl() {
		return xxl;
	}

	public void setXxl(String xxl) {
		this.xxl = xxl;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getEssential() {
		return essential;
	}

	public void setEssential(String essential) {
		this.essential = essential;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getQuestionnaire_id() {
		return questionnaire_id;
	}

	public void setQuestionnaire_id(String questionnaire_id) {
		this.questionnaire_id = questionnaire_id;
	}

	public String getOutlet_size() {
		return outlet_size;
	}

	public void setOutlet_size(String outlet_size) {
		this.outlet_size = outlet_size;
	}

	public String getOutlet_type() {
		return outlet_type;
	}

	public void setOutlet_type(String outlet_type) {
		this.outlet_type = outlet_type;
	}

	public String getOutlets() {
		return outlets;
	}

	public void setOutlets(String outlets) {
		this.outlets = outlets;
	}

	public String getOutlet_id() {
		return outlet_id;
	}

	public void setOutlet_id(String outlet_id) {
		this.outlet_id = outlet_id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getDealer_id() {
		return dealer_id;
	}

	public void setDealer_id(String dealer_id) {
		this.dealer_id = dealer_id;
	}

	public String getAudit_schedule_id() {
		return audit_schedule_id;
	}

	public void setAudit_schedule_id(String audit_schedule_id) {
		this.audit_schedule_id = audit_schedule_id;
	}

	public String getAnswer_type() {
		return answer_type;
	}

	public void setAnswer_type(String answer_type) {
		this.answer_type = answer_type;
	}

	public String getAudit_id() {
		return audit_id;
	}

	public void setAudit_id(String audit_id) {
		this.audit_id = audit_id;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getBest_practice() {
		return best_practice;
	}

	public void setBest_practice(String best_practice) {
		this.best_practice = best_practice;
	}

	public String getDefault_logo() {
		return default_logo;
	}

	public void setDefault_logo(String default_logo) {
		this.default_logo = default_logo;
	}

	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	public String getReview_status() {
		return review_status;
	}

	public void setReview_status(String review_status) {
		this.review_status = review_status;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getAuditor_comment() {
		return auditor_comment;
	}

	public void setAuditor_comment(String auditor_comment) {
		this.auditor_comment = auditor_comment;
	}

	public String getDealer_comment() {
		return dealer_comment;
	}

	public void setDealer_comment(String dealer_comment) {
		this.dealer_comment = dealer_comment;
	}

	public String getException_remarks() {
		return exception_remarks;
	}

	public void setException_remarks(String exception_remarks) {
		this.exception_remarks = exception_remarks;
	}

	public String getImage_manadatory() {
		return image_manadatory;
	}

	public void setImage_manadatory(String image_manadatory) {
		this.image_manadatory = image_manadatory;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModeOfContact() {
		return modeOfContact;
	}

	public void setModeOfContact(String modeOfContact) {
		this.modeOfContact = modeOfContact;
	}

	public String getSubSectionId() {
		return subSectionId;
	}

	public void setSubSectionId(String subSectionId) {
		this.subSectionId = subSectionId;
	}

	public String getSubSection() {
		return subSection;
	}

	public void setSubSection(String subSection) {
		this.subSection = subSection;
	}

	public String getStandardNumber() {
		return standardNumber;
	}

	public void setStandardNumber(String standardNumber) {
		this.standardNumber = standardNumber;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionOptionType() {
		return questionOptionType;
	}

	public void setQuestionOptionType(String questionOptionType) {
		this.questionOptionType = questionOptionType;
	}

	public String getQuestionPoints() {
		return questionPoints;
	}

	public void setQuestionPoints(String questionPoints) {
		this.questionPoints = questionPoints;
	}

	public String getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	public String getQuestionResponse() {
		return questionResponse;
	}

	public void setQuestionResponse(String questionResponse) {
		this.questionResponse = questionResponse;
	}

	public String getQuestionRouting() {
		return questionRouting;
	}

	public void setQuestionRouting(String questionRouting) {
		this.questionRouting = questionRouting;
	}

	public String getAnswerType() {
		return answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getSubQuestionId() {
		return subQuestionId;
	}

	public void setSubQuestionId(String subQuestionId) {
		this.subQuestionId = subQuestionId;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public String getAnswerPoints() {
		return answerPoints;
	}

	public void setAnswerPoints(String answerPoints) {
		this.answerPoints = answerPoints;
	}

	public String getAnswerCode() {
		return answerCode;
	}

	public void setAnswerCode(String answerCode) {
		this.answerCode = answerCode;
	}

	public String getAnswerResponse() {
		return answerResponse;
	}

	public void setAnswerResponse(String answerResponse) {
		this.answerResponse = answerResponse;
	}

	public String getAnswerOptionType() {
		return answerOptionType;
	}

	public void setAnswerOptionType(String answerOptionType) {
		this.answerOptionType = answerOptionType;
	}

	public String getAnswerComment() {
		return answerComment;
	}

	public String getAvg() {
		return avg;
	}

	public void setAvg(String avg) {
		this.avg = avg;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public void setAnswerComment(String answerComment) {
		this.answerComment = answerComment;
	}

	public String getSubQuestionAnswer() {
		return SubQuestionAnswer;
	}

	public void setSubQuestionAnswer(String subQuestionAnswer) {
		SubQuestionAnswer = subQuestionAnswer;
	}

	public String getSubQuestionAnswerId() {
		return SubQuestionAnswerId;
	}

	public void setSubQuestionAnswerId(String subQuestionAnswerId) {
		SubQuestionAnswerId = subQuestionAnswerId;
	}

	public String getSubQuestionAnswerPoints() {
		return SubQuestionAnswerPoints;
	}

	public void setSubQuestionAnswerPoints(String subQuestionAnswerPoints) {
		SubQuestionAnswerPoints = subQuestionAnswerPoints;
	}

	public String getSubQuestionAnswerCode() {
		return SubQuestionAnswerCode;
	}

	public void setSubQuestionAnswerCode(String subQuestionAnswerCode) {
		SubQuestionAnswerCode = subQuestionAnswerCode;
	}

	public String getSubQuestionAnswerResponse() {
		return SubQuestionAnswerResponse;
	}

	public void setSubQuestionAnswerResponse(String subQuestionAnswerResponse) {
		SubQuestionAnswerResponse = subQuestionAnswerResponse;
	}

	public String getSubQuestionAnswerOptionType() {
		return SubQuestionAnswerOptionType;
	}

	public void setSubQuestionAnswerOptionType(String subQuestionAnswerOptionType) {
		SubQuestionAnswerOptionType = subQuestionAnswerOptionType;
	}

	public String getSubQuestionAnswerComment() {
		return SubQuestionAnswerComment;
	}

	public void setSubQuestionAnswerComment(String subQuestionAnswerComment) {
		SubQuestionAnswerComment = subQuestionAnswerComment;
	}

	public String getSubQuestion() {
		return subQuestion;
	}

	public void setSubQuestion(String subQuestion) {
		this.subQuestion = subQuestion;
	}

	public String getSubQuestionAnswerType() {
		return subQuestionAnswerType;
	}

	public void setSubQuestionAnswerType(String subQuestionAnswerType) {
		this.subQuestionAnswerType = subQuestionAnswerType;
	}

	public String getSuperQuestion() {
		return superQuestion;
	}

	public void setSuperQuestion(String superQuestion) {
		this.superQuestion = superQuestion;
	}

	public String getSuperQuestionAnswer() {
		return superQuestionAnswer;
	}

	public void setSuperQuestionAnswer(String superQuestionAnswer) {
		this.superQuestionAnswer = superQuestionAnswer;
	}

	public String getFormulaId() {
		return formulaId;
	}

	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}

	public String getFormulaResult() {
		return formulaResult;
	}

	public void setFormulaResult(String formulaResult) {
		this.formulaResult = formulaResult;
	}

	public String getFormulaFinalResult() {
		return formulaFinalResult;
	}

	public String getScore_type() {
		return score_type;
	}

	public void setScore_type(String score_type) {
		this.score_type = score_type;
	}

	public void setFormulaFinalResult(String formulaFinalResult) {
		this.formulaFinalResult = formulaFinalResult;
	}

	public String getDatePoints() {
		return datePoints;
	}

	public void setDatePoints(String datePoints) {
		this.datePoints = datePoints;
	}

	public String getDateCode() {
		return dateCode;
	}

	public void setDateCode(String dateCode) {
		this.dateCode = dateCode;
	}

	public String getDateResponse() {
		return dateResponse;
	}

	public void setDateResponse(String dateResponse) {
		this.dateResponse = dateResponse;
	}

	public String getDateRouting() {
		return dateRouting;
	}

	public void setDateRouting(String dateRouting) {
		this.dateRouting = dateRouting;
	}

	public String getCommentMandatory() {
		return commentMandatory;
	}

	public void setCommentMandatory(String commentMandatory) {
		this.commentMandatory = commentMandatory;
	}

	public String getCommentCriteria() {
		return commentCriteria;
	}

	public void setCommentCriteria(String commentCriteria) {
		this.commentCriteria = commentCriteria;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimePoints() {
		return timePoints;
	}

	public void setTimePoints(String timePoints) {
		this.timePoints = timePoints;
	}

	public String getTimeCode() {
		return timeCode;
	}

	public void setTimeCode(String timeCode) {
		this.timeCode = timeCode;
	}

	public String getTimeResponse() {
		return timeResponse;
	}

	public void setTimeResponse(String timeResponse) {
		this.timeResponse = timeResponse;
	}

	public String getTimeRouting() {
		return timeRouting;
	}

	public void setTimeRouting(String timeRouting) {
		this.timeRouting = timeRouting;
	}

	public String getRoutingType() {
		return routingType;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setRoutingType(String routingType) {
		this.routingType = routingType;
	}

	public String getSk_model_id() {
		return sk_model_id;
	}

	public void setSk_model_id(String sk_model_id) {
		this.sk_model_id = sk_model_id;
	}

	public String getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	public String getSk_serial_no() {
		return sk_serial_no;
	}

	public void setSk_serial_no(String sk_serial_no) {
		this.sk_serial_no = sk_serial_no;
	}

	public String getSk_shopper_id() {
		return sk_shopper_id;
	}

	public void setSk_shopper_id(String sk_shopper_id) {
		this.sk_shopper_id = sk_shopper_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<QuestionBean> getAnswerDetails() {
		return answerDetails;
	}

	public void setAnswerDetails(List<QuestionBean> answerDetails) {
		this.answerDetails = answerDetails;
	}

	public List<QuestionBean> getSubQuestionAnswerDetails() {
		return subQuestionAnswerDetails;
	}

	public void setSubQuestionAnswerDetails(List<QuestionBean> subQuestionAnswerDetails) {
		this.subQuestionAnswerDetails = subQuestionAnswerDetails;
	}

	public List<QuestionBean> getDependentDetails() {
		return dependentDetails;
	}

	public void setDependentDetails(List<QuestionBean> dependentDetails) {
		this.dependentDetails = dependentDetails;
	}

	public List<AnswerBean> getSubquestionanswers() {
		return subquestionanswers;
	}

	public void setSubquestionanswers(List<AnswerBean> subquestionanswers) {
		this.subquestionanswers = subquestionanswers;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getEssential_count() {
		return essential_count;
	}

	public void setEssential_count(String essential_count) {
		this.essential_count = essential_count;
	}

	public String getQuestion_count() {
		return question_count;
	}

	public void setQuestion_count(String question_count) {
		this.question_count = question_count;
	}

	public String getAnswer_count() {
		return answer_count;
	}

	public void setAnswer_count(String answer_count) {
		this.answer_count = answer_count;
	}

	public String getAudit_qn_count() {
		return audit_qn_count;
	}

	public void setAudit_qn_count(String audit_qn_count) {
		this.audit_qn_count = audit_qn_count;
	}

	public String getNsc_qn_count() {
		return nsc_qn_count;
	}

	public void setNsc_qn_count(String nsc_qn_count) {
		this.nsc_qn_count = nsc_qn_count;
	}

	public String getMs_qn_count() {
		return ms_qn_count;
	}

	public void setMs_qn_count(String ms_qn_count) {
		this.ms_qn_count = ms_qn_count;
	}

	public String getAudit_ans_count() {
		return audit_ans_count;
	}

	public void setAudit_ans_count(String audit_ans_count) {
		this.audit_ans_count = audit_ans_count;
	}

	public String getNsc_ans_count() {
		return nsc_ans_count;
	}

	public void setNsc_ans_count(String nsc_ans_count) {
		this.nsc_ans_count = nsc_ans_count;
	}

	public String getMs_ans_count() {
		return ms_ans_count;
	}

	public void setMs_ans_count(String ms_ans_count) {
		this.ms_ans_count = ms_ans_count;
	}

	public String getContractual() {
		return contractual;
	}

	public void setContractual(String contractual) {
		this.contractual = contractual;
	}

	public String getSales_rank() {
		return sales_rank;
	}

	public void setSales_rank(String sales_rank) {
		this.sales_rank = sales_rank;
	}

	public String getService_rank() {
		return service_rank;
	}

	public void setService_rank(String service_rank) {
		this.service_rank = service_rank;
	}

	public String getDealer_sales_count() {
		return dealer_sales_count;
	}

	public void setDealer_sales_count(String dealer_sales_count) {
		this.dealer_sales_count = dealer_sales_count;
	}

	public String getDealer_service_count() {
		return dealer_service_count;
	}

	public void setDealer_service_count(String dealer_service_count) {
		this.dealer_service_count = dealer_service_count;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

}
