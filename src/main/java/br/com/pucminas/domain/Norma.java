package br.com.pucminas.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "norma")
public class Norma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricaocompleta")
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
        Norma norma = (Norma) o;
        return Objects.equals(id, norma.id) && Objects.equals(codigo, norma.codigo) && Objects.equals(nome, norma.nome) && Objects.equals(descricaoCompleta, norma.descricaoCompleta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, nome, descricaoCompleta);
    }

    @Override
    public String toString() {
        return "Norma{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricaoCompleta='" + descricaoCompleta + '\'' +
                '}';
    }
}
