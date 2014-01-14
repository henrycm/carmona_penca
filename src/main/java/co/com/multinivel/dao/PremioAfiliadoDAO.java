package co.com.multinivel.dao;

import java.util.List;

import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.PremioAfiliado;

public abstract interface PremioAfiliadoDAO {
	public abstract List<Object> consultar(PremioAfiliado paramPremioAfiliado)
			throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.dao.PremioAfiliadoDAO
 */