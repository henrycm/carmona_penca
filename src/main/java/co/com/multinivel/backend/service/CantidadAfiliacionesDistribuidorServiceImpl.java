package co.com.multinivel.backend.service;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.CantidadAfiliacionesDistribuidorDAO;
import co.com.multinivel.backend.model.CantidadAfiliacionesDistribuidor;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
public class CantidadAfiliacionesDistribuidorServiceImpl implements
		CantidadAfiliacionesDistribuidorService {
	@EJB
	private CantidadAfiliacionesDistribuidorDAO cantidadAfiliacionesDistribuidorDAO;

	public CantidadAfiliacionesDistribuidor consultar(
			CantidadAfiliacionesDistribuidor cantidadAfiliacionesDistribuidor)
			throws MultinivelServiceException {
		CantidadAfiliacionesDistribuidor cantidadAfiliacionesDistribuidorretorno = null;
		try {
			cantidadAfiliacionesDistribuidorretorno = this.cantidadAfiliacionesDistribuidorDAO
					.consultar(cantidadAfiliacionesDistribuidor);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return cantidadAfiliacionesDistribuidorretorno;
	}

	public boolean ingresar(CantidadAfiliacionesDistribuidor cantidadAfiliacionesDistribuidor)
			throws MultinivelServiceException {
		boolean retorno = Boolean.FALSE;
		try {
			retorno = this.cantidadAfiliacionesDistribuidorDAO
					.ingresar(cantidadAfiliacionesDistribuidor);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name:
 * co.com.multinivel.backend.service.CantidadAfiliacionesDistribuidorServiceImpl
 */