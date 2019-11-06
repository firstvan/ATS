package com.tigra.ats.controller;

import com.tigra.ats.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileController {
    private StorageService storageService;

    @Autowired
    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping(value = "/upload-file", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return storageService.uploadFile(file);
    }
}
