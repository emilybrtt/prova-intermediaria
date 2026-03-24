package br.insper.provaintermediaria;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class Cliente {
    private String id;
    private String nome;
    private String email;
    private String cpf; // pesquisei no Google sobre a forma mais utilizada para guardar esse tipo de variável
    private String telefone; // idem acima
    private boolean deleted;

    public Cliente(String nome, String email, String cpf, String telefone) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.deleted = false;
    }

    public String getId() {
        return id;
    }

 //   public void setId(String id) {this.id = id;}  -> Não se altera o ID


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean b) {
        this.deleted = b;
    }

}
