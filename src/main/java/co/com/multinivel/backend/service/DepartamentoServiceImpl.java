package co.com.multinivel.backend.service;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.DepartamentoDAO;
import co.com.multinivel.backend.model.Departamento;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {
	@EJB
	private DepartamentoDAO departamentoDAO;

	public List<Departamento> listar() throws MultinivelServiceException {
		List<Departamento> lista = null;
		try {
			lista = this.departamentoDAO.listar();
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.DepartamentoServiceImpl
 */