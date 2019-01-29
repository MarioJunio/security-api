package br.com.security.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.security.model.Checkin;
import br.com.security.rest.stub.AppClienteCheckin;
import br.com.security.rest.stub.ClienteSms;
import br.com.security.rest.stub.SyncCheckin;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long> {

	@Query("select new br.com.security.rest.stub.SyncCheckin(a.id, a.empregado.id, a.cliente.id, a.data, a.latitude, a.longitude, a.status, a.descricao) from Checkin a where a.empregado.id = :id and a.timeSync > :time_sync")
	public Collection<SyncCheckin> findByEmpregado(@Param("id") Long id, @Param("time_sync") Long timeSync);

	@Query("select new br.com.security.rest.stub.ClienteSms(b.nome, a.data, a.descricao, b.telefone1) from Checkin a join a.cliente b where a.id = :id")
	public ClienteSms findBySms(@Param("id") Long id);
	
	@Query("select new br.com.security.rest.stub.AppClienteCheckin(a.id, b.nome, a.data, a.status, a.descricao, a.foto <> null) from Checkin a join a.empregado b where a.cliente.id = :id and a.timeSync > :time_sync")
	public List<AppClienteCheckin> findByCliente(@Param("id") Long id, @Param("time_sync") Long timeSync); 
	
	

}
