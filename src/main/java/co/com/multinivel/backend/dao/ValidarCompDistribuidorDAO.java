package co.com.multinivel.backend.dao;

import co.com.multinivel.backend.model.ValidacionCompensacionDistribuidor;
import co.com.multinivel.shared.exception.MultinivelDAOException;

public abstract interface ValidarCompDistribuidorDAO {
	public abstract boolean ingresar(
			ValidacionCompensacionDistribuidor paramValidacionCompensacionDistribuidor)
			throws MultinivelDAOException;

	public abstract boolean eliminar(
			ValidacionCompensacionDistribuidor paramValidacionCompensacionDistribuidor)
			throws MultinivelDAOException;

	public abstract ValidacionCompensacionDistribuidor consultar(
			ValidacionCompensacionDistribuidor paramValidacionCompensacionDistribuidor)
			throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.ValidarCompDistribuidorDAO
 */