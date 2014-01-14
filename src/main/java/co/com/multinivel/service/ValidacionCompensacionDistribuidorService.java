package co.com.multinivel.service;

import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.ValidacionCompensacionDistribuidor;

public abstract interface ValidacionCompensacionDistribuidorService {
	public abstract boolean ingresar(
			ValidacionCompensacionDistribuidor paramValidacionCompensacionDistribuidor)
			throws MultinivelServiceException;

	public abstract boolean eliminar(
			ValidacionCompensacionDistribuidor paramValidacionCompensacionDistribuidor)
			throws MultinivelServiceException;

	public abstract ValidacionCompensacionDistribuidor consultar(
			ValidacionCompensacionDistribuidor paramValidacionCompensacionDistribuidor)
			throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name:
 * co.com.multinivel.service.ValidacionCompensacionDistribuidorService
 */