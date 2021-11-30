package br.com.dwb.crud_contatos.model;

import java.io.Serializable;

public class PessoaEndereco implements Serializable {

    private Long id_pessoa;
    private String logradouro;
    private Integer numero;
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;

    @Override
    public String toString() {
        return "Rua: "+logradouro.toString()+"\n"+
                "Nº: "+numero.toString()+"\n"+
                "CEP: "+cep.toString()+"\n"+
                "Bairro: "+bairro.toString()+"\n"+
                "Cidade: "+cidade.toString()+"\n"+
                "Estado: "+estado.toString();
    }

    public Long getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(Long id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
