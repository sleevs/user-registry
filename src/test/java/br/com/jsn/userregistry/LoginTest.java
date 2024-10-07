package br.com.jsn.userregistry;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jsn.userregistry.controller.LoginController;
import br.com.jsn.userregistry.dto.LoginDto;
import br.com.jsn.userregistry.dto.UsuarioDto;
import br.com.jsn.userregistry.service.LoginService;
import br.com.jsn.userregistry.validator.UsuarioValidator;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class) 

public class LoginTest {

    @Autowired
    private MockMvc mockMvc; 

    @MockBean
    private LoginService loginService; 

    @MockBean
    private UsuarioValidator usuarioValidator; 

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper(); 
    }

    @Test
    @DisplayName (value="Deve retornar 200 Quando Login for válido")
    public void deveRetornar200QuandoLoginValido() throws Exception  {
       
        LoginDto loginDto = new LoginDto("mandela@hotmail.com", "senhaCorreta");

        when(usuarioValidator.validarLogin(any(LoginDto.class))).thenReturn(true);
        when(usuarioValidator.emailExistente(loginDto.getEmail())).thenReturn(true);
        when(loginService.login(loginDto)).thenReturn(new UsuarioDto());

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON) 
                .content(objectMapper.writeValueAsString(loginDto))) 
            .andExpect(status().isOk()); 
    }



@Test
@DisplayName (value="Deve retornar 400 Quando Login for inválido")
public void deveRetornar400QuandoLoginInvalido() throws Exception {
   
    LoginDto loginDto = new LoginDto("", ""); 

    when(usuarioValidator.validarLogin(any(LoginDto.class))).thenReturn(false);
    when(usuarioValidator.emailExistente(loginDto.getEmail())).thenReturn(false); 

    mockMvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginDto))) 
        .andExpect(status().isBadRequest()); 
}



}