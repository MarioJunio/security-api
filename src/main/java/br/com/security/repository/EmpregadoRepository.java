package br.com.security.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.security.model.Empregado;

@Repository
public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {
	
	public Empregado findByLogin(String login);

	@Transactional
	@Modifying
	@Query("update Empregado a set a.senha = :senha where a.login = :usuario")
	public void alterarSenha(@Param("senha") String novaSenha, @Param("usuario") String usuario);
	
}
