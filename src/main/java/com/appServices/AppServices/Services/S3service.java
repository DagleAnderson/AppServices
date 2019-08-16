package com.appServices.AppServices.Services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.appServices.AppServices.Service.exception.FileException;

@Service
public class S3service {

	private Logger LOG = LoggerFactory.getLogger(S3service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartfile) {
		try {
			String fileName = multipartfile.getOriginalFilename();
			InputStream is = multipartfile.getInputStream();
			String contentType = multipartfile.getContentType();
			return uploadFile(is, fileName, contentType);
		} catch (IOException e) {
			throw new FileException("Erro de IO"+ e.getMessage());
		}

	}

	public URI uploadFile(InputStream is, String fileName, String contentType) {

		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);

			LOG.info("iniciando upload");
			s3client.putObject(bucketName+"/clientprofile", fileName, is, meta);
			LOG.info("upload Finalizado");

			return s3client.getUrl(bucketName+"/clientprofile", fileName).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao converter URL para URI");
		}

	}

	public URI uploadFilePrestador(MultipartFile multipartfile) {
		try {
			String fileName = multipartfile.getOriginalFilename();
			InputStream is = multipartfile.getInputStream();
			String contentType = multipartfile.getContentType();
			return uploadFile(is, fileName, contentType);
		} catch (IOException e) {
			throw new FileException("Erro de IO"+ e.getMessage());
		}

	}

	public URI uploadFilePrestador(InputStream is, String fileName, String contentType) {

		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);

			LOG.info("iniciando upload");
			s3client.putObject(bucketName+"/prestadorprofile", fileName, is, meta);
			LOG.info("upload Finalizado");

			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao converter URL para URI");
		}

	}

	public URI uploadFilePortfolio(MultipartFile multipartfile) {
		try {
			String fileName = multipartfile.getOriginalFilename();
			InputStream is = multipartfile.getInputStream();
			String contentType = multipartfile.getContentType();
			return uploadFile(is, fileName, contentType);
		} catch (IOException e) {
			throw new FileException("Erro de IO"+ e.getMessage());
		}

	}

	public URI uploadFilePortfolio(InputStream is, String fileName, String contentType) {

		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);

			LOG.info("iniciando upload");
			s3client.putObject(bucketName+"/portfolio", fileName, is, meta);
			LOG.info("upload Finalizado");

			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao converter URL para URI");
		}

	}


}
