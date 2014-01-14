package co.com.multinivel.backend.service;

import java.util.List;

import co.com.multinivel.backend.model.Premio;
import co.com.multinivel.shared.exception.MultinivelServiceException;

public abstract interface PremioService {
	public abstract List<Premio> consultar(Premio paramPremio) throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.PremioService
 */