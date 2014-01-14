package co.com.multinivel.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import co.com.multinivel.backend.model.GroupAuthority;
import co.com.multinivel.backend.model.GroupMember;
import co.com.multinivel.backend.model.User;
import co.com.multinivel.shared.exception.MultinivelDAOException;

@Component
public class RolDAOImp implements RolDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public GroupAuthority consultar(String codigo) throws MultinivelDAOException {
		return (GroupAuthority) this.entityManager.find(GroupAuthority.class, codigo);
	}

	public void eliminar(GroupAuthority rol) throws MultinivelDAOException {
		try {
			GroupAuthority rolConsultado = consultar(rol.getGroupId());
			if (rolConsultado != null) {
				this.entityManager.remove(rolConsultado);
			}
		} catch (Exception e) {
			throw new MultinivelDAOException();
		}
	}

	public void ingresar(GroupAuthority rol) throws MultinivelDAOException {
		try {
			GroupAuthority rolConsultado = consultar(rol.getGroupId());
			if (rolConsultado == null) {
				this.entityManager.persist(rol);
			}
		} catch (Exception e) {
			throw new MultinivelDAOException();
		}
	}

	public void actualizar(GroupAuthority rol) throws MultinivelDAOException {
		try {
			this.entityManager.merge(rol);
		} catch (Exception e) {
			throw new MultinivelDAOException();
		}
	}

	public List<GroupAuthority> listar() throws MultinivelDAOException {
		Query query = this.entityManager.createQuery("from GroupAuthority");
		List<GroupAuthority> lista = query.getResultList();
		return lista;
	}

	public List<GroupAuthority> rolesPorUsuario(User usuario) throws MultinivelDAOException {
		Query query = this.entityManager
				.createQuery(" select g.groupAuthority from GroupMember g where g.user.username=?");
		query.setParameter(1, usuario.getUsername());
		List<GroupAuthority> lista = query.getResultList();
		return lista;
	}

	public boolean consultarAsociacionRolUsuario(User usuario, GroupAuthority rol)
			throws MultinivelDAOException {
		boolean retorno = true;
		Query query = this.entityManager
				.createQuery(" select g from GroupMember g where g.user.username=? and g.groupAuthority.id=?");
		query.setParameter(1, usuario.getUsername());
		query.setParameter(2, rol.getGroupId());
		List<GroupMember> lista = query.getResultList();
		if (lista.isEmpty()) {
			retorno = false;
		}
		return retorno;
	}

	public void guardarRolUsuario(GroupMember rolUsuario) throws MultinivelDAOException {
		try {
			Boolean rolConsultado = Boolean.valueOf(consultarAsociacionRolUsuario(
					rolUsuario.getUser(), rolUsuario.getGroupAuthority()));
			if (!rolConsultado.booleanValue()) {
				rolUsuario.setId(rolUsuario.getUser().getUsername());
				this.entityManager.persist(rolUsuario);
			}
		} catch (Exception e) {
			throw new MultinivelDAOException();
		}
	}

	public void borrarRolUsuario(GroupMember rolUsuario) throws MultinivelDAOException {
		try {
			GroupMember rolConsultado = consultarAsociacionRolUsuarioABorrar(rolUsuario.getUser());
			if (rolConsultado != null) {
				this.entityManager.remove(rolConsultado);
			}
			this.entityManager.flush();
		} catch (Exception e) {
			throw new MultinivelDAOException();
		}
	}

	public void actualizarRolUsuario(GroupMember rolUsuario) throws MultinivelDAOException {
		try {
			GroupMember rolConsultado = consultarAsociacionRolUsuarioABorrar(rolUsuario.getUser());
			if (rolConsultado != null) {
				Query query = this.entityManager
						.createNativeQuery(" UPDATE group_members set group_id=? where id=?");
				query.setParameter(1, rolUsuario.getGroupAuthority().getGroupId());
				query.setParameter(2, rolUsuario.getUser().getUsername());
				query.executeUpdate();
			}
			this.entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException();
		}
	}

	public GroupMember consultarAsociacionRolUsuarioABorrar(User usuario)
			throws MultinivelDAOException {
		GroupMember retorno = null;
		Query query = this.entityManager
				.createNativeQuery(" SELECT id,group_id,username FROM group_members where id=?");
		query.setParameter(1, usuario.getUsername());

		List result = query.getResultList();
		int s = result.size();
		for (int i = 0; i < s; i++) {
			Object obj = result.get(i);
			Object[] objectArray = (Object[]) obj;
			String id = (String) objectArray[0];
			String idGrupo = (String) objectArray[1];
			String login = (String) objectArray[2];

			retorno = new GroupMember();

			retorno.setId(idGrupo);
		}
		return retorno;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.RolDAOImp
 */