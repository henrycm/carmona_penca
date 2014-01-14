package co.com.multinivel.backend.dao;

import java.util.List;

import co.com.multinivel.backend.model.PremioAfiliado;
import co.com.multinivel.shared.exception.MultinivelDAOException;

public abstract interface PremioAfiliadoDAO {
	public abstract List<Object> consultar(PremioAfiliado paramPremioAfiliado)
			throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.PremioAfiliadoDAO
 */