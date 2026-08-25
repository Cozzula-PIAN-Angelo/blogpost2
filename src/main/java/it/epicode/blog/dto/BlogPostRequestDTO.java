package it.epicode.blog.dto;

public record BlogPostRequestDTO(String categoria, String titolo, String contenuto,
                                 int tempoDiLettura, boolean pubblicato, java.util.UUID autoreId) {
}
