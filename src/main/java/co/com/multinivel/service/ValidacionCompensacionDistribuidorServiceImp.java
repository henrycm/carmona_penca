package co.com.multinivel.service;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import co.com.multinivel.dao.ValidarCompDistribuidorDAO;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.ValidacionCompensacionDistribuidor;

@Stateless
@Local({ ValidacionCompensacionDistribuidorService.class })
public class ValidacionCompensacionDistribuidorServiceImp implements
		ValidacionCompensacionDistribuidorService {
	@EJB
	private ValidarCompDistribuidorDAO validarCompDistribuidorDAO;

	public ValidacionCompensacionDistribuidor consultar(
			ValidacionCompensacionDistribuidor validacionCompensacionDistribuidor)
			throws MultinivelServiceException {
		ValidacionCompensacionDistribuidor validacionCompensacionDistribuidor2 = null;
		try {
			validacionCompensacionDistribuidor2 = this.validarCompDistribuidorDAO
					.consultar(validacionCompensacionDistribuidor);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return validacionCompensacionDistribuidor2;
	}

	public boolean eliminar(ValidacionCompensacionDistribuidor validacionCompensacionDistribuidor)
			throws MultinivelServiceException {
		boolean retorno = false;
		try {
			retorno = this.validarCompDistribuidorDAO.eliminar(validacionCompensacionDistribuidor);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public boolean ingresar(ValidacionCompensacionDistribuidor validacionCompensacionDistribuidor)
			throws MultinivelServiceException {
		boolean retorno = false;
		try {
			retorno = this.validarCompDistribuidorDAO.ingresar(validacionCompensacionDistribuidor);
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
 * co.com.multinivel.service.ValidacionCompensacionDistribuidorServiceImp
 * 
 * 
 */