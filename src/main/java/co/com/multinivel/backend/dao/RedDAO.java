package co.com.multinivel.backend.dao;

import java.util.List;

import co.com.multinivel.backend.model.Red;
import co.com.multinivel.shared.exception.MultinivelDAOException;

public abstract interface RedDAO {
	public abstract List<Red> listar() throws MultinivelDAOException;

	public abstract boolean ingresar(Red paramRed) throws MultinivelDAOException;

	public abstract Red consultar(String paramString) throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.RedDAO
 */