package com.dcl.service.imp;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dcl.exception.AppException;
import com.dcl.response.CloudinaryResponse;
import com.dcl.service.CloudinaryService;

@Service
public class CloudinaryServiceImp implements CloudinaryService{
	
	@Autowired
	private Cloudinary cloud;

	@Override
	public CloudinaryResponse uploadImage(MultipartFile image) {
		CloudinaryResponse response=null;
		try {
		if(image!=null && !image.isEmpty()) {	
					Map<?, ?> result = cloud.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
						String publicId=result.get("public_id").toString();
						String imageurl=result.get("secure_url").toString();
						response=new CloudinaryResponse(imageurl, publicId);
		}
					
		} catch (IOException e) {
			e.printStackTrace();
					
		}
		return response;
	}

	@Override
	public void deleteImage(String publicId) {
		try {
			Map<?, ?> output=cloud.uploader().destroy(publicId, ObjectUtils.emptyMap());
			String status=output.get("result").toString();
			if(!status.equalsIgnoreCase("ok")) {
				throw new AppException("An error Occured", HttpStatus.BAD_REQUEST);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
