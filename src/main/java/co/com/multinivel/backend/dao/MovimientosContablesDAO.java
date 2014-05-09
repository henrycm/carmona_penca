package co.com.multinivel.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.multinivel.backend.model.Mvtos_Cont_Distribuidor;

public interface MovimientosContablesDAO extends JpaRepository<Mvtos_Cont_Distribuidor, Integer> {
	@Query("FROM Mvtos_Cont_Distribuidor m WHERE m.distribuidor = :distribuidor")
	public List<Mvtos_Cont_Distribuidor> buscar(@Param("distribuidor") String distribuidor);
}
