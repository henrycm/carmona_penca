package co.com.multinivel.backend.service;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.RedDAO;
import co.com.multinivel.backend.model.Red;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
public class RedServiceImpl implements RedService {
	@EJB
	private RedDAO redDAO;

	public List<Red> listar() throws MultinivelServiceException {
		List<Red> lista = null;
		try {
			lista = this.redDAO.listar();
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public boolean ingresar(Red red) throws MultinivelServiceException {
		boolean retorno = false;
		try {
			retorno = this.redDAO.ingresar(red);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public Red consultar(String codigo) throws MultinivelServiceException {
		Red retorno = null;
		try {
			retorno = this.redDAO.consultar(codigo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.RedServiceImpl
 */