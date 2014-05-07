package co.com.multinivel.backend.dao;

import java.util.List;

import co.com.multinivel.backend.model.Parametro;
import co.com.multinivel.shared.exception.MultinivelDAOException;

public abstract interface ParametroDAO {
	public abstract List<Parametro> listar() throws MultinivelDAOException;

	public abstract Parametro obtenerValor(String paramString) throws MultinivelDAOException;
	
	public boolean guardar(Parametro p) throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.ParametroDAO
 */