package co.com.multinivel.backend.service;

import java.util.List;

import co.com.multinivel.backend.model.Parametro;
import co.com.multinivel.shared.exception.MultinivelServiceException;

public abstract interface ParametroService {
	public abstract List<Parametro> listar() throws MultinivelServiceException;

	public abstract Parametro obtenerValor(String paramString) throws MultinivelServiceException;

	public abstract boolean guardar(Parametro p) throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.ParametroService
 */