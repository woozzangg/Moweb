package com.a507.moweb.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
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
            logger.info(e.toString());
            logger.info("파일 업로드 실패");
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
