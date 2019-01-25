package br.com.security.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.security.model.Cliente;
import br.com.security.rest.stub.AppCliente;
import br.com.security.rest.stub.SyncCliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("select new br.com.security.rest.stub.SyncCliente(a.id, a.nome, a.email, a.tipo, a.cpf, a.cnpj, a.telefone1, a.telefone2, a.logradouro, a.numero, a.bairro, a.cep, a.cidade.nome, a.cidade.uf, a.ativo, a.latitude, a.longitude, a.excluido) from Cliente a where a.dataAlteracao > :dataSync")
	public Collection<SyncCliente> buscarNaoSincronizados(@Param("dataSync") Date dataSync);

	@Query("select new br.com.security.rest.stub.AppCliente(a.id, a.nome, a.telefone1, a.email) from Cliente a where a.telefone1 = :telefone1")
	public List<AppCliente> buscar(@Param("telefone1") String phone);

	@Transactional
	@Modifying
	@Query("update Cliente a set a.latitude = :latitude, a.longitude = :longitude, a.dataAlteracao = :dataAlteracao where a.id = :id")
	public void atualizarLocalizacao(@Param("latitude") double latitude, @Param("longitude") double longitude,
			@Param("dataAlteracao") Date dataAlteracao, @Param("id") Long id);

	@Transactional
	@Modifying
	@Query("update Cliente a set a.ativo = true, a.dataAlteracao = :dataAlteracao where a.id = :id")
	public void ativar(@Param("id") Long id, @Param("dataAlteracao") Date date);

}
