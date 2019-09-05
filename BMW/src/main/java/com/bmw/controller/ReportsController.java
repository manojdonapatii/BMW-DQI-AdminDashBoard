package com.bmw.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.bmw.api.AESCrypt;
import com.bmw.beans.QuestionBean;
import com.bmw.beans.UserBean;
import com.bmw.dao.HomeDao;
import com.bmw.dao.ReportsDao;
import com.google.gson.Gson;

@Controller
public class ReportsController {
	@Autowired
	HomeDao hDao;
	@Autowired
	ReportsDao rDao;

	ResourceBundle resource = ResourceBundle.getBundle("resources/web");
	String local_filepath = resource.getString("local_filepath");
	
	private static final Logger logger = Logger.getLogger(ReportsController.class);


	/*@RequestMapping("/sales-report/{did}")
	public ModelAndView viewCompletedAudit(@PathVariable String did, HttpServletRequest request,
			HttpServletResponse response, QuestionBean qBean, UserBean uBean) throws IOException {
		ModelAndView model = null;
		hDao.getDealerDeatailsById(uBean, did);
		request.setAttribute("dealership_name", uBean.getDealership_name());
		model = new ModelAndView("reports/sales-report");
		//List<UserBean> OutletList = hDao.getSalesOutletBydealerShipId(uBean, did);
		//model.addObject("OutletList", OutletList);
		try {
			List<QuestionBean> TotalAvgreports = rDao.getreportsByDid(qBean, did, "Sales");
			model.addObject("TotalAvgreports", TotalAvgreports);
			rDao.getOverallEssentialScoreByDealerId(qBean, did, "Sales");
			rDao.getOverallContractualScoreByDealerId(qBean, did, "Sales");
			request.setAttribute("overall_ess_score", qBean.getTotal_ans_count());
			request.setAttribute("overall_cont_score", qBean.getTotal_qns_count());
		} catch (Exception e) {
			logger.info(e + "No results");
			request.setAttribute("overall_ess_score", qBean.getTotal_ans_count());
			request.setAttribute("overall_cont_score", qBean.getTotal_qns_count());
		}
		return model;
	}*/

	/*@RequestMapping("/after-sales-report/{did}")
	public ModelAndView aftersalesreport(@PathVariable String did, HttpServletRequest request,
			HttpServletResponse response, QuestionBean qBean, UserBean uBean) throws IOException {
		ModelAndView model = null;
		hDao.getDealerDeatailsById(uBean, did);
		request.setAttribute("dealership_name", uBean.getDealership_name());
		model = new ModelAndView("reports/service-report");
	//	List<UserBean> OutletList = hDao.getServiceOutletBydealerShipId(uBean, did);
	//	model.addObject("OutletList", OutletList);
		try {
			List<QuestionBean> TotalAvgreports = rDao.getreportsByDid(qBean, did, "Service");
			model.addObject("TotalAvgreports", TotalAvgreports);
			rDao.getOverallEssentialScoreByDealerId(qBean, did, "Service");
			rDao.getOverallContractualScoreByDealerId(qBean, did, "Service");
			request.setAttribute("overall_ess_score", qBean.getTotal_ans_count());
			request.setAttribute("overall_cont_score", qBean.getTotal_qns_count());
		} catch (Exception e) {
			logger.info(e + "No results");
			request.setAttribute("overall_ess_score", "0");
			request.setAttribute("overall_cont_score", "0");
		}
		return model;
	}*/

	@RequestMapping("/overall-report/{did}")
	public ModelAndView overallreports(@PathVariable String did, HttpServletRequest request,
			HttpServletResponse response, QuestionBean qBean, UserBean uBean) throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("reports/overall-report");
		hDao.getDealerDeatailsById(uBean, did);
		request.setAttribute("dealership_name", uBean.getDealership_name());

