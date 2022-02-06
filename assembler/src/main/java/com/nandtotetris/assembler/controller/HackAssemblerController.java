package com.nandtotetris.assembler.controller;

import com.nandtotetris.assembler.service.HackAssemblerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("/assembler")
public class HackAssemblerController implements AsseblerController{

    @Autowired
    private HackAssemblerService hackAssemblerService;

    @PostMapping("/compile")
    public ResponseEntity<Resource> compile(@RequestParam("file") MultipartFile file) {
       Resource compiledFile = hackAssemblerService.compile(file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + compiledFile.getFilename() + "\"").body(compiledFile);
    }
}
