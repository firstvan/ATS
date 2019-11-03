package com.tigra.ats.service.logic;

import com.tigra.ats.domain.DBFile;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

public interface FileUploader {
    DBFile upload(MultipartFile file);
}
