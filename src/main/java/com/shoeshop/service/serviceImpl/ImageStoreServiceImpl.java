package com.shoeshop.service.serviceImpl;

import com.shoeshop.service.IStorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;

@Service
public class ImageStoreServiceImpl implements IStorageService {
	private String pathStorageFolder = "products";
	private Path storageFolder = Paths.get("src\\main\\resources\\static\\images\\"+pathStorageFolder);

	@Override
	public void setStorageFolder(String path){
		this.pathStorageFolder = path;
		this.storageFolder = Paths.get("src\\main\\resources\\static\\images\\"+pathStorageFolder);
	}

	
	public ImageStoreServiceImpl() {
		try {
			Files.createDirectories(storageFolder);
		} catch (Exception e) {
			new RuntimeException("Cannot inialize storage file. ",e);
		}
	}
	
	private boolean isImageFile(MultipartFile file) {
		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		return Arrays.asList(new String [] {"png","jpg","jpeg","bmp"})
				.contains(fileExtension.trim().toLowerCase());
	}

	@Override
	public String storeFile(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new RuntimeException("Failed to store empty file");
			}
			
			if (!isImageFile(file)) {
				throw new RuntimeException("You can only upload image file");
			}
			
			float fileSizeInMegabytes = file.getSize() / 1000000;
			if (fileSizeInMegabytes > 5.0f) {
				throw new RuntimeException("File must be <= 5Mb");
			}
			
			 //File must be rename, why ?
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String generatedFileName = UUID.randomUUID().toString().replace("-", "");
            generatedFileName = generatedFileName+"."+fileExtension;
            Path destinationFilePath = this.storageFolder.resolve(
                            Paths.get(generatedFileName))
                    .normalize().toAbsolutePath();
            if (!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())) {
                throw new RuntimeException(
                        "Cannot store file outside current directory.");
            }
            try(InputStream inputStream = file.getInputStream()){
            	Files.copy(inputStream, destinationFilePath,StandardCopyOption.REPLACE_EXISTING);
            }
            
            return generatedFileName;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to store file",e);
		}
	}


	@Override
	public byte[] readFileContent(String fileName) {
		try {
			Path file = storageFolder.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				byte [] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
				return bytes;
			}else {
				throw new RuntimeException("Could not read file: " + fileName);
			}
		} catch (Exception e) {
			throw new RuntimeException("Could not read file: " + fileName,e);
		}
	}

	@Override
	public void deleteFile(String fileName) {
		try {
			Path file = storageFolder.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				resource.getFile().delete();
			}else {
				throw new RuntimeException("Could not delete file: " + fileName);
			}
		} catch (Exception e) {
			throw new RuntimeException("Could not delete file: " + fileName,e);
		}
	}

}
