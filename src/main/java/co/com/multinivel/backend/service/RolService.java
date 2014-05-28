package co.com.multinivel.backend.service;

import java.util.List;

import co.com.multinivel.backend.model.GroupAuthority;
import co.com.multinivel.backend.model.GroupMember;
import co.com.multinivel.backend.model.User;
import co.com.multinivel.shared.exception.MultinivelServiceException;

public abstract interface RolService {
	public abstract void ingresar(GroupAuthority paramGroupAuthority)
			throws MultinivelServiceException;

	public abstract void actualizar(GroupAuthority paramGroupAuthority)
			throws MultinivelServiceException;

	public abstract void borrar(GroupAuthority paramGroupAuthority)
			throws MultinivelServiceException;

	public abstract GroupAuthority consultar(String paramString) throws MultinivelServiceException;

	public abstract List<GroupAuthority> listar() throws MultinivelServiceException;

	public abstract List<GroupAuthority> rolesPorUsuario(User paramUser)
			throws MultinivelServiceException;

	public abstract boolean consultarAsociacionRolUsuario(User paramUser,
			GroupAuthority paramGroupAuthority) throws MultinivelServiceException;

	public abstract void guardarRolUsuario(GroupMember paramGroupMember)
			throws MultinivelServiceException;

	public abstract void borrarRolUsuario(GroupMember paramGroupMember)
			throws MultinivelServiceException;

	public abstract void actualizarRolUsuario(GroupMember paramGroupMember)
			throws MultinivelServiceException;

	public abstract List<GroupMember> consultarMiembroUsuario(String usuario)
			throws MultinivelServiceException;

	public void actualizarMember(GroupMember mem) throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.RolService
 */