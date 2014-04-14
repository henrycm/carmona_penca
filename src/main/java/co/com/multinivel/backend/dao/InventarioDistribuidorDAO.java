package co.com.multinivel.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.multinivel.backend.model.InventarioDistribuidor;
import co.com.multinivel.backend.model.InventarioDistribuidorPK;

public interface InventarioDistribuidorDAO extends
		JpaRepository<InventarioDistribuidor, InventarioDistribuidorPK> {

	public List<InventarioDistribuidor> findByPkDistribuidor(String distribuidor);

}