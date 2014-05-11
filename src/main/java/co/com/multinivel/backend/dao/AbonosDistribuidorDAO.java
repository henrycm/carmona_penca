package co.com.multinivel.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.multinivel.backend.model.Abonos_Distribuidor;

public interface AbonosDistribuidorDAO extends JpaRepository<Abonos_Distribuidor, Integer> {
	@Query("FROM Abonos_Distribuidor m WHERE m.distribuidor = :distribuidor")
	public List<Abonos_Distribuidor> buscar(@Param("distribuidor") String distribuidor);
}
