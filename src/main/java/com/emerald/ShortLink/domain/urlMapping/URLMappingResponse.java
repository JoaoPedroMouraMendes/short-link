package com.emerald.ShortLink.domain.urlMapping;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class URLMappingResponse {
    private UUID id;
    private String originalURL;
    private String shortURL;
    private LocalDateTime createdAt;
    private String qrcode;

    public URLMappingResponse(URLMapping URLMapping) {
        this.id = URLMapping.getId();
        this.originalURL = URLMapping.getOriginalURL();
        this.shortURL = URLMapping.getShortURL();
        this.createdAt = URLMapping.getCreatedAt();
        this.qrcode = URLMapping.getQrcode();
    }
}
