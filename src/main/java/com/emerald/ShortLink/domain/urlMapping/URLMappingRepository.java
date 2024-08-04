package com.emerald.ShortLink.domain.urlMapping;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface URLMappingRepository extends JpaRepository<URLMapping, UUID> {

    URLMapping findByShortURL(String shortURL);
}
