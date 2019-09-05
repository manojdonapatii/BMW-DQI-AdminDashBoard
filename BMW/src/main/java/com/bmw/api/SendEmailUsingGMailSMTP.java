package com.bmw.api;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmailUsingGMailSMTP {
  
	public void sendReview_email_notification(String email,String message ,String user_Name) {
		System.out.println("In Email");
		
      // Recipient's email ID needs to be mentioned.
      String to = email;//"manjuktrl@gmail.com";//change accordingly
      
      String emailBody= user_Name+ "commented on your review:" +message;
      // Sender's email ID needs to be mentioned
      String from = "retailstandardsaudit@dqi.co.in";//change accordingly
      final String username = "retailstandardsaudit@dqi.co.in";//change accordingly
      final String password = "Codebele1!";//change accordingly

    
      String result = null;
      
      
      try {
           
          Properties props = System.getProperties();
          props.setProperty("mail.transport.protocol", "smtp");
          props.setProperty("mail.host", "smtp.zoho.com");
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.port", "465");
          props.put("mail.debug", "true");
          props.put("mail.smtp.socketFactory.port", "465");
          props.put("mail.smtp.socketFactory.class",
                  "javax.net.ssl.SSLSocketFactory");
          props.put("mail.smtp.socketFactory.fallback", "false");

          Session emailSession = Session.getInstance(props,
                  new javax.mail.Authenticator() {
                      protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(username,password);
                  }
          });

          emailSession.setDebug(true);
          Message msg = new MimeMessage(emailSession);

      	msg.setFrom(new InternetAddress(username));
      	InternetAddress[] toAddresses = { new InternetAddress(email) };
      	msg.setRecipients(Message.RecipientType.TO, toAddresses);
      	msg.setSubject("Dealer Standards Audit - DQI");
      	//msg.setSentDate(new Date());

      	// creates message part
      	MimeBodyPart messageBodyPart = new MimeBodyPart();
      	messageBodyPart.setContent(emailBody, "text/html");

      	// creates multi-part
      	Multipart multipart = new MimeMultipart();
      	multipart.addBodyPart(messageBodyPart);

      	// adds attachments


      	// sets the multi-part as e-mail's content
      	msg.setContent(multipart);

      	// sends the e-mail
      	Transport.send(msg);

      	System.out.println("Successfully sent email");
          //result = "Successfully sent email";

         } catch (MessagingException e) {
      	   System.out.println("Unable to send email");
      }

      
   }

	/*public void sendLike_email_notification(String email, String user_Name) {
		System.out.println("In Email");
		
	      // Recipient's email ID needs to be mentioned.
	      String to = email;//"manjuktrl@gmail.com";//change accordingly
	      
	      String emailBody= user_Name+ "Liked your Comment";
	      // Sender's email ID needs to be mentioned
	      String from = "manojreddy.mkr@gmail.com";//change accordingly
	      final String username = "manojreddy.mkr@gmail.com";//change accordingly
	      final String password = "manoj.mkr";//change accordingly

	    
	      String result = null;
	      
	      
	      try {
	           
	          Properties props = System.getProperties();
	          props.setProperty("mail.transport.protocol", "smtp");
	          props.setProperty("mail.host", "smtp.gmail.com");
	          props.put("mail.smtp.auth", "true");
	          props.put("mail.smtp.port", "465");
	          props.put("mail.debug", "true");
	          props.put("mail.smtp.socketFactory.port", "465");
	          props.put("mail.smtp.socketFactory.class",
	                  "javax.net.ssl.SSLSocketFactory");
	          props.put("mail.smtp.socketFactory.fallback", "false");

	          Session emailSession = Session.getInstance(props,
	                  new javax.mail.Authenticator() {
	                      protected PasswordAuthentication getPasswordAuthentication() {
	                      return new PasswordAuthentication(username,password);
	                  }
	          });

	          emailSession.setDebug(true);
	          Message msg = new MimeMessage(emailSession);

	      	msg.setFrom(new InternetAddress(username));
	      	InternetAddress[] toAddresses = { new InternetAddress(email) };
	      	msg.setRecipients(Message.RecipientType.TO, toAddresses);
	      	msg.setSubject("Lugma Notification ");
	      	//msg.setSentDate(new Date());

	      	// creates message part
	      	MimeBodyPart messageBodyPart = new MimeBodyPart();
	      	messageBodyPart.setContent(emailBody, "text/html");

	      	// creates multi-part
	      	Multipart multipart = new MimeMultipart();
	      	multipart.addBodyPart(messageBodyPart);

	      	// adds attachments


	      	// sets the multi-part as e-mail's content
	      	msg.setContent(multipart);

	      	// sends the e-mail
	      	Transport.send(msg);

	      	System.out.println("Successfully sent email");
	          //result = "Successfully sent email";

	         } catch (MessagingException e) {
	      	   System.out.println("Unable to send email");
	      }

	      
	   }*/
	public void sendFollow_email_notification(String email,String user_Name) {
		System.out.println("In Email");
		
      // Recipient's email ID needs to be mentioned.
      String to = email;//"manjuktrl@gmail.com";//change accordingly
      
      String emailBody= user_Name+ " Wants to Follow You";
      // Sender's email ID needs to be mentioned
      String from = "retailstandardsaudit@dqi.co.in";//change accordingly
      final String username = "retailstandardsaudit@dqi.co.in";//change accordingly
      final String password = "Codebele1!";//change accordingly

    
      String result = null;
      
      
      try {
           
          Properties props = System.getProperties();
          props.setProperty("mail.transport.protocol", "smtp");
          props.setProperty("mail.host", "smtp.zoho.com");
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.port", "465");
          props.put("mail.debug", "true");
          props.put("mail.smtp.socketFactory.port", "465");
          props.put("mail.smtp.socketFactory.class",
                  "javax.net.ssl.SSLSocketFactory");
          props.put("mail.smtp.socketFactory.fallback", "false");

          Session emailSession = Session.getInstance(props,
                  new javax.mail.Authenticator() {
                      protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(username,password);
                  }
          });

          emailSession.setDebug(true);
          Message msg = new MimeMessage(emailSession);

      	msg.setFrom(new InternetAddress(username));
      	InternetAddress[] toAddresses = { new InternetAddress(email) };
      	msg.setRecipients(Message.RecipientType.TO, toAddresses);
      	msg.setSubject("Dealer Standards Audit - DQI");
      	//msg.setSentDate(new Date());

      	// creates message part
      	MimeBodyPart messageBodyPart = new MimeBodyPart();
      	messageBodyPart.setContent(emailBody, "text/html");

      	// creates multi-part
      	Multipart multipart = new MimeMultipart();
      	multipart.addBodyPart(messageBodyPart);

      	// adds attachments


      	// sets the multi-part as e-mail's content
      	msg.setContent(multipart);

      	// sends the e-mail
      	Transport.send(msg);

      	System.out.println("Successfully sent email");
          //result = "Successfully sent email";

         } catch (MessagingException e) {
      	   System.out.println("Unable to send email");
      }

      
   }

	public void sendMail(String email,String message ,String dealer_contact_person) {
	    System.out.println("In Email");
	    String temp1[] = dealer_contact_person.split(",");
	    dealer_contact_person=temp1[1];
	    String auditor=temp1[1];
	    String emailBody="<div>"
   +"<div>"
   +"<div class='m_-1580274307317970081WordSection1' style='font-family:Helvetica;font-size:12px;font-style:normal;font-variant-caps:normal;font-weight:normal;letter-spacing:normal;text-align:start;text-indent:0px;text-transform:none;white-space:normal;word-spacing:0px;text-decoration:none'>"
     +"    <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif'><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'>Dear "+dealer_contact_person+",</span><b><i><u><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'><u></u><u></u></span></u></i></b></div>"
       +"  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif'><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'><u></u>&nbsp;<u></u></span></div>"
         +"<div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif'><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'>Further to the communication regarding Dealer Standards Audit "+temp1[4]+" "+temp1[3]+", an audit team from EY will visit<span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span><b>"+temp1[2]+" <span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span></b>outlets. The details of audit location, dates, scope, team members and contact details are tabulated below:<u></u><u></u></span></div>"
         +"<div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span style='color:rgb(68,84,106)'>&nbsp;<u></u><u></u></span></div>"
         +"<table class='m_-1580274307317970081MsoNormalTable' border='0' cellspacing='0' cellpadding='0' width='0' style='width:984.7pt;margin-left:0.2pt;border-collapse:collapse'>"
           +" <tbody>"
             +"  <tr style='height:0.2in'>"
               +"   <td width='161' nowrap='' style='width:121pt;border:1pt solid windowtext;background-color:rgb(60,73,74);padding:0in 5.4pt;height:0.2in;background-position:initial initial;background-repeat:initial initial'>"
                 +"    <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-family:&quot;BMW Group Condensed&quot;;color:white'>Dealership Name<u></u><u></u></span></div>"
                 +" </td>"
                
                 +" <td width='161' nowrap='' style='width:121pt;border-top-width:1pt;border-right-width:1pt;border-bottom-width:1pt;border-style:solid solid solid none;border-top-color:windowtext;border-right-color:windowtext;border-bottom-color:windowtext;background-color:rgb(60,73,74);padding:0in 5.4pt;height:0.2in;background-position:initial initial;background-repeat:initial initial'>"
                  +"   <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-family:&quot;BMW Group Condensed&quot;;color:white'>Outlet<u></u><u></u></span></div>"
                  +"</td>"
                  +" <td width='161' nowrap='' style='width:121pt;border-top-width:1pt;border-right-width:1pt;border-bottom-width:1pt;border-style:solid solid solid none;border-top-color:windowtext;border-right-color:windowtext;border-bottom-color:windowtext;background-color:rgb(60,73,74);padding:0in 5.4pt;height:0.2in;background-position:initial initial;background-repeat:initial initial'>"
                  +"   <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-family:&quot;BMW Group Condensed&quot;;color:white'>Scaling<u></u><u></u></span></div>"
                  +"</td>"
                  +"   <td width='161' nowrap='' style='width:121pt;border:1pt solid windowtext;background-color:rgb(60,73,74);padding:0in 5.4pt;height:0.2in;background-position:initial initial;background-repeat:initial initial'>"
                  +"    <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-family:&quot;BMW Group Condensed&quot;;color:white'>Brand<u></u><u></u></span></div>"
                  +" </td>"
                  +"<td width='144' nowrap='' style='width:1.5in;border-top-width:1pt;border-right-width:1pt;border-bottom-width:1pt;border-style:solid solid solid none;border-top-color:windowtext;border-right-color:windowtext;border-bottom-color:windowtext;background-color:rgb(60,73,74);padding:0in 5.4pt;height:0.2in;background-position:initial initial;background-repeat:initial initial'>"
                   +"  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-family:&quot;BMW Group Condensed&quot;;color:white'>Facility<u></u><u></u></span></div>"
                  +"</td>"
                  
                 +" <td width='109' nowrap='' style='width:82pt;border-top-width:1pt;border-right-width:1pt;border-bottom-width:1pt;border-style:solid solid solid none;border-top-color:windowtext;border-right-color:windowtext;border-bottom-color:windowtext;background-color:rgb(60,73,74);padding:0in 5.4pt;height:0.2in;background-position:initial initial;background-repeat:initial initial'>"
                  +"   <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-family:&quot;BMW Group Condensed&quot;;color:white'>Audit Start Date<u></u><u></u></span></div>"
                 +" </td>"
                 +" <td width='104' nowrap='' style='width:78pt;border-top-width:1pt;border-right-width:1pt;border-bottom-width:1pt;border-style:solid solid solid none;border-top-color:windowtext;border-right-color:windowtext;border-bottom-color:windowtext;background-color:rgb(60,73,74);padding:0in 5.4pt;height:0.2in;background-position:initial initial;background-repeat:initial initial'>"
                  +"   <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-family:&quot;BMW Group Condensed&quot;;color:white'>Audit End Date<u></u><u></u></span></div>"
                  +"</td>"
                 +" <td width='80' nowrap='' style='width:60pt;border-top-width:1pt;border-right-width:1pt;border-bottom-width:1pt;border-style:solid solid solid none;border-top-color:windowtext;border-right-color:windowtext;border-bottom-color:windowtext;background-color:rgb(60,73,74);padding:0in 5.4pt;height:0.2in;background-position:initial initial;background-repeat:initial initial'>"
                 +"    <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-family:&quot;BMW Group Condensed&quot;;color:white'>No. of days<u></u><u></u></span></div>"
                 +" </td>"
                  +"<td width='105' nowrap='' style='width:79.1pt;border-top-width:1pt;border-right-width:1pt;border-bottom-width:1pt;border-style:solid solid solid none;border-top-color:windowtext;border-right-color:windowtext;border-bottom-color:windowtext;background-color:rgb(60,73,74);padding:0in 5.4pt;height:0.2in;background-position:initial initial;background-repeat:initial initial'>"
                   +"  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><span style='font-family:&quot;BMW Group Condensed&quot;;color:white'>Auditor<u></u><u></u></span></div>"
                  +"</td>"
                 +" <td width='181' style='width:135.6pt;border-top-width:1pt;border-right-width:1pt;border-bottom-width:1pt;border-style:solid solid solid none;border-top-color:windowtext;border-right-color:windowtext;border-bottom-color:windowtext;background-color:rgb(60,73,74);padding:0in 5.4pt;height:0.2in;background-position:initial initial;background-repeat:initial initial'>"
                   +"  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><b><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;;color:white'>Email address<u></u><u></u></span></b></div>"
                  +"</td>"
                 +" <td width='123' style='width:92pt;border-top-width:1pt;border-right-width:1pt;border-bottom-width:1pt;border-style:solid solid solid none;border-top-color:windowtext;border-right-color:windowtext;border-bottom-color:windowtext;background-color:rgb(60,73,74);padding:0in 5.4pt;height:0.2in;background-position:initial initial;background-repeat:initial initial'>"
                   +"  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;text-align:center'><b><span style='font-size:10pt;font-family:&quot;BMW Group Condensed&quot;;color:white'>Contact Details<u></u><u></u></span></b></div>"
                  +"</td>"
               +"</tr>"
                  +message
               +"</tbody>"
         +"</table>"
         +"<div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span style='color:rgb(68,84,106)'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style='color:rgb(68,84,106)'><u></u><u></u></span></div>"
         +"<div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'><u></u>&nbsp;<u></u></span></div>"
         +"<div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<wbr>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<wbr>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<wbr>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<wbr>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u></u><u></u></span></div>"
         +"<div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'>The audit will<span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span><b>focus</b><span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span>on processes (Sales,Service) as outlined in the<span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span><b>Dealer Standards Audit parameters<span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span></b>as communicated to you by BMW India.<u></u><u></u></span></div>"
         +"<div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'><u></u>&nbsp;<u></u></span></div>"
         +"<div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'>We would request your support in making this audit a success by:<u></u><u></u></span></div>"
         +"<div style='margin:0in 0in 0.0001pt 0.5in;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span style='font-size:10pt;font-family:Symbol'>·</span><span style='font-size:7pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span></span><span style='font-size:10pt;font-family:Arial,sans-serif'>Communicating this audit announcement to all necessary personnel and emphasizing the importance of this exercise;<u></u><u></u></span></div>"
         +"<div style='margin:0in 0in 0.0001pt 0.5in;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span style='font-size:10pt;font-family:Symbol'>·</span><span style='font-size:7pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span></span><span style='font-size:10pt;font-family:Arial,sans-serif'>Organizing an opening meeting at 10.00 am on the first day of the audit, with yourself, Business Head, GM (Sales,Service) and any other personnel as deemed necessary by you for the purposes of the audit;<u></u><u></u></span></div>"
         +"<div style='margin:0in 0in 0.0001pt 0.5in;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span style='font-size:10pt;font-family:Symbol'>·</span><span style='font-size:7pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span></span><span style='font-size:10pt;font-family:Arial,sans-serif'>Nominate a representative from your executive team who would help the audit team&nbsp; co-ordinate the audit activities for the duration stated above;<u></u><u></u></span></div>"
         +"<div style='margin:0in 0in 0.0001pt 0.5in;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span style='font-size:10pt;font-family:Symbol'>·</span><span style='font-size:7pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span></span><span style='font-size:10pt;font-family:Arial,sans-serif'>Confirm availability of personnel required for the purposes of the audit and as identified in the attached preliminary audit interview agenda; and<span style='background-color:yellow;background-position:initial initial;background-repeat:initial initial'><u></u><u></u></span></span></div>"
         +"<div style='margin:0in 0in 0.0001pt 0.5in;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span style='font-size:10pt;font-family:Symbol'>·</span><span style='font-size:7pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span></span><span style='font-size:10pt;font-family:Arial,sans-serif'>Organizing an audit closing meeting on the last day of the audit at 5.30 pm, with yourself, Business Head, GM (Sales,Service) and any other personnel as deemed necessary by you for the purposes of the audit.<u></u><u></u></span></div>"
         +"<div style='margin:0in 0in 0.0001pt 0.5in;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span style='font-size:10pt;font-family:Arial,sans-serif'><u></u>&nbsp;<u></u></span></div>"
        +" <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'>The Project Management Office (PMO)<span><span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span>will call the Dealer Principal / Business head prior to the start date of the audit to confirm the schedule and discuss queries, if any.</span><u></u><u></u></span></div>"
         +" <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'><u></u>&nbsp;<u></u></span></div>"
       +"  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'>The audit opening meeting will further elaborate the audit objectives, scope, approach and timelines. Should you have any queries regarding the audit please feel free to contact the PMO,<span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span><b>Tanya Malik</b><span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span>(+91-9654 435598,</span><a href='mailto:Tanya.malik@in.ey.com' style='color:purple;text-decoration:underline' target='_blank'><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'>Tanya.malik@in.ey.com</span></a><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'>) or<span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span><b>Amitoj S Kundhal</b><span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span>(+91-9988671160,<span class='m_-1580274307317970081Apple-converted-space'>&nbsp;</span></span><a href='mailto:amitoj.kundhal@in.ey.com' style='color:purple;text-decoration:underline' target='_blank'><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'>amito<wbr>j.kundhal@in.ey.com</span></a><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'>)<u></u><u></u></span></div>"
       +"  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'><u></u>&nbsp;<u></u></span></div>"
       +"  <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;line-height:12pt'><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'>Thank you in advance for your support.<u></u><u></u></span></div>"
      +"   <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif'><span style='font-size:10pt;font-family:Arial,sans-serif'><u></u>&nbsp;<u></u></span></div>"
         +"   <div style='margin:0in 0in 0.0001pt;font-size:11pt;font-family:Calibri,sans-serif;line-height:11pt'><b><span lang='EN-GB' style='font-size:10pt;font-family:Arial,sans-serif'>BMW INDIA<u></u><u></u></span></b></div>"
      +"<br>"
        +" <p class='MsoNormal' style='page-break-after:avoid;text-autospace:none'><span style='font-family:&quot;Tms Rmn&quot;,serif;color:black'><img width='290' height='100' id='m_-4597568058627138949Picture_x0020_1' src='https://rsa.dqi.co.in:8443/BMW_UI/uploads/bmw.jpg' data-image-whitelisted='' class='CToWUd'>"
        +" <u></u><u></u></span>"
     +" </p>";
         
     
	      String from = "retailstandardsaudit@dqi.co.in";//change accordingly
	      final String username = "retailstandardsaudit@dqi.co.in";//change accordingly
	      final String password = "Codebele1!";//change accordingly

	    
	      String result = null;
	      
	      
	      try {
	           
	          Properties props = System.getProperties();
	          props.setProperty("mail.transport.protocol", "smtp");
	          props.setProperty("mail.host", "smtp.zoho.com");
	          props.put("mail.smtp.auth", "true");
	          props.put("mail.smtp.port", "465");
	          props.put("mail.debug", "true");
	          props.put("mail.smtp.socketFactory.port", "465");
	          props.put("mail.smtp.socketFactory.class",
	                  "javax.net.ssl.SSLSocketFactory");
	          props.put("mail.smtp.socketFactory.fallback", "false");

	          Session emailSession = Session.getInstance(props,
	                  new javax.mail.Authenticator() {
	                      protected PasswordAuthentication getPasswordAuthentication() {
	                      return new PasswordAuthentication(username,password);
	                  }
	          });

	          emailSession.setDebug(true);
	          Message msg = new MimeMessage(emailSession);

	        msg.setFrom(new InternetAddress(username));
	        String[] ary = email.split(",");


	        String[] to =ary;
	        InternetAddress[] address = new InternetAddress[to.length];
	        for(int i =0; i< to.length; i++)
	        {
	            address[i] = new InternetAddress(to[i]);
	        }

	         msg.setRecipients(Message.RecipientType.TO, address);
	        
	        
	        
	        
	        
	        
	      /*  InternetAddress[] toAddresses = { new InternetAddress(email) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);*/
	        msg.setSubject("BMW Dealer Standards Audit "+temp1[4]+" "+temp1[3]+" Announcement");
	        //msg.setSentDate(new Date());

	        // creates message part
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(emailBody, "text/html");

	        // creates multi-part
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);

	        // adds attachments


	        // sets the multi-part as e-mail's content
	        msg.setContent(multipart);

	        // sends the e-mail
	        Transport.send(msg);

	        System.out.println("Successfully sent email");
	          //result = "Successfully sent email";

	         } catch (MessagingException e) {
	           System.out.println("Unable to send email");
	      }

	      
	   }
	
	public void sendOnBoardMail(String email, String first_name, String uname,String pwd) {
	      System.out.println("In Email");
	      
	        // Recipient's email ID needs to be mentioned.
	        String to = email;//"manjuktrl@gmail.com";//change accordingly
	        
	        String emailBody="<div>"
	        		+"<span class='m_-6818972255031740077Apple-tab-span' style='white-space:pre-wrap'>					<font face='Times New Roman' style='font-size:14px'>	</font></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u><b><font face='Times New Roman' size='4'>Welcome Onboard!</font></b></u></div>"
	        		+" <div>"
           +" <b>"
            +"   <div style='display:inline!important'><b><font face='Times New Roman' style='font-size:14px'>Hello "+first_name+",</font></b></div>"
           +" </b>"
        +" </div>"
      +"   <div>"
            +"<font face='Times New Roman' style='font-size:14px'>"
             +"  <b>"
              +"    <div><b><br></b></div>"
             
            +"   </b>"
           +"    &nbsp;Please find below relevant details to log into the Dealer Standards Audit tool to view your audit schedule, audit completion status and audit results"
          +"  </font>"
      +"   </div>"
    +"  </div>"
      +"<p>Tool link: https://rsa.dqi.co.in:8443/rsa/signin</P>"
    +"  <div>&nbsp;</div>"
     +" <div>Your Login Credentials:</div>"
	        +"  <div>Username:&nbsp;"+uname+"</div>"
    +"  <div>Password:&nbsp;"+pwd+"</div>"

   +" <p class='MsoNormal'><u></u>&nbsp;<u></u></p>"
   +" <p class='MsoNormal'><u></u>&nbsp;<u></u></p>"
  +"  <p class='MsoNormal'>Best Wishes,<u></u><u></u></p>"
  +"  <p class='MsoNormal'>BMW Dealer Standards Audit Team<u></u><u></u></p>"
  +"<br>"
  +" <p class='MsoNormal' style='page-break-after:avoid;text-autospace:none'><span style='font-family:&quot;Tms Rmn&quot;,serif;color:black'><img width='111' height='100' id='m_-4597568058627138949Picture_x0020_1' src='https://png.pngtree.com/element_origin_min_pic/00/03/70/65568bd0be0065c.jpg' data-image-whitelisted='' class='CToWUd'><img width='111' height='100' id='m_-4597568058627138949Picture_x0020_1' src='https://thriftyzone-thriftysigns.netdna-ssl.com/wp-content/uploads/2018/05/Mini-Cooper-Logo.jpg' data-image-whitelisted='' class='CToWUd'>"
  +" <u></u><u></u></span>"
+" </p>";
       
	        // Sender's email ID needs to be mentioned
	        String from = "retailstandardsaudit@dqi.co.in";//change accordingly
	        final String username = "retailstandardsaudit@dqi.co.in";//change accordingly
	        final String password = "Codebele1!";//change accordingly

	      
	        String result = null;
	        
	        
	        try {
	             
	            Properties props = System.getProperties();
	            props.setProperty("mail.transport.protocol", "smtp");
	            props.setProperty("mail.host", "smtp.zoho.com");
	            props.put("mail.smtp.auth", "true");
	            props.put("mail.smtp.port", "465");
	            props.put("mail.debug", "true");
	            props.put("mail.smtp.socketFactory.port", "465");
	            props.put("mail.smtp.socketFactory.class",
	                    "javax.net.ssl.SSLSocketFactory");
	            props.put("mail.smtp.socketFactory.fallback", "false");

	            Session emailSession = Session.getInstance(props,
	                    new javax.mail.Authenticator() {
	                        protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username,password);
	                    }
	            });

	            emailSession.setDebug(true);
	            Message msg = new MimeMessage(emailSession);

	          msg.setFrom(new InternetAddress(username));
	          InternetAddress[] toAddresses = { new InternetAddress(email) };
	          msg.setRecipients(Message.RecipientType.TO, toAddresses);
	         /* String subject="<div>"
	        		  +"<u>Subject</u>:&nbsp;<b style='font-family:&quot;Times New Roman&quot;;font-size:14px'>BMW</b><span style='font-family:&quot;Times New Roman&quot;;font-size:14px'>&nbsp;</span><b style='font-family:&quot;Times New Roman&quot;;font-size:14px'>D</b><span style='font-family:&quot;Times New Roman&quot;;font-size:14px'>ealer’s</span><span style='font-family:&quot;Times New Roman&quot;;font-size:14px'>&nbsp;</span><b style='font-family:&quot;Times New Roman&quot;;font-size:14px'>Q</b><span style='font-family:&quot;Times New Roman&quot;;font-size:14px'>uality</span><span style='font-family:&quot;Times New Roman&quot;;font-size:14px'>&nbsp;</span><b style='font-family:&quot;Times New Roman&quot;;font-size:14px'><wbr>I</b><span style='font-family:&quot;Times New Roman&quot;;font-size:14px'>ndex : Welcome Message!</span></div>"
	                     +"<div><br></div>";*/
	          msg.setSubject("BMW Dealer Standards Audit, H2-2018 : Welcome Message!");
	          //msg.setSentDate(new Date());

	          // creates message part
	          MimeBodyPart messageBodyPart = new MimeBodyPart();
	          messageBodyPart.setContent(emailBody, "text/html");

	          // creates multi-part
	          Multipart multipart = new MimeMultipart();
	          multipart.addBodyPart(messageBodyPart);

	          // adds attachments


	          // sets the multi-part as e-mail's content
	          msg.setContent(multipart);

	          // sends the e-mail
	          Transport.send(msg);

	          System.out.println("Successfully sent email");
	            //result = "Successfully sent email";

	           } catch (MessagingException e) {
	             System.out.println("Unable to send email");
	        }

	        
	     }

	public void sendExceptiondMail(String email, String mesg, String date,String dealer_name,String period,String link,String phase,String year) {
		System.out.println("In Email");
	    /*String temp1 = "";
	    dealer_contact_person=temp1[1];
	    String auditor=temp1[1];*/
	    String emailBody="<div lang='EN-US' link='#0563C1' vlink='#954F72'>"
+"<div class='m_3043989184875868471m_-7331286915871013823WordSection1'><p class='MsoNormal'><span lang='EN-IN'>Dear "+dealer_name+",<u></u><u></u></span></p><p class='MsoNormal'><span lang='EN-IN'><u></u>&nbsp;<u></u></span></p><p class='MsoNormal'><span lang='EN-IN'>As communicated in the dealer executive summary and final score card for the BMW Dealer Standards Audits - "+phase+" "+year+", there are still a few observations against which you need to post the"
+"<span>&nbsp;</span>remediation actions taken by the dealership management.<u></u><u></u></span></p><p class='MsoNormal'><span lang='EN-IN'><u></u>&nbsp;<u></u></span></p><p class='MsoNormal'><span lang='EN-IN'>As of <span class='m_3043989184875868471m_-7331286915871013823SpellE'>"
+"<span style='background:yellow'></span></span><span style='background:yellow'><span class='m_3043989184875868471m_-7331286915871013823SpellE'></span> "+date+",</span> you have"
+"<span style='background:yellow'>"+period+" period (from the escalation matrix</span>) to close the following observation/s.<u></u><u></u></span></p><p class='MsoNormal'><span lang='EN-IN'><u></u>&nbsp;<u></u></span></p>"

+"<table class='m_3043989184875868471m_-7331286915871013823MsoNormalTable' border='0' cellspacing='0' cellpadding='0' width='0' style='width:1391.1pt;border-collapse:collapse'>"
+"<tbody>"
+"<tr style='height:12.85pt'>"
+"<td width='107' nowrap='' style='width:79.95pt;border:solid windowtext 1.0pt;background:#d9d9d9;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>Standard Number<u></u><u></u></span></p>"
+"</td>"
+"<td width='177' nowrap='' style='width:132.8pt;border:solid windowtext 1.0pt;border-left:none;background:#d9d9d9;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>Standard Name<u></u><u></u></span></p>"
+"</td>"
+"<td width='84' nowrap='' style='width:63.35pt;border:solid windowtext 1.0pt;border-left:none;background:#d9d9d9;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>Outlet<u></u><u></u></span></p>"
+"</td>"
+"<td width='434' nowrap='' style='width:325.5pt;border:solid windowtext 1.0pt;border-left:none;background:#d9d9d9;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>Observation<u></u><u></u></span></p>"
+"</td>"
+"<td width='526' nowrap='' style='width:394.75pt;border:solid windowtext 1.0pt;border-left:none;background:#d9d9d9;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>Actionable<u></u><u></u></span></p>"
+"</td>"
+"<td width='526' valign='top' style='width:394.75pt;border:solid windowtext 1.0pt;border-left:none;background:#d9d9d9;padding:0in 5.4pt 0in 5.4pt;height:12.85pt'><p class='MsoNormal' align='center' style='text-align:center'><span>Timeline<u></u><u></u></span></p><p class='MsoNormal' align='center' style='text-align:center'><span><u></u>&nbsp;<u></u></span></p>"
+"</td>"
+"</tr>"

+mesg

+"</tbody>"
+"</table>"
+"<p class='MsoNormal'><span lang='EN-IN'><u></u>&nbsp;<u></u></span></p><p class='MsoNormal'><span lang='EN-IN'>Kindly access the link- <a href='"+link+"' target='_blank' data-saferedirecturl='https://www.google.com/url?q="+link+";source=gmail&amp;ust=1545806310674000&amp;usg=AFQjCNGlHwedarBVCJ-HgQlhHfWV7zWD7Q'>"+link+"</a>, and populate the dealer management’s corrective actions. Any delay from the set timelines may lead to deduction of scores from the next audit’s scorecards,"
+" which may further lead to a loss in bonus <span class='m_3043989184875868471m_-7331286915871013823SpellE'>payouts</span>.<u></u><u></u></span></p><p class='MsoNormal'><span lang='EN-IN'><u></u>&nbsp;<u></u></span></p><p class='MsoNormal'><span lang='EN-IN'>In case of any concerns, kindly reach out to the PMO - Riga Singh (NSC PMO) &amp; Amitoj Singh (EY PMO)<u></u><u></u></span></p><p class='MsoNormal'><span lang='EN-IN'><u></u>&nbsp;<u></u></span></p><p class='MsoNormal'><span lang='EN-IN'><u></u>&nbsp;<u></u></span></p><p class='MsoNormal'><span lang='EN-IN'><u></u>&nbsp;<u></u></span></p><p class='MsoNormal'><span lang='EN-IN'>Thanks and Regards<u></u><u></u></span></p><p class='MsoNormal'><span lang='EN-IN'><u></u>&nbsp;<u></u></span></p><p class='MsoNormal'><span lang='EN-IN'><u></u>&nbsp;<u></u></span></p><p class='MsoNormal'><span lang='EN-IN'>"
+"<br>"
        +" <p class='MsoNormal' style='page-break-after:avoid;text-autospace:none'><span style='font-family:&quot;Tms Rmn&quot;,serif;color:black'><img width='290' height='100' id='m_-4597568058627138949Picture_x0020_1' src='https://rsa.dqi.co.in:8443/BMW_UI/uploads/bmw.jpg' data-image-whitelisted='' class='CToWUd'>"
        +" <u></u><u></u></span>"
     +" </p>"
        +"<u></u><u></u></span></p><p class='MsoNormal'><span lang='EN-IN'><u></u>&nbsp;<u></u></span></p><p class='MsoNormal'><span lang='EN-IN'><u></u>&nbsp;<u></u></span></p>"
+"</div>"
+"</div>";
         
     
	      String from = "retailstandardsaudit@dqi.co.in";//change accordingly
	      final String username = "retailstandardsaudit@dqi.co.in";//change accordingly
	      final String password = "Codebele1!";//change accordingly

	    
	      String result = null;
	      
	      
	      try {
	           
	          Properties props = System.getProperties();
	          props.setProperty("mail.transport.protocol", "smtp");
	          props.setProperty("mail.host", "smtp.zoho.com");
	          props.put("mail.smtp.auth", "true");
	          props.put("mail.smtp.port", "465");
	          props.put("mail.debug", "true");
	          props.put("mail.smtp.socketFactory.port", "465");
	          props.put("mail.smtp.socketFactory.class",
	                  "javax.net.ssl.SSLSocketFactory");
	          props.put("mail.smtp.socketFactory.fallback", "false");

	          Session emailSession = Session.getInstance(props,
	                  new javax.mail.Authenticator() {
	                      protected PasswordAuthentication getPasswordAuthentication() {
	                      return new PasswordAuthentication(username,password);
	                  }
	          });

	          emailSession.setDebug(true);
	          Message msg = new MimeMessage(emailSession);

	        msg.setFrom(new InternetAddress(username));
	        String[] ary = email.split(",");


	        String[] to =ary;
	        InternetAddress[] address = new InternetAddress[to.length];
	        for(int i =0; i< to.length; i++)
	        {
	            address[i] = new InternetAddress(to[i]);
	        }

	         msg.setRecipients(Message.RecipientType.TO, address);
	        
	        
	        
	        
	        
	        
	      /*  InternetAddress[] toAddresses = { new InternetAddress(email) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);*/
	        msg.setSubject(""+dealer_name+"_BMW RSA "+phase+" "+year+"_Remidiation timeline_"+date+"");
	        //msg.setSentDate(new Date());

	        // creates message part
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(emailBody, "text/html");

	        // creates multi-part
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);

	        // adds attachments


	        // sets the multi-part as e-mail's content
	        msg.setContent(multipart);

	        // sends the e-mail
	        Transport.send(msg);

	        System.out.println("Successfully sent email");
	          //result = "Successfully sent email";

	         } catch (MessagingException e) {
	           System.out.println("Unable to send email");
	      }

		
	}
	/*public void publishReport(String email, String date, String dname,String link,String phase,String year) {
	      System.out.println("In Email");
	      
	        // Recipient's email ID needs to be mentioned.
	        String to = email;//"manjuktrl@gmail.com";//change accordingly
	        
	        String emailBody="<div>"
	        		+"<span class='m_-6818972255031740077Apple-tab-span' style='white-space:pre-wrap'>					<font face='Times New Roman' style='font-size:14px'>	</font></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u><b><font face='Times New Roman' size='4'>Welcome Onboard!</font></b></u></div>"
	        		+" <div>"
         +" <b>"
          +"   <div style='display:inline!important'><b><font face='Times New Roman' style='font-size:14px'>Dear  "+dname+",</font></b></div>"
         +" </b>"
      +" </div>"
    +"   <div>"
          +"<font face='Times New Roman' style='font-size:14px'>"
           +"  <b>"
            +"    <div><b><br></b></div>"
           
          +"   </b>"
         +"    &nbsp;Further to the Retail Standards Audit- H2 2018 conducted at the outlets of ABC, please find the link for the dealer executive summary, encompassing the final results and area of improvements as observed during the audit."
        +"  </font>"
    +"   </div>"
  +"  </div>"
  +"   <div>"
  +"<font face='Times New Roman' style='font-size:14px'>"
   +"  <b>"
    +"    <div><b><br></b></div>"
   
  +"   </b>"
 +"    &nbsp;Also, as discussed, please find below the link for updating open points from RSA H2 2018.Noncompliance of the same within finalised  timelines may affect future audit scores."
+"  </font>"
+"   </div>"
    +"<p> link: "+link+""
  

 +" <p class='MsoNormal'><u></u>&nbsp;<u></u></p>"
 +" <p class='MsoNormal'><u></u>&nbsp;<u></u></p>"
+"  <p class='MsoNormal'>Best Wishes,<u></u><u></u></p>"
+"  <p class='MsoNormal'>BMW Retail Standards Audit Team<u></u><u></u></p>"
+"<br>"
+" <p class='MsoNormal' style='page-break-after:avoid;text-autospace:none'><span style='font-family:&quot;Tms Rmn&quot;,serif;color:black'><img width='111' height='100' id='m_-4597568058627138949Picture_x0020_1' src='https://png.pngtree.com/element_origin_min_pic/00/03/70/65568bd0be0065c.jpg' data-image-whitelisted='' class='CToWUd'><img width='111' height='100' id='m_-4597568058627138949Picture_x0020_1' src='https://thriftyzone-thriftysigns.netdna-ssl.com/wp-content/uploads/2018/05/Mini-Cooper-Logo.jpg' data-image-whitelisted='' class='CToWUd'>"
+" <u></u><u></u></span>"
+" </p>";
     
	        // Sender's email ID needs to be mentioned
	        String from = "retailstandardsaudit@dqi.co.in";//change accordingly
	        final String username = "retailstandardsaudit@dqi.co.in";//change accordingly
	        final String password = "Codebele1!";//change accordingly

	      
	        String result = null;
	        
	        
	        try {
	             
	            Properties props = System.getProperties();
	            props.setProperty("mail.transport.protocol", "smtp");
	            props.setProperty("mail.host", "smtp.zoho.com");
	            props.put("mail.smtp.auth", "true");
	            props.put("mail.smtp.port", "465");
	            props.put("mail.debug", "true");
	            props.put("mail.smtp.socketFactory.port", "465");
	            props.put("mail.smtp.socketFactory.class",
	                    "javax.net.ssl.SSLSocketFactory");
	            props.put("mail.smtp.socketFactory.fallback", "false");

	            Session emailSession = Session.getInstance(props,
	                    new javax.mail.Authenticator() {
	                        protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username,password);
	                    }
	            });

	            emailSession.setDebug(true);
	            Message msg = new MimeMessage(emailSession);

	          msg.setFrom(new InternetAddress(username));
	          InternetAddress[] toAddresses = { new InternetAddress(email) };
	          msg.setRecipients(Message.RecipientType.TO, toAddresses);
	          String subject="<div>"
	        		  +"<u>Subject</u>:&nbsp;<b style='font-family:&quot;Times New Roman&quot;;font-size:14px'>BMW</b><span style='font-family:&quot;Times New Roman&quot;;font-size:14px'>&nbsp;</span><b style='font-family:&quot;Times New Roman&quot;;font-size:14px'>D</b><span style='font-family:&quot;Times New Roman&quot;;font-size:14px'>ealer’s</span><span style='font-family:&quot;Times New Roman&quot;;font-size:14px'>&nbsp;</span><b style='font-family:&quot;Times New Roman&quot;;font-size:14px'>Q</b><span style='font-family:&quot;Times New Roman&quot;;font-size:14px'>uality</span><span style='font-family:&quot;Times New Roman&quot;;font-size:14px'>&nbsp;</span><b style='font-family:&quot;Times New Roman&quot;;font-size:14px'><wbr>I</b><span style='font-family:&quot;Times New Roman&quot;;font-size:14px'>ndex : Welcome Message!</span></div>"
	                     +"<div><br></div>";
	          msg.setSubject("BMW Retail Standards Audit, H2-2018 : Welcome Message!");
	          //msg.setSentDate(new Date());

	          // creates message part
	          MimeBodyPart messageBodyPart = new MimeBodyPart();
	          messageBodyPart.setContent(emailBody, "text/html");

	          // creates multi-part
	          Multipart multipart = new MimeMultipart();
	          multipart.addBodyPart(messageBodyPart);

	          // adds attachments


	          // sets the multi-part as e-mail's content
	          msg.setContent(multipart);

	          // sends the e-mail
	          Transport.send(msg);

	          System.out.println("Successfully sent email");
	            //result = "Successfully sent email";

	           } catch (MessagingException e) {
	             System.out.println(e+"Unable to send email");
	        }

	        
	     }*/
	
	public void publishReport(String email, String date, String dname,String outlets,String link,String phase,String year,String contact_person,String did) {
	    System.out.println("In Email publishreport ======"+email);
	    
	    String emailBody=" <div>"
     +" <b>"
      +"   <div style='display:inline!important'><b><font face='Times New Roman' style='font-size:14px'>Dear  "+contact_person+",</font></b></div>"
     +" </b>"
  +" </div>"
+"   <div>"
     
       +"  <b>"
        +"    <div><b><br></b></div>"
       
      +"   </b>"
     +"    Further to the Dealer Standards Audit- "+phase+" "+year+" conducted at the outlets of "+outlets+", please find the link for the dealer executive summary, encompassing the final results and area of improvements as observed during the audit."
    
+"   </div>"
+"  </div>"
+"<p> "+link+"</p>"

+"   <div>"

+"  <b>"
+"    <div><b><br></b></div>"

+"   </b>"
+"    Also, as discussed, please find below the link for updating open points from RSA H2 2018.Noncompliance of the same within finalised  timelines may affect future audit scores."

+"   </div>"
+"<p> http://rsa.dqi.co.in/rsa/dealer_exception/"+did+"/"+phase+"/"+year+""
+"   <div>"

+"  <b>"
+"    <div><b><br></b></div>"

+"   </b>"
+"    Please do reach out to PMO members (Amitoj Singh-9988671160 and Riga Singh-9717886250)"

+"   </div>"
+"<p> Thank you for your help and support. </p>"

+"  <p class='MsoNormal'>BMW India<u></u><u></u></p>"
/*+"  <p class='MsoNormal'>BMW Retail Standards Audit Team<u></u><u></u></p>"
*/
+"<br>"
+" <p class='MsoNormal' style='page-break-after:avoid;text-autospace:none'><span style='font-family:&quot;Tms Rmn&quot;,serif;color:black'><img width='290' height='100' id='m_-4597568058627138949Picture_x0020_1' src='https://rsa.dqi.co.in:8443/BMW_UI/uploads/bmw.jpg' data-image-whitelisted='' class='CToWUd'>"
+" <u></u><u></u></span>"
+" </p>"
+"<u></u><u></u></span></p><p class='MsoNormal'><span lang='EN-IN'><u></u>&nbsp;<u></u></span></p><p class='MsoNormal'><span lang='EN-IN'><u></u>&nbsp;<u></u></span></p>";
         
     
	      String from = "retailstandardsaudit@dqi.co.in";//change accordingly
	      final String username = "retailstandardsaudit@dqi.co.in";//change accordingly
	      final String password = "Codebele1!";//change accordingly

	    
	      String result = null;
	      
	      
	      try {
	           
	          Properties props = System.getProperties();
	          props.setProperty("mail.transport.protocol", "smtp");
	          props.setProperty("mail.host", "smtp.zoho.com");
	          props.put("mail.smtp.auth", "true");
	          props.put("mail.smtp.port", "465");
	          props.put("mail.debug", "true");
	          props.put("mail.smtp.socketFactory.port", "465");
	          props.put("mail.smtp.socketFactory.class",
	                  "javax.net.ssl.SSLSocketFactory");
	          props.put("mail.smtp.socketFactory.fallback", "false");

	          Session emailSession = Session.getInstance(props,
	                  new javax.mail.Authenticator() {
	                      protected PasswordAuthentication getPasswordAuthentication() {
	                      return new PasswordAuthentication(username,password);
	                  }
	          });

	          emailSession.setDebug(true);
	          Message msg = new MimeMessage(emailSession);

	        msg.setFrom(new InternetAddress(username));
	        String[] ary = email.split(",");


	        String[] to =ary;
	        InternetAddress[] address = new InternetAddress[to.length];
	        for(int i =0; i< to.length; i++)
	        {
	            address[i] = new InternetAddress(to[i]);
	        }

	         msg.setRecipients(Message.RecipientType.TO, address);
	        
	        
	      /*  InternetAddress[] toAddresses = { new InternetAddress(email) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);*/
	        msg.setSubject("BMW RSA "+year+" "+phase+" Dealer Executive Summary_"+dname+" ");
	        //msg.setSentDate(new Date());

	        // creates message part
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(emailBody, "text/html");

	        // creates multi-part
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);

	        // adds attachments


	        // sets the multi-part as e-mail's content
	        msg.setContent(multipart);

	        // sends the e-mail
	        Transport.send(msg);

	        System.out.println("Successfully sent email");
	          //result = "Successfully sent email";

	         } catch (MessagingException e) {
	           System.out.println("Unable to send email");
	      }

	      
	   }

	public void publishReport2(String email, String format, String dname, String outlets, String link,String phase, String year, String contact_person) {
System.out.println("In Email======publishreport2"+email);
	    
	    String emailBody=" <div>"
     +" <b>"
      +"   <div style='display:inline!important'><b><font face='Times New Roman' style='font-size:14px'>Dear  "+contact_person+",</font></b></div>"
     +" </b>"
  +" </div>"
+"   <div>"
     
       +"  <b>"
        +"    <div><b><br></b></div>"
       
      +"   </b>"
     +"    Further to the Dealer Standards Audit- "+phase+" "+year+" conducted at the outlets of "+outlets+", please find the link for the dealer executive summary, encompassing the final results and area of improvements as observed during the audit."
    
+"   </div>"
+"  </div>"
+"<p> "+link+"</p>"

+"   <div>"

+"  <b>"
+"   </b>"
+"    Please do reach out to PMO members (Amitoj Singh-9988671160 and Riga Singh-9717886250)"

+"   </div>"
+"<p> Thank you for your help and support. </p>"

+"  <p class='MsoNormal'>BMW India<u></u><u></u></p>"
/*+"  <p class='MsoNormal'>BMW Retail Standards Audit Team<u></u><u></u></p>"
*/
+"<br>"
+" <p class='MsoNormal' style='page-break-after:avoid;text-autospace:none'><span style='font-family:&quot;Tms Rmn&quot;,serif;color:black'><img width='290' height='100' id='m_-4597568058627138949Picture_x0020_1' src='https://rsa.dqi.co.in:8443/BMW_UI/uploads/bmw.jpg' data-image-whitelisted='' class='CToWUd'>"
+" <u></u><u></u></span>"
+" </p>"
+"<u></u><u></u></span></p><p class='MsoNormal'><span lang='EN-IN'><u></u>&nbsp;<u></u></span></p><p class='MsoNormal'><span lang='EN-IN'><u></u>&nbsp;<u></u></span></p>";
         
     
	      String from = "retailstandardsaudit@dqi.co.in";//change accordingly
	      final String username = "retailstandardsaudit@dqi.co.in";//change accordingly
	      final String password = "Codebele1!";//change accordingly

	    
	      String result = null;
	      
	      
	      try {
	           
	          Properties props = System.getProperties();
	          props.setProperty("mail.transport.protocol", "smtp");
	          props.setProperty("mail.host", "smtp.zoho.com");
	          props.put("mail.smtp.auth", "true");
	          props.put("mail.smtp.port", "465");
	          props.put("mail.debug", "true");
	          props.put("mail.smtp.socketFactory.port", "465");
	          props.put("mail.smtp.socketFactory.class",
	                  "javax.net.ssl.SSLSocketFactory");
	          props.put("mail.smtp.socketFactory.fallback", "false");

	          Session emailSession = Session.getInstance(props,
	                  new javax.mail.Authenticator() {
	                      protected PasswordAuthentication getPasswordAuthentication() {
	                      return new PasswordAuthentication(username,password);
	                  }
	          });

	          emailSession.setDebug(true);
	          Message msg = new MimeMessage(emailSession);

	        msg.setFrom(new InternetAddress(username));
	        String[] ary = email.split(",");


	        String[] to =ary;
	        InternetAddress[] address = new InternetAddress[to.length];
	        for(int i =0; i< to.length; i++)
	        {
	            address[i] = new InternetAddress(to[i]);
	        }

	         msg.setRecipients(Message.RecipientType.TO, address);
	        
	        
	      /*  InternetAddress[] toAddresses = { new InternetAddress(email) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);*/
	        msg.setSubject("BMW RSA "+year+" "+phase+" Dealer Executive Summary_"+dname+" ");
	        //msg.setSentDate(new Date());

	        // creates message part
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(emailBody, "text/html");

	        // creates multi-part
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);

	        // adds attachments


	        // sets the multi-part as e-mail's content
	        msg.setContent(multipart);

	        // sends the e-mail
	        Transport.send(msg);

	        System.out.println("Successfully sent email");
	          //result = "Successfully sent email";

	         } catch (MessagingException e) {
	           System.out.println("Unable to send email");
	      }

	      
	   }


}