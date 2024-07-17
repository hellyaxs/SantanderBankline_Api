package com.dio.santander.Bankline.api.cnab.controller;

import com.dio.santander.Bankline.api.cnab.services.CnabService;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("cnab")
public class CnabController {

    private final CnabService cnabService;

    @Autowired
    public CnabController(CnabService cnabService) {
        this.cnabService = cnabService;
    }


    @PostMapping("upload")
    public ResponseEntity<String> uplaodFile(MultipartFile file) throws IOException, JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        cnabService.uploadCnabfile(file);
        return ResponseEntity.ok("Arquivo recebido");
    }
}
