package co.com.multinivel.backend.service;

import java.util.List;

import co.com.multinivel.backend.model.Red;
import co.com.multinivel.shared.exception.MultinivelServiceException;

public abstract interface RedService {
	public abstract List<Red> listar() throws MultinivelServiceException;

	public abstract boolean ingresar(Red paramRed) throws MultinivelServiceException;

	public abstract Red consultar(String paramString) throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.RedService
 */