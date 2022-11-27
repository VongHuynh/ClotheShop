package com.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	public Object upload(MultipartFile multipartFile);
    public Object download(String fileName) throws IOException;
    public boolean delete(String fileName) throws IOException;
}
