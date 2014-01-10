package co.com.multinivel.service;

import java.util.List;

import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.Departamento;

public abstract interface DepartamentoService {
	public abstract List<Departamento> listar() throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.service.DepartamentoService
 * 
 * 
 */