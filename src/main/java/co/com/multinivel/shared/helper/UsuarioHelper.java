package co.com.multinivel.shared.helper;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.backend.model.User;

public class UsuarioHelper {
	public static User cargarEntidad(HttpServletRequest request) {
		User usuario = new User();
		usuario.setUsername(request.getParameter("usuario"));
		usuario.setPassword(request.getParameter("password"));
		byte activado = (request.getParameter("activado") == null)
				|| ("0".equals(request.getParameter("activado"))) ? Byte.valueOf("0").byteValue()
				: Byte.valueOf("1").byteValue();
		usuario.setEnabled(activado);
		return usuario;
	}

	public static User cargarEntidad(Afiliado afiliado) {
		User usuario = new User();
		usuario.setUsername(afiliado.getCedula());
		usuario.setPassword("Aloe*" + afiliado.getCedula() + "*");
		byte activado = Byte.valueOf("1").byteValue();
		usuario.setEnabled(activado);
		return usuario;
	}

	public static User cargarEntidad(Afiliado afiliado, HttpServletRequest request) {
		User usuario = new User();
		usuario.setUsername(afiliado.getCedula());
		if ("3".equals(request.getParameter("tipoAfiliado"))) {
			usuario.setPassword(afiliado.getCedula());
		} else {
			usuario.setPassword("Aloe*" + afiliado.getCedula() + "*");
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
		Collection<? extends GrantedAuthority> roles = null;
		char roleRetornado = '3';
		roles = principal.getAuthorities();

		if (roles != null) {
			Iterator<? extends GrantedAuthority> listaRoles = roles.iterator();

			boolean bandera = Boolean.TRUE;
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

	public static String getNombreRol() {
		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> roles = principal.getAuthorities();

		if (roles != null) {
			Iterator<? extends GrantedAuthority> listaRoles = roles.iterator();

			while (listaRoles.hasNext()) {
				GrantedAuthority grantedAuthority = (GrantedAuthority) listaRoles.next();
				return grantedAuthority.getAuthority();
			}
		}

		return null;
	}
}
