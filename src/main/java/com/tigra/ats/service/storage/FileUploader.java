package com.tigra.ats.service.storage;

import com.tigra.ats.domain.DBFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploader {
    DBFile upload(MultipartFile file);
}
