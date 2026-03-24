package br.insper.provaintermediaria;

import java.util.UUID;

public class Equipamento {
    private String nome;
    private String categoria;
    private float valorDiaria;
    private long codigoPatrimonio;
    private boolean deleted;
    private String id;

    public Equipamento(String nome, String categoria, float valorDiaria, long codigoPatrimonio) {
        this.nome = nome;
        this.categoria = categoria;
        this.valorDiaria = valorDiaria;
        this.codigoPatrimonio = codigoPatrimonio;
        this.deleted = false;
        this.id = UUID.randomUUID().toString();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(float valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public long getCodigoPatrimonio() {
        return codigoPatrimonio;
    }

    public void setCodigoPatrimonio(long codigoPatrimonio) {
        this.codigoPatrimonio = codigoPatrimonio;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getId() {return this.id;}

//    public void setId(String id) {this.id = id;} -> Não se altera o ID

}
