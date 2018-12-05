package com.example.rodri.filmesseries.Classe;

import java.io.Serializable;

public class Filmes implements Serializable {
    private String titulo;
    private String id;
    private String popularidade;
    private String descricao;
    private String date;
    private String imagem;


    public Filmes(String titulo, String id, String popularidade, String descricao, String date,String imagem) {
        this.titulo = titulo;
        this.id = id;
        this.popularidade = popularidade;
        this.descricao = descricao;
        this.date = date;
        this.imagem = "http://image.tmdb.org/t/p/w185" + imagem;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getId() {
        return id;
    }

    public String getPopularidade() {
        return popularidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDate() {
        return date;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPopularidade(String popularidade) {
        this.popularidade = popularidade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
