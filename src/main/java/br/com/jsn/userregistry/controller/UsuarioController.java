package br.com.jsn.userregistry.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jsn.userregistry.dto.UsuarioDto;
import br.com.jsn.userregistry.service.UsuarioService;
import br.com.jsn.userregistry.validator.UsuarioValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@RestController
public class UsuarioController {

    private UsuarioService usuarioService;
    private UsuarioValidator usuarioValidator ;

    public UsuarioController(UsuarioService usuarioService , UsuarioValidator usuarioValidator){
        this.usuarioValidator = usuarioValidator;
        this.usuarioService = usuarioService;
    }

     @Operation(
      summary = "Criar um usuário",
      description = "Realiza a criação de um usuário .",
      tags = {"Criar usuário"}
       )
     @ApiResponses(value = {
     @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
     @ApiResponse(responseCode = "400", description = "Requisição inválida")
     })
     @PostMapping("/usuario")
     public ResponseEntity<Object> criarUsuario(@RequestBody(required = false) UsuarioDto usuario){
    
        try {
         
            if (!usuarioValidator.validarInput(usuario)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requisição inválida: todos os dados solicitados são obrigatórios.");
            }
            if (usuarioValidator.emailExistente(usuario.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requisição inválida: O email já foi registrado.");
            }
            return ResponseEntity.ok(usuarioService.criar(usuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor.");
        }
    }
    


    @Operation(
      summary = "Buscar um usuário",
      description = "Realiza a buscar de usuário a partir do ID.",
      tags = {"Buscar usuário"}
       )
     @ApiResponses(value = {
     @ApiResponse(responseCode = "200", description = "Busca dp usuário realizada com sucesso"),
     @ApiResponse(responseCode = "400", description = "Requisição inválida")
     })
     @GetMapping("/buscar")
     public ResponseEntity<Object> buscarUsuario(@RequestParam(value= "id") Integer id){

        try {
            if (id == null || id < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O ID do usuário não pode ser nulo.");
            }
            if (id == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
            return ResponseEntity.ok(usuarioService.buscar(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor.");
        }
     }




    
}
