package br.com.jsn.userregistry.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import br.com.jsn.userregistry.dto.UsuarioDto;

import br.com.jsn.userregistry.model.Usuario;
import br.com.jsn.userregistry.repository.UsuarioRepository;
import br.com.jsn.userregistry.security.SHAEncryption;
import br.com.jsn.userregistry.util.DtoMap;

@Service
public class UsuarioService  {
    
    private static final Logger logger =  Logger.getLogger(UsuarioService.class.getName());

    private SHAEncryption shaEncryption;
    private UsuarioRepository usuarioRepository;
    private DtoMap dtoMap = new DtoMap();
    public UsuarioService(UsuarioRepository usuarioRepository ,  SHAEncryption shaEncryption){
        this.usuarioRepository = usuarioRepository;
        this.shaEncryption = shaEncryption;
    }

     public UsuarioDto criar(UsuarioDto dto) {

        try {
            if (dto == null) {
                logger.log(Level.SEVERE, "O DTO de usuário não pode ser nulo");
                throw new IllegalArgumentException("usuário não pode ser nulo");
            }
    
           
            String senhaCriptografada = shaEncryption.getSHA512SecurePassword(dto.getSenha());
            dto.setSenha(senhaCriptografada);
    
          
            logger.log(Level.INFO, "DTO após criptografia: " + dto);
    
       
            Usuario usuario = usuarioRepository.save(dtoMap.convertDtoToEntity(dto));
            logger.log(Level.INFO, "Usuário salvo no repositório: " + usuario);
    
            if (usuario != null) {
                return dtoMap.convertEnityToDto(usuario);
            } else {
                logger.log(Level.SEVERE, "Usuário não foi salvo: " + dto);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Erro ao tentar criar Usuário: " + dto, e);
        }
    
        logger.log(Level.SEVERE, "Usuário não foi registrado: " + dto);
        throw new IllegalArgumentException("usuário não pode ser nulo");
    }
    

    public UsuarioDto buscar(Integer id) {
        try{

            if (id == null) {
                throw new IllegalArgumentException("O id de usuário não pode ser nulo");
            }
            Usuario usuario = usuarioRepository.findUsuarioById(id);
            

            if(usuario != null){
                
                return dtoMap.convertEnityToDto(usuario);
                }
            }catch(Exception e){
                e.getMessage();
                logger.log(Level.WARNING,  "Erro ao tentar buscar Usuário : " + id , e);

            }
            logger.log(Level.SEVERE,  "Usuário não foi encontrado : " + id);

            throw new IllegalArgumentException("Usuário não foi encontrado com o ID informado");

    }





   
}
