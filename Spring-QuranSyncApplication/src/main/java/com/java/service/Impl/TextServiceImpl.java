package com.java.service.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java.service.ITextService;

@Service
public class TextServiceImpl implements ITextService {

	private final String uploadDir = "upload/text/";

	@Override
	public String uploadText(MultipartFile file) {

		Path path = Paths.get(uploadDir + file.getOriginalFilename());

		try {
			Files.createDirectories(path.getParent());
			Files.write(path, file.getBytes());

		} catch (IOException e) {

			e.printStackTrace();
		}

		return path.toString();
	}

}
