package com.controller;

import com.service.FileService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class FileController {

	private final FileService fileService;

	@PostMapping("admin/upload")
	public ResponseEntity<Object> upload(@RequestPart("file") MultipartFile multipartFile) {
		return ResponseEntity.ok(fileService.upload(multipartFile));
	}


	@DeleteMapping("admin/files/{url}")
	public ResponseEntity<?> delete(@PathVariable("url") String url) {
		try {
			if (fileService.delete(url) == true)
				return ResponseEntity.ok("Delete successfully!");
			else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete failed!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
		
	}

}
