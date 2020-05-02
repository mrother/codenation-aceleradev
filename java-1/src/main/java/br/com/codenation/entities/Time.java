package br.com.codenation.entities;

import java.time.LocalDate;

public class Time {
    private long id;
    private String nome;
    private LocalDate dateCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;

    public Time(long id, String nome, LocalDate dateCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        this.id = id;
        this.nome = nome;
        this.dateCriacao = dateCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDateCriacao() {
        return dateCriacao;
    }

    public void setDateCriacao(LocalDate dateCriacao) {
        this.dateCriacao = dateCriacao;
    }

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    public void setCorUniformePrincipal(String corUniformePrincipal) {
        this.corUniformePrincipal = corUniformePrincipal;
    }

    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    public void setCorUniformeSecundario(String corUniformeSecundario) {
        this.corUniformeSecundario = corUniformeSecundario;
    }
}
