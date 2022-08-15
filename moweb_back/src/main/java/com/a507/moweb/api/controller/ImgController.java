package com.a507.moweb.api.controller;

import com.a507.moweb.api.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
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
    @Autowired
    private RoomService roomService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImg(@RequestParam("image") MultipartFile img) {
        String imgName = img.getOriginalFilename();
        String imgPath = Paths.get("").toAbsolutePath()+File.separator+"images"+File.separator;

        logger.info("파일이름: {}", imgName);
        logger.info("파일경로: {}", imgPath);

        try(FileOutputStream fos = new FileOutputStream(imgPath + imgName)) {
            fos.write(img.getBytes());
            logger.info("파일 업로드 성공");
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            logger.info("파일 업로드 실패");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/display")
    public ResponseEntity<Resource> displayImg(@RequestParam("imgName") String imgName){
        String imgPath = Paths.get("").toAbsolutePath()+File.separator+"images"+File.separator;

        Resource resource = new FileSystemResource(imgPath+imgName);
        if (!resource.exists())
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        HttpHeaders header = new HttpHeaders();
        try {
            Path path = Paths.get(imgPath+imgName);
            header.add("Content-Type", Files.probeContentType(path));
            logger.info("파일 url 생성 성공");
            return new ResponseEntity<>(resource, header, HttpStatus.OK);
        }catch (Exception e){
            logger.info("파일 url 생성 실패");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload2")
    public ResponseEntity<String> uploadImg2(@RequestParam("image") MultipartFile img, @RequestParam("shot_cnt") String shot_cnt, @RequestParam("layer") String layer, @RequestParam("room_no") String room_no, @RequestParam("bg_code") String bg_code) {
        String imgName = img.getOriginalFilename();
        String imgPath = Paths.get("").toAbsolutePath()+File.separator+"images"+File.separator;

        logger.info("파일이름: {}", imgName);
        logger.info("파일경로: {}", imgPath);

        try(FileOutputStream fos = new FileOutputStream(imgPath + imgName)) {
            fos.write(img.getBytes());
            logger.info("파일 업로드 성공");
            if(roomService.makePic(Integer.parseInt(room_no), Integer.parseInt(shot_cnt), bg_code)) {
                logger.info("방번호 : " + room_no + " " + shot_cnt + "번째 사진 업로드 완료");
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            logger.info("파일 업로드 실패");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
