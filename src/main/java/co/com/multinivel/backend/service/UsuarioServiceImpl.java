package co.com.multinivel.backend.service;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.UsuarioDAO;
import co.com.multinivel.backend.model.GroupMember;
import co.com.multinivel.backend.model.User;
import co.com.multinivel.shared.dto.UsuarioDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;
import co.com.multinivel.shared.util.Pagina;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@EJB
	private UsuarioDAO usuarioDAO;

	@EJB
	private RolService rolSrv;

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

	public List<UsuarioDTO> buscar(String nomFiltro, String filtro)
			throws MultinivelServiceException {
		List<UsuarioDTO> lista = null;
		try {
			lista = this.usuarioDAO.buscar(nomFiltro, filtro);
			for (UsuarioDTO u : lista)
			{
				List<GroupMember> l = rolSrv.consultarMiembroUsuario(u.getUsername());
				for (GroupMember gm : l)
					u.setRol(gm.getGroupAuthority().getGroupId());
			}
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

}
