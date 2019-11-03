package com.tigra.ats.service.logic;

import com.tigra.ats.domain.DBFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component("textUploader")
public class FileUploaderImpl implements FileUploader {
    @Override
    public DBFile upload(MultipartFile file) {
        DBFile dbFile = new DBFile();
        dbFile.setId(1L);
        dbFile.setContentType(file.getContentType());
        dbFile.setFileName(file.getName());
        try {
            dbFile.setContent(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbFile;
    }
}
