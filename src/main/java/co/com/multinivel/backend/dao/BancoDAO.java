package co.com.multinivel.backend.dao;

import java.util.List;

import co.com.multinivel.backend.model.Banco;
import co.com.multinivel.shared.exception.MultinivelDAOException;

public abstract interface BancoDAO {
	public abstract List<Banco> listar() throws MultinivelDAOException;

	public abstract Banco consultar(String paramString) throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.BancoDAO
 */