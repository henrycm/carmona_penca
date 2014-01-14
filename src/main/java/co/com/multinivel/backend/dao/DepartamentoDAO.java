package co.com.multinivel.backend.dao;

import java.util.List;

import co.com.multinivel.backend.model.Departamento;
import co.com.multinivel.shared.exception.MultinivelDAOException;

public abstract interface DepartamentoDAO {
	public abstract List<Departamento> listar() throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.DepartamentoDAO
 */