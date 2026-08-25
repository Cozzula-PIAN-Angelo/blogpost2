package it.epicode.blog.entities;

import jakarta.persistence.*;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoria;
    private String titolo;
    private String cover;
    @Column(columnDefinition = "TEXT")
    private String contenuto;
    @Column(name = "tempo_lettura")
    private int tempoDiLettura;
    private boolean pubblicato;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private User autore;

    public BlogPost() {
    }

    public BlogPost(String categoria, String titolo, String contenuto,
                    int tempoDiLettura, boolean pubblicato) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.pubblicato = pubblicato;
        this.cover = "https://picsum.photos/300/200";
    }

    public Long getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCover() {
        return cover;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public int getTempoDiLettura() {
        return tempoDiLettura;
    }

    public void setTempoDiLettura(int tempoDiLettura) {
        this.tempoDiLettura = tempoDiLettura;
    }

    public boolean isPubblicato() {
        return pubblicato;
    }

    public void setPubblicato(boolean pubblicato) {
        this.pubblicato = pubblicato;
    }

    public User getAutore() {
        return autore;
    }

    public void setAutore(User autore) {
        this.autore = autore;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
