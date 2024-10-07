package br.com.jsn.userregistry.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jsn.userregistry.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    @Query(value="SELECT * FROM usuario WHERE usuario_id=?1", nativeQuery=true)
    public Usuario findUsuarioById(Integer id );

    @Query(value="SELECT * FROM usuario WHERE usuario_email=?1 ", nativeQuery=true)
    public Usuario findUsuarioByEmail(String email);
    
}
