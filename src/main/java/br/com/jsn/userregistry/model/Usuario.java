package br.com.jsn.userregistry.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "usuario")
public class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name ="usuario_id")
    private Integer id;
    @Column(name ="usuario_nome")
    private String nome;
    @Column(name="usuario_email")
    private String email;
    @Column(name ="usuario_password")
    private String password;
    

    public Usuario(){

    }

    


    public Usuario( String nome, String email, String password) {
        
        this.nome = nome;
        this.email = email;
        this.password = password;
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


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    
    
}
