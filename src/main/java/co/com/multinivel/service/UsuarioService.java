package co.com.multinivel.service;

import java.util.List;

import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.User;
import co.com.multinivel.util.Pagina;

public abstract interface UsuarioService {
	public abstract void ingresar(User paramUser) throws MultinivelServiceException;

	public abstract void actualizar(User paramUser) throws MultinivelServiceException;

	public abstract void borrar(User paramUser) throws MultinivelServiceException;

	public abstract User consultar(String paramString) throws MultinivelServiceException;

	public abstract List<User> listar() throws MultinivelServiceException;

	public abstract User consultar(User paramUser) throws MultinivelServiceException;

	public abstract Pagina listarConDistribuidor(int pagina) throws MultinivelServiceException;
}
