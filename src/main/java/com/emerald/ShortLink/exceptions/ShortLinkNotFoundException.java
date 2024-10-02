package com.emerald.ShortLink.exceptions;

public class ShortLinkNotFoundException extends RuntimeException {
    public ShortLinkNotFoundException() { super("Link curto não encontrado."); }
    public ShortLinkNotFoundException(String message) { super(message); }
}
