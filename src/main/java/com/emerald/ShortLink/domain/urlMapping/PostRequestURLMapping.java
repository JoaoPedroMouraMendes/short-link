package com.emerald.ShortLink.domain.urlMapping;

import jakarta.validation.constraints.NotBlank;

public record PostRequestURLMapping(
        @NotBlank
        String original_url
) { }
