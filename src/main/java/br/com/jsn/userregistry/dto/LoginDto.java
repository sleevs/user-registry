package br.com.jsn.userregistry.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginDto {

    @JsonProperty("Informe seu email")
    private String email;
    @JsonProperty("Informe sua senha")
    private String senha;

    public LoginDto(String email , String senha){
        this.email = email;
        this.senha = senha;
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
