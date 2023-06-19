package com.cybersoft.cozastore_21.controller;

import com.cybersoft.cozastore_21.exception.CustomFileNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/demo")
public class DemoUploadFileController {

    //path : chua toan bo ham ho tro san lien quan den duong dan

    @Value("${path.root}")
    private String spath;


    @GetMapping("/{filename}")
    public ResponseEntity<?> loadFile(@PathVariable String filename){

        try {
            //duong dam folder rooot luu hinh
            Path rootPath = Paths.get(spath);
            Resource resource = new UrlResource(rootPath.toUri());

            if(resource.exists()){
                // neu ton tai thi moi cho download
               return  ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
            }else {
                //khi nem exception thi code se dung va quang ra loi
                throw new CustomFileNotFoundException(200,"File not found");
            }
        }catch (Exception e){
            throw new CustomFileNotFoundException(200,"File not found");
        }

    }

    @PostMapping("/uploadfile")
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file){
        //dinh nghia duong dan
        Path rootPath = Paths.get(spath);

        try {
            if(!Files.exists(rootPath)){
                // tao folder ung voi duong dan neu khong ton tai foler
                Files.createDirectories(rootPath);
            }
            ///     c/Users/ADMIN/Desktop/image21
            // resolve tuong duong dau' /
            // file.getoriginalFilename : lay ten file + dinh dang
            String filename = file.getOriginalFilename();
            Files.copy(file.getInputStream(),rootPath.resolve(filename), StandardCopyOption.REPLACE_EXISTING);


        }catch (Exception e){
            System.out.println("loi " + e.getLocalizedMessage());
        }




        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
