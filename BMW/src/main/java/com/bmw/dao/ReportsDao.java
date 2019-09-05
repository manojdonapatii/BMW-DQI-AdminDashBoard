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

public class ReportsDao {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	ResourceBundle resource = ResourceBundle.getBundle("resources/web");
	final String dashboardURL = resource.getString("dashboardURL");

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();

	public List<QuestionBean> getreportsByDid(QuestionBean qBean, String did, String type,String phase,String year,String brand) {
		System.out.println("SELECT GROUP_CONCAT(mst_final_score.score) as score,mst_outlet.outlet_name FROM mst_final_score,mst_outlet WHERE dealer_id='"
						+ did
						+ "' AND mst_final_score.outlet_id=mst_outlet.sk_outlet_id AND mst_final_score.outlet_type='"
						+ type + "' AND mst_final_score.year='"+year+"' AND mst_final_score.quarter='"+phase+"' AND mst_final_score.brand='"+brand+"' GROUP BY mst_final_score.outlet_id ORDER BY mst_outlet.outlet_name ASC");
		return template
				.query("SELECT GROUP_CONCAT(mst_final_score.score) as score,mst_outlet.outlet_name FROM mst_final_score,mst_outlet WHERE dealer_id='"
						+ did
						+ "' AND mst_final_score.outlet_id=mst_outlet.sk_outlet_id AND mst_final_score.outlet_type='"
						+ type + "' AND mst_final_score.year='"+year+"' AND mst_final_score.quarter='"+phase+"' AND mst_final_score.brand='"+brand+"' GROUP BY mst_final_score.outlet_id ORDER BY mst_outlet.outlet_name ASC", new RowMapper<QuestionBean>() {
							public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
								QuestionBean qBean = new QuestionBean();
								qBean.setOutlets(rs.getString("outlet_name"));
								String score[] = rs.getString("score").split(",");
								qBean.setContractual(score[0]);
								qBean.setEssential(score[1]);
								qBean.setScore(rs.getString("score"));
								return qBean;
							}
						});
	}

	public QuestionBean getOverallEssentialScoreByDealerId(final QuestionBean qBean, String did, String type,String phase,String year,String brand) {
		System.out.println("SELECT ROUND(AVG(score),2) as overall_essential_score FROM mst_final_score WHERE dealer_id='" + did
				+ "' AND score_type='Essential Score' AND mst_final_score.year='"+year+"' AND mst_final_score.quarter='"+phase+"' AND mst_final_score.brand='"+brand+"' AND outlet_type='" + type + "';");
		return template.queryForObject(
				"SELECT ROUND(AVG(score),2) as overall_essential_score FROM mst_final_score WHERE dealer_id='" + did
				+ "' AND score_type='Essential Score' AND mst_final_score.year='"+year+"' AND mst_final_score.quarter='"+phase+"' AND mst_final_score.brand='"+brand+"' AND outlet_type='" + type + "';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setTotal_ans_count(rs.getString("overall_essential_score"));
						return qBean;
					}
				});
	}

	public QuestionBean getOverallContractualScoreByDealerId(final QuestionBean qBean, String did, String type,String phase,String year,String brand) {
		System.out.println("SELECT ROUND(AVG(score),2) as overall_contractual_score FROM mst_final_score WHERE dealer_id='" + did
				+ "' AND score_type='Contractual Score' AND mst_final_score.year='"+year+"' AND mst_final_score.quarter='"+phase+"' AND mst_final_score.brand='"+brand+"' AND outlet_type='" + type + "';");
		return template.queryForObject(
				"SELECT ROUND(AVG(score),2 )as overall_contractual_score FROM mst_final_score WHERE dealer_id='" + did
						+ "' AND score_type='Contractual Score' AND mst_final_score.year='"+year+"' AND mst_final_score.quarter='"+phase+"' AND mst_final_score.brand='"+brand+"' AND outlet_type='" + type + "';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setTotal_qns_count(rs.getString("overall_contractual_score"));
						return qBean;
					}
				});
	}

	public List<QuestionBean> getDealerSalesSectionScore(QuestionBean qBean, String did, String year, String phase,String brand) {
		System.out.println(
				"SELECT *,AVG(total_score) as section_average,SUM(total_qns) ,SUM(total_qns_ans),SUM(total_zero_qns) FROM `mst_sections_score` WHERE dealer_id='"
						+ did + "' AND quarter='" + phase + "' AND year='" + year
						+ "' AND outlet_type='Sales' AND brand='"+brand+"' GROUP BY section_id;");
		return template
				.query("SELECT *,AVG(total_score) as section_average,SUM(total_qns) ,SUM(total_qns_ans),SUM(total_zero_qns) FROM `mst_sections_score` WHERE dealer_id='"
						+ did + "' AND quarter='" + phase + "' AND year='" + year
						+ "' AND outlet_type='Sales' AND brand='"+brand+"' GROUP BY section_id;", new RowMapper<QuestionBean>() {
							public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
								QuestionBean qBean = new QuestionBean();

								String region_id = rs.getString("region_id");
								String dealer_id = rs.getString("dealer_id");
								String type = rs.getString("outlet_type");
								String section_id = rs.getString("section_id");
								String section_name = rs.getString("section_name");
								String total_qns = rs.getString("SUM(total_qns)");
								String total_qns_ans = rs.getString("SUM(total_qns_ans)");
								String total_zero_ans = rs.getString("SUM(total_zero_qns)");
								String section_average = rs.getString("section_average");
								String year = rs.getString("year");
								String quarter = rs.getString("quarter");
								String brand = rs.getString("brand");
  
								System.out.println(
										"INSERT INTO mst_dealer_section_score(`region_id`, `dealer_id`, `type`, `section_id`, `section_name`, `total_qns`, `total_qns_ans`, `total_zero_qns`, `total_score`,year,quarter,brand) VALUES ('"
												+ region_id + "','" + dealer_id + "','" + type + "','" + section_id
												+ "','" + section_name + "','" + total_qns + "','" + total_qns_ans
												+ "','" + total_zero_ans + "','" + section_average + "','" + year
												+ "','" + quarter + "','"+brand+"')");
								template.execute(
										"INSERT INTO mst_dealer_section_score(`region_id`, `dealer_id`, `type`, `section_id`, `section_name`, `total_qns`, `total_qns_ans`, `total_zero_qns`, `total_score`,year,quarter,brand) VALUES ('"
												+ region_id + "','" + dealer_id + "','" + type + "','" + section_id
												+ "','" + section_name + "','" + total_qns + "','" + total_qns_ans
												+ "','" + total_zero_ans + "','" + section_average + "','" + year
												+ "','" + quarter + "','"+brand+"')");

								return qBean;
							}
						});
	}

	public List<QuestionBean> getDealerServiceSectionScore(QuestionBean qBean, String did, String year, String phase,String brand) {
		System.out.println(
				"SELECT *,AVG(total_score) as section_average,SUM(total_qns) ,SUM(total_qns_ans),SUM(total_zero_qns) FROM `mst_sections_score` WHERE dealer_id='"
						+ did + "' AND quarter='" + phase + "' AND year='" + year
						+ "' AND outlet_type='Service' AND brand='"+brand+"' GROUP BY section_id;");
		return template
				.query("SELECT *,AVG(total_score) as section_average,SUM(total_qns) ,SUM(total_qns_ans),SUM(total_zero_qns) FROM `mst_sections_score` WHERE dealer_id='"
						+ did + "' AND quarter='" + phase + "' AND year='" + year
						+ "' AND outlet_type='Service' AND brand='"+brand+"' GROUP BY section_id;", new RowMapper<QuestionBean>() {
							public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
								QuestionBean qBean = new QuestionBean();

								String region_id = rs.getString("region_id");
								String dealer_id = rs.getString("dealer_id");
								String type = rs.getString("outlet_type");
								String section_id = rs.getString("section_id");
								String section_name = rs.getString("section_name");
								String total_qns = rs.getString("SUM(total_qns)");
								String total_qns_ans = rs.getString("SUM(total_qns_ans)");
								String total_zero_ans = rs.getString("SUM(total_zero_qns)");
								String section_average = rs.getString("section_average");
								String year = rs.getString("year");
								String quarter = rs.getString("quarter");
								String brand = rs.getString("brand");

								System.out.println(
										"INSERT INTO mst_dealer_section_score(`region_id`, `dealer_id`, `type`, `section_id`, `section_name`, `total_qns`, `total_qns_ans`, `total_zero_qns`, `total_score`,year,quarter,brand VALUES ('"
												+ region_id + "','" + dealer_id + "','" + type + "','" + section_id
												+ "','" + section_name + "','" + total_qns + "','" + total_qns_ans
												+ "','" + total_zero_ans + "','" + section_average + "','" + year
												+ "','" + quarter + "','"+brand+"')");
								template.execute(
										"INSERT INTO mst_dealer_section_score(`region_id`, `dealer_id`, `type`, `section_id`, `section_name`, `total_qns`, `total_qns_ans`, `total_zero_qns`, `total_score`,year,quarter,brand) VALUES ('"
												+ region_id + "','" + dealer_id + "','" + type + "','" + section_id
												+ "','" + section_name + "','" + total_qns + "','" + total_qns_ans
												+ "','" + total_zero_ans + "','" + section_average + "','" + year
												+ "','" + quarter + "','"+brand+"')");

								return qBean;
							}
						});
	}

	public List<QuestionBean> getReportsOfDealers(QuestionBean qBean, String did, String year, String phase,String brand) {
		System.out.println("SELECT dealer_id,type,section_id,section_name,Round(total_score) as total_score  FROM `mst_dealer_section_score` WHERE dealer_id='" + did + "' AND quarter='"
				+ phase + "' AND year='" + year + "' AND brand='"+brand+"' AND type='Sales' order by Round(total_score) ASC;");
		return template.query(
				"SELECT dealer_id,type,section_id,section_name,Round(total_score) as total_score    FROM `mst_dealer_section_score` WHERE dealer_id='" + did + "' AND quarter='" + phase
						+ "' AND year='" + year + "' AND brand='"+brand+"' AND type='Sales' order by Round(total_score) ASC",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();

						qBean.setDealer_id(rs.getString("dealer_id"));
						qBean.setOutlet_type(rs.getString("type"));
						qBean.setSection_id(rs.getString("section_id"));
						qBean.setSection(rs.getString("section_name"));
						
						try{
						String temp [] = rs.getString("section_name").split("-");
						
						qBean.setSection(temp [1]);
						}catch (Exception e) {
							qBean.setSection(rs.getString("section_name"));
						}
						qBean.setScore(rs.getString("total_score"));

						return qBean;
					}
				});
	}

	public List<QuestionBean> getReportsOfDealersByService(QuestionBean qBean, String did, String year, String phase,String brand) {
		System.out.println("SELECT dealer_id,type,section_id,section_name,Round(total_score) as total_score    FROM `mst_dealer_section_score` WHERE dealer_id='" + did + "' AND quarter='" + phase
						+ "' AND year='" + year + "' AND brand='"+brand+"' AND type='Service' order by Round(total_score) ASC;");
		return template.query(
				"SELECT dealer_id,type,section_id,section_name,Round(total_score) as total_score    FROM `mst_dealer_section_score` WHERE dealer_id='" + did + "' AND quarter='" + phase
						+ "' AND year='" + year + "' AND brand='"+brand+"' AND type='Service' order by Round(total_score) ASC",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();

						qBean.setDealer_id(rs.getString("dealer_id"));
						qBean.setOutlet_type(rs.getString("type"));
						qBean.setSection_id(rs.getString("section_id"));
						qBean.setSection(rs.getString("section_name"));
						try{
							String temp [] = rs.getString("section_name").split("-");
							
							qBean.setSection(temp [1]);
							}catch (Exception e) {
								qBean.setSection(rs.getString("section_name"));
							}
						qBean.setScore(rs.getString("total_score"));

						return qBean;
					}
				});
	}

	public List<QuestionBean> getDealerSalesFinalScore(QuestionBean qBean, final String did, final String year,
			final String phase,final String brand) {
		System.out.println(
				"SELECT *,AVG(score) as average,SUM(total_no_qns) ,SUM(total_no_qns_ans),SUM(total_zero_qns) FROM `mst_final_score` WHERE dealer_id='"
						+ did + "' AND quarter='" + phase + "' AND year='" + year
						+ "' AND outlet_type='Sales' AND brand='"+brand+"' AND score_type='Contractual Score' GROUP BY dealer_id;");
		return template.query(
				"SELECT *,AVG(score) as average,SUM(total_no_qns) ,SUM(total_no_qns_ans),SUM(total_zero_qns) FROM `mst_final_score` WHERE dealer_id='"
						+ did + "' AND quarter='" + phase + "' AND year='" + year
						+ "' AND outlet_type='Sales' AND brand='"+brand+"' AND score_type='Contractual Score' GROUP BY dealer_id;",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();

						String dealer_id = rs.getString("dealer_id");
						String type = rs.getString("outlet_type");
						String total_qns = rs.getString("SUM(total_no_qns)");
						String total_qns_ans = rs.getString("SUM(total_no_qns_ans)");
						String total_zero_ans = rs.getString("SUM(total_zero_qns)");
						String average = rs.getString("average");
						String year = rs.getString("year");
						String quarter = rs.getString("quarter");
						String score_type = rs.getString("score_type");
						String brand = rs.getString("brand");

						System.out.println(
								"INSERT INTO mst_dealer_final_score( score_type,`dealer_id`, `outlet_type`, `total_no_qns`, `total_no_qns_ans`, `total_zero_qns`, `score`,year,quarter,brand) VALUES ('"
										+ score_type + "','" + dealer_id + "','" + type + "','" + total_qns + "','"
										+ total_qns_ans + "','" + total_zero_ans + "','" + average + "','" + year
										+ "','" + quarter + "','"+brand+"')");
						template.execute(
								"INSERT INTO mst_dealer_final_score( score_type,`dealer_id`, `outlet_type`, `total_no_qns`, `total_no_qns_ans`, `total_zero_qns`, `score`,year,quarter,brand) VALUES ('"
										+ score_type + "','" + dealer_id + "','" + type + "','" + total_qns + "','"
										+ total_qns_ans + "','" + total_zero_ans + "','" + average + "','" + year
										+ "','" + quarter + "','"+brand+"')");

						System.out.println(
								"SELECT *,AVG(score) as average,SUM(total_no_qns) ,SUM(total_no_qns_ans),SUM(total_zero_qns) FROM `mst_final_score` WHERE dealer_id='"
										+ did + "' AND quarter='" + phase + "' AND year='" + year
										+ "' AND outlet_type='Sales'  AND brand='"+brand+"' AND score_type='Essential Score' GROUP BY dealer_id;");
						template.query(
								"SELECT *,AVG(score) as average,SUM(total_no_qns) ,SUM(total_no_qns_ans),SUM(total_zero_qns) FROM `mst_final_score` WHERE dealer_id='"
										+ did + "' AND quarter='" + phase + "' AND year='" + year
										+ "' AND outlet_type='Sales' AND brand='"+brand+"' AND score_type='Essential Score' GROUP BY dealer_id;",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										QuestionBean qBean = new QuestionBean();

										String dealer_id = rs.getString("dealer_id");
										String type = rs.getString("outlet_type");
										String total_qns = rs.getString("SUM(total_no_qns)");
										String total_qns_ans = rs.getString("SUM(total_no_qns_ans)");
										String total_zero_ans = rs.getString("SUM(total_zero_qns)");
										String average = rs.getString("average");
										String year = rs.getString("year");
										String quarter = rs.getString("quarter");
										String score_type = rs.getString("score_type");
										String brand = rs.getString("brand");

										System.out.println(
												"INSERT INTO mst_dealer_final_score( score_type,`dealer_id`, `outlet_type`, `total_no_qns`, `total_no_qns_ans`, `total_zero_qns`, `score`,year,quarter,brand) VALUES ('"
														+ score_type + "','" + dealer_id + "','" + type + "','"
														+ total_qns + "','" + total_qns_ans + "','" + total_zero_ans
														+ "','" + average + "','" + year + "','" + quarter + "','"+brand+"')");
										template.execute(
												"INSERT INTO mst_dealer_final_score( score_type,`dealer_id`, `outlet_type`, `total_no_qns`, `total_no_qns_ans`, `total_zero_qns`, `score`,year,quarter,brand) VALUES ('"
														+ score_type + "','" + dealer_id + "','" + type + "','"
														+ total_qns + "','" + total_qns_ans + "','" + total_zero_ans
														+ "','" + average + "','" + year + "','" + quarter + "','"+brand+"')");

										return qBean;
									}
								});

						return qBean;
					}
				});
	}

	public List<QuestionBean> getDealerServiceFinalScore(QuestionBean qBean, final String did, final String year,
			final String phase,final String brand) {
		System.out.println(
				"SELECT *,AVG(score) as average,SUM(total_no_qns) ,SUM(total_no_qns_ans),SUM(total_zero_qns) FROM `mst_final_score` WHERE dealer_id='"
						+ did + "' AND quarter='" + phase + "' AND year='" + year
						+ "' AND outlet_type='Service' AND brand='"+brand+"' AND score_type='Contractual Score' GROUP BY dealer_id;");
		return template.query(
				"SELECT *,AVG(score) as average,SUM(total_no_qns) ,SUM(total_no_qns_ans),SUM(total_zero_qns) FROM `mst_final_score` WHERE dealer_id='"
						+ did + "' AND quarter='" + phase + "' AND year='" + year
						+ "' AND outlet_type='Service' AND brand='"+brand+"' AND score_type='Contractual Score' GROUP BY dealer_id;",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();

						String dealer_id = rs.getString("dealer_id");
						String type = rs.getString("outlet_type");
						String total_qns = rs.getString("SUM(total_no_qns)");
						String total_qns_ans = rs.getString("SUM(total_no_qns_ans)");
						String total_zero_ans = rs.getString("SUM(total_zero_qns)");
						String average = rs.getString("average");
						String year = rs.getString("year");
						String quarter = rs.getString("quarter");
						String score_type = rs.getString("score_type");
						String brand = rs.getString("brand");

						System.out.println(
								"INSERT INTO mst_dealer_final_score( score_type,`dealer_id`, `outlet_type`, `total_no_qns`, `total_no_qns_ans`, `total_zero_qns`, `score`,year,quarter,brand) VALUES ('"
										+ score_type + "','" + dealer_id + "','" + type + "','" + total_qns + "','"
										+ total_qns_ans + "','" + total_zero_ans + "','" + average + "','" + year
										+ "','" + quarter + "','"+brand+"')");
						template.execute(
								"INSERT INTO mst_dealer_final_score( score_type,`dealer_id`, `outlet_type`, `total_no_qns`, `total_no_qns_ans`, `total_zero_qns`, `score`,year,quarter,brand) VALUES ('"
										+ score_type + "','" + dealer_id + "','" + type + "','" + total_qns + "','"
										+ total_qns_ans + "','" + total_zero_ans + "','" + average + "','" + year
										+ "','" + quarter + "','"+brand+"')");

						System.out.println(
								"SELECT *,AVG(score) as average,SUM(total_no_qns) ,SUM(total_no_qns_ans),SUM(total_zero_qns) FROM `mst_final_score` WHERE dealer_id='"
										+ did + "' AND quarter='" + phase + "' AND year='" + year
										+ "' AND outlet_type='Service' AND brand='"+brand+"' AND score_type='Essential Score' GROUP BY dealer_id;");
						template.query(
								"SELECT *,AVG(score) as average,SUM(total_no_qns) ,SUM(total_no_qns_ans),SUM(total_zero_qns) FROM `mst_final_score` WHERE dealer_id='"
										+ did + "' AND quarter='" + phase + "' AND year='" + year
										+ "' AND outlet_type='Service' AND brand='"+brand+"' AND score_type='Essential Score' GROUP BY dealer_id;",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										QuestionBean qBean = new QuestionBean();

										String dealer_id = rs.getString("dealer_id");
										String type = rs.getString("outlet_type");
										String total_qns = rs.getString("SUM(total_no_qns)");
										String total_qns_ans = rs.getString("SUM(total_no_qns_ans)");
										String total_zero_ans = rs.getString("SUM(total_zero_qns)");
										String average = rs.getString("average");
										String year = rs.getString("year");
										String quarter = rs.getString("quarter");
										String score_type = rs.getString("score_type");
										String brand = rs.getString("brand");

										System.out.println(
												"INSERT INTO mst_dealer_final_score( score_type,`dealer_id`, `outlet_type`, `total_no_qns`, `total_no_qns_ans`, `total_zero_qns`, `score`,year,quarter,brand) VALUES ('"
														+ score_type + "','" + dealer_id + "','" + type + "','"
														+ total_qns + "','" + total_qns_ans + "','" + total_zero_ans
														+ "','" + average + "','" + year + "','" + quarter + "','"+brand+"')");
										template.execute(
												"INSERT INTO mst_dealer_final_score( score_type,`dealer_id`, `outlet_type`, `total_no_qns`, `total_no_qns_ans`, `total_zero_qns`, `score`,year,quarter,brand) VALUES ('"
														+ score_type + "','" + dealer_id + "','" + type + "','"
														+ total_qns + "','" + total_qns_ans + "','" + total_zero_ans
														+ "','" + average + "','" + year + "','" + quarter + "','"+brand+"')");

										return qBean;
									}
								});

						return qBean;
					}
				});
	}

	public List<QuestionBean> getContractualBySales(QuestionBean qBean, final String did, final String year,
			final String phase,final String brand) {
		System.out.println(
				"SELECT Round(AVG(score),1) as score from mst_dealer_final_score WHERE  outlet_type='Sales' AND brand='"+brand+"' AND score_type='Contractual Score' AND dealer_id='"
						+ did + "' AND year='" + year + "' AND quarter='" + phase + "' GROUP BY dealer_id");
		return template.query(
				"SELECT Round(AVG(score),1) as score from mst_dealer_final_score WHERE outlet_type='Sales' AND brand='"+brand+"' AND score_type='Contractual Score' AND dealer_id='"
						+ did + "' AND year='" + year + "' AND quarter='" + phase + "' GROUP BY dealer_id",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final QuestionBean qBean = new QuestionBean();
						qBean.setAvg(rs.getString("score"));

						System.out.println(
								"SELECT dealer_id,Round(AVG(score),1) as score1 FROM mst_dealer_final_score WHERE outlet_type='Sales' AND brand='"+brand+"' AND score_type='Contractual Score' AND year='"
										+ year + "' AND quarter='" + phase
										+ "' GROUP BY dealer_id ORDER BY AVG(score) ASC LIMIT 1");
						template.query(
								"SELECT dealer_id,Round(AVG(score),1) as score1 FROM mst_dealer_final_score WHERE outlet_type='Sales' AND brand='"+brand+"' AND score_type='Contractual Score' AND year='"
										+ year + "' AND quarter='" + phase
										+ "' GROUP BY dealer_id ORDER BY AVG(score) ASC LIMIT 1",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setMin(rs.getString("score1"));

										System.out.println(
												"SELECT dealer_id,Round(AVG(score),1) as score2 FROM mst_dealer_final_score WHERE outlet_type='Sales' AND brand='"+brand+"' AND score_type='Contractual Score' AND year='"
														+ year + "' AND quarter='" + phase
														+ "' GROUP BY dealer_id ORDER BY AVG(score) DESC LIMIT 1");
										template.query(
												"SELECT dealer_id,Round(AVG(score),1) as score2 FROM mst_dealer_final_score WHERE outlet_type='Sales' AND brand='"+brand+"' AND score_type='Contractual Score' AND year='"
														+ year + "' AND quarter='" + phase
														+ "' GROUP BY dealer_id ORDER BY AVG(score) DESC LIMIT 1",
												new RowMapper<QuestionBean>() {
													public QuestionBean mapRow(ResultSet rs, int row)
															throws SQLException {
														qBean.setMax(rs.getString("score2"));
														return qBean;
													}
												});
										return qBean;
									}
								});
						return qBean;
					}
				});
	}

	public List<QuestionBean> getContractualByServices(QuestionBean qBean, final String did, final String year,
			final String phase,final String brand) {
		System.out.println(
				"SELECT Round(AVG(score),1) as score from mst_dealer_final_score WHERE outlet_type='Service' AND brand='"+brand+"' AND score_type='Contractual Score' AND dealer_id='"
						+ did + "' AND year='" + year + "' AND quarter='" + phase + "' GROUP BY dealer_id");
		return template.query(
				"SELECT Round(AVG(score),1) as score from mst_dealer_final_score WHERE outlet_type='Service' AND brand='"+brand+"' AND score_type='Contractual Score' AND dealer_id='"
						+ did + "' AND year='" + year + "' AND quarter='" + phase + "' GROUP BY dealer_id",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final QuestionBean qBean = new QuestionBean();
						qBean.setAvg(rs.getString("score"));

						System.out.println(
								"SELECT dealer_id,Round(AVG(score),1) as score1 FROM mst_dealer_final_score WHERE outlet_type='Service' AND brand='"+brand+"' AND score_type='Contractual Score' AND year='"
										+ year + "' AND quarter='" + phase
										+ "' GROUP BY dealer_id ORDER BY AVG(score) ASC LIMIT 1");
						template.query(
								"SELECT dealer_id,Round(AVG(score),1) as score1 FROM mst_dealer_final_score WHERE outlet_type='Service' AND brand='"+brand+"' AND score_type='Contractual Score' AND year='"
										+ year + "' AND quarter='" + phase
										+ "' GROUP BY dealer_id ORDER BY AVG(score) ASC LIMIT 1",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {

										qBean.setMin(rs.getString("score1"));

										System.out.println(
												"SELECT dealer_id,Round(AVG(score),1) as score2 FROM mst_dealer_final_score WHERE outlet_type='Service' AND brand='"+brand+"' AND score_type='Contractual Score' AND year='"
														+ year + "' AND quarter='" + phase
														+ "' GROUP BY dealer_id ORDER BY AVG(score) DESC LIMIT 1");
										template.query(
												"SELECT dealer_id,Round(AVG(score),1) as score2 FROM mst_dealer_final_score WHERE outlet_type='Service' AND brand='"+brand+"' AND score_type='Contractual Score' AND year='"
														+ year + "' AND quarter='" + phase
														+ "' GROUP BY dealer_id ORDER BY AVG(score) DESC LIMIT 1",
												new RowMapper<QuestionBean>() {
													public QuestionBean mapRow(ResultSet rs, int row)
															throws SQLException {
														qBean.setMax(rs.getString("score2"));
														return qBean;
													}
												});
										return qBean;
									}
								});
						return qBean;
					}
				});
	}

	public QuestionBean getRankByDealerIdSales(final QuestionBean qBean, String did, String year, String phase,String brand) {
		 System.out.println("SELECT *, FIND_IN_SET( score, (SELECT GROUP_CONCAT( score ORDER BY score DESC) FROM mst_dealer_final_score WHERE   outlet_type='Sales' AND score_type='Contractual Score' AND brand='"+brand+"' AND year='"+year+"' AND quarter='"+phase+"'  ) ) AS rank FROM mst_dealer_final_score WHERE  dealer_id='"+did+"' AND outlet_type='Sales' AND score_type='Contractual Score' AND brand='"+brand+"' AND year='"+year+"' AND quarter='"+phase+"';");
         return template.queryForObject("SELECT *, FIND_IN_SET( score, (SELECT GROUP_CONCAT( score ORDER BY score DESC) FROM mst_dealer_final_score WHERE   outlet_type='Sales' AND score_type='Contractual Score' AND brand='"+brand+"' AND year='"+year+"' AND quarter='"+phase+"'  ) ) AS rank FROM mst_dealer_final_score WHERE  dealer_id='"+did+"' AND outlet_type='Sales' AND score_type='Contractual Score' AND brand='"+brand+"' AND year='"+year+"' AND quarter='"+phase+"';", new RowMapper<QuestionBean>() {
               public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
                qBean.setSales_rank(rs.getString("rank"));
         return qBean;
       }
     });
	}

	public QuestionBean getRankByDealerIdService(final QuestionBean qBean, String did, String year, String phase,String brand) {
		System.out.println("SELECT *, FIND_IN_SET( score, (SELECT GROUP_CONCAT( score ORDER BY score DESC) FROM mst_dealer_final_score WHERE   outlet_type='Service' AND score_type='Contractual Score' AND brand='"+brand+"' AND year='"+year+"' AND quarter='"+phase+"'  ) ) AS rank FROM mst_dealer_final_score WHERE  dealer_id='"+did+"' AND outlet_type='Service' AND score_type='Contractual Score' AND brand='"+brand+"' AND year='"+year+"' AND quarter='"+phase+"';");
        return template.queryForObject("SELECT *, FIND_IN_SET( score, (SELECT GROUP_CONCAT( score ORDER BY score DESC) FROM mst_dealer_final_score WHERE   outlet_type='Service' AND score_type='Contractual Score' AND brand='"+brand+"' AND year='"+year+"' AND quarter='"+phase+"'  ) ) AS rank FROM mst_dealer_final_score WHERE  dealer_id='"+did+"' AND outlet_type='Service' AND score_type='Contractual Score' AND brand='"+brand+"' AND year='"+year+"' AND quarter='"+phase+"';", new RowMapper<QuestionBean>() {
              public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
               qBean.setService_rank(rs.getString("rank"));
        return qBean;
      }
    });
	}

	public QuestionBean getDealerCountBySales(final QuestionBean qBean, String year, String phase,String brand) {
		System.out.println("SELECT COUNT(*) as count FROM mst_dealer_final_score WHERE  outlet_type='Sales' AND score_type='Contractual Score' AND brand='"+brand+"' AND year='"+year+"' AND quarter='"+phase+"' ORDER BY score DESC;");
        return template.queryForObject("SELECT COUNT(*) as count FROM mst_dealer_final_score WHERE  outlet_type='Sales' AND score_type='Contractual Score' AND brand='"+brand+"' AND year='"+year+"' AND quarter='"+phase+"' ORDER BY score DESC;", new RowMapper<QuestionBean>() {
              public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
               qBean.setDealer_sales_count(rs.getString("count"));
        return qBean;
      }
    });
	}

	public QuestionBean getDealerCountByService(final QuestionBean qBean, String year, String phase,String brand) {
		System.out.println("SELECT COUNT(*) as count FROM mst_dealer_final_score WHERE  outlet_type='Service' AND score_type='Contractual Score' AND brand='"+brand+"' AND year='"+year+"' AND quarter='"+phase+"' ORDER BY score DESC;");
        return template.queryForObject("SELECT COUNT(*) as count FROM mst_dealer_final_score WHERE  outlet_type='Service' AND score_type='Contractual Score' AND brand='"+brand+"' AND year='"+year+"' AND quarter='"+phase+"' ORDER BY score DESC;", new RowMapper<QuestionBean>() {
              public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
               qBean.setDealer_service_count(rs.getString("count"));
        return qBean;
      }
    });
	}

	public QuestionBean checkMIniSalesExist(String did,final QuestionBean qBean) {
		System.out.println("SELECT * FROM mst_outlet WHERE dealership_id='"+did+"' AND brand='MINI' and outlet_type='Sales' LIMIT 1;");
        return template.queryForObject("SELECT * FROM mst_outlet WHERE dealership_id='"+did+"' AND brand='MINI' and outlet_type='Sales' LIMIT 1;", new RowMapper<QuestionBean>() {
              public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
              
        return qBean;
      }
    });
	}

	

}
