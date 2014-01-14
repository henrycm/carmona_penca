package co.com.multinivel.service;

import java.util.List;

import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.Parametro;

public abstract interface ParametroService {
	public abstract List<Parametro> listar() throws MultinivelServiceException;

	public abstract Parametro obtenerValor(String paramString) throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.service.ParametroService
 */