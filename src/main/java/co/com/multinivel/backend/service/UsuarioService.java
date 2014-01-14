package co.com.multinivel.backend.service;

import java.util.List;

import co.com.multinivel.backend.model.User;
import co.com.multinivel.shared.dto.UsuarioDTO;
import co.com.multinivel.shared.exception.MultinivelServiceException;
import co.com.multinivel.shared.util.Pagina;

public abstract interface UsuarioService {
	public abstract void ingresar(User paramUser) throws MultinivelServiceException;

	public abstract void actualizar(User paramUser) throws MultinivelServiceException;

	public abstract void borrar(User paramUser) throws MultinivelServiceException;

	public abstract User consultar(String paramString) throws MultinivelServiceException;

	public abstract List<User> listar() throws MultinivelServiceException;

	public abstract User consultar(User paramUser) throws MultinivelServiceException;

	public abstract Pagina listarConDistribuidor(int pagina) throws MultinivelServiceException;

	public abstract List<UsuarioDTO> buscar(String nomFiltro, String filtro)
			throws MultinivelServiceException;
}
