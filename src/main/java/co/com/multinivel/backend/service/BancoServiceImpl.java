package co.com.multinivel.backend.service;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.BancoDAO;
import co.com.multinivel.backend.model.Banco;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
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
 * Qualified Name: co.com.multinivel.backend.service.BancoServiceImpl
 */