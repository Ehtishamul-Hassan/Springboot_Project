package com.java.service.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java.service.IAudioService;

@Service
public class AudioServiceImpl implements IAudioService {

	private final String uploadDir = "uploads/audio/";

	@Override
	public String uploadAudio(MultipartFile file) {

		Path path = Paths.get(uploadDir + file.getOriginalFilename());
		try {
			Files.createDirectories(path.getParent());
			Files.write(path, file.getBytes());
		}

		catch (IOException e) {
			
			e.printStackTrace();
		}

		return path.toString();

	}

}
