package com.emerald.ShortLink.domain.QRCode;

import jakarta.validation.constraints.NotBlank;

public record PostRequestQRCode(
    @NotBlank
    String url
) {}
