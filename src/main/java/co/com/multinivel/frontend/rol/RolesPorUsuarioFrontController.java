package co.com.multinivel.frontend.rol;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.backend.model.GroupAuthority;
import co.com.multinivel.backend.model.GroupMember;
import co.com.multinivel.backend.model.User;
import co.com.multinivel.backend.service.RolService;
import co.com.multinivel.backend.service.UsuarioService;
import co.com.multinivel.shared.exception.MultinivelServiceException;
import co.com.multinivel.shared.util.RecursosEnum;

public class RolesPorUsuarioFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private RolService rolService;
	@Autowired
	private UsuarioService usuarioService;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = RecursosEnum.FW_ROL_POR_USUARIO.getRecurso();
		List<GroupAuthority> listaRoles = null;
		List<User> listaUsuarios = null;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter("accion").charAt(0);
			User usuario = null;
			GroupAuthority rol = null;
			GroupMember rolPorUsuario = null;
			switch (accion) {
			case 'I':
				usuario = this.usuarioService.consultar(request.getParameter("usuario"));
				rol = this.rolService.consultar(request.getParameter("rol"));
				rolPorUsuario = new GroupMember();
				rolPorUsuario.setUser(usuario);
				rolPorUsuario.setGroupAuthority(rol);
				this.rolService.guardarRolUsuario(rolPorUsuario);
				request.setAttribute("ingreso", Boolean.valueOf(true));
				request.setAttribute("listaRolesPorUsuario", this.rolService.rolesPorUsuario(usuario));
				request.setAttribute("usuario", usuario);
				break;
			case 'B':
				usuario = this.usuarioService.consultar(request.getParameter("usuario"));
				rol = this.rolService.consultar(request.getParameter("rol"));
				rolPorUsuario = new GroupMember();
				rolPorUsuario.setUser(usuario);
				rolPorUsuario.setGroupAuthority(rol);
				this.rolService.borrarRolUsuario(rolPorUsuario);
				request.setAttribute("retiro", Boolean.valueOf(true));
				request.setAttribute("usuario", usuario);
				request.setAttribute("listaRolesPorUsuario", this.rolService.rolesPorUsuario(usuario));

				break;
			case 'C':
				if (request.getParameter("usuario") != null) {
					usuario = this.usuarioService.consultar(request.getParameter("usuario"));

					request.setAttribute("usuario", usuario);
					request.setAttribute("listaRolesPorUsuario", this.rolService.rolesPorUsuario(usuario));
				}
				break;
			}
			listaRoles = this.rolService.listar();
			listaUsuarios = this.usuarioService.listar();
			request.setAttribute("listaUsuarios", listaUsuarios);
			request.setAttribute("listaRoles", listaRoles);
		} catch (MultinivelServiceException e) {
			request.setAttribute("mensajeError", e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.frontend.rol.RolesPorUsuarioFrontController
 */