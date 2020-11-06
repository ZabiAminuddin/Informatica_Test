package com.example.controller;

import com.example.service.FileOperationsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {
    //Autowiring Service component
    @Autowired
    private FileOperationsService fileOperationsService;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    /**
     * Handles post call to upload a txt file
     * @param file
     * @return ResponseEntity
     */
    @PostMapping(value="/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("textfile") MultipartFile file) {
        String fileName = file.getOriginalFilename();

        //check whether the file present ||  the file is a .txt file
        if (null == fileName || !fileOperationsService.isTextFile(fileName)) {
            logger.error("Please upload a .txt file");
            return new ResponseEntity<>("Please upload a .txt file.", HttpStatus.BAD_REQUEST);
        }

        logger.info("File Name={}", file.getOriginalFilename());
        try {
            logger.info("Executing uploadFile");
            byte[] bytes = file.getBytes();
            Path filePath = Paths.get(file.getOriginalFilename());
            //we have get the file into the current location
            Files.write(filePath, bytes);
            //perform selection of distinct words, sorting and writing into a newly created file
            fileName = fileOperationsService.saveFile(filePath, fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(fileName + " written successfully", HttpStatus.OK);
    }
}
