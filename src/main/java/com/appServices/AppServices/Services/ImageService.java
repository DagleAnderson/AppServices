package com.appServices.AppServices.Services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.appServices.AppServices.Service.exception.FileException;

@Service
public class ImageService {
	
	public  BufferedImage getJpgImageFromFile(MultipartFile uploadFile) {
		String ext = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
		if(!"png".equals(ext) &&!"jpg".equals(ext)){
			throw new FileException("Somente imagens PNG ou JPG são permitidas");			
		}	
		
		try {
			BufferedImage img = ImageIO.read(uploadFile.getInputStream());
			if("png".equals(img)) {
				img = pngtoJpg(img);
			}
			return img;	
		} catch (IOException e) {
			throw new FileException("Erro ao ler o arquivo!");
		}
		
	}

	public BufferedImage pngtoJpg(BufferedImage img) {
		BufferedImage jpgimage  = new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB);
		jpgimage.createGraphics().drawImage(img,0,0, Color.WHITE,null);
		return jpgimage;
	}
	
	public InputStream getInputStream(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img,extension,os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (Exception e) {
			throw new  FileException("Erro ao ler arquivo");
		}
		
	}
}
