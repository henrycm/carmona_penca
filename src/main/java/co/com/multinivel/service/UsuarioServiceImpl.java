package co.com.multinivel.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import co.com.multinivel.dao.UsuarioDAO;
import co.com.multinivel.dto.UsuarioDTO;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.User;
import co.com.multinivel.util.Pagina;

@Stateless
@Local({ UsuarioService.class })
public class UsuarioServiceImpl implements UsuarioService {
	@EJB
	private UsuarioDAO usuarioDAO;

	public void actualizar(User barrio) throws MultinivelServiceException {
		try {
			this.usuarioDAO.actualizar(barrio);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public void borrar(User barrio) throws MultinivelServiceException {
		try {
			this.usuarioDAO.eliminar(barrio);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public User consultar(String codigo) throws MultinivelServiceException {
		try {
			return this.usuarioDAO.consultar(codigo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public void ingresar(User barrio) throws MultinivelServiceException {
		try {
			this.usuarioDAO.ingresar(barrio);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public List<User> listar() throws MultinivelServiceException {
		List<User> lista = null;
		try {
			lista = this.usuarioDAO.listar();
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public User consultar(User usuario) throws MultinivelServiceException {
		try {
			return this.usuarioDAO.consultar(usuario);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public Pagina listarConDistribuidor(int pagina) throws MultinivelServiceException {
		Pagina lista = null;
		try {
			lista = this.usuarioDAO.listarConDistribuidor(pagina);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}
}
