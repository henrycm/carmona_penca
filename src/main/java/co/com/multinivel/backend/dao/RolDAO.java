package co.com.multinivel.backend.dao;

import java.util.List;

import co.com.multinivel.backend.model.GroupAuthority;
import co.com.multinivel.backend.model.GroupMember;
import co.com.multinivel.backend.model.User;
import co.com.multinivel.shared.exception.MultinivelDAOException;

public abstract interface RolDAO {
	public abstract void ingresar(GroupAuthority paramGroupAuthority) throws MultinivelDAOException;

	public abstract void actualizar(GroupAuthority paramGroupAuthority)
			throws MultinivelDAOException;

	public abstract GroupAuthority consultar(String paramString) throws MultinivelDAOException;

	public abstract void eliminar(GroupAuthority paramGroupAuthority) throws MultinivelDAOException;

	public abstract List<GroupAuthority> listar() throws MultinivelDAOException;

	public abstract List<GroupAuthority> rolesPorUsuario(User paramUser)
			throws MultinivelDAOException;

	public abstract boolean consultarAsociacionRolUsuario(User paramUser,
			GroupAuthority paramGroupAuthority) throws MultinivelDAOException;

	public abstract void guardarRolUsuario(GroupMember paramGroupMember)
			throws MultinivelDAOException;

	public abstract void borrarRolUsuario(GroupMember paramGroupMember)
			throws MultinivelDAOException;

	public abstract void actualizarRolUsuario(GroupMember paramGroupMember)
			throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.RolDAO
 */