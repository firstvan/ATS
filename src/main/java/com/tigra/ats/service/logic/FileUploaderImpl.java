package com.tigra.ats.service.logic;

import com.tigra.ats.domain.DBFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component("textUploader")
public class FileUploaderImpl implements FileUploader {
    @Override
    public DBFile upload(MultipartFile file) {
        DBFile dbFile = null;
        try {
            dbFile = new DBFile(file.getName(), file.getContentType(), file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbFile;
    }
}
