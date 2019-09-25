package Horizon.WeeklyReport;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.log4j.Logger;
import javax.activation.*;

	public class SendMailDC {
		static Logger log = Logger.getLogger(SendMailDC.class.getName());
		public static void sendMail(String [] args) {  
			
			
			   Properties prop = getProperties();
			   PropertyConfigurator.configure(prop.getProperty("log4jConfPath"));
		      // Recipient's email ID needs to be mentioned.
		      //String to = "raghvendra.tiwari@niit-tech.com";

		      // Sender's email ID needs to be mentioned
		      String from = prop.getProperty("FROM");

		      // Assuming you are sending email from localhost
		      //String host = "localhost";

		      // Get system properties
		      Properties properties = System.getProperties();

		      // Setup mail server
		      properties.setProperty("mail.smtp.host",prop.getProperty("SMTP_HOST"));

		      // Get the default Session object.
		      Session session = Session.getDefaultInstance(properties);
		     
		      
		      try {
		         // Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);
		         
		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));
		         
		         String [] receipentList=getToList();
		         int count=receipentList.length;
		         Address[] addresses =new Address[count];
		         
		         for(int index=0;index<count;index++) {
		        	 //System.out.println(receipentList[index]);
		        	 addresses[index]=new InternetAddress(receipentList[index]);
		         }
		         
		         	         
		         for(int index=0;index<addresses.length;index++) {
		        	// System.out.println("==>"+addresses[index].toString());
		        	
		         }
		         message.addRecipients(Message.RecipientType.TO,addresses);
		         // Set Subject: header field
		         message.setSubject("Weekly Deployments count for "+args[0]);

		         
		         
		         
		         // Now set the actual message
		         
		         String mailBody="<div><font size=\"2\" color=\"black\" face=\"Calibri\">"
		         		+ "Hi All,<br><br>"
		         		+ "Below is the deplyment count for  <font color=black>"+args[0]+". </font><br><br>"
		         		+ "</div>"
		         		+ args[1]+""
		         		
	         				+ "<br>Thanks,<br>"
		         				+ "HORIZON Deployment Team";
		         
		         //message.setText(mailBody);
		         message.setContent(mailBody,"text/html" );  

		         // Send message
		         Transport.send(message);
		         
		         log.info("Sent message successfully....");
		      } catch (MessagingException mex) {
		         log.error("",mex);
		      }
		   }
		
		public static String[]  getToList() {
			
			Properties prop = getProperties();
	    				
			String toList=prop.getProperty("TO_LIST");
			
			String [] receipentList=toList.split(",");		
			
			return receipentList;
		}
		
		public static Properties getProperties() {
			
			Properties prop = new Properties();
	    	InputStream input = null;
			try {
				//input = new FileInputStream("./config/config.properties");
				input = new FileInputStream("D:\\Test\\config\\config.properties");
				prop.load(input);
							
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return prop;
		}
	}



