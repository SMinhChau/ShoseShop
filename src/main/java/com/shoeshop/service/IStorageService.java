package com.shoeshop.service;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {

    void setStorageFolder(String path);

    String storeFile(MultipartFile file);

	byte[] readFileContent(String fileName);

	void deleteFile(String fileName);
}
