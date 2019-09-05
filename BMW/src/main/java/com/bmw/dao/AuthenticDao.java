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
import java.util.Map;
import java.util.ResourceBundle;

import javax.mail.Session;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Properties;

import org.omg.CORBA.Request;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bmw.api.SendEmailUsingGMailSMTP;
import com.bmw.beans.MenuBean;
import com.bmw.beans.QuestionBean;
import com.bmw.beans.UserBean;


public class AuthenticDao {
	
	JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	ResourceBundle resource = ResourceBundle.getBundle("resources/web");
	final String dashboardURL=resource.getString("dashboardURL");
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();
	
	
	


	public UserBean signin(final UserBean uBean) {
		
		System.out.println("select email from mst_users where email='" + uBean.getEmail() + "' and password=MD5('"+uBean.getPassword()+"')");
		String count = "";
		String sql = "select count(email)as exist from mst_users where email='" + uBean.getEmail() + "' and password=MD5('"+uBean.getPassword()+"')";
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
		}
		int id_status = Integer.parseInt(count);
		if (id_status > 0) {
			System.out.println("exist");
			
			
			
			String sql1 = "SELECT * FROM mst_users WHERE email=? AND password=MD5(?) AND user_status='active'";
			 return template.queryForObject(sql1, new Object[] { uBean.getEmail(),uBean.getPassword() }, new RowMapper<UserBean>(){
				public UserBean mapRow(ResultSet rs, int row) throws SQLException {
					uBean.setUser_type_id(rs.getString("user_type"));
					uBean.setFirst_name(rs.getString("first_name"));
					uBean.setLast_name(rs.getString("last_name"));
					uBean.setEmail(rs.getString("email"));
					uBean.setMobile(rs.getString("mobile"));
					uBean.setPassword(rs.getString("password"));
					uBean.setRole(rs.getString("role"));
					uBean.setUser_id(rs.getString("sk_user_id"));
					uBean.setOutlets(rs.getString("outlets"));
					uBean.setUser_type(rs.getString("user_type"));
					return uBean;
				}
			});
	 
		
		}else{
			System.out.println("error");
			
			uBean.setMessage("Invalid Credintials");
			return uBean;
		}
		
		
	}





	public UserBean verifyEmail(final UserBean uBean) {
		
		
		System.out.println("select email from mst_users where email='" + uBean.getEmail() + "' ");
		String count = "";
		String sql = "select count(email)as exist from mst_users where email='" + uBean.getEmail() + "'";
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
		}
		int id_status = Integer.parseInt(count);
		if (id_status > 0) {
			System.out.println("exist");
			
			
			System.out.println("select * from mst_users where email='" + uBean.getEmail() + "'");
			return template.queryForObject("select * from mst_users where email='" + uBean.getEmail() + "'",
					new RowMapper<UserBean>() {
						public UserBean mapRow(ResultSet rs, int row) throws SQLException {
							Long user_id = rs.getLong("sk_user_id");
							System.out.println(user_id);

							uBean.setEmail(rs.getString("email"));
							uBean.setUser_id(rs.getString("sk_user_id"));
							System.out.println(rs.getString("email"));
							SendEmailUsingGMailSMTP mail = new SendEmailUsingGMailSMTP();

							String msg = "<td><a href='" + dashboardURL + "resetPassword/" + user_id + "'>click on the link to reset password</a></td>";
							System.out.println(msg+"=msg");
							mail.sendMail(uBean.getEmail(), msg, user_id.toString());
							uBean.setMessage("Mail has been sent,Please check your email!");
							return uBean;
						}
					});
			
		}else{
			System.out.println("error");
			
			uBean.setMessage("Email Not Exist");
			return uBean;
		}
		
		
	}




	public UserBean updatePassword(UserBean uBean,String uid) {
		System.out.println("UPDATE `mst_users` SET `password`='"+uBean.getPassword()+"' WHERE sk_user_id='"+uid+"';");
        template.execute("UPDATE `mst_users` SET `password`='"+uBean.getPassword()+"' WHERE sk_user_id='"+uid+"';");
     return uBean;
   }
	
	
	

}
