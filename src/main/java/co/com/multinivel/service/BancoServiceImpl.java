package co.com.multinivel.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import co.com.multinivel.dao.BancoDAO;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.Banco;

@Stateless
@Local({ BancoService.class })
public class BancoServiceImpl implements BancoService {
	@EJB
	private BancoDAO bancoDAO;

	public List<Banco> listar() throws MultinivelServiceException {
		List<Banco> lista = null;
		try {
			lista = this.bancoDAO.listar();
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public Banco consultar(String codigo) throws MultinivelServiceException {
		Banco banco = null;
		try {
			banco = this.bancoDAO.consultar(codigo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return banco;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.service.BancoServiceImpl
 * 
 * 
 */