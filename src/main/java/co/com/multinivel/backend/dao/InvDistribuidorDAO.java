package co.com.multinivel.backend.dao;

import java.util.List;

import co.com.multinivel.shared.dto.InventarioDistribuidorDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;

public interface InvDistribuidorDAO {
	public List<InventarioDistribuidorDTO> consultarInventarioDistribuidor(String distribuidor) throws MultinivelDAOException;
}
