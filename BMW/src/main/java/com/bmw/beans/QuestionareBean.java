package com.bmw.beans;
import java.util.List;

public class QuestionareBean extends QuestionBean{

private List<QuestionBean>questiondata;

public List<QuestionBean> getQuestiondata() {
	return questiondata;
}

public void setQuestiondata(List<QuestionBean> questiondata) {
	this.questiondata = questiondata;
}

private List<QuestionBean>answerdata;

public List<QuestionBean> getAnswerdata() {
	return answerdata;
}

public void setAnswerdata(List<QuestionBean> answerdata) {
	this.answerdata = answerdata;
}

private List<QuestionBean>subquestiondata;

public List<QuestionBean> getSubquestiondata() {
	return subquestiondata;
}

public void setSubquestiondata(List<QuestionBean> subquestiondata) {
	this.subquestiondata = subquestiondata;
}

private List<QuestionBean>formuladata;

public List<QuestionBean> getFormuladata() {
	return formuladata;
}

public void setFormuladata(List<QuestionBean> formuladata) {
	this.formuladata = formuladata;
}



	
}
