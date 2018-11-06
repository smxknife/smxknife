package com.smxknife.springcloud.netflix.eureka.fileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author smxknife
 * 2018/9/16
 */
@Controller
@RequestMapping("/file")
public class FileController {

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file) throws IOException {
		byte[] bytes = file.getBytes();
		File fileToSave = new File(file.getOriginalFilename());
		FileCopyUtils.copy(bytes, fileToSave);
		return fileToSave.getAbsolutePath();
	}
}
