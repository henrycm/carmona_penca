package co.com.multinivel.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import co.com.multinivel.dao.ParametroDAO;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.Parametro;

@Stateless
@Local({ ParametroService.class })
public class ParametroServiceImpl implements ParametroService {
	@EJB
	private ParametroDAO parametroDAO;

	public List<Parametro> listar() throws MultinivelServiceException {
		List<Parametro> lista = null;
		try {
			lista = this.parametroDAO.listar();
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public Parametro obtenerValor(String nombreParametro) throws MultinivelServiceException {
		try {
			return this.parametroDAO.obtenerValor(nombreParametro);
		} catch (MultinivelDAOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.service.ParametroServiceImpl
 * 
 * 
 */