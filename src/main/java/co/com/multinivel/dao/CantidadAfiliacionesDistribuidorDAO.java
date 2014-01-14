package co.com.multinivel.dao;

import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.CantidadAfiliacionesDistribuidor;

public abstract interface CantidadAfiliacionesDistribuidorDAO {
	public abstract boolean ingresar(
			CantidadAfiliacionesDistribuidor paramCantidadAfiliacionesDistribuidor)
			throws MultinivelDAOException;

	public abstract CantidadAfiliacionesDistribuidor consultar(
			CantidadAfiliacionesDistribuidor paramCantidadAfiliacionesDistribuidor)
			throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.dao.CantidadAfiliacionesDistribuidorDAO
 */