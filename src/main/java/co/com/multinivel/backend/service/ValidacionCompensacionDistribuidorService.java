package co.com.multinivel.backend.service;

import co.com.multinivel.backend.model.ValidacionCompensacionDistribuidor;
import co.com.multinivel.shared.exception.MultinivelServiceException;

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
 * co.com.multinivel.backend.service.ValidacionCompensacionDistribuidorService
 */