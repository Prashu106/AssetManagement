package com.lti.controller;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lti.model.ImageModel;
import com.lti.repository.ImageRepository;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
public class FileUploadController {

	@Autowired
	ImageRepository imageRepository;

	@PostMapping("/upload")
	public ResponseEntity<ImageModel> uplaodImage(@RequestParam("file") MultipartFile file) throws IOException{
			System.out.println("Original Image Byte Size - " + file.getBytes().length);
			ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),file.getBytes(),
			file.getSize());
			img.setDate(LocalDateTime.now());
			imageRepository.save(img);
			return new ResponseEntity<ImageModel>(img,HttpStatus.OK);
	}

	@PutMapping("/upload/{id}")
	public ResponseEntity<ImageModel> updateImage(@PathVariable("id") Long id,@RequestParam("file") MultipartFile file) throws IOException{

			ImageModel img = imageRepository.findById(id).get();
			img.setPicByte(file.getBytes());
			img.setFileSize(file.getSize());
			img.setName(file.getOriginalFilename());
			img.setType(file.getContentType());
			img.setDate(LocalDateTime.now());
			imageRepository.save(img);
			return new ResponseEntity<ImageModel>(img,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/get/{id}")
	public ImageModel getImage(@PathVariable("id") Long id) throws IOException {
		final Optional<ImageModel> retrievedImage = imageRepository.findById(id);
		ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
				retrievedImage.get().getPicByte(),retrievedImage.get().getFileSize());
		img.setDate(retrievedImage.get().getDate());
		System.out.println(img.getFileSize());
		return img;
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteImage(@PathVariable("id") Long id) throws IOException {
		System.out.println("Deleted");
		imageRepository.deleteById(id);
	}
	
	@GetMapping(path = {"/get" })
	public ResponseEntity<List<ImageModel>> getImage() throws IOException {
		List<ImageModel> allImages = imageRepository.findAll();
		
		return new ResponseEntity<List<ImageModel>>(allImages,HttpStatus.OK);
	}
	
}

