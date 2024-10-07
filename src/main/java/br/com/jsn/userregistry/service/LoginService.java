package br.com.jsn.userregistry.service;

import java.math.BigInteger;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.jsn.userregistry.dto.LoginDto;
import br.com.jsn.userregistry.dto.UsuarioDto;
import br.com.jsn.userregistry.model.Usuario;
import br.com.jsn.userregistry.repository.UsuarioRepository;
import br.com.jsn.userregistry.security.SHAEncryption;
import br.com.jsn.userregistry.util.DtoMap;

@Service
public class LoginService {

    private static final Logger logger =  Logger.getLogger(LoginService.class.getName());
    private UsuarioRepository usuarioRepository;
    private SHAEncryption shaEncryption;
    private DtoMap dtoMap = new DtoMap();
    public LoginService(UsuarioRepository usuarioRepository , SHAEncryption shaEncryption){
        this.usuarioRepository = usuarioRepository;
        this.shaEncryption = shaEncryption;
    }

    public UsuarioDto login(LoginDto login){

        try {
            // Verifica se o objeto LoginDto é nulo
            if (login == null) {
                throw new IllegalArgumentException("O Login de usuário não pode ser nulo");
            }
    
            // Verifica se o email é nulo ou vazio
            if (login.getEmail() == null || login.getEmail().isEmpty()) {
                throw new IllegalArgumentException("O email de login não pode ser nulo ou vazio");
            }
    
            // Busca o usuário pelo email
            Usuario usuario = usuarioRepository.findUsuarioByEmail(login.getEmail());
            if (usuario != null && validarSenha(login.getSenha(), usuario.getPassword())) {
                return dtoMap.convertEnityToDto(usuario);
            }
    
        } catch (Exception e) {
            logger.log(Level.WARNING, "Erro ao tentar logar com Usuário : " + login, e);
        }
    
        logger.log(Level.SEVERE, "O login do usuário não foi encontrado : " + login);
        throw new IllegalArgumentException("Login do usuário não foi realizado; existe informação nula ou vazia");
    }




        

    public boolean validarSenha(String loginSenha , String storedPassword){
        
        
        boolean isPasswordValid = shaEncryption.comparePassword(loginSenha, storedPassword);
        if (isPasswordValid) {
            return true;
        } else {
            
            return false;
        }
    
    
        }
      
    }
    







