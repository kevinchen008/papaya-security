package com.papaya.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {

    String path = "D:\\work\\workspaces\\workspace\\GIT\\papaya-security\\papaya-security-demo\\src\\main\\java\\com\\papaya\\controller";

    @PostMapping
    private void upload(MultipartFile file) throws IOException {
        System.out.print(file.getName());
        System.out.print(file.getOriginalFilename());
        File fileI = new File(path, new Date().getTime()+".txt");
        file.transferTo(fileI);
    }

    @GetMapping("/{id}")
    private void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try(InputStream inputStream = new FileInputStream(new File(path,id+".txt"));
          OutputStream outputStream = response.getOutputStream();){

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        }
    }
}
