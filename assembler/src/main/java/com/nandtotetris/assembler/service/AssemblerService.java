package com.nandtotetris.assembler.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface AssemblerService {
    Resource compile(MultipartFile file);
}
