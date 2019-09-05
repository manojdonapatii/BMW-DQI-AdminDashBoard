package com.bmw.beans;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
 
public class MultipleFileUploadForm {
 
    private List<MultipartFile> files;
    private MultipartFile multipartFile;

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
 
       
 
}
