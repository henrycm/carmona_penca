package co.com.multinivel.dao;

import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.ValidacionCompensacionDistribuidor;

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
 * Qualified Name: co.com.multinivel.dao.ValidarCompDistribuidorDAO
 * 
 * 
 */