package com.emerald.ShortLink.domain.urlMapping;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class URLMappingService {

    @Autowired
    private URLMappingRepository URLMappingRepository;
    @Value("${project.url}")
    private String projectURL;

    public String generateURL() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }

    public URLMapping createURLMapping(PostRequestURLMapping data) throws WriterException, IOException {
        String newShortURL = generateURL();
        byte[] QRCodeInByte = generateQRCode(projectURL + newShortURL, 200, 200);
        String QRCodeBase64 = Base64.getEncoder().encodeToString(QRCodeInByte);

        var newURLMapping = new URLMapping.URLMappingBuilder()
                .originalURL(data.original_url())
                .shortURL(newShortURL)
                .qrcode(QRCodeBase64)
                .build();
        return URLMappingRepository.save(newURLMapping);
    }

    public URLMapping getOriginalURL(String shortURL) {
        try {
            return URLMappingRepository.findByShortURL(shortURL);
        } catch (Exception error) {
            throw new RuntimeException("Essa URL não existe em nosso registro", error);
        }
    }

    public byte[] generateQRCode(String url, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }
}
