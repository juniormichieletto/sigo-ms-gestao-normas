package br.com.pucminas.resource.dto;

import java.util.Objects;

public class NormaResponse {

    private Long id;
    private String codigo;
    private String nome;
    private String descricaoCompleta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoCompleta() {
        return descricaoCompleta;
    }

    public void setDescricaoCompleta(String descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormaResponse that = (NormaResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(codigo, that.codigo) && Objects.equals(nome, that.nome) && Objects.equals(descricaoCompleta, that.descricaoCompleta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, nome, descricaoCompleta);
    }

    @Override
    public String toString() {
        return "NormaResponse{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricaoCompleta='" + descricaoCompleta + '\'' +
                '}';
    }
}
