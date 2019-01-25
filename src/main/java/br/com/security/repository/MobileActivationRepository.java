package br.com.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.security.model.MobileActivation;

@Repository
public interface MobileActivationRepository extends JpaRepository<MobileActivation, String> {

}
