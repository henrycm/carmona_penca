package co.com.multinivel.backend.service;

import java.util.List;

import co.com.multinivel.backend.model.Departamento;
import co.com.multinivel.shared.exception.MultinivelServiceException;

public abstract interface DepartamentoService {
	public abstract List<Departamento> listar() throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.DepartamentoService
 */