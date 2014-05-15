package co.com.multinivel.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.InvDistribuidorDAO;
import co.com.multinivel.shared.dto.InventarioDistribuidorDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
public class InventarioDistribuidorServiceImpl implements InventarioDistribuidorService {
	@Autowired
	private InvDistribuidorDAO invDistribuidorDAO;

	@Override
	public List<InventarioDistribuidorDTO> consultarInventarioDistribuidor(String distribuidor) throws MultinivelServiceException {
		List<InventarioDistribuidorDTO> lstInventarioDistribuidor = null;
		try {
			lstInventarioDistribuidor = this.invDistribuidorDAO.consultarInventarioDistribuidor(distribuidor);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lstInventarioDistribuidor;
	}
}
