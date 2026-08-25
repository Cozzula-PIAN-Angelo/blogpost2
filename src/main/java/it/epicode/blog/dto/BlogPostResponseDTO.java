package it.epicode.blog.dto;

public record BlogPostResponseDTO(Long id, String categoria, String titolo, String cover,
                                  String contenuto, int tempoDiLettura, boolean pubblicato,
                                  UserResponseDTO autore) {

}
