package co.com.multinivel.service;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import co.com.multinivel.dao.PremioAfiliadoDAO;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.PremioAfiliado;

@Service
public class PremioAfiliadoServiceImpl implements PremioAfiliadoService {
	@EJB
	private PremioAfiliadoDAO premioAfiliadoDAO;

	public List<Object> consultar(PremioAfiliado premioAfiliado) throws MultinivelServiceException {
		List<Object> retorno = null;
		try {
			retorno = this.premioAfiliadoDAO.consultar(premioAfiliado);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.service.PremioAfiliadoServiceImpl
 */