package com.bmw.dao;


import java.lang.reflect.InvocationTargetException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bmw.api.AESCrypt;
import com.bmw.api.SendEmailUsingGMailSMTP;
import com.bmw.beans.AnswerBean;
import com.bmw.beans.MenuBean;
import com.bmw.beans.QuestionBean;
import com.bmw.beans.QuestionareBean;
import com.bmw.beans.UserBean;

public class HomeDao {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	ResourceBundle resource = ResourceBundle.getBundle("resources/web");
	final String dashboardURL = resource.getString("dashboardURL");

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();

	public void addUserType(UserBean uBean) {
		System.out.println("INSERT INTO mst_user_type(user_type) VALUES ('" + uBean.getUser_type() + "');");
		template.execute("INSERT INTO mst_user_type(user_type) VALUES ('" + uBean.getUser_type() + "');");

	}

	public List<UserBean> getUserTypes(UserBean uBean) {
		System.out.println("SELECT * FROM mst_user_type;");
		return template.query("SELECT * FROM mst_user_type;", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean uBean = new UserBean();
				uBean.setUser_type(rs.getString("user_type"));
				uBean.setUser_type_id(rs.getString("sk_user_id"));
				return uBean;
			}
		});
	}

	public void addMenu(MenuBean mBean) {
		if (mBean.getMenu_type().equals("1")) {
			System.out.println(
					"INSERT INTO mst_settings_menu(menu_level,menu_id,menu_name,menu_link,menu_status,user_id) VALUES ('"
							+ mBean.getMenu_type() + "','0','" + mBean.getMenu_name() + "','" + mBean.getMenu_link()
							+ "','active','1')");
			template.execute(
					"INSERT INTO mst_settings_menu(menu_level,menu_id,menu_name,menu_link,menu_status,user_id) VALUES ('"
							+ mBean.getMenu_type() + "','0','" + mBean.getMenu_name() + "','" + mBean.getMenu_link()
							+ "','active','1');");
		} else if (mBean.getMenu_type().equals("2")) {
			System.out.println(
					"INSERT INTO mst_settings_menu(menu_level,menu_id,menu_name,menu_link,menu_status,user_id) VALUES ('"
							+ mBean.getMenu_type() + "','" + mBean.getMenu_id() + "','" + mBean.getSub_menu_name()
							+ "','" + mBean.getMenu_link() + "','active','1')");
			template.execute(
					"INSERT INTO mst_settings_menu(menu_level,menu_id,menu_name,menu_link,menu_status,user_id) VALUES ('"
							+ mBean.getMenu_type() + "','" + mBean.getMenu_id() + "','" + mBean.getSub_menu_name()
							+ "','" + mBean.getMenu_link() + "','active','1');");
		} else {
			System.out.println(
					"INSERT INTO mst_settings_menu(menu_level,menu_id,menu_name,menu_link,menu_status,user_id) VALUES ('"
							+ mBean.getMenu_type() + "','" + mBean.getSub_menu_id() + "','" + mBean.getSub_menu_name()
							+ "','" + mBean.getMenu_link() + "','active','1')");
			template.execute(
					"INSERT INTO mst_settings_menu(menu_level,menu_id,menu_name,menu_link,menu_status,user_id) VALUES ('"
							+ mBean.getMenu_type() + "','" + mBean.getSub_menu_id() + "','" + mBean.getSub_menu_name()
							+ "','" + mBean.getMenu_link() + "','active','1');");
		}

	}

	public List<MenuBean> getMainMenu(MenuBean mBean, String menu_list) {
		System.out
				.println("SELECT * FROM mst_settings_menu WHERE sk_menu_id IN (" + menu_list + ") AND menu_level='1';");
		return template.query(
				"SELECT * FROM mst_settings_menu WHERE sk_menu_id IN (" + menu_list + ") AND menu_level='1';",
				new RowMapper<MenuBean>() {
					public MenuBean mapRow(ResultSet rs, int row) throws SQLException {
						MenuBean mBean = new MenuBean();
						mBean.setMenu_name(rs.getString("menu_name"));
						mBean.setMenu_id(rs.getString("sk_menu_id"));
						mBean.setMenu_link(rs.getString("menu_link"));
						mBean.setMenu_icon(rs.getString("menu_icon"));
						return mBean;
					}
				});
	}

	public List<MenuBean> getMainMenu(MenuBean mBean) {
		System.out.println("SELECT * FROM mst_settings_menu WHERE menu_id IN (0);");
		return template.query("SELECT * FROM mst_settings_menu WHERE menu_id IN (0);", new RowMapper<MenuBean>() {
			public MenuBean mapRow(ResultSet rs, int row) throws SQLException {
				MenuBean mBean = new MenuBean();
				mBean.setMenu_name(rs.getString("menu_name"));
				mBean.setMenu_id(rs.getString("sk_menu_id"));
				mBean.setMenu_link(rs.getString("menu_link"));
				mBean.setMenu_icon(rs.getString("menu_icon"));
				return mBean;
			}
		});
	}

	public List<MenuBean> getMenu(MenuBean mBean) {
		System.out.println("SELECT * FROM mst_settings_menu;");
		return template.query("SELECT * FROM mst_settings_menu;", new RowMapper<MenuBean>() {
			public MenuBean mapRow(ResultSet rs, int row) throws SQLException {
				MenuBean mBean = new MenuBean();
				mBean.setMenu_name(rs.getString("menu_name"));
				mBean.setMenu_id(rs.getString("sk_menu_id"));
				return mBean;
			}
		});
	}

	public List<MenuBean> getSubMenuById(MenuBean mBean, String menuid, String menu_list) {

		String sql = "";
		if (menu_list == "") {
			sql = "SELECT * FROM mst_settings_menu WHERE menu_id IN(" + menuid + ");";
		} else {

			sql = "SELECT * FROM mst_settings_menu WHERE menu_id=" + menuid + " and sk_menu_id IN(" + menu_list + ");";
		}
		System.out.println(sql);
		return template.query(sql, new RowMapper<MenuBean>() {
			public MenuBean mapRow(ResultSet rs, int row) throws SQLException {
				MenuBean mBean = new MenuBean();
				mBean.setMenu_name(rs.getString("menu_name"));
				mBean.setMenu_id(rs.getString("sk_menu_id"));
				mBean.setMenu_link(rs.getString("menu_link"));
				return mBean;
			}
		});
	}

	public List<UserBean> getUsers(UserBean uBean) {
		System.out.println("SELECT * FROM mst_users;");
		return template.query("SELECT * FROM mst_users;", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				final UserBean uBean = new UserBean();
				uBean.setUser_type_id(rs.getString("user_type"));
				uBean.setOutlets(rs.getString("outlets"));
				uBean.setFirst_name(rs.getString("first_name"));
				uBean.setLast_name(rs.getString("last_name"));
				uBean.setEmail(rs.getString("email"));
				uBean.setMobile(rs.getString("mobile"));
				uBean.setPassword(rs.getString("password"));
				uBean.setRole(rs.getString("role"));
				uBean.setUser_id(rs.getString("sk_user_id"));
				template.query("SELECT * FROM mst_user_type WHERE sk_user_id='" + rs.getString("user_type") + "';",
						new RowMapper<UserBean>() {
							public UserBean mapRow(ResultSet rs, int row) throws SQLException {
								uBean.setUser_type(rs.getString("user_type"));
								uBean.setUser_type_id(rs.getString("sk_user_id"));
								return uBean;
							}
						});
				template.query("SELECT * FROM mst_roles WHERE sk_role_id='" + rs.getString("role") + "';",
						new RowMapper<UserBean>() {
							public UserBean mapRow(ResultSet rs, int row) throws SQLException {
								uBean.setRole(rs.getString("role_name"));
								uBean.setRole_id(rs.getString("sk_role_id"));
								return uBean;
							}
						});
				return uBean;

			}
		});

	}

	public UserBean getAllOutlets(final UserBean uBean) {
		System.out.println("SELECT GROUP_CONCAT(sk_outlet_id) as outlet_id FROM mst_outlet;");
		return template.queryForObject("SELECT GROUP_CONCAT(sk_outlet_id) as outlet_id FROM mst_outlet;",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						uBean.setOutlet_id(rs.getString("outlet_id"));
						return uBean;
					}
				});
	}

	public void addUserToAllOutlets(UserBean uBean, String outlet_id) {
		System.out.println(
				"INSERT INTO `mst_users`(`user_type`, `outlets`, `first_name`, `last_name`, `email`, `mobile`, `password`, `role`, `user_status`) VALUES('"
						+ uBean.getUser_type_id() + "'," + "'" + outlet_id + "','" + uBean.getFirst_name() + "','"
						+ uBean.getLast_name() + "','" + uBean.getEmail() + "','" + uBean.getMobile() + "','"
						+ uBean.getPassword() + "','" + uBean.getRole_id() + "','active')");
		template.execute(
				"INSERT INTO `mst_users`(`user_type`, `outlets`, `first_name`, `last_name`, `email`, `mobile`, `password`, `role`, `user_status`) VALUES('"
						+ uBean.getUser_type_id() + "'," + "'" + outlet_id + "','" + uBean.getFirst_name() + "','"
						+ uBean.getLast_name() + "','" + uBean.getEmail() + "','" + uBean.getMobile() + "','"
						+ uBean.getPassword() + "','" + uBean.getRole_id() + "','active')");

		/*
		 * SendEmailUsingGMailSMTP mail = new SendEmailUsingGMailSMTP();
		 * mail.sendOnBoardMail(uBean.getEmail(),uBean.getFirst_name(),uBean.
		 * getEmail(),uBean.getPassword());
		 */
	}

	public void addUsers(UserBean uBean) {
		System.out.println(
				"INSERT INTO `mst_users`(`user_type`, `outlets`, `first_name`, `last_name`, `email`, `mobile`, `password`, `role`, `user_status`) VALUES('"
						+ uBean.getUser_type_id() + "'," + "'" + uBean.getOutlets() + "','" + uBean.getFirst_name()
						+ "','" + uBean.getLast_name() + "','" + uBean.getEmail() + "','" + uBean.getMobile() + "',MD5('"+ uBean.getPassword() + "'),'" + uBean.getRole_id() + "','active')");
		template.execute(
				"INSERT INTO `mst_users`(`user_type`, `outlets`, `first_name`, `last_name`, `email`, `mobile`, `password`, `role`, `user_status`) VALUES('"
						+ uBean.getUser_type_id() + "'," + "'" + uBean.getOutlets() + "','" + uBean.getFirst_name()
						+ "','" + uBean.getLast_name() + "','" + uBean.getEmail() + "','" + uBean.getMobile() + "',MD5('"+ uBean.getPassword() + "'),'" + uBean.getRole_id() + "','active')");

		/*
		 * SendEmailUsingGMailSMTP mail = new SendEmailUsingGMailSMTP();
		 * mail.sendOnBoardMail(uBean.getEmail(),uBean.getFirst_name(),uBean.
		 * getEmail(),uBean.getPassword());
		 */
	}

	public void assignRole(MenuBean mBean) {
		System.out.println("INSERT INTO mst_roles(role_name,role_for,menu_list,role_status) VALUES ('" + mBean.getRole()
				+ "','" + mBean.getRole_for() + "','" + mBean.getMenu_id() + "','active')");
		template.execute("INSERT INTO mst_roles(role_name,role_for,menu_list,role_status) VALUES ('" + mBean.getRole()
				+ "','" + mBean.getRole_for() + "','" + mBean.getMenu_id() + "','active')");
	}

	public List<UserBean> getRoles(UserBean uBean) {
		System.out.println("SELECT * FROM mst_roles;");
		return template.query("SELECT * FROM mst_roles;", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean uBean = new UserBean();
				uBean.setRole(rs.getString("role_name"));
				uBean.setRole_id(rs.getString("sk_role_id"));
				return uBean;
			}
		});
	}

	/*
	 * public void addDealership(UserBean uBean) { System.out.
	 * println("INSERT INTO mst_dealership(dealership_name,contact_person,email,mobile,address) VALUES ('"
	 * +uBean.getDealership_name()+"','"+uBean.getContact_person()+"','"+uBean.
	 * getEmail()+"','"+uBean.getMobile()+"','"+uBean.getAddress()+"')"); template.
	 * execute("INSERT INTO mst_dealership(dealership_name,contact_person,email,mobile,address) VALUES ('"
	 * +uBean.getDealership_name()+"','"+uBean.getContact_person()+"','"+uBean.
	 * getEmail()+"','"+uBean.getMobile()+"','"+uBean.getAddress()+"')");
	 * 
	 * }
	 */
	public void addDealership(UserBean uBean, String file) {
		System.out.println(
				"INSERT INTO mst_dealership(dealership_name,dealer_logo,contact_person,email,mobile,address,lat,lang) VALUES ('"
						+ uBean.getDealership_name() + "','" + file + "','" + uBean.getContact_person() + "','"
						+ uBean.getEmail() + "','" + uBean.getMobile() + "','" + uBean.getAddress() + "','"
						+ uBean.getLat() + "','" + uBean.getLang() + "')");
		template.execute(
				"INSERT INTO mst_dealership(dealership_name,dealer_logo,contact_person,email,mobile,address,lat,lang) VALUES ('"
						+ uBean.getDealership_name() + "','" + file + "','" + uBean.getContact_person() + "','"
						+ uBean.getEmail() + "','" + uBean.getMobile() + "','" + uBean.getAddress() + "','"
						+ uBean.getLat() + "','" + uBean.getLang() + "')");

	}

	public List<UserBean> getDealerList(UserBean uBean) {
		System.out.println("SELECT * FROM mst_dealership;");
		return template.query("SELECT * FROM mst_dealership;", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean uBean = new UserBean();
				uBean.setDealership_id(rs.getString("sk_dealership_id"));
				uBean.setDealership_name(rs.getString("dealership_name"));
				uBean.setContact_person(rs.getString("contact_person"));
				uBean.setEmail(rs.getString("email"));
				uBean.setMobile(rs.getString("mobile"));
				uBean.setAddress(rs.getString("address"));
				return uBean;
			}
		});
	}

	public List<UserBean> getRegionList(UserBean uBean) {
		System.out.println("SELECT * FROM mst_region;");
		return template.query("SELECT * FROM mst_region;", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean uBean = new UserBean();
				uBean.setRegion(rs.getString("region_name"));
				uBean.setRegion_id(rs.getString("sk_region_id"));
				return uBean;
			}
		});
	}

	public UserBean getDealerDeatailsById(final UserBean uBean, String did) {
		System.out.println("SELECT * FROM mst_dealership WHERE sk_dealership_id='" + did + "';");
		return template.queryForObject("SELECT * FROM mst_dealership WHERE sk_dealership_id='" + did + "';",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						uBean.setDealership_id(rs.getString("sk_dealership_id"));
						uBean.setDealership_name(rs.getString("dealership_name"));
						uBean.setContact_person(rs.getString("contact_person"));
						uBean.setEmail(rs.getString("email"));
						uBean.setMobile(rs.getString("mobile"));
						uBean.setAddress(rs.getString("address"));
						uBean.setDealer_logo(rs.getString("dealer_logo"));
						uBean.setLat(rs.getString("lat"));
						uBean.setLang(rs.getString("lang"));
						return uBean;
					}
				});
	}

	public void updateDealershipById(UserBean uBean, String did, String file) {
		System.out.println("UPDATE mst_dealership SET dealership_name='" + uBean.getDealership_name()
				+ "',dealer_logo='" + file + "',contact_person='" + uBean.getContact_person() + "',email='"
				+ uBean.getEmail() + "',mobile='" + uBean.getMobile() + "',address='" + uBean.getAddress() + "',lat='"
				+ uBean.getLat() + "',lang='" + uBean.getLang() + "' WHERE sk_dealership_id='" + did + "';");
		template.execute("UPDATE mst_dealership SET dealership_name='" + uBean.getDealership_name() + "',dealer_logo='"
				+ file + "',contact_person='" + uBean.getContact_person() + "',email='" + uBean.getEmail()
				+ "',mobile='" + uBean.getMobile() + "',address='" + uBean.getAddress() + "',lat='" + uBean.getLat()
				+ "',lang='" + uBean.getLang() + "' WHERE sk_dealership_id='" + did + "';");

	}

	public void getDeleteDealershipById(String did) {
		System.out.println("DELETE FROM mst_dealership WHERE sk_dealership_id='" + did + "';");
		template.execute("DELETE FROM mst_dealership WHERE sk_dealership_id='" + did + "';");
	}

	public UserBean getUserDetailsById(final UserBean uBean, String uid) {
		System.out.println("SELECT * FROM mst_users WHERE sk_user_id='" + uid + "';");
		return template.queryForObject("SELECT * FROM mst_users WHERE sk_user_id='" + uid + "';",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						uBean.setUser_id(rs.getString("sk_user_id"));
						uBean.setUser_type_id(rs.getString("user_type"));
						uBean.setOutlets(rs.getString("outlets"));
						uBean.setFirst_name(rs.getString("first_name"));
						uBean.setLast_name(rs.getString("last_name"));
						uBean.setEmail(rs.getString("email"));
						uBean.setMobile(rs.getString("mobile"));
						uBean.setPassword(rs.getString("password"));
						uBean.setRole_id(rs.getString("role"));
						template.queryForObject(
								"SELECT * FROM mst_user_type WHERE sk_user_id='" + rs.getString("user_type") + "';",
								new RowMapper<UserBean>() {
									public UserBean mapRow(ResultSet rs, int row) throws SQLException {
										uBean.setUser_type(rs.getString("user_type"));
										uBean.setUser_type_id(rs.getString("sk_user_id"));
										return uBean;
									}
								});
						template.queryForObject(
								"SELECT * FROM mst_roles WHERE sk_role_id='" + rs.getString("role") + "';",
								new RowMapper<UserBean>() {
									public UserBean mapRow(ResultSet rs, int row) throws SQLException {
										uBean.setRole(rs.getString("role_name"));
										uBean.setRole_id(rs.getString("sk_role_id"));
										return uBean;
									}
								});
						return uBean;

					}
				});

	}

	public void updateUserById(UserBean uBean, String uid) {
		System.out.println("UPDATE mst_users SET user_type='" + uBean.getUser_type_id() + "',outlets='"
				+ uBean.getOutlets() + "',first_name='" + uBean.getFirst_name() + "',last_name='" + uBean.getLast_name()
				+ "',email='" + uBean.getEmail() + "',mobile='" + uBean.getMobile() + "',password='"
				+ uBean.getPassword() + "',role='" + uBean.getRole_id() + "' WHERE sk_user_id='" + uid + "'");
		template.execute("UPDATE mst_users SET user_type='" + uBean.getUser_type_id() + "',outlets='"
				+ uBean.getOutlets() + "',first_name='" + uBean.getFirst_name() + "',last_name='" + uBean.getLast_name()
				+ "',email='" + uBean.getEmail() + "',mobile='" + uBean.getMobile() + "',password=MD5('"+ uBean.getPassword() + "'),role='" + uBean.getRole_id() + "' WHERE sk_user_id='" + uid + "'");
	}

	public void updateUserByIdToAllOutlets(UserBean uBean, String uid, String outlets) {
		System.out.println("UPDATE mst_users SET user_type='" + uBean.getUser_type_id() + "',outlets='" + outlets
				+ "',first_name='" + uBean.getFirst_name() + "',last_name='" + uBean.getLast_name() + "',email='"
				+ uBean.getEmail() + "',mobile='" + uBean.getMobile() + "',password='" + uBean.getPassword()
				+ "',role='" + uBean.getRole_id() + "' WHERE sk_user_id='" + uid + "'");
		template.execute("UPDATE mst_users SET user_type='" + uBean.getUser_type_id() + "',outlets='" + outlets
				+ "',first_name='" + uBean.getFirst_name() + "',last_name='" + uBean.getLast_name() + "',email='"
				+ uBean.getEmail() + "',mobile='" + uBean.getMobile() + "',password=MD5('"+ uBean.getPassword() + "'),role='" + uBean.getRole_id() + "' WHERE sk_user_id='" + uid + "'");
	}

	public void deleteUserById(UserBean uBean, String uid) {
		System.out.println("DELETE FROM mst_users WHERE sk_user_id='" + uid + "'");
		template.execute("DELETE FROM mst_users WHERE sk_user_id='" + uid + "'");
	}

	public void addOutlet(UserBean uBean) {
		System.out.println(
				"INSERT INTO mst_outlet (outlet_name,dealership_id,state,city,contact_person,email,mobile,password,address,status,outlet_size,outlet_type,brand,region,lat,lang,outlet_id) VALUES ('"
						+ uBean.getOutlets() + "','" + uBean.getDealership_id() + "','" + uBean.getState() + "','"
						+ uBean.getCity() + "','" + uBean.getContact_person() + "','" + uBean.getEmail() + "','"
						+ uBean.getMobile() + "','" + uBean.getPassword() + "','" + uBean.getAddress() + "','active','"
						+ uBean.getOutlet_size() + "','" + uBean.getOutlet_type() + "','" + uBean.getBrand() + "','"
						+ uBean.getRegion() + "','" + uBean.getLat() + "','" + uBean.getLang() + "','" + uBean.getOid()
						+ "')");
		template.execute(
				"INSERT INTO mst_outlet (outlet_name,dealership_id,state,city,contact_person,email,mobile,password,address,status,outlet_size,outlet_type,brand,region,lat,lang,outlet_id) VALUES ('"
						+ uBean.getOutlets() + "','" + uBean.getDealership_id() + "','" + uBean.getState() + "','"
						+ uBean.getCity() + "','" + uBean.getContact_person() + "','" + uBean.getEmail() + "','"
						+ uBean.getMobile() + "','" + uBean.getPassword() + "','" + uBean.getAddress() + "','active','"
						+ uBean.getOutlet_size() + "','" + uBean.getOutlet_type() + "','" + uBean.getBrand() + "','"
						+ uBean.getRegion() + "','" + uBean.getLat() + "','" + uBean.getLang() + "','" + uBean.getOid()
						+ "')");
	}

	public List<UserBean> getOutletList(UserBean uBean) {
		System.out.println(
				"SELECT mst_outlet.*,mst_dealership.dealership_name FROM mst_outlet,mst_dealership WHERE mst_outlet.dealership_id=mst_dealership.sk_dealership_id;");
		return template.query(
				"SELECT mst_outlet.*,mst_dealership.dealership_name FROM mst_outlet,mst_dealership WHERE mst_outlet.dealership_id=mst_dealership.sk_dealership_id;",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						UserBean uBean = new UserBean();
						uBean.setDealership_id(rs.getString("dealership_id"));
						uBean.setContact_person(rs.getString("contact_person"));
						uBean.setEmail(rs.getString("email"));
						uBean.setMobile(rs.getString("mobile"));
						uBean.setAddress(rs.getString("address"));
						uBean.setState(rs.getString("state"));
						uBean.setCity(rs.getString("city"));
						uBean.setOutlets(rs.getString("outlet_name"));
						uBean.setOutlet_id(rs.getString("sk_outlet_id"));
						uBean.setDealership_name(rs.getString("dealership_name"));
						uBean.setOutlet_size(rs.getString("outlet_size"));
						uBean.setOutlet_type(rs.getString("outlet_type"));
						uBean.setBrand(rs.getString("brand"));
						return uBean;
					}
				});
	}

	public UserBean getOutletById(final UserBean uBean, String oid) {
		System.out.println(
				"SELECT mst_outlet.*,mst_dealership.dealership_name,mst_dealership.contact_person as cp FROM mst_outlet,mst_dealership WHERE mst_outlet.dealership_id=mst_dealership.sk_dealership_id AND mst_outlet.sk_outlet_id='"
						+ oid + "';");
		return template.queryForObject(
				"SELECT mst_outlet.*,mst_dealership.dealership_name,mst_dealership.contact_person as cp FROM mst_outlet,mst_dealership WHERE mst_outlet.dealership_id=mst_dealership.sk_dealership_id AND mst_outlet.sk_outlet_id='"
						+ oid + "';",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						uBean.setDealership_id(rs.getString("dealership_id"));
						uBean.setContact_person(rs.getString("contact_person"));
						uBean.setEmail(rs.getString("email"));
						uBean.setMobile(rs.getString("mobile"));
						uBean.setAddress(rs.getString("address"));
						uBean.setState_id(rs.getString("state"));
						uBean.setCity_id(rs.getString("city"));
						uBean.setOutlets(rs.getString("outlet_name"));
						uBean.setOutlet_id(rs.getString("sk_outlet_id"));
						uBean.setPassword(rs.getString("password"));
						uBean.setDealership_name(rs.getString("dealership_name"));
						uBean.setOutlet_size(rs.getString("outlet_size"));
						uBean.setOutlet_type(rs.getString("outlet_type"));
						uBean.setRegion_id(rs.getString("region"));
						uBean.setBrand(rs.getString("brand"));
						uBean.setFile_path(rs.getString("cp"));
						uBean.setLat(rs.getString("lat"));
						uBean.setLang(rs.getString("lang"));
						uBean.setOid(rs.getString("outlet_id"));
						return uBean;
					}
				});
	}

	public void updateOutletById(UserBean uBean, String oid) {
		System.out.println("UPDATE mst_outlet SET outlet_name='" + uBean.getOutlets() + "',dealership_id='"
				+ uBean.getDealership_id() + "',state='" + uBean.getState() + "',city='" + uBean.getCity()
				+ "',contact_person='" + uBean.getContact_person() + "',email='" + uBean.getEmail() + "',mobile='"
				+ uBean.getMobile() + "',password='" + uBean.getPassword() + "',address='" + uBean.getAddress()
				+ "',status='active',outlet_size='" + uBean.getOutlet_size() + "',outlet_type='"
				+ uBean.getOutlet_type() + "',brand='" + uBean.getBrand() + "',region='" + uBean.getRegion() + "',lat='"
				+ uBean.getLat() + "',lang='" + uBean.getLang() + "',outlet_id='" + uBean.getOid()
				+ "' WHERE sk_outlet_id='" + oid + "';");
		template.execute("UPDATE mst_outlet SET outlet_name='" + uBean.getOutlets() + "',dealership_id='"
				+ uBean.getDealership_id() + "',state='" + uBean.getState() + "',city='" + uBean.getCity()
				+ "',contact_person='" + uBean.getContact_person() + "',email='" + uBean.getEmail() + "',mobile='"
				+ uBean.getMobile() + "',password='" + uBean.getPassword() + "',address='" + uBean.getAddress()
				+ "',status='active',outlet_size='" + uBean.getOutlet_size() + "',outlet_type='"
				+ uBean.getOutlet_type() + "',brand='" + uBean.getBrand() + "',region='" + uBean.getRegion() + "',lat='"
				+ uBean.getLat() + "',lang='" + uBean.getLang() + "',outlet_id='" + uBean.getOid()
				+ "' WHERE sk_outlet_id='" + oid + "';");

	}

	public void deleteOutletById(UserBean uBean, String oid) {
		System.out.println("DELETE FROM mst_outlet WHERE sk_outlet_id='" + oid + "';");
		template.execute("DELETE FROM mst_outlet WHERE sk_outlet_id='" + oid + "';");
	}

	public void addSection(QuestionBean qBean) {
		System.out.println("INSERT INTO mst_section (section_name,type,brand,status) VALUES ('" + qBean.getSection()
				+ "','" + qBean.getType() + "','" + qBean.getBrand() + "','active');");
		template.execute("INSERT INTO mst_section (section_name,type,brand,status) VALUES ('" + qBean.getSection()
				+ "','" + qBean.getType() + "','" + qBean.getBrand() + "','active');");

	}

	public List<QuestionBean> getSectionList(QuestionBean qBean) {
		System.out.println("SELECT * FROM mst_section;");
		return template.query("SELECT * FROM mst_section;", new RowMapper<QuestionBean>() {
			public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
				QuestionBean qBean = new QuestionBean();
				qBean.setSection(rs.getString("section_name"));
				qBean.setSection_id(rs.getString("sk_section_id"));
				return qBean;
			}
		});
	}

	public void addQuestionnaire(QuestionBean qBean, String file) {
		System.out.println(
				"INSERT INTO mst_questionnaire(brand,type,section_id,standard,requirement,comment,number,xs,s,m,l,xl,xxl,type_of_check,essential,question,observation,suggested_person,file,status,image_mandatory) VALUES "
						+ "('" + qBean.getBrand() + "','" + qBean.getType() + "','" + qBean.getSection_id() + "','"
						+ qBean.getStandard().replace("'", "\\'") + "','" + qBean.getRequirement().replace("'", "\\'")
						+ "','" + qBean.getComment().replace("'", "\\'") + "','" + qBean.getNumber() + "','"
						+ qBean.getXs() + "','" + qBean.getS() + "','" + qBean.getM() + "','" + qBean.getL() + "','"
						+ qBean.getXl() + "','" + qBean.getXxl() + "','" + qBean.getCheck() + "','"
						+ qBean.getEssential() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"
						+ qBean.getObservation().replace("'", "\\'") + "','" + qBean.getPerson() + "','" + file
						+ "','active','"+ qBean.getImage_manadatory() + "')");
		template.execute(
				"INSERT INTO mst_questionnaire(brand,type,section_id,standard,requirement,comment,number,xs,s,m,l,xl,xxl,type_of_check,essential,question,observation,suggested_person,file,status,image_mandatory) VALUES "
						+ "('" + qBean.getBrand() + "','" + qBean.getType() + "','" + qBean.getSection_id() + "','"
						+ qBean.getStandard().replace("'", "\\'") + "','" + qBean.getRequirement().replace("'", "\\'")
						+ "','" + qBean.getComment().replace("'", "\\'") + "','" + qBean.getNumber() + "','"
						+ qBean.getXs() + "','" + qBean.getS() + "','" + qBean.getM() + "','" + qBean.getL() + "','"
						+ qBean.getXl() + "','" + qBean.getXxl() + "','" + qBean.getCheck() + "','"
						+ qBean.getEssential() + "','" + qBean.getQuestion().replace("'", "\\'") + "','"
						+ qBean.getObservation().replace("'", "\\'") + "','" + qBean.getPerson() + "','" + file
						+ "','active','" + qBean.getImage_manadatory() + "')");
	}

	public List<QuestionBean> getQuestionnaireList(QuestionBean qBean) {
		System.out.println(
				"SELECT mst_questionnaire.*,mst_section.* FROM mst_questionnaire,mst_section WHERE mst_questionnaire.section_id=mst_section.sk_section_id;");
		return template.query(
				"SELECT mst_questionnaire.*,mst_section.* FROM mst_questionnaire,mst_section WHERE mst_questionnaire.section_id=mst_section.sk_section_id;",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						qBean.setSection(rs.getString("section_name"));
						qBean.setSection_id(rs.getString("sk_section_id"));
						qBean.setStandard(rs.getString("standard"));
						qBean.setRequirement(rs.getString("requirement"));
						qBean.setComment(rs.getString("comment"));
						qBean.setNumber(rs.getString("number"));
						qBean.setXs(rs.getString("xs"));
						qBean.setS(rs.getString("s"));
						qBean.setM(rs.getString("m"));
						qBean.setL(rs.getString("l"));
						qBean.setXl(rs.getString("xl"));
						qBean.setXxl(rs.getString("xxl"));
						qBean.setCheck(rs.getString("type_of_check"));
						qBean.setEssential(rs.getString("essential"));
						qBean.setQuestion(rs.getString("question"));
						qBean.setObservation(rs.getString("observation"));
						qBean.setPerson(rs.getString("suggested_person"));
						qBean.setQuestionnaire_id(rs.getString("sk_questionnaire_id"));
						qBean.setPic(rs.getString("file"));
						qBean.setBrand(rs.getString("brand"));
						qBean.setType(rs.getString("type"));
						return qBean;
					}
				});
	}

	public List<QuestionBean> getQuestionnaireList(QuestionBean qBean, String bname, String type) {
		System.out.println(
				"SELECT mst_questionnaire.*,mst_section.* FROM mst_questionnaire,mst_section WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_questionnaire.brand='"
						+ bname + "' AND mst_questionnaire.type='" + type + "';");
		return template.query(
				"SELECT mst_questionnaire.*,mst_section.* FROM mst_questionnaire,mst_section WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_questionnaire.brand='"
						+ bname + "' AND mst_questionnaire.type='" + type + "';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						qBean.setSection(rs.getString("section_name"));
						qBean.setSection_id(rs.getString("sk_section_id"));
						qBean.setStandard(rs.getString("standard"));
						qBean.setRequirement(rs.getString("requirement"));
						qBean.setComment(rs.getString("comment"));
						qBean.setNumber(rs.getString("number"));
						qBean.setXs(rs.getString("xs"));
						qBean.setS(rs.getString("s"));
						qBean.setM(rs.getString("m"));
						qBean.setL(rs.getString("l"));
						qBean.setXl(rs.getString("xl"));
						qBean.setXxl(rs.getString("xxl"));
						qBean.setCheck(rs.getString("type_of_check"));
						qBean.setEssential(rs.getString("essential"));
						qBean.setQuestion(rs.getString("question"));
						qBean.setObservation(rs.getString("observation"));
						qBean.setPerson(rs.getString("suggested_person"));
						qBean.setQuestionnaire_id(rs.getString("sk_questionnaire_id"));
						qBean.setPic(rs.getString("file"));
						qBean.setBrand(rs.getString("brand"));
						qBean.setType(rs.getString("type"));
						qBean.setImage_manadatory(rs.getString("image_mandatory"));
						return qBean;
					}
				});
	}

	public QuestionBean getQuestionnaireById(final QuestionBean qBean, String qid) {
		System.out.println(
				"SELECT mst_questionnaire.*,mst_section.* FROM mst_questionnaire,mst_section WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_questionnaire.sk_questionnaire_id='"
						+ qid + "';");
		return template.queryForObject(
				"SELECT mst_questionnaire.*,mst_section.* FROM mst_questionnaire,mst_section WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_questionnaire.sk_questionnaire_id='"
						+ qid + "';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setSection(rs.getString("section_name"));
						qBean.setSection_id(rs.getString("sk_section_id"));
						qBean.setStandard(rs.getString("standard"));
						qBean.setRequirement(rs.getString("requirement"));
						qBean.setComment(rs.getString("comment"));
						qBean.setNumber(rs.getString("number"));
						qBean.setXs(rs.getString("xs"));
						qBean.setS(rs.getString("s"));
						qBean.setM(rs.getString("m"));
						qBean.setL(rs.getString("l"));
						qBean.setXl(rs.getString("xl"));
						qBean.setXxl(rs.getString("xxl"));
						qBean.setCheck(rs.getString("type_of_check"));
						qBean.setEssential(rs.getString("essential"));
						qBean.setQuestion(rs.getString("question"));
						qBean.setObservation(rs.getString("observation"));
						qBean.setPerson(rs.getString("suggested_person"));
						qBean.setQuestionnaire_id(rs.getString("sk_questionnaire_id"));
						qBean.setBrand(rs.getString("brand"));
						qBean.setType(rs.getString("type"));
						qBean.setPic(rs.getString("file"));
						qBean.setImage_manadatory(rs.getString("image_mandatory"));
						return qBean;
					}
				});
	}

	public void updateQuestionnaireById(QuestionBean qBean, String qid, String file) {
		System.out.println("UPDATE mst_questionnaire SET section_id='" + qBean.getSection_id() + "',standard='"
				+ qBean.getStandard().replace("'", "\\'") + "',requirement='"
				+ qBean.getRequirement().replace("'", "\\'") + "',comment='" + qBean.getComment().replace("'", "\\'")
				+ "',number='" + qBean.getNumber() + "',xs='" + qBean.getXs() + "',s='" + qBean.getS() + "',m='"
				+ qBean.getM() + "',l='" + qBean.getL() + "',xl='" + qBean.getXl() + "'," + "xxl='" + qBean.getXxl()
				+ "',type_of_check='" + qBean.getCheck() + "',essential='" + qBean.getEssential() + "',question='"
				+ qBean.getQuestion().replace("'", "\\'") + "',observation='"
				+ qBean.getObservation().replace("'", "\\'") + "',suggested_person='" + qBean.getPerson() + "',brand='"
				+ qBean.getBrand() + "',type='" + qBean.getType() + "',file='" + file
				+ "',status='active',image_mandatory='" + qBean.getImage_manadatory() + "' WHERE sk_questionnaire_id='"
				+ qid + "' ");
		template.execute("UPDATE mst_questionnaire SET section_id='" + qBean.getSection_id() + "',standard='"
				+ qBean.getStandard().replace("'", "\\'") + "',requirement='"
				+ qBean.getRequirement().replace("'", "\\'") + "',comment='" + qBean.getComment().replace("'", "\\'")
				+ "',number='" + qBean.getNumber() + "',xs='" + qBean.getXs() + "',s='" + qBean.getS() + "',m='"
				+ qBean.getM() + "',l='" + qBean.getL() + "',xl='" + qBean.getXl() + "'," + "xxl='" + qBean.getXxl()
				+ "',type_of_check='" + qBean.getCheck() + "',essential='" + qBean.getEssential() + "',question='"
				+ qBean.getQuestion().replace("'", "\\'") + "',observation='"
				+ qBean.getObservation().replace("'", "\\'") + "',suggested_person='" + qBean.getPerson() + "',brand='"
				+ qBean.getBrand() + "',type='" + qBean.getType() + "',file='" + file
				+ "',status='active',image_mandatory='" + qBean.getImage_manadatory() + "' WHERE sk_questionnaire_id='"
				+ qid + "' ");

	}

	public void deleteQuestionnaireById(QuestionBean qBean, String qid) {
		System.out.println("DELETE FROM mst_questionnaire WHERE sk_questionnaire_id='" + qid + "';");
		template.execute("DELETE FROM mst_questionnaire WHERE sk_questionnaire_id='" + qid + "';");

	}

	public List<UserBean> getOutletBydealerShipId(UserBean uBean, String dealerShipId) {
		System.out.println("SELECT * FROM mst_outlet WHERE dealership_id='" + dealerShipId + "' ;");
		return template.query("SELECT * FROM mst_outlet WHERE dealership_id='" + dealerShipId + "' ;",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						UserBean uBean = new UserBean();
						uBean.setOutlet_id(rs.getString("sk_outlet_id"));
						uBean.setOutlets(rs.getString("outlet_name"));
						uBean.setBrand(rs.getString("brand"));
						uBean.setOutlet_type(rs.getString("outlet_type"));
						return uBean;
					}
				});
	}

	public List<UserBean> getOutletBydealerShipId(UserBean uBean, String dealerShipId, String type) {
		String sql = "";
		if (type.equals("ALL")) {
			sql = "SELECT * FROM mst_outlet WHERE dealership_id='" + dealerShipId + "'";
		} else {
			sql = "SELECT * FROM mst_outlet WHERE dealership_id='" + dealerShipId + "' AND outlet_type='" + type + "'";
		}
		System.out.println(sql);
		return template.query(sql, new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean uBean = new UserBean();
				uBean.setOutlet_id(rs.getString("sk_outlet_id"));
				uBean.setOutlets(rs.getString("outlet_name"));
				uBean.setBrand(rs.getString("brand"));
				uBean.setOutlet_type(rs.getString("outlet_type"));
				return uBean;
			}
		});
	}

	public List<UserBean> getSalesOutletBydealerShipId(UserBean uBean, String dealerShipId, String phase, String year,String brand) {
		System.out.println("SELECT * FROM mst_outlet,mst_audit_schedule WHERE mst_outlet.dealership_id='" + dealerShipId
				+ "' AND mst_outlet.outlet_type='Sales' AND mst_outlet.sk_outlet_id=mst_audit_schedule.outlet_id AND mst_audit_schedule.quarter='"
				+ phase + "' AND year='" + year + "' ORDER BY mst_outlet.outlet_name ASC;");
		return template.query("SELECT * FROM mst_outlet,mst_audit_schedule WHERE mst_outlet.dealership_id='"
				+ dealerShipId
				+ "' AND mst_outlet.outlet_type='Sales' AND mst_outlet.brand='"+brand+"' AND mst_outlet.sk_outlet_id=mst_audit_schedule.outlet_id AND mst_audit_schedule.quarter='"
				+ phase + "' AND year='" + year + "' ORDER BY mst_outlet.outlet_name ASC;", new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						UserBean uBean = new UserBean();
						uBean.setOutlet_id(rs.getString("sk_outlet_id"));
						uBean.setOutlets(rs.getString("outlet_name"));
						uBean.setBrand(rs.getString("brand"));
						uBean.setOutlet_type(rs.getString("outlet_type"));
						return uBean;
					}
				});
	}

	public List<UserBean> getServiceOutletBydealerShipId(UserBean uBean, String dealerShipId, String phase,String year,String brand) {
		System.out.println("SELECT * FROM mst_outlet,mst_audit_schedule WHERE mst_outlet.dealership_id='" + dealerShipId
				+ "' AND mst_outlet.outlet_type='Service' AND mst_outlet.brand='"+brand+"' AND mst_outlet.sk_outlet_id=mst_audit_schedule.outlet_id AND mst_audit_schedule.quarter='"
				+ phase + "' AND year='" + year + "' ORDER BY mst_outlet.outlet_name ASC;");
		return template.query("SELECT * FROM mst_outlet,mst_audit_schedule WHERE mst_outlet.dealership_id='"
				+ dealerShipId
				+ "' AND mst_outlet.outlet_type='Service' AND mst_outlet.brand='"+brand+"' AND mst_outlet.sk_outlet_id=mst_audit_schedule.outlet_id AND mst_audit_schedule.quarter='"
				+ phase + "' AND year='" + year + "' ORDER BY mst_outlet.outlet_name ASC;", new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						UserBean uBean = new UserBean();
						uBean.setOutlet_id(rs.getString("sk_outlet_id"));
						uBean.setOutlets(rs.getString("outlet_name"));
						uBean.setBrand(rs.getString("brand"));
						uBean.setOutlet_type(rs.getString("outlet_type"));
						return uBean;
					}
				});
	}

	public void addAudit(UserBean uBean, String did) {
		String extra_emails = uBean.getEmail();
		String dealer_email = "";
		System.out.println(uBean.getUser_id());
		String sql_filter = "";
		try {
			String temp1[] = uBean.getOutlet_id().split(",");
			String temp2[] = uBean.getOutlets().split(",");
			String temp3[] = uBean.getAudit_date().split(",");
			String temp4[] = uBean.getEnd_date().split(",");
			String temp5[] = uBean.getUser_id().split(",");
			String temp6[] = uBean.getOutlet_type().split(",");
			String temp7[] = uBean.getBrand().split(",");
			

			System.out.println("MANJU IN DAO CHECK--" + temp1.length);
			String outlets = "";
			String email = "";
			for (int j = 0; j < temp1.length; j++) {
				System.out.println(
						"INSERT INTO mst_audit_schedule (dealership_id,outlet_id,outlet_name,audit_date,audit_end_date,quarter,year,email_for,outlet_type,status,auditor_id) VALUES ('"
								+ did + "','" + temp1[j] + "','" + temp2[j] + "','" + temp3[j] + "','" + temp4[j]
								+ "','" + uBean.getQuarter() + "','" + uBean.getYear() + "','" + uBean.getEmail()
								+ "','" + temp6[j] + "','active','" + temp5[j] + "');");
				template.execute(
						"INSERT INTO mst_audit_schedule (dealership_id,outlet_id,outlet_name,audit_date,audit_end_date,quarter,year,email_for,outlet_type,status,auditor_id) VALUES ('"
								+ did + "','" + temp1[j] + "','" + temp2[j] + "','" + temp3[j] + "','" + temp4[j]
								+ "','" + uBean.getQuarter() + "','" + uBean.getYear() + "','" + uBean.getEmail()
								+ "','" + temp6[j] + "','active','" + temp5[j] + "');");

				if (j == 0) {
					sql_filter = " FIND_IN_SET('" + temp1[j] + "',outlets) ";
				} else {
					sql_filter = sql_filter + " OR FIND_IN_SET('" + temp1[j] + "',outlets) ";
				}

				/* getDealerDeatailsById(uBean, did); */
				getOutletById(uBean, temp1[j]);
				UserBean uBean1 = new UserBean();
				getUserDetailsById(uBean1, temp5[j]);
				email = uBean.getEmail();
				long diff = 0;
				long diffInMillies = 0;
				try {

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					Date firstDate = sdf.parse(temp3[j]);
					Date secondDate = sdf.parse(temp4[j]);

					String[] d1 = temp3[j].split("/");
					String[] d2 = temp4[j].split("/");
					diff = (Integer.parseInt(d2[0]) - Integer.parseInt(d1[0])) + 1;
					diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
					diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;

				} catch (Exception e) {
					System.out.println(e + "DATES ERROR");
				}
				outlets = outlets + "<tr style='height:24.05pt'>"
						+ " <td style='border-right-width:1pt;border-bottom-width:1pt;border-left-width:1pt;border-style:none solid solid;border-right-color:windowtext;border-bottom-color:windowtext;border-left-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;'>"
						+ uBean.getDealership_name() + "<u></u><u></u></span></div>" + "</td>"
						+ "<td width='161' nowrap='' style='width:121pt;border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;'>"
						+ temp2[j] + "<u></u><u></u></span></div>" + "</td>"
						+ "<td width='161' nowrap='' style='width:121pt;border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;'>"
						+ uBean.getOutlet_size() + "<u></u><u></u></span></div>" + "</td>"
						+ "<td width='161' nowrap='' style='width:121pt;border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;'>"
						+ temp7[j] + "<u></u><u></u></span></div>" + "</td>"
						
						+ "<td width='144' nowrap='' style='width:1.5in;border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;'>"
						+ uBean.getOutlet_type() + "<u></u><u></u></span></div>" + " </td>"
						+ "<td width='109' nowrap='' style='width:82pt;border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='color:rgb(31,73,125)'>"
						+ temp3[j] + "<u></u><u></u></span></div>" + "</td>"
						+ "<td width='104' nowrap='' style='width:78pt;border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='color:rgb(31,73,125)'>"
						+ temp4[j] + "<u></u><u></u></span></div>" + "</td>"
						+ "<td width='80' nowrap='' style='width:60pt;border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;'>"
						+ diff + "<u></u><u></u></span></div>" + "</td>"
						+ "<td style='border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ " <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;;color:rgb(31,73,125)'>"
						+ uBean1.getFirst_name()
						+ "</span><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;'><u></u><u></u></span></div>"
						+ " </td>"
						+ "<td style='border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><a href='mailto:amitoj.kundhal@in.ey.com' style='color:purple;text-decoration:underline' target='_blank'>"
						+ uBean1.getEmail() + "</a><u><span style='color:blue'><u></u><u></u></span></u></div>"
						+ "</td>"
						+ "<td width='123' style='width:92pt;border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif'><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;'>"
						+ uBean1.getMobile()
						+ "</span><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;;color:white'><u></u><u></u></span></div>"
						+ "</td>" + "</tr>";

				UserBean uBean3 = new UserBean();
				getDealerDeatailsById(uBean3, did);
				dealer_email = uBean3.getEmail();
			}
			UserBean uBean2 = new UserBean();
			getEmailsByAudit(sql_filter, uBean2);

			System.out.println("emailsssssssssssssssssssssss : " + uBean2.getEmail());
			if (extra_emails.isEmpty() || extra_emails == "") {
				email = uBean2.getEmail() + "," + dealer_email;
			} else {
				email = uBean2.getEmail() + "," + dealer_email + "," + extra_emails;
			}
			
			SendEmailUsingGMailSMTP mail = new SendEmailUsingGMailSMTP();
			mail.sendMail(email, outlets, uBean.getContact_person() + "," + uBean.getFile_path() + ","
					+ uBean.getDealership_name() + "," + uBean.getQuarter() + "," + uBean.getYear());
			System.out.println("IN  MAIL :" + email);
		} catch (Exception e) {
			System.out.println(e + "Something messed up");
		}
	}

	public UserBean getEmailsByAudit(String sql_filter, final UserBean uBean) {
		System.out.println("SELECT group_concat(email) as email FROM `mst_users` WHERE user_status='active'  and "
				+ sql_filter + " ");
		return template
				.queryForObject("SELECT group_concat(email) as email FROM `mst_users` WHERE user_status='active'  and "
						+ sql_filter + ";", new RowMapper<UserBean>() {
							public UserBean mapRow(ResultSet rs, int row) throws SQLException {
								uBean.setEmail(rs.getString("email"));
								return uBean;
							}
						});
	}

	public List<UserBean> getAuditList(UserBean uBean, String status) {
		System.out.println(
				"SELECT mst_audit_schedule.*,mst_dealership.*,mst_outlet.brand FROM mst_dealership,mst_audit_schedule,mst_outlet WHERE mst_audit_schedule.dealership_id=mst_dealership.sk_dealership_id AND mst_audit_schedule.status='"
						+ status
						+ "'  and mst_audit_schedule.outlet_id=mst_outlet.sk_outlet_id and mst_audit_schedule.dealership_id=mst_outlet.dealership_id");
		return template.query(
				"SELECT mst_audit_schedule.*,mst_dealership.*,mst_outlet.brand FROM mst_dealership,mst_audit_schedule,mst_outlet WHERE mst_audit_schedule.dealership_id=mst_dealership.sk_dealership_id AND mst_audit_schedule.status='"
						+ status
						+ "'  and mst_audit_schedule.outlet_id=mst_outlet.sk_outlet_id and mst_audit_schedule.dealership_id=mst_outlet.dealership_id;",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						final UserBean uBean = new UserBean();
						uBean.setDealership_id(rs.getString("sk_dealership_id"));
						uBean.setDealership_name(rs.getString("dealership_name"));
						uBean.setOutlets(rs.getString("outlet_name"));
						uBean.setOutlet_id(rs.getString("outlet_id"));
						uBean.setAudit_date(rs.getString("audit_date"));
						uBean.setAudit_id(rs.getString("sk_audit_id"));
						uBean.setClosed_date(rs.getString("audit_complete_date"));
						uBean.setEnd_date(rs.getString("audit_end_date"));
						uBean.setOutlet_type(rs.getString("outlet_type"));
						uBean.setAuditor_id(rs.getString("auditor_id"));
						uBean.setBrand(rs.getString("brand"));
						uBean.setAudit_summary(rs.getString("audit_summary"));
						System.out.println(
								"SELECT * FROM mst_users WHERE sk_user_id = '" + rs.getString("auditor_id") + "'");
						template.query(
								"SELECT * FROM mst_users WHERE sk_user_id = '" + rs.getString("auditor_id") + "';",
								new RowMapper<UserBean>() {
									public UserBean mapRow(ResultSet rs, int row) throws SQLException {
										uBean.setUser_type_id(rs.getString("user_type"));
										uBean.setFirst_name(rs.getString("first_name"));
										uBean.setLast_name(rs.getString("last_name"));
										uBean.setEmail(rs.getString("email"));
										uBean.setMobile(rs.getString("mobile"));
										uBean.setPassword(rs.getString("password"));
										uBean.setUser_id(rs.getString("sk_user_id"));

										return uBean;
									}
								});

						return uBean;
					}
				});
	}

	public List<UserBean> getOutletsByUserId(UserBean uBean, String outlet_id) {
		System.out.println(
				"SELECT mst_outlet.*,mst_dealership.dealership_name FROM mst_outlet,mst_dealership WHERE mst_outlet.dealership_id=mst_dealership.sk_dealership_id AND mst_outlet.sk_outlet_id in ("
						+ outlet_id + ")");
		return template.query(
				"SELECT mst_outlet.*,mst_dealership.dealership_name FROM mst_outlet,mst_dealership WHERE mst_outlet.dealership_id=mst_dealership.sk_dealership_id AND mst_outlet.sk_outlet_id in ("
						+ outlet_id + ");",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						UserBean uBean = new UserBean();
						uBean.setDealership_id(rs.getString("dealership_id"));
						uBean.setContact_person(rs.getString("contact_person"));
						uBean.setEmail(rs.getString("email"));
						uBean.setMobile(rs.getString("mobile"));
						uBean.setAddress(rs.getString("address"));
						uBean.setState(rs.getString("state"));
						uBean.setCity(rs.getString("city"));
						uBean.setOutlets(rs.getString("outlet_name"));
						uBean.setOutlet_id(rs.getString("sk_outlet_id"));
						uBean.setDealership_name(rs.getString("dealership_name"));
						uBean.setOutlet_size(rs.getString("outlet_size"));
						uBean.setOutlet_type(rs.getString("outlet_type"));
						uBean.setBrand(rs.getString("brand"));
						return uBean;
					}
				});
	}

	public List<UserBean> getAllOutlets(UserBean uBean, String outlet_id) {
		System.out.println(
				"SELECT mst_outlet.*,mst_dealership.dealership_name FROM mst_outlet,mst_dealership WHERE mst_outlet.dealership_id=mst_dealership.sk_dealership_id LIMIT 1");
		return template.query(
				"SELECT mst_outlet.*,mst_dealership.dealership_name FROM mst_outlet,mst_dealership WHERE mst_outlet.dealership_id=mst_dealership.sk_dealership_id LIMIT 1;",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						UserBean uBean = new UserBean();
						uBean.setOutlets("All");
						uBean.setOutlet_id("all");
						uBean.setDealership_name("All");
						uBean.setOutlet_type("All");
						uBean.setBrand("All");
						return uBean;
					}
				});
	}

	public UserBean checkDealerAuditExist(final UserBean uBean, String did, String qid, String year) {
		System.out.println("SELECT * FROM mst_audit_schedule WHERE dealership_id='" + did + "' and quarter='" + qid
				+ "' and year='" + year + "'  LIMIT 1;");
		return template.queryForObject("SELECT * FROM mst_audit_schedule WHERE dealership_id='" + did
				+ "' and quarter='" + qid + "' and year='" + year + "'  LIMIT 1;", new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {

						return uBean;
					}
				});
	}

	public List<UserBean> getOutletsFromAudit(UserBean uBean, final String did, String qid, String year) {
		System.out.println(
				"SELECT mst_audit_schedule.*,mst_outlet.brand  FROM mst_audit_schedule,mst_outlet WHERE mst_audit_schedule.dealership_id='"
						+ did + "' and mst_audit_schedule.quarter='" + qid + "' and mst_audit_schedule.year='" + year
						+ "' and mst_audit_schedule.outlet_id=mst_outlet.sk_outlet_id and mst_audit_schedule.dealership_id=mst_outlet.dealership_id;");
		return template.query(
				"SELECT mst_audit_schedule.*,mst_outlet.brand  FROM mst_audit_schedule,mst_outlet WHERE mst_audit_schedule.dealership_id='"
						+ did + "' and mst_audit_schedule.quarter='" + qid + "' and mst_audit_schedule.year='" + year
						+ "' and mst_audit_schedule.outlet_id=mst_outlet.sk_outlet_id and mst_audit_schedule.dealership_id=mst_outlet.dealership_id;",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						final UserBean uBean = new UserBean();
						uBean.setOutlet_id(rs.getString("outlet_id"));
						uBean.setOutlets(rs.getString("outlet_name"));
						uBean.setAudit_date(rs.getString("audit_date"));
						uBean.setEnd_date(rs.getString("audit_end_date"));
						uBean.setEmail(rs.getString("email_for"));
						uBean.setOutlet_type(rs.getString("outlet_type"));
						uBean.setBrand(rs.getString("brand"));
						
						
						System.out.println("SELECT * FROM mst_users WHERE sk_user_id='"+rs.getString("auditor_id")+"';");
						 template.query("SELECT * FROM mst_users WHERE sk_user_id='"+rs.getString("auditor_id")+"';",new RowMapper<UserBean>() {
									public UserBean mapRow(ResultSet rs, int row) throws SQLException {
										uBean.setFirst_name(rs.getString("first_name"));
										uBean.setLast_name(rs.getString("last_name"));
										uBean.setAuditor_id(rs.getString("sk_user_id"));
										return uBean;
									}
								});
						
						
						return uBean;
					}
				});
	}

	public void updateAudit(UserBean uBean, String did) {
		String extra_emails = uBean.getEmail();
		String dealer_email = "";
		System.out.println(uBean.getUser_id());
		String sql_filter = "";
		System.out.println("DELETE FROM mst_audit_schedule WHERE dealership_id='" + did + "' and quarter='"
				+ uBean.getQuarter() + "' AND year='" + uBean.getYear() + "'");
		template.execute("DELETE FROM mst_audit_schedule WHERE dealership_id='" + did + "' and quarter='"
				+ uBean.getQuarter() + "' AND year='" + uBean.getYear() + "'");
		/*
		 * String temp1[] = uBean.getOutlet_id().split(","); String temp2[] =
		 * uBean.getOutlets().split(","); String temp3[] =
		 * uBean.getAudit_date().split(","); String temp4[] =
		 * uBean.getEnd_date().split(","); String temp5[] =
		 * uBean.getUser_id().split(","); String temp6[] =
		 * uBean.getOutlet_type().split(","); for (int j = 0; j < temp1.length; j++) {
		 * System.out.println(
		 * "INSERT INTO mst_audit_schedule (dealership_id,outlet_id,outlet_name,audit_date,audit_end_date,quarter,year,email_for,outlet_type,status,auditor_id) VALUES ('"
		 * + did + "','" + temp1[j] + "','" + temp2[j] + "','" + temp3[j] + "','" +
		 * temp4[j] + "','" + uBean.getQuarter() + "','" + uBean.getYear() + "','" +
		 * uBean.getEmail() + "','" + temp6[j] + "','active','" + temp5[j] + "');");
		 * template.execute(
		 * "INSERT INTO mst_audit_schedule (dealership_id,outlet_id,outlet_name,audit_date,audit_end_date,quarter,year,email_for,outlet_type,status,auditor_id) VALUES ('"
		 * + did + "','" + temp1[j] + "','" + temp2[j] + "','" + temp3[j] + "','" +
		 * temp4[j] + "','" + uBean.getQuarter() + "','" + uBean.getYear() + "','" +
		 * uBean.getEmail() + "','" + temp6[j] + "','active','" + temp5[j] + "');");
		 * 
		 * 
		 * 
		 * }
		 */
		try {
			String temp1[] = uBean.getOutlet_id().split(",");
			String temp2[] = uBean.getOutlets().split(",");
			String temp3[] = uBean.getAudit_date().split(",");
			String temp4[] = uBean.getEnd_date().split(",");
			String temp5[] = uBean.getUser_id().split(",");
			String temp6[] = uBean.getOutlet_type().split(",");
			String temp7[] = uBean.getBrand().split(",");

			System.out.println("MANJU IN DAO CHECK--" + temp1.length);
			String outlets = "";
			String email = "";
			for (int j = 0; j < temp1.length; j++) {
				System.out.println(
						"INSERT INTO mst_audit_schedule (dealership_id,outlet_id,outlet_name,audit_date,audit_end_date,quarter,year,email_for,outlet_type,status,auditor_id) VALUES ('"
								+ did + "','" + temp1[j] + "','" + temp2[j] + "','" + temp3[j] + "','" + temp4[j]
								+ "','" + uBean.getQuarter() + "','" + uBean.getYear() + "','" + uBean.getEmail()
								+ "','" + temp6[j] + "','active','" + temp5[j] + "');");
				template.execute(
						"INSERT INTO mst_audit_schedule (dealership_id,outlet_id,outlet_name,audit_date,audit_end_date,quarter,year,email_for,outlet_type,status,auditor_id) VALUES ('"
								+ did + "','" + temp1[j] + "','" + temp2[j] + "','" + temp3[j] + "','" + temp4[j]
								+ "','" + uBean.getQuarter() + "','" + uBean.getYear() + "','" + uBean.getEmail()
								+ "','" + temp6[j] + "','active','" + temp5[j] + "');");

				if (j == 0) {
					sql_filter = " FIND_IN_SET('" + temp1[j] + "',outlets) ";
				} else {
					sql_filter = sql_filter + " OR FIND_IN_SET('" + temp1[j] + "',outlets) ";
				}

				/* getDealerDeatailsById(uBean, did); */
				getOutletById(uBean, temp1[j]);
				UserBean uBean1 = new UserBean();
				getUserDetailsById(uBean1, temp5[j]);
				email = uBean.getEmail();
				long diff = 0;
				long diffInMillies = 0;
				try {

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
					Date firstDate = sdf.parse(temp3[j]);
					Date secondDate = sdf.parse(temp4[j]);

					String[] d1 = temp3[j].split("/");
					String[] d2 = temp4[j].split("/");
					diff = (Integer.parseInt(d2[0]) - Integer.parseInt(d1[0])) + 1;
					diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
					diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;

				} catch (Exception e) {
					System.out.println(e + "DATES ERROR");
				}
				outlets = outlets + "<tr style='height:24.05pt'>"
						+ " <td style='border-right-width:1pt;border-bottom-width:1pt;border-left-width:1pt;border-style:none solid solid;border-right-color:windowtext;border-bottom-color:windowtext;border-left-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;'>"
						+ uBean.getDealership_name() + "<u></u><u></u></span></div>" + "</td>"
						+ "<td width='161' nowrap='' style='width:121pt;border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;'>"
						+ temp2[j] + "<u></u><u></u></span></div>" + "</td>"
						+ "<td width='161' nowrap='' style='width:121pt;border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;'>"
						+ uBean.getOutlet_size() + "<u></u><u></u></span></div>" + "</td>"
						+ "<td width='161' nowrap='' style='width:121pt;border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;'>"
						+ temp7[j] + "<u></u><u></u></span></div>" + "</td>"
						+ "<td width='144' nowrap='' style='width:1.5in;border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;'>"
						+ uBean.getOutlet_type() + "<u></u><u></u></span></div>" + " </td>"

						+ "<td width='109' nowrap='' style='width:82pt;border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='color:rgb(31,73,125)'>"
						+ temp3[j] + "<u></u><u></u></span></div>" + "</td>"
						+ "<td width='104' nowrap='' style='width:78pt;border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='color:rgb(31,73,125)'>"
						+ temp4[j] + "<u></u><u></u></span></div>" + "</td>"
						+ "<td width='80' nowrap='' style='width:60pt;border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;'>"
						+ diff + "<u></u><u></u></span></div>" + "</td>"
						+ "<td style='border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ " <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;;color:rgb(31,73,125)'>"
						+ uBean1.getFirst_name()
						+ "</span><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;'><u></u><u></u></span></div>"
						+ " </td>"
						+ "<td style='border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><a href='mailto:amitoj.kundhal@in.ey.com' style='color:purple;text-decoration:underline' target='_blank'>"
						+ uBean1.getEmail() + "</a><u><span style='color:blue'><u></u><u></u></span></u></div>"
						+ "</td>"
						+ "<td width='123' style='width:92pt;border-style:none solid solid none;border-bottom-width:1pt;border-bottom-color:windowtext;border-right-width:1pt;border-right-color:windowtext;padding:0in 5.4pt;height:24.05pt'>"
						+ "  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif'><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;'>"
						+ uBean1.getMobile()
						+ "</span><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;;color:white'><u></u><u></u></span></div>"
						+ "</td>" + "</tr>";

				UserBean uBean3 = new UserBean();
				getDealerDeatailsById(uBean3, did);
				dealer_email = uBean3.getEmail();
			}
			UserBean uBean2 = new UserBean();
			getEmailsByAudit(sql_filter, uBean2);

			System.out.println("emailsssssssssssssssssssssss : " + uBean2.getEmail());
			if (extra_emails.isEmpty() || extra_emails == "") {
				email = uBean2.getEmail() + "," + dealer_email;
			} else {
				email = uBean2.getEmail() + "," + dealer_email + "," + extra_emails;
			}
			SendEmailUsingGMailSMTP mail = new SendEmailUsingGMailSMTP();
			mail.sendMail(email, outlets, uBean.getContact_person() + "," + uBean.getFile_path() + ","
					+ uBean.getDealership_name() + "," + uBean.getQuarter() + "," + uBean.getYear());
			System.out.println("IN  MAIL :" + email);
		} catch (Exception e) {
			System.out.println(e + "Something messed up");
		}

	}

	public void insertAudit(QuestionBean qBean, String section_id, String toc, String outlet_size, String user_id,
			String essential, String answer, String question_id) {
		System.out.println(
				"INSERT INTO mst_audit (dealer_id,outlet_id,audit_schedule_id,question_id,section_id,type_of_check,outlet_size,essential,results,auditor_id,session_id,audit_status) VALUES ("
						+ "'" + qBean.getDealer_id() + "','" + qBean.getOutlet_id() + "','"
						+ qBean.getAudit_schedule_id() + "','" + question_id + "','" + section_id + "','" + toc + "','"
						+ outlet_size + "','" + essential + "','" + answer + "','0','" + user_id + "','completed')");
		template.execute(
				"INSERT INTO mst_audit (dealer_id,outlet_id,audit_schedule_id,question_id,section_id,type_of_check,outlet_size,essential,results,auditor_id,session_id,audit_status) VALUES ("
						+ "'" + qBean.getDealer_id() + "','" + qBean.getOutlet_id() + "','"
						+ qBean.getAudit_schedule_id() + "','" + question_id + "','" + section_id + "','" + toc + "','"
						+ outlet_size + "','" + essential + "','" + answer + "','0','" + user_id + "','completed')");

	}

	public void updateAuditById(QuestionBean qBean, String answer, String question_id) {
		System.out.println("UPDATE mst_audit SET results='" + answer + "' WHERE audit_schedule_id='"
				+ qBean.getAudit_schedule_id() + "' AND question_id='" + question_id + "'");
		template.execute("UPDATE mst_audit SET results='" + answer + "' WHERE audit_schedule_id='"
				+ qBean.getAudit_schedule_id() + "' AND question_id='" + question_id + "'");

	}

	public QuestionBean getOutletById(final QuestionBean qBean, String oid) {
		System.out.println("SELECT * FROM mst_outlet WHERE mst_outlet.sk_outlet_id='" + oid + "';");
		return template.queryForObject("SELECT * FROM mst_outlet WHERE mst_outlet.sk_outlet_id='" + oid + "';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setOutlet_size(rs.getString("outlet_size"));
						return qBean;
					}
				});
	}

	/*
	 * SELECT mst_questionnaire.*,mst_section.* FROM mst_questionnaire,mst_section
	 * WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND
	 * mst_questionnaire.type='" + type +
	 * "' AND (mst_questionnaire.type_of_check='audit' OR mst_questionnaire.type_of_check='NSC/Audit') AND mst_questionnaire."
	 * + size + "!='0' and mst_questionnaire.section_id='" + hBean.getSection_id() +
	 * "' and mst_questionnaire.brand='" + brand + "' AND
	 * mst_questionnaire.type=mst_section.type ORDER BY mst_questionnaire.number ASC
	 */
	
	public List<QuestionBean> getQuestionnaireListWithOptions(QuestionBean qBean, final String asid, String type,
			String size, String brand, String nsc) {
		 final String qqid1="";
		System.out.println(
				"SELECT mst_questionnaire.*,mst_section.* FROM mst_questionnaire,mst_section WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_questionnaire.type='"
						+ type + "' AND mst_questionnaire.type_of_check='" + nsc + "' AND mst_questionnaire." + size
						+ "!='0' AND mst_questionnaire.brand='" + brand
						+ "' AND mst_questionnaire.type=mst_section.type ORDER BY mst_questionnaire.number ASC;");
		return template.query(
				"SELECT mst_questionnaire.*,mst_section.* FROM mst_questionnaire,mst_section WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_questionnaire.type='"
						+ type + "' AND mst_questionnaire.type_of_check='" + nsc + "' AND mst_questionnaire." + size
						+ "!='0' AND mst_questionnaire.brand='" + brand
						+ "' AND mst_questionnaire.type=mst_section.type ORDER BY mst_questionnaire.number ASC;",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final QuestionBean qBean = new QuestionBean();
						qBean.setSection(rs.getString("section_name"));
						qBean.setSection_id(rs.getString("sk_section_id"));
						qBean.setStandard(rs.getString("standard"));
						qBean.setRequirement(rs.getString("requirement"));
						qBean.setComment(rs.getString("comment"));
						qBean.setNumber(rs.getString("number"));
						qBean.setXs(rs.getString("xs"));
						qBean.setS(rs.getString("s"));
						qBean.setM(rs.getString("m"));
						qBean.setL(rs.getString("l"));
						qBean.setXl(rs.getString("xl"));
						qBean.setXxl(rs.getString("xxl"));
						qBean.setCheck(rs.getString("type_of_check"));
						qBean.setEssential(rs.getString("essential"));
						qBean.setQuestion(rs.getString("question"));
						qBean.setObservation(rs.getString("observation"));
						qBean.setPerson(rs.getString("suggested_person"));
						qBean.setQuestionnaire_id(rs.getString("sk_questionnaire_id"));

						System.out.println("SELECT mst_audit.* FROM mst_audit WHERE mst_audit.question_id='"
								+ rs.getString("sk_questionnaire_id") + "'  AND mst_audit.audit_schedule_id='" + asid
								+ "';");
						template.query("SELECT mst_audit.* FROM mst_audit WHERE mst_audit.question_id='"
								+ rs.getString("sk_questionnaire_id") + "'  AND mst_audit.audit_schedule_id='" + asid
								+ "';", new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										
										System.out.println("ANSWER :"+rs.getString("results"));
										qBean.setAnswer(rs.getString("results"));
										qBean.setAudit_id(rs.getString("sk_audit_id"));
										qBean.setAuditor_comment(rs.getString("auditor_comment"));
										qBean.setDealer_comment(rs.getString("dealer_comment"));
										
										if (rs.getString("results").equals("0")) {
											qBean.setAnswer_type("NO");
										} else {
											qBean.setAnswer_type("YES");
										}
										if (rs.getString("pmo_exception_remarks") == null
												|| rs.getString("pmo_exception_remarks").equals(null)) {
											qBean.setException_remarks("False");
										} else {
											qBean.setException_remarks("True");
										}
										//qqid1 = rs.getString("sk_audit_id");
										 String qqid2 = rs.getString("question_id");
										/* getImage(rs.getString("question_id"));*/
										 qBean.setQuestionId(rs.getString("question_id"));
										return qBean;
									}

									/*private void getImage(String qqid1) {
										System.out.println("SELECT * FROM txn_audit_images WHERE audit_scheduled_id='"+asid+"' AND question_id='"+qqid1+"';");
										template.query("SELECT * FROM txn_audit_images WHERE audit_scheduled_id='"+asid+"' AND question_id='"+qqid1+"';;", new RowMapper<QuestionBean>() {
													public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
														qBean.setReference_image(rs.getString("image_name"));
														System.out.println("image name"+rs.getString("image_name"));
														return qBean;
													}
												});										
									}*/
								});
						
						
						return qBean;
					}
				});
	}
	
	public List<QuestionBean> getPmoClosureQuestionnaires(QuestionBean qBean, final String asid, String outlet_type,
			String outlet_size) {
		System.out.println(
				"SELECT mst_questionnaire.*,mst_section.* FROM mst_questionnaire,mst_section,mst_audit WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_questionnaire.type='"
						+ outlet_type + "' AND mst_questionnaire." + outlet_size
						+ "!='0' AND mst_audit.results='1' AND mst_audit.pmo_review_status='Open' AND mst_audit.exception_timeline IS NOT NULL AND mst_audit.exception_timeline!='' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.audit_schedule_id='" + asid
								+ "';");
		return template.query(
				"SELECT mst_questionnaire.*,mst_section.* FROM mst_questionnaire,mst_section,mst_audit WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_questionnaire.type='"
						+ outlet_type + "'  AND mst_questionnaire." + outlet_size
						+ "!='0' AND mst_audit.results='1' AND mst_audit.pmo_review_status='Open' AND mst_audit.exception_timeline IS NOT NULL AND mst_audit.exception_timeline!='' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.audit_schedule_id='" + asid
								+ "';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final QuestionBean qBean = new QuestionBean();
						qBean.setSection(rs.getString("section_name"));
						qBean.setSection_id(rs.getString("sk_section_id"));
						qBean.setStandard(rs.getString("standard"));
						qBean.setRequirement(rs.getString("requirement"));
						qBean.setComment(rs.getString("comment"));
						qBean.setNumber(rs.getString("number"));
						qBean.setXs(rs.getString("xs"));
						qBean.setS(rs.getString("s"));
						qBean.setM(rs.getString("m"));
						qBean.setL(rs.getString("l"));
						qBean.setXl(rs.getString("xl"));
						qBean.setXxl(rs.getString("xxl"));
						qBean.setCheck(rs.getString("type_of_check"));
						qBean.setEssential(rs.getString("essential"));
						qBean.setQuestion(rs.getString("question"));
						qBean.setObservation(rs.getString("observation"));
						qBean.setPerson(rs.getString("suggested_person"));
						qBean.setQuestionnaire_id(rs.getString("sk_questionnaire_id"));

						System.out.println("SELECT mst_audit.* FROM mst_audit WHERE mst_audit.question_id='"
								+ rs.getString("sk_questionnaire_id") + "'  AND mst_audit.audit_schedule_id='" + asid
								+ "'  AND mst_audit.exception_timeline IS NOT NULL AND mst_audit.exception_timeline!='' ;");
						template.query("SELECT mst_audit.* FROM mst_audit WHERE mst_audit.question_id='"
								+ rs.getString("sk_questionnaire_id") + "'  AND mst_audit.audit_schedule_id='" + asid
								+ "'  AND mst_audit.exception_timeline IS NOT NULL AND mst_audit.exception_timeline!='';", new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setAnswer(rs.getString("results"));
										qBean.setAudit_id(rs.getString("sk_audit_id"));
										qBean.setAuditor_comment(rs.getString("auditor_comment"));
										qBean.setDealer_comment(rs.getString("dealer_comment"));
										qBean.setTime(rs.getString("exception_timeline"));
										qBean.setDealer_remarks(rs.getString("dealer_remarks"));
										qBean.setException_remarks(rs.getString("pmo_exception_remarks"));
										if (rs.getString("results").equals("0")) {
											qBean.setAnswer_type("NO");
										} else {
											qBean.setAnswer_type("YES");
										}
										qBean.setDealer_image(rs.getString("dealer_image"));
										return qBean;
									}
								});
						return qBean;
					}
				});
	}
	
	
	
	
	
	public List<QuestionBean> getPmoClosureQuestionnairesByDID(QuestionBean qBean, String did, String phase,String year) {
		System.out.println(
				"SELECT mst_questionnaire.*,mst_section.*,mst_audit_schedule.sk_audit_id,mst_audit_schedule.outlet_id FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id  AND mst_audit.results='1' AND mst_audit.pmo_review_status='Open' AND mst_audit.exception_timeline IS NOT NULL AND mst_audit.exception_timeline!='' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='" + did
								+ "' AND mst_audit_schedule.quarter='"+phase+"' AND mst_audit_schedule.year='"+year+"' AND mst_audit.audit_schedule_id=mst_audit_schedule.sk_audit_id;");
		return template.query(
				"SELECT mst_questionnaire.*,mst_section.*,mst_audit_schedule.sk_audit_id,mst_audit_schedule.outlet_id FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id  AND mst_audit.results='1' AND mst_audit.pmo_review_status='Open' AND mst_audit.exception_timeline IS NOT NULL AND mst_audit.exception_timeline!='' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='" + did
								+ "' AND mst_audit_schedule.quarter='"+phase+"' AND mst_audit_schedule.year='"+year+"' AND mst_audit.audit_schedule_id=mst_audit_schedule.sk_audit_id;",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final QuestionBean qBean = new QuestionBean();
						qBean.setSection(rs.getString("section_name"));
						qBean.setSection_id(rs.getString("sk_section_id"));
						qBean.setStandard(rs.getString("standard"));
						qBean.setRequirement(rs.getString("requirement"));
						qBean.setComment(rs.getString("comment"));
						qBean.setNumber(rs.getString("number"));
						qBean.setXs(rs.getString("xs"));
						qBean.setS(rs.getString("s"));
						qBean.setM(rs.getString("m"));
						qBean.setL(rs.getString("l"));
						qBean.setXl(rs.getString("xl"));
						qBean.setXxl(rs.getString("xxl"));
						qBean.setCheck(rs.getString("type_of_check"));
						qBean.setEssential(rs.getString("essential"));
						qBean.setQuestion(rs.getString("question"));
						qBean.setObservation(rs.getString("observation"));
						qBean.setPerson(rs.getString("suggested_person"));
						qBean.setQuestionnaire_id(rs.getString("sk_questionnaire_id"));
						String asid = rs.getString("sk_audit_id");
						qBean.setOutlet_id(rs.getString("outlet_id"));
						qBean.setAudit_schedule_id(rs.getString("sk_audit_id"));
						
						QuestionBean qBean1 = new QuestionBean();
						getOutletsByID(qBean1, rs.getString("outlet_id"));
						qBean.setOutlets(qBean1.getOutlets());
						qBean.setDealer(qBean1.getDealer());
						qBean.setOutlet_type(qBean1.getOutlet_type());
						

						System.out.println("SELECT mst_audit.* FROM mst_audit WHERE mst_audit.question_id='"
								+ rs.getString("sk_questionnaire_id") + "'  AND mst_audit.audit_schedule_id='" + asid
								+ "'  AND mst_audit.exception_timeline IS NOT NULL AND mst_audit.exception_timeline!='' ;");
						template.query("SELECT mst_audit.* FROM mst_audit WHERE mst_audit.question_id='"
								+ rs.getString("sk_questionnaire_id") + "'  AND mst_audit.audit_schedule_id='" + asid
								+ "'  AND mst_audit.exception_timeline IS NOT NULL AND mst_audit.exception_timeline!='';", new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setAnswer(rs.getString("results"));
										qBean.setAudit_id(rs.getString("sk_audit_id"));
										qBean.setAuditor_comment(rs.getString("auditor_comment"));
										qBean.setDealer_comment(rs.getString("dealer_comment"));
										qBean.setTime(rs.getString("exception_timeline"));
										qBean.setDealer_remarks(rs.getString("dealer_remarks"));
										qBean.setException_remarks(rs.getString("pmo_exception_remarks"));
										if (rs.getString("results").equals("0")) {
											qBean.setAnswer_type("NO");
										} else {
											qBean.setAnswer_type("YES");
										}
										qBean.setDealer_image(rs.getString("dealer_image"));
										return qBean;
									}
								});
						return qBean;
					}
				});
	}
	private QuestionBean getOutletsByID(final QuestionBean qBean, String oid) {
		try{
		System.out.println(
				"SELECT mst_outlet.*,mst_dealership.dealership_name,mst_dealership.contact_person as cp FROM mst_outlet,mst_dealership WHERE mst_outlet.dealership_id=mst_dealership.sk_dealership_id AND mst_outlet.sk_outlet_id='"
						+ oid + "';");
		return template.queryForObject(
				"SELECT mst_outlet.*,mst_dealership.dealership_name,mst_dealership.contact_person as cp FROM mst_outlet,mst_dealership WHERE mst_outlet.dealership_id=mst_dealership.sk_dealership_id AND mst_outlet.sk_outlet_id='"
						+ oid + "';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setOutlets(rs.getString("outlet_name"));
						qBean.setDealer(rs.getString("dealership_name"));
						qBean.setOutlet_type(rs.getString("outlet_type"));
						return qBean;
					}
				});
		}catch (Exception e) {
			System.out.println(e);
		}
		return qBean;
	}
	
	
	
	
	
	
	
	
	
	
	String adid = "";
	String qqid = "";
	public List<QuestionBean> getExceptionQuestionnaireListWithOptions(QuestionBean qBean, final String asid) {
		System.out.println(
				"SELECT mst_questionnaire.*,mst_section.* FROM mst_questionnaire,mst_section,mst_audit WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_audit.audit_schedule_id='"
						+ asid + "' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_questionnaire.essential!='O';");
		return template.query(
				"SELECT mst_questionnaire.*,mst_section.* FROM mst_questionnaire,mst_section,mst_audit WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_audit.audit_schedule_id='"
						+ asid + "' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_questionnaire.essential!='O';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final QuestionBean qBean = new QuestionBean();
						qBean.setSection(rs.getString("section_name"));
						qBean.setSection_id(rs.getString("sk_section_id"));
						qBean.setStandard(rs.getString("standard"));
						qBean.setRequirement(rs.getString("requirement"));
						qBean.setComment(rs.getString("comment"));
						qBean.setNumber(rs.getString("number"));
						qBean.setXs(rs.getString("xs"));
						qBean.setS(rs.getString("s"));
						qBean.setM(rs.getString("m"));
						qBean.setL(rs.getString("l"));
						qBean.setXl(rs.getString("xl"));
						qBean.setXxl(rs.getString("xxl"));
						qBean.setCheck(rs.getString("type_of_check"));
						qBean.setEssential(rs.getString("essential"));
						qBean.setQuestion(rs.getString("question"));
						qBean.setObservation(rs.getString("observation"));
						qBean.setPerson(rs.getString("suggested_person"));
						qBean.setQuestionnaire_id(rs.getString("sk_questionnaire_id"));

						System.out.println("SELECT mst_audit.* FROM mst_audit WHERE mst_audit.question_id='"
								+ rs.getString("sk_questionnaire_id") + "'  AND mst_audit.audit_schedule_id='" + asid
								+ "' AND mst_audit.results='0';");
						template.query("SELECT mst_audit.* FROM mst_audit WHERE mst_audit.question_id='"
								+ rs.getString("sk_questionnaire_id") + "'  AND mst_audit.audit_schedule_id='" + asid
								+ "' AND mst_audit.results='0';", new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setAnswer(rs.getString("results"));
										qBean.setAudit_id(rs.getString("sk_audit_id"));
										qBean.setAuditor_comment(rs.getString("auditor_comment"));
										qBean.setDealer_comment(rs.getString("dealer_comment"));
										
										if (rs.getString("results").equals("0")) {
											qBean.setAnswer_type("NO");
										} else {
											qBean.setAnswer_type("YES");
										}
										if (rs.getString("pmo_exception_remarks") == null
												|| rs.getString("pmo_exception_remarks").equals(null)) {
											qBean.setException_remarks("False");
										} else {
											qBean.setException_remarks("True");
										}
										 adid = rs.getString("sk_audit_id");
									/*	 getImage(rs.getString("question_id"));*/
										 qBean.setQuestionId(rs.getString("question_id"));
											return qBean;
										}

										/*private void getImage(String qqid1) {
											System.out.println("SELECT * FROM txn_audit_images WHERE audit_scheduled_id='"+asid+"' AND question_id='"+qqid1+"';");
											template.query("SELECT * FROM txn_audit_images WHERE audit_scheduled_id='"+asid+"' AND question_id='"+qqid1+"';;", new RowMapper<QuestionBean>() {
														public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
															qBean.setReference_image(rs.getString("image_name"));
															System.out.println("image name"+rs.getString("image_name"));
															return qBean;
														}
													});										
										}*/
									});
							
							
							return qBean;
						}
					});
		}

	public List<QuestionBean> getMSQuestionnaireListWithOptions(QuestionBean qBean, final String asid,
			String outlet_type, String outlet_size, String brand) {

		/*
		 * SELECT mst_questionnaire.*,mst_section.* FROM mst_questionnaire,mst_section
		 * WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND
		 * mst_questionnaire.type='" + type + "' AND mst_questionnaire.type_of_check='"
		 * + nsc + "' AND mst_questionnaire." + size +
		 * "!='0' AND mst_questionnaire.brand='" + brand + "' AND
		 * mst_questionnaire.type=mst_section.type ORDER BY mst_questionnaire.number
		 * ASC;
		 */

		System.out.println(
				"SELECT mst_questionnaire.*,mst_section.* FROM mst_questionnaire,mst_section WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_questionnaire.type='"
						+ outlet_type + "' AND mst_questionnaire.type_of_check='MS' AND mst_questionnaire."
						+ outlet_size + "!='0' AND mst_questionnaire.brand='" + brand
						+ "' AND mst_questionnaire.type=mst_section.type ORDER BY mst_questionnaire.number ASC;");
		return template.query(
				"SELECT mst_questionnaire.*,mst_section.* FROM mst_questionnaire,mst_section WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_questionnaire.type='"
						+ outlet_type + "' AND mst_questionnaire.type_of_check='MS' AND mst_questionnaire."
						+ outlet_size + "!='0' AND mst_questionnaire.brand='" + brand
						+ "' AND mst_questionnaire.type=mst_section.type ORDER BY mst_questionnaire.number ASC;;",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final QuestionBean qBean = new QuestionBean();
						qBean.setSection(rs.getString("section_name"));
						qBean.setSection_id(rs.getString("sk_section_id"));
						qBean.setStandard(rs.getString("standard"));
						qBean.setRequirement(rs.getString("requirement"));
						qBean.setComment(rs.getString("comment"));
						qBean.setNumber(rs.getString("number"));
						qBean.setXs(rs.getString("xs"));
						qBean.setS(rs.getString("s"));
						qBean.setM(rs.getString("m"));
						qBean.setL(rs.getString("l"));
						qBean.setXl(rs.getString("xl"));
						qBean.setXxl(rs.getString("xxl"));
						qBean.setCheck(rs.getString("type_of_check"));
						qBean.setEssential(rs.getString("essential"));
						qBean.setQuestion(rs.getString("question"));
						qBean.setObservation(rs.getString("observation"));
						qBean.setPerson(rs.getString("suggested_person"));
						qBean.setQuestionnaire_id(rs.getString("sk_questionnaire_id"));

						System.out.println("SELECT mst_audit.* FROM mst_audit WHERE mst_audit.question_id='"
								+ rs.getString("sk_questionnaire_id") + "'  AND mst_audit.audit_schedule_id='" + asid
								+ "';");
						template.query("SELECT mst_audit.* FROM mst_audit WHERE mst_audit.question_id='"
								+ rs.getString("sk_questionnaire_id") + "'  AND mst_audit.audit_schedule_id='" + asid
								+ "';", new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setAnswer(rs.getString("results"));
										qBean.setAudit_id(rs.getString("sk_audit_id"));
										if (rs.getString("results").equals("0")) {
											qBean.setAnswer_type("NO");
										} else {
											qBean.setAnswer_type("YES");
										}
										if (rs.getString("pmo_exception_remarks") == null) {
											qBean.setException_remarks("False");
										} else {
											qBean.setException_remarks("True");
										}
										return qBean;
									}
								});
						return qBean;
					}
				});
	}

	public QuestionBean checkAuditExist(final QuestionBean qBean, String question_id) {
		System.out.println("SELECT * FROM mst_audit WHERE audit_schedule_id='" + qBean.getAudit_schedule_id()
				+ "' AND question_id='" + question_id + "' LIMIT 1;");
		return template.queryForObject("SELECT * FROM mst_audit WHERE audit_schedule_id='"
				+ qBean.getAudit_schedule_id() + "' AND question_id='" + question_id + "' LIMIT 1;",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						return qBean;
					}
				});
	}

	public void addOutletImages(UserBean uBean, String file, String oid) {

		System.out.println(
				"INSERT INTO mst_image(image_for,file_path,reference_id,image_status, image_date) VALUES ('outlet','"
						+ file + "','" + oid + "','Active','" + dateFormat.format(date) + "');");
		template.execute(
				"INSERT INTO mst_image(image_for,file_path,reference_id,image_status, image_date) VALUES ('outlet','"
						+ file + "','" + oid + "','Active','" + dateFormat.format(date) + "');");

	}

	public List<UserBean> getOutletImages(UserBean uBean, String oid) {
		System.out.println("SELECT * FROM `mst_image` WHERE reference_id='" + oid + "' ");
		return template.query("SELECT * FROM `mst_image` WHERE reference_id='" + oid + "';", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean uBean = new UserBean();
				uBean.setSk_image_id(rs.getString("sk_image_id"));
				uBean.setImage_date(rs.getString("image_date"));
				uBean.setImage_for(rs.getString("image_for"));
				uBean.setFile_path(rs.getString("file_path"));
				uBean.setOutlet_id(rs.getString("reference_id"));
				uBean.setImage_status(rs.getString("image_status"));
				return uBean;
			}
		});
	}

	public void updateAuditSummary(UserBean uBean) {
		System.out.println("UPDATE mst_audit_schedule SET audit_summary='"
				+ uBean.getAudit_summary().replaceAll(",", "") + "',auditClose_date='" + dateFormat.format(date)
				+ "' , status='closed' WHERE sk_audit_id='" + uBean.getAudit_id() + "';");
		template.execute("UPDATE mst_audit_schedule SET audit_summary='" + uBean.getAudit_summary().replaceAll(",", "")
				+ "',auditClose_date='" + dateFormat.format(date) + "' , status='closed' WHERE sk_audit_id='"
				+ uBean.getAudit_id() + "';");

	}

	public void addEmailSettings(UserBean uBean, String user_id) {
		String count = "";
		String sql = "select count(user_id) as exist from mst_settings_email where user_id='" + user_id + "';";

		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
		}
		int id_status = Integer.parseInt(count);
		if (id_status > 0) {
			System.out.println("UPDATE mst_settings_email SET email='" + uBean.getEmail() + "',password='"
					+ uBean.getPassword() + "',smtp_host='" + uBean.getSmtp_host() + "',smtp_port='"
					+ uBean.getSmtp_port() + "',smtp_security_type='" + uBean.getSmtp_security_type()
					+ "',smtp_settings_for='" + uBean.getSmtp_settings_for() + "'  WHERE user_id='" + user_id + "';");
			template.execute("UPDATE mst_settings_email SET email='" + uBean.getEmail() + "',password='"
					+ uBean.getPassword() + "',smtp_host='" + uBean.getSmtp_host() + "',smtp_port='"
					+ uBean.getSmtp_port() + "',smtp_security_type='" + uBean.getSmtp_security_type()
					+ "',smtp_settings_for='" + uBean.getSmtp_settings_for() + "'  WHERE user_id='" + user_id + "';");
		} else {
			System.out.println(
					"INSERT INTO mst_settings_email(email,password,smtp_host,smtp_port, smtp_security_type,smtp_settings_for,smtp_status,user_id) VALUES ('"
							+ uBean.getEmail() + "','" + uBean.getPassword() + "','" + uBean.getSmtp_host() + "','"
							+ uBean.getSmtp_port() + "','" + uBean.getSmtp_security_type() + "','"
							+ uBean.getSmtp_settings_for() + "','active','" + user_id + "');");
			template.execute(
					"INSERT INTO mst_settings_email(email,password,smtp_host,smtp_port, smtp_security_type,smtp_settings_for,smtp_status,user_id) VALUES ('"
							+ uBean.getEmail() + "','" + uBean.getPassword() + "','" + uBean.getSmtp_host() + "','"
							+ uBean.getSmtp_port() + "','" + uBean.getSmtp_security_type() + "','"
							+ uBean.getSmtp_settings_for() + "','active','" + user_id + "');");

		}
	}

	public void addClientSettings(UserBean uBean, String file, String user_id) {
		String count = "";
		String sql = "select count(user_id) as exist from mst_client_settings where user_id='" + user_id + "';";

		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("UPDATE mst_client_settings SET client_name='" + uBean.getClient_name()
					+ "',client_logo='" + file + "',mobile='" + uBean.getMobile() + "',email='" + uBean.getEmail()
					+ "' WHERE user_id='" + user_id + "';");
			template.execute("UPDATE mst_client_settings SET client_name='" + uBean.getClient_name() + "',client_logo='"
					+ file + "',mobile='" + uBean.getMobile() + "',email='" + uBean.getEmail() + "' WHERE user_id='"
					+ user_id + "';");

		} else {
			System.out.println(
					"INSERT INTO mst_client_settings(client_name,client_logo,mobile,email,client_status,user_id) VALUES ('"
							+ uBean.getClient_name() + "','" + file + "','" + uBean.getMobile() + "','"
							+ uBean.getEmail() + "','active','" + user_id + "');");
			template.execute(
					"INSERT INTO mst_client_settings(client_name,client_logo,mobile,email,client_status,user_id) VALUES ('"
							+ uBean.getClient_name() + "','" + file + "','" + uBean.getMobile() + "','"
							+ uBean.getEmail() + "','active','" + user_id + "');");

		}

	}

	public void addSmsSettings(UserBean uBean, String user_id) {
		String count = "";
		String sql = "select count(user_id) as exist from mst_settings_email where user_id='" + user_id + "';";

		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
		}
		int id_status = Integer.parseInt(count);
		if (id_status > 0) {
			System.out.println("UPDATE mst_settings_sms SET sms_template='" + uBean.getSms_template() + "',user_name='"
					+ uBean.getUser_name() + "',password='" + uBean.getPassword() + "',sender_id='"
					+ uBean.getSender_id() + "',sms_for='" + uBean.getSms_for() + "' WHERE user_id='" + user_id + "';");
			template.execute("UPDATE mst_settings_sms SET sms_template='" + uBean.getSms_template() + "',user_name='"
					+ uBean.getUser_name() + "',password='" + uBean.getPassword() + "',sender_id='"
					+ uBean.getSender_id() + "',sms_for='" + uBean.getSms_for() + "' WHERE user_id='" + user_id + "';");
		} else {
			System.out.println(
					"INSERT INTO mst_settings_sms(sms_template,user_name,password,sender_id, sms_for,sms_status,user_id) VALUES ('"
							+ uBean.getSms_template() + "','" + uBean.getUser_name() + "','" + uBean.getPassword()
							+ "','" + uBean.getSender_id() + "','" + uBean.getSms_for() + "','active','" + user_id
							+ "');");
			template.execute(
					"INSERT INTO mst_settings_sms(sms_template,user_name,password,sender_id, sms_for,sms_status,user_id) VALUES ('"
							+ uBean.getSms_template() + "','" + uBean.getUser_name() + "','" + uBean.getPassword()
							+ "','" + uBean.getSender_id() + "','" + uBean.getSms_for() + "','active','" + user_id
							+ "');");

		}

	}

	public UserBean getEmailSettingInfo(final UserBean uBean, String user_id) {
		System.out.println(
				"SELECT * FROM mst_settings_email WHERE user_id='" + user_id + "' and smtp_status='active' LIMIT 1;");
		return template.queryForObject(
				"SELECT * FROM mst_settings_email WHERE user_id='" + user_id + "' and smtp_status='active' LIMIT 1;",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						uBean.setPassword(rs.getString("password"));
						uBean.setSmtp_host(rs.getString("smtp_host"));
						uBean.setSmtp_port(rs.getString("smtp_port"));
						uBean.setSmtp_security_type(rs.getString("smtp_security_type"));
						uBean.setSmtp_settings_for(rs.getString("smtp_settings_for"));
						uBean.setUser_id(rs.getString("user_id"));
						uBean.setEmail(rs.getString("email"));
						return uBean;
					}
				});
	}

	public UserBean getClientSettingInfo(final UserBean uBean, String user_id) {
		System.out.println("SELECT * FROM mst_client_settings WHERE user_id='" + user_id
				+ "' and client_status='active' LIMIT 1;");
		return template.queryForObject(
				"SELECT * FROM mst_client_settings WHERE user_id='" + user_id + "' and client_status='active' LIMIT 1;",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						uBean.setClient_name(rs.getString("client_name"));
						System.out.println(rs.getString("client_name"));
						uBean.setClient_logo(rs.getString("client_logo"));
						uBean.setMobile(rs.getString("mobile"));
						uBean.setEmail(rs.getString("email"));
						uBean.setUser_id(rs.getString("user_id"));
						return uBean;
					}
				});
	}

	public UserBean getSmsSettingInfo(final UserBean uBean, String user_id) {
		System.out.println(
				"SELECT * FROM mst_settings_sms WHERE user_id='" + user_id + "'and sms_status='active' LIMIT 1;");
		return template.queryForObject(
				"SELECT * FROM mst_settings_sms WHERE user_id='" + user_id + "' and sms_status='active' LIMIT 1;",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						uBean.setSms_template(rs.getString("sms_template"));
						uBean.setUser_name(rs.getString("user_name"));
						uBean.setPassword(rs.getString("password"));
						uBean.setSender_id(rs.getString("sender_id"));
						uBean.setSms_for(rs.getString("sms_for"));
						uBean.setUser_id(rs.getString("user_id"));
						return uBean;
					}
				});
	}

	String comment_type = "";

	public List<QuestionBean> getQuestionnaireOptionById(QuestionBean qBean, String user_id, String user_type,
			String audit_id) {

		if (user_type.equals("1")) {
			comment_type = "auditor_comment";
		}
		if (user_type.equals("2")) {
			comment_type = "auditor_comment";
		}
		if (user_type.equals("3")) {
			comment_type = "EYI_comment";
		}
		System.out.println("SELECT * FROM mst_audit WHERE sk_audit_id='" + audit_id + "';");
		return template.query("SELECT * FROM mst_audit  WHERE sk_audit_id='" + audit_id + "';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						qBean.setAnswer(rs.getString("results"));
						qBean.setAudit_id(rs.getString("sk_audit_id"));
						qBean.setComment(rs.getString("pmo_closure_comment"));
						qBean.setBadge(rs.getString("badge"));
						qBean.setReview_status(rs.getString("pmo_review_status"));
						qBean.setAuditor_comment(rs.getString("auditor_comment"));
						qBean.setDealer_comment(rs.getString("dealer_comment"));
						qBean.setEnd_date(rs.getString("exception_timeline"));
						qBean.setException_remarks(rs.getString("pmo_exception_remarks"));
						if (rs.getString("results").equals("0")) {
							qBean.setAnswer_type("NO");
						} else {
							qBean.setAnswer_type("YES");
						}
						return qBean;
					}
				});
	}

	public void updateCommentsById(QuestionBean qBean, String user_type) {
		String comment_type = "";
		if (user_type.equals("1")) {
			comment_type = "auditor_comment";
		}
		if (user_type.equals("2")) {
			comment_type = "auditor_comment";
		}
		if (user_type.equals("3")) {
			comment_type = "EYI_comment";
		}
		System.out.println("UPDATE mst_audit SET results='" + qBean.getAnswer() + "',pmo_closure_comment='"
				+ qBean.getComment() + "',badge='" + qBean.getBadge() + "',pmo_review_status='"
				+ qBean.getReview_status() + "',pmo_review_date='" + dateFormat.format(date) + "',auditor_comment='"+qBean.getAuditor_comment()+"',dealer_comment='"+qBean.getDealer_comment()+"' WHERE sk_audit_id='"
				+ qBean.getAudit_id() + "' ");
		template.execute("UPDATE mst_audit SET results='" + qBean.getAnswer() + "',pmo_closure_comment='"
				+ qBean.getComment() + "',badge='" + qBean.getBadge() + "',pmo_review_status='"
				+ qBean.getReview_status() + "',pmo_review_date='" + dateFormat.format(date) + "',auditor_comment='"+qBean.getAuditor_comment()+"',dealer_comment='"+qBean.getDealer_comment()+"' WHERE sk_audit_id='"
				+ qBean.getAudit_id() + "'");

	}
	
	public void updateCommentsofPMO(QuestionBean qBean, String user_type) {
		String comment_type = "";
		if (user_type.equals("1")) {
			comment_type = "auditor_comment";
		}
		if (user_type.equals("2")) {
			comment_type = "auditor_comment";
		}
		if (user_type.equals("3")) {
			comment_type = "EYI_comment";
		}
		System.out.println("UPDATE mst_audit SET results='" + qBean.getAnswer() + "',pmo_closure_comment='"
				+ qBean.getComment() + "',badge='" + qBean.getBadge() + "',pmo_review_status='"
				+ qBean.getReview_status() + "',pmo_review_date='" + dateFormat.format(date) + "',pmo_exception_remarks='"+qBean.getException_remarks()+"' WHERE sk_audit_id='"
				+ qBean.getAudit_id() + "' ");
		template.execute("UPDATE mst_audit SET results='" + qBean.getAnswer() + "',pmo_closure_comment='"
				+ qBean.getComment() + "',badge='" + qBean.getBadge() + "',pmo_review_status='"
				+ qBean.getReview_status() + "',pmo_review_date='" + dateFormat.format(date) + "',pmo_exception_remarks='"+qBean.getException_remarks()+"'  WHERE sk_audit_id='"
				+ qBean.getAudit_id() + "'");

	}

	public void updatePMOComments(QuestionBean qBean) {
		if (qBean.getEnd_date().isEmpty()) {
			System.out.println("UPDATE mst_audit SET results='" + qBean.getAnswer() + "',pmo_exception_remarks='"
					+ qBean.getException_remarks().replace("'", "\\'") + "',auditor_comment='"+qBean.getAuditor_comment().replace("'", "\\'")+"',dealer_comment='"+qBean.getDealer_comment().replace("'", "\\'")+"'  WHERE sk_audit_id='" + qBean.getAudit_id() + "'");
			template.execute("UPDATE mst_audit SET results='" + qBean.getAnswer() + "',pmo_exception_remarks='"
					+ qBean.getException_remarks().replace("'", "\\'") + "',auditor_comment='"+qBean.getAuditor_comment().replace("'", "\\'")+"',dealer_comment='"+qBean.getDealer_comment().replace("'", "\\'")+"'  WHERE sk_audit_id='" + qBean.getAudit_id() + "'");
		} else {
			System.out.println("UPDATE mst_audit SET results='" + qBean.getAnswer() + "',pmo_exception_remarks='"
					+ qBean.getException_remarks().replace("'", "\\'") + "',exception_timeline='" + qBean.getEnd_date()
					+ "',auditor_comment='"+qBean.getAuditor_comment().replace("'", "\\'")+"',dealer_comment='"+qBean.getDealer_comment().replace("'", "\\'")+"' WHERE sk_audit_id='" + qBean.getAudit_id() + "'");
			template.execute("UPDATE mst_audit SET results='" + qBean.getAnswer() + "',pmo_exception_remarks='"
					+ qBean.getException_remarks().replace("'", "\\'") + "',exception_timeline='" + qBean.getEnd_date()
					+ "',auditor_comment='"+qBean.getAuditor_comment().replace("'", "\\'")+"',dealer_comment='"+qBean.getDealer_comment().replace("'", "\\'")+"' WHERE sk_audit_id='" + qBean.getAudit_id() + "'");
			/*String first_notify = "";
			String second_notify = "";
			String third_notify = "";
			String fourth_notify = "";
			try {

				int d2 = -30;
				int d3 = -15;
				int d4 = 1;
				int d5 = 2;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(qBean.getEnd_date())); // Now use today date.
				c.add(Calendar.DATE, d2); // Adding -30 days
				first_notify = sdf.format(c.getTime());

				Calendar c2 = Calendar.getInstance();
				c2.setTime(sdf.parse(qBean.getEnd_date()));
				c2.add(Calendar.DATE, d3); // Adding -15 days
				second_notify = sdf.format(c2.getTime());

				Calendar c3 = Calendar.getInstance();
				c3.setTime(sdf.parse(qBean.getEnd_date()));
				c3.add(Calendar.DATE, d4); // Adding 1 days
				third_notify = sdf.format(c3.getTime());
				Calendar c4 = Calendar.getInstance();
				c4.setTime(sdf.parse(qBean.getEnd_date()));
				c4.add(Calendar.DATE, d5); // Adding 2 days
				fourth_notify = sdf.format(c4.getTime());

			
			System.out.println(
					"INSERT INTO mst_notification_scheduler (audit_id,first_notification,second_notification,third_notification,fourth_notification,fifth_notification) VALUES ('"
							+ qBean.getAudit_id() + "','" + first_notify + "','" + second_notify + "','"
							+ qBean.getEnd_date() + "','" + third_notify + "','" + fourth_notify + "')");
			template.execute(
					"INSERT INTO mst_notification_scheduler (audit_id,first_notification,second_notification,third_notification,fourth_notification,fifth_notification) VALUES ('"
							+ qBean.getAudit_id() + "','" + first_notify + "','" + second_notify + "','"
							+ qBean.getEnd_date() + "','" + third_notify + "','" + fourth_notify + "')");
			} catch (Exception e) {
				System.out.println(e + "DATES ERROR");
			}*/


		}

	}

	public UserBean getDealersCount(final UserBean uBean) {
		System.out
				.println("SELECT COUNT(sk_dealership_id) as dealers_count FROM mst_dealership WHERE status='active';");
		return template.queryForObject(
				"SELECT COUNT(sk_dealership_id) as dealers_count FROM mst_dealership WHERE status='active'",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						uBean.setCount_dealers(rs.getString("dealers_count"));
						return uBean;
					}
				});
	}

	public UserBean getOutletsCount(final UserBean uBean) {
		System.out.println("SELECT COUNT(sk_outlet_id) as outlet_count FROM mst_outlet WHERE status='active';");
		return template.queryForObject(
				"SELECT COUNT(sk_outlet_id) as outlet_count  FROM mst_outlet WHERE status='active'",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						uBean.setCount_outlets(rs.getString("outlet_count"));
						return uBean;
					}
				});
	}

	public UserBean getSheculedAuditsCount(final UserBean uBean) {
		System.out.println("SELECT COUNT(sk_audit_id) as audit_count FROM mst_audit_schedule WHERE status='active';");
		return template.queryForObject(
				"SELECT COUNT(sk_audit_id) as audit_count FROM mst_audit_schedule WHERE status='active';",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						uBean.setAudits_count(rs.getString("audit_count"));
						return uBean;
					}
				});
	}

	public UserBean getRegionById(final UserBean uBean, String region_id) {
		System.out.println("SELECT * FROM mst_region WHERE sk_region_id='" + region_id + "';");
		return template.queryForObject("SELECT * FROM mst_region WHERE sk_region_id='" + region_id + "';",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						uBean.setRegion(rs.getString("region_name"));
						uBean.setRegion_id(rs.getString("sk_region_id"));
						return uBean;
					}
				});
	}

	public List<QuestionBean> getDealersByOutlet(QuestionBean qBean, String outlet_type) {
		System.out.println(
				"SELECT  mst_outlet.*,mst_dealership.dealership_name FROM mst_outlet,mst_dealership WHERE  mst_outlet.outlet_type='"
						+ outlet_type + "' AND mst_outlet.dealership_id=mst_dealership.sk_dealership_id");
		return template.query(
				"SELECT  mst_outlet.*,mst_dealership.dealership_name FROM mst_outlet,mst_dealership WHERE  mst_outlet.outlet_type='"
						+ outlet_type + "' AND mst_outlet.dealership_id=mst_dealership.sk_dealership_id;",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						qBean.setDealer_id(rs.getString("dealership_id"));
						qBean.setDealer(rs.getString("dealership_name"));
						return qBean;
					}
				});
	}

	public List<UserBean> getAuditorList(UserBean uBean) {
		System.out.println("SELECT * FROM mst_users WHERE user_type='1';");
		return template.query("SELECT * FROM mst_users WHERE user_type='1';", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean uBean = new UserBean();
				uBean.setUser_type_id(rs.getString("user_type"));
				uBean.setOutlets(rs.getString("outlets"));
				uBean.setFirst_name(rs.getString("first_name"));
				uBean.setLast_name(rs.getString("last_name"));
				uBean.setEmail(rs.getString("email"));
				uBean.setMobile(rs.getString("mobile"));
				uBean.setPassword(rs.getString("password"));
				uBean.setRole(rs.getString("role"));
				uBean.setUser_id(rs.getString("sk_user_id"));
				return uBean;

			}
		});

	}

	public void addState(UserBean uBean) {
		System.out.println(
				"INSERT INTO mst_states(state_name,status) VALUES ('" + uBean.getState_name() + "','active');");
		template.execute(
				"INSERT INTO mst_states(state_name,status) VALUES ('" + uBean.getState_name() + "','active');");

	}

	public List<UserBean> getStateList(UserBean uBean) {
		System.out.println("SELECT * FROM mst_states where status='active';");
		return template.query("SELECT * FROM `mst_states`where status='active' ;", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean uBean = new UserBean();
				uBean.setState_id(rs.getString("sk_state_id"));
				uBean.setState_name(rs.getString("state_name"));
				return uBean;
			}
		});
	}

	public UserBean getStateDetailsById(final UserBean uBean, String sid) {
		System.out.println("SELECT * FROM mst_states WHERE sk_state_id='" + sid + "';");
		return template.queryForObject("SELECT * FROM mst_states WHERE sk_state_id='" + sid + "';",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						uBean.setState_id(rs.getString("sk_state_id"));
						uBean.setState_name(rs.getString("state_name"));
						return uBean;

					}

				});

	}

	public void deleteStateById(UserBean uBean, String sid) {
		System.out.println("DELETE FROM mst_states WHERE sk_state_id='" + sid + "'");
		template.execute("DELETE FROM mst_states WHERE sk_state_id='" + sid + "'");
	}

	public void updatStateById(UserBean uBean, String sid) {
		System.out.println(
				"UPDATE mst_states SET state_name='" + uBean.getState_name() + "' WHERE sk_state_id='" + sid + "'");
		template.execute(
				"UPDATE mst_states SET state_name='" + uBean.getState_name() + "' WHERE sk_state_id='" + sid + "'");
	}

	public void deleteCityById(UserBean uBean, String cid) {
		System.out.println("DELETE FROM mst_city WHERE sk_city_id='" + cid + "'");
		template.execute("DELETE FROM mst_city WHERE sk_city_id='" + cid + "'");
	}

	public void updateCityById(UserBean uBean, String cid) {
		System.out.println("UPDATE mst_city SET city_name='" + uBean.getCity_name() + "',state_id='"
				+ uBean.getState_id() + "' WHERE sk_city_id='" + cid + "'");
		template.execute("UPDATE mst_city SET city_name='" + uBean.getCity_name() + "',state_id='" + uBean.getState_id()
				+ "' WHERE sk_city_id='" + cid + "'");
	}

	public UserBean getCityDetailsById(final UserBean uBean, String cid) {
		System.out.println(
				"SELECT mst_city.sk_city_id,mst_city.city_name,mst_states.sk_state_id,mst_states.state_name FROM  mst_city,mst_states WHERE mst_states.sk_state_id=mst_city.state_id AND mst_city.status='active' AND mst_states.status='active' AND mst_city.sk_city_id='"
						+ cid + "';");
		return template.queryForObject(
				"SELECT mst_city.sk_city_id,mst_city.city_name,mst_states.sk_state_id,mst_states.state_name FROM  mst_city,mst_states WHERE mst_states.sk_state_id=mst_city.state_id AND mst_city.status='active' AND mst_states.status='active' AND mst_city.sk_city_id='"
						+ cid + "';",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						uBean.setState_id(rs.getString("sk_state_id"));
						uBean.setState_name(rs.getString("state_name"));
						uBean.setCity_id(rs.getString("sk_city_id"));
						uBean.setCity_name(rs.getString("city_name"));
						return uBean;

					}

				});

	}

	public void addCity(UserBean uBean) {
		System.out.println("INSERT INTO mst_city(city_name,state_id,status) VALUES ('" + uBean.getCity_name() + "','"
				+ uBean.getState_id() + "','active');");
		template.execute("INSERT INTO mst_city(city_name,state_id,status) VALUES ('" + uBean.getCity_name() + "','"
				+ uBean.getState_id() + "','active');");

	}

	public List<UserBean> getCityList(UserBean uBean) {
		System.out.println(
				"SELECT mst_city.sk_city_id,mst_city.city_name,mst_states.sk_state_id,mst_states.state_name FROM  mst_city,mst_states WHERE mst_states.sk_state_id=mst_city.state_id AND mst_city.status='active' AND mst_states.status='active';");
		return template.query(
				"SELECT mst_city.sk_city_id,mst_city.city_name,mst_states.sk_state_id,mst_states.state_name FROM  mst_city,mst_states WHERE mst_states.sk_state_id=mst_city.state_id AND mst_city.status='active' AND mst_states.status='active' ;",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						UserBean uBean = new UserBean();
						uBean.setState_id(rs.getString("sk_state_id"));
						uBean.setState_name(rs.getString("state_name"));
						uBean.setCity_id(rs.getString("sk_city_id"));
						uBean.setCity_name(rs.getString("city_name"));
						return uBean;
					}
				});
	}

	public List<UserBean> getCities(UserBean uBean, String state_id) {
		System.out.println("SELECT * FROM mst_city WHERE state_id='" + state_id + "';");
		return template.query("SELECT * FROM mst_city WHERE state_id='" + state_id + "' ;", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean uBean = new UserBean();
				uBean.setCity_id(rs.getString("sk_city_id"));
				uBean.setCity_name(rs.getString("city_name"));
				return uBean;
			}
		});
	}

	public List<QuestionBean> getSectionsByType(QuestionBean qBean, String type, String brand) {
		System.out.println(
				"SELECT * FROM mst_section WHERE type='" + type + "' AND brand='" + brand + "' ORDER BY section_name");
		return template.query(
				"SELECT * FROM mst_section WHERE type='" + type + "' AND brand='" + brand + "' ORDER BY section_name;",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						qBean.setSection(rs.getString("section_name"));
						qBean.setSection_id(rs.getString("sk_section_id"));
						return qBean;
					}
				});
	}

	public MenuBean getroleMenuById(final MenuBean mBean, String role) {
		System.out.println("SELECT * FROM mst_roles WHERE sk_role_id='" + role + "';");
		return template.queryForObject("SELECT * FROM mst_roles WHERE sk_role_id='" + role + "';",
				new RowMapper<MenuBean>() {
					public MenuBean mapRow(ResultSet rs, int row) throws SQLException {
						mBean.setMenu(rs.getString("menu_list"));
						return mBean;
					}
				});
	}

	public List<UserBean> getOutletListByBrandAndType(UserBean uBean, String brand, String type) {
		System.out.println("SELECT * FROM mst_outlet WHERE outlet_type='" + type + "' AND brand='" + brand + "'");
		return template.query("SELECT * FROM mst_outlet WHERE outlet_type='" + type + "' AND brand='" + brand + "';",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						UserBean uBean = new UserBean();
						uBean.setOutlet_id(rs.getString("sk_outlet_id"));
						uBean.setOutlets(rs.getString("outlet_name"));
						return uBean;
					}
				});
	}

	public List<UserBean> getModelsByBrand(UserBean uBean, String brand) {
		System.out.println("SELECT * FROM mys_mst_brand_models WHERE brand='" + brand + "'");
		return template.query("SELECT * FROM mys_mst_brand_models WHERE brand='" + brand + "';",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						UserBean uBean = new UserBean();
						uBean.setModel_id(rs.getString("sk_model_id"));
						uBean.setModel(rs.getString("type") + " - " + rs.getString("model"));
						return uBean;
					}
				});
	}

	public void addMysteryShoppers(UserBean uBean) {
		System.out.println(
				"INSERT INTO mys_mst_shopper_details(name,age,email,contact_number,gender,quarter,year,mode_of_contact,visit_date,brand,type,outlet,brand_model)"
						+ "VALUES('" + uBean.getFirst_name() + "','" + uBean.getAge() + "','" + uBean.getEmail() + "','"
						+ uBean.getMobile() + "','" + uBean.getGender() + "','" + uBean.getQuarter() + "'," + "'"
						+ uBean.getYear() + "','" + uBean.getMode_of_contact() + "','" + uBean.getVisit_date() + "','"
						+ uBean.getBrand() + "','" + uBean.getType() + "','" + uBean.getOutlets() + "','"
						+ uBean.getModel() + "');");
		template.execute(
				"INSERT INTO mys_mst_shopper_details(name,age,email,contact_number,gender,quarter,year,mode_of_contact,visit_date,brand,type,outlet,brand_model)"
						+ "VALUES('" + uBean.getFirst_name() + "','" + uBean.getAge() + "','" + uBean.getEmail() + "','"
						+ uBean.getMobile() + "','" + uBean.getGender() + "','" + uBean.getQuarter() + "'," + "'"
						+ uBean.getYear() + "','" + uBean.getMode_of_contact() + "','" + uBean.getVisit_date() + "','"
						+ uBean.getBrand() + "','" + uBean.getType() + "','" + uBean.getOutlets() + "','"
						+ uBean.getModel() + "');");

	}

	public List<UserBean> getMysteryShoppersList(UserBean uBean) {
		System.out.println("SELECT * FROM mys_mst_shopper_details WHERE status='active';");
		return template.query("SELECT * FROM mys_mst_shopper_details WHERE status='active';",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						UserBean uBean = new UserBean();
						uBean.setUser_id(rs.getString("sk_shopper_id"));
						uBean.setFirst_name(rs.getString("name"));
						uBean.setAge(rs.getString("age"));
						uBean.setEmail(rs.getString("email"));
						uBean.setMobile(rs.getString("contact_number"));
						uBean.setGender(rs.getString("gender"));
						uBean.setQuarter(rs.getString("quarter"));
						uBean.setYear(rs.getString("year"));
						uBean.setMode_of_contact(rs.getString("mode_of_contact"));
						uBean.setVisit_date(rs.getString("visit_date"));
						uBean.setBrand(rs.getString("brand"));
						uBean.setType(rs.getString("type"));
						uBean.setOutlet_id(rs.getString("outlet"));
						uBean.setModel_id(rs.getString("brand_model"));

						return uBean;
					}
				});
	}

	public UserBean getMysteryShoppersById(final UserBean uBean, String uid) {
		System.out.println("SELECT * FROM mys_mst_shopper_details WHERE sk_shopper_id='" + uid + "';");
		return template.queryForObject("SELECT * FROM mys_mst_shopper_details WHERE sk_shopper_id='" + uid + "';",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						uBean.setUser_id(rs.getString("sk_shopper_id"));
						uBean.setFirst_name(rs.getString("name"));
						uBean.setAge(rs.getString("age"));
						uBean.setEmail(rs.getString("email"));
						uBean.setMobile(rs.getString("contact_number"));
						uBean.setGender(rs.getString("gender"));
						uBean.setQuarter(rs.getString("quarter"));
						uBean.setYear(rs.getString("year"));
						uBean.setMode_of_contact(rs.getString("mode_of_contact"));
						uBean.setVisit_date(rs.getString("visit_date"));
						uBean.setBrand(rs.getString("brand"));
						uBean.setType(rs.getString("type"));
						uBean.setOutlet_id(rs.getString("outlet"));
						uBean.setModel_id(rs.getString("brand_model"));

						return uBean;
					}
				});
	}

	public UserBean getBrandModelsById(final UserBean uBean, String model_id) {
		System.out.println("SELECT * FROM mys_mst_brand_models WHERE sk_model_id='" + model_id + "'");
		return template.queryForObject("SELECT * FROM mys_mst_brand_models WHERE sk_model_id='" + model_id + "';",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						uBean.setModel_id(rs.getString("sk_model_id"));
						uBean.setModel(rs.getString("type") + " - " + rs.getString("model"));
						return uBean;
					}
				});
	}

	public void updateMysteryShoppers(UserBean uBean, String uid) {
		System.out.println("UPDATE mys_mst_shopper_details SET name='" + uBean.getFirst_name() + "',age='"
				+ uBean.getAge() + "',email='" + uBean.getEmail() + "',contact_number='" + uBean.getMobile() + "',"
				+ "gender='" + uBean.getGender() + "',quarter='" + uBean.getQuarter() + "',year='" + uBean.getYear()
				+ "',mode_of_contact='" + uBean.getMode_of_contact() + "',visit_date='" + uBean.getVisit_date()
				+ "',brand='" + uBean.getBrand() + "',type='" + uBean.getType() + "',outlet='" + uBean.getOutlets()
				+ "'," + "brand_model='" + uBean.getModel() + "' WHERE sk_shopper_id='" + uid + "'");
		template.execute("UPDATE mys_mst_shopper_details SET name='" + uBean.getFirst_name() + "',age='"
				+ uBean.getAge() + "',email='" + uBean.getEmail() + "',contact_number='" + uBean.getMobile() + "',"
				+ "gender='" + uBean.getGender() + "',quarter='" + uBean.getQuarter() + "',year='" + uBean.getYear()
				+ "',mode_of_contact='" + uBean.getMode_of_contact() + "',visit_date='" + uBean.getVisit_date()
				+ "',brand='" + uBean.getBrand() + "',type='" + uBean.getType() + "',outlet='" + uBean.getOutlets()
				+ "'," + "brand_model='" + uBean.getModel() + "' WHERE sk_shopper_id='" + uid + "'");

	}

	public void deleteMysteryShoppers(UserBean uBean, String uid) {
		System.out.println("DELETE FROM mys_mst_shopper_details WHERE sk_shopper_id='" + uid + "'");
		template.execute("DELETE FROM mys_mst_shopper_details WHERE sk_shopper_id='" + uid + "'");
	}

	public void addModel(QuestionBean qBean) {
		System.out.println("INSERT INTO mys_brand_models (brand,type,model) VALUES ('" + qBean.getBrand() + "','"
				+ qBean.getType() + "','" + qBean.getModel() + "')");
		template.execute("INSERT INTO mys_brand_models (brand,type,model) VALUES ('" + qBean.getBrand() + "','"
				+ qBean.getType() + "','" + qBean.getModel() + "')");

	}

	public List<QuestionBean> getModels(QuestionBean qBean) {
		System.out.println("SELECT * FROM mys_brand_models WHERE status='active'");
		return template.query("SELECT * FROM mys_brand_models WHERE status='active';", new RowMapper<QuestionBean>() {
			public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
				QuestionBean qBean = new QuestionBean();
				qBean.setBrand(rs.getString("brand"));
				qBean.setType(rs.getString("type"));
				qBean.setModel(rs.getString("model"));
				qBean.setModel_id(rs.getString("sk_model_id"));
				return qBean;
			}
		});
	}

	public QuestionBean getModelsById(final QuestionBean qBean, String mid) {
		System.out.println("SELECT * FROM mys_brand_models WHERE sk_model_id='" + mid + "'");
		return template.queryForObject("SELECT * FROM mys_brand_models WHERE sk_model_id='" + mid + "';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setBrand(rs.getString("brand"));
						qBean.setType(rs.getString("type"));
						qBean.setModel(rs.getString("model"));
						qBean.setModel_id(rs.getString("sk_model_id"));
						return qBean;
					}
				});
	}

	public void updateModel(QuestionBean qBean, String mid) {
		System.out.println("UPDATE mys_brand_models SET brand='" + qBean.getBrand() + "',type='" + qBean.getType()
				+ "',model='" + qBean.getModel() + "' WHERE sk_model_id='" + mid + "'");
		template.execute("UPDATE mys_brand_models SET brand='" + qBean.getBrand() + "',type='" + qBean.getType()
				+ "',model='" + qBean.getModel() + "' WHERE sk_model_id='" + mid + "'");
	}

	public void deleteModelById(QuestionBean qBean, String mid) {
		System.out.println("DELETE FROM mys_brand_models WHERE sk_model_id='" + mid + "'");
		template.execute("DELETE FROM mys_brand_models WHERE sk_model_id='" + mid + "'");

	}

	public List<QuestionBean> getAuditScoreOf1(final String asid, String oid, String brand, String outlet_size,
			String outlet_type, final QuestionBean qBean) {

		System.out.println(
				"SELECT mst_audit.audit_schedule_id,mst_questionnaire.number,SUM(mst_audit.results) as result,COUNT(mst_questionnaire.sk_questionnaire_id) as question,TRUNCATE(SUM(mst_audit.results)/COUNT(mst_questionnaire.sk_questionnaire_id),0) as average FROM `mst_audit` LEFT JOIN mst_questionnaire ON mst_questionnaire.sk_questionnaire_id=mst_audit.question_id WHERE audit_schedule_id="
						+ asid + " AND mst_audit.essential!='O' AND mst_questionnaire." + outlet_size
						+ "!='0' GROUP BY mst_questionnaire.number HAVING average=1");
		return template.query(
				"SELECT mst_audit.audit_schedule_id,mst_questionnaire.number,SUM(mst_audit.results) as result,COUNT(mst_questionnaire.sk_questionnaire_id) as question,TRUNCATE(SUM(mst_audit.results)/COUNT(mst_questionnaire.sk_questionnaire_id),0) as average FROM `mst_audit` LEFT JOIN mst_questionnaire ON mst_questionnaire.sk_questionnaire_id=mst_audit.question_id WHERE audit_schedule_id="
						+ asid + " AND mst_audit.essential!='O' AND mst_questionnaire." + outlet_size
						+ "!='0' GROUP BY mst_questionnaire.number HAVING average=1",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {

						qBean.setResult(rs.getString("average"));

						return qBean;
					}
				});
	}

	public List<QuestionBean> getAuditScoreOf0(final String asid, String oid, String brand, String outlet_size,
			String outlet_type, final QuestionBean qBean) {

		System.out.println(
				"SELECT mst_audit.audit_schedule_id,mst_questionnaire.number,SUM(mst_audit.results) as result,COUNT(mst_questionnaire.sk_questionnaire_id) as question,TRUNCATE(SUM(mst_audit.results)/COUNT(mst_questionnaire.sk_questionnaire_id),0) as average FROM `mst_audit` LEFT JOIN mst_questionnaire ON mst_questionnaire.sk_questionnaire_id=mst_audit.question_id WHERE audit_schedule_id="
						+ asid + " AND mst_audit.essential!='O' AND mst_questionnaire." + outlet_size
						+ "!='0' GROUP BY mst_questionnaire.number HAVING average=0");
		return template.query(
				"SELECT mst_audit.audit_schedule_id,mst_questionnaire.number,SUM(mst_audit.results) as result,COUNT(mst_questionnaire.sk_questionnaire_id) as question,TRUNCATE(SUM(mst_audit.results)/COUNT(mst_questionnaire.sk_questionnaire_id),0) as average FROM `mst_audit` LEFT JOIN mst_questionnaire ON mst_questionnaire.sk_questionnaire_id=mst_audit.question_id WHERE audit_schedule_id="
						+ asid + " AND mst_audit.essential!='O' AND mst_questionnaire." + outlet_size
						+ "!='0' GROUP BY mst_questionnaire.number HAVING average=0",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {

						qBean.setResult(rs.getString("average"));

						return qBean;
					}
				});
	}

	public List<QuestionBean> getAuditScoreOf1ess(String asid, String oid, String brand, String outlet_size,
			String outlet_type, final QuestionBean qBean) {
		System.out.println(
				"SELECT mst_audit.audit_schedule_id,mst_questionnaire.number,SUM(mst_audit.results) as result,COUNT(mst_questionnaire.sk_questionnaire_id) as question,TRUNCATE(SUM(mst_audit.results)/COUNT(mst_questionnaire.sk_questionnaire_id),0) as average FROM `mst_audit` LEFT JOIN mst_questionnaire ON mst_questionnaire.sk_questionnaire_id=mst_audit.question_id WHERE audit_schedule_id="
						+ asid + " AND mst_audit.essential='X' GROUP BY mst_questionnaire.number HAVING average=1");
		return template.query(
				"SELECT mst_audit.audit_schedule_id,mst_questionnaire.number,SUM(mst_audit.results) as result,COUNT(mst_questionnaire.sk_questionnaire_id) as question,TRUNCATE(SUM(mst_audit.results)/COUNT(mst_questionnaire.sk_questionnaire_id),0) as average FROM `mst_audit` LEFT JOIN mst_questionnaire ON mst_questionnaire.sk_questionnaire_id=mst_audit.question_id WHERE audit_schedule_id="
						+ asid + " AND mst_audit.essential='X' GROUP BY mst_questionnaire.number HAVING average=1",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {

						qBean.setResult(rs.getString("average"));

						return qBean;
					}
				});
	}

	public List<QuestionBean> getAuditScoreOf0ess(String asid, String oid, String brand, String outlet_size,
			String outlet_type, final QuestionBean qBean) {
		System.out.println(
				"SELECT mst_audit.audit_schedule_id,mst_questionnaire.number,SUM(mst_audit.results) as result,COUNT(mst_questionnaire.sk_questionnaire_id) as question,TRUNCATE(SUM(mst_audit.results)/COUNT(mst_questionnaire.sk_questionnaire_id),0) as average FROM `mst_audit` LEFT JOIN mst_questionnaire ON mst_questionnaire.sk_questionnaire_id=mst_audit.question_id WHERE audit_schedule_id="
						+ asid + " AND mst_audit.essential='X' GROUP BY mst_questionnaire.number HAVING average=0");
		return template.query(
				"SELECT mst_audit.audit_schedule_id,mst_questionnaire.number,SUM(mst_audit.results) as result,COUNT(mst_questionnaire.sk_questionnaire_id) as question,TRUNCATE(SUM(mst_audit.results)/COUNT(mst_questionnaire.sk_questionnaire_id),0) as average FROM `mst_audit` LEFT JOIN mst_questionnaire ON mst_questionnaire.sk_questionnaire_id=mst_audit.question_id WHERE audit_schedule_id="
						+ asid + " AND mst_audit.essential='X' GROUP BY mst_questionnaire.number HAVING average=0",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {

						qBean.setResult(rs.getString("average"));

						return qBean;
					}
				});
	}

	public QuestionBean getTotalNscQuestionCount(String asid, final QuestionBean qBean, String brand,
			String outlet_type, String outlet_size) {
		System.out.println("SELECT COUNT(*) as nsc_count FROM mst_questionnaire WHERE brand='" + brand + "' AND type='"
				+ outlet_type + "' AND " + outlet_size + "!='0' AND type_of_check='NSC'");
		return template.queryForObject(
				"SELECT COUNT(*) as nsc_count FROM mst_questionnaire WHERE brand='" + brand + "' AND type='"
						+ outlet_type + "' AND " + outlet_size + "!='0' AND type_of_check='NSC';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setNsc_count(rs.getString("nsc_count"));
						return qBean;
					}
				});
	}

	public QuestionBean getTotalNscQuestionAnsweredCount(String asid, final QuestionBean qBean, String brand,
			String outlet_type, String outlet_size) {
		System.out.println("SELECT COUNT(*) as nsc_count FROM mst_audit WHERE audit_schedule_id='" + asid
				+ "' AND type_of_check='NSC'");
		return template.queryForObject("SELECT COUNT(*) as nsc_count FROM mst_audit WHERE audit_schedule_id='" + asid
				+ "' AND type_of_check='NSC';", new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setNsc_count_ans(rs.getString("nsc_count"));
						return qBean;
					}
				});
	}

	public QuestionBean getTotalMSQuestionCount(String asid, final QuestionBean qBean, String brand, String outlet_type,
			String outlet_size) {
		System.out.println("SELECT COUNT(*) as ms_count FROM mst_questionnaire WHERE brand='" + brand + "' AND type='"
				+ outlet_type + "' AND " + outlet_size + "!='0' AND type_of_check='MS'");
		return template.queryForObject(
				"SELECT COUNT(*) as ms_count FROM mst_questionnaire WHERE brand='" + brand + "' AND type='"
						+ outlet_type + "' AND " + outlet_size + "!='0' AND type_of_check='MS';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setMs_count(rs.getString("ms_count"));
						return qBean;
					}
				});
	}

	public QuestionBean getTotalMSQuestionAnsweredCount(String asid, final QuestionBean qBean, String brand,
			String outlet_type, String outlet_size) {
		System.out.println("SELECT COUNT(*) as ms_count FROM mst_audit WHERE audit_schedule_id='" + asid
				+ "' AND type_of_check='MS'");
		return template.queryForObject("SELECT COUNT(*) as ms_count FROM mst_audit WHERE audit_schedule_id='" + asid
				+ "' AND type_of_check='MS';", new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setMs_count_ans(rs.getString("ms_count"));
						return qBean;
					}
				});
	}

	public QuestionBean getTotalAuditQuestionCount(String asid, final QuestionBean qBean, String brand,
			String outlet_type, String outlet_size) {
		System.out.println("SELECT COUNT(*) as audit_count FROM mst_questionnaire WHERE brand='" + brand
				+ "' AND type='" + outlet_type + "' AND " + outlet_size
				+ "!='0' AND (type_of_check='Audit' OR type_of_check='NSC/Audit') ");
		return template.queryForObject(
				"SELECT COUNT(*) as audit_count FROM mst_questionnaire WHERE brand='" + brand + "' AND type='"
						+ outlet_type + "' AND " + outlet_size
						+ "!='0' AND (type_of_check='Audit' OR type_of_check='NSC/Audit');",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setAudit_count(rs.getString("audit_count"));
						return qBean;
					}
				});
	}

	public QuestionBean getTotalAuditQuestionAnsweredCount(String asid, final QuestionBean qBean, String brand,
			String outlet_type, String outlet_size) {
		System.out.println("SELECT COUNT(*) as audit_count FROM mst_audit WHERE audit_schedule_id='" + asid
				+ "' AND (type_of_check='Audit' OR type_of_check='NSC/Audit')");
		return template.queryForObject("SELECT COUNT(*) as audit_count FROM mst_audit WHERE audit_schedule_id='" + asid
				+ "' AND (type_of_check='Audit' OR type_of_check='NSC/Audit');", new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setAudit_count_ans(rs.getString("audit_count"));
						return qBean;
					}
				});
	}

	public List<QuestionBean> getAuditScoreBySections(String asid, String oid, String brand, String outlet_size,
			String outlet_type, QuestionBean qBean) {
		System.out.println(
				"SELECT mst_audit.audit_schedule_id,mst_questionnaire.number,SUM(mst_audit.results) as result,COUNT(mst_questionnaire.sk_questionnaire_id) as question,TRUNCATE(SUM(mst_audit.results)/COUNT(mst_questionnaire.sk_questionnaire_id),0) as average FROM `mst_audit` LEFT JOIN mst_questionnaire ON mst_questionnaire.sk_questionnaire_id=mst_audit.question_id WHERE audit_schedule_id="
						+ asid + " GROUP BY mst_questionnaire.number ");
		return template.query(
				"SELECT mst_audit.audit_schedule_id,mst_questionnaire.number,SUM(mst_audit.results) as result,COUNT(mst_questionnaire.sk_questionnaire_id) as question,TRUNCATE(SUM(mst_audit.results)/COUNT(mst_questionnaire.sk_questionnaire_id),0) as average FROM `mst_audit` LEFT JOIN mst_questionnaire ON mst_questionnaire.sk_questionnaire_id=mst_audit.question_id WHERE audit_schedule_id="
						+ asid + " GROUP BY mst_questionnaire.number ",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						qBean.setResult(rs.getString("average"));
						qBean.setAudit_schedule_id(rs.getString("audit_schedule_id"));
						return qBean;
					}
				});
	}

	public QuestionBean getTotalQuestionCountOfAuditId(String asid, final QuestionBean qBean, final String brand,
			final String outlet_type, final String outlet_size) {
		System.out.println(
				"SELECT COUNT(DISTINCT( number)) as audit_count FROM mst_questionnaire WHERE essential='C'  and  brand='"
						+ brand + "' AND type='" + outlet_type + "';");
		return template.queryForObject(
				"SELECT COUNT(DISTINCT( number)) as audit_count FROM mst_questionnaire WHERE essential='C'  and  brand='"
						+ brand + "' AND type='" + outlet_type + "';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final int q1 = Integer.parseInt(rs.getString("audit_count"));

						System.out.println(
								"SELECT COUNT(DISTINCT( number)) as audit_count FROM mst_questionnaire WHERE  essential='X'  and  brand='"
										+ brand + "' AND type='" + outlet_type + "'");
						template.queryForObject(
								"SELECT COUNT(DISTINCT( number)) as audit_count FROM mst_questionnaire WHERE  essential='X'  and  brand='"
										+ brand + "' AND type='" + outlet_type + "';",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										int q2 = Integer.parseInt(rs.getString("audit_count"));
										int q3 = q1 + q2;
										qBean.setAudit_count(String.valueOf(q3));

										return qBean;
									}
								});

						return qBean;
					}
				});
	}

	public QuestionBean getTotalQuestionAnsweredCountOfAuditId(final String asid, final QuestionBean qBean,
			String brand, String outlet_type, String outlet_size) {
		System.out.println(
				"SELECT COUNT(DISTINCT(mst_questionnaire.number)) as audit_count FROM mst_questionnaire,mst_audit WHERE mst_audit.audit_schedule_id='"
						+ asid
						+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.essential='C'");
		return template.queryForObject(
				"SELECT COUNT(DISTINCT(mst_questionnaire.number)) as audit_count FROM mst_questionnaire,mst_audit WHERE mst_audit.audit_schedule_id='"
						+ asid
						+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.essential='C';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final int q1 = Integer.parseInt(rs.getString("audit_count"));

						System.out.println(
								"SELECT COUNT(DISTINCT(mst_questionnaire.number)) as audit_count FROM mst_questionnaire,mst_audit WHERE mst_audit.audit_schedule_id='"
										+ asid
										+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.essential='X'");
						template.queryForObject(
								"SELECT COUNT(DISTINCT(mst_questionnaire.number)) as audit_count FROM mst_questionnaire,mst_audit WHERE mst_audit.audit_schedule_id='"
										+ asid
										+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.essential='X';",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										int q2 = Integer.parseInt(rs.getString("audit_count"));
										int q3 = q1 + q2;

										qBean.setAudit_count_ans(String.valueOf(q3));
										return qBean;
									}
								});
						return qBean;
					}
				});
	}

	public QuestionBean getTotalQuestionAnsweredYesCountOfAuditId(final String asid, final QuestionBean qBean,
			String brand, String outlet_type, String outlet_size) {
		System.out.println(
				"SELECT COUNT(DISTINCT(mst_questionnaire.number)) as audit_count FROM mst_questionnaire,mst_audit WHERE mst_audit.audit_schedule_id='"
						+ asid
						+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.essential='C' AND mst_audit.results='1'");
		return template.queryForObject(
				"SELECT COUNT(DISTINCT(mst_questionnaire.number)) as audit_count FROM mst_questionnaire,mst_audit WHERE mst_audit.audit_schedule_id='"
						+ asid
						+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.essential='C' AND mst_audit.results='1';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final int q1 = Integer.parseInt(rs.getString("audit_count"));

						System.out.println(
								"SELECT COUNT(DISTINCT(mst_questionnaire.number)) as audit_count FROM mst_questionnaire,mst_audit WHERE mst_audit.audit_schedule_id='"
										+ asid
										+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.essential='X' AND mst_audit.results='1'");
						template.queryForObject(
								"SELECT COUNT(DISTINCT(mst_questionnaire.number)) as audit_count FROM mst_questionnaire,mst_audit WHERE mst_audit.audit_schedule_id='"
										+ asid
										+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.essential='X' AND mst_audit.results='1';",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										int q2 = Integer.parseInt(rs.getString("audit_count"));
										int q3 = q1 + q2;

										qBean.setOne_count(String.valueOf(q3));
										return qBean;
									}
								});
						return qBean;
					}
				});
	}

	public QuestionBean getTotalQuestionAnsweredNoCountOfAuditId(final String asid, final QuestionBean qBean,
			String brand, String outlet_type, String outlet_size) {
		System.out.println(
				"SELECT COUNT(DISTINCT(mst_questionnaire.number)) as audit_count FROM mst_questionnaire,mst_audit WHERE mst_audit.audit_schedule_id='"
						+ asid
						+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.essential='C' AND mst_audit.results='0'");
		return template.queryForObject(
				"SELECT COUNT(DISTINCT(mst_questionnaire.number)) as audit_count FROM mst_questionnaire,mst_audit WHERE mst_audit.audit_schedule_id='"
						+ asid
						+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.essential='C' AND mst_audit.results='0';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final int q1 = Integer.parseInt(rs.getString("audit_count"));

						System.out.println(
								"SELECT COUNT(DISTINCT(mst_questionnaire.number)) as audit_count FROM mst_questionnaire,mst_audit WHERE mst_audit.audit_schedule_id='"
										+ asid
										+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.essential='X' AND mst_audit.results='0'");
						template.queryForObject(
								"SELECT COUNT(DISTINCT(mst_questionnaire.number)) as audit_count FROM mst_questionnaire,mst_audit WHERE mst_audit.audit_schedule_id='"
										+ asid
										+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.essential='X' AND mst_audit.results='0';",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										int q2 = Integer.parseInt(rs.getString("audit_count"));
										int q3 = q1 + q2;

										qBean.setZero_count(String.valueOf(q3));
										return qBean;
									}
								});
						return qBean;
					}
				});
	}

	public QuestionBean getTotalQuestionCountOfAuditIdEssentialX(String asid, final QuestionBean qBean, String brand,
			String outlet_type, String outlet_size) {
		System.out.println(
				"SELECT COUNT(DISTINCT( number)) as audit_count FROM mst_questionnaire WHERE essential='X'  and  brand='"
						+ brand + "' AND type='" + outlet_type + "'");
		return template.queryForObject(
				"SELECT COUNT(DISTINCT( number)) as audit_count FROM mst_questionnaire WHERE essential='X'  and  brand='"
						+ brand + "' AND type='" + outlet_type + "';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setNsc_count(rs.getString("audit_count"));
						return qBean;
					}
				});
	}

	public QuestionBean getTotalQuestionAnsweredCountOfAuditIdEssentialX(String asid, final QuestionBean qBean,
			String brand, String outlet_type, String outlet_size) {
		System.out.println(
				"SELECT COUNT(DISTINCT(mst_questionnaire.number)) as audit_count FROM mst_questionnaire,mst_audit WHERE mst_audit.audit_schedule_id='"
						+ asid
						+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.essential='X'");
		return template.queryForObject(
				"SELECT COUNT(DISTINCT(mst_questionnaire.number)) as audit_count FROM mst_questionnaire,mst_audit WHERE mst_audit.audit_schedule_id='"
						+ asid
						+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.essential='X';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setNsc_count_ans(rs.getString("audit_count"));
						return qBean;
					}
				});
	}

	public QuestionBean getTotalQuestionAnsweredCountOfAuditIdEssentialXNo(String asid, final QuestionBean qBean,
			String brand, String outlet_type, String outlet_size) {
		System.out.println(
				"SELECT COUNT(DISTINCT(mst_questionnaire.number)) as audit_count FROM mst_questionnaire,mst_audit WHERE mst_audit.audit_schedule_id='"
						+ asid
						+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.essential='X' AND mst_audit.results='0'");
		return template.queryForObject(
				"SELECT COUNT(DISTINCT(mst_questionnaire.number)) as audit_count FROM mst_questionnaire,mst_audit WHERE mst_audit.audit_schedule_id='"
						+ asid
						+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.essential='X' AND mst_audit.results='0';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setEssential_count(rs.getString("audit_count"));
						return qBean;
					}
				});
	}

	public List<QuestionBean> getSectionScoreList(QuestionBean qBean, final String section_id, final String asid,
			final String brand, final String outlet_size, final String outlet_type) {
		
		
		System.out.println(
				"SELECT SUM(a.average) as average,a.audit_schedule_id,a.section_id,count(a.result) as answer_count,(SELECT COUNT(DISTINCT( number)) as audit_count FROM mst_questionnaire WHERE brand='"+brand+"' AND type='"+outlet_type+"' and essential!='O' AND mst_questionnaire.section_id='"+section_id+"') as question_count FROM (SELECT mst_audit.audit_schedule_id as count,mst_audit.audit_schedule_id,mst_questionnaire.number,mst_questionnaire.section_id,mst_questionnaire.standard,SUM(mst_audit.results) as result,COUNT(mst_questionnaire.sk_questionnaire_id) as question,TRUNCATE(SUM(mst_audit.results)/COUNT(mst_questionnaire.sk_questionnaire_id),0) as average FROM mst_audit LEFT JOIN mst_questionnaire ON mst_questionnaire.sk_questionnaire_id=mst_audit.question_id WHERE audit_schedule_id='"+asid+"' AND mst_questionnaire.essential!='O' AND mst_questionnaire.section_id='"+section_id+"' GROUP BY mst_questionnaire.number) a");
		return template.query(
				"SELECT SUM(a.average) as average,a.audit_schedule_id,a.section_id,count(a.result) as answer_count,(SELECT COUNT(DISTINCT( number)) as audit_count FROM mst_questionnaire WHERE brand='"+brand+"' AND type='"+outlet_type+"' and essential!='O' AND mst_questionnaire.section_id='"+section_id+"') as question_count FROM (SELECT mst_audit.audit_schedule_id as count,mst_audit.audit_schedule_id,mst_questionnaire.number,mst_questionnaire.section_id,mst_questionnaire.standard,SUM(mst_audit.results) as result,COUNT(mst_questionnaire.sk_questionnaire_id) as question,TRUNCATE(SUM(mst_audit.results)/COUNT(mst_questionnaire.sk_questionnaire_id),0) as average FROM mst_audit LEFT JOIN mst_questionnaire ON mst_questionnaire.sk_questionnaire_id=mst_audit.question_id WHERE audit_schedule_id='"+asid+"' AND mst_questionnaire.essential!='O' AND mst_questionnaire.section_id='"+section_id+"' GROUP BY mst_questionnaire.number) a",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final QuestionBean qBean = new QuestionBean();

						qBean.setSection_id(rs.getString("section_id"));
						qBean.setQuestion_count(rs.getString("question_count"));
						qBean.setAnswer_count(rs.getString("answer_count"));
						int q1 = 0;
						try{
						 q1 = Integer.parseInt(rs.getString("answer_count"))
								- Integer.parseInt(rs.getString("average"));
						}catch (Exception e) {
							System.out.println(e);
							q1 = 0;
						}
						qBean.setZero_count(String.valueOf(q1));
						int q2 = Integer.parseInt(rs.getString("question_count")) - q1;
						int q3 = Integer.parseInt(rs.getString("question_count"));
						Double final_avg_score = ((Double.valueOf(q2) / q3) * 100);
						if(final_avg_score.isNaN())
						{
							final_avg_score =0.0;
						}
						qBean.setResult(String.format("%.1f", final_avg_score) + "%");
						

						System.out.println("SELECT * FROM mst_section WHERE sk_section_id='" + section_id + "'");
						template.queryForObject("SELECT * FROM mst_section WHERE sk_section_id='" + section_id + "'",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setSection(rs.getString("section_name"));
										return qBean;
									}
								});

						System.out.println(
								"SELECT (SELECT COUNT(*) as nsc_count FROM mst_questionnaire WHERE  type_of_check = 'NSC' AND brand='"
										+ brand + "' AND type='" + outlet_type + "' AND " + outlet_size
										+ "!='0' AND essential!='O' AND section_id='" + section_id + "') AS nsc_count,"
										+ "(SELECT COUNT(*) as nsc_count FROM mst_questionnaire WHERE  type_of_check = 'Audit' AND brand='"
										+ brand + "' AND type='" + outlet_type + "' AND " + outlet_size
										+ "!='0' AND essential!='O' AND section_id='" + section_id
										+ "')   AS audit_count,"
										+ "(SELECT COUNT(*) as ms_count FROM mst_questionnaire WHERE  type_of_check = 'MS' AND brand='"
										+ brand + "' AND type='" + outlet_type + "' AND " + outlet_size
										+ "!='0' AND essential!='O' AND section_id='" + section_id
										+ "')   AS ms_count");
						template.queryForObject(
								"SELECT (SELECT COUNT(*) as nsc_count FROM mst_questionnaire WHERE  type_of_check = 'NSC' AND brand='"
										+ brand + "' AND type='" + outlet_type + "' AND " + outlet_size
										+ "!='0' AND essential!='O' AND section_id='" + section_id + "') AS nsc_count,"
										+ "(SELECT COUNT(*) as nsc_count FROM mst_questionnaire WHERE  type_of_check = 'Audit' AND brand='"
										+ brand + "' AND type='" + outlet_type + "' AND " + outlet_size
										+ "!='0' AND essential!='O' AND section_id='" + section_id
										+ "')   AS audit_count,"
										+ "(SELECT COUNT(*) as ms_count FROM mst_questionnaire WHERE  type_of_check = 'MS' AND brand='"
										+ brand + "' AND type='" + outlet_type + "' AND " + outlet_size
										+ "!='0' AND essential!='O' AND section_id='" + section_id + "')   AS ms_count",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setAudit_qn_count(rs.getString("audit_count"));
										qBean.setMs_qn_count(rs.getString("ms_count"));
										qBean.setNsc_qn_count(rs.getString("nsc_count"));
										return qBean;
									}
								});
						System.out.println(
								"SELECT (SELECT COUNT(mst_audit.sk_audit_id) as nsc_count FROM mst_audit,mst_questionnaire WHERE  mst_audit.type_of_check = 'NSC'  AND mst_audit.essential!='O' AND audit_schedule_id='"
										+ asid + "' AND mst_questionnaire.section_id='" + section_id
										+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id) AS nsc_count,"
										+ "(SELECT COUNT(mst_audit.sk_audit_id) as audit_count FROM mst_audit,mst_questionnaire WHERE  mst_audit.type_of_check = 'Audit'  AND mst_audit.essential!='O' AND audit_schedule_id='"
										+ asid + "' AND mst_questionnaire.section_id='" + section_id
										+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id)   AS audit_count,"
										+ "(SELECT COUNT(mst_audit.sk_audit_id) as ms_count FROM mst_audit,mst_questionnaire WHERE  mst_audit.type_of_check = 'MS'  AND mst_audit.essential!='O' AND audit_schedule_id='"
										+ asid + "' AND mst_questionnaire.section_id='" + section_id
										+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id)   AS ms_count");
						template.queryForObject(
								"SELECT (SELECT COUNT(mst_audit.sk_audit_id) as nsc_count FROM mst_audit,mst_questionnaire WHERE  mst_audit.type_of_check = 'NSC'  AND mst_audit.essential!='O' AND audit_schedule_id='"
										+ asid + "' AND mst_questionnaire.section_id='" + section_id
										+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id) AS nsc_count,"
										+ "(SELECT COUNT(mst_audit.sk_audit_id) as audit_count FROM mst_audit,mst_questionnaire WHERE  mst_audit.type_of_check = 'Audit'  AND mst_audit.essential!='O' AND audit_schedule_id='"
										+ asid + "' AND mst_questionnaire.section_id='" + section_id
										+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id)   AS audit_count,"
										+ "(SELECT COUNT(mst_audit.sk_audit_id) as ms_count FROM mst_audit,mst_questionnaire WHERE  mst_audit.type_of_check = 'MS'  AND mst_audit.essential!='O' AND audit_schedule_id='"
										+ asid + "' AND mst_questionnaire.section_id='" + section_id
										+ "' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id)   AS ms_count",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setAudit_ans_count(rs.getString("audit_count"));
										qBean.setMs_ans_count(rs.getString("ms_count"));
										qBean.setNsc_ans_count(rs.getString("nsc_count"));
										return qBean;
									}
								});

						return qBean;
					}
				});
	}

	public void addSectionScoreResults(QuestionBean qBean, String did, String oid, String asid, String region_id,
			String outlet_type, String phase, String year,String brand) {

		try {
			String section_id[] = qBean.getSection_id().split(",");
			String section_name[] = qBean.getSection().split(",");
			String total_qns[] = qBean.getQuestion_count().split(",");
			String total_qns_ans[] = qBean.getAnswer_count().split(",");
			String zero_qns[] = qBean.getZero_count().split(",");

			String audit_qn_count[] = qBean.getAudit_qn_count().split(",");
			String nsc_qn_count[] = qBean.getNsc_qn_count().split(",");
			String ms_qn_count[] = qBean.getMs_qn_count().split(",");

			String audit_ans_count[] = qBean.getAudit_ans_count().split(",");
			String nsc_ans_count[] = qBean.getNsc_ans_count().split(",");
			String ms_ans_count[] = qBean.getMs_ans_count().split(",");

			String result[] = qBean.getResult().split(",");

			for (int j = 0; j < section_id.length; j++) {
				System.out.println(
						"INSERT INTO mst_sections_score (audit_schedule_id,region_id,dealer_id,outlet_id,section_name,section_id,total_qns,total_qns_ans,total_zero_qns,total_score,total_nsc_qns,total_ms_qns,total_audit_qns,total_nsc_qns_ans,total_ms_qns_ans,total_audit_qns_ans,outlet_type,quarter,year,brand) VALUES ("
								+ "'" + asid + "','" + region_id + "','" + did + "','" + oid + "','" + section_name[j]
								+ "','" + section_id[j] + "','" + total_qns[j] + "','" + total_qns_ans[j] + "','"
								+ zero_qns[j] + "','" + result[j] + "','" + nsc_qn_count[j] + "','" + ms_qn_count[j]
								+ "','" + audit_qn_count[j] + "','" + nsc_ans_count[j] + "','" + ms_ans_count[j] + "','"
								+ audit_ans_count[j] + "','" + outlet_type + "','" + phase + "','" + year + "','"+brand+"')");
				template.execute(
						"INSERT INTO mst_sections_score (audit_schedule_id,region_id,dealer_id,outlet_id,section_name,section_id,total_qns,total_qns_ans,total_zero_qns,total_score,total_nsc_qns,total_ms_qns,total_audit_qns,total_nsc_qns_ans,total_ms_qns_ans,total_audit_qns_ans,outlet_type,quarter,year,brand) VALUES ("
								+ "'" + asid + "','" + region_id + "','" + did + "','" + oid + "','" + section_name[j]
								+ "','" + section_id[j] + "','" + total_qns[j] + "','" + total_qns_ans[j] + "','"
								+ zero_qns[j] + "','" + result[j] + "','" + nsc_qn_count[j] + "','" + ms_qn_count[j]
								+ "','" + audit_qn_count[j] + "','" + nsc_ans_count[j] + "','" + ms_ans_count[j] + "','"
								+ audit_ans_count[j] + "','" + outlet_type + "','" + phase + "','" + year + "','"+brand+"')");
			}

		} catch (Exception e) {
			System.out.println(e + "Something messed up");
		}

	}

	public void updateSectionScoreResults(QuestionBean qBean, String did, String oid, String asid, String region_id,
			String outlet_type, String phase, String year,String brand) {
		System.out.println("DELETE FROM mst_sections_score WHERE audit_schedule_id='" + asid + "'");
		template.execute("DELETE FROM mst_sections_score WHERE audit_schedule_id='" + asid + "'");
		try {
			String section_id[] = qBean.getSection_id().split(",");
			String section_name[] = qBean.getSection().split(",");
			String total_qns[] = qBean.getQuestion_count().split(",");
			String total_qns_ans[] = qBean.getAnswer_count().split(",");
			String zero_qns[] = qBean.getZero_count().split(",");

			String audit_qn_count[] = qBean.getAudit_qn_count().split(",");
			String nsc_qn_count[] = qBean.getNsc_qn_count().split(",");
			String ms_qn_count[] = qBean.getMs_qn_count().split(",");

			String audit_ans_count[] = qBean.getAudit_ans_count().split(",");
			String nsc_ans_count[] = qBean.getNsc_ans_count().split(",");
			String ms_ans_count[] = qBean.getMs_ans_count().split(",");

			String result[] = qBean.getResult().split(",");

			for (int j = 0; j < section_id.length; j++) {
				System.out.println(
						"INSERT INTO mst_sections_score (audit_schedule_id,region_id,dealer_id,outlet_id,section_name,section_id,total_qns,total_qns_ans,total_zero_qns,total_score,total_nsc_qns,total_ms_qns,total_audit_qns,total_nsc_qns_ans,total_ms_qns_ans,total_audit_qns_ans,outlet_type,quarter,year,brand) VALUES ("
								+ "'" + asid + "','" + region_id + "','" + did + "','" + oid + "','" + section_name[j]
								+ "','" + section_id[j] + "','" + total_qns[j] + "','" + total_qns_ans[j] + "','"
								+ zero_qns[j] + "','" + result[j] + "','" + nsc_qn_count[j] + "','" + ms_qn_count[j]
								+ "','" + audit_qn_count[j] + "','" + nsc_ans_count[j] + "','" + ms_ans_count[j] + "','"
								+ audit_ans_count[j] + "','" + outlet_type + "','" + phase + "','" + year + "','"+brand+"')");
				template.execute(
						"INSERT INTO mst_sections_score (audit_schedule_id,region_id,dealer_id,outlet_id,section_name,section_id,total_qns,total_qns_ans,total_zero_qns,total_score,total_nsc_qns,total_ms_qns,total_audit_qns,total_nsc_qns_ans,total_ms_qns_ans,total_audit_qns_ans,outlet_type,quarter,year,brand) VALUES ("
								+ "'" + asid + "','" + region_id + "','" + did + "','" + oid + "','" + section_name[j]
								+ "','" + section_id[j] + "','" + total_qns[j] + "','" + total_qns_ans[j] + "','"
								+ zero_qns[j] + "','" + result[j] + "','" + nsc_qn_count[j] + "','" + ms_qn_count[j]
								+ "','" + audit_qn_count[j] + "','" + nsc_ans_count[j] + "','" + ms_ans_count[j] + "','"
								+ audit_ans_count[j] + "','" + outlet_type + "','" + phase + "','" + year + "','"+brand+"');");
			}

		} catch (Exception e) {
			System.out.println(e + "Something messed up");
		}

	}

	public QuestionBean checkSectionWiseResultsExist(String asid, final QuestionBean qBean) {
		System.out.println("SELECT * FROM mst_sections_score WHERE audit_schedule_id='" + asid + "' LIMIT 1;");
		return template.queryForObject(
				"SELECT * FROM mst_sections_score WHERE audit_schedule_id='" + asid + "' LIMIT 1;",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {

						return qBean;
					}
				});
	}

	public void addfinalScore(QuestionBean qBean, String did, String oid, String asid, String outlet_type, String year,
			String phase,String brand) {
		try {

			System.out.println(qBean.getScore_type());
			System.out.println(qBean.getTotal_qns_count());
			System.out.println(qBean.getTotal_ans_count());
			System.out.println(qBean.getTotal_zero_count());
			System.out.println(qBean.getScore());

			String score_type[] = qBean.getScore_type().split(",");
			String total_qns[] = qBean.getTotal_qns_count().split(",");
			String total_qns_ans[] = qBean.getTotal_ans_count().split(",");
			String zero_qns[] = qBean.getTotal_zero_count().split(",");
			String score[] = qBean.getScore().split(",");

			for (int j = 0; j < score_type.length; j++) {
				System.out.println(
						"INSERT INTO mst_final_score(score_type,audit_schedule_id,outlet_id,dealer_id,total_no_qns,total_no_qns_ans,total_zero_qns,score,outlet_type,year,quarter,brand) VALUES ('"
								+ score_type[j] + "'," + "'" + asid + "','" + oid + "','" + did + "','" + total_qns[j]
								+ "','" + total_qns_ans[j] + "','" + zero_qns[j] + "','" + score[j] + "','"
								+ outlet_type + "','" + year + "','" + phase + "','"+brand+"')");
				template.execute(
						"INSERT INTO mst_final_score(score_type,audit_schedule_id,outlet_id,dealer_id,total_no_qns,total_no_qns_ans,total_zero_qns,score,outlet_type,year,quarter,brand) VALUES ('"
								+ score_type[j] + "'," + "'" + asid + "','" + oid + "','" + did + "','" + total_qns[j]
								+ "','" + total_qns_ans[j] + "','" + zero_qns[j] + "','" + score[j] + "','"
								+ outlet_type + "','" + year + "','" + phase + "','"+brand+"')");
			}
		} catch (Exception e) {
			System.out.println(e + "Something messed up");
		}

	}

	public void updateFinalScore(QuestionBean qBean, String did, String oid, String asid, String outlet_type,
			String year, String phase,String brand) {
		System.out.println("DELETE FROM mst_final_score WHERE audit_schedule_id='" + asid + "'");
		template.execute("DELETE FROM mst_final_score WHERE audit_schedule_id='" + asid + "'");
		try {
			String score_type[] = qBean.getScore_type().split(",");
			String total_qns[] = qBean.getTotal_qns_count().split(",");
			String total_qns_ans[] = qBean.getTotal_ans_count().split(",");
			String zero_qns[] = qBean.getTotal_zero_count().split(",");
			String score[] = qBean.getScore().split(",");

			for (int j = 0; j < score_type.length; j++) {
				System.out.println(
						"INSERT INTO mst_final_score(score_type,audit_schedule_id,outlet_id,dealer_id,total_no_qns,total_no_qns_ans,total_zero_qns,score,outlet_type,year,quarter,brand) VALUES ('"
								+ score_type[j] + "'," + "'" + asid + "','" + oid + "','" + did + "','" + total_qns[j]
								+ "','" + total_qns_ans[j] + "','" + zero_qns[j] + "','" + score[j] + "','"
								+ outlet_type + "','" + year + "','" + phase + "','"+brand+"')");
				template.execute(
						"INSERT INTO mst_final_score(score_type,audit_schedule_id,outlet_id,dealer_id,total_no_qns,total_no_qns_ans,total_zero_qns,score,outlet_type,year,quarter,brand) VALUES ('"
								+ score_type[j] + "'," + "'" + asid + "','" + oid + "','" + did + "','" + total_qns[j]
								+ "','" + total_qns_ans[j] + "','" + zero_qns[j] + "','" + score[j] + "','"
								+ outlet_type + "','" + year + "','" + phase + "','"+brand+"')");
			}
		} catch (Exception e) {
			System.out.println(e + "Something messed up");
		}

	}

	public QuestionBean checkFinalScoreExist(String asid, final QuestionBean qBean) {
		System.out.println("SELECT * FROM mst_final_score WHERE audit_schedule_id='" + asid + "' LIMIT 1;");
		return template.queryForObject("SELECT * FROM mst_final_score WHERE audit_schedule_id='" + asid + "' LIMIT 1;",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {

						return qBean;
					}
				});
	}

	public List<QuestionBean> getMonthlyAudits(QuestionBean qBean) {
		System.out.println(
				"SELECT COUNT(*) as count,MONTH(audit_complete_date) as month FROM mst_audit_schedule WHERE (status='completed' OR status='closed') GROUP BY MONTH(audit_complete_date)  ORDER BY `month`  DESC;");
		return template.query(
				"SELECT COUNT(*) as count,MONTH(audit_complete_date) as month FROM mst_audit_schedule WHERE (status='completed' OR status='closed') GROUP BY MONTH(audit_complete_date)  ORDER BY `month`  DESC;",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						qBean.setScore(rs.getString("count"));
						qBean.setDate(rs.getString("month"));
						return qBean;
					}
				});
	}

	public UserBean getOutletScheduledDetails(final UserBean uBean, String asid) {
		System.out.println("SELECT * FROM mst_audit_schedule WHERE sk_audit_id='" + asid + "' LIMIT 1;");
		return template.queryForObject("SELECT * FROM mst_audit_schedule WHERE sk_audit_id='" + asid + "' LIMIT 1;",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {

						uBean.setQuarter(rs.getString("quarter"));
						uBean.setYear(rs.getString("year"));

						return uBean;
					}
				});
	}

	public QuestionBean checkDealerSectionScoreExist(final QuestionBean qBean, String did, String year, String phase) {
		System.out.println("SELECT * FROM mst_dealer_section_score WHERE dealer_id='" + did + "' AND year='" + year
				+ "' AND quarter='" + phase + "' LIMIT 1;");
		return template.queryForObject("SELECT * FROM mst_dealer_section_score WHERE dealer_id='" + did + "' AND year='"
				+ year + "' AND quarter='" + phase + "' LIMIT 1;", new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {

						return qBean;
					}
				});
	}
	public QuestionBean checkDealerSectionScoreExist(final QuestionBean qBean, String did, String year, String phase,String brand) {
		System.out.println("SELECT * FROM mst_dealer_section_score WHERE dealer_id='" + did + "' AND year='" + year
				+ "' AND quarter='" + phase + "' AND brand='"+brand+"' LIMIT 1;");
		return template.queryForObject("SELECT * FROM mst_dealer_section_score WHERE dealer_id='" + did + "' AND year='"
				+ year + "' AND quarter='" + phase + "' AND brand='"+brand+"'  LIMIT 1;", new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {

						return qBean;
					}
				});
	}

	public void deleteDealerScore(QuestionBean qBean, String did, String year, String phase) {
		System.out.println("DELETE FROM mst_dealer_section_score WHERE dealer_id='" + did + "' AND year='" + year
				+ "' AND quarter='" + phase + "'");
		template.execute("DELETE FROM mst_dealer_section_score WHERE dealer_id='" + did + "' AND year='" + year
				+ "' AND quarter='" + phase + "'");

	}
	public void deleteDealerScore(QuestionBean qBean, String did, String year, String phase,String brand) {
		System.out.println("DELETE FROM mst_dealer_section_score WHERE dealer_id='" + did + "' AND year='" + year
				+ "' AND quarter='" + phase + "' AND brand='"+brand+"'");
		template.execute("DELETE FROM mst_dealer_section_score WHERE dealer_id='" + did + "' AND year='" + year
				+ "' AND quarter='" + phase + "' AND brand='"+brand+"'");

	}

	public QuestionBean checkDealerFinalScoreExist(final QuestionBean qBean, String did, String year, String phase) {
		System.out.println("SELECT * FROM mst_dealer_final_score WHERE dealer_id='" + did + "' AND year='" + year
				+ "' AND quarter='" + phase + "' LIMIT 1;");
		return template.queryForObject("SELECT * FROM mst_dealer_final_score WHERE dealer_id='" + did + "' AND year='"
				+ year + "' AND quarter='" + phase + "' LIMIT 1;", new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {

						return qBean;
					}
				});
	}
	public QuestionBean checkDealerFinalScoreExist(final QuestionBean qBean, String did, String year, String phase,String brand) {
		System.out.println("SELECT * FROM mst_dealer_final_score WHERE dealer_id='" + did + "' AND year='" + year
				+ "' AND quarter='" + phase + "' AND brand='"+brand+"' LIMIT 1;");
		return template.queryForObject("SELECT * FROM mst_dealer_final_score WHERE dealer_id='" + did + "' AND year='"
				+ year + "' AND quarter='" + phase + "' AND brand='"+brand+"' LIMIT 1;", new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {

						return qBean;
					}
				});
	}

	public void deleteDeleteDealerFinalScore(QuestionBean qBean, String did, String year, String phase) {
		System.out.println("DELETE FROM mst_dealer_final_score WHERE dealer_id='" + did + "' AND year='" + year
				+ "' AND quarter='" + phase + "'");
		template.execute("DELETE FROM mst_dealer_final_score WHERE dealer_id='" + did + "' AND year='" + year
				+ "' AND quarter='" + phase + "'");

	}
	public void deleteDeleteDealerFinalScore(QuestionBean qBean, String did, String year, String phase,String brand) {
		System.out.println("DELETE FROM mst_dealer_final_score WHERE dealer_id='" + did + "' AND year='" + year
				+ "' AND quarter='" + phase + "' AND brand='"+brand+"'");
		template.execute("DELETE FROM mst_dealer_final_score WHERE dealer_id='" + did + "' AND year='" + year
				+ "' AND quarter='" + phase + "' AND brand='"+brand+"'");

	}

	public List<QuestionBean> checkScoreExistByAuditId(QuestionBean qBean, String audit_id) {
		System.out.println(
				"SELECT audit_schedule_id FROM mst_sections_score WHERE audit_schedule_id='" + audit_id + "' LIMIT 1;");
		return template.query(
				"SELECT audit_schedule_id FROM mst_sections_score WHERE audit_schedule_id='" + audit_id + "'  LIMIT 1;",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						qBean.setAudit_id(rs.getString("audit_schedule_id"));
						return qBean;
					}
				});
	}

	public void updateAuditStatusByAuditId(UserBean uBean, String aid) {
		System.out.println("UPDATE mst_audit_schedule SET status = 'completed' WHERE sk_audit_id='" + aid + "' ");
		template.execute("UPDATE mst_audit_schedule SET status = 'completed' WHERE sk_audit_id='" + aid + "' ");

	}

	String audit_id = "";
	String number  = "";
	public List<QuestionBean> getPenalizedStandard(UserBean uBean, final String did, String type, final String phase,
			String year,String brand) {
		System.out.println("PHASE :"+phase);
		String sql="";
		if(phase.equals("H1"))
		{
		 sql = "SELECT mst_questionnaire.*,mst_section.*,COUNT(mst_questionnaire.number) as number_count,mst_audit_schedule.quarter,mst_audit_schedule.year FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"
						+ type
						+ "'  AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"
						+ did
						+ "' AND mst_questionnaire.essential!='O' AND  mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id AND mst_audit_schedule.quarter='"+phase+"' AND mst_audit_schedule.year='"+year+"' GROUP BY mst_questionnaire.number,mst_questionnaire.brand;";
		}
		if(phase.equals("H2"))
		{
			/*sql = "SELECT mst_questionnaire.*,mst_section.*,COUNT(mst_questionnaire.number) as number_count,mst_audit_schedule.quarter,mst_audit_schedule.year FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"
					+ type
					+ "'  AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"
					+ did
					+ "' AND mst_questionnaire.essential!='O' AND  mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id  AND mst_audit_schedule.year='"+year+"' GROUP BY mst_questionnaire.number,mst_questionnaire.brand;";*/
				/*sql="SELECT mst_questionnaire.*,mst_section.*,COUNT(mst_questionnaire.number) as number_count,mst_audit_schedule.quarter,mst_audit_schedule.year FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"+type+"' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"+did+"' AND mst_questionnaire.essential!='O' AND mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id AND mst_audit_schedule.year='"+year+"' AND quarter='"+phase+"' GROUP BY mst_questionnaire.number,mst_questionnaire.brand UNION SELECT mst_questionnaire.*,mst_section.*,COUNT(mst_questionnaire.number) as number_count,mst_audit_schedule.quarter,mst_audit_schedule.year FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"+type+"' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"+did+"' AND mst_questionnaire.essential!='O' AND mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id AND mst_audit_schedule.year='"+year+"' and mst_audit_schedule.quarter='H1' AND mst_questionnaire.number IN (SELECT mst_questionnaire.number FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"+type+"' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"+did+"' AND mst_questionnaire.essential!='O' AND mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id AND mst_audit_schedule.year='"+year+"' and mst_audit_schedule.quarter='"+phase+"') GROUP BY mst_questionnaire.number,mst_questionnaire.brand AND mst_questionnaire.number";*/
				  /*sql="SELECT mst_questionnaire.*,mst_section.*,COUNT(mst_questionnaire.number) as number_count,GROUP_CONCAT(mst_audit_schedule.quarter) as quarter,mst_audit_schedule.year FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"+type+"' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"+did+"' AND mst_questionnaire.essential!='O' AND mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id AND mst_audit_schedule.year='"+year+"'  AND mst_questionnaire.number IN (SELECT mst_questionnaire.number FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"+type+"' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"+did+"' AND mst_questionnaire.essential!='O' AND mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id AND mst_audit_schedule.year='"+year+"' and mst_audit_schedule.quarter='"+phase+"') GROUP BY mst_questionnaire.number,mst_questionnaire.brand";*/
			sql = "SELECT mst_questionnaire.*,mst_section.*,COUNT(mst_questionnaire.number) as number_count,mst_audit_schedule.quarter,mst_audit_schedule.year FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"
					+ type
					+ "'  AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"
					+ did
					+ "' AND mst_questionnaire.essential!='O' AND  mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id AND mst_audit_schedule.quarter='"+phase+"' AND mst_audit_schedule.year='"+year+"' GROUP BY mst_questionnaire.number,mst_questionnaire.brand;";
		}
		System.out.println(sql);
		return template.query(sql,new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final QuestionBean qBean = new QuestionBean();
						qBean.setSection(rs.getString("section_name"));
						try{
							String temp [] = rs.getString("section_name").split("-");
							
							qBean.setSection(temp [1]);
							}catch (Exception e) {
								qBean.setSection(rs.getString("section_name"));
							}
						qBean.setSection_id(rs.getString("sk_section_id"));
						qBean.setStandard(rs.getString("standard"));
						qBean.setRequirement(rs.getString("requirement"));
						qBean.setComment(rs.getString("comment"));
						qBean.setNumber(rs.getString("number"));
						qBean.setXs(rs.getString("xs"));
						qBean.setS(rs.getString("s"));
						qBean.setM(rs.getString("m"));
						qBean.setL(rs.getString("l"));
						qBean.setXl(rs.getString("xl"));
						qBean.setXxl(rs.getString("xxl"));
						qBean.setCheck(rs.getString("type_of_check"));
						qBean.setEssential(rs.getString("essential"));
						qBean.setQuestion(rs.getString("question"));
						qBean.setObservation(rs.getString("observation"));
						qBean.setPerson(rs.getString("suggested_person"));
						qBean.setQuestionnaire_id(rs.getString("sk_questionnaire_id"));
						qBean.setPhase(rs.getString("quarter"));
						/*try{
							String temp[] = rs.getString("quarter").split(",");
						
							qBean.setPhase( temp[0]);
							qBean.setPh( temp[1]);
						}catch (Exception e) {
							System.out.println("RAVI "+e);
							qBean.setPhase(rs.getString("quarter"));
						}*/
						qBean.setYear(rs.getString("year"));
						 number  = rs.getString("number_count");

						System.out.println("SELECT mst_audit.*,COUNT(*) as count,GROUP_CONCAT(mst_audit.outlet_id) as oid FROM mst_audit,mst_audit_schedule WHERE mst_audit.question_id='"
								+ rs.getString("sk_questionnaire_id") + "'  AND mst_audit.dealer_id='" + did
								+ "' AND mst_audit.results='0' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id AND mst_audit_schedule.quarter='"+phase+"' and year='"+year+"';;");
						
						template.query("SELECT mst_audit.*,COUNT(*) as count,GROUP_CONCAT(mst_audit.outlet_id) as oid FROM mst_audit,mst_audit_schedule WHERE mst_audit.question_id='"
								+ rs.getString("sk_questionnaire_id") + "'  AND mst_audit.dealer_id='" + did
								+ "' AND mst_audit.results='0' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id AND mst_audit_schedule.quarter='"+phase+"' and year='"+year+"';", new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setAnswer(rs.getString("results"));
										qBean.setAudit_id(rs.getString("sk_audit_id"));
										audit_id = rs.getString("sk_audit_id");
										try{
										if (rs.getString("auditor_comment").isEmpty()	|| rs.getString("auditor_comment") == "" || rs.getString("auditor_comment").equals(null)) {
											qBean.setAuditor_comment("Obseravtion Noted");
											
										} else {
											
											qBean.setAuditor_comment(rs.getString("auditor_comment"));
										}
										}catch (Exception e) {
											qBean.setAuditor_comment(rs.getString("auditor_comment"));
										}
										qBean.setDealer_comment(rs.getString("dealer_comment"));
										if (rs.getString("results").equals("0")) {
											qBean.setAnswer_type("NO");
										} else {
											qBean.setAnswer_type("YES");
										}
										if (rs.getString("pmo_exception_remarks") == null
												|| rs.getString("pmo_exception_remarks").equals(null)) {
											qBean.setException_remarks("False");
										} else {
											qBean.setException_remarks("True");
										}
										qBean.setOutlet_id(rs.getString("oid"));
										try{
										String temp[] = rs.getString("oid").split(",");
										int len = temp.length;
										System.out.println(len+" LEN"); 
										if(len==2)
										{
											qBean.setOutlet_id(temp[0]);
											qBean.setOutlets(temp[1]);
										}
										if(len==3)
										{
										qBean.setOutlet_id(temp[0]);
										qBean.setOutlets(temp[1]);
										qBean.setPh(temp[2]);
										}
										}catch (Exception e) {
											qBean.setOutlet_id(rs.getString("oid"));
										}

										if(!rs.getString("count").equals("1"))
										{
											qBean.setAuditor_comment("Refer Annexure Table");
										}
										else
										{
											
										}

										return qBean;
									}
								});
						/*System.out.println(
								"SELECT mst_audit_schedule.quarter,mst_audit_schedule.outlet_id,mst_audit_schedule.year from mst_audit_schedule,mst_audit WHERE mst_audit.sk_audit_id='"
										+ audit_id
										+ "' AND mst_audit.audit_schedule_id=mst_audit_schedule.sk_audit_id;");
						template.query(
								"SELECT mst_audit_schedule.quarter,mst_audit_schedule.outlet_id,mst_audit_schedule.year from mst_audit_schedule,mst_audit WHERE mst_audit.sk_audit_id='"
										+ audit_id
										+ "' AND mst_audit.audit_schedule_id=mst_audit_schedule.sk_audit_id;",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setTime(rs.getString("year"));
										System.out.println(rs.getString("year") + "sad");
										qBean.setAvg(rs.getString("quarter"));

										return qBean;
									}
								});*/
						return qBean;
					}
				});
	}
	String audis="";
	String num = "";
	String qnnid = "";
	public List<QuestionBean> getBMWAnnexurePenalizedList(UserBean uBean, final String did, final String type,final String phase,final String year,final String brand) {
		
		String sql = "";
		if(phase.equals("H1"))
		{
		/*sql="SELECT * FROM(SELECT mst_questionnaire.*,mst_section.sk_section_id,mst_section.section_name,mst_section.type as section_type,mst_section.brand as section_brand,mst_section.status as section_status,COUNT(mst_questionnaire.number) as number_count,mst_audit_schedule.year,mst_audit_schedule.quarter FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"+type+"'  AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"+did+"' AND mst_questionnaire.essential!='O' AND  mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id AND mst_audit_schedule.quarter='"+phase+"' AND mst_audit_schedule.year='"+year+"' GROUP BY mst_questionnaire.number,mst_questionnaire.brand HAVING COUNT(mst_questionnaire.number)>1) as res1 LEFT JOIN (SELECT mst_audit.*,COUNT(*) as count FROM mst_audit,mst_audit_schedule WHERE  mst_audit.dealer_id='"+did+"' AND mst_audit.results='0' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id AND mst_audit_schedule.quarter='"+phase+"' AND mst_audit_schedule.year='"+year+"' GROUP BY mst_audit.question_id HAVING COUNT(*)>1) as res2 ON res2.question_id=res1.sk_questionnaire_id WHERE res2.count IS NOT null";*/
			sql="SELECT * FROM(SELECT   * FROM ( SELECT  mst_questionnaire.sk_questionnaire_id as sk_qid,mst_questionnaire.number as sn FROM  mst_questionnaire,  mst_section, mst_audit,   mst_audit_schedule WHERE  mst_questionnaire.section_id = mst_section.sk_section_id AND mst_audit.results = '0' AND mst_questionnaire.type = '"+type+"' AND mst_audit.question_id = mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id = '"+did+"' AND mst_questionnaire.essential != 'O' AND mst_questionnaire.brand = '"+brand+"' AND mst_audit_schedule.sk_audit_id = mst_audit.audit_schedule_id AND mst_audit_schedule.quarter = '"+phase+"' AND mst_audit_schedule.year = '"+year+"'  GROUP BY  mst_questionnaire.number,  mst_questionnaire.brand  HAVING  COUNT(mst_questionnaire.number) > 1 ) AS res1 LEFT JOIN(  SELECT mst_audit.question_id as qid, COUNT(*) AS C1 FROM mst_audit,  mst_audit_schedule  WHERE  mst_audit.dealer_id = '"+did+"' AND mst_audit.results = '0' AND mst_audit_schedule.sk_audit_id = mst_audit.audit_schedule_id AND mst_audit_schedule.quarter = '"+phase+"' AND mst_audit_schedule.year = '"+year+"' GROUP BY mst_audit.question_id  HAVING  COUNT(*) > 1 ) AS res2 ON res2.qid = res1.sk_qid WHERE  res2.c1 IS NOT NULL) as r1 LEFT JOIN (SELECT  mst_questionnaire.*,  mst_section.sk_section_id,  mst_section.section_name,   mst_section.type AS section_type, mst_section.brand AS section_brand, mst_section.status AS section_status,  COUNT(mst_questionnaire.number) AS number_count,  mst_audit_schedule.year, mst_audit_schedule.quarter,mst_audit.outlet_id,mst_audit.dealer_comment,mst_audit.auditor_comment,mst_audit.results,mst_audit.pmo_exception_remarks,mst_audit.audit_schedule_id,mst_audit.question_id   FROM mst_questionnaire, mst_section, mst_audit,  mst_audit_schedule  WHERE  mst_questionnaire.section_id = mst_section.sk_section_id AND mst_audit.results = '0' AND mst_questionnaire.type = '"+type+"' AND mst_audit.question_id = mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id = '"+did+"' AND mst_questionnaire.essential != 'O' AND mst_questionnaire.brand = '"+brand+"' AND mst_audit_schedule.sk_audit_id = mst_audit.audit_schedule_id AND mst_audit_schedule.quarter = '"+phase+"' AND mst_audit_schedule.year = '"+year+"' GROUP BY  mst_questionnaire.number,mst_audit.outlet_id) as r2 on r1.sn=r2.number";
		}
		if(phase.equals("H2"))
		{
			/*sql="SELECT * FROM(SELECT mst_questionnaire.*,mst_section.sk_section_id,mst_section.section_name,mst_section.type as section_type,mst_section.brand as section_brand,mst_section.status as section_status,COUNT(mst_questionnaire.number) as number_count,mst_audit_schedule.year,mst_audit_schedule.quarter FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"+type+"'  AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"+did+"' AND mst_questionnaire.essential!='O' AND  mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id  AND mst_audit_schedule.year='"+year+"' GROUP BY mst_questionnaire.number,mst_questionnaire.brand HAVING COUNT(mst_questionnaire.number)>1) as res1 LEFT JOIN (SELECT mst_audit.*,COUNT(*) as count FROM mst_audit,mst_audit_schedule WHERE  mst_audit.dealer_id='"+did+"' AND mst_audit.results='0' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id  AND mst_audit_schedule.year='"+year+"' GROUP BY mst_audit.question_id HAVING COUNT(*)>1) as res2 ON res2.question_id=res1.sk_questionnaire_id WHERE res2.count IS NOT null";*/	
			/*sql="SELECT * FROM(SELECT mst_questionnaire.*,mst_section.sk_section_id,mst_section.section_name,mst_section.type as section_type,mst_section.brand as section_brand,mst_section.status as section_status,COUNT(mst_questionnaire.number) as number_count,mst_audit_schedule.year,mst_audit_schedule.quarter FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"+type+"'  AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"+did+"' AND mst_questionnaire.essential!='O' AND  mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id  AND mst_audit_schedule.year='"+year+"' GROUP BY mst_questionnaire.number,mst_questionnaire.brand HAVING COUNT(mst_questionnaire.number)>1) as res1 LEFT JOIN (SELECT mst_audit.*,COUNT(*) as count FROM mst_audit,mst_audit_schedule WHERE  mst_audit.dealer_id='"+did+"' AND mst_audit.results='0' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id  AND mst_audit_schedule.year='"+year+"' GROUP BY mst_audit.question_id HAVING COUNT(*)>1) as res2 ON res2.question_id=res1.sk_questionnaire_id WHERE res2.count IS NOT null AND quarter='"+phase+"' UNION SELECT * FROM(SELECT mst_questionnaire.*,mst_section.sk_section_id,mst_section.section_name,mst_section.type as section_type,mst_section.brand as section_brand,mst_section.status as section_status,COUNT(mst_questionnaire.number) as number_count,mst_audit_schedule.year,mst_audit_schedule.quarter FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"+type+"'  AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"+did+"' AND mst_questionnaire.essential!='O' AND  mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id  AND mst_audit_schedule.year='"+year+"' GROUP BY mst_questionnaire.number,mst_questionnaire.brand HAVING COUNT(mst_questionnaire.number)>1) as res1 LEFT JOIN (SELECT mst_audit.*,COUNT(*) as count FROM mst_audit,mst_audit_schedule WHERE  mst_audit.dealer_id='19' AND mst_audit.results='0' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id  AND mst_audit_schedule.year='"+year+"' GROUP BY mst_audit.question_id HAVING COUNT(*)>1) as res2 ON res2.question_id=res1.sk_questionnaire_id WHERE res2.count IS NOT null AND quarter='H1' AND res1.number IN (SELECT mst_questionnaire.number FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"+type+"'  AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"+did+"' AND mst_questionnaire.essential!='O' AND  mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id  AND mst_audit_schedule.year='"+year+"' and mst_audit_schedule.quarter='"+phase+"')";*/
			/*sql="SELECT * FROM(SELECT mst_questionnaire.*,mst_section.sk_section_id,mst_section.section_name,mst_section.type as section_type,mst_section.brand as section_brand,mst_section.status as section_status,COUNT(mst_questionnaire.number) as number_count,mst_audit_schedule.year,mst_audit_schedule.quarter FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"+type+"'  AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"+did+"' AND mst_questionnaire.essential!='O' AND  mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id AND mst_audit_schedule.quarter='"+phase+"' AND mst_audit_schedule.year='"+year+"' GROUP BY mst_questionnaire.number,mst_questionnaire.brand HAVING COUNT(mst_questionnaire.number)>1) as res1 LEFT JOIN (SELECT mst_audit.*,COUNT(*) as count FROM mst_audit,mst_audit_schedule WHERE  mst_audit.dealer_id='"+did+"' AND mst_audit.results='0' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id AND mst_audit_schedule.quarter='"+phase+"' AND mst_audit_schedule.year='"+year+"' GROUP BY mst_audit.question_id HAVING COUNT(*)>1) as res2 ON res2.question_id=res1.sk_questionnaire_id WHERE res2.count IS NOT null";*/
			/*sql="SELECT * FROM(SELECT mst_questionnaire.*,mst_section.sk_section_id,mst_section.section_name,mst_section.type as section_type,mst_section.brand as section_brand,mst_section.status as section_status,COUNT(mst_questionnaire.number) as number_count,mst_audit_schedule.year,mst_audit_schedule.quarter,mst_audit.outlet_id,mst_audit.audit_schedule_id,mst_audit.question_id,mst_audit.results,mst_audit.auditor_comment,mst_audit.dealer_comment,mst_audit.pmo_exception_remarks FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"+type+"'  AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"+did+"' AND mst_questionnaire.essential!='O' AND  mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id  AND mst_audit_schedule.year='"+year+"' GROUP BY mst_questionnaire.number,mst_audit.outlet_id) as res1 LEFT JOIN (SELECT mst_questionnaire.number as sn,COUNT(mst_questionnaire.number) as nc FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"+type+"'  AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"+did+"' AND mst_questionnaire.essential!='O' AND  mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id  AND mst_audit_schedule.year='"+year+"' AND mst_audit_schedule.quarter='"+phase+"' GROUP BY mst_questionnaire.number,mst_questionnaire.brand HAVING COUNT(mst_questionnaire.number)>1) as res2 ON res1.number=res2.sn WHERE res2.sn IS NOT NULL  AND res1.quarter='"+phase+"'";*/
		
			sql="SELECT * FROM(SELECT   * FROM ( SELECT  mst_questionnaire.sk_questionnaire_id as sk_qid,mst_questionnaire.number as sn FROM  mst_questionnaire,  mst_section, mst_audit,   mst_audit_schedule WHERE  mst_questionnaire.section_id = mst_section.sk_section_id AND mst_audit.results = '0' AND mst_questionnaire.type = '"+type+"' AND mst_audit.question_id = mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id = '"+did+"' AND mst_questionnaire.essential != 'O' AND mst_questionnaire.brand = '"+brand+"' AND mst_audit_schedule.sk_audit_id = mst_audit.audit_schedule_id AND mst_audit_schedule.quarter = '"+phase+"' AND mst_audit_schedule.year = '"+year+"'  GROUP BY  mst_questionnaire.number,  mst_questionnaire.brand  HAVING  COUNT(mst_questionnaire.number) > 1 ) AS res1 LEFT JOIN(  SELECT mst_audit.question_id as qid, COUNT(*) AS C1 FROM mst_audit,  mst_audit_schedule  WHERE  mst_audit.dealer_id = '"+did+"' AND mst_audit.results = '0' AND mst_audit_schedule.sk_audit_id = mst_audit.audit_schedule_id AND mst_audit_schedule.quarter = '"+phase+"' AND mst_audit_schedule.year = '"+year+"' GROUP BY mst_audit.question_id  HAVING  COUNT(*) > 1 ) AS res2 ON res2.qid = res1.sk_qid WHERE  res2.c1 IS NOT NULL) as r1 LEFT JOIN (SELECT  mst_questionnaire.*,  mst_section.sk_section_id,  mst_section.section_name,   mst_section.type AS section_type, mst_section.brand AS section_brand, mst_section.status AS section_status,  COUNT(mst_questionnaire.number) AS number_count,  mst_audit_schedule.year, mst_audit_schedule.quarter,mst_audit.outlet_id,mst_audit.dealer_comment,mst_audit.auditor_comment,mst_audit.results,mst_audit.pmo_exception_remarks,mst_audit.audit_schedule_id,mst_audit.question_id   FROM mst_questionnaire, mst_section, mst_audit,  mst_audit_schedule  WHERE  mst_questionnaire.section_id = mst_section.sk_section_id AND mst_audit.results = '0' AND mst_questionnaire.type = '"+type+"' AND mst_audit.question_id = mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id = '"+did+"' AND mst_questionnaire.essential != 'O' AND mst_questionnaire.brand = '"+brand+"' AND mst_audit_schedule.sk_audit_id = mst_audit.audit_schedule_id AND mst_audit_schedule.quarter = '"+phase+"' AND mst_audit_schedule.year = '"+year+"' GROUP BY  mst_questionnaire.number,mst_audit.outlet_id) as r2 on r1.sn=r2.number";
		
		}
		System.out.println(sql);
		
		return template.query(sql,
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final QuestionBean qBean = new QuestionBean();
						qBean.setSection(rs.getString("section_name"));
						try{
							String temp [] = rs.getString("section_name").split("-");
							
							qBean.setSection(temp [1]);
							}catch (Exception e) {
								qBean.setSection(rs.getString("section_name"));
							}
						qBean.setSection_id(rs.getString("sk_section_id"));
						qBean.setStandard(rs.getString("standard"));
						qBean.setRequirement(rs.getString("requirement"));
						qBean.setComment(rs.getString("comment"));
						qBean.setNumber(rs.getString("number"));
						qBean.setXs(rs.getString("xs"));
						qBean.setS(rs.getString("s"));
						qBean.setM(rs.getString("m"));
						qBean.setL(rs.getString("l"));
						qBean.setXl(rs.getString("xl"));
						qBean.setXxl(rs.getString("xxl"));
						qBean.setCheck(rs.getString("type_of_check"));
						qBean.setEssential(rs.getString("essential"));
						qBean.setQuestion(rs.getString("question"));
						qBean.setObservation(rs.getString("observation"));
						qBean.setPerson(rs.getString("suggested_person"));
						qBean.setQuestionnaire_id(rs.getString("sk_questionnaire_id"));
						qBean.setQuestionId(rs.getString("sk_questionnaire_id"));
						
						num  = rs.getString("number_count");
						qnnid = rs.getString("sk_questionnaire_id");
						/*qBean.setAnswer(rs.getString("results"));*/
						/*qBean.setAudit_id(rs.getString("sk_audit_id"));*/
						/*audis = rs.getString("sk_audit_id");*/
						
						
						try{
							QuestionBean qBean1 = new QuestionBean();
						getImagess(rs.getString("audit_schedule_id"), rs.getString("question_id"), qBean1);
						System.out.println("MANJU IMG DAO:"+qBean1.getImg());
						qBean.setReference_image(qBean1.getImg());
						}catch (Exception e) {
							qBean.setReference_image("N-A.jpg");
						}

						if (rs.getString("auditor_comment").isEmpty()	|| rs.getString("auditor_comment") == "" || rs.getString("auditor_comment").equals(null)) {
							qBean.setAuditor_comment("Obseravtion Noted");
							
						} else {
							
							qBean.setAuditor_comment(rs.getString("auditor_comment"));
						}
						qBean.setDealer_comment(rs.getString("dealer_comment"));
						if (rs.getString("results").equals("0")) {
							qBean.setAnswer_type("NO");
						} else {
							qBean.setAnswer_type("YES");
						}
						if (rs.getString("pmo_exception_remarks") == null
								|| rs.getString("pmo_exception_remarks").equals(null)) {
							qBean.setException_remarks("False");
						} else {
							qBean.setException_remarks("True");
						}
						qBean.setOutlet_id(rs.getString("outlet_id"));
						
						
						
							 
						System.out.println("AUDIS :"+audis);
						System.out.println("SELECT outlet_name FROM mst_outlet WHERE sk_outlet_id='"+rs.getString("outlet_id")+"'");
						template.query("SELECT outlet_name FROM mst_outlet WHERE sk_outlet_id='"+rs.getString("outlet_id")+"';",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setOutlets(rs.getString("outlet_name"));
										return qBean;
									}
								});
						/*System.out.println("SELECT image_name FROM txn_audit_images WHERE audit_scheduled_id='"+audis+"' AND question_id='"+qnnid+"'");
						template.query("SELECT image_name FROM txn_audit_images WHERE audit_scheduled_id='"+audis+"' AND question_id='"+qnnid+"';",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setReference_image(rs.getString("image_name"));
										return qBean;
									}
								});*/
						
						System.out.println("AUDIS :"+audis);
						System.out.println(
								"SELECT mst_audit_schedule.quarter,mst_audit_schedule.outlet_id,mst_audit_schedule.year from mst_audit_schedule,mst_audit WHERE mst_audit.sk_audit_id='"
										+ audis
										+ "' AND mst_audit.audit_schedule_id=mst_audit_schedule.sk_audit_id;");
						template.query(
								"SELECT mst_audit_schedule.quarter,mst_audit_schedule.outlet_id,mst_audit_schedule.year from mst_audit_schedule,mst_audit WHERE mst_audit.sk_audit_id='"
										+ audis
										+ "' AND mst_audit.audit_schedule_id=mst_audit_schedule.sk_audit_id;",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setTime(rs.getString("year"));
										System.out.println(rs.getString("year") + "sad");
										qBean.setAvg(rs.getString("quarter"));
										return qBean;
									}
								});
						 
						return qBean;
					}
				});
		
	}

	public List<QuestionBean> getPenalizedStandardAnnexure(UserBean uBean, final String did, String type, String phase,
			String year) {

		System.out.println(
				"SELECT * FROM(SELECT mst_questionnaire.*,mst_section.sk_section_id,mst_section.section_name,mst_section.type as sec_type,mst_section.brand as sec_brand,mst_section.status as sec_status FROM mst_questionnaire,mst_section,mst_audit WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"
						+ type
						+ "' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"
						+ did
						+ "' AND mst_questionnaire.essential!='O') as res1 LEFT JOIN (SELECT mst_questionnaire.number as id, COUNT(*) as c1 FROM mst_questionnaire,mst_section,mst_audit WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"
						+ type
						+ "' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"
						+ did
						+ "' AND mst_questionnaire.essential!='O' GROUP BY number HAVING c1 > 1 ) as res2 ON res1.number=res2.id WHERE c1 is not null ORDER BY res1.sk_questionnaire_id ");
		return template.query(
				"SELECT * FROM(SELECT mst_questionnaire.*,mst_section.sk_section_id,mst_section.section_name,mst_section.type as sec_type,mst_section.brand as sec_brand,mst_section.status as sec_status FROM mst_questionnaire,mst_section,mst_audit WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"
						+ type
						+ "' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"
						+ did
						+ "' AND mst_questionnaire.essential!='O') as res1 LEFT JOIN (SELECT mst_questionnaire.number as id, COUNT(*) as c1 FROM mst_questionnaire,mst_section,mst_audit WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='"
						+ type
						+ "' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"
						+ did
						+ "' AND mst_questionnaire.essential!='O' GROUP BY number HAVING c1 > 1 ) as res2 ON res1.number=res2.id WHERE c1 is not null ORDER BY res1.sk_questionnaire_id",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final QuestionBean qBean = new QuestionBean();
						qBean.setSection(rs.getString("section_name"));
						qBean.setSection_id(rs.getString("sk_section_id"));
						qBean.setStandard(rs.getString("standard"));
						qBean.setRequirement(rs.getString("requirement"));
						qBean.setComment(rs.getString("comment"));
						qBean.setNumber(rs.getString("number"));
						qBean.setXs(rs.getString("xs"));
						qBean.setS(rs.getString("s"));
						qBean.setM(rs.getString("m"));
						qBean.setL(rs.getString("l"));
						qBean.setXl(rs.getString("xl"));
						qBean.setXxl(rs.getString("xxl"));
						qBean.setCheck(rs.getString("type_of_check"));
						qBean.setEssential(rs.getString("essential"));
						qBean.setQuestion(rs.getString("question"));
						qBean.setObservation(rs.getString("observation"));
						qBean.setPerson(rs.getString("suggested_person"));
						qBean.setQuestionnaire_id(rs.getString("sk_questionnaire_id"));

						System.out.println("SELECT mst_audit.* FROM mst_audit WHERE mst_audit.question_id='"
								+ rs.getString("sk_questionnaire_id") + "'  AND mst_audit.dealer_id='" + did
								+ "' AND mst_audit.results='0';");
						template.query("SELECT mst_audit.* FROM mst_audit WHERE mst_audit.question_id='"
								+ rs.getString("sk_questionnaire_id") + "'  AND mst_audit.dealer_id='" + did
								+ "' AND mst_audit.results='0';", new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setAnswer(rs.getString("results"));
										qBean.setAudit_id(rs.getString("sk_audit_id"));
										audit_id = rs.getString("sk_audit_id");

										if (rs.getString("auditor_comment").isEmpty()
												|| rs.getString("auditor_comment") == ""
												|| rs.getString("auditor_comment").equals(null)) {
											qBean.setAuditor_comment("Obseravtion Noted");
										} else {
											qBean.setAuditor_comment(rs.getString("auditor_comment"));
											System.out.println("AC :" + rs.getString("auditor_comment"));
										}
										qBean.setDealer_comment(rs.getString("dealer_comment"));
										if (rs.getString("results").equals("0")) {
											qBean.setAnswer_type("NO");
										} else {
											qBean.setAnswer_type("YES");
										}
										if (rs.getString("pmo_exception_remarks") == null
												|| rs.getString("pmo_exception_remarks").equals(null)) {
											qBean.setException_remarks("False");
										} else {
											qBean.setException_remarks("True");
										}
										qBean.setOutlet_id(rs.getString("outlet_id"));

										System.out.println("OUTLET :" + rs.getString("outlet_id"));
										
										try{
											QuestionBean qBean1 = new QuestionBean();
										getImagess(rs.getString("audit_schedule_id"), rs.getString("question_id"), qBean1);
										System.out.println("MANJU IMG DAO:"+qBean1.getImg());
										qBean.setReference_image(qBean1.getImg());
										}catch (Exception e) {
											qBean.setReference_image("N-A.jpg");
										}
										return qBean;
									}
								});
						
						
						
						System.out.println(
								"SELECT mst_audit_schedule.quarter,mst_audit_schedule.outlet_id,mst_audit_schedule.year from mst_audit_schedule,mst_audit WHERE mst_audit.sk_audit_id='"
										+ audit_id
										+ "' AND mst_audit.audit_schedule_id=mst_audit_schedule.sk_audit_id;");
						template.query(
								"SELECT mst_audit_schedule.quarter,mst_audit_schedule.outlet_id,mst_audit_schedule.year from mst_audit_schedule,mst_audit WHERE mst_audit.sk_audit_id='"
										+ audit_id
										+ "' AND mst_audit.audit_schedule_id=mst_audit_schedule.sk_audit_id;",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setTime(rs.getString("year"));
										System.out.println(rs.getString("year") + "sad");
										qBean.setAvg(rs.getString("quarter"));

										return qBean;
									}
								});
						return qBean;
					}
				});
	}

	public List<QuestionBean> getAreaOfImprovementsStandard(UserBean uBean, final String did, String type, String phase,
			String year,String brand) {
		String sql = "";
		if(phase.equals("H1"))
		{
			sql="SELECT mst_questionnaire.*,mst_section.*,mst_audit_schedule.quarter,mst_audit_schedule.year FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND  mst_questionnaire.type='"
					+ type
					+ "' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"
					+ did + "' AND  mst_questionnaire.essential!='O' AND mst_questionnaire.brand='"+brand+"' AND mst_audit.exception_timeline IS NOT NULL AND mst_audit.exception_timeline!='' AND mst_audit.results='1' AND mst_audit.pmo_review_status='Open' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id AND mst_audit_schedule.quarter='"+phase+"' AND mst_audit_schedule.year='"+year+"'";
		}
		if(phase.equals("H2"))
		{
			/*sql="SELECT mst_questionnaire.*,mst_section.*,mst_audit_schedule.quarter,mst_audit_schedule.year FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND  mst_questionnaire.type='"
					+ type
					+ "' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"
					+ did + "' AND  mst_questionnaire.essential!='O' AND mst_questionnaire.brand='"+brand+"' AND mst_audit.exception_timeline IS NOT NULL AND mst_audit.exception_timeline!='' AND mst_audit.results='1' AND mst_audit.pmo_review_status='Open' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id  AND mst_audit_schedule.year='"+year+"'";*/
			sql="SELECT mst_questionnaire.*,mst_section.*,mst_audit_schedule.quarter,mst_audit_schedule.year FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND  mst_questionnaire.type='"+type+"' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"+did+"' AND  mst_questionnaire.essential!='O' AND mst_questionnaire.brand='"+brand+"' AND mst_audit.exception_timeline IS NOT NULL AND mst_audit.exception_timeline!='' AND mst_audit.results='1' AND mst_audit.pmo_review_status='Open' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id  AND mst_audit_schedule.year='"+year+"' AND quarter='"+phase+"' UNION SELECT mst_questionnaire.*,mst_section.*,mst_audit_schedule.quarter,mst_audit_schedule.year FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND  mst_questionnaire.type='"+type+"' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='"+did+"' AND  mst_questionnaire.essential!='O' AND mst_questionnaire.brand='"+brand+"' AND mst_audit.exception_timeline IS NOT NULL AND mst_audit.exception_timeline!='' AND mst_audit.results='1' AND mst_audit.pmo_review_status='Open' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id  AND mst_audit_schedule.year='"+year+"' AND quarter='H1' AND mst_questionnaire.number IN (SELECT mst_questionnaire.number FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id AND mst_audit.results='0' AND mst_questionnaire.type='Sales' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='2' AND mst_questionnaire.essential!='O' AND mst_questionnaire.brand='"+brand+"' AND mst_audit_schedule.sk_audit_id=mst_audit.audit_schedule_id AND mst_audit_schedule.year='"+year+"' and mst_audit_schedule.quarter='"+phase+"')";
		}
		
		System.out.println(sql);
		return template.query(sql,new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final QuestionBean qBean = new QuestionBean();
						qBean.setSection(rs.getString("section_name"));
						try{
							String temp [] = rs.getString("section_name").split("-");
							
							qBean.setSection(temp [1]);
							}catch (Exception e) {
								qBean.setSection(rs.getString("section_name"));
							}
						qBean.setSection_id(rs.getString("sk_section_id"));
						qBean.setStandard(rs.getString("standard"));
						qBean.setRequirement(rs.getString("requirement"));
						qBean.setComment(rs.getString("comment"));
						qBean.setNumber(rs.getString("number"));
						qBean.setXs(rs.getString("xs"));
						qBean.setS(rs.getString("s"));
						qBean.setM(rs.getString("m"));
						qBean.setL(rs.getString("l"));
						qBean.setXl(rs.getString("xl"));
						qBean.setXxl(rs.getString("xxl"));
						qBean.setCheck(rs.getString("type_of_check"));
						qBean.setEssential(rs.getString("essential"));
						qBean.setQuestion(rs.getString("question"));
						qBean.setObservation(rs.getString("observation"));
						qBean.setPerson(rs.getString("suggested_person"));
						qBean.setQuestionnaire_id(rs.getString("sk_questionnaire_id"));
						qBean.setPhase(rs.getString("quarter"));

						System.out.println("SELECT mst_audit.* FROM mst_audit WHERE mst_audit.question_id='"
								+ rs.getString("sk_questionnaire_id") + "'  AND mst_audit.dealer_id='" + did
								+ "' AND exception_timeline IS NOT NULL AND exception_timeline!='';");
						template.query("SELECT mst_audit.* FROM mst_audit WHERE mst_audit.question_id='"
								+ rs.getString("sk_questionnaire_id") + "'  AND mst_audit.dealer_id='" + did
								+ "' AND exception_timeline IS NOT NULL AND exception_timeline!='';", new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setAnswer(rs.getString("results"));
										qBean.setAudit_id(rs.getString("sk_audit_id"));
										audit_id = rs.getString("sk_audit_id");
										try{
										if (rs.getString("auditor_comment").isEmpty()
												|| rs.getString("auditor_comment") == ""
												|| rs.getString("auditor_comment").equals(null)) {
											qBean.setAuditor_comment("Obseravtion Noted");
										} else {
											qBean.setAuditor_comment(rs.getString("auditor_comment"));
											System.out.println("AC :" + rs.getString("auditor_comment"));
										}
										}catch (Exception e) {
											qBean.setAuditor_comment(rs.getString("auditor_comment"));
											System.out.println("AC :" + rs.getString("auditor_comment"));
										}
										qBean.setDealer_comment(rs.getString("dealer_comment"));
										if (rs.getString("results").equals("0")) {
											qBean.setAnswer_type("NO");
										} else {
											qBean.setAnswer_type("YES");
										}
										if (rs.getString("pmo_exception_remarks") == null
												|| rs.getString("pmo_exception_remarks").equals(null)) {
											qBean.setException_remarks("False");
										} else {
											qBean.setException_remarks("True");
										}
										qBean.setOutlet_id(rs.getString("outlet_id"));
										
										qBean.setException_remarks(rs.getString("pmo_exception_remarks"));
										qBean.setTime(rs.getString("exception_timeline"));
										

										return qBean;
									}
								});
						System.out.println(
								"SELECT mst_audit_schedule.quarter,mst_audit_schedule.outlet_id,mst_audit_schedule.year from mst_audit_schedule,mst_audit WHERE mst_audit.sk_audit_id='"
										+ audit_id
										+ "' AND mst_audit.audit_schedule_id=mst_audit_schedule.sk_audit_id;");
						template.query(
								"SELECT mst_audit_schedule.quarter,mst_audit_schedule.outlet_id,mst_audit_schedule.year from mst_audit_schedule,mst_audit WHERE mst_audit.sk_audit_id='"
										+ audit_id
										+ "' AND mst_audit.audit_schedule_id=mst_audit_schedule.sk_audit_id;",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										/*qBean.setTime(rs.getString("year"));*/
										System.out.println(rs.getString("year") + "sad");
										qBean.setAvg(rs.getString("quarter"));
										

										return qBean;
									}
								});
						return qBean;
					}
				});
	}
	
	public List<QuestionBean> getDealersToBeNotified(QuestionBean qBean, String current_date) {
		System.out.println("SELECT *   ,(SELECT count(fourth_notification) as fourth   FROM mst_notification_scheduler WHERE fourth_notification='"+current_date+"' and fourth_notification_status='false' ) as fourth,(SELECT count(third_notification) as third   FROM mst_notification_scheduler WHERE third_notification='"+current_date+"' and third_notification_status='false') as third,(SELECT count(second_notification) as second   FROM mst_notification_scheduler WHERE second_notification='"+current_date+"' and second_notification_status='false') as second,(SELECT count(first_notification) as second   FROM mst_notification_scheduler WHERE first_notification='"+current_date+"' and first_notification_status='false') as first,(SELECT count(fifth_notification) as fifth FROM mst_notification_scheduler WHERE fifth_notification='"+current_date+"' and first_notification_status='false') as fifth FROM mst_notification_scheduler WHERE (first_notification='"+current_date+"' AND first_notification_status='false' OR second_notification='"+current_date+"' AND second_notification_status='false' OR third_notification='"+current_date+"' AND third_notification_status='false' OR fourth_notification='"+current_date+"' AND fourth_notification_status='false' OR fifth_notification='"+current_date+"' AND fifth_notification_status='false') AND dealer_status='false' GROUP BY dealer_id;");
		return template.query("SELECT *   ,(SELECT count(fourth_notification) as fourth   FROM mst_notification_scheduler WHERE fourth_notification='"+current_date+"' and fourth_notification_status='false' ) as fourth,(SELECT count(third_notification) as third   FROM mst_notification_scheduler WHERE third_notification='"+current_date+"' and third_notification_status='false') as third,(SELECT count(second_notification) as second   FROM mst_notification_scheduler WHERE second_notification='"+current_date+"' and second_notification_status='false') as second,(SELECT count(first_notification) as second   FROM mst_notification_scheduler WHERE first_notification='"+current_date+"' and first_notification_status='false') as first,(SELECT count(fifth_notification) as fifth FROM mst_notification_scheduler WHERE fifth_notification='"+current_date+"' and first_notification_status='false') as fifth FROM mst_notification_scheduler WHERE (first_notification='"+current_date+"' AND first_notification_status='false' OR second_notification='"+current_date+"' AND second_notification_status='false' OR third_notification='"+current_date+"' AND third_notification_status='false' OR fourth_notification='"+current_date+"' AND fourth_notification_status='false' OR fifth_notification='"+current_date+"' AND fifth_notification_status='false') AND dealer_status='false' GROUP BY dealer_id;", new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final QuestionBean qBean = new QuestionBean();
						qBean.setDealer_id(rs.getString("dealer_id"));
						
						return qBean;
					}
				});
	}

	public List<QuestionBean> getPendingSchedulers(QuestionBean qBean, String current_date,String dealer_id) {
		System.out.println("SELECT *   ,(SELECT count(fourth_notification) as fourth   FROM mst_notification_scheduler WHERE fourth_notification='"+current_date+"' and fourth_notification_status='false' ) as fourth,(SELECT count(third_notification) as third   FROM mst_notification_scheduler WHERE third_notification='"+current_date+"' and third_notification_status='false') as third,(SELECT count(second_notification) as second   FROM mst_notification_scheduler WHERE second_notification='"+current_date+"' and second_notification_status='false') as second,(SELECT count(first_notification) as second   FROM mst_notification_scheduler WHERE first_notification='"+current_date+"' and first_notification_status='false') as first,(SELECT count(fifth_notification) as fifth FROM mst_notification_scheduler WHERE fifth_notification='"+current_date+"' and first_notification_status='false') as fifth FROM mst_notification_scheduler WHERE (first_notification='"+current_date+"' AND first_notification_status='false' OR second_notification='"+current_date+"' AND second_notification_status='false' OR third_notification='"+current_date+"' AND third_notification_status='false' OR fourth_notification='"+current_date+"' AND fourth_notification_status='false' OR fifth_notification='"+current_date+"' AND fifth_notification_status='false') AND dealer_status='false' AND dealer_id='"+dealer_id+"';");
		return template.query("SELECT *   ,(SELECT count(fourth_notification) as fourth   FROM mst_notification_scheduler WHERE fourth_notification='"+current_date+"' and fourth_notification_status='false' ) as fourth,(SELECT count(third_notification) as third   FROM mst_notification_scheduler WHERE third_notification='"+current_date+"' and third_notification_status='false') as third,(SELECT count(second_notification) as second   FROM mst_notification_scheduler WHERE second_notification='"+current_date+"' and second_notification_status='false') as second,(SELECT count(first_notification) as second   FROM mst_notification_scheduler WHERE first_notification='"+current_date+"' and first_notification_status='false') as first,(SELECT count(fifth_notification) as fifth FROM mst_notification_scheduler WHERE fifth_notification='"+current_date+"' and first_notification_status='false') as fifth FROM mst_notification_scheduler WHERE (first_notification='"+current_date+"' AND first_notification_status='false' OR second_notification='"+current_date+"' AND second_notification_status='false' OR third_notification='"+current_date+"' AND third_notification_status='false' OR fourth_notification='"+current_date+"' AND fourth_notification_status='false' OR fifth_notification='"+current_date+"' AND fifth_notification_status='false') AND dealer_status='false' AND dealer_id='"+dealer_id+"';", new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final QuestionBean qBean = new QuestionBean();
						String auditId = rs.getString("audit_id");
						String id = rs.getString("sk_notification_id");
						if(!rs.getString("first").equals("0"))
						{
							
							qBean.setResult("30 Days");
							template.execute("UPDATE mst_notification_scheduler SET first_notification_status='true' WHERE sk_notification_id='"+id+"'");
							getServiceHeadEmails(qBean,rs.getString("outlet_id"));
							String service_head_emails = qBean.getEmail();
							getDealerBussinessHeadEmails(qBean,rs.getString("outlet_id"));
							String business_head_emails = qBean.getEmail();
							getDealerPrincipleEmails(qBean,rs.getString("outlet_id"));
							String DPemails = qBean.getEmail();
							getEYPMOsEmails(qBean,rs.getString("outlet_id"));
							String EYIemails = qBean.getEmail();
							getSalesHeadEmails(qBean,rs.getString("outlet_id"));
							String sales_head = qBean.getEmail();
							String emails = service_head_emails+","+sales_head+","+business_head_emails+","+DPemails+","+EYIemails+","+"Riga.singh@bmw.in";
							qBean.setEmail(emails);
							System.out.println("one"+emails);
							
							
						}
						 if(!rs.getString("second").equals("0"))
						{
							qBean.setResult("15 Days");
							template.execute("UPDATE mst_notification_scheduler SET second_notification_status='true' WHERE sk_notification_id='"+id+"'");
							getServiceHeadEmails(qBean,rs.getString("outlet_id"));
							String service_head_emails = qBean.getEmail();
							getDealerBussinessHeadEmails(qBean,rs.getString("outlet_id"));
							String business_head_emails = qBean.getEmail();
							getDealerPrincipleEmails(qBean,rs.getString("outlet_id"));
							String DPemails = qBean.getEmail();
							getEYPMOsEmails(qBean,rs.getString("outlet_id"));
							String EYIemails = qBean.getEmail();
							getRegionalManagerEmails(qBean,rs.getString("outlet_id"));
							String RMEmails = qBean.getEmail();
							getSalesHeadEmails(qBean,rs.getString("outlet_id"));
							String sales_head = qBean.getEmail();
							String emails = service_head_emails+","+sales_head+","+business_head_emails+","+DPemails+","+EYIemails+","+RMEmails+","+"Riga.singh@bmw.in";
							qBean.setEmail(emails);
							System.out.println("two"+emails);
						}
						 if(!rs.getString("third").equals("0"))
						{
							qBean.setResult("0 Days");
							template.execute("UPDATE mst_notification_scheduler SET third_notification_status='true' WHERE sk_notification_id='"+id+"'");
							getServiceHeadEmails(qBean,rs.getString("outlet_id"));
							String service_head_emails = qBean.getEmail();
							getDealerBussinessHeadEmails(qBean,rs.getString("outlet_id"));
							String business_head_emails = qBean.getEmail();
							getDealerPrincipleEmails(qBean,rs.getString("outlet_id"));
							String DPemails = qBean.getEmail();
							getEYPMOsEmails(qBean,rs.getString("outlet_id"));
							String EYIemails = qBean.getEmail();
							getRegionalManagerEmails(qBean,rs.getString("outlet_id"));
							String RMEmails = qBean.getEmail();
							getSalesHeadEmails(qBean,rs.getString("outlet_id"));
							String sales_head = qBean.getEmail();
							String emails = service_head_emails+","+sales_head+","+business_head_emails+","+DPemails+","+EYIemails+","+RMEmails+","+"Riga.singh@bmw.in";
							qBean.setEmail(emails);
							System.out.println("third"+emails);
						}
						 if(!rs.getString("fourth").equals("0"))
						{
							qBean.setResult("-1 Days");
							template.execute("UPDATE mst_notification_scheduler SET fourth_notification_status='true' WHERE sk_notification_id='"+id+"'");
							getServiceHeadEmails(qBean,rs.getString("outlet_id"));
							String service_head_emails = qBean.getEmail();
							getDealerBussinessHeadEmails(qBean,rs.getString("outlet_id"));
							String business_head_emails = qBean.getEmail();
							getDealerPrincipleEmails(qBean,rs.getString("outlet_id"));
							String DPemails = qBean.getEmail();
							getEYPMOsEmails(qBean,rs.getString("outlet_id"));
							String EYIemails = qBean.getEmail();
							getRegionalManagerEmails(qBean,rs.getString("outlet_id"));
							String RMEmails = qBean.getEmail();
							getSalesHeadEmails(qBean,rs.getString("outlet_id"));
							String sales_head = qBean.getEmail();
							String emails = service_head_emails+","+sales_head+","+business_head_emails+","+DPemails+","+EYIemails+","+RMEmails+","+"Riga.singh@bmw.in"+","+"MANISH.SACHDEV@BMW.IN";
							qBean.setEmail(emails);
							System.out.println("fourth"+emails);
						}
						 if(!rs.getString("fifth").equals("0"))
						{
							qBean.setResult("-2 Days");
							template.execute("UPDATE mst_notification_scheduler SET fifth_notification_status='true' WHERE sk_notification_id='"+id+"'");
							getServiceHeadEmails(qBean,rs.getString("outlet_id"));
							String service_head_emails = qBean.getEmail();
							getDealerBussinessHeadEmails(qBean,rs.getString("outlet_id"));
							String business_head_emails = qBean.getEmail();
							getDealerPrincipleEmails(qBean,rs.getString("outlet_id"));
							String DPemails = qBean.getEmail();
							getEYPMOsEmails(qBean,rs.getString("outlet_id"));
							String EYIemails = qBean.getEmail();
							getRegionalManagerEmails(qBean,rs.getString("outlet_id"));
							String RMEmails = qBean.getEmail();
							getSalesHeadEmails(qBean,rs.getString("outlet_id"));
							String sales_head = qBean.getEmail();
							String emails = service_head_emails+","+sales_head+","+business_head_emails+","+DPemails+","+EYIemails+","+RMEmails+","+"Riga.singh@bmw.in"+","+"MANISH.SACHDEV@BMW.IN";
							qBean.setEmail(emails);
							System.out.println("fifth"+emails);
						}
						qBean.setDealer(rs.getString("dealer_name"));
						qBean.setNumber(rs.getString("standard_number"));
						qBean.setStandard(rs.getString("standard_name"));
						qBean.setOutlets(rs.getString("outlet_name"));
						qBean.setObservation(rs.getString("observation"));
						qBean.setException_remarks(rs.getString("exception_remarks"));
						qBean.setTime(rs.getString("third_notification"));
						qBean.setDealer_id(rs.getString("dealer_id"));
						qBean.setOutlet_id(rs.getString("outlet_id"));
						qBean.setAudit_schedule_id(rs.getString("audit_id"));
						qBean.setPhase(rs.getString("phase"));
						qBean.setYear(rs.getString("year"));
						
						return qBean;
					}
				});
	}

	

	

	

	

	

	public QuestionBean getTotalCompletedAudits(final QuestionBean qBean) {
		String sql = "";
		String brand_filter = "";
		String type_filter = "";
		String region_filter = "";
		String dealer_filter = "";

		if (!qBean.getBrand().equals("ALL")) {
			brand_filter = " AND mst_outlet.brand='" + qBean.getBrand() + "'";
		}

		if (!qBean.getType().equals("ALL")) {
			type_filter = " AND mst_audit_schedule.outlet_type='" + qBean.getType() + "'";
		}

		if (!qBean.getDealer().equals("ALL")) {
			dealer_filter = " AND mst_audit_schedule.dealership_id='" + qBean.getDealer() + "'";
		}

		if (!qBean.getRegion().equals("ALL")) {
			region_filter = " AND mst_outlet.region='" + qBean.getRegion() + "'";
		}

		sql = "SELECT COUNT(mst_audit_schedule.sk_audit_id) as audit_count  FROM mst_audit_schedule,mst_outlet WHERE mst_audit_schedule.status='closed' AND mst_audit_schedule.quarter='"
				+ qBean.getPhase() + "' AND mst_audit_schedule.year='" + qBean.getYear() + "'" + brand_filter + ""
				+ type_filter + "" + dealer_filter + "" + region_filter
				+ " AND mst_outlet.sk_outlet_id=mst_audit_schedule.outlet_id";

		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<QuestionBean>() {
			public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
				qBean.setAudit_count(rs.getString("audit_count"));
				return qBean;

			}
		});

	}

	public QuestionBean getTotalSubmitedAudits(final QuestionBean qBean) {
		String sql = "";
		String brand_filter = "";
		String type_filter = "";
		String region_filter = "";
		String dealer_filter = "";

		if (!qBean.getBrand().equals("ALL")) {
			brand_filter = " AND mst_outlet.brand='" + qBean.getBrand() + "'";
		}

		if (!qBean.getType().equals("ALL")) {
			type_filter = " AND mst_audit_schedule.outlet_type='" + qBean.getType() + "'";
		}

		if (!qBean.getDealer().equals("ALL")) {
			dealer_filter = " AND mst_audit_schedule.dealership_id='" + qBean.getDealer() + "'";
		}

		if (!qBean.getRegion().equals("ALL")) {
			region_filter = " AND mst_outlet.region='" + qBean.getRegion() + "'";
		}

		sql = "SELECT COUNT(mst_audit_schedule.sk_audit_id) as audit_count  FROM mst_audit_schedule,mst_outlet WHERE mst_audit_schedule.status='completed' AND mst_audit_schedule.quarter='"
				+ qBean.getPhase() + "' AND mst_audit_schedule.year='" + qBean.getYear() + "'" + brand_filter + ""
				+ type_filter + "" + dealer_filter + "" + region_filter
				+ " AND mst_outlet.sk_outlet_id=mst_audit_schedule.outlet_id";

		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<QuestionBean>() {
			public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
				qBean.setAudit_count_ans(rs.getString("audit_count"));
				return qBean;

			}
		});

	}

	public QuestionBean getToBeDoneAudits(final QuestionBean qBean) {

		String sql = "";
		String brand_filter = "";
		String type_filter = "";
		String region_filter = "";
		String dealer_filter = "";

		if (!qBean.getBrand().equals("ALL")) {
			brand_filter = " AND mst_outlet.brand='" + qBean.getBrand() + "'";
		}

		if (!qBean.getType().equals("ALL")) {
			type_filter = " AND mst_audit_schedule.outlet_type='" + qBean.getType() + "'";
		}

		if (!qBean.getDealer().equals("ALL")) {
			dealer_filter = " AND mst_audit_schedule.dealership_id='" + qBean.getDealer() + "'";
		}

		if (!qBean.getRegion().equals("ALL")) {
			region_filter = " AND mst_outlet.region='" + qBean.getRegion() + "'";
		}

		sql = "SELECT COUNT(mst_audit_schedule.sk_audit_id) as audit_count  FROM mst_audit_schedule,mst_outlet WHERE mst_audit_schedule.status='active' AND mst_audit_schedule.quarter='"
				+ qBean.getPhase() + "' AND mst_audit_schedule.year='" + qBean.getYear() + "'" + brand_filter + ""
				+ type_filter + "" + dealer_filter + "" + region_filter
				+ " AND mst_outlet.sk_outlet_id=mst_audit_schedule.outlet_id";

		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<QuestionBean>() {
			public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
				qBean.setAudit_qn_count(rs.getString("audit_count"));
				return qBean;

			}
		});
	}

	public QuestionBean getDealerNationalScore(final QuestionBean qBean) {

		String sql = "";
		String brand_filter = "";
		String type_filter = "";
		String region_filter = "";
		String dealer_filter = "";

		if (!qBean.getBrand().equals("ALL")) {
			brand_filter = " AND mst_outlet.brand='" + qBean.getBrand() + "'";
		}

		if (!qBean.getType().equals("ALL")) {
			type_filter = " AND mst_dealer_final_score.outlet_type='" + qBean.getType() + "'";
		}

		if (!qBean.getDealer().equals("ALL")) {
			dealer_filter = " AND mst_dealer_final_score.dealer_id='" + qBean.getDealer() + "'";
		}

		if (!qBean.getRegion().equals("ALL")) {
			region_filter = " AND mst_outlet.region='" + qBean.getRegion() + "'";
		}
		sql = "SELECT IFNULL(round(AVG(mst_dealer_final_score.score)),0) as score FROM mst_dealer_final_score,mst_outlet,mst_dealership WHERE mst_dealer_final_score.quarter='"
				+ qBean.getPhase() + "'" + brand_filter + "" + dealer_filter + "" + region_filter + "" + type_filter
				+ " AND mst_dealer_final_score.year='" + qBean.getYear()
				+ "' AND mst_outlet.dealership_id=mst_dealer_final_score.dealer_id";
		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<QuestionBean>() {
			public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
				qBean.setDealer_avg(rs.getString("score") + "%");
				return qBean;

			}
		});

	}

	public QuestionBean getDealerNationalSalesScore(final QuestionBean qBean) {

		String sql = "";
		String brand_filter = "";
		String type_filter = "";
		String region_filter = "";
		String dealer_filter = "";

		if (!qBean.getBrand().equals("ALL")) {
			brand_filter = " AND mst_outlet.brand='" + qBean.getBrand() + "'";
		}

		if (!qBean.getType().equals("ALL")) {
			type_filter = " AND mst_dealer_final_score.outlet_type='Sales'";
		}

		if (!qBean.getDealer().equals("ALL")) {
			dealer_filter = " AND mst_dealer_final_score.dealer_id='" + qBean.getDealer() + "'";
		}

		if (!qBean.getRegion().equals("ALL")) {
			region_filter = " AND mst_outlet.region='" + qBean.getRegion() + "'";
		}
		sql = "SELECT IFNULL(round(AVG(mst_dealer_final_score.score)),0) as score FROM mst_dealer_final_score,mst_outlet,mst_dealership WHERE mst_dealer_final_score.quarter='"
				+ qBean.getPhase() + "'" + brand_filter + "" + dealer_filter + "" + region_filter
				+ " AND mst_dealer_final_score.outlet_type='Sales' AND mst_dealer_final_score.year='" + qBean.getYear()
				+ "' AND mst_outlet.dealership_id=mst_dealer_final_score.dealer_id";
		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<QuestionBean>() {
			public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
				qBean.setDealer_sales_avg(rs.getString("score") + "%");
				return qBean;

			}
		});

	}

	public QuestionBean getDealerNationalServiceScore(final QuestionBean qBean) {
		String sql = "";
		String brand_filter = "";
		String type_filter = "";
		String region_filter = "";
		String dealer_filter = "";

		if (!qBean.getBrand().equals("ALL")) {
			brand_filter = " AND mst_outlet.brand='" + qBean.getBrand() + "'";
		}

		if (!qBean.getType().equals("ALL")) {
			type_filter = " AND mst_dealer_final_score.outlet_type='Service'";
		}

		if (!qBean.getDealer().equals("ALL")) {
			dealer_filter = " AND mst_dealer_final_score.dealer_id='" + qBean.getDealer() + "'";
		}

		if (!qBean.getRegion().equals("ALL")) {
			region_filter = " AND mst_outlet.region='" + qBean.getRegion() + "'";
		}
		sql = "SELECT IFNULL(round(AVG(mst_dealer_final_score.score)),0) as score FROM mst_dealer_final_score,mst_outlet,mst_dealership WHERE mst_dealer_final_score.quarter='"
				+ qBean.getPhase() + "'" + brand_filter + "" + dealer_filter + "" + region_filter
				+ " AND mst_dealer_final_score.outlet_type='Service' AND mst_dealer_final_score.year='"
				+ qBean.getYear() + "' AND mst_outlet.dealership_id=mst_dealer_final_score.dealer_id";
		System.out.println(sql);
		return template.queryForObject(sql, new RowMapper<QuestionBean>() {
			public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
				qBean.setDealer_service_avg(rs.getString("score") + "%");
				return qBean;

			}
		});

	}

	public List<UserBean> getDealerListByRank(UserBean uBean) {
		System.out.println(
				"SELECT ROUND(AVG(mst_dealer_final_score.score)) as score,mst_dealership.dealership_name FROM mst_dealer_final_score,mst_dealership WHERE mst_dealer_final_score.score_type='Contractual score' GROUP BY mst_dealer_final_score.dealer_id  ORDER BY `score` DESC  ;");
		return template.query(
				"SELECT ROUND(AVG(mst_dealer_final_score.score)) as score,mst_dealership.dealership_name FROM mst_dealer_final_score,mst_dealership WHERE mst_dealer_final_score.score_type='Contractual score' GROUP BY mst_dealer_final_score.dealer_id  ORDER BY `score` DESC",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						UserBean uBean = new UserBean();
						uBean.setScore(rs.getString("score"));
						uBean.setDealership_name(rs.getString("dealership_name"));
						return uBean;
					}
				});
	}

	public List<UserBean> getDealerWiseCompletedAudits(UserBean uBean, String status,final String phase,final String year) {
		System.out.println("SELECT * FROM(SELECT mst_audit_schedule.dealership_id as dealer_id,quarter,year,sk_audit_id FROM mst_audit_schedule WHERE (quarter='"+phase+"') AND year='"+year+"' GROUP BY quarter,dealership_id,year) as res1 LEFT JOIN (SELECT dealership_id as did,quarter as q,sk_audit_id as id FROM mst_audit_schedule WHERE (quarter='"+phase+"') AND year='"+year+"' AND (status='completed' OR status='active') GROUP BY dealership_id,quarter,year) as res2  ON res1.sk_audit_id=res2.id WHERE res2.did IS null;");
		return template.query("SELECT * FROM(SELECT mst_audit_schedule.dealership_id as dealer_id,quarter,year,sk_audit_id FROM mst_audit_schedule WHERE (quarter='"+phase+"') AND year='"+year+"' GROUP BY quarter,dealership_id,year) as res1 LEFT JOIN (SELECT dealership_id as did,quarter as q,sk_audit_id as id FROM mst_audit_schedule WHERE (quarter='"+phase+"') AND year='"+year+"' AND (status='completed' OR status='active') GROUP BY dealership_id,quarter,year) as res2  ON res1.sk_audit_id=res2.id WHERE res2.did IS null;",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						final UserBean uBean = new UserBean();
						String did  =  rs.getString("dealer_id");
						
						uBean.setQuarter(rs.getString("quarter"));
						uBean.setYear(rs.getString("year"));
						uBean.setDealership_id(rs.getString("dealer_id"));
						 try {
			        	        String encrypted_country_id = AESCrypt.encrypt(rs.getString("dealer_id"));
			        	        uBean.setDealership_id(encrypted_country_id);
			        	        } catch(Exception e) { System.out.println("bug"+e.getMessage()); }
						System.out.println("SELECT * FROM mst_dealership WHERE sk_dealership_id='"+did+"';");
						 template.query("SELECT * FROM mst_dealership WHERE sk_dealership_id='"+did+"';;",
								new RowMapper<UserBean>() {
									public UserBean mapRow(ResultSet rs, int row) throws SQLException {
										uBean.setDealership_name(rs.getString("dealership_name"));
										return uBean;
									}
								});
						 try{
						 System.out.println("SELECT *, FIND_IN_SET( score, (SELECT GROUP_CONCAT( score ORDER BY score DESC) FROM mst_dealer_final_score WHERE   outlet_type='Sales' AND score_type='Contractual Score' AND brand='BMW' AND year='"+year+"' AND quarter='"+phase+"'  ) ) AS rank FROM mst_dealer_final_score WHERE  dealer_id='"+did+"' AND outlet_type='Sales' AND score_type='Contractual Score' AND brand='BMW' AND year='"+year+"' AND quarter='"+phase+"';");
				          template.queryForObject("SELECT *, FIND_IN_SET( score, (SELECT GROUP_CONCAT( score ORDER BY score DESC) FROM mst_dealer_final_score WHERE   outlet_type='Sales' AND score_type='Contractual Score' AND brand='BMW' AND year='"+year+"' AND quarter='"+phase+"'  ) ) AS rank FROM mst_dealer_final_score WHERE  dealer_id='"+did+"' AND outlet_type='Sales' AND score_type='Contractual Score' AND brand='BMW' AND year='"+year+"' AND quarter='"+phase+"';", new RowMapper<UserBean>() {
				               public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				            	   uBean.setBMW_sales_rank(rs.getString("rank"));
				         return uBean;
				       }
				     });
						 }catch (Exception e) {
							 uBean.setBMW_sales_rank("N/A");
						}
				          try{
				          System.out.println("SELECT *, FIND_IN_SET( score, (SELECT GROUP_CONCAT( score ORDER BY score DESC) FROM mst_dealer_final_score WHERE   outlet_type='Service' AND score_type='Contractual Score' AND brand='BMW' AND year='"+year+"' AND quarter='"+phase+"'  ) ) AS rank FROM mst_dealer_final_score WHERE  dealer_id='"+did+"' AND outlet_type='Service' AND score_type='Contractual Score' AND brand='BMW' AND year='"+year+"' AND quarter='"+phase+"';");
				          template.queryForObject("SELECT *, FIND_IN_SET( score, (SELECT GROUP_CONCAT( score ORDER BY score DESC) FROM mst_dealer_final_score WHERE   outlet_type='Service' AND score_type='Contractual Score' AND brand='BMW' AND year='"+year+"' AND quarter='"+phase+"'  ) ) AS rank FROM mst_dealer_final_score WHERE  dealer_id='"+did+"' AND outlet_type='Service' AND score_type='Contractual Score' AND brand='BMW' AND year='"+year+"' AND quarter='"+phase+"';", new RowMapper<UserBean>() {
				               public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				            	   uBean.setBMW_service_rank(rs.getString("rank"));
				         return uBean;
				       }
				     });
				          }catch (Exception e) {
				        	  uBean.setBMW_service_rank("N/A");
						}
				          try{
				          System.out.println("SELECT *, FIND_IN_SET( score, (SELECT GROUP_CONCAT( score ORDER BY score DESC) FROM mst_dealer_final_score WHERE   outlet_type='Sales' AND score_type='Contractual Score' AND brand='MINI' AND year='"+year+"' AND quarter='"+phase+"'  ) ) AS rank FROM mst_dealer_final_score WHERE  dealer_id='"+did+"' AND outlet_type='Sales' AND score_type='Contractual Score' AND brand='MINI' AND year='"+year+"' AND quarter='"+phase+"';");
				          template.queryForObject("SELECT *, FIND_IN_SET( score, (SELECT GROUP_CONCAT( score ORDER BY score DESC) FROM mst_dealer_final_score WHERE   outlet_type='Sales' AND score_type='Contractual Score' AND brand='MINI' AND year='"+year+"' AND quarter='"+phase+"'  ) ) AS rank FROM mst_dealer_final_score WHERE  dealer_id='"+did+"' AND outlet_type='Sales' AND score_type='Contractual Score' AND brand='MINI' AND year='"+year+"' AND quarter='"+phase+"';", new RowMapper<UserBean>() {
				               public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				            	   uBean.setMINI_sales_rank(rs.getString("rank"));
				         return uBean;
				       }
				     });
				          }catch (Exception e) {
				        	  uBean.setMINI_sales_rank("N/A");
						}
						 
						/* System.out.println("SELECT *, FIND_IN_SET( score, (SELECT GROUP_CONCAT( score ORDER BY score DESC) FROM mst_dealer_final_score WHERE    score_type='Contractual Score'  AND quarter='H1'  ) ) AS rank FROM mst_dealer_final_score WHERE  dealer_id='"+did+"'  AND score_type='Contractual Score' AND quarter='H1';");
				         return template.queryForObject("SELECT *, FIND_IN_SET( score, (SELECT GROUP_CONCAT( score ORDER BY score DESC) FROM mst_dealer_final_score WHERE    score_type='Contractual Score'  AND quarter='H1'  ) ) AS rank FROM mst_dealer_final_score WHERE  dealer_id='"+did+"'  AND score_type='Contractual Score' AND quarter='H1';", new RowMapper<UserBean>() {
				               public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				            	   uBean.setScore(rs.getString("rank"));
				         return uBean;
				       }
				     });*/
						 return uBean;
					}
				});
	}

	
	public List<UserBean> getUserType(UserBean uBean) {
		System.out.println("SELECT * FROM mst_user_type where user_status='active';");
		return template.query("SELECT * FROM mst_user_type where user_status='active';", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean uBean = new UserBean();
				uBean.setUser_type_id(rs.getString("sk_user_id"));
				uBean.setUser_type(rs.getString("user_type"));
				return uBean;
			}
		});
	}

	public UserBean getUserTypeDetailsById(final UserBean uBean, String uid) {
		System.out.println("SELECT * FROM mst_user_type where sk_user_id='"+uid+"' and user_status='active';");
		return template.queryForObject("SELECT * FROM mst_user_type where sk_user_id='"+uid+"' and user_status='active';", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				uBean.setUser_type_id(rs.getString("sk_user_id"));
				uBean.setUser_type(rs.getString("user_type"));
				return uBean;
			}
		});
	}

	public void updateUserTypeById(UserBean uBean) {
			System.out.println("UPDATE mst_user_type SET user_type='" + uBean.getUser_type() + "' WHERE sk_user_id='" + uBean.getUser_type_id() + "'");
			template.execute("UPDATE mst_user_type SET user_type='" + uBean.getUser_type() + "' WHERE sk_user_id='" + uBean.getUser_type_id() + "'");
		}

	public void deleteUserTypeById(UserBean uBean, String uid) {
		System.out.println("DELETE FROM mst_user_type WHERE sk_user_id='" + uid + "'");
		template.execute("DELETE FROM mst_user_type WHERE sk_user_id='" + uid + "'");
		
	}
	public QuestionBean getOutletByScheduleId(final QuestionBean qBean, String a_id) {
		System.out.println("SELECT * FROM mst_audit_schedule WHERE sk_audit_id='"+a_id+"' LIMIT 1;");
		return template.queryForObject(
				"SELECT * FROM mst_audit_schedule WHERE sk_audit_id='"+a_id+"' LIMIT 1;",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setOutlet_id(rs.getString("outlet_id"));
						qBean.setPhase(rs.getString("quarter"));
						qBean.setYear(rs.getString("year"));
						return qBean;
					}
				});
	}
	public List<QuestionBean> getQuestionsList(final QuestionBean hBean, String a_id) {
		/*
		 * System.out.
		 * println("SELECT * FROM mst_questionnaire WHERE mst_questionnaire.sk_questionnaire_id IN(SELECT question_id FROM `mst_audit` WHERE audit_schedule_id='"
		 * +a_id+"') GROUP by sk_questionnaire_id;"); return template.
		 * query("SELECT * FROM mst_questionnaire WHERE mst_questionnaire.sk_questionnaire_id IN(SELECT question_id FROM `mst_audit` WHERE audit_schedule_id='"
		 * +a_id+"') GROUP by sk_questionnaire_id",new RowMapper<HomeBean>(){
		 */
		System.out.println(
				"SELECT mst_outlet.brand,mst_audit_schedule.outlet_type as type from mst_outlet,mst_audit_schedule WHERE mst_audit_schedule.sk_audit_id='"
						+ a_id + "' AND mst_audit_schedule.outlet_id=mst_outlet.sk_outlet_id ");
		template.query(
				"SELECT mst_outlet.brand,mst_audit_schedule.outlet_type as type from mst_outlet,mst_audit_schedule WHERE mst_audit_schedule.sk_audit_id='"
						+ a_id + "' AND mst_audit_schedule.outlet_id=mst_outlet.sk_outlet_id ",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						hBean.setBrand(rs.getString("brand"));
						System.out.println(rs.getString("brand") + rs.getString("type"));
						hBean.setType(rs.getString("type"));
						return hBean;
					}
				});
		System.out.println(
				"SELECT mst_audit.* ,mst_questionnaire.* FROM `mst_audit` ,mst_questionnaire WHERE mst_audit.audit_schedule_id='"
						+ a_id
						+ "' AND mst_audit.results='0' AND mst_questionnaire.essential!='O' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.brand='"
						+ hBean.getBrand() + "' AND mst_questionnaire.type='" + hBean.getType() + "';");
		return template.query(
				"SELECT mst_audit.* ,mst_questionnaire.* FROM `mst_audit` ,mst_questionnaire WHERE mst_audit.audit_schedule_id='"
						+ a_id
						+ "' AND mst_audit.results='0' AND mst_questionnaire.essential!='O' AND mst_questionnaire.sk_questionnaire_id=mst_audit.question_id AND mst_questionnaire.brand='"
						+ hBean.getBrand() + "' AND mst_questionnaire.type='" + hBean.getType() + "';",
				new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean hBean1 = new QuestionBean();
						hBean1.setStandard(rs.getString("standard"));
						hBean1.setRequirement(rs.getString("requirement"));
						hBean1.setComment(rs.getString("comment"));
						hBean1.setNumber(rs.getString("number"));
						hBean1.setAuditor_comment(rs.getString("auditor_comment"));
						hBean1.setDealer_comment(rs.getString("dealer_comment"));
						hBean1.setXs(rs.getString("xs"));
						hBean1.setS(rs.getString("s"));
						hBean1.setM(rs.getString("m"));
						hBean1.setL(rs.getString("l"));
						hBean1.setXl(rs.getString("xl"));
						hBean1.setXxl(rs.getString("xxl"));
						hBean1.setCheck(rs.getString("type_of_check"));
						hBean1.setEssential(rs.getString("essential"));
						hBean1.setQuestion(rs.getString("question"));
						hBean1.setObservation(rs.getString("observation"));
						hBean1.setPerson(rs.getString("suggested_person"));
						hBean1.setQuestionnaire_id(rs.getString("sk_questionnaire_id"));

						return hBean1;
					}
				});

	}
	public List<QuestionBean> getImages(String a_id, String question_id,QuestionBean hBean) {
		System.out.println("SELECT * from txn_audit_images WHERE question_id='"+question_id+"' AND audit_scheduled_id='"+a_id+"';");
		return template.query("SELECT * from txn_audit_images WHERE question_id='"+question_id+"' AND audit_scheduled_id='"+a_id+"';",	new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean hBean = new QuestionBean();
						hBean.setQuestion(rs.getString("question_id"));
						hBean.setReference_image(rs.getString("image_name"));
						hBean.setAudit_schedule_id(rs.getString("audit_scheduled_id"));
						return hBean;

					}
				});
	}

	public QuestionBean checkScoreSubmitted(QuestionBean qBean, String asid) {
		System.out.println("SELECT * from mst_sections_score WHERE audit_schedule_id='"+asid+"' LIMIT 1;");
		return template.queryForObject("SELECT * from mst_sections_score WHERE audit_schedule_id='"+asid+"' LIMIT 1;",	new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						
						return qBean;

					}
				});
	}

	public QuestionBean getauditDetails(String asid, final QuestionBean qBean) {
		System.out.println("SELECT * from mst_audit_schedule WHERE sk_audit_id='"+asid+"' LIMIT 1;");
		return template.queryForObject("SELECT * from mst_audit_schedule WHERE sk_audit_id='"+asid+"' LIMIT 1;",	new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setPhase(rs.getString("quarter"));
						qBean.setYear(rs.getString("year"));
						return qBean;

					}
				});
	}

	public void updateNotificationSet(QuestionBean qBean,String asid, String oid, String did, String phase, String year,String date,String std_number,String std_name,String outlet_name,String observation,String exception_remarks,String dealer_name,String audit_qn_id) {
		String first_notify = "";
		String second_notify = "";
		String third_notify = "";
		String fourth_notify = "";
		try {

			int d2 = -30;
			int d3 = -15;
			int d4 = 1;
			int d5 = 2;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(date)); // Now use today date.
			c.add(Calendar.DATE, d2); // Adding -30 days
			first_notify = sdf.format(c.getTime());

			Calendar c2 = Calendar.getInstance();
			c2.setTime(sdf.parse(date));
			c2.add(Calendar.DATE, d3); // Adding -15 days
			second_notify = sdf.format(c2.getTime());

			Calendar c3 = Calendar.getInstance();
			c3.setTime(sdf.parse(date));
			c3.add(Calendar.DATE, d4); // Adding 1 days
			third_notify = sdf.format(c3.getTime());
			Calendar c4 = Calendar.getInstance();
			c4.setTime(sdf.parse(date));
			c4.add(Calendar.DATE, d5); // Adding 2 days
			fourth_notify = sdf.format(c4.getTime());

		
		System.out.println(
				"INSERT INTO mst_notification_scheduler (audit_id,dealer_id,outlet_id,phase,year,first_notification,second_notification,third_notification,fourth_notification,fifth_notification,standard_number,standard_name,outlet_name,observation,exception_remarks,dealer_name,audit_qn_id) VALUES ('"
						+ asid + "','"+did+"','"+oid+"','"+phase+"','"+year+"','" + first_notify + "','" + second_notify + "','"
						+ date + "','" + third_notify + "','" + fourth_notify + "','"+std_number+"','"+std_name+"','"+outlet_name+"','"+observation+"','"+exception_remarks+"','"+dealer_name+"','"+audit_qn_id+"')");
		template.execute(
				"INSERT INTO mst_notification_scheduler (audit_id,dealer_id,outlet_id,phase,year,first_notification,second_notification,third_notification,fourth_notification,fifth_notification,standard_number,standard_name,outlet_name,observation,exception_remarks,dealer_name,audit_qn_id) VALUES ('"
						+ asid + "','"+did+"','"+oid+"','"+phase+"','"+year+"','" + first_notify + "','" + second_notify + "','"
						+ date + "','" + third_notify + "','" + fourth_notify + "','"+std_number+"','"+std_name+"','"+outlet_name+"','"+observation+"','"+exception_remarks+"','"+dealer_name+"','"+audit_qn_id+"')");
		} catch (Exception e) {
			System.out.println(e + "DATES ERROR");
		}
		
	}

	public void updateDealerComments(QuestionBean qBean, String comments, String aid) {
		System.out.println("UPDATE mst_audit SET dealer_remarks='"+comments.replace("'", "\\'")+"' WHERE sk_audit_id='"+aid+"';");
		 template.execute("UPDATE mst_audit SET dealer_remarks='"+comments.replace("'", "\\'")+"' WHERE sk_audit_id='"+aid+"';");
	}
	
	
	protected QuestionBean getEYPMOsEmails(final QuestionBean qBean, String oid) {
		System.out.println("SELECT GROUP_CONCAT(email) as email FROM `mst_users` WHERE user_type='4' AND FIND_IN_SET('"+oid+"',outlets)");
		return template.queryForObject("SELECT GROUP_CONCAT(email) as email FROM `mst_users` WHERE user_type='4' AND FIND_IN_SET('"+oid+"',outlets)",	new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setEmail(rs.getString("email"));
						return qBean;

					}
				});
	}

	protected QuestionBean getDealerPrincipleEmails(final QuestionBean qBean, String oid) {
		System.out.println("SELECT GROUP_CONCAT(email) as email FROM `mst_users` WHERE user_type='13' AND FIND_IN_SET('"+oid+"',outlets)");
		return template.queryForObject("SELECT GROUP_CONCAT(email) as email FROM `mst_users` WHERE user_type='13' AND FIND_IN_SET('"+oid+"',outlets)",	new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setEmail(rs.getString("email"));
						return qBean;

					}
				});
	}

	protected QuestionBean getDealerBussinessHeadEmails(final QuestionBean qBean, String oid) {
		System.out.println("SELECT GROUP_CONCAT(email) as email FROM `mst_users` WHERE user_type='7' AND FIND_IN_SET('"+oid+"',outlets)");
		return template.queryForObject("SELECT GROUP_CONCAT(email) as email FROM `mst_users` WHERE user_type='7' AND FIND_IN_SET('"+oid+"',outlets)",	new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setEmail(rs.getString("email"));
						return qBean;

					}
				});
	}
	protected QuestionBean getSalesHeadEmails(final QuestionBean qBean, String oid) {
		System.out.println("SELECT GROUP_CONCAT(email) as email FROM `mst_users` WHERE user_type='8' AND FIND_IN_SET('"+oid+"',outlets)");
		return template.queryForObject("SELECT GROUP_CONCAT(email) as email FROM `mst_users` WHERE user_type='8' AND FIND_IN_SET('"+oid+"',outlets)",	new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setEmail(rs.getString("email"));
						return qBean;

					}
				});
	}
	protected QuestionBean getServiceHeadEmails(final QuestionBean qBean, String oid) {
		System.out.println("SELECT GROUP_CONCAT(email) as email FROM `mst_users` WHERE user_type='9' AND FIND_IN_SET('"+oid+"',outlets)");
		return template.queryForObject("SELECT GROUP_CONCAT(email) as email FROM `mst_users` WHERE user_type='9' AND FIND_IN_SET('"+oid+"',outlets)",	new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setEmail(rs.getString("email"));
						return qBean;

					}
				});
	}
	protected QuestionBean getRegionalManagerEmails(final QuestionBean qBean, String oid) {
		System.out.println("SELECT GROUP_CONCAT(email) as email FROM `mst_users` WHERE user_type='5' AND FIND_IN_SET('"+oid+"',outlets)");
		return template.queryForObject("SELECT GROUP_CONCAT(email) as email FROM `mst_users` WHERE user_type='5' AND FIND_IN_SET('"+oid+"',outlets)",	new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setEmail(rs.getString("email"));
						return qBean;

					}
				});
	}

	public List<QuestionBean> getDealerWiseScores(QuestionBean qBean,final String phase, final String year,final String level,final String type,final String brand) {
		System.out.println("SELECT GROUP_CONCAT(mst_dealer_final_score.score_type),GROUP_CONCAT(score) as fscore, mst_dealer_final_score.*,mst_dealership.dealership_name FROM `mst_dealer_final_score`,mst_dealership WHERE quarter='"+phase+"' AND year='"+year+"' AND brand='"+brand+"' AND outlet_type='"+type+"' AND mst_dealership.sk_dealership_id=mst_dealer_final_score.dealer_id GROUP BY mst_dealer_final_score.dealer_id,mst_dealer_final_score.brand,mst_dealer_final_score.outlet_type");
		return template.query("SELECT GROUP_CONCAT(mst_dealer_final_score.score_type),GROUP_CONCAT(score) as fscore, mst_dealer_final_score.*,mst_dealership.dealership_name FROM `mst_dealer_final_score`,mst_dealership WHERE quarter='"+phase+"' AND year='"+year+"' AND brand='"+brand+"' AND outlet_type='"+type+"' AND mst_dealership.sk_dealership_id=mst_dealer_final_score.dealer_id GROUP BY mst_dealer_final_score.dealer_id,mst_dealer_final_score.brand,mst_dealer_final_score.outlet_type",	new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						final QuestionBean qBean = new QuestionBean();
						qBean.setOutlet_type(rs.getString("outlet_type"));
						qBean.setScore_type(rs.getString("score_type"));
						qBean.setBrand(rs.getString("brand"));
						qBean.setPhase(rs.getString("quarter"));
						qBean.setYear(rs.getString("year"));
						qBean.setDealer(rs.getString("dealership_name"));
						String temp[] = rs.getString("fscore").split(",");
						qBean.setScore(temp[0]+"%");
						qBean.setScore_type(temp[1]+"%");
						
						
						final String dealer_id=rs.getString("dealer_id");
						System.out.println("SELECT ROUND(AVG(score),2) as overall_essential_score FROM mst_final_score WHERE  score_type='Essential Score' AND dealer_id='"+dealer_id+"' AND mst_final_score.year='"+year+"' AND mst_final_score.quarter='"+phase+"' AND mst_final_score.brand='"+brand+"' AND outlet_type='" + type + "';");
						 template.queryForObject("SELECT ROUND(AVG(score),2) as overall_essential_score FROM mst_final_score WHERE  score_type='Essential Score' AND dealer_id='"+dealer_id+"' AND mst_final_score.year='"+year+"' AND mst_final_score.quarter='"+phase+"' AND mst_final_score.brand='"+brand+"' AND outlet_type='" + type + "';",
								new RowMapper<QuestionBean>() {
									public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setTotal_ans_count(rs.getString("overall_essential_score")+"%");
										
										
										
										 System.out.println("SELECT ROUND(AVG(score),2) as overall_essential_score FROM mst_final_score WHERE  score_type='Contractual Score' AND dealer_id='"+dealer_id+"' AND mst_final_score.year='"+year+"' AND mst_final_score.quarter='"+phase+"' AND mst_final_score.brand='"+brand+"' AND outlet_type='" + type + "';");
										 template.queryForObject("SELECT ROUND(AVG(score),2) as overall_essential_score FROM mst_final_score WHERE  score_type='Contractual Score' AND dealer_id='"+dealer_id+"' AND mst_final_score.year='"+year+"' AND mst_final_score.quarter='"+phase+"' AND mst_final_score.brand='"+brand+"' AND outlet_type='" + type + "';",
												new RowMapper<QuestionBean>() {
													public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
														qBean.setTotal_qns_count(rs.getString("overall_essential_score")+"%");
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

	public List<QuestionBean> getOutletWiseScores(QuestionBean qBean, String phase, String year, String score_type,String outlet_type, String brand) {
		System.out.println("SELECT GROUP_CONCAT(score) as fscore, mst_final_score.*,mst_dealership.dealership_name,mst_outlet.outlet_name FROM `mst_final_score`,mst_dealership,mst_outlet WHERE mst_final_score.quarter='"+phase+"' AND mst_final_score.year='"+year+"' AND mst_final_score.brand='"+brand+"' AND mst_final_score.outlet_type='"+outlet_type+"' AND mst_dealership.sk_dealership_id=mst_final_score.dealer_id AND mst_outlet.sk_outlet_id=mst_final_score.outlet_id GROUP BY mst_final_score.outlet_id,mst_final_score.brand,mst_final_score.outlet_type");
		return template.query("SELECT GROUP_CONCAT(score) as fscore, mst_final_score.*,mst_dealership.dealership_name,mst_outlet.outlet_name FROM `mst_final_score`,mst_dealership,mst_outlet WHERE mst_final_score.quarter='"+phase+"' AND mst_final_score.year='"+year+"' AND mst_final_score.brand='"+brand+"' AND mst_final_score.outlet_type='"+outlet_type+"' AND mst_dealership.sk_dealership_id=mst_final_score.dealer_id AND mst_outlet.sk_outlet_id=mst_final_score.outlet_id GROUP BY mst_final_score.outlet_id,mst_final_score.brand,mst_final_score.outlet_type",	new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						qBean.setOutlet_type(rs.getString("outlet_type"));
						qBean.setScore_type(rs.getString("score_type"));
						qBean.setBrand(rs.getString("brand"));
						qBean.setPhase(rs.getString("quarter"));
						qBean.setYear(rs.getString("year"));
						qBean.setDealer(rs.getString("dealership_name"));
						String temp[] = rs.getString("fscore").split(",");
						qBean.setScore(temp[0]);
						qBean.setScore_type(temp[1]);
						qBean.setOutlets(rs.getString("outlet_name"));
						return qBean;

					}
				});
	}
	
	/*public QuestionBean getOverallEssentialScoreByDealerId(final QuestionBean qBean, String did, String type,String phase,String year,String brand) {
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
	}*/

	public void RunSqlGroupByException(UserBean uBean) {
		System.out.println("SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));");
		template.execute("SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));");
		
	}

	public void updateDealerImages(QuestionBean qBean, String id,String img) {
		System.out.println("UPDATE mst_audit SET dealer_image='"+img+"' WHERE sk_audit_id='"+id+"'");
		template.execute("UPDATE mst_audit SET dealer_image='"+img+"' WHERE sk_audit_id='"+id+"'");
		
	}

	public UserBean getOutletsBydealerShipId(final UserBean uBean, String dealerShipId) {
		System.out.println("SELECT GROUP_CONCAT(outlet_name) as outlet_name,GROUP_CONCAT(sk_outlet_id) as outlet_id FROM mst_outlet WHERE dealership_id='" + dealerShipId + "' ;");
		return template.queryForObject("SELECT GROUP_CONCAT(outlet_name) as outlet_name,GROUP_CONCAT(sk_outlet_id) as outlet_id FROM mst_outlet WHERE dealership_id='" + dealerShipId + "' ;",
				new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
					    HashSet<String> test=new HashSet<String>(Arrays.asList(rs.getString("outlet_name").split(",")));
					    String test1 = test.toString();
					    String test2 = test1.replaceAll("[\\[\\](){}]","");

						uBean.setOutlets(test2);
						
						uBean.setOutlet_id(rs.getString("outlet_id"));
						
						return uBean;
					}
				});
	}

	public UserBean checkAreaOfImpExists(final UserBean uBean, String did, String year, String phase) {
		String sql = "";
		sql = "SELECT mst_questionnaire.*,mst_section.*,mst_audit_schedule.sk_audit_id,mst_audit_schedule.outlet_id FROM mst_questionnaire,mst_section,mst_audit,mst_audit_schedule WHERE mst_questionnaire.section_id=mst_section.sk_section_id  AND mst_audit.results='1' AND mst_audit.pmo_review_status='Open' AND mst_audit.exception_timeline IS NOT NULL AND mst_audit.exception_timeline!='' AND mst_audit.question_id=mst_questionnaire.sk_questionnaire_id AND mst_audit.dealer_id='" + did
								+ "' AND mst_audit_schedule.quarter='"+phase+"' AND mst_audit_schedule.year='"+year+"' AND mst_audit.audit_schedule_id=mst_audit_schedule.sk_audit_id LIMIT 1";
		System.out.println(sql);
		return template.queryForObject(sql,new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
					   
						return uBean;
					}
				});
	}

	public UserBean getCountOfClosedAudits(final UserBean uBean) {
		String sql = "";
		sql = "SELECT COUNT(sk_audit_id) as count FROM mst_audit_schedule WHERE status='closed'";
		System.out.println(sql);
		return template.queryForObject(sql,new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
					   uBean.setAddress(rs.getString("count"));
						return uBean;
					}
				});
	}
		
	
	public QuestionBean getImagess(String a_id, String question_id,final QuestionBean qBean) {
		System.out.println("SELECT * from txn_audit_images WHERE question_id='"+question_id+"' AND audit_scheduled_id='"+a_id+"' LIMIT 1;");
		return template.queryForObject("SELECT * from txn_audit_images WHERE question_id='"+question_id+"' AND audit_scheduled_id='"+a_id+"' LIMIT 1;",	new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						qBean.setQuestion(rs.getString("question_id"));
						qBean.setImg(rs.getString("image_name"));
						System.out.println("MANJU IMG :"+rs.getString("image_name"));
						qBean.setAudit_schedule_id(rs.getString("audit_scheduled_id"));
						return qBean;

					}
				});
	}

	public UserBean checkCurrentYearAuditsSchedules(final UserBean uBean, String current_year, String current_phase) {
		System.out.println("SELECT * FROM mst_audit_schedule WHERE year='"+current_year+"' AND quarter='"+current_phase+"' LIMIT 1;");
		return template.queryForObject("SELECT * FROM mst_audit_schedule WHERE year='"+current_year+"' AND quarter='"+current_phase+"' LIMIT 1;",	new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
					
						return uBean;

					}
				});
	}

	public List<UserBean> getMapData(UserBean uBean, String current_year, String current_phase) {
		System.out.println("SELECT mst_outlet.lat,mst_outlet.lang,mst_audit_schedule.status FROM `mst_audit_schedule`,mst_outlet WHERE year='"+current_year+"' AND quarter='"+current_phase+"' AND mst_audit_schedule.outlet_id=mst_outlet.sk_outlet_id");
		return template.query("SELECT mst_outlet.lat,mst_outlet.lang,mst_audit_schedule.status FROM `mst_audit_schedule`,mst_outlet WHERE year='"+current_year+"' AND quarter='"+current_phase+"' AND mst_audit_schedule.outlet_id=mst_outlet.sk_outlet_id;",	new RowMapper<UserBean>() {
					public UserBean mapRow(ResultSet rs, int row) throws SQLException {
						UserBean uBean = new UserBean();
						uBean.setLat(rs.getString("lat"));
						uBean.setLang(rs.getString("lang"));
						uBean.setStatus(rs.getString("status"));
						return uBean;

					}
				});
	}

	public List<QuestionBean> getImage(QuestionBean qBean, String questionId,String asid) {
		System.out.println("SELECT * FROM txn_audit_images WHERE audit_scheduled_id='"+asid+"' AND question_id='"+questionId+"';");
		return template.query("SELECT * FROM txn_audit_images WHERE audit_scheduled_id='"+asid+"' AND question_id='"+questionId+"';;", new RowMapper<QuestionBean>() {
					public QuestionBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionBean qBean = new QuestionBean();
						qBean.setReference_image(rs.getString("image_name"));
						System.out.println("image name"+rs.getString("image_name"));
						return qBean;
					}
				});										
	}

	public String updatePmoExceptiontoYes(UserBean uBean, String did, String qid, String asid) {
		System.out.println("UPDATE mst_audit SET pmo_review_status='Close' WHERE dealer_id='"+did+"' AND question_id='"+qid+"' AND audit_schedule_id='"+asid+"'");
		template.execute("UPDATE mst_audit SET pmo_review_status='Close' WHERE dealer_id='"+did+"' AND question_id='"+qid+"' AND audit_schedule_id='"+asid+"'");
		return null;
	}
		
	

	
	

	

	
	
	
	

}
