package com.emerald.ShortLink.controllers;

import com.emerald.ShortLink.domain.QRCode.PostRequestQRCode;
import com.emerald.ShortLink.domain.QRCode.QRCodeResponse;
import com.emerald.ShortLink.domain.QRCode.QRCodeService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("/qrcode")
public class QRCodeController {

    @Autowired
    private QRCodeService QRCodeService;

    @PostMapping
    public ResponseEntity<QRCodeResponse> createQRCode(@RequestBody @Validated PostRequestQRCode data) throws IOException, WriterException {
        byte[] QRCodeInByte = QRCodeService.generateQRCode(data.url(), 200, 200);
        String QRCodeInBase64 = Base64.getEncoder().encodeToString(QRCodeInByte);
        var response = new QRCodeResponse(QRCodeInBase64);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
