package co.com.multinivel.helper;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import co.com.multinivel.model.Afiliado;
import co.com.multinivel.model.User;

public class UsuarioHelper {
	public static User cargarEntidad(HttpServletRequest request) {
		User usuario = new User();
		usuario.setUsername(request.getParameter("usuario"));
		usuario.setPassword(request.getParameter("password"));
		byte activado = (request.getParameter("activado") == null)
				|| (!"N".equals(request.getParameter("activado"))) ? Byte.valueOf("0").byteValue()
				: Byte.valueOf("1").byteValue();
		usuario.setEnabled(activado);
		return usuario;
	}

	public static User cargarEntidad(Afiliado afiliado) {
		User usuario = new User();
		usuario.setUsername(afiliado.getCedula());
		usuario.setPassword(afiliado.getCedula() + "_aloe*");
		byte activado = Byte.valueOf("1").byteValue();
		usuario.setEnabled(activado);
		return usuario;
	}

	public static User cargarEntidad(Afiliado afiliado, HttpServletRequest request) {
		User usuario = new User();
		usuario.setUsername(afiliado.getCedula());
		if ("3".equals(request.getParameter("rol"))) {
			usuario.setPassword(afiliado.getCedula());
		} else {
			usuario.setPassword(afiliado.getCedula() + "_aloe*");
		}
		byte activado = Byte.valueOf("1").byteValue();
		usuario.setEnabled(activado);
		return usuario;
	}

	public static String getUsuario() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String usuario = null;
		if ((principal instanceof UserDetails)) {
			usuario = ((UserDetails) principal).getUsername();
		} else {
			usuario = principal.toString();
		}
		return usuario;
	}

	public static char getRol() {
		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		Collection< ? extends GrantedAuthority> roles = null;
		char roleRetornado = '3';		
		roles = principal.getAuthorities();
		
		if (roles != null) {
			Iterator< ? extends GrantedAuthority> listaRoles = roles.iterator();

			boolean bandera = true;
			while ((listaRoles.hasNext()) && (bandera)) {
				GrantedAuthority grantedAuthority = (GrantedAuthority) listaRoles.next();
				if ("ROLE_ADMIN".equals(grantedAuthority.getAuthority())) {
					return '1';
				}
				if ("ROLE_DISTRIBUIDOR".equals(grantedAuthority.getAuthority())) {
					roleRetornado = '2';
				}
			}
		}
		return roleRetornado;
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.helper.UsuarioHelper
 * 
 * 
 */