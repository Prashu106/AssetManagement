package com.lti.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IMAGE_TABLE")
public class ImageModel {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;
	 
	private LocalDateTime date;
	
	public Long fileSize;
    //image bytes can have large lengths so we specify a value
    //which is more than the default length for picByte column

	@Column(name = "picByte")
	private byte[] picByte;
	
	public ImageModel() {
		super();
	}

	public ImageModel(String name, String type, byte[] picByte,Long fileSize) {
		this.name = name;
		this.type = type;
		this.picByte = picByte;
		this.fileSize=fileSize;
		
	}
	
	public String getName() {
		return name;
	}
	
	public Long getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime localDateTime) {
		this.date = localDateTime;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
}
