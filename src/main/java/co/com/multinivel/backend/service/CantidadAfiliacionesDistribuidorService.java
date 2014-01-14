package co.com.multinivel.backend.service;

import co.com.multinivel.backend.model.CantidadAfiliacionesDistribuidor;
import co.com.multinivel.shared.exception.MultinivelServiceException;

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
 * co.com.multinivel.backend.service.CantidadAfiliacionesDistribuidorService
 */