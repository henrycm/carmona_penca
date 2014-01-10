package co.com.multinivel.helper;

import javax.servlet.http.HttpServletRequest;

import co.com.multinivel.model.GroupAuthority;

public class RolHelper {
	public static GroupAuthority cargarEntidad(HttpServletRequest request) {
		GroupAuthority rol = new GroupAuthority();
		rol.setGroupId(request.getParameter("codigo"));
		rol.setAuthority(request.getParameter("descripcion"));
		return rol;
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.helper.RolHelper
 * 
 * 
 */