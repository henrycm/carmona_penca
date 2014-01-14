package co.com.multinivel.service;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import co.com.multinivel.dao.DepartamentoDAO;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.Departamento;

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
 * Qualified Name: co.com.multinivel.service.DepartamentoServiceImpl
 */