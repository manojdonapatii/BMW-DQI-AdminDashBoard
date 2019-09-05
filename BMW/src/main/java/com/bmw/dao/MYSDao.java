package com.bmw.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bmw.api.SendEmailUsingGMailSMTP;
import com.bmw.beans.AnswerBean;
import com.bmw.beans.MenuBean;
import com.bmw.beans.QuestionBean;
import com.bmw.beans.QuestionareBean;
import com.bmw.beans.UserBean;

public class MYSDao {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	ResourceBundle resource = ResourceBundle.getBundle("resources/web");
	final String dashboardURL = resource.getString("dashboardURL");

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();

	/////////////////////////////////////////// MYSTRY VIVEK
	/////////////////////////////////////////// 12-11-2018/////////////////////////////////////////////////

	public List<QuestionBean> getSections(QuestionBean qBean) {
		System.out.println("SELECT * FROM mys_mst_section;");
		return template.query("SELECT * FROM mys_mst_section;", new RowMapper<QuestionBean>() {
			public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
				QuestionBean qBean = new QuestionBean();
				qBean.setSection(rs.getString("section_name"));
				qBean.setSectionId(rs.getString("sk_section_id"));
				return qBean;
			}
		});
	}

	public void addQuestionnaire(final QuestionBean qBean, final QuestionareBean answer,
			final QuestionareBean superquestion, final QuestionareBean subquestiondata) {

		System.out.println(
				"INSERT INTO mys_mst_questionare(mode_of_contact, section_id, subsection_id, standard_number, question_type, question_optiontype, question_points, question_code, question_response, question_routing,question, answer_type, comment_mandatory, comment_criteria) VALUES ('"
						+ qBean.getModeOfContact() + "','" + qBean.getSection() + "','" + qBean.getSubSection() + "','"
						+ qBean.getStandardNumber() + "','" + qBean.getQuestionType() + "','"
						+ qBean.getQuestionOptionType() + "','" + qBean.getQuestionPoints() + "','"
						+ qBean.getQuestionCode() + "','" + qBean.getQuestionResponse() + "','"
						+ qBean.getQuestionRouting() + "','"
						+ (qBean.getQuestion().replace("'", "\\'")).replace("\"", "\"") + "','" + qBean.getAnswerType()
						+ "','" + qBean.getCommentMandatory() + "','" + qBean.getCommentCriteria() + "');");
		template.execute(
				"INSERT INTO mys_mst_questionare(mode_of_contact, section_id, subsection_id, standard_number, question_type, question_optiontype, question_points, question_code, question_response, question_routing,question, answer_type, comment_mandatory, comment_criteria) VALUES ('"
						+ qBean.getModeOfContact() + "','" + qBean.getSection() + "','" + qBean.getSubSection() + "','"
						+ qBean.getStandardNumber() + "','" + qBean.getQuestionType() + "','"
						+ qBean.getQuestionOptionType() + "','" + qBean.getQuestionPoints() + "','"
						+ qBean.getQuestionCode() + "','" + qBean.getQuestionResponse() + "','"
						+ qBean.getQuestionRouting() + "','"
						+ (qBean.getQuestion().replace("'", "\\'")).replace("\"", "\"") + "','" + qBean.getAnswerType()
						+ "','" + qBean.getCommentMandatory() + "','" + qBean.getCommentCriteria() + "');");

		template.queryForObject("select max(sk_question_id) as questionId from mys_mst_questionare",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						// QuestionBean homeBean = new QuestionBean();
						String questionId = rs.getString("questionId");
						System.out.println(questionId);
						if (qBean.getAnswerType().equals("Select/List")) {
							addAnswers(qBean, questionId, qBean.getSubQuestionId(), answer);
						}
						if (qBean.getAnswerType().equals("Date & Time")) {
							addDateAnswers(qBean, questionId, qBean.getSubQuestionId());
						}
						System.out.println("hello" + qBean.getQuestionType());
						if (qBean.getQuestionType().equals("Main Question With Set Of SubQuestions")) {
							System.out.println("in here");
							addSubQuestions(qBean, questionId, subquestiondata);
							addSubQuestionsFormula(questionId, subquestiondata);
						}
						if (qBean.getQuestionType().equals("Dependent Question")) {
							System.out.println("in here");
							addDependQuestions(qBean, questionId, superquestion);
						}
						if (qBean.getQuestionType().equals("Dependent Question With Set Of SubQuestions")) {
							System.out.println("in here");
							addDependQuestions(qBean, questionId, superquestion);
							addSubQuestions(qBean, questionId, subquestiondata);
							addSubQuestionsFormula(questionId, subquestiondata);
						}
						return null;

					}
				});
	}

	public QuestionBean addAnswers(QuestionBean qBean, String questionId, String subQuestionId,
			QuestionareBean answer1) {

		if (subQuestionId == null) {
			subQuestionId = "NULL";
		}

		try {
			System.out.println("hello" + answer1.getAnswerdata().size());
			System.out.println("data" + answer1.getAnswerdata().get(1).getAnswer());
		} catch (Exception e) {
			System.out.println(e + "chethan's");
		}
		for (int i = 0; i < answer1.getAnswerdata().size(); i++) {

			String answer = answer1.getAnswerdata().get(i).getAnswer();
			if (answer != null) {
				System.out.println(
						"INSERT INTO mys_mst_answer(question_id, subquestion_id, answer, answer_points, answer_code, answer_response, answer_optiontype, answer_comment,routing_type) VALUES ('"
								+ questionId + "'," + subQuestionId + ",'" + answer1.getAnswerdata().get(i).getAnswer()
								+ "','" + answer1.getAnswerdata().get(i).getAnswerPoints() + "','"
								+ answer1.getAnswerdata().get(i).getAnswerCode() + "','"
								+ answer1.getAnswerdata().get(i).getAnswerResponse() + "','"
								+ answer1.getAnswerdata().get(i).getAnswerOptionType() + "','"
								+ answer1.getAnswerdata().get(i).getAnswerComment() + "','"
								+ answer1.getAnswerdata().get(i).getRoutingType() + "')");
				template.execute(
						"INSERT INTO mys_mst_answer(question_id, subquestion_id, answer, answer_points, answer_code, answer_response, answer_optiontype, answer_comment,routing_type) VALUES ('"
								+ questionId + "'," + subQuestionId + ",'" + answer1.getAnswerdata().get(i).getAnswer()
								+ "','" + answer1.getAnswerdata().get(i).getAnswerPoints() + "','"
								+ answer1.getAnswerdata().get(i).getAnswerCode() + "','"
								+ answer1.getAnswerdata().get(i).getAnswerResponse() + "','"
								+ answer1.getAnswerdata().get(i).getAnswerOptionType() + "','"
								+ answer1.getAnswerdata().get(i).getAnswerComment() + "','"
								+ answer1.getAnswerdata().get(i).getRoutingType() + "')");
			}
		}
		return qBean;
	}

	public void addDateAnswers(QuestionBean qBean, String questionId, String subQuestionId) {
		if (subQuestionId == null) {
			subQuestionId = "NULL";
		}
		System.out.println(
				"INSERT INTO mys_mystry_dateanswers(question_id, subquestion_id, date_points, date_code, date_response, date_routing,time_points,time_code,time_response,time_routing) VALUES ('"
						+ questionId + "'," + subQuestionId + ",'" + qBean.getDatePoints() + "','" + qBean.getDateCode()
						+ "','" + qBean.getDateResponse() + "','" + qBean.getDateRouting() + "','"
						+ qBean.getTimePoints() + "','" + qBean.getTimeCode() + "','" + qBean.getTimeResponse() + "','"
						+ qBean.getTimeRouting() + "');");
		template.execute(
				"INSERT INTO mys_mystry_dateanswers(question_id, subquestion_id, date_points, date_code, date_response, date_routing,time_points,time_code,time_response,time_routing) VALUES ('"
						+ questionId + "'," + subQuestionId + ",'" + qBean.getDatePoints() + "','" + qBean.getDateCode()
						+ "','" + qBean.getDateResponse() + "','" + qBean.getDateRouting() + "','"
						+ qBean.getTimePoints() + "','" + qBean.getTimeCode() + "','" + qBean.getTimeResponse() + "','"
						+ qBean.getTimeRouting() + "');");

	}

	public QuestionBean addSubQuestions(final QuestionBean qBean, final String questionId,
			final QuestionareBean SubQuestions) {

		// final String[] subQuestion=qBean.getSubQuestion().split(",");
		// final String[]
		// subQuestionAnswerType=qBean.getSubQuestionAnswerType().split(",");
		System.out.println("size of subquestions" + SubQuestions.getSubquestiondata().size());
		for (int i = 0; i < SubQuestions.getSubquestiondata().size(); i++) {

			// System.out.println("length of sub questions"+subQuestion.length);
			final String subQuestionAnswerType1 = SubQuestions.getSubquestiondata().get(i).getSubQuestionAnswerType();
			final List<AnswerBean> data = SubQuestions.getSubquestiondata().get(i).getSubquestionanswers();
			System.out.println("Input" + subQuestionAnswerType1);

			if (subQuestionAnswerType1 != null) {
				System.out.println("here" + i);
				System.out.println(
						"INSERT INTO mys_mst_subquestions(question_id, question, answer_type) VALUES (" + questionId + ",'"
								+ (SubQuestions.getSubquestiondata().get(i).getSubQuestion().replace("'", "\\'"))
										.replace("\"", "\"")
								+ "','" + SubQuestions.getSubquestiondata().get(i).getSubQuestionAnswerType() + "')");
				template.execute(
						"INSERT INTO mys_mst_subquestions(question_id, question, answer_type) VALUES (" + questionId + ",'"
								+ (SubQuestions.getSubquestiondata().get(i).getSubQuestion().replace("'", "\\'"))
										.replace("\"", "\"")
								+ "','" + SubQuestions.getSubquestiondata().get(i).getSubQuestionAnswerType() + "')");

				template.queryForObject("select max(sk_subquestion_id) as subQuestionId from mys_mst_subquestions",
						new RowMapper<QuestionBean>() {
							public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
								// QuestionBean homeBean = new QuestionBean();
								String subQuestionId = rs.getString("subQuestionId");
								System.out.println(questionId);
								System.out.println("Input" + subQuestionAnswerType1);
								if (subQuestionAnswerType1.equals("Select/List")) {
									System.out.println("insubbbb");
									System.out.println(questionId);
									System.out.println(subQuestionId);
									addAnswers1(qBean, questionId, subQuestionId, data);
								}

								return qBean;
							}
						});
			}
		}
		return qBean;
	}

	public QuestionBean addSubQuestionsFormula(final String questionId, final QuestionareBean SubQuestions) {

		// final String[] subQuestion=qBean.getSubQuestion().split(",");
		// final String[]
		// subQuestionAnswerType=qBean.getSubQuestionAnswerType().split(",");
		System.out.println("size of subquestions" + SubQuestions.getSubquestiondata().size());

		for (int i = 0; i < SubQuestions.getFormuladata().size(); i++) {
			final String formulaquestionId = SubQuestions.getFormuladata().get(i).getSubQuestionId();
			final String formularesult = SubQuestions.getFormuladata().get(i).getFormulaResult();
			System.out.println("here" + i);
			System.out.println(SubQuestions.getFormuladata().get(i).getFormulaFinalResult());
			if (!SubQuestions.getFormuladata().get(i).getFormulaFinalResult().equals("")) {
				System.out.println("INSERT INTO mys_mst_formula(question_id, result) VALUES ('" + questionId + "','"
						+ SubQuestions.getFormuladata().get(i).getFormulaFinalResult() + "')");
				template.execute("INSERT INTO mys_mst_formula(question_id, result) VALUES ('" + questionId + "','"
						+ SubQuestions.getFormuladata().get(i).getFormulaFinalResult() + "')");

				template.queryForObject("select max(sk_formula_id) as formulaId from mys_mst_formula",
						new RowMapper<QuestionBean>() {
							public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
								// QuestionBean homeBean = new QuestionBean();
								String formulaId = rs.getString("formulaId");
								System.out.println(questionId);
								addFormulaMap(SubQuestions, formulaquestionId, formularesult, formulaId);

								return SubQuestions;
							}
						});
			}
		}
		return SubQuestions;

	}

	public QuestionBean addFormulaMap(final QuestionBean qBean, final String questionId, final String formularesult,
			final String formulaId) {
		final String formulares = formularesult.replaceAll(",,", ",");
		System.out.println(formulares);
		final String[] formularesult1 = formulares.split(",");
		final String[] questionIds = questionId.split(",");
		System.out.println(questionIds.length);
		for (int i = 0; i < questionIds.length; i++) {

			System.out.println("INSERT INTO mys_mst_formula_map(formula_id, subquestion_id, answer_result) VALUES ('"
					+ formulaId + "','" + questionIds[i] + "','" + formularesult1[i] + "')");
			template.execute("INSERT INTO mys_mst_formula_map(formula_id, subquestion_id, answer_result) VALUES ('"
					+ formulaId + "','" + questionIds[i] + "','" + formularesult1[i] + "')");

		}
		return qBean;
	}

	public QuestionBean addDependQuestions(final QuestionBean qBean, final String questionId,
			QuestionareBean superquestion) {

		// final String[] superQuestion=qBean.getSuperQuestion().split(",");
		// final String[]
		// superQuestionAnswer=qBean.getSuperQuestionAnswer().split(",");

		for (int i = 0; i < superquestion.getQuestiondata().size(); i++) {

			System.out.println("INSERT INTO mys_mst_depend_questions(super_question_id, question_id, answer) VALUES ('"
					+ superquestion.getQuestiondata().get(i).getSuperQuestion() + "'," + questionId + ",'"
					+ superquestion.getQuestiondata().get(i).getSuperQuestionAnswer() + "')");
			template.execute("INSERT INTO mys_mst_depend_questions(super_question_id, question_id, answer) VALUES ('"
					+ superquestion.getQuestiondata().get(i).getSuperQuestion() + "'," + questionId + ",'"
					+ superquestion.getQuestiondata().get(i).getSuperQuestionAnswer() + "')");

		}
		return qBean;
	}

	public QuestionBean addAnswers1(QuestionBean qBean, String questionId, String subQuestionId,
			List<AnswerBean> answers) {

		if (subQuestionId == null) {
			subQuestionId = "NULL";
		}
		System.out.println("hello" + answers.size());
		System.out.println("data" + answers.get(0).getAnswer());
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(i).getAnswer() != null) {
				System.out.println(
						"INSERT INTO mys_mst_answer(question_id, subquestion_id, answer, answer_points, answer_code, answer_response, answer_optiontype, answer_comment) VALUES ('"
								+ questionId + "'," + subQuestionId + ",'" + answers.get(i).getAnswer() + "','"
								+ answers.get(i).getAnswerPoints() + "','" + answers.get(i).getAnswerCode() + "','"
								+ answers.get(i).getAnswerResponse() + "','" + answers.get(i).getAnswerOptionType()
								+ "','" + answers.get(i).getAnswerComment() + "')");
				template.execute(
						"INSERT INTO mys_mst_answer(question_id, subquestion_id, answer, answer_points, answer_code, answer_response, answer_optiontype, answer_comment) VALUES ('"
								+ questionId + "'," + subQuestionId + ",'" + answers.get(i).getAnswer() + "','"
								+ answers.get(i).getAnswerPoints() + "','" + answers.get(i).getAnswerCode() + "','"
								+ answers.get(i).getAnswerResponse() + "','" + answers.get(i).getAnswerOptionType()
								+ "','" + answers.get(i).getAnswerComment() + "')");
			}
		}
		return qBean;
	}

	public List<QuestionBean> getSubSectionBySectionId(String SectionId) {
		System.out.println("SELECT * FROM mys_mst_subsection where section_id='" + SectionId + "';");
		return template.query("SELECT * FROM mys_mst_subsection where section_id='" + SectionId + "';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						qBean.setSubSection(rs.getString("subsection_name"));
						qBean.setSubSectionId(rs.getString("sk_subsection_id"));
						qBean.setSectionId(rs.getString("section_id"));
						return qBean;
					}
				});
	}

	public List<QuestionBean> getStandardNumberBySubSectionId(String SubSectionId) {
		System.out.println("SELECT distinct(standard_number) FROM mys_mst_questionare WHERE subsection_id='" + SubSectionId
				+ "' and standard_number!='';");
		return template.query("SELECT distinct(standard_number) FROM mys_mst_questionare WHERE subsection_id='"
				+ SubSectionId + "' and standard_number!='';", new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						qBean.setStandardNumber(rs.getString("standard_number"));
						return qBean;
					}
				});
	}

	public QuestionBean getQuestionByStandNumber(String standardNumber) {
		System.out.println("SELECT * FROM mys_mst_questionare where standard_number='" + standardNumber + "';");
		return template.queryForObject("SELECT * FROM mys_mst_questionare where standard_number='" + standardNumber + "';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						qBean.setQuestionId(rs.getString("sk_question_id"));
						qBean.setQuestionType(rs.getString("answer_type"));
						return qBean;
					}
				});
	}

	public List<QuestionBean> getSubSections(QuestionBean qBean) {
		System.out.println("SELECT * FROM mys_mst_subsection,mys_mst_section where sk_section_id=section_id;");
		return template.query("SELECT * FROM mys_mst_subsection,mys_mst_section where sk_section_id=section_id;",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						qBean.setSubSection(rs.getString("subsection_name"));
						qBean.setSubSectionId(rs.getString("sk_subsection_id"));
						qBean.setSectionId(rs.getString("section_id"));
						qBean.setSection(rs.getString("section_name"));
						return qBean;
					}
				});
	}

	public QuestionBean getSubSectionById(String subSectionId) {
		System.out.println("SELECT * FROM mys_mst_subsection where sk_subsection_id='" + subSectionId + "';");
		return template.queryForObject("SELECT * FROM mys_mst_subsection where sk_subsection_id='" + subSectionId + "';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						qBean.setSubSection(rs.getString("subsection_name"));
						qBean.setSubSectionId(rs.getString("sk_subsection_id"));
						qBean.setSectionId(rs.getString("section_id"));
						return qBean;
					}
				});
	}

	public void addSection(QuestionBean qBean) {
		System.out.println("INSERT INTO mys_mst_section(section_name) VALUES ('" + qBean.getSection() + "');");
		template.execute("INSERT INTO mys_mst_section(section_name) VALUES ('" + qBean.getSection() + "');");

	}

	public void updateSection(QuestionBean qBean) {
		System.out.println("UPDATE mys_mst_section SET section_name='" + qBean.getSection() + "' WHERE sk_section_id='"
				+ qBean.getSectionId() + "';");
		template.execute("UPDATE mys_mst_section SET section_name='" + qBean.getSection() + "' WHERE sk_section_id='"
				+ qBean.getSectionId() + "';");

	}

	public QuestionBean getSectionById(String sectionId) {
		System.out.println("SELECT * FROM mys_mst_section where sk_section_id='" + sectionId + "';");
		return template.queryForObject("SELECT * FROM mys_mst_section where sk_section_id='" + sectionId + "'",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						qBean.setSection(rs.getString("section_name"));
						qBean.setSectionId(rs.getString("sk_section_id"));
						return qBean;
					}
				});
	}

	public void addSubSection(QuestionBean qBean) {
		System.out.println("INSERT INTO mys_mst_subsection(subsection_name, section_id) VALUES ('" + qBean.getSubSection()
				+ "','" + qBean.getSectionId() + "');");
		template.execute("INSERT INTO mys_mst_subsection(subsection_name, section_id) VALUES ('" + qBean.getSubSection()
				+ "','" + qBean.getSectionId() + "');");

	}

	public void deleteSubSection(QuestionBean qBean) {
		System.out.println("DELETE FROM mys_mst_subsection WHERE sk_subsection_id='" + qBean.getSubSectionId() + "';");
		template.execute("DELETE FROM mys_mst_subsection WHERE sk_subsection_id='" + qBean.getSubSectionId() + "';");

	}

	public void deleteSection(QuestionBean qBean) {
		System.out.println("DELETE FROM mys_mst_section WHERE sk_section_id='" + qBean.getSectionId() + "';");
		template.execute("DELETE FROM mys_mst_section WHERE sk_section_id='" + qBean.getSectionId() + "';");

	}

	public void updateSubSection(QuestionBean qBean) {
		System.out.println("UPDATE mys_mst_subsection SET section_id='" + qBean.getSectionId() + "',subsection_name='"
				+ qBean.getSubSection() + "' WHERE sk_subsection_id='" + qBean.getSubSectionId() + "';");
		template.execute("UPDATE mys_mst_subsection SET section_id='" + qBean.getSectionId() + "',subsection_name='"
				+ qBean.getSubSection() + "' WHERE sk_subsection_id='" + qBean.getSubSectionId() + "';");

	}

	/////////////////////////////////////////// MYSTRY VIVEK
	/////////////////////////////////////////// 12-11-2018/////////////////////////////////////////////////
}
