package com.ecommerce.project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //Get file name of original/current file
        String originalFileName = file.getOriginalFilename();      //mobile.jpg

        //Create a unique file name
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(originalFileName.substring(originalFileName.lastIndexOf('.')));
        /*
            indexOf('.') → finds the position of the first dot
            lastIndexOf('.') → finds the last dot
            substring(x) → cuts string from index x till the end
            filename will become -> 1234 + .jpg = 1234.jpg
        */
        String filePath = path + File.separator + fileName;     // images + / + 1234.jpg

        //Check if path exists, create if not
        File folder = new File(path);
        if(!folder.exists()){
            folder.mkdir();
        }

        //Upload to Server
        Files.copy(file.getInputStream(), Paths.get(filePath));

        //Return file name
        return fileName; //1234.jpg
    }
}
