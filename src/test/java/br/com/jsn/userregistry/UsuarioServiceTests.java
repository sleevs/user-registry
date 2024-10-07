package br.com.jsn.userregistry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import br.com.jsn.userregistry.dto.UsuarioDto;
import br.com.jsn.userregistry.model.Usuario;
import br.com.jsn.userregistry.repository.UsuarioRepository;
import br.com.jsn.userregistry.security.SHAEncryption;
import br.com.jsn.userregistry.service.UsuarioService;
import br.com.jsn.userregistry.util.DtoMap;




class UsuarioServiceTests {

	@InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private DtoMap dtoMap;

	@Mock
    private SHAEncryption shaEncryption;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


	@Test
    @DisplayName (value="Deve retornar sucesso Quando UsuarioDto for válido")
	public void cadastroDeUsuarioComSucesso() {

        
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome("Nelson Mandela");
        usuarioDto.setEmail("mandela@hotmail.com");
        usuarioDto.setSenha("Mandela29SouthAfrica");

       
        Usuario usuario = new Usuario();
        usuario.setNome("Nelson Mandela");
        usuario.setEmail("mandela@hotmail.com");
        usuario.setPassword("senhaCriptografada"); 

        // Mock do comportamento da criptografia
        String senhaCriptografada = "senhaCriptografada"; 
        when(shaEncryption.getSHA512SecurePassword(usuarioDto.getSenha())).thenReturn(senhaCriptografada);

        
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

       
        UsuarioDto resultado = usuarioService.criar(usuarioDto);

      assertNotNull(resultado, "Usuário não foi registrado; existe informação nula ou vazia");
    assertEquals("Nelson Mandela", resultado.getNome());
    assertEquals("mandela@hotmail.com", resultado.getEmail());
    assertEquals(senhaCriptografada, resultado.getSenha());
    }



	@Test
	@DisplayName("Usuário não foi registrado; existe informação nula ou vazia")
	public void comportamentoQuandoUsuarioForNulo() {
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			usuarioService.criar(null);
		});
	
		
		String expectedMessage = "usuário não pode ser nulo";
		String actualMessage = exception.getMessage();
	
		assertEquals(expectedMessage, actualMessage, "A mensagem de erro não é a esperada.");
	}






@Test
@DisplayName("Deve retornar UsuarioDto quando ID é válido")
public void comportamentoQuandoIdForValido() {
    
    Integer id = 1;

    Usuario usuario = new Usuario();
    usuario.setId(id);
    usuario.setNome("Nelson Mandela");
    usuario.setEmail("mandela@hotmail.com");
    usuario.setPassword("senhaCriptografada");

    
    when(usuarioRepository.findUsuarioById(id)).thenReturn(usuario);
    UsuarioDto resultado = usuarioService.buscar(id);

    assertNotNull(resultado, "Usuário não foi encontrado; o resultado não pode ser nulo.");
    assertEquals("Nelson Mandela", resultado.getNome());
    assertEquals("mandela@hotmail.com", resultado.getEmail());
}


@Test
@DisplayName("Deve lançar IllegalArgumentException quando ID não é encontrado")
public void comportamentoQuandoIdNaoEEncontrado() {
  
    Integer id = 50000;

   
    when(usuarioRepository.findUsuarioById(id)).thenReturn(null);
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        usuarioService.buscar(id);
    });

    String expectedMessage = "Usuário não foi encontrado com o ID informado";
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage, "A mensagem de erro não é a esperada.");
}

}
