package com.emerald.ShortLink.domain.urlMapping;

import com.emerald.ShortLink.domain.QRCode.QRCodeService;
import com.google.zxing.WriterException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;

@Service
public class URLMappingService {

    @Autowired
    private URLMappingRepository URLMappingRepository;
    @Autowired
    private QRCodeService QRCodeService;
    @Value("${project.url}")
    private String projectURL;

    public String generateURL() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }

    public URLMapping createURLMapping(PostRequestURLMapping data) throws WriterException, IOException {
        String newShortURL = generateURL();
        byte[] QRCodeInByte = QRCodeService.generateQRCode(projectURL + newShortURL, 200, 200);
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
}
