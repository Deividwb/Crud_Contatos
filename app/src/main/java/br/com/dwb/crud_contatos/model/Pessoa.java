package br.com.dwb.crud_contatos.model;

import java.io.Serializable;

public class Pessoa implements Serializable {

    private Long id;
    private  String nome;
    private String cpf;
    private String enderecos;
    private String telefones;



    public String toString(){
        return nome.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(String enderecos) {
        this.enderecos = enderecos;
    }

    public String getTelefones() {
        return telefones;
    }

    public void setTelefones(String telefones) {
        this.telefones = telefones;
    }
}
