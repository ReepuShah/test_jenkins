package com.framework.SendMail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.testng.annotations.Test;

import com.framework.reporting.ExtentReportHelper;

public class sendmail {
	@Test
	public static void sendmail() throws EmailException {
		System.out.println("starting mail sending.....");
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("reepushah78@gmail.com", "xqxwlswlbiwxuwcr"));
		email.setSSLOnConnect(true);
		email.setFrom("reepukumar38@gmail.com");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("reepu@geekferry.com");
		email.send();
		System.out.println("mail sent");
	}
	
	@Test
	public static String getreportpath() {
		List<String> results = new ArrayList<String>();
		List<String> htmlresults = new ArrayList<String>();
		File[] files = new File(System.getProperty("user.dir")+"/reports").listFiles();
		for (File file : files) {
		    if (file.isFile()) {
		        results.add(file.getName());
		    }
		}
		for(int i=0; i<results.size(); i++) {
			if(results.get(i).contains("ExtentReport")) {
				htmlresults.add(results.get(i));
			}
		}
		
		String latesthtmlreport = htmlresults.get(htmlresults.size()-1);
		return latesthtmlreport;
	}
	
	@Test
	public static void sendmailWithAttachmentFile() throws EmailException {
		System.out.println("Email Sending.....");
		ArrayList<String> userlist = new ArrayList<String>();
		userlist.add("reepushah78@gmail.com");
		userlist.add("manish@hellonesh.io");
		userlist.add("sidd@hellonesh.io");
		userlist.add("seth@hellonesh.io");
		EmailAttachment attachment = new EmailAttachment();
	     attachment.setPath(System.getProperty("user.dir")+"/reports/"+getreportpath());
	     attachment.setDisposition(EmailAttachment.ATTACHMENT);
	     attachment.setDescription("TestNG Report");
	     attachment.setName("QA"+getreportpath());

	     MultiPartEmail email = new MultiPartEmail();
	     email.setHostName("smtp.gmail.com");
	     email.setSmtpPort(465);
	     email.setAuthenticator(new DefaultAuthenticator("manishsangwan920@gmail.com","scfnchxurwmehvhh"));
	     email.setSSLOnConnect(true);
	     for(int i=0; i<userlist.size(); i++) {
	    	String emaiId = userlist.get(i);
	    	email.addTo(emaiId);
	     }
	     email.setFrom("manishsangwan920@gmail.com");
	     email.setSubject("QA UI Automation Execution Report");
	     email.setMsg("attached Ui Automation test cases execution report");
	     email.attach(attachment);
	     email.send();
	     System.out.println("Email Sent!");
	}
	
	@Test
	public static void demo() {
		//declaring and initializing a string  
		String str = "Converting string to string array using split() method";  
		//declaring an empty string array  
		String[] strArray = null;  
		//converting using String.split() method with whitespace as a delimiter  
		strArray = str.split(" ");  
		//printing the converted string array  
		for (int i = 0; i< strArray.length; i++){  
		System.out.println(strArray[i]);  
		}  
	}
	
	
            

	
	
	

}
