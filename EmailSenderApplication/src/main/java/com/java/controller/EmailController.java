package com.java.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.java.entities.EmailRequest;
import com.java.entities.EmailResponse;
import com.java.service.IEmailService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

@RequestMapping("/api/email")
public class EmailController {
	
	@Autowired
	private IEmailService service;
	
	
	@PostMapping("/send")
	public ResponseEntity<?> sendEmail( @RequestBody EmailRequest request ) {
		
		
		service.sendEmailWithHtml(request.getTo(), request.getSubject(), request.getMessage());
		
		return ResponseEntity.ok(
				EmailResponse
				.builder()
				.message("Email Send Successfully.")
				.httpStatus(HttpStatus.OK)
				.success(true)
				.build()
				);
	}
	
//	@PostMapping("/send-with-file")
//	public ResponseEntity<EmailResponse> sendWithFile(@RequestPart EmailRequest request
//			,@RequestPart MultipartFile file ) {
//		
//		try {
//			service.SendEmailWithFile(request.getTo(), request.getSubject(),
//					request.getMessage(), file.getInputStream() );
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		
//		return ResponseEntity.ok(
//				EmailResponse
//				.builder()
//				.message("Email Send Successfully.")
//				.httpStatus(HttpStatus.OK)
//				.success(true)
//				.build()
//				);
//		
//	}
	
	
	@PostMapping("/send-with-file")
	public ResponseEntity<EmailResponse> sendWithFile(
	        @RequestPart EmailRequest request,
	        @RequestPart MultipartFile file) {

	    service.SendEmailWithFile(request.getTo(), request.getSubject(),
		        request.getMessage(), file);

	    return ResponseEntity.ok(
	            EmailResponse.builder()
	                    .message("Email sent successfully.")
	                    .httpStatus(HttpStatus.OK)
	                    .success(true)
	                    .build());
	}

	

}
