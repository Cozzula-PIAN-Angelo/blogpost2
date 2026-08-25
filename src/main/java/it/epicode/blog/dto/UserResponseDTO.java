package it.epicode.blog.dto;

import it.epicode.blog.entities.Ruolo;

import java.util.UUID;

public record UserResponseDTO(UUID id, String nome, String cognome, String email, Ruolo ruolo) {
}
