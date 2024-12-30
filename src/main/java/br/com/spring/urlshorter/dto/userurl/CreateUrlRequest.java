package br.com.spring.urlshorter.dto.userurl;

public record CreateUrlRequest(String originalUrl, String expirationTime) {}
