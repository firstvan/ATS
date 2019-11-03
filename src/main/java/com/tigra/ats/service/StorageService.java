package com.tigra.ats.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tigra.ats.domain.DBFile;
import com.tigra.ats.service.logic.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class StorageService {
    private FileUploader fileUploader;

    @Autowired
    public StorageService(@Qualifier("textUploader") FileUploader fileUploader) {
        this.fileUploader = fileUploader;
    }

    public String uploadFile(MultipartFile file) {
        DBFile dbFile = fileUploader.upload(file);
        ObjectMapper objectMapper = new ObjectMapper();
        String response = null;
        try {
            response = objectMapper.writeValueAsString(dbFile);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }
}
