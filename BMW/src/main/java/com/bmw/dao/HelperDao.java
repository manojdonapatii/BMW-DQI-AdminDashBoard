package com.bmw.dao;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class HelperDao {

	JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	String data="";

	public String renamePhoto(String file){
		
		 int length = 5;
		    boolean useLetters = true;
		    boolean useNumbers = false;
		    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		 
		    System.out.println(generatedString);
		
		String ext2 = FilenameUtils.getExtension(file); // returns "exe"
		 /* Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		  Instant instant = timestamp.toInstant();
		  Timestamp tsFromInstant = Timestamp.from(instant);
	       long time=tsFromInstant.getTime();*/
		  String renamedfilename = generatedString+"."+ext2;
		System.out.println("filename name in DAO1:"+renamedfilename);
		data=renamedfilename;
	
	
	return data;

}

}
