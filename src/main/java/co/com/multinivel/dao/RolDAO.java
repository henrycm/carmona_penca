package co.com.multinivel.dao;

import java.util.List;

import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.GroupAuthority;
import co.com.multinivel.model.GroupMember;
import co.com.multinivel.model.User;

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
 * Qualified Name: co.com.multinivel.dao.RolDAO
 * 
 * 
 */