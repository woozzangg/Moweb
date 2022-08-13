package com.a507.moweb.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImgController {
    private static final Logger logger = LoggerFactory.getLogger(ImgController.class);

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImg(@RequestParam("image") MultipartFile img) {
        String imgName = img.getOriginalFilename();
        String imgPath = Paths.get("").toAbsolutePath()+File.separator+"images"+File.separator;

        logger.info("파일이름: "+imgName);
        logger.info("파일경로: "+imgPath);

        try {
            FileOutputStream fos = new FileOutputStream(imgPath + imgName);
            fos.write(img.getBytes());
            fos.close();
            logger.info("파일 업로드 성공");
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            logger.info("파일 업로드 실패");
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/display")
    public ResponseEntity<Resource> displayImg(@Param("filename") String imgName){
        String imgPath = Paths.get("").toAbsolutePath()+File.separator+"images"+File.separator;

        Resource resource = new FileSystemResource(imgPath+imgName);
        if (!resource.exists())
            return new ResponseEntity<Resource>(HttpStatus.INTERNAL_SERVER_ERROR);

        HttpHeaders header = new HttpHeaders();
        Path path = null;

        try {
            path = Paths.get(imgPath+imgName);
            header.add("Content-Type", Files.probeContentType(path));
            logger.info("파일 url 생성 성공");
            return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("파일 url 생성 실패");
            return new ResponseEntity<Resource>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
