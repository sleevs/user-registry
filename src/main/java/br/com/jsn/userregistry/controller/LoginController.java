package br.com.jsn.userregistry.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jsn.userregistry.dto.LoginDto;
import br.com.jsn.userregistry.service.LoginService;
import br.com.jsn.userregistry.validator.UsuarioValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class LoginController {
    
    private LoginService loginService;
    private UsuarioValidator usuarioValidator ;
    public LoginController(LoginService loginService , UsuarioValidator usuarioValidator){
        this.loginService = loginService;
        this.usuarioValidator = usuarioValidator ;
    }

     @Operation(
      summary = "Login de usuário",
      description = "Realiza login de usuário .",
      tags = {"Login de usuário"}
       )
     @ApiResponses(value = {
     @ApiResponse(responseCode = "200", description = "Login de realizado com sucesso"),
     @ApiResponse(responseCode = "400", description = "Requisição inválida")
     })
     @PostMapping("/login")
     public ResponseEntity<Object> login(@RequestBody LoginDto login){
    
        try {
            if (!usuarioValidator.validarLogin(login)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A requisição de login do usuário não pode ser nula ou vazia.");
            }
            if (!usuarioValidator.emailExistente(login.getEmail())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
            return ResponseEntity.ok(loginService.login(login));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor.");
        }
    }
    
}
