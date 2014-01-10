package co.com.multinivel.dao;

import java.util.List;

import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.Parametro;

public abstract interface ParametroDAO {
	public abstract List<Parametro> listar() throws MultinivelDAOException;

	public abstract Parametro obtenerValor(String paramString) throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.dao.ParametroDAO
 * 
 * 
 */