		return model;
	}

	@RequestMapping("/getDealerSectionScore/{did}")
	public ModelAndView getDealerSectionScore(@PathVariable String did, HttpServletRequest request,
			HttpServletResponse response, QuestionBean qBean, UserBean uBean) throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("reports/overall-report");
		hDao.getDealerDeatailsById(uBean, did);
		request.setAttribute("dealership_name", uBean.getDealership_name());

		return model;
	}

	@RequestMapping(value = "/getDealerSectionScore/{did}/{year}/{phase}")
	public ModelAndView updateComments(@PathVariable String did, @PathVariable String year, @PathVariable String phase,
			HttpServletRequest request, HttpServletResponse response, QuestionBean qBean,UserBean uBean) throws IOException {
		ModelAndView model = null;
		model = new ModelAndView("reports/graph");
		hDao.RunSqlGroupByException(uBean);
		String did1 = did;
		String decrypted_country_id="";
		try{
			
			int x = Integer.parseInt(did);
			if(x == (int)x)
			{
				System.out.println("INTEGER");
				decrypted_country_id=did;
			}
			
		}
		catch (Exception e) {
			System.out.println("Not an Integer");
			
		    try {
		        String encrypted_country_id = did;
		        String decrypt_country_id = encrypted_country_id.replace("symbol", "/");
		        decrypted_country_id = AESCrypt.decrypt(decrypt_country_id);    
		                did = decrypted_country_id;
		                System.out.println("did :"+did);
		                if(decrypted_country_id.isEmpty() || decrypted_country_id=="" || decrypted_country_id==null )
		                {
		                	System.out.println("IN IF");
		                	 did = did1;
		                	 decrypted_country_id=did1;
		                }
		        } catch(Exception e1) { logger.info("bug"+e.getMessage());
		        decrypted_country_id=did;
		        System.out.println(e1);
		        }
		}
		
		
		
	    
	    System.out.println("MANJU :"+did);
		
	    
		request.setAttribute("decrypted_country_id", decrypted_country_id);
		request.setAttribute("year", year);
		request.setAttribute("phase", phase);
		hDao.getDealerDeatailsById(uBean, did);
		request.setAttribute("dealership_name", uBean.getDealership_name());
		
		logger.info("MP :"+phase);
		String ph  =  phase;
		
		String temp[] = year.split("0");
		String yr = temp[1];
		request.setAttribute("yr", yr);
		
		/*TOTAL DEALER COUNT BY BRAND YEAR ph */
		rDao.getDealerCountBySales(qBean,year,ph,"BMW");
		request.setAttribute("dealer_sales_count_bmw", qBean.getDealer_sales_count());
		
		rDao.getDealerCountByService(qBean,year,ph,"BMW");
		request.setAttribute("dealer_service_count_bmw", qBean.getDealer_service_count());
		
		rDao.getDealerCountBySales(qBean,year,ph,"MINI");
		request.setAttribute("dealer_sales_count_mini", qBean.getDealer_sales_count());
		
		rDao.getDealerCountByService(qBean,year,ph,"MINI");
		request.setAttribute("dealer_service_count_mini", qBean.getDealer_service_count());
		/*TOTAL DEALER COUNT BY BRAND YEAR ph */
		
		
		/*TOTAL DEALER RANK BY BRAND YEAR ph */
		try{
		rDao.getRankByDealerIdSales(qBean,did,year,ph,"BMW");
		request.setAttribute("sales_rank_bmw", qBean.getSales_rank());
		}catch (Exception e) {
			request.setAttribute("sales_rank_bmw", "-");
		}
		try{
		rDao.getRankByDealerIdService(qBean,did,year,ph,"BMW");
		request.setAttribute("service_rank_bmw", qBean.getService_rank());
		}catch (Exception e) {
			request.setAttribute("service_rank_bmw", "-");
		}
		try{
		rDao.getRankByDealerIdSales(qBean,did,year,ph,"MINI");
		request.setAttribute("sales_rank_mini", qBean.getSales_rank());
		}catch (Exception e) {
			request.setAttribute("sales_rank_mini", "-");
		}
		try{
		rDao.getRankByDealerIdService(qBean,did,year,ph,"MINI");
		request.setAttribute("service_rank_mini", qBean.getService_rank());
		}catch (Exception e) {
			request.setAttribute("service_rank_mini", "-");
		}
		/*TOTAL DEALER RANK BY BRAND YEAR ph */
		
		
		
		/*Outlet List By Dealer,ph,Year,Type*/
		List<UserBean> BMWSalesOutletList = hDao.getSalesOutletBydealerShipId(uBean, did,ph,year,"BMW");
	    model.addObject("BMWSalesOutletList", BMWSalesOutletList);
	    
	    List<UserBean> BMWServiceOutletList = hDao.getServiceOutletBydealerShipId(uBean, did,ph,year,"BMW");
		model.addObject("BMWServiceOutletList", BMWServiceOutletList);
		
		List<UserBean> MINISalesOutletList = hDao.getSalesOutletBydealerShipId(uBean, did,ph,year,"MINI");
	    model.addObject("MINISalesOutletList", MINISalesOutletList);
	    
	    List<UserBean> MINIServiceOutletList = hDao.getServiceOutletBydealerShipId(uBean, did,ph,year,"MINI");
		model.addObject("MINIServiceOutletList", MINIServiceOutletList);
		/*Outlet List By Dealer,ph,Year,Type*/
		
		
		
		/* PENALIZED LIST BY BRAND YEAR TYPE AND DEALER ID */
	    List<QuestionBean> BMWPenalizedList = hDao.getPenalizedStandard(uBean, did,"Sales",ph,year,"BMW");
	    model.addObject("PenalizedList", BMWPenalizedList);
	    
	    List<QuestionBean> BMWPenalizedListService = hDao.getPenalizedStandard(uBean, did,"Service",ph,year,"BMW");
	    model.addObject("PenalizedListService", BMWPenalizedListService);
	    try{
	    List<QuestionBean> MINIPenalizedList = hDao.getPenalizedStandard(uBean, did,"Sales",ph,year,"MINI");
	    model.addObject("MINIPenalizedList", MINIPenalizedList);
	    }catch (Exception e) {
			logger.info(e);
		}
	    try{
	    List<QuestionBean> MINIPenalizedListService = hDao.getPenalizedStandard(uBean, did,"Service",ph,year,"MINI");
	    model.addObject("MINIPenalizedListService", MINIPenalizedListService);
	    }catch (Exception e) {
			logger.info(e);
		}
	    /* PENALIZED LIST BY BRAND YEAR TYPE AND DEALER ID */
	    
	    
	    /* ANNEXURE LIST BY BRAND YEAR TYPE AND DEALER ID */
	    
	    List<QuestionBean> BMWAnnexurePenalizedList = hDao.getBMWAnnexurePenalizedList(uBean, did,"Sales",ph,year,"BMW");
	    model.addObject("BMWAnnexurePenalizedList", BMWAnnexurePenalizedList);
	    
	    List<QuestionBean> BMWAnnexurePenalizedListSales = hDao.getBMWAnnexurePenalizedList(uBean, did,"Service",ph,year,"BMW");
	    model.addObject("BMWAnnexurePenalizedListSales", BMWAnnexurePenalizedListSales);
	    
	    List<QuestionBean> MINIAnnexurePenalizedList = hDao.getBMWAnnexurePenalizedList(uBean, did,"Sales",ph,year,"MINI");
	    model.addObject("MINIAnnexurePenalizedList", MINIAnnexurePenalizedList);
	    
	    /* ANNEXURE LIST BY BRAND YEAR TYPE AND DEALER ID */
	    
	    
	    /* AREA OF IMP. LIST BY BRAND YEAR TYPE AND DEALER ID */
	    
	    
	    List<QuestionBean> BMWareaImpList = hDao.getAreaOfImprovementsStandard(uBean, did,"Sales",ph,year,"BMW");
	    model.addObject("areaImpList", BMWareaImpList);
	    
	    List<QuestionBean> BMWareaImpListService = hDao.getAreaOfImprovementsStandard(uBean, did,"Service",ph,year,"BMW");
	    model.addObject("areaImpListService", BMWareaImpListService);
	    
	    List<QuestionBean> MINIareaImpList = hDao.getAreaOfImprovementsStandard(uBean, did,"Sales",ph,year,"MINI");
	    model.addObject("MINIareaImpList", MINIareaImpList);
	    
	    List<QuestionBean> MINIareaImpListService = hDao.getAreaOfImprovementsStandard(uBean, did,"Service",ph,year,"MINI");
	    model.addObject("MINIareaImpListService", MINIareaImpListService);
	    /* AREA OF IMP. LIST BY BRAND YEAR TYPE AND DEALER ID */
	    
	    
	    List<QuestionBean> PenalizedListAnnexure = hDao.getPenalizedStandardAnnexure(uBean, did,"Sales",ph,year);
	    model.addObject("PenalizedListAnnexure", PenalizedListAnnexure);
	    
	    List<QuestionBean> PenalizedListAnnexureService = hDao.getPenalizedStandardAnnexure(uBean, did,"Service",ph,year);
	    model.addObject("PenalizedListAnnexureService", PenalizedListAnnexureService);
	    
	    
	    try{
	    	rDao.checkMIniSalesExist(did,qBean);
	    	request.setAttribute("exist", "1");
	    }catch (Exception e) {
			request.setAttribute("exist", "0");
		}
	    
	    /*RANK SCORE CALCULATION BY BRAND YEAR ph*/
	    try {
			List<QuestionBean> TotalAvgreportsBMW = rDao.getreportsByDid(qBean, did, "Sales",ph,year,"BMW");
			model.addObject("TotalAvgreports", TotalAvgreportsBMW);
			rDao.getOverallEssentialScoreByDealerId(qBean, did, "Sales",ph,year,"BMW");
			rDao.getOverallContractualScoreByDealerId(qBean, did, "Sales",ph,year,"BMW");
			request.setAttribute("overall_ess_score_bmw", qBean.getTotal_ans_count());
			request.setAttribute("overall_cont_score_bmw", qBean.getTotal_qns_count());
		} catch (Exception e) {
			logger.info(e + "No results");
			request.setAttribute("overall_ess_score_bmw", qBean.getTotal_ans_count());
			request.setAttribute("overall_cont_score_bmw", qBean.getTotal_qns_count());
		}
	    try {
			List<QuestionBean> TotalAvgreportsServiceBMW = rDao.getreportsByDid(qBean, did, "Service",ph,year,"BMW");
			model.addObject("TotalAvgreportsService", TotalAvgreportsServiceBMW);
			rDao.getOverallEssentialScoreByDealerId(qBean, did, "Service",ph,year,"BMW");
			rDao.getOverallContractualScoreByDealerId(qBean, did, "Service",ph,year,"BMW");
			request.setAttribute("overall_ess_score_service_bmw", qBean.getTotal_ans_count());
			request.setAttribute("overall_cont_score_service_bmw", qBean.getTotal_qns_count());
		} catch (Exception e) {
			logger.info(e + "No results");
			request.setAttribute("overall_ess_score_service_bmw", "0");
			request.setAttribute("overall_cont_score_service_bmw", "0");
		}
	    
	    try {
			List<QuestionBean> TotalAvgreportsMINI = rDao.getreportsByDid(qBean, did, "Sales",ph,year,"MINI");
			model.addObject("TotalAvgreportsMINI", TotalAvgreportsMINI);
			rDao.getOverallEssentialScoreByDealerId(qBean, did, "Sales",ph,year,"MINI");
			rDao.getOverallContractualScoreByDealerId(qBean, did, "Sales",ph,year,"MINI");
			request.setAttribute("overall_ess_score_mini", qBean.getTotal_ans_count());
			request.setAttribute("overall_cont_score_mini", qBean.getTotal_qns_count());
		} catch (Exception e) {
			logger.info(e + "No results");
			request.setAttribute("overall_ess_score_mini", qBean.getTotal_ans_count());
			request.setAttribute("overall_cont_score_mini", qBean.getTotal_qns_count());
		}
	    try {
			List<QuestionBean> TotalAvgreportsService = rDao.getreportsByDid(qBean, did, "Service",ph,year,"MINI");
			model.addObject("TotalAvgreportsServiceMINI", TotalAvgreportsService);
			rDao.getOverallEssentialScoreByDealerId(qBean, did, "Service",ph,year,"MINI");
			rDao.getOverallContractualScoreByDealerId(qBean, did, "Service",ph,year,"MINI");
			request.setAttribute("overall_ess_score_service_mini", qBean.getTotal_ans_count());
			request.setAttribute("overall_cont_score_service_mini", qBean.getTotal_qns_count());
		} catch (Exception e) {
			logger.info(e + "No results");
			request.setAttribute("overall_ess_score_service_mini", "0");
			request.setAttribute("overall_cont_score_service_mini", "0");
		}
	    /*RANK SCORE CALCULATION BY BRAND YEAR PHASE*/
	    
		/*try {
			hDao.checkDealerSectionScoreExist(qBean, did, year, phase);
			hDao.deleteDealerScore(qBean, did, year, phase);
		} catch (Exception e) {

			logger.info(e);
		}
		rDao.getDealerSalesSectionScore(qBean, did, year, phase,"BMW");
		rDao.getDealerServiceSectionScore(qBean, did, year, phase,"BMW");
		
		rDao.getDealerSalesSectionScore(qBean, did, year, phase,"MINI");
		rDao.getDealerServiceSectionScore(qBean, did, year, phase,"MINI");
		
		try {
			hDao.checkDealerFinalScoreExist(qBean, did, year, phase);
			hDao.deleteDeleteDealerFinalScore(qBean, did, year, phase);
		} catch (Exception e) {

			logger.info(e);
		}
		 rDao.getDealerSalesFinalScore(qBean, did, year, phase,"BMW");
		 rDao.getDealerServiceFinalScore(qBean, did, year, phase,"BMW");
		 rDao.getDealerSalesFinalScore(qBean, did, year, phase,"MINI");
		 rDao.getDealerServiceFinalScore(qBean, did, year, phase,"MINI");*/
		
		return model;
	}
	
	

	@RequestMapping("/getReportsOfDealers")
	public void viewState(HttpServletRequest request, HttpServletResponse response, UserBean uBean,
			QuestionBean qBean) {

		String did = request.getParameter("did");
		String year = request.getParameter("year");
		String phase = request.getParameter("phase");
		String brand = request.getParameter("brand");
		List<QuestionBean> sectionScore = rDao.getReportsOfDealers(qBean, did, year, phase,brand);
		Gson gson = new Gson();
		String cityData = gson.toJson(sectionScore);
		logger.info("sectionScore :" + cityData);
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

	@RequestMapping("/getReportsOfDealersByService")
	public void getReportsOfDealersByService(HttpServletRequest request, HttpServletResponse response, UserBean uBean,
			QuestionBean qBean) {

		String did = request.getParameter("did");
		String year = request.getParameter("year");
		String phase = request.getParameter("phase");
		String brand = request.getParameter("brand");
		List<QuestionBean> sectionScore = rDao.getReportsOfDealersByService(qBean, did, year, phase,brand);
		Gson gson = new Gson();
		String cityData = gson.toJson(sectionScore);
		logger.info("sectionScore :" + cityData);
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
	@RequestMapping("/getContractualBySales")
	  public void getContractualBySales(HttpServletRequest request, HttpServletResponse response, UserBean uBean,
	      QuestionBean qBean) {

	    String did = request.getParameter("did");
	    String year = request.getParameter("year");
	    String phase = request.getParameter("phase");
	    String brand = request.getParameter("brand");
	    List<QuestionBean> sectionScore = rDao.getContractualBySales(qBean, did, year, phase,brand);
	    logger.info(sectionScore.get(0).getAvg());
	    logger.info(sectionScore.get(0).getMin());
	    logger.info(sectionScore.get(0).getMax());
	    Gson gson = new Gson();
	    String cityData = gson.toJson(sectionScore);
	    logger.info("sectionScore :" + cityData);
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
	  
	  @RequestMapping("/getContractualByServices")
	  public void getContractualByServices(HttpServletRequest request, HttpServletResponse response, UserBean uBean,
	      QuestionBean qBean) {

	    String did = request.getParameter("did");
	    String year = request.getParameter("year");
	    String phase = request.getParameter("phase");
	    String brand = request.getParameter("brand");
	    List<QuestionBean> sectionScore = rDao.getContractualByServices(qBean, did, year, phase,brand);
	    logger.info(sectionScore.get(0).getAvg());
	    logger.info(sectionScore.get(0).getMin());
	    logger.info(sectionScore.get(0).getMax());
	    Gson gson = new Gson();
	    String cityData = gson.toJson(sectionScore);
	    logger.info("sectionScore :" + cityData);
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

}
