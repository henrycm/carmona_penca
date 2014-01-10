package co.com.multinivel.dao;

import java.util.List;

import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.Banco;

public abstract interface BancoDAO {
	public abstract List<Banco> listar() throws MultinivelDAOException;

	public abstract Banco consultar(String paramString) throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.dao.BancoDAO
 * 
 * 
 */