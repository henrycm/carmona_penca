package co.com.multinivel.backend.service;

import java.util.List;

import co.com.multinivel.shared.dto.InventarioDistribuidorDTO;
import co.com.multinivel.shared.exception.MultinivelServiceException;

public interface InventarioDistribuidorService {
	public List<InventarioDistribuidorDTO> consultarInventarioDistribuidor(String distribuidor) throws MultinivelServiceException;
}
