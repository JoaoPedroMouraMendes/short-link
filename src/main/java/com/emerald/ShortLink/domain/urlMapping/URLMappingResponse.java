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
}
