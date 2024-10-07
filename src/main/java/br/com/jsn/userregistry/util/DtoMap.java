package br.com.jsn.userregistry.util;

import br.com.jsn.userregistry.dto.UsuarioDto;
import br.com.jsn.userregistry.model.Usuario;

public class DtoMap {
    

    public Usuario convertDtoToEntity(UsuarioDto dto){

    
        Usuario usuario = new Usuario();

        if (dto.getNome() != null || !dto.getNome().isEmpty()) {
            usuario.setNome(dto.getNome());
        }
        if (dto.getEmail() != null || dto.getEmail().isEmpty()) {
            usuario.setEmail(dto.getEmail());
        }
        if (dto.getSenha() != null || dto.getSenha().isEmpty()) {
            usuario.setPassword(dto.getSenha());
        }
        
        return usuario;

    }

    public  UsuarioDto convertEnityToDto(Usuario o){

        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setEmail(o.getEmail());
        usuarioDto.setId(o.getId());
        usuarioDto.setSenha(o.getPassword());
        usuarioDto.setNome(o.getNome());


        return usuarioDto ;
    }
    


}

