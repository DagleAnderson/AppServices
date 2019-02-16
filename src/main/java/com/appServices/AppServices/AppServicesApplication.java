package com.appServices.AppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.appServices.AppServices.Services.S3service;


@SpringBootApplication
public class AppServicesApplication implements CommandLineRunner {
	
	@Autowired
	private S3service s3service;
	
	
	public static void main(String[] args){
		SpringApplication.run(AppServicesApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	
		s3service.uploadFile("C:\\Users\\dagle\\OneDrive\\Documentos\\IGTI\\TCC\\appservicesCapa.JPG");
	 	
	}
}
