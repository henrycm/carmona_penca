package co.com.multinivel.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.dao.RolDAO;
import co.com.multinivel.backend.model.GroupAuthority;
import co.com.multinivel.backend.model.GroupMember;
import co.com.multinivel.backend.model.User;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
public class RolServiceImpl implements RolService {
	@EJB
	private RolDAO rolDAO;

	@EJB
	private UsuarioService usrService;

	public void actualizar(GroupAuthority rol) throws MultinivelServiceException {
		try {
			this.rolDAO.actualizar(rol);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public void actualizarMember(GroupMember mem) throws MultinivelServiceException {
		try {
			this.rolDAO.actualizarRolUsuario(mem);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public void borrar(GroupAuthority rol) throws MultinivelServiceException {
		try {
			this.rolDAO.eliminar(rol);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public GroupAuthority consultar(String codigo) throws MultinivelServiceException {
		try {
			return this.rolDAO.consultar(codigo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	@Transactional
	public List<GroupMember> consultarMiembroUsuario(String usuario)
			throws MultinivelServiceException {
		try {
			return new ArrayList<GroupMember>(usrService.consultar(usuario).getGroupMembers());
		} catch (Exception e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public void ingresar(GroupAuthority rol) throws MultinivelServiceException {
		try {
			this.rolDAO.ingresar(rol);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public List<GroupAuthority> listar() throws MultinivelServiceException {
		List<GroupAuthority> lista = null;
		try {
			lista = this.rolDAO.listar();
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<GroupAuthority> rolesPorUsuario(User usuario) throws MultinivelServiceException {
		List<GroupAuthority> lista = null;
		try {
			lista = this.rolDAO.rolesPorUsuario(usuario);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public void borrarRolUsuario(GroupMember rolUsuario) throws MultinivelServiceException {
		try {
			this.rolDAO.borrarRolUsuario(rolUsuario);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public boolean consultarAsociacionRolUsuario(User usuario, GroupAuthority rol)
			throws MultinivelServiceException {
		boolean retorno = Boolean.FALSE;
		try {
			retorno = this.rolDAO.consultarAsociacionRolUsuario(usuario, rol);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public void guardarRolUsuario(GroupMember rolUsuario) throws MultinivelServiceException {
		try {
			this.rolDAO.guardarRolUsuario(rolUsuario);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public void actualizarRolUsuario(GroupMember rolUsuario) throws MultinivelServiceException {
		try {
			this.rolDAO.actualizarRolUsuario(rolUsuario);
		} catch (MultinivelDAOException e) {
			e.printStackTrace();
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.RolServiceImpl
 */