package it.epicode.blog.services;

import it.epicode.blog.dto.BlogPostRequestDTO;
import it.epicode.blog.dto.BlogPostResponseDTO;
import it.epicode.blog.dto.UserResponseDTO;
import it.epicode.blog.entities.BlogPost;
import it.epicode.blog.entities.User;
import it.epicode.blog.exceptions.NotFoundException;
import it.epicode.blog.exceptions.ValidationException;
import it.epicode.blog.repositories.BlogPostRepository;
import it.epicode.blog.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final UserRepository userRepository;

    public BlogPostService(BlogPostRepository blogPostRepository, UserRepository userRepository) {
        this.blogPostRepository = blogPostRepository;
        this.userRepository = userRepository;
    }

    public BlogPostResponseDTO create(BlogPostRequestDTO blogPostRequestDTO) {
        if (blogPostRequestDTO.categoria() == null || blogPostRequestDTO.categoria().isBlank()) {
            throw new ValidationException("La categoria è obbligatoria");
        }
        if (blogPostRequestDTO.titolo() == null || blogPostRequestDTO.titolo().isBlank()) {
            throw new ValidationException("Il titolo è obbligatorio");
        }
        if (blogPostRequestDTO.contenuto() == null || blogPostRequestDTO.contenuto().isBlank()) {
            throw new ValidationException("Il contenuto è obbligatorio");
        }
        if (blogPostRequestDTO.tempoDiLettura() < 0) {
            throw new ValidationException("Il tempo di lettura non può essere negativo");
        }

        BlogPost nuovoPost = new BlogPost(
                blogPostRequestDTO.categoria(),
                blogPostRequestDTO.titolo(),
                blogPostRequestDTO.contenuto(),
                blogPostRequestDTO.tempoDiLettura(),
                blogPostRequestDTO.pubblicato()
        );

        User autore = userRepository.findById(blogPostRequestDTO.autoreId())
                .orElseThrow(() -> new NotFoundException(blogPostRequestDTO.autoreId()));
        nuovoPost.setAutore(autore);

        BlogPost postSalvato = blogPostRepository.save(nuovoPost);
        return new BlogPostResponseDTO(
                postSalvato.getId(),
                postSalvato.getCategoria(),
                postSalvato.getTitolo(),
                postSalvato.getCover(),
                postSalvato.getContenuto(),
                postSalvato.getTempoDiLettura(),
                postSalvato.isPubblicato(),
                new UserResponseDTO(
                        autore.getId(),
                        autore.getNome(),
                        autore.getCognome(),
                        autore.getEmail(),
                        autore.getRuolo()
                )
        );
    }

    public BlogPost findById(Long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public List<BlogPost> findAll(String pubblicato) {
        List<BlogPost> tuttiIPost = blogPostRepository.findAll();

        if (pubblicato == null) {
            return tuttiIPost;
        }

        boolean filtro = Boolean.parseBoolean(pubblicato);
        return tuttiIPost.stream()
                .filter(post -> post.isPubblicato() == filtro)
                .collect(Collectors.toList());
    }

    public BlogPost update(Long id, BlogPost blogPostAggiornato) {
        if (blogPostAggiornato.getCategoria() == null || blogPostAggiornato.getCategoria().isBlank()) {
            throw new ValidationException("La categoria è obbligatoria");
        }
        if (blogPostAggiornato.getTitolo() == null || blogPostAggiornato.getTitolo().isBlank()) {
            throw new ValidationException("Il titolo è obbligatorio");
        }
        if (blogPostAggiornato.getContenuto() == null || blogPostAggiornato.getContenuto().isBlank()) {
            throw new ValidationException("Il contenuto è obbligatorio");
        }
        if (blogPostAggiornato.getTempoDiLettura() < 0) {
            throw new ValidationException("Il tempo di lettura non può essere negativo");
        }
        BlogPost esistente = findById(id);

        esistente.setCategoria(blogPostAggiornato.getCategoria());
        esistente.setTitolo(blogPostAggiornato.getTitolo());
        esistente.setContenuto(blogPostAggiornato.getContenuto());
        esistente.setTempoDiLettura(blogPostAggiornato.getTempoDiLettura());
        esistente.setPubblicato(blogPostAggiornato.isPubblicato());

        return blogPostRepository.save(esistente);
    }

    public void delete(Long id) {
        BlogPost esistente = findById(id);
        blogPostRepository.delete(esistente);
    }

}
