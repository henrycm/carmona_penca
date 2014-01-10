package co.com.multinivel.service;

import java.util.List;

import co.com.multinivel.dto.UsuarioDTO;
import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.User;

public abstract interface UsuarioService {
	public abstract void ingresar(User paramUser) throws MultinivelServiceException;

	public abstract void actualizar(User paramUser) throws MultinivelServiceException;

	public abstract void borrar(User paramUser) throws MultinivelServiceException;

	public abstract User consultar(String paramString) throws MultinivelServiceException;

	public abstract List<User> listar() throws MultinivelServiceException;

	public abstract User consultar(User paramUser) throws MultinivelServiceException;

	public abstract List<UsuarioDTO> listarConDistribuidor() throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.service.UsuarioService
 * 
 * 
 */