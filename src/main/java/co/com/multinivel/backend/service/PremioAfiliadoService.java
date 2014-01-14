package co.com.multinivel.backend.service;

import java.util.List;

import co.com.multinivel.backend.model.PremioAfiliado;
import co.com.multinivel.shared.exception.MultinivelServiceException;

public abstract interface PremioAfiliadoService {
	public abstract List<Object> consultar(PremioAfiliado paramPremioAfiliado)
			throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.PremioAfiliadoService
 */