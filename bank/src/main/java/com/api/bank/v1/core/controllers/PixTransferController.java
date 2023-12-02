package com.api.bank.v1.core.controllers;

import com.api.bank.v1.core.entity.PixTransferRequest;
import com.api.bank.v1.core.service.PixTransferService;
import com.api.bank.v1.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.math.BigDecimal;

@RestController
@RequestMapping("/V1/bank/pix")
public class PixTransferController {

    private final Logger logger = LoggerFactory.getLogger(PixTransferController.class);

    private final PixTransferService pixTransferService;

    @Autowired
    public PixTransferController(PixTransferService pixTransferService) {
        this.pixTransferService = pixTransferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferPix(@RequestBody PixTransferRequest request) {
        try {

            pixTransferService.addPixTransferToAccount(request);
            return ResponseEntity.ok("PIX Transfer processed successfully.");
            
        } catch (RepositoryException e) {
            logger.error("Internal server error during PIX Transfer processing", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error during PIX Transfer processing");
        }
    }
}
