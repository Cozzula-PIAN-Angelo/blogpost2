package it.epicode.blog.exceptions;

import java.util.UUID;


public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Nessun BlogPost trovato con id " + id);
    }

    public NotFoundException(UUID uuid) {
        super("Nessun BlogPost trovato con id " + uuid);
    }

}
