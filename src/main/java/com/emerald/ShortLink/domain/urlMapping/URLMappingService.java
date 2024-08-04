package com.emerald.ShortLink.domain.urlMapping;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class URLMappingService {

    @Autowired
    private URLMappingRepository URLMappingRepository;

    public String generateURL() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }

    public URLMapping createURLMapping(PostRequestURLMapping data) {
        var newURLMapping = new URLMapping.URLMappingBuilder()
                .originalURL(data.original_url())
                .shortURL(generateURL())
                .build();
        return URLMappingRepository.save(newURLMapping);
    }

    public URLMapping getOriginalURL(String shortURL) {
        try {
            return URLMappingRepository.findByShortURL(shortURL);
        } catch (Exception error) {
            throw new RuntimeException("URL não existe em nosso registro", error);
        }
    }
}
