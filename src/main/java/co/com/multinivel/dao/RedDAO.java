package co.com.multinivel.dao;

import java.util.List;

import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.Red;

public abstract interface RedDAO {
	public abstract List<Red> listar() throws MultinivelDAOException;

	public abstract boolean ingresar(Red paramRed) throws MultinivelDAOException;

	public abstract Red consultar(String paramString) throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.dao.RedDAO
 * 
 * 
 */