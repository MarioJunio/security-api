package br.com.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.security.model.EmpregadoRecoveryCode;

public interface EmpregadoRecoveryCodeRepository extends JpaRepository<EmpregadoRecoveryCode, Long> {
	public EmpregadoRecoveryCode findByUsuarioAndCode(String usuario, String code); 
}
