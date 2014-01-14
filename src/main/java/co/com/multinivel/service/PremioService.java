package co.com.multinivel.service;

import java.util.List;

import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.Premio;

public abstract interface PremioService {
	public abstract List<Premio> consultar(Premio paramPremio) throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.service.PremioService
 */