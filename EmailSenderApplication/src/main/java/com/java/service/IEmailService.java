package com.java.service;

import java.io.File;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface IEmailService {
	
	//send email to single person
	void sendEmail(String to, String subject,String message);
	
	void sendEmail(String to[], String subject, String message );
	
	void sendEmailWithHtml(String to,String subject,String htmlContent);
	
	void SendEmailWithFile(String to,String subject,String message,File file);

	void SendEmailWithFile(String to,String subject,String message,MultipartFile file);
}
