package co.com.multinivel.backend.dao;

import co.com.multinivel.backend.model.CantidadAfiliacionesDistribuidor;
import co.com.multinivel.shared.exception.MultinivelDAOException;

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
 * Qualified Name:
 * co.com.multinivel.backend.dao.CantidadAfiliacionesDistribuidorDAO
 */