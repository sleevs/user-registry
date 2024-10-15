package br.com.jsn.userregistry.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioDto {
    

    @JsonIgnore 
    private Integer id;
    @JsonProperty("Informe seu nome")
    private String nome ;
    @JsonProperty("Informe seu email")
    private String email;
    @JsonProperty("Informe sua senha")
    private String senha;

    public UsuarioDto(){}

    

    public UsuarioDto(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    
}
