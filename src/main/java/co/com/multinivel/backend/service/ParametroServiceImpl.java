package co.com.multinivel.backend.service;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.ParametroDAO;
import co.com.multinivel.backend.model.Parametro;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
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

	public boolean guardar(Parametro p) throws MultinivelServiceException {
		boolean retorno = Boolean.FALSE;
		try {
			retorno = this.parametroDAO.guardar(p);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.ParametroServiceImpl
 */