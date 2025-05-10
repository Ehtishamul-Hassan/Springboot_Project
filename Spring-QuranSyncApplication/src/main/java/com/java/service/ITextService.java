package com.java.service;

import org.springframework.web.multipart.MultipartFile;

public interface ITextService {
	
	String uploadText(MultipartFile file);

}
