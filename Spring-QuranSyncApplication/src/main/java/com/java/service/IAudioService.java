package com.java.service;

import org.springframework.web.multipart.MultipartFile;

public interface IAudioService {
	
	String uploadAudio(MultipartFile file);

}
