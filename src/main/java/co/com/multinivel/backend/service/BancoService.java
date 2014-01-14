package co.com.multinivel.backend.service;

import java.util.List;

import co.com.multinivel.backend.model.Banco;
import co.com.multinivel.shared.exception.MultinivelServiceException;

public abstract interface BancoService {
	public abstract List<Banco> listar() throws MultinivelServiceException;

	public abstract Banco consultar(String paramString) throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.BancoService
 */