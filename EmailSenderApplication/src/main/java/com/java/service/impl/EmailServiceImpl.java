package com.java.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java.service.IEmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements IEmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Async
	@Override
	public void sendEmail(String to, String subject, String message) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		simpleMailMessage.setFrom("asifhassan2822@gmail.com");

		mailSender.send(simpleMailMessage);

	}

	@Async
	@Override
	public void sendEmail(String[] to, String subject, String message) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		simpleMailMessage.setFrom("asifhassan2822@gmail.com");
		mailSender.send(simpleMailMessage);

	}

	@Async
	@Override
	public void sendEmailWithHtml(String to, String subject, String htmlContent) {

		MimeMessage simpleMailMessage = mailSender.createMimeMessage();

		try {

			MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage, true, "UTF-8");

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("asifhassan2822@gmail.com");
			helper.setText(htmlContent, true);
			mailSender.send(simpleMailMessage);

		} catch (MessagingException e) {

			e.printStackTrace();
		}

		mailSender.send(simpleMailMessage);

	}

	@Async
	@Override
	public void SendEmailWithFile(String to, String subject, String message, File file) {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom("asifhassan2822@gmail.com");
			helper.setTo(to);
			helper.setText(message,true);
			helper.setSubject(subject);

			FileSystemResource fileSystemResource = new FileSystemResource(file);

			helper.addAttachment(fileSystemResource.getFilename(), file);

			mailSender.send(mimeMessage);

		} catch (MessagingException e) {

			e.printStackTrace();
		}

	}

//	@Async
//	@Override
//	public void SendEmailWithFile(String to, String subject, String message, InputStream inputStream) {
//
//		MimeMessage mimeMessage = mailSender.createMimeMessage();
//
//		try {
//			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//
//			helper.setFrom("asifhassan2822@gmail.com");
//			helper.setTo(to);
//			helper.setText(message,true);
//			helper.setSubject(subject);
//
//			File file = new File("src/main/resources/email/text.png");
//
//			Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//			FileSystemResource fileSystemResource = new FileSystemResource(file);
//			helper.addAttachment(fileSystemResource.getFilename(), file);
//
//			mailSender.send(mimeMessage);
//
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
	
	@Async
	@Override
	public void SendEmailWithFile(String to, String subject, String message, MultipartFile multipartFile) {
	    try {
	        // Save the file immediately
	        String uploadDir = "C:\\uploads\\";
	        File destinationFile = new File(uploadDir + multipartFile.getOriginalFilename());
	        multipartFile.transferTo(destinationFile);

	        // Create the email
	        MimeMessage mimeMessage = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	        helper.setFrom("asifhassan2822@gmail.com");
	        helper.setTo(to);
	        helper.setText(message, true);
	        helper.setSubject(subject);

	        FileSystemResource fileResource = new FileSystemResource(destinationFile);
	        helper.addAttachment(fileResource.getFilename(), fileResource);

	        // Send the email
	        mailSender.send(mimeMessage);

	        // Delete the file after processing
	        destinationFile.delete();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}



}
