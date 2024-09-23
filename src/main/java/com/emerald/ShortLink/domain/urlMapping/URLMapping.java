package com.emerald.ShortLink.domain.urlMapping;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "url_mapping")
@Entity(name = "url_mapping")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class URLMapping {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "original_url")
    private String originalURL;
    @Column(name = "short_url")
    private String shortURL;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "qrcode")
    private String qrcode;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public URLMapping(PostRequestURLMapping data) {
        this.originalURL = data.original_url();
    }
}
