package co.com.multinivel.service;

import java.util.List;

import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.PremioAfiliado;

public abstract interface PremioAfiliadoService {
	public abstract List<Object> consultar(PremioAfiliado paramPremioAfiliado)
			throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.service.PremioAfiliadoService
 */