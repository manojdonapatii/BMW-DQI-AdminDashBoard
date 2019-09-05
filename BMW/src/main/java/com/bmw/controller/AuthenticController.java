package com.bmw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.bmw.beans.UserBean;
import com.bmw.dao.AuthenticDao;
import com.bmw.dao.HomeDao;


/**
 * The {@code AuthenticController} is a "Controller" class to handle request.
 *   
 * Managed by {@code Controller} annotation to forward request to front
 * controller and send model view as response. </b>
 *
 * @author Manjunath Reddy
 */

@Controller
public class AuthenticController {

	@Autowired
	AuthenticDao aDao;
	@Autowired
	HomeDao hDao;
	
	private static final Logger logger = Logger.getLogger(AuthenticController.class);
	
	/**
	 * The {@code sign in} use to route the response to sign in page. Response will be
	 * sent sign in page using tiles.
	 * 
	 * @param uBean
	 * @return
	 * 
	 */
	@RequestMapping("/signin")
	public ModelAndView user_type(HttpServletRequest request,HttpServletResponse response,UserBean uBean) {
		ModelAndView model = null;
		model = new ModelAndView("login");
		return model;
	}
	
	/**
	 * The {@code sign in } use to route the response to home page if authentication is success. 
	 * Response will be sent home page using tiles.
	 * else the response to sign in page with error message
	 * @param uBean
	 * @return
	 * 
	 */
	@RequestMapping(value="/signin", method=RequestMethod.POST)
    public ModelAndView signin(HttpServletRequest request, HttpServletResponse response,UserBean uBean){
		ModelAndView model= null;
		aDao.signin(uBean);
		HttpSession session=request.getSession();
		session.setAttribute("errMsg", uBean.getMessage());
		session.setAttribute("first_name", uBean.getFirst_name());
		session.setAttribute("last_name", uBean.getLast_name());
		session.setAttribute("email", uBean.getEmail());
		session.setAttribute("mobile", uBean.getMobile());
		session.setAttribute("role", uBean.getRole());
		session.setAttribute("user_id", uBean.getUser_id());
		session.setAttribute("user_type", uBean.getUser_type());
		if(uBean.getMessage()==null){
			model = new ModelAndView("redirect:/home");
		}else{
			model = new ModelAndView("redirect:/signin");
		}
		logger.info(uBean.getMessage());
	    return model;
    }
	
	/**
	 * The {@code forgot password} use to route the response to forgot password page. Response will be
	 * sent to forgot password page using tiles.
	 * 
	 * @param uBean
	 * @return
	 * 
	 */
	@RequestMapping("/forgotpassword")
	public ModelAndView forgotpassword(HttpServletRequest request,HttpServletResponse response,UserBean uBean) {
		ModelAndView model = null;
		model = new ModelAndView("forgotpwd");
		return model;
	}
	
	/**
	 * The {@code forgot password} use to route the response to login page. 
	 * user can login with the updated password
	 * 
	 * @param uBean
	 * @return
	 * 
	 */
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ModelAndView forgotPassword(HttpServletRequest request, HttpServletResponse response, UserBean uBean) {

		ModelAndView model = null;
		aDao.verifyEmail(uBean);
		request.setAttribute("user_id", uBean.getUser_id());
		request.setAttribute("msg", uBean.getMessage());
		HttpSession session=request.getSession();
		session.setAttribute("msg", uBean.getMessage());
			model = new ModelAndView("forgotpwd");
		return model;
	}
	
	/**
	 * The {@code reset password} use to reset an user password and route the response to login page. 
	 * user can login with the updated password
	 * 
	 * @param uBean
	 * @return
	 * 
	 */
	@RequestMapping("/resetPassword/{id}")
	public ModelAndView resetPassword(@PathVariable String id, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView model = null;
		logger.info("User ID IS :" + id);
		request.setAttribute("user_id", id);
		model = new ModelAndView("resetPassword");
		return model;
	}
	
	/**
	 * The {@code save reset password} use to save the updated password and route the response to login page. 
	 * user can login with the updated password
	 * 
	 * @param uBean
	 * @return
	 * 
	 */
	@RequestMapping(value = "/saveResetpassword/{uid}", method = RequestMethod.POST)
	public ModelAndView ShowresetForm(@PathVariable String uid,HttpServletRequest request, HttpServletResponse response, UserBean uBean) {

		ModelAndView model = null;
		logger.info("Hi :" + uBean.getUser_id());
		aDao.updatePassword(uBean,uid);
		model = new ModelAndView("redirect:/signin");
		return model;
	}
	
	/**
	 * The {@code logout} use to route the response to login page. 
	 * 
	 * @param uBean
	 * @return
	 * 
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		request.getSession().invalidate();
		model = new ModelAndView("login");
		
		return model;
	}
	
}
