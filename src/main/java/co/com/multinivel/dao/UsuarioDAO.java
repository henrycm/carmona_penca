package co.com.multinivel.dao;

import java.util.List;

import co.com.multinivel.dto.UsuarioDTO;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.User;
import co.com.multinivel.util.Pagina;

public abstract interface UsuarioDAO {
	public abstract void ingresar(User paramUser) throws MultinivelDAOException;

	public abstract void actualizar(User paramUser) throws MultinivelDAOException;

	public abstract User consultar(String paramString) throws MultinivelDAOException;

	public abstract User consultar(User paramUser) throws MultinivelDAOException;

	public abstract void eliminar(User paramUser) throws MultinivelDAOException;

	public abstract List<User> listar() throws MultinivelDAOException;

	public abstract Pagina listarConDistribuidor(int Pagina) throws MultinivelDAOException;

	public abstract List<UsuarioDTO> buscar(String nomFiltro, String filtro)
			throws MultinivelDAOException;
}