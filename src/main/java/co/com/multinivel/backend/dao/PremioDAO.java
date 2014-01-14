package co.com.multinivel.backend.dao;

import java.util.List;

import co.com.multinivel.backend.model.Premio;
import co.com.multinivel.shared.exception.MultinivelDAOException;

public abstract interface PremioDAO {
	public abstract List<Premio> consultar(Premio paramPremio) throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.PremioDAO
 */