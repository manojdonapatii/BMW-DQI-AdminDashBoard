package com.bmw.api;
import java.net.*;

public class SmsAPI {

	public static String sendOTP(String phone,String otp) {
		String status="";
        try {
                String recipient = phone;
                String message = " Greetings from Lugma! Your OTP is : "+otp;
                String username = "svrmem";
                String password = "svrmem@123";
                String originator = "SVRMEM";

                String requestUrl  = "http://167.114.60.246/VidyaSMS/SendSMS.aspx?" +
                					 "user=" + URLEncoder.encode(username, "UTF-8") +
                					 "&pwd=" + URLEncoder.encode(password, "UTF-8") +
                					 "&SenderId=" + URLEncoder.encode(originator, "UTF-8") +
                					 "&MobileNo=" + URLEncoder.encode(recipient, "UTF-8") +
                					
                					 "&Message=" + URLEncoder.encode(message, "UTF-8");
                					 //"&originator=" + URLEncoder.encode(originator, "UTF-8") +
                					 //"&serviceprovider=GSMModem1" +
                					 //"&responseformat=html";
                System.out.println("URL :"+requestUrl);
                
               // http://167.114.60.246/VidyaSMS/SendSMS.aspx?User=svrmem&Pwd=svrmem@123&SenderId=SVRMEM&MobileNo=9986120820&Message=HELLO



                URL url = new URL(requestUrl);
                HttpURLConnection uc = (HttpURLConnection)url.openConnection();

                System.out.println(uc.getResponseMessage());
                status=uc.getResponseMessage();
                uc.disconnect();

        } catch(Exception ex) {
                System.out.println(ex.getMessage());

        }
		return status;
        
	}
	
	public static String sendAppLink(String mobile) {
		String status="";
        try {
                String recipient = mobile;
                String message = " Greetings from Lugma! Link for Lugma is : http://localhost:8080/Lugma_User_New/Lugma";
                String username = "svrmem";
                String password = "svrmem@123";
                String originator = "SVRMEM";

                String requestUrl  = "http://167.114.60.246/VidyaSMS/SendSMS.aspx?" +
                					 "user=" + URLEncoder.encode(username, "UTF-8") +
                					 "&pwd=" + URLEncoder.encode(password, "UTF-8") +
                					 "&SenderId=" + URLEncoder.encode(originator, "UTF-8") +
                					 "&MobileNo=" + URLEncoder.encode(recipient, "UTF-8") +
                					
                					 "&Message=" + URLEncoder.encode(message, "UTF-8");
                					 //"&originator=" + URLEncoder.encode(originator, "UTF-8") +
                					 //"&serviceprovider=GSMModem1" +
                					 //"&responseformat=html";
                System.out.println("URL :"+requestUrl);
                
               // http://167.114.60.246/VidyaSMS/SendSMS.aspx?User=svrmem&Pwd=svrmem@123&SenderId=SVRMEM&MobileNo=9986120820&Message=HELLO



                URL url = new URL(requestUrl);
                HttpURLConnection uc = (HttpURLConnection)url.openConnection();

                System.out.println(uc.getResponseMessage());
                status=uc.getResponseMessage();
                uc.disconnect();

        } catch(Exception ex) {
                System.out.println(ex.getMessage());

        }
		return status;
        
	}
	public static String sendReview_mobile_notification(String mobile,String msg,String u_id) {
	    String status="";
	        try {
	                String recipient = mobile;
	                String message = " Greetings from Lugma! you have Received New Notification From "+u_id+":"+msg+"";
	                String username = "svrmem";
	                String password = "svrmem@123";
	                String originator = "SVRMEM";

	                String requestUrl  = "http://167.114.60.246/VidyaSMS/SendSMS.aspx?" +
	                           "user=" + URLEncoder.encode(username, "UTF-8") +
	                           "&pwd=" + URLEncoder.encode(password, "UTF-8") +
	                           "&SenderId=" + URLEncoder.encode(originator, "UTF-8") +
	                           "&MobileNo=" + URLEncoder.encode(recipient, "UTF-8") +
	                          
	                           "&Message=" + URLEncoder.encode(message, "UTF-8");
	                           //"&originator=" + URLEncoder.encode(originator, "UTF-8") +
	                           //"&serviceprovider=GSMModem1" +
	                           //"&responseformat=html";
	                System.out.println("URL :"+requestUrl);
	                
	               // http://167.114.60.246/VidyaSMS/SendSMS.aspx?User=svrmem&Pwd=svrmem@123&SenderId=SVRMEM&MobileNo=9986120820&Message=HELLO



	                URL url = new URL(requestUrl);
	                HttpURLConnection uc = (HttpURLConnection)url.openConnection();

	                System.out.println(uc.getResponseMessage());
	                status=uc.getResponseMessage();
	                uc.disconnect();

	        } catch(Exception ex) {
	                System.out.println(ex.getMessage());

	        }
	    return status;
	        
	  }


}