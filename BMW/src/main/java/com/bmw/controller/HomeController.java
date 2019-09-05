package com.bmw.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.bmw.api.AESCrypt;
import com.bmw.api.MailCheck;
import com.bmw.api.SendEmailUsingGMailSMTP;
import com.bmw.beans.MenuBean;
import com.bmw.beans.MultipleFileUploadForm;
import com.bmw.beans.QuestionBean;
import com.bmw.beans.UserBean;
import com.bmw.dao.HelperDao;
import com.bmw.dao.HomeDao;
import com.bmw.dao.ReportsDao;
import com.google.gson.Gson;

@Controller
public class HomeController {
	@Autowired
	HomeDao hDao;
	@Autowired
	ReportsDao rDao;

	ResourceBundle resource = ResourceBundle.getBundle("resources/web");
	String local_filepath = resource.getString("local_filepath");
	String dashboard_url = resource.getString("dashboardURL");
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static final Logger logger = Logger.getLogger(HomeController.class);
	Date date = new Date();

	/*
	 * The {@code home} use to route the response to home page. Response will be
	 * sent home page using tiles.
	 * 
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping("/home")
	public ModelAndView employee_new(HttpServletRequest request, HttpServletResponse response, MenuBean mBean,
			UserBean uBean, QuestionBean qBean) throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("home");

		HttpSession session = request.getSession(true);
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		httpResponse.setHeader("Pragma", "no-cache");
		httpResponse.setDateHeader("Expires", 0);

		hDao.RunSqlGroupByException(uBean);
		int timeout = 2000;
		session.setMaxInactiveInterval(timeout);
		String role = (String) session.getAttribute("role");
		hDao.getroleMenuById(mBean, role);
		mBean.getMenu();

		List<MenuBean> mainMenu = hDao.getMainMenu(mBean);
		// WITH ROLES//
		/* List<MenuBean> mainMenu = hDao.getMainMenu(mBean,menu_list); */
		// WITH ROLES//
		model.addObject("mainMenuList", mainMenu);
		request.getSession().setAttribute("mainMenuList", mainMenu);
		List<List<MenuBean>> subMenu = new ArrayList<List<MenuBean>>();
		Iterator<MenuBean> iterator = mainMenu.iterator();
		while (iterator.hasNext()) {
			String menu_id = iterator.next().getMenu_id();
			logger.info("Hi menu ids" + menu_id);
			subMenu.add(hDao.getSubMenuById(mBean, menu_id, ""));
		}
		model.addObject("subMenuList", subMenu);
		request.getSession().setAttribute("subMenuList", subMenu);

		request.setAttribute("first_name", session.getAttribute("first_name"));
		request.setAttribute("last_name", session.getAttribute("last_name"));
		request.setAttribute("email", session.getAttribute("email"));
		request.setAttribute("mobile", session.getAttribute("mobile"));
		request.setAttribute("user_id", session.getAttribute("user_id"));
		request.setAttribute("role", session.getAttribute("role"));

		/*
		 * if(session.getAttribute("user_id") != null && !session.isNew()) { //do
		 * something here } else { model = new ModelAndView("redirect:/signin"); }
		 */

		try {
			hDao.getDealersCount(uBean);
			request.setAttribute("dealers_count", uBean.getCount_dealers());
			hDao.getOutletsCount(uBean);
			request.setAttribute("outlets_count", uBean.getCount_outlets());
			hDao.getSheculedAuditsCount(uBean);
			request.setAttribute("audits_count", uBean.getAudits_count());
			hDao.getCountOfClosedAudits(uBean);
			logger.info("ADRESS :" + uBean.getAddress());
			request.setAttribute("closed_count", uBean.getAddress());

		} catch (Exception e) {
			logger.error(e);
		}

		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			logger.info(dateFormat.format(date));
			dateFormat.format(date);

		} catch (Exception e) {
			logger.info("Home controller" + e);
		}
		String current_year;
		String current_phase;
		/* New Code */
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String current_date = dateFormat.format(date);
		String temp[] = current_date.split("/");
		String month = temp[1];
		String phase = "";

		if (month.equals("01") || month.equals("02") || month.equals("03") || month.equals("04") || month.equals("05")
				|| month.equals("06")) {
			phase = "H1";
		} else {
			phase = "H2";
		}
		current_year = temp[2];
		current_phase = phase;

		try {
			hDao.checkCurrentYearAuditsSchedules(uBean, current_year, current_phase);
			request.getSession().setAttribute("current_year", current_year);
			request.getSession().setAttribute("current_phase", current_phase);
		} catch (Exception e) {
			logger.error(e + "NO CURRENT YEAR PHASE RESULTS");
			if (current_phase.equals("H1")) {
				current_phase = "H2";
				int last_year = Integer.parseInt(current_year) - 1;
				logger.info(last_year + "last_year");
				request.getSession().setAttribute("current_year", String.valueOf(last_year));
				request.getSession().setAttribute("current_phase", current_phase);
			} else {
				current_phase = "H1";
				request.getSession().setAttribute("current_year", current_year);
				request.getSession().setAttribute("current_phase", current_phase);
			}
		}

		current_year = (String) request.getSession().getAttribute("current_year");
		current_phase = (String) request.getSession().getAttribute("current_phase");

		logger.info(current_year + "=" + current_phase);

		return model;
	}

	@RequestMapping("/createUser")
	public ModelAndView user_type(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("users");
		request.getSession();
		List<UserBean> userTypeList = hDao.getUserTypes(uBean);
		model.addObject("userTypeList", userTypeList);
		List<UserBean> roleList = hDao.getRoles(uBean);
		model.addObject("roleList", roleList);
		List<UserBean> outletList = hDao.getOutletList(uBean);
		model.addObject("outletList", outletList);

		return model;
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ModelAndView createUser(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/viewUser");
		if (uBean.getOutlets().equals("all")) {
			hDao.getAllOutlets(uBean);
			hDao.addUserToAllOutlets(uBean, uBean.getOutlet_id());
		} else {
			hDao.addUsers(uBean);
		}

		return model;
	}

	@RequestMapping("/viewUser")
	public ModelAndView viewUser(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("users_view");
		List<UserBean> userList = hDao.getUsers(uBean);
		model.addObject("userList", userList);
		return model;
	}

	@RequestMapping("/editUser/{uid}")
	public ModelAndView editUser(@PathVariable String uid, HttpServletRequest request, HttpServletResponse response,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("users_edit");
		hDao.getUserDetailsById(uBean, uid);
		request.setAttribute("uid", uBean.getUser_id());
		request.setAttribute("first_name", uBean.getFirst_name());
		request.setAttribute("last_name", uBean.getLast_name());
		request.setAttribute("email", uBean.getEmail());
		request.setAttribute("mobile", uBean.getMobile());
		request.setAttribute("password", uBean.getPassword());
		request.setAttribute("role", uBean.getRole());
		request.setAttribute("role_id", uBean.getRole_id());
		request.setAttribute("user_type", uBean.getUser_type());
		request.setAttribute("user_type_id", uBean.getUser_type_id());
		int length = uBean.getOutlets().length();
		if (length > 130) {
			request.setAttribute("outlets", "all");
		} else {
			request.setAttribute("outlets", uBean.getOutlets());
		}

		List<UserBean> userTypeList = hDao.getUserTypes(uBean);
		model.addObject("userTypeList", userTypeList);
		List<UserBean> roleList = hDao.getRoles(uBean);
		model.addObject("roleList", roleList);
		List<UserBean> outletList = hDao.getOutletList(uBean);
		model.addObject("outletList", outletList);

		return model;
	}

	@RequestMapping(value = "/editUser/{uid}", method = RequestMethod.POST)
	public ModelAndView editUserPost(@PathVariable String uid, HttpServletRequest request, HttpServletResponse response,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/viewUser");

		if (uBean.getOutlets().equals("all")) {
			hDao.getAllOutlets(uBean);
			hDao.updateUserByIdToAllOutlets(uBean, uid, uBean.getOutlet_id());
		} else {
			hDao.updateUserById(uBean, uid);
		}

		return model;
	}

	@RequestMapping("/deleteUser/{uid}")
	public ModelAndView deleteUser(@PathVariable String uid, HttpServletRequest request, HttpServletResponse response,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/viewUser");
		hDao.deleteUserById(uBean, uid);
		return model;
	}

	@RequestMapping(value = "/user_type", method = RequestMethod.POST)
	public ModelAndView signin_post(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/createUser");
		hDao.addUserType(uBean);
		return model;
	}

	@RequestMapping("/roles")
	public ModelAndView roles(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("roles");
		List<UserBean> userTypeList = hDao.getUserTypes(uBean);
		model.addObject("userTypeList", userTypeList);
		return model;
	}

	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public ModelAndView roles_post(HttpServletRequest request, HttpServletResponse response, MenuBean mBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/roles");
		hDao.assignRole(mBean);
		return model;
	}

	@RequestMapping("/menu")
	public ModelAndView menu(HttpServletRequest request, HttpServletResponse response, MenuBean mBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("menu");
		List<MenuBean> mainMenuList = hDao.getMainMenu(mBean);
		model.addObject("mainMenuList", mainMenuList);
		List<MenuBean> menuList = hDao.getMenu(mBean);
		model.addObject("menuList", menuList);
		return model;
	}

	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public ModelAndView menu_post(HttpServletRequest request, HttpServletResponse response, MenuBean mBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/menu");
		hDao.addMenu(mBean);
		return model;
	}

	@RequestMapping("/mail_settings")
	public ModelAndView mail_settings(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("mail_settings");
		return model;
	}

	/**
	 * Manage User Type
	 */
	@RequestMapping("/manageUserType")
	public ModelAndView manageUserType(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("manage_user_type");
		List<UserBean> userTypeList = hDao.getUserType(uBean);
		model.addObject("userTypeList", userTypeList);
		return model;
	}

	@RequestMapping(value = "/userType", method = RequestMethod.POST)
	public ModelAndView userType(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/manageUserType");

		hDao.addUserType(uBean);

		return model;
	}

	@RequestMapping("/editUserType/{uid}")
	public ModelAndView editUserType(@PathVariable String uid, HttpServletRequest request, HttpServletResponse response,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("usertype_edit");
		hDao.getUserTypeDetailsById(uBean, uid);
		request.setAttribute("userType_id", uBean.getUser_type_id());
		request.setAttribute("userType", uBean.getUser_type());

		return model;
	}

	@RequestMapping(value = "/userType_edit", method = RequestMethod.POST)
	public ModelAndView editUserPost(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/manageUserType");
		hDao.updateUserTypeById(uBean);
		return model;
	}

	@RequestMapping("/deleteUserType/{uid}")
	public ModelAndView deleteUserType(@PathVariable String uid, HttpServletRequest request,
			HttpServletResponse response, UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/manageUserType");
		hDao.deleteUserTypeById(uBean, uid);
		return model;
	}

	/**
	 * Manage User Type End
	 */
	@RequestMapping("/smsSettings")
	public ModelAndView sms_settings(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		model = new ModelAndView("sms_settings");
		hDao.getSmsSettingInfo(uBean, (String) session.getAttribute("user_id"));
		request.setAttribute("user_id", uBean.getUser_id());
		request.setAttribute("sms_template", uBean.getSms_template());
		request.setAttribute("user_name", uBean.getUser_name());
		request.setAttribute("password", uBean.getPassword());
		request.setAttribute("sender_id", uBean.getSender_id());
		request.setAttribute("sms_for", uBean.getSms_for());
		return model;
	}

	@RequestMapping(value = "/smsSettings", method = RequestMethod.POST)
	public ModelAndView smsSettings(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		model = new ModelAndView("redirect:/smsSettings");
		hDao.addSmsSettings(uBean, (String) session.getAttribute("user_id"));
		return model;
	}

	@RequestMapping("/createDealership")
	public ModelAndView createDealership(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("dealership");
		List<UserBean> dealerList = hDao.getDealerList(uBean);
		model.addObject("dealerList", dealerList);
		return model;
	}

	@RequestMapping(value = "/createDealership", method = RequestMethod.POST)
	public String createDealershipPost(
			@ModelAttribute("multipleFileUploadForm") MultipleFileUploadForm multipleFileUploadForm, Model model,
			HttpServletRequest request, HttpServletResponse response, UserBean uBean) throws IOException {
		request.getSession();

		List<MultipartFile> files = multipleFileUploadForm.getFiles();
		String file = "";
		List<String> fileNames = new ArrayList<String>();
		for (int i = 0; i < files.size(); i++) {
			file = files.get(i).getOriginalFilename();
			logger.info("sfdasdfasdfds" + file);
			fileNames.add(file);
			logger.info("FILES :" + file);
		}

		if (file.isEmpty()) {
			logger.info("IN IF COND");
			file = "";

			hDao.addDealership(uBean, file);
			model.addAttribute("files", files);
			return "redirect:/createDealership";
		} else {

			HelperDao helperDao = new HelperDao();
			String renamefile = helperDao.renamePhoto(file);
			file = renamefile;
			hDao.addDealership(uBean, file);
		}

		try {
			String filePath = local_filepath + "/dealer_logo/";
			for (int i = 0; i < files.size(); i++)
				files.get(i).transferTo(new File(filePath + file));
		} catch (Exception e) {
			return "Error While uploading your files " + e.getMessage();
		}
		model.addAttribute("files", files);
		return "redirect:/createDealership";

	}

	@RequestMapping("/editDealership/{did}")
	public ModelAndView editDealership(@PathVariable String did, HttpServletRequest request,
			HttpServletResponse response, UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("dealership_edit");
		hDao.getDealerDeatailsById(uBean, did);
		request.setAttribute("did", uBean.getDealership_id());
		request.setAttribute("dname", uBean.getDealership_name());
		request.setAttribute("contact_person", uBean.getContact_person());
		request.setAttribute("email", uBean.getEmail());
		request.setAttribute("mobile", uBean.getMobile());
		request.setAttribute("address", uBean.getAddress());
		request.setAttribute("dealer_logo", uBean.getDealer_logo());
		request.setAttribute("lat", uBean.getLat());
		request.setAttribute("lang", uBean.getLang());
		return model;
	}

	@RequestMapping(value = "/editDealership/{did}", method = RequestMethod.POST)
	public String editdealershipPost(@PathVariable String did,
			@ModelAttribute("multipleFileUploadForm") MultipleFileUploadForm multipleFileUploadForm, Model model,
			HttpServletRequest request, HttpServletResponse response, UserBean uBean) throws IOException {
		request.getSession();

		List<MultipartFile> files = multipleFileUploadForm.getFiles();
		String file = "";
		List<String> fileNames = new ArrayList<String>();
		for (int i = 0; i < files.size(); i++) {
			file = files.get(i).getOriginalFilename();
			fileNames.add(file);
			logger.info("FILES :" + file);
		}

		if (file.isEmpty()) {
			logger.info("IN IF COND");
			file = uBean.getDefault_logo();

			hDao.updateDealershipById(uBean, did, file);

			model.addAttribute("files", files);
			return "redirect:/createDealership";
		} else {

			HelperDao helperDao = new HelperDao();
			String renamefile = helperDao.renamePhoto(file);
			file = renamefile;
			hDao.updateDealershipById(uBean, did, file);
		}

		try {
			String filePath = local_filepath + "/dealer_logo/";
			for (int i = 0; i < files.size(); i++)
				files.get(i).transferTo(new File(filePath + file));
		} catch (Exception e) {
			return "Error While uploading your files " + e.getMessage();
		}
		model.addAttribute("files", files);
		return "redirect:/createDealership";
	}

	@RequestMapping("/deleteDealership/{did}")
	public ModelAndView createDealership(@PathVariable String did, HttpServletRequest request,
			HttpServletResponse response, UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/createDealership");
		hDao.getDeleteDealershipById(did);
		return model;
	}

	@RequestMapping("/createOutlet")
	public ModelAndView createOutlet(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("outlet");
		List<UserBean> dealerList = hDao.getDealerList(uBean);
		model.addObject("dealerList", dealerList);
		List<UserBean> regionList = hDao.getRegionList(uBean);
		model.addObject("regionList", regionList);
		List<UserBean> stateList = hDao.getStateList(uBean);
		model.addObject("stateList", stateList);

		return model;
	}

	@RequestMapping(value = "/createOutlet", method = RequestMethod.POST)
	public ModelAndView createOutletPost(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/viewOutlet");
		hDao.addOutlet(uBean);
		return model;
	}

	@RequestMapping("/viewOutlet")
	public ModelAndView viewOutlet(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("outlet_view");
		List<UserBean> outletList = hDao.getOutletList(uBean);
		model.addObject("outletList", outletList);
		return model;
	}

	@RequestMapping("/editOutlet/{oid}")
	public ModelAndView editOutlet(@PathVariable String oid, HttpServletRequest request, HttpServletResponse response,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("outlet_edit");
		hDao.getOutletById(uBean, oid);
		request.setAttribute("did", uBean.getDealership_id());
		request.setAttribute("dname", uBean.getDealership_name());
		request.setAttribute("outlets", uBean.getOutlets());
		request.setAttribute("oid", uBean.getOutlet_id());
		request.setAttribute("contact_person", uBean.getContact_person());
		request.setAttribute("state_id", uBean.getState_id());
		request.setAttribute("city_id", uBean.getCity_id());
		request.setAttribute("mobile", uBean.getMobile());
		request.setAttribute("email", uBean.getEmail());
		request.setAttribute("password", uBean.getPassword());
		request.setAttribute("address", uBean.getAddress());
		request.setAttribute("outlet_size", uBean.getOutlet_size());
		request.setAttribute("outlet_type", uBean.getOutlet_type());
		request.setAttribute("brand", uBean.getBrand());
		request.setAttribute("lat", uBean.getLat());
		request.setAttribute("lang", uBean.getLang());
		request.setAttribute("oids", uBean.getOid());

		List<UserBean> dealerList = hDao.getDealerList(uBean);
		model.addObject("dealerList", dealerList);
		List<UserBean> regionList = hDao.getRegionList(uBean);
		model.addObject("regionList", regionList);

		hDao.getRegionById(uBean, uBean.getRegion_id());
		request.setAttribute("region_id", uBean.getRegion_id());
		request.setAttribute("region", uBean.getRegion());

		hDao.getStateDetailsById(uBean, uBean.getState_id());
		request.setAttribute("state", uBean.getState_name());
		hDao.getCityDetailsById(uBean, uBean.getCity_id());
		request.setAttribute("city", uBean.getCity_name());

		List<UserBean> stateList = hDao.getStateList(uBean);
		model.addObject("stateList", stateList);
		return model;
	}

	@RequestMapping(value = "/editOutlet/{oid}", method = RequestMethod.POST)
	public ModelAndView editOutletPost(@PathVariable String oid, HttpServletRequest request,
			HttpServletResponse response, UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/viewOutlet");
		hDao.updateOutletById(uBean, oid);
		return model;
	}

	@RequestMapping("/deleteOutlet/{oid}")
	public ModelAndView deleteOutlet(@PathVariable String oid, HttpServletRequest request, HttpServletResponse response,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/viewOutlet");
		hDao.deleteOutletById(uBean, oid);
		return model;
	}

	@RequestMapping("/createQuestionnaire")
	public ModelAndView createQuestionnaire(HttpServletRequest request, HttpServletResponse response,
			QuestionBean qBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("questionnaire");
		List<QuestionBean> sectionList = hDao.getSectionList(qBean);
		model.addObject("sectionList", sectionList);
		return model;
	}

	@RequestMapping(value = "/createQuestionnaire", method = RequestMethod.POST)
	public String createQuestionnairePost(
			@ModelAttribute("multipleFileUploadForm") MultipleFileUploadForm multipleFileUploadForm, Model model,
			HttpServletRequest request, HttpServletResponse response, QuestionBean qBean) throws IOException {
		request.getSession();

		List<MultipartFile> files = multipleFileUploadForm.getFiles();
		String file = "";
		List<String> fileNames = new ArrayList<String>();
		for (int i = 0; i < files.size(); i++) {
			file = files.get(i).getOriginalFilename();
			logger.info("sfdasdfasdfds" + file);
			fileNames.add(file);
			logger.info("FILES :" + file);
		}

		if (file.isEmpty()) {
			logger.info("IN IF COND");
			file = "";

			hDao.addQuestionnaire(qBean, file);
			model.addAttribute("files", files);
			return "redirect:/viewQuestionnaire/" + qBean.getBrand() + "/" + qBean.getType() + "";
		} else {

			HelperDao helperDao = new HelperDao();
			String renamefile = helperDao.renamePhoto(file);
			file = renamefile;
			hDao.addQuestionnaire(qBean, file);
		}

		try {
			String filePath = local_filepath + "/questionnary/";
			for (int i = 0; i < files.size(); i++)
				files.get(i).transferTo(new File(filePath + file));
		} catch (Exception e) {
			return "Error While uploading your files " + e.getMessage();
		}
		model.addAttribute("files", files);
		return "redirect:/viewQuestionnaire/" + qBean.getBrand() + "/" + qBean.getType() + "";
	}

	@RequestMapping("/viewQuestionnaire")
	public ModelAndView viewQuestionnaire(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/viewQuestionnaire/BMW/Sales");
		/*
		 * List<QuestionBean> questionnaireList = hDao.getQuestionnaireList(qBean);
		 * model.addObject("questionnaireList", questionnaireList);
		 */
		return model;
	}

	@RequestMapping("/viewQuestionnaire/{bname}/{type}")
	public ModelAndView viewQuestionnaire(@PathVariable String bname, @PathVariable String type,
			HttpServletRequest request, HttpServletResponse response, QuestionBean qBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		request.setAttribute("bname", bname);
		request.setAttribute("type", type);
		model = new ModelAndView("questionnaire_view");
		List<QuestionBean> questionnaireList = hDao.getQuestionnaireList(qBean, bname, type);
		model.addObject("questionnaireList", questionnaireList);
		return model;
	}

	@RequestMapping("/editQuestionnaire/{qid}")
	public ModelAndView editQuestionnaire(@PathVariable String qid, HttpServletRequest request,
			HttpServletResponse response, QuestionBean qBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("questionnaire_edit");
		hDao.getQuestionnaireById(qBean, qid);
		request.setAttribute("qid", qBean.getQuestionnaire_id());
		request.setAttribute("section", qBean.getSection());
		request.setAttribute("section_id", qBean.getSection_id());
		request.setAttribute("standard", qBean.getStandard());
		request.setAttribute("requirement", qBean.getRequirement());
		request.setAttribute("comment", qBean.getComment());
		request.setAttribute("number", qBean.getNumber());
		request.setAttribute("xs", qBean.getXs());
		request.setAttribute("s", qBean.getS());
		request.setAttribute("m", qBean.getM());
		request.setAttribute("l", qBean.getL());
		request.setAttribute("xl", qBean.getXl());
		request.setAttribute("xxl", qBean.getXxl());
		request.setAttribute("check", qBean.getCheck());
		request.setAttribute("essential", qBean.getEssential());
		request.setAttribute("question", qBean.getQuestion());
		request.setAttribute("observation", qBean.getObservation());
		request.setAttribute("person", qBean.getPerson());
		request.setAttribute("file", qBean.getPic());
		request.setAttribute("brand", qBean.getBrand());
		request.setAttribute("type", qBean.getType());
		request.setAttribute("image_manadatory", qBean.getImage_manadatory());

		List<QuestionBean> sectionList = hDao.getSectionList(qBean);
		model.addObject("sectionList", sectionList);

		return model;
	}

	@RequestMapping(value = "/editQuestionnaire/{qid}", method = RequestMethod.POST)
	public String editQuestionnaireUpdate(@PathVariable String qid,
			@ModelAttribute("multipleFileUploadForm") MultipleFileUploadForm multipleFileUploadForm, Model model,
			HttpServletRequest request, HttpServletResponse response, QuestionBean qBean) throws IOException {
		request.getSession();

		List<MultipartFile> files = multipleFileUploadForm.getFiles();
		String file = "";
		List<String> fileNames = new ArrayList<String>();
		for (int i = 0; i < files.size(); i++) {
			file = files.get(i).getOriginalFilename();
			fileNames.add(file);
			logger.info("FILES :" + file);
		}

		if (file.isEmpty()) {
			logger.info("IN IF COND");
			file = qBean.getDefault_logo();

			hDao.updateQuestionnaireById(qBean, qid, file);

			model.addAttribute("files", files);
			return "redirect:/viewQuestionnaire/" + qBean.getBrand() + "/" + qBean.getType() + "";
		} else {

			HelperDao helperDao = new HelperDao();
			String renamefile = helperDao.renamePhoto(file);
			file = renamefile;
			hDao.updateQuestionnaireById(qBean, qid, file);
		}

		try {
			String filePath = local_filepath + "/questionnary/";
			for (int i = 0; i < files.size(); i++)
				files.get(i).transferTo(new File(filePath + file));
		} catch (Exception e) {
			return "Error While uploading your files " + e.getMessage();
		}
		model.addAttribute("files", files);
		return "redirect:/viewQuestionnaire/" + qBean.getBrand() + "/" + qBean.getType() + "";

	}

	@RequestMapping("/deleteQuestionnaire/{qid}")
	public ModelAndView deleteQuestionnaire(@PathVariable String qid, HttpServletRequest request,
			HttpServletResponse response, QuestionBean qBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/viewQuestionnaire");
		hDao.deleteQuestionnaireById(qBean, qid);
		return model;
	}

	@RequestMapping(value = "/section", method = RequestMethod.POST)
	public ModelAndView section(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/createQuestionnaire");
		hDao.addSection(qBean);
		return model;
	}

	@RequestMapping("/sheduleAudit")
	public ModelAndView sheduleAudit(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("schedule_audit");
		List<UserBean> dealerList = hDao.getDealerList(uBean);
		model.addObject("dealerList", dealerList);
		request.setAttribute("dname", "Select dealer");
		request.setAttribute("qid", "Select phase");
		request.setAttribute("year", "Select year");
		request.setAttribute("type", "Select type");
		request.setAttribute("qidd", "");
		request.setAttribute("years", "");
		request.setAttribute("tid", "");
		return model;
	}

	@RequestMapping(value = "/sheduleAudit/{did}", method = RequestMethod.POST)
	public ModelAndView sheduleAuditPost(@PathVariable String did, HttpServletRequest request,
			HttpServletResponse response, UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();
		model = new ModelAndView("redirect:/sheduleAudit");
		try {
			hDao.checkDealerAuditExist(uBean, did, uBean.getQuarter(), uBean.getYear());
			hDao.updateAudit(uBean, did);
		} catch (Exception e) {
			hDao.addAudit(uBean, did);
			logger.info("No Dealers Yet" + e);

		}
		hDao.getDealerDeatailsById(uBean, did);

		return model;
	}

	@RequestMapping("/sheduleAudit/{did}/{qid}/{year}")
	public ModelAndView sheduleAudit(@PathVariable String did, @PathVariable String qid, @PathVariable String year,
			HttpServletRequest request, HttpServletResponse response, UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("schedule_audit_update");
		request.setAttribute("qid", qid);
		request.setAttribute("year", year);
		request.setAttribute("qidd", qid);
		request.setAttribute("years", year);
		/*
		 * request.setAttribute("type", type); request.setAttribute("tid", type);
		 */
		try {
			hDao.checkDealerAuditExist(uBean, did, qid, year);
			List<UserBean> outletList = hDao.getOutletsFromAudit(uBean, did, qid, year);
			model.addObject("outletList", outletList);
			request.setAttribute("emails", outletList.get(0).getEmail());
		} catch (Exception e) {
			logger.info("No Dealers Yet" + e);
			List<UserBean> outletList = hDao.getOutletBydealerShipId(uBean, did);
			model.addObject("outletList", outletList);
		}

		List<UserBean> auditorList = hDao.getAuditorList(uBean);
		model.addObject("auditorList", auditorList);

		hDao.getDealerDeatailsById(uBean, did);
		request.setAttribute("did", uBean.getDealership_id());
		request.setAttribute("dname", uBean.getDealership_name());

		List<UserBean> dealerList = hDao.getDealerList(uBean);
		model.addObject("dealerList", dealerList);
		return model;
	}

	@RequestMapping("/getSubMenu")
	public void getLandmarks(HttpServletRequest request, HttpServletResponse response, MenuBean mBean)
			throws IOException {
		request.getSession();

		String menuid = request.getParameter("menuid");
		List<MenuBean> subMenuList = hDao.getSubMenuById(mBean, menuid, "");
		Gson gson = new Gson();
		String subMenu = gson.toJson(subMenuList);
		try {
			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(subMenu);
			pw.flush();
			pw.close();

		} catch (Exception e) {
			logger.error(e);
		}
	}

	@RequestMapping("/getOutletsById")
	public void getOutletsByUserId(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		request.getSession();

		String outlet_id = request.getParameter("outlet_id");
		if (outlet_id.equals("all")) {
			List<UserBean> outletList = hDao.getAllOutlets(uBean, outlet_id);
			Gson gson = new Gson();
			String outList = gson.toJson(outletList);
			try {
				response.setContentType("application/json");
				PrintWriter pw = response.getWriter();
				pw.print(outList);
				pw.flush();
				pw.close();

			} catch (Exception e) {
				logger.error(e);
			}
		} else {
			List<UserBean> outletList = hDao.getOutletsByUserId(uBean, outlet_id);
			Gson gson = new Gson();
			String outList = gson.toJson(outletList);
			try {
				response.setContentType("application/json");
				PrintWriter pw = response.getWriter();
				pw.print(outList);
				pw.flush();
				pw.close();

			} catch (Exception e) {
				logger.error(e);
			}
		}

	}

	@RequestMapping("/getSectionsByType")
	public void getSectionsByType(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean)
			throws IOException {
		request.getSession();

		String type = request.getParameter("type");
		String brand = request.getParameter("brand");
		List<QuestionBean> sectionList = hDao.getSectionsByType(qBean, type, brand);
		Gson gson = new Gson();
		String sectionLists = gson.toJson(sectionList);
		try {
			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(sectionLists);
			pw.flush();
			pw.close();

		} catch (Exception e) {
			logger.error(e);
		}
	}

	@RequestMapping("/getDealersByOutlet")
	public void getDealersByOutlet(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean)
			throws IOException {
		request.getSession();

		String outlet_type = request.getParameter("type");
		List<QuestionBean> dealerList = hDao.getDealersByOutlet(qBean, outlet_type);
		Gson gson = new Gson();
		String outList = gson.toJson(dealerList);
		try {
			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(outList);
			pw.flush();
			pw.close();

		} catch (Exception e) {
			logger.error(e);
		}
	}

	@RequestMapping("/viewAudit")
	public ModelAndView viewAudit(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		String status = "active";
		request.getSession();

		model = new ModelAndView("viewAudit");
		List<UserBean> auditList = hDao.getAuditList(uBean, status);
		model.addObject("auditList", auditList);

		request.setAttribute("dname", "Select dealer");
		return model;
	}

	@RequestMapping("/viewCompletedAudit")
	public ModelAndView viewCompletedAudit(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		String status = "completed";
		request.getSession();

		model = new ModelAndView("view_completed_audit");
		List<UserBean> auditList = hDao.getAuditList(uBean, status);
		model.addObject("auditList", auditList);
		request.setAttribute("dname", "Select dealer");
		return model;
	}

	@RequestMapping("/viewClosedAudit")
	public ModelAndView viewClosedAudit(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		String status = "closed";
		request.getSession();

		model = new ModelAndView("view_closed_audit");
		List<UserBean> auditList = hDao.getAuditList(uBean, status);
		model.addObject("auditList", auditList);
		request.setAttribute("dname", "Select dealer");
		return model;
	}

	@RequestMapping("/viewDealerSubmissions/{phase}/{year}")
	public ModelAndView viewDealerSubmissions(@PathVariable String phase, @PathVariable String year,
			HttpServletRequest request, HttpServletResponse response, UserBean uBean) throws IOException {
		ModelAndView model = null;
		String status = "closed";
		request.getSession();

		request.setAttribute("phase", phase);
		request.setAttribute("year", year);

		model = new ModelAndView("view_dealer_submissions");
		List<UserBean> dealerCompletedList = hDao.getDealerWiseCompletedAudits(uBean, status, phase, year);
		model.addObject("dealerCompletedList", dealerCompletedList);

		return model;
	}

	@RequestMapping("/tentative-scores/{score_type}/{outlet_type}/{brand}/{phase}/{year}")
	public ModelAndView tentative_scores(@PathVariable String score_type, @PathVariable String outlet_type,
			@PathVariable String brand, @PathVariable String phase, @PathVariable String year,
			HttpServletRequest request, HttpServletResponse response, UserBean uBean, QuestionBean qBean)
			throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("tentative-scores");

		request.setAttribute("score_type", score_type);
		request.setAttribute("outlet_type", outlet_type);
		request.setAttribute("brand", brand);
		request.setAttribute("phase", phase);
		request.setAttribute("year", year);

		List<QuestionBean> DealerScoresList = hDao.getDealerWiseScores(qBean, phase, year, score_type, outlet_type,
				brand);
		model.addObject("dealerScoresList", DealerScoresList);

		List<QuestionBean> OutletScoresList = hDao.getOutletWiseScores(qBean, phase, year, score_type, outlet_type,
				brand);
		model.addObject("OutletScoresList", OutletScoresList);

		return model;
	}

	@RequestMapping("/auditReOpen/{aid}")
	public ModelAndView auditReOpen(@PathVariable String aid, HttpServletRequest request, HttpServletResponse response,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/viewClosedAudit");
		hDao.updateAuditStatusByAuditId(uBean, aid);
		return model;
	}

	@RequestMapping("/getQuestionnaire/{did}/{oid}/{asid}")
	public ModelAndView getQuestionnaire(@PathVariable String did, @PathVariable String oid, @PathVariable String asid,
			HttpServletRequest request, HttpServletResponse response, QuestionBean qBean, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		model = new ModelAndView("getQuestionnaire");
		hDao.getOutletById(uBean, oid);
		request.setAttribute("outlet", uBean.getOutlets());
		request.setAttribute("outlet_size", uBean.getOutlet_size());
		request.setAttribute("outlet_type", uBean.getOutlet_type());
		request.setAttribute("brand", uBean.getBrand());

		List<QuestionBean> questionnaireList = hDao.getQuestionnaireListWithOptions(qBean, asid, uBean.getOutlet_type(),
				uBean.getOutlet_size(), uBean.getBrand(), "NSC");
		model.addObject("questionnaireList", questionnaireList);
		request.getSession().setAttribute("nsc_count", questionnaireList.size());

		List<QuestionBean> auditorsubmissionList = hDao.getQuestionnaireListWithOptions(qBean, asid,
				uBean.getOutlet_type(), uBean.getOutlet_size(), uBean.getBrand(), "Audit");
		request.getSession().setAttribute("audit_count", auditorsubmissionList.size());
		List<QuestionBean> pmoclosureList = hDao.getPmoClosureQuestionnaires(qBean, asid, uBean.getOutlet_type(),
				uBean.getOutlet_size());
		request.getSession().setAttribute("closure_count", pmoclosureList.size());
		List<QuestionBean> exceptionList = hDao.getExceptionQuestionnaireListWithOptions(qBean, asid);
		request.getSession().setAttribute("exception_count", exceptionList.size());
		List<QuestionBean> MSquestionnaireList = hDao.getMSQuestionnaireListWithOptions(qBean, asid,
				uBean.getOutlet_type(), uBean.getOutlet_size(), uBean.getBrand());
		request.getSession().setAttribute("msquestions_count", MSquestionnaireList.size());

		session = request.getSession();
		request.setAttribute("user_id", session.getAttribute("user_id"));
		request.setAttribute("dealer_id", did);
		request.setAttribute("outlet_id", oid);
		request.setAttribute("audit_schedule_id", asid);

		hDao.getDealerDeatailsById(uBean, did);
		request.setAttribute("dname", uBean.getDealership_name());
		return model;
	}

	@RequestMapping("/auditorSubmissions/{did}/{oid}/{asid}")
	public ModelAndView auditorSubmissions(@PathVariable String did, @PathVariable String oid,
			@PathVariable String asid, HttpServletRequest request, HttpServletResponse response, QuestionBean qBean,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		model = new ModelAndView("get_audit_submissions");
		hDao.getOutletById(uBean, oid);
		request.setAttribute("outlet", uBean.getOutlets());
		request.setAttribute("outlet_size", uBean.getOutlet_size());
		request.setAttribute("outlet_type", uBean.getOutlet_type());
		request.setAttribute("brand", uBean.getBrand());

		List<QuestionBean> questionnaireList = hDao.getQuestionnaireListWithOptions(qBean, asid, uBean.getOutlet_type(),
				uBean.getOutlet_size(), uBean.getBrand(), "Audit");

		List<List<QuestionBean>> imageList = new ArrayList<List<QuestionBean>>();
		Iterator<QuestionBean> iterator = questionnaireList.iterator();
		while (iterator.hasNext()) {
			String questionId = iterator.next().getQuestionId();
			imageList.add(hDao.getImage(qBean, questionId, asid));
		}
		model.addObject("imageList", imageList);
		model.addObject("questionnaireList", questionnaireList);

		request.getSession().setAttribute("audit_count", questionnaireList.size());
		session = request.getSession();
		request.setAttribute("user_id", session.getAttribute("user_id"));
		request.setAttribute("dealer_id", did);
		request.setAttribute("outlet_id", oid);
		request.setAttribute("audit_schedule_id", asid);

		hDao.getDealerDeatailsById(uBean, did);
		request.setAttribute("dname", uBean.getDealership_name());

		hDao.getauditDetails(asid, qBean);
		request.setAttribute("year", qBean.getYear());
		request.setAttribute("phase", qBean.getPhase());
		return model;
	}

	@RequestMapping("/getPmoClosure/{did}/{oid}/{asid}")
	public ModelAndView getPmoClosure(@PathVariable String did, @PathVariable String oid, @PathVariable String asid,
			HttpServletRequest request, HttpServletResponse response, QuestionBean qBean, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		request.setAttribute(did, did);
		request.setAttribute(oid, oid);
		request.setAttribute(asid, asid);

		model = new ModelAndView("getPmoClosure");
		hDao.getOutletById(uBean, oid);
		request.setAttribute("outlet", uBean.getOutlets());
		request.setAttribute("outlet_size", uBean.getOutlet_size());
		request.setAttribute("outlet_type", uBean.getOutlet_type());
		request.setAttribute("brand", uBean.getBrand());

		List<QuestionBean> questionnaireList = hDao.getPmoClosureQuestionnaires(qBean, asid, uBean.getOutlet_type(),
				uBean.getOutlet_size());
		model.addObject("questionnaireList", questionnaireList);
		request.getSession().setAttribute("closure_count", questionnaireList.size());
		session = request.getSession();
		request.setAttribute("user_id", session.getAttribute("user_id"));
		request.setAttribute("dealer_id", did);
		request.setAttribute("outlet_id", oid);
		request.setAttribute("audit_schedule_id", asid);

		hDao.getDealerDeatailsById(uBean, did);
		request.setAttribute("dname", uBean.getDealership_name());
		return model;
	}

	@RequestMapping("/getExceptionQuestionnaires/{did}/{oid}/{asid}")
	public ModelAndView getExceptionQuestionnaires(@PathVariable String did, @PathVariable String oid,
			@PathVariable String asid, HttpServletRequest request, HttpServletResponse response, QuestionBean qBean,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		model = new ModelAndView("getExceptionQuestionnaire");
		List<QuestionBean> questionnaireList = hDao.getExceptionQuestionnaireListWithOptions(qBean, asid);

		List<List<QuestionBean>> imageList = new ArrayList<List<QuestionBean>>();
		Iterator<QuestionBean> iterator = questionnaireList.iterator();
		while (iterator.hasNext()) {
			String questionId = iterator.next().getQuestionId();
			imageList.add(hDao.getImage(qBean, questionId, asid));
		}
		model.addObject("imageList", imageList);

		model.addObject("questionnaireList", questionnaireList);
		request.getSession().setAttribute("exception_count", questionnaireList.size());
		session = request.getSession();
		request.setAttribute("user_id", session.getAttribute("user_id"));
		logger.info(session.getAttribute("user_id"));
		request.setAttribute("dealer_id", did);
		request.setAttribute("outlet_id", oid);
		request.setAttribute("audit_schedule_id", asid);
		hDao.getOutletById(uBean, oid);
		request.setAttribute("outlet", uBean.getOutlets());
		request.setAttribute("outlet_size", uBean.getOutlet_size());
		request.setAttribute("outlet_type", uBean.getOutlet_type());
		request.setAttribute("brand", uBean.getBrand());
		hDao.getDealerDeatailsById(uBean, did);
		request.setAttribute("dname", uBean.getDealership_name());

		hDao.getauditDetails(asid, qBean);
		request.setAttribute("year", qBean.getYear());
		request.setAttribute("phase", qBean.getPhase());
		return model;
	}

	@RequestMapping(value = "/updatePMOComments", method = RequestMethod.POST)
	public ModelAndView updatePMOComments(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean)
			throws IOException {
		ModelAndView model = null;
		String did = request.getParameter("dealer_id");
		String oid = request.getParameter("outlet_id");
		String asid = request.getParameter("audit_schedule_id");
		model = new ModelAndView("redirect:/getExceptionQuestionnaires/" + did + "/" + oid + "/" + asid + "");
		HttpSession session = request.getSession();
		@SuppressWarnings("unused")
		String user_type = (String) session.getAttribute("user_type");
		hDao.updatePMOComments(qBean);
		return model;
	}

	@RequestMapping("/getMSQuestionnaire/{did}/{oid}/{asid}")
	public ModelAndView getMSQuestionnaire(@PathVariable String did, @PathVariable String oid,
			@PathVariable String asid, HttpServletRequest request, HttpServletResponse response, QuestionBean qBean,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		model = new ModelAndView("getMSQuestionnaire");
		hDao.getOutletById(uBean, oid);
		request.setAttribute("outlet", uBean.getOutlets());
		request.setAttribute("outlet_size", uBean.getOutlet_size());
		request.setAttribute("outlet_type", uBean.getOutlet_type());
		request.setAttribute("brand", uBean.getBrand());
		List<QuestionBean> questionnaireList = hDao.getMSQuestionnaireListWithOptions(qBean, asid,
				uBean.getOutlet_type(), uBean.getOutlet_size(), uBean.getBrand());
		model.addObject("questionnaireList", questionnaireList);
		/*
		 * request.getSession().setAttribute("exception_count",
		 * questionnaireList.size());
		 */
		session = request.getSession();
		request.setAttribute("user_id", session.getAttribute("user_id"));
		request.setAttribute("dealer_id", did);
		request.setAttribute("outlet_id", oid);
		request.setAttribute("audit_schedule_id", asid);

		hDao.getDealerDeatailsById(uBean, did);
		request.setAttribute("dname", uBean.getDealership_name());
		return model;
	}

	@RequestMapping("/review/{did}/{oid}/{asid}")
	public ModelAndView review(@PathVariable String did, @PathVariable String oid, @PathVariable String asid,
			HttpServletRequest request, HttpServletResponse response, QuestionBean qBean, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("review");

		HttpSession session = request.getSession();

		List<QuestionBean> questionnaireList = hDao.getExceptionQuestionnaireListWithOptions(qBean, asid);
		model.addObject("questionnaireList", questionnaireList);
		request.getSession().setAttribute("exception_count", questionnaireList.size());
		session = request.getSession();
		hDao.getOutletById(uBean, oid);
		request.setAttribute("user_id", session.getAttribute("user_id"));
		request.setAttribute("dealer_id", did);
		request.setAttribute("outlet_id", oid);
		request.setAttribute("audit_schedule_id", asid);
		request.setAttribute("outlet", uBean.getOutlets());
		request.setAttribute("outlet_size", uBean.getOutlet_size());
		request.setAttribute("outlet_type", uBean.getOutlet_type());
		request.setAttribute("brand", uBean.getBrand());
		hDao.getDealerDeatailsById(uBean, did);
		request.setAttribute("dname", uBean.getDealership_name());

		return model;
	}

	@RequestMapping("/publish/{did}/{oid}/{asid}")
	public ModelAndView addOutletImage(@PathVariable String did, @PathVariable String oid, @PathVariable String asid,
			HttpServletRequest request, HttpServletResponse response, QuestionBean qBean, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();
		model = new ModelAndView("publish");
		request.setAttribute("did", did);
		request.setAttribute("oid", oid);
		request.setAttribute("asid", asid);
		hDao.getOutletById(uBean, oid);
		request.setAttribute("outlet", uBean.getOutlets());
		request.setAttribute("outlet_size", uBean.getOutlet_size());
		request.setAttribute("outlet_type", uBean.getOutlet_type());
		request.setAttribute("brand", uBean.getBrand());
		hDao.getDealerDeatailsById(uBean, did);
		request.setAttribute("dname", uBean.getDealership_name());

		String brand = uBean.getBrand();
		String outlet_type = uBean.getOutlet_type();
		String outlet_size = uBean.getOutlet_size();

		List<QuestionBean> pmoClosureList = hDao.getPmoClosureQuestionnaires(qBean, asid, uBean.getOutlet_type(),
				uBean.getOutlet_size());
		model.addObject("pmoClosureList", pmoClosureList);
		request.getSession().setAttribute("closure_count", pmoClosureList.size());

		/*
		 * List<QuestionBean> avgList =
		 * hDao.getAuditScoreOf1(asid,oid,uBean.getBrand(),uBean.getOutlet_size(
		 * ),uBean.getOutlet_type(),qBean); logger.info("SIZE :"+avgList.size());
		 * List<QuestionBean> avgList1 =
		 * hDao.getAuditScoreOf0(asid,oid,uBean.getBrand(),uBean.getOutlet_size(
		 * ),uBean.getOutlet_type(),qBean); logger.info("SIZE1 :"+avgList1.size());
		 * Double total_count = (double) (avgList.size()+avgList1.size()); Double
		 * avg_score = (avgList.size()/total_count)*100; logger.info("double : " +
		 * String.format("%.1f", avg_score)); String final_avg_score =
		 * String.format("%.1f", avg_score)+"%"; request.setAttribute("final_avg_score",
		 * final_avg_score);
		 * 
		 * 
		 * List<QuestionBean> essavgList =
		 * hDao.getAuditScoreOf1ess(asid,oid,uBean.getBrand(),uBean.
		 * getOutlet_size(),uBean.getOutlet_type(),qBean);
		 * logger.info("SIZE :"+avgList.size()); List<QuestionBean> essavgList1 =
		 * hDao.getAuditScoreOf0ess(asid,oid,uBean.getBrand(),uBean.
		 * getOutlet_size(),uBean.getOutlet_type(),qBean);
		 * logger.info("SIZE1 :"+avgList1.size()); Double total_count1 = (double)
		 * (essavgList.size()+essavgList1.size()); Double avg_score1 =
		 * (essavgList.size()/total_count1)*100; logger.info("double : " +
		 * String.format("%.1f", avg_score1)); String final_avg_score1 =
		 * String.format("%.1f", avg_score1)+"%"; request.setAttribute("ess_score",
		 * final_avg_score1);
		 */

		List<QuestionBean> sectionList = hDao.getSectionsByType(qBean, outlet_type, brand);

		List<List<QuestionBean>> sectionScoreList = new ArrayList<List<QuestionBean>>();
		Iterator<QuestionBean> iterator = sectionList.iterator();
		while (iterator.hasNext()) {
			String section_id = iterator.next().getSection_id();
			sectionScoreList.add(hDao.getSectionScoreList(qBean, section_id, asid, brand, outlet_size, outlet_type));
			logger.info("Section Id :" + sectionScoreList.get(0).get(0).getSubquestionanswers());
		}
		model.addObject("sectionList", sectionList);
		model.addObject("sectionScoreList", sectionScoreList);
		logger.info("sectionScoreList" + sectionScoreList);

		hDao.getTotalQuestionCountOfAuditId(asid, qBean, brand, outlet_type, outlet_size);
		hDao.getTotalQuestionAnsweredCountOfAuditId(asid, qBean, brand, outlet_type, outlet_size);
		hDao.getTotalQuestionAnsweredYesCountOfAuditId(asid, qBean, brand, outlet_type, outlet_size);
		hDao.getTotalQuestionAnsweredNoCountOfAuditId(asid, qBean, brand, outlet_type, outlet_size);

		hDao.getTotalQuestionCountOfAuditIdEssentialX(asid, qBean, brand, outlet_type, outlet_size);
		hDao.getTotalQuestionAnsweredCountOfAuditIdEssentialX(asid, qBean, brand, outlet_type, outlet_size);
		hDao.getTotalQuestionAnsweredCountOfAuditIdEssentialXNo(asid, qBean, brand, outlet_type, outlet_size);

		request.setAttribute("total_qns", qBean.getAudit_count());
		request.setAttribute("total_qns_ans", qBean.getAudit_count_ans());
		request.setAttribute("one_count", qBean.getOne_count());
		request.setAttribute("zero_count", qBean.getZero_count());
		request.setAttribute("total_x_qns", qBean.getNsc_count());
		request.setAttribute("total_x_qns_ans", qBean.getNsc_count_ans());
		request.setAttribute("ess_no_qns", qBean.getEssential_count());

		int v1 = Integer.parseInt(qBean.getAudit_count()) - Integer.parseInt(qBean.getZero_count());
		logger.info("V1 :" + v1);
		int v2 = Integer.parseInt(qBean.getAudit_count());
		logger.info("V2 :" + v2);
		Double final_avg_score = ((Double.valueOf(v1) / v2)) * 100;
		request.setAttribute("final_avg_score", String.format("%.1f", final_avg_score) + "%");

		int v3 = Integer.parseInt(qBean.getNsc_count()) - Integer.parseInt(qBean.getEssential_count());
		int v4 = Integer.parseInt(qBean.getNsc_count());
		Double final_x_score = ((Double.valueOf(v3) / v4)) * 100;
		request.setAttribute("final_x_score", String.format("%.1f", final_x_score) + "%");
		try {
			hDao.checkScoreSubmitted(qBean, asid);

			request.setAttribute("button", "1");
		} catch (Exception e) {
			logger.info("Score not submited :" + e);
			request.setAttribute("button", asid);

		}

		return model;
	}

	@RequestMapping(value = "/publish/{did}/{oid}/{asid}", method = RequestMethod.POST)
	public String publish(@PathVariable String did, @PathVariable String oid, @PathVariable String asid,
			HttpServletRequest request, HttpServletResponse response, QuestionBean qBean, UserBean uBean)
			throws IOException {
		request.getSession();
		request.getSession();
		hDao.getOutletById(uBean, oid);
		String outlet_type = uBean.getOutlet_type();
		String region_id = uBean.getRegion_id();
		String brand = uBean.getBrand();
		hDao.getOutletScheduledDetails(uBean, asid);
		String year = uBean.getYear();
		String phase = uBean.getQuarter();
		try {
			hDao.checkSectionWiseResultsExist(asid, qBean);
			hDao.updateSectionScoreResults(qBean, did, oid, asid, region_id, outlet_type, phase, year, brand);
		} catch (Exception e) {
			logger.info("results not exist" + e);
			hDao.addSectionScoreResults(qBean, did, oid, asid, region_id, outlet_type, phase, year, brand);
		}
		try {
			hDao.checkFinalScoreExist(asid, qBean);
			hDao.updateFinalScore(qBean, did, oid, asid, outlet_type, year, phase, brand);
		} catch (Exception e) {
			logger.info("final results not exist" + e);
			hDao.addfinalScore(qBean, did, oid, asid, outlet_type, year, phase, brand);
		}

		/**** FINAL Section and score calculations ****/
		try {
			hDao.checkDealerSectionScoreExist(qBean, did, year, phase, brand);
			hDao.deleteDealerScore(qBean, did, year, phase, brand);
		} catch (Exception e) {

			logger.error(e);
		}
		rDao.getDealerSalesSectionScore(qBean, did, year, phase, brand);
		rDao.getDealerServiceSectionScore(qBean, did, year, phase, brand);

		try {
			hDao.checkDealerFinalScoreExist(qBean, did, year, phase, brand);
			hDao.deleteDeleteDealerFinalScore(qBean, did, year, phase, brand);
		} catch (Exception e) {

			logger.error(e);
		}
		rDao.getDealerSalesFinalScore(qBean, did, year, phase, brand);
		rDao.getDealerServiceFinalScore(qBean, did, year, phase, brand);

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/getQuestionnaire", method = RequestMethod.POST)
	public String getQuestionnairePost(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean)
			throws IOException {
		HttpSession session = request.getSession();

		session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		String temp1[] = qBean.getUser_id().split(",");
		String temp2[] = qBean.getOutlet_id().split(",");
		String temp3[] = qBean.getAnswer().split(",");
		String temp4[] = qBean.getQuestionnaire_id().split(",");
		String section_id = "";
		String toc = "";
		String essential = "";
		String outlet_size = "";
		for (int j = 0; j < temp1.length; j++) {
			hDao.getQuestionnaireById(qBean, temp4[j]);
			section_id = qBean.getSection_id();
			toc = qBean.getCheck();
			essential = qBean.getEssential();
			hDao.getOutletById(qBean, temp2[j]);
			outlet_size = qBean.getOutlet_size();
		}
		for (int i = 0; i < temp3.length; i++) {
			try {
				hDao.checkAuditExist(qBean, temp4[i]);
				logger.info("AUDIT EXIST UPDATING....");
				hDao.updateAuditById(qBean, temp3[i], temp4[i]);
			} catch (Exception e) {
				logger.error(e + "Not yet audited");
				hDao.insertAudit(qBean, section_id, toc, outlet_size, user_id, essential, temp3[i], temp4[i]);
			}
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping("/addOutletImage/{oid}")
	public ModelAndView addOutletImage(@PathVariable String oid, HttpServletRequest request,
			HttpServletResponse response, UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("outletImageUpload");
		List<UserBean> getoutletImages = hDao.getOutletImages(uBean, oid);
		model.addObject("getoutletImages", getoutletImages);
		request.setAttribute("oid", oid);
		return model;
	}

	@RequestMapping(value = "/addOutletImages/{oid}", method = RequestMethod.POST)
	public String addOutletImages(@PathVariable String oid,
			@ModelAttribute("multipleFileUploadForm") MultipleFileUploadForm multipleFileUploadForm, Model model,
			UserBean uBean, HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession();

		List<MultipartFile> files = multipleFileUploadForm.getFiles();
		String file = "";
		List<String> fileNames = new ArrayList<String>();
		for (int i = 0; i < files.size(); i++) {
			file = files.get(i).getOriginalFilename();
			fileNames.add(file);
			HelperDao helperDao = new HelperDao();
			String renamefile = helperDao.renamePhoto(file);
			file = renamefile;
			logger.info("FILES :" + file);
		}

		// file=file+oid;
		hDao.addOutletImages(uBean, file, oid);
		try {
			String filePath = local_filepath + "/outlet/";
			for (int i = 0; i < files.size(); i++)
				files.get(i).transferTo(new File(filePath + file));
		} catch (Exception e) {
			return "Error While uploading your files " + e.getMessage();
		}
		model.addAttribute("files", files);
		return "redirect:/addOutletImage/" + oid + "";
	}

	@RequestMapping(value = "/closeAudit", method = RequestMethod.POST)
	public ModelAndView closeAudit(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/viewAudit");
		hDao.updateAuditSummary(uBean);

		return model;
	}

	@RequestMapping("/emailSettings")
	public ModelAndView systemSettings(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		model = new ModelAndView("emailSettings");
		try {
			hDao.getEmailSettingInfo(uBean, (String) session.getAttribute("user_id"));
			request.setAttribute("user_id", uBean.getUser_id());
			request.setAttribute("smtp_host", uBean.getSmtp_host());
			request.setAttribute("smtp_port", uBean.getSmtp_port());
			request.setAttribute("password", uBean.getPassword());
			request.setAttribute("emailp", uBean.getEmail());
			request.setAttribute("smtp_security_type", uBean.getSmtp_security_type());
			request.setAttribute("smtp_settings_for", uBean.getSmtp_settings_for());
		} catch (Exception e) {
			logger.info("NO EMAIL CONFIGURED YET" + e);
		}
		return model;
	}

	@RequestMapping(value = "/emailSettings", method = RequestMethod.POST)
	public ModelAndView emailDetails(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		model = new ModelAndView("redirect:/emailSettings");
		hDao.addEmailSettings(uBean, (String) session.getAttribute("user_id"));
		return model;
	}

	@RequestMapping("/clientSettings")
	public ModelAndView menu_management(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		model = new ModelAndView("client_settings");
		try {
			hDao.getClientSettingInfo(uBean, (String) session.getAttribute("user_id"));
			request.setAttribute("user_id", uBean.getUser_id());
			request.setAttribute("client_logo", uBean.getClient_logo());
			request.setAttribute("client_name", uBean.getClient_name());
			request.setAttribute("emailp", uBean.getEmail());
			request.setAttribute("mobilep", uBean.getMobile());
			logger.info(uBean.getMobile());
		} catch (Exception e) {
			logger.error(e);
		}
		return model;
	}

	@RequestMapping(value = "/clientSettings", method = RequestMethod.POST)
	public String clientSettings(
			@ModelAttribute("multipleFileUploadForm") MultipleFileUploadForm multipleFileUploadForm, Model model,
			UserBean uBean, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		List<MultipartFile> files = multipleFileUploadForm.getFiles();
		String file = "";
		List<String> fileNames = new ArrayList<String>();
		for (int i = 0; i < files.size(); i++) {
			file = files.get(i).getOriginalFilename();
			fileNames.add(file);
			HelperDao helperDao = new HelperDao();
			String renamefile = helperDao.renamePhoto(file);
			file = renamefile;
			logger.info("FILES :" + file);
		}

		hDao.addClientSettings(uBean, file, (String) session.getAttribute("user_id"));
		try {
			String filePath = local_filepath + "/client_logo/";
			for (int i = 0; i < files.size(); i++)
				files.get(i).transferTo(new File(filePath + file));
		} catch (Exception e) {
			return "Error While uploading your files " + e.getMessage();
		}
		model.addAttribute("files", files);
		return "redirect:/clientSettings";
	}

	@RequestMapping("/dealerNotifications")
	public ModelAndView notificationSettings(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("dealerNotifications");
		return model;
	}

	@RequestMapping("/auditorNotifications")
	public ModelAndView auditorNotification(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("auditorNotifications");
		return model;
	}

	@RequestMapping("/regionalManagerNotifications")
	public ModelAndView regionalManagerNotification(HttpServletRequest request, HttpServletResponse response,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("regionalManagerNotifications");
		return model;
	}

	@RequestMapping("/clientNotifications")
	public ModelAndView clientNotifications(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("clientNotifications");
		return model;
	}

	@RequestMapping("/EyiNotifications")
	public ModelAndView EyiNotifications(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("EyiNotifications");
		return model;
	}

	@RequestMapping("/getQuestionnaireOptionById")
	public void getQuestionnaireOptionById(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean)
			throws IOException {
		logger.info("IN getQuestionnaireOptionById");
		HttpSession session = request.getSession();
		String user_type;
		String user_id = user_type = (String) session.getAttribute("user_type");
		String audit_id = request.getParameter("audit_id");
		List<QuestionBean> outletList = hDao.getQuestionnaireOptionById(qBean, user_id, user_type, audit_id);
		Gson gson = new Gson();
		String outList = gson.toJson(outletList);
		try {
			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(outList);
			pw.flush();
			pw.close();

		} catch (Exception e) {
			logger.error(e);
		}
	}

	@RequestMapping(value = "/updateComments", method = RequestMethod.POST)
	public String updateComments(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean)
			throws IOException {
		HttpSession session = request.getSession();
		String user_type;
		user_type = (String) session.getAttribute("user_type");
		hDao.updateCommentsById(qBean, user_type);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/updateCommentsPMO", method = RequestMethod.POST)
	public String updateCommentsPMO(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean)
			throws IOException {
		HttpSession session = request.getSession();
		String user_type;
		user_type = (String) session.getAttribute("user_type");
		hDao.updateCommentsofPMO(qBean, user_type);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping("/state")
	public ModelAndView state(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("state");
		List<UserBean> stateList = hDao.getStateList(uBean);
		model.addObject("stateList", stateList);
		return model;
	}

	@RequestMapping(value = "/createState", method = RequestMethod.POST)
	public ModelAndView addState(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/state");
		hDao.addState(uBean);

		return model;
	}

	@RequestMapping("/editState/{sid}")
	public ModelAndView editstate(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("state_edit");
		hDao.getStateDetailsById(uBean, sid);
		request.setAttribute("sid", uBean.getState_id());
		request.setAttribute("state_name", uBean.getState_name());
		return model;
	}

	@RequestMapping(value = "/editstate/{sid}", method = RequestMethod.POST)
	public ModelAndView editstatePost(@PathVariable String sid, HttpServletRequest request,
			HttpServletResponse response, UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/state");
		hDao.updatStateById(uBean, sid);
		return model;
	}

	@RequestMapping("/deleteState/{sid}")
	public ModelAndView deleteState(@PathVariable String sid, HttpServletRequest request, HttpServletResponse response,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/state");
		hDao.deleteStateById(uBean, sid);
		return model;
	}

	@RequestMapping("/city")
	public ModelAndView city(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("city");
		List<UserBean> stateList = hDao.getStateList(uBean);
		model.addObject("stateList", stateList);
		List<UserBean> cityList = hDao.getCityList(uBean);
		model.addObject("cityList", cityList);

		return model;
	}

	@RequestMapping(value = "/createCity", method = RequestMethod.POST)
	public ModelAndView createCity(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/city");
		hDao.addCity(uBean);

		return model;
	}

	@RequestMapping("/editCity/{cid}")
	public ModelAndView editCity(@PathVariable String cid, HttpServletRequest request, HttpServletResponse response,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("city_edit");
		hDao.getCityDetailsById(uBean, cid);
		request.setAttribute("sid", uBean.getState_id());
		request.setAttribute("state_name", uBean.getState_name());
		request.setAttribute("city_name", uBean.getCity_name());
		request.setAttribute("cid", uBean.getCity_id());
		List<UserBean> stateList = hDao.getStateList(uBean);
		model.addObject("stateList", stateList);
		return model;
	}

	@RequestMapping(value = "/editCity/{cid}", method = RequestMethod.POST)
	public ModelAndView editCityPost(@PathVariable String cid, HttpServletRequest request, HttpServletResponse response,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/city");
		hDao.updateCityById(uBean, cid);
		return model;
	}

	@RequestMapping("/deleteCity/{cid}")
	public ModelAndView deleteCity(@PathVariable String cid, HttpServletRequest request, HttpServletResponse response,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("redirect:/city");
		hDao.deleteCityById(uBean, cid);
		return model;
	}

	// TO LOAD cities ON CHANGE OF state
	@RequestMapping("/getCities")
	public void viewState(HttpServletRequest request, HttpServletResponse response, UserBean uBean) {

		String state_id = request.getParameter("state_id");
		List<UserBean> cityList = hDao.getCities(uBean, state_id);
		Gson gson = new Gson();
		String cityData = gson.toJson(cityList);
		logger.info("cityData :" + cityData);
		try {

			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(cityData);
			pw.flush();
			pw.close();

		} catch (Exception e) {
			logger.error(e);
		}
	}

	@RequestMapping("/getMonthlyAudits")
	public void getMonthlyAudits(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean) {

		List<QuestionBean> monthlyAuditList = hDao.getMonthlyAudits(qBean);
		Gson gson = new Gson();
		String cityData = gson.toJson(monthlyAuditList);
		logger.info("cityData :" + cityData);
		try {

			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(cityData);
			pw.flush();
			pw.close();

		} catch (Exception e) {
			logger.error(e);
		}
	}

	@RequestMapping("/checkScoreExistByAuditId")
	public void checkScoreExistByAuditId(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean) {
		String audit_id = request.getParameter("audit_id");
		List<QuestionBean> checkScoreExistByAuditId = hDao.checkScoreExistByAuditId(qBean, audit_id);
		Gson gson = new Gson();
		String cityData = gson.toJson(checkScoreExistByAuditId);
		logger.info("cityData :" + cityData);
		try {

			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(cityData);
			pw.flush();
			pw.close();

		} catch (Exception e) {
			logger.error(e);
		}
	}

	@RequestMapping(value = "/addExceptionsTimings/{did}/{oid}/{asid}", method = RequestMethod.POST)
	public ModelAndView notifyExceptions(@PathVariable String did, @PathVariable String oid, @PathVariable String asid,
			HttpServletRequest request, HttpServletResponse response, UserBean uBean, QuestionBean qBean) {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/getPmoClosure/" + did + "/" + oid + "/" + asid + "");
		hDao.getDealerDeatailsById(uBean, did);
		String dealer_name = uBean.getDealership_name();
		/* SendEmailUsingGMailSMTP mail = new SendEmailUsingGMailSMTP(); */

		hDao.getOutletById(uBean, oid);
		request.setAttribute("outlet", uBean.getOutlets());
		request.setAttribute("outlet_size", uBean.getOutlet_size());
		request.setAttribute("outlet_type", uBean.getOutlet_type());
		request.setAttribute("brand", uBean.getBrand());

		hDao.getauditDetails(asid, qBean);
		String phase = qBean.getPhase();
		String year = qBean.getYear();

		/* hDao.updateNotificationSet(qBean,oid,did,phase,year); */

		List<QuestionBean> questionnaireList = hDao.getPmoClosureQuestionnaires(qBean, asid, uBean.getOutlet_type(),
				uBean.getOutlet_size());
		for (int i = 0; i < questionnaireList.size(); i++) {

			hDao.updateNotificationSet(qBean, asid, oid, did, phase, year, questionnaireList.get(i).getTime(),
					questionnaireList.get(i).getNumber(), questionnaireList.get(i).getStandard(), uBean.getOutlets(),
					questionnaireList.get(i).getObservation(), questionnaireList.get(i).getException_remarks(),
					dealer_name, questionnaireList.get(i).getAudit_id());

			/*
			 * msg = msg+ "<tr style='height:12.85pt'>"
			 * +"<td width='107' nowrap='' style='width:79.95pt;border:solid windowtext 1.0pt;border-top:none;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>"
			 * +questionnaireList.get(i).getNumber()+"<u></u><u></u></span></p>" +"</td>"
			 * +"<td width='177' nowrap='' style='width:132.8pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>"
			 * +questionnaireList.get(i).getStandard()+"<u></u><u></u></span></p>" +"</td>"
			 * +"<td width='84' nowrap='' style='width:63.35pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>"
			 * +uBean.getOutlets()+"<u></u><u></u></span></p>" +"</td>"
			 * +"<td width='434' nowrap='' style='width:325.5pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>"
			 * +questionnaireList.get(i).getObservation()+"<u></u><u></u></span></p>"
			 * +"</td>"
			 * +"<td width='526' nowrap='' style='width:394.75pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>"
			 * +questionnaireList.get(i).getException_remarks()+""
			 * +"<u></u><u></u></span></p>" +"</td>"
			 * +"<td width='434' nowrap='' style='width:325.5pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>"
			 * +questionnaireList.get(i).getTime()+"<u></u><u></u></span></p>" +"</td>"
			 * +"</tr>";
			 */
		}
		/*
		 * mail.sendExceptiondMail("amitoj.Kundhal@in.ey.com,manjuktrl@gmail.com",msg,
		 * did,dateFormat.format(date),dealer_name);
		 */

		return model;

	}

	@RequestMapping("/overview")
	public ModelAndView overviewget(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean,
			UserBean uBean) throws IOException {
		ModelAndView model = null;

		model = new ModelAndView("overview/overview");
		request.setAttribute("year", "2018");
		request.setAttribute("phase", "H2");
		request.setAttribute("brand", "ALL");

		List<UserBean> getDealerList = hDao.getDealerList(uBean);
		model.addObject("getDealerList", getDealerList);

		List<UserBean> getDealerListByRank = hDao.getDealerListByRank(uBean);
		model.addObject("getDealerListByRank", getDealerListByRank);

		return model;
	}

	@RequestMapping(value = "/overview", method = RequestMethod.POST)
	public ModelAndView overview(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		request.getSession();

		model = new ModelAndView("overview/overview");

		request.setAttribute("year", qBean.getYear());
		request.setAttribute("phase", qBean.getPhase());
		request.setAttribute("brand", qBean.getBrand());

		request.setAttribute("year", qBean.getYear());
		request.setAttribute("phase", qBean.getPhase());
		request.setAttribute("brand", qBean.getBrand());

		hDao.getTotalCompletedAudits(qBean);
		request.setAttribute("audit_count", qBean.getAudit_count());

		hDao.getTotalSubmitedAudits(qBean);
		request.setAttribute("audit_count_submit", qBean.getAudit_count_ans());

		hDao.getToBeDoneAudits(qBean);
		request.setAttribute("audits_tobe_done", qBean.getAudit_qn_count());

		hDao.getDealerNationalScore(qBean);
		request.setAttribute("dealer_avg", qBean.getDealer_avg());

		hDao.getDealerNationalSalesScore(qBean);
		request.setAttribute("dealer_sales_avg", qBean.getDealer_sales_avg());

		hDao.getDealerNationalServiceScore(qBean);
		request.setAttribute("dealer_service_avg", qBean.getDealer_service_avg());

		List<UserBean> getDealerList = hDao.getDealerList(uBean);
		model.addObject("getDealerList", getDealerList);

		List<UserBean> getDealerListByRank = hDao.getDealerListByRank(uBean);
		model.addObject("getDealerListByRank", getDealerListByRank);

		return model;
	}

	@RequestMapping("/getOutletsByDealer")
	public void getOutletsByDealer(HttpServletRequest request, HttpServletResponse response, UserBean uBean) {

		String did = request.getParameter("did");
		String type = request.getParameter("type");
		List<UserBean> cityList = hDao.getOutletBydealerShipId(uBean, did, type);
		Gson gson = new Gson();
		String cityData = gson.toJson(cityList);
		logger.info("cityData :" + cityData);
		try {

			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(cityData);
			pw.flush();
			pw.close();

		} catch (Exception e) {
			logger.error(e);
		}
	}

	@RequestMapping(value = "/questions/{a_id}")
	public ModelAndView user_type(@PathVariable String a_id, HttpServletRequest request, HttpServletResponse response,
			QuestionBean qBean, UserBean uBean) throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("questions");
		hDao.getOutletByScheduleId(qBean, a_id);
		String phase = qBean.getPhase();
		String year = qBean.getYear();
		String oid = qBean.getOutlet_id();

		request.setAttribute("year", year);
		request.setAttribute("phase", phase);

		hDao.getOutletById(uBean, oid);
		request.setAttribute("header_text", uBean.getDealership_name() + "-" + uBean.getOutlets() + "_"
				+ uBean.getBrand() + " RSA " + phase + " " + year + " Observations");
		List<QuestionBean> questionsList = hDao.getQuestionsList(qBean, a_id);
		List<List<QuestionBean>> ImageList = new ArrayList<List<QuestionBean>>();
		Iterator<QuestionBean> iterator = questionsList.iterator();
		while (iterator.hasNext()) {
			String question_id = iterator.next().getQuestionnaire_id();
			ImageList.add(hDao.getImages(a_id, question_id, qBean));
		}

		/*
		 * for (HomeBean homeBean : questionsList) { logger.info("sdjnjsd" +
		 * homeBean.getQuestionnaire_id());
		 * 
		 * }
		 */
		model.addObject("questionsList", questionsList);
		model.addObject("ImageList", ImageList);
		return model;
	}

	@RequestMapping(value = "/notifyExceptions")
	public void notifyExceptions(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean,
			UserBean uBean) throws IOException {

		List<QuestionBean> getDealers = hDao.getDealersToBeNotified(qBean, dateFormat.format(date));
		for (int l = 0; l < getDealers.size(); l++) {
			List<QuestionBean> getPendingSchedulers = hDao.getPendingSchedulers(qBean, dateFormat.format(date),
					getDealers.get(l).getDealer_id());
			String msg = "";
			String dealer_name = "";
			String period = "";
			String link = "";
			String phase = "";
			String year = "";
			for (int i = 0; i < getPendingSchedulers.size(); i++) {

				msg = msg + "<tr style='height:12.85pt'>"
						+ "<td width='107' nowrap='' style='width:79.95pt;border:solid windowtext 1.0pt;border-top:none;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>"
						+ getPendingSchedulers.get(i).getNumber() + "<u></u><u></u></span></p>" + "</td>"
						+ "<td width='177' nowrap='' style='width:132.8pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>"
						+ getPendingSchedulers.get(i).getStandard() + "<u></u><u></u></span></p>" + "</td>"
						+ "<td width='84' nowrap='' style='width:63.35pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>"
						+ getPendingSchedulers.get(i).getOutlets() + "<u></u><u></u></span></p>" + "</td>"
						+ "<td width='434' nowrap='' style='width:325.5pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>"
						+ getPendingSchedulers.get(i).getObservation() + "<u></u><u></u></span></p>" + "</td>"
						+ "<td width='526' nowrap='' style='width:394.75pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>"
						+ getPendingSchedulers.get(i).getException_remarks() + "" + "<u></u><u></u></span></p>"
						+ "</td>"
						+ "<td width='434' nowrap='' style='width:325.5pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>"
						+ getPendingSchedulers.get(i).getTime() + "<u></u><u></u></span></p>" + "</td>" + "</tr>";
				dealer_name = getPendingSchedulers.get(i).getDealer();
				period = getPendingSchedulers.get(i).getResult();
				link = "http://rsa.dqi.co.in/rsa/dealer_exceptions/" + getPendingSchedulers.get(i).getDealer_id() + "/"
						+ getPendingSchedulers.get(i).getOutlet_id() + "/"
						+ getPendingSchedulers.get(i).getAudit_schedule_id() + "";
				getPendingSchedulers.get(i).getEmail();
				phase = getPendingSchedulers.get(i).getPhase();
				year = getPendingSchedulers.get(i).getYear();
			}
			if (getPendingSchedulers.size() > 0) {
//				SendEmailUsingGMailSMTP mail = new SendEmailUsingGMailSMTP();
//				mail.sendExceptiondMail("manjuktrl@gmail.com", msg, dateFormat.format(date), dealer_name, period, link,
//						phase, year);
				logger.info("dealer" + dealer_name);
			} else {
				logger.info("LIST SIZE ZERO");
			}
		}

	}

	@RequestMapping(value = "/dealer_exceptions/{did}/{oid}/{asid}")
	public ModelAndView user_type(@PathVariable String did, @PathVariable String oid, @PathVariable String asid,
			HttpServletRequest request, HttpServletResponse response, QuestionBean qBean, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("dealer_exceptions");
		hDao.getOutletById(uBean, oid);
		hDao.getOutletByScheduleId(qBean, asid);
		String phase = qBean.getPhase();
		String year = qBean.getYear();
		hDao.getOutletById(uBean, oid);
		request.setAttribute("header_text", uBean.getDealership_name() + "-" + uBean.getOutlets() + "_"
				+ uBean.getBrand() + " RSA " + phase + " " + year + " Exceptions");
		/* request.setAttribute("header_text", "BMW-RSA 2018-H2" ); */
		request.setAttribute("outlet", uBean.getOutlets());
		request.setAttribute("outlet_size", uBean.getOutlet_size());
		request.setAttribute("outlet_type", uBean.getOutlet_type());
		request.setAttribute("brand", uBean.getBrand());

		List<QuestionBean> questionnaireList = hDao.getPmoClosureQuestionnaires(qBean, asid, uBean.getOutlet_type(),
				uBean.getOutlet_size());
		model.addObject("questionnaireList", questionnaireList);
		return model;
	}

	@RequestMapping(value = "/dealer_exception/{did}/{phase}/{year}")
	public ModelAndView dealer_exceptions_(@PathVariable String did, @PathVariable String phase,
			@PathVariable String year, HttpServletRequest request, HttpServletResponse response, QuestionBean qBean,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("dealer_exceptions");
		String decrypted_country_id = "";
		try {
			String encrypted_country_id = did;
			String decrypt_country_id = encrypted_country_id.replace("symbol", "/");
			decrypted_country_id = AESCrypt.decrypt(decrypt_country_id);
			logger.info("decrypted country id=" + decrypted_country_id);
			did = decrypted_country_id;
		} catch (Exception e) {
			logger.info("bug" + e.getMessage());
			decrypted_country_id = did;
		}

		try {
			List<QuestionBean> questionnaireList = hDao.getPmoClosureQuestionnairesByDID(qBean, did, phase, year);

			model.addObject("questionnaireList", questionnaireList);
			request.setAttribute("header_text",
					questionnaireList.get(0).getDealer() + " RSA " + phase + " " + year + " Exceptions");
			/* request.setAttribute("header_text", "BMW-RSA 2018-H2" ); */
			request.setAttribute("outlet", uBean.getOutlets());
			request.setAttribute("outlet_size", uBean.getOutlet_size());
			request.setAttribute("outlet_type", uBean.getOutlet_type());
			request.setAttribute("brand", uBean.getBrand());
		} catch (Exception e) {
			logger.error(e);
		}

		return model;
	}

	@RequestMapping(value = "/dealer_exception_/{did}/{phase}/{year}")
	public ModelAndView dealer_exception_(@PathVariable String did, @PathVariable String phase,
			@PathVariable String year, HttpServletRequest request, HttpServletResponse response, QuestionBean qBean,
			UserBean uBean) throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("dealer_exceptions1");
		String decrypted_country_id = "";
		try {
			String encrypted_country_id = did;
			String decrypt_country_id = encrypted_country_id.replace("symbol", "/");
			decrypted_country_id = AESCrypt.decrypt(decrypt_country_id);
			logger.info("decrypted country id=" + decrypted_country_id);
			did = decrypted_country_id;
		} catch (Exception e) {
			logger.info("bug" + e.getMessage());
			decrypted_country_id = did;
		}

		try {
			request.setAttribute("manju", did);
			List<QuestionBean> questionnaireList = hDao.getPmoClosureQuestionnairesByDID(qBean, did, phase, year);

			model.addObject("questionnaireList", questionnaireList);
			request.setAttribute("header_text",
					questionnaireList.get(0).getDealer() + " RSA " + phase + " " + year + " Exceptions");
			/* request.setAttribute("header_text", "BMW-RSA 2018-H2" ); */
			request.setAttribute("outlet", uBean.getOutlets());
			request.setAttribute("outlet_size", uBean.getOutlet_size());
			request.setAttribute("outlet_type", uBean.getOutlet_type());
			request.setAttribute("brand", uBean.getBrand());
		} catch (Exception e) {
			logger.error(e);
		}

		return model;
	}

	@RequestMapping(value = "/dealer_exceptions/{did}/{oid}/{asid}", method = RequestMethod.POST)
	public ModelAndView overview(@PathVariable String did, @PathVariable String oid, @PathVariable String asid,
			HttpServletRequest request, HttpServletResponse response, QuestionBean qBean, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/dealer_exceptions/" + did + "/" + oid + "/" + asid + "");

		return model;
	}

	@RequestMapping("/updateDealerComments")
	public void updateDealerComments(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean) {

		String comments = request.getParameter("comments");
		String aid = request.getParameter("id");
		hDao.updateDealerComments(qBean, comments, aid);
		/*
		 * Gson gson = new Gson(); String cityData = gson.toJson(cityList);
		 * logger.info("cityData :" + cityData); try {
		 * 
		 * response.setContentType("application/json"); PrintWriter pw =
		 * response.getWriter(); pw.print(cityData); pw.flush(); pw.close();
		 * 
		 * } catch (Exception e) { logger.error(e); }
		 */
	}

	@RequestMapping("/saveproduct")
	public void saveFile(HttpServletRequest servletRequest,
			@ModelAttribute MultipleFileUploadForm multipleFileUploadForm, BindingResult bindingResult, Model model,
			HttpServletRequest request) {
		logger.info("MANJU IN SAVE PRODUCT");

//		String aid = request.getParameter("aid");
//		logger.info(aid);
		MultipartFile files = multipleFileUploadForm.getMultipartFile();
		logger.info(multipleFileUploadForm.getMultipartFile());

		String file = files.getOriginalFilename();
		logger.info(file);
		HelperDao helperDao = new HelperDao();
		String renamefile = helperDao.renamePhoto(file);

		logger.info("renamefile SDF" + renamefile);
		String filePath = local_filepath + "merchant_images/";

		/* dDao.addFilesByReviewId(mBean, user_id, renamefile, review_id); */
		try {
			files.transferTo(new File(filePath + renamefile));
			logger.info("renamefile" + renamefile);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/saveproductdetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveproductdetails(Model model, @RequestBody QuestionBean qBean) throws IOException {
		logger.info("Enter" + qBean.getM());
		/* logger.info(qBean.getS()); */
		String[] profile_pic = qBean.getS().split(",");
		byte[] bytes = Base64.getDecoder().decode(profile_pic[1]);
		String photo = "1_image.jpg";
		HelperDao helperDao = new HelperDao();
		String file = "";
		String renamefile = helperDao.renamePhoto(photo);
		file = renamefile;
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(local_filepath + file)));
		stream.write(bytes);
		stream.flush();
		stream.close();
		hDao.updateDealerImages(qBean, qBean.getM(), file);

	}

	@RequestMapping("/publish_score/{did1}/{year}/{phase}")
	public ModelAndView publish_score(@PathVariable String did1, @PathVariable String year, @PathVariable String phase,
			HttpServletRequest request, HttpServletResponse response, UserBean uBean) throws Exception {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/viewDealerSubmissions/" + phase + "/" + year);
       String did=AESCrypt.decrypt(did1);
       System.out.println(did+":did");

		SendEmailUsingGMailSMTP mail = new SendEmailUsingGMailSMTP();

		String link = ""+dashboard_url+"getDealerSectionScore/" + did + "/" + year + "/" + phase;

		hDao.getDealerDeatailsById(uBean, did);
		String dealer_name = uBean.getDealership_name();
		String contact_person = uBean.getContact_person();
		String dealer_email = uBean.getEmail();
		hDao.getOutletsBydealerShipId(uBean, did);
		String outlets = uBean.getOutlets();

		String emails = "";
		hDao.getOutletsBydealerShipId(uBean, did);
		String outletids = uBean.getOutlet_id();
		logger.info("outletids" + outletids);

		String sql_filter = "";
		String email = "";
		String temp[] = outletids.split(",");
		for (int j = 0; j < temp.length; j++) {
			sql_filter = " FIND_IN_SET('" + temp[j] + "',outlets) ";
			hDao.getEmailsByAudit(sql_filter, uBean);
			email = uBean.getEmail();
		}
		email = email + "," + email;

		emails = dealer_email + "," + email;
		logger.info("before :" + emails);
		HashSet<String> test = new HashSet<String>(Arrays.asList(emails.split(",")));
		String test1 = test.toString();
		String test2 = test1.replaceAll("[\\[\\](){}]", "");
		logger.info("after :" + test2);
		try {
			hDao.checkAreaOfImpExists(uBean, did, year, phase);

			mail.publishReport(test2, dateFormat.format(date), dealer_name, outlets, link, phase, year, contact_person,did);

		} catch (Exception e) {
			logger.info("NO Exception link mail");

			mail.publishReport2(test2, dateFormat.format(date), dealer_name, outlets, link, phase, year,contact_person);

		}
		return model;
	}

	@RequestMapping("/getMapData")
	public void getMapData(HttpServletRequest request, HttpServletResponse response, UserBean uBean) {

		String current_year = (String) request.getSession().getAttribute("current_year");
		String current_phase = (String) request.getSession().getAttribute("current_phase");
		List<UserBean> mapData = hDao.getMapData(uBean, current_year, current_phase);
		Gson gson = new Gson();
		String cityData = gson.toJson(mapData);
		logger.info("cityData :" + cityData);
		try {

			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(cityData);
			pw.flush();
			pw.close();

		} catch (Exception e) {
			logger.error(e);
		}
	}

	@RequestMapping("/updatePmoExceptiontoYes")
	public void updatePmoExceptiontoYes(HttpServletRequest request, HttpServletResponse response, UserBean uBean) {
		String did = request.getParameter("did");
		String qid = request.getParameter("qid");
		String asid = request.getParameter("asid");
		String mapData = hDao.updatePmoExceptiontoYes(uBean, did, qid, asid);
		Gson gson = new Gson();
		String cityData = gson.toJson(mapData);
		logger.info("cityData :" + cityData);
		try {

			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(cityData);
			pw.flush();
			pw.close();

		} catch (Exception e) {
			logger.error(e);
		}
	}

	@RequestMapping("/check-email")
	public ModelAndView check_email(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("check_email");
		return model;
	}

	/*
	 * @RequestMapping(value="/check-email", method = RequestMethod.POST) public
	 * ModelAndView check_email_post(HttpServletRequest request, HttpServletResponse
	 * response, UserBean uBean) throws IOException { ModelAndView model = null;
	 * model = new ModelAndView("check_email"); MailCheck mc = new MailCheck();
	 * mc.checkEmail(uBean.getEmail()); boolean status =
	 * mc.call_this_to_validate(uBean.getEmail()); logger.info("STATUS :"+status);
	 * String str = String.valueOf(status); if(str.equals("true")) {
	 * request.setAttribute("status", "Email is valid."); } else {
	 * request.setAttribute("status", "Email is in-valid!"); } return model; }
	 */
	@SuppressWarnings("static-access")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping("/check_email")
	public void check_emaila(HttpServletRequest request, HttpServletResponse response, UserBean uBean) {
		String email = request.getParameter("email");
		logger.error(email + "EMAIL");
		MailCheck mc = new MailCheck();
		mc.checkEmail(email);
		boolean status = mc.call_this_to_validate(email);
		logger.info("STATUS :" + status);
		String str = String.valueOf(status);
		if (str.equals("true")) {
			request.setAttribute("status", "Email is valid.");
		} else {
			request.setAttribute("status", "Email is in-valid!");
		}
		Gson gson = new Gson();
		String cityData = gson.toJson(str);
		logger.info("cityData :" + cityData);
		try {

			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(cityData);
			pw.flush();
			pw.close();

		} catch (Exception e) {
			logger.error(e);
		}
	}

}
