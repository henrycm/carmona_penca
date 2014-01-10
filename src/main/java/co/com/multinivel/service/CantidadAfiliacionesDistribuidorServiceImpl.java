package co.com.multinivel.service;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import co.com.multinivel.dao.CantidadAfiliacionesDistribuidorDAO;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.CantidadAfiliacionesDistribuidor;

@Stateless
@Local({ CantidadAfiliacionesDistribuidorService.class })
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
		boolean retorno = false;
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
 * co.com.multinivel.service.CantidadAfiliacionesDistribuidorServiceImpl
 * 
 * 
 */