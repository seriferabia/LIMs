package com.company.limsbackend.service;


import com.company.limsbackend.exception.FileStorageException;
import com.company.limsbackend.exception.MyFileNotFoundException;
import com.company.limsbackend.persistence.model.DBFile;
import com.company.limsbackend.persistence.repository.DBFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DBFileStorageService {
    private final DBFileRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file) {

        //Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = DBFile.builder()
                    .fileName(fileName)
                    .fileType(file.getContentType())
                    .data(file.getBytes())
                    .build();

            return dbFileRepository.save(dbFile);

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DBFile getFile(Long fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

}
