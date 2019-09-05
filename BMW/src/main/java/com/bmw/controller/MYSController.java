package com.bmw.controller;

import java.awt.Menu;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.bmw.api.SendEmailUsingGMailSMTP;
import com.bmw.beans.MenuBean;
import com.bmw.beans.MultipleFileUploadForm;
import com.bmw.beans.QuestionBean;
import com.bmw.beans.QuestionareBean;
import com.bmw.beans.UserBean;
import com.bmw.dao.HelperDao;
import com.bmw.dao.HomeDao;
import com.bmw.dao.MYSDao;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.mail.iap.Response;

@Controller
public class MYSController {
	@Autowired
	HomeDao hDao;
	@Autowired
	MYSDao mDao;
	ResourceBundle resource = ResourceBundle.getBundle("resources/web");
	String local_filepath = resource.getString("local_filepath");
	
	private static final Logger logger = Logger.getLogger(MYSController.class);


	@RequestMapping("/scheduleMysteryShopper")
	public ModelAndView ScheduleMysteryShopper(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("shedule_mys_shopper");

		return model;
	}

	@RequestMapping(value = "/scheduleMysteryShopper", method = RequestMethod.POST)
	public ModelAndView scheduleMysteryShopper(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/viewMysteryShoppers");
		hDao.addMysteryShoppers(uBean);
		SendEmailUsingGMailSMTP mail = new SendEmailUsingGMailSMTP();
		mail.sendMail("manjuktrl@gmail.com",
				"Hello There are few exceptions raised to your outlet please check the link ", "");
		return model;
	}

	@RequestMapping("/viewMysteryShoppers")
	public ModelAndView viewMysteryShoppers(HttpServletRequest request, HttpServletResponse response, UserBean uBean)
			throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("view_shcedule_mys_shopper");
		List<UserBean> userList = hDao.getMysteryShoppersList(uBean);
		model.addObject("mysUserList", userList);
		return model;
	}

	@RequestMapping("/editMysteryShopper/{uid}")
	public ModelAndView editMysteryShopper(@PathVariable String uid, HttpServletRequest request,
			HttpServletResponse response, UserBean uBean, QuestionBean qBean) throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("edit_shedule_mys_shopper");
		hDao.getMysteryShoppersById(uBean, uid);
		request.setAttribute("uid", uid);
		request.setAttribute("name", uBean.getFirst_name());
		request.setAttribute("age", uBean.getAge());
		request.setAttribute("email", uBean.getEmail());
		request.setAttribute("contact_numebr", uBean.getMobile());
		request.setAttribute("gender", uBean.getGender());
		request.setAttribute("quarter", uBean.getQuarter());
		request.setAttribute("year", uBean.getYear());
		request.setAttribute("mode_of_contact", uBean.getMode_of_contact());
		request.setAttribute("visit_date", uBean.getVisit_date());
		request.setAttribute("brand", uBean.getBrand());
		request.setAttribute("type", uBean.getType());
		request.setAttribute("outlet", uBean.getOutlet_id());
		request.setAttribute("model_id", uBean.getModel_id());
		hDao.getOutletById(uBean, uBean.getOutlet_id());
		request.setAttribute("outlet_name", uBean.getOutlets());
		hDao.getBrandModelsById(uBean, uBean.getModel_id());
		request.setAttribute("model", uBean.getModel());
		return model;
	}

	@RequestMapping(value = "/updateMysteryShopper/{uid}", method = RequestMethod.POST)
	public ModelAndView updateMysteryShopper(@PathVariable String uid, HttpServletRequest request,
			HttpServletResponse response, UserBean uBean) throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/viewMysteryShoppers");
		hDao.updateMysteryShoppers(uBean, uid);
		return model;
	}

	@RequestMapping("/deleteMysteryShopper/{uid}")
	public ModelAndView deleteMysteryShopper(@PathVariable String uid, HttpServletRequest request,
			HttpServletResponse response, UserBean uBean) throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/viewMysteryShoppers");
		hDao.deleteMysteryShoppers(uBean, uid);
		return model;
	}

	@RequestMapping("/model")
	public ModelAndView model(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean) {
		ModelAndView model = null;
		model = new ModelAndView("model");
		List<QuestionBean> modelList = hDao.getModels(qBean);
		model.addObject("modelList", modelList);
		return model;
	}

	@RequestMapping(value = "/model", method = RequestMethod.POST)
	public ModelAndView modelPost(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean) {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/model");
		hDao.addModel(qBean);
		return model;
	}

	@RequestMapping("/model/{mid}")
	public ModelAndView model(@PathVariable String mid, HttpServletRequest request, HttpServletResponse response,
			QuestionBean qBean) {
		ModelAndView model = null;
		model = new ModelAndView("model_edit");
		hDao.getModelsById(qBean, mid);
		request.setAttribute("mid", mid);
		request.setAttribute("brand", qBean.getBrand());
		request.setAttribute("type", qBean.getType());
		request.setAttribute("model", qBean.getModel());
		return model;
	}

	@RequestMapping(value = "/update_model/{mid}", method = RequestMethod.POST)
	public ModelAndView update_model(@PathVariable String mid, HttpServletRequest request, HttpServletResponse response,
			QuestionBean qBean) {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/model");
		hDao.updateModel(qBean, mid);
		return model;
	}

	@RequestMapping("/delete_model/{mid}")
	public ModelAndView delete_model(@PathVariable String mid, HttpServletRequest request, HttpServletResponse response,
			QuestionBean qBean) {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/model");
		hDao.deleteModelById(qBean, mid);
		return model;
	}

	@RequestMapping("/MYS_createQuestionnaire")
	public ModelAndView MYS_createQuestionnaire(HttpServletRequest request, HttpServletResponse response,
			QuestionBean qBean) {
		ModelAndView model = null;
		model = new ModelAndView("mys_create_questions");
		List<QuestionBean> sectionList = mDao.getSections(qBean);
		model.addObject("sectionList", sectionList);
		return model;
	}

	@RequestMapping(value = "/MYS_createQuestionnaire", method = RequestMethod.POST)
	public ModelAndView createQuestionnaire(@ModelAttribute("subquestiondata") QuestionareBean subquestiondata,
			@ModelAttribute("answer") QuestionareBean answer,
			@ModelAttribute("superquestion") QuestionareBean superquestion, HttpServletRequest request,
			HttpServletResponse response, QuestionBean qBean) throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/home");
		logger.info(qBean.getQuestionType());
		logger.info(subquestiondata.getSubquestiondata());
		logger.info(subquestiondata.getFormuladata());
		if (qBean.getCommentMandatory() == null) {
			qBean.setCommentMandatory("No");
		}
		logger.info(qBean.getCommentMandatory());
		List<QuestionBean> data = answer.getAnswerdata();
		List<QuestionBean> data1 = subquestiondata.getFormuladata();
		logger.info(data1);
		logger.info(data1.iterator().next().getFormulaFinalResult());
		logger.info(data1.get(0).getSubQuestionId());
		logger.info(data1.iterator().next().getFormulaResult());

		mDao.addQuestionnaire(qBean, answer, superquestion, subquestiondata);
		return model;
	}

	@RequestMapping("/MYS_addSection")
	public ModelAndView addSection(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean) {
		ModelAndView model = null;
		model = new ModelAndView("mys_addSection");
		List<QuestionBean> sectionList = mDao.getSections(qBean);
		List<QuestionBean> subsectionList = mDao.getSubSections(qBean);
		model.addObject("sectionList", sectionList);
		model.addObject("subsectionList", subsectionList);
		return model;
	}

	@RequestMapping(value = "/MYS_addSection", method = RequestMethod.POST)
	public ModelAndView addSection1(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean)
			throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/addSection");
		mDao.addSection(qBean);
		return model;
	}

	@RequestMapping(value = "/MYS_updateSection", method = RequestMethod.POST)
	public ModelAndView updateSection(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean)
			throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/addSection");
		mDao.updateSection(qBean);
		return model;
	}

	@RequestMapping(value = "/MYS_deleteSection/{sectionId}")
	public ModelAndView deleteSection(@PathVariable String sectionId, HttpServletRequest request,
			HttpServletResponse response, QuestionBean qBean) throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/addSection");
		qBean.setSubSectionId(sectionId);
		mDao.deleteSection(qBean);
		return model;
	}

	@RequestMapping(value = "/MYS_deleteSubSection/{subSectionId}")
	public ModelAndView deleteSubSection(@PathVariable String subSectionId, HttpServletRequest request,
			HttpServletResponse response, QuestionBean qBean) throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/addSection");
		qBean.setSubSectionId(subSectionId);
		mDao.deleteSubSection(qBean);
		return model;
	}

	@RequestMapping("/MYS_getSectionById")
	public void getSectionById(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean) {
		logger.info("request.getParameter:" + request.getParameter("sectionId"));

		QuestionBean SectionList = mDao.getSectionById(request.getParameter("sectionId"));
		Gson gson = new Gson();
		String data = gson.toJson(SectionList);
		logger.info("data :" + data);
		try {
			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(data);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			logger.info(e);
		}
	}

	@RequestMapping(value = "/MYS_addSubSection", method = RequestMethod.POST)
	public ModelAndView addSubSection(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean)
			throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/addSection");
		mDao.addSubSection(qBean);
		return model;
	}

	@RequestMapping("/MYS_getSubSectionById")
	public void getSubSectionById(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean) {
		logger.info("request.getParameter:" + request.getParameter("subSectionId"));

		QuestionBean SectionList = mDao.getSubSectionById(request.getParameter("subSectionId"));
		Gson gson = new Gson();
		String data = gson.toJson(SectionList);
		logger.info("data :" + data);
		try {
			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(data);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			logger.info(e);
		}
	}

	@RequestMapping(value = "/MYS_updateSubSection", method = RequestMethod.POST)
	public ModelAndView updateSubSection(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean)
			throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("redirect:/addSection");
		mDao.updateSubSection(qBean);
		return model;
	}

	@RequestMapping("/getOutletsByBrand")
	public void getOutletsByBrand(HttpServletRequest request, HttpServletResponse response, UserBean uBean) {

		String brand = request.getParameter("brand");
		String type = request.getParameter("type");
		List<UserBean> outletList = hDao.getOutletListByBrandAndType(uBean, brand, type);
		Gson gson = new Gson();
		String cityData = gson.toJson(outletList);
		logger.info("cityData :" + cityData);
		try {

			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(cityData);
			pw.flush();
			pw.close();

		} catch (Exception e) {
			logger.info(e);
		}
	}

	@RequestMapping("/getModelsByBrand")
	public void getModelsByBrand(HttpServletRequest request, HttpServletResponse response, UserBean uBean) {

		String brand = request.getParameter("brand");
		List<UserBean> modelList = hDao.getModelsByBrand(uBean, brand);
		Gson gson = new Gson();
		String cityData = gson.toJson(modelList);
		logger.info("cityData :" + cityData);
		try {

			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(cityData);
			pw.flush();
			pw.close();

		} catch (Exception e) {
			logger.info(e);
		}
	}

	@RequestMapping("getSubSectionBySectionId")
	public void getSubSectionBySectionId(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean) {
		logger.info("request.getParameter:" + request.getParameter("sectionId"));

		List<QuestionBean> SectionList = mDao.getSubSectionBySectionId(request.getParameter("sectionId"));
		Gson gson = new Gson();
		String data = gson.toJson(SectionList);
		logger.info("data :" + data);
		try {
			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(data);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			logger.info(e);
		}
	}

	@RequestMapping("getStandardNumberBySubSectionId")
	public void getStandardNumberBySubSectionId(HttpServletRequest request, HttpServletResponse response,
			QuestionBean qBean) {
		logger.info("request.getParameter:" + request.getParameter("subSection"));

		List<QuestionBean> StandNumList = mDao.getStandardNumberBySubSectionId(request.getParameter("subSection"));
		Gson gson = new Gson();
		String data = gson.toJson(StandNumList);
		logger.info("data :" + data);
		try {
			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(data);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			logger.info(e);
		}
	}

	@RequestMapping("getQuestionByStandNumber")
	public void getQuestionByStandNumber(HttpServletRequest request, HttpServletResponse response, QuestionBean qBean) {
		logger.info("request.getParameter:" + request.getParameter("standardNumber"));

		QuestionBean StandNumList = mDao.getQuestionByStandNumber(request.getParameter("standardNumber"));
		Gson gson = new Gson();
		String data = gson.toJson(StandNumList);
		logger.info("data :" + data);
		try {
			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.print(data);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			logger.info(e);
		}
	}
}
