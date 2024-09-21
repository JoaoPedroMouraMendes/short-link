package com.emerald.ShortLink.controllers;

import com.emerald.ShortLink.domain.urlMapping.*;
import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping
public class URLMappingController {

    @Autowired
    private URLMappingService URLMappingService;
    @Value("${project.url}")
    private String projectURL;

    @GetMapping("/{shortURL}")
    public void redirectURL(@PathVariable String shortURL, HttpServletResponse response) throws IOException {
        // Obtem a URL original usanda o URL curta
        var URLMapping = URLMappingService.getOriginalURL(shortURL);
        // Redireciona para a URL original
        if (URLMapping != null)
            response.sendRedirect(URLMapping.getOriginalURL());
        else
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<URLMappingResponse> createURLMapping(@RequestBody @Validated PostRequestURLMapping data) throws WriterException, IOException {
        // Cria um novo URL mapping
        var newURLMapping = URLMappingService.createURLMapping(data);

        var response = new URLMappingResponse(
          newURLMapping.getId(),
          newURLMapping.getOriginalURL(),
                projectURL + newURLMapping.getShortURL(),
          newURLMapping.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/qrcode/{shortURL}")
    public ResponseEntity<QRCodeResponse> getQRCode(@PathVariable String shortURL) {
        URLMapping URLMapping = URLMappingService.getOriginalURL(shortURL);

        if (URLMapping != null) {
            String base64Image = Base64.getEncoder().encodeToString(URLMapping.getQrcode());

            QRCodeResponse response = new QRCodeResponse(base64Image);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
