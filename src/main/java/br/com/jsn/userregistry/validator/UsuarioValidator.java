package br.com.jsn.userregistry.validator;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jsn.userregistry.dto.LoginDto;
import br.com.jsn.userregistry.dto.UsuarioDto;

import br.com.jsn.userregistry.model.Usuario;
import br.com.jsn.userregistry.repository.UsuarioRepository;


@Component
public class UsuarioValidator {

       private static final Logger logger =  Logger.getLogger(UsuarioValidator.class.getName());

    @Autowired
    private UsuarioRepository usuarioRepository;

     public boolean validateDto(UsuarioDto usuario) {
        if (usuario == null) {
          return false;
        }
        return true;
    
    }



    public boolean validateDto(LoginDto dto) {
        if (dto == null) {
          return false;
        }
        return true;
    
    }


    public boolean validarInput(UsuarioDto dto){
        if (dto.getNome() == null || dto.getNome().isEmpty() ||
            dto.getEmail() == null || dto.getEmail().isEmpty() ||
            dto.getSenha() == null || dto.getSenha().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validarLogin(LoginDto dto){
        if (dto.getSenha() == null || dto.getSenha().isEmpty() ||
            dto.getEmail() == null || dto.getEmail().isEmpty()) {
            return false;
        }
        return true;
    }

     public Boolean emailExistente(String email){

        try{
            Usuario u =  usuarioRepository.findUsuarioByEmail(email);
        if(u != null){
            return true;
        }
        }catch(Exception e){
            e.getMessage();
            logger.log(Level.SEVERE,  "Erro ao tentar validar email" + email, e);
        }
        return false;
    }
    
}
