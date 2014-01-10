package co.com.multinivel.service;

import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.CantidadAfiliacionesDistribuidor;

public abstract interface CantidadAfiliacionesDistribuidorService {
	public abstract boolean ingresar(
			CantidadAfiliacionesDistribuidor paramCantidadAfiliacionesDistribuidor)
			throws MultinivelServiceException;

	public abstract CantidadAfiliacionesDistribuidor consultar(
			CantidadAfiliacionesDistribuidor paramCantidadAfiliacionesDistribuidor)
			throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name:
 * co.com.multinivel.service.CantidadAfiliacionesDistribuidorService
 * 
 * 
 */