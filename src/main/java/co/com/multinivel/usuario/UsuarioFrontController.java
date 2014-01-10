package co.com.multinivel.usuario;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.dto.UsuarioDTO;
import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.model.GroupAuthority;
import co.com.multinivel.model.GroupMember;
import co.com.multinivel.model.User;
import co.com.multinivel.service.RolService;
import co.com.multinivel.service.UsuarioService;
import co.com.multinivel.util.RecursosEnum;

public class UsuarioFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private UsuarioService usuarioService;
	@EJB
	private RolService rolService;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter(
					"accion").charAt(0);
			User usuario = UsuarioHelper.cargarEntidad(request);
			List<UsuarioDTO> lista = null;
			List<GroupAuthority> listaRolList = null;
			GroupAuthority rol = null;
			GroupMember rolPorUsuario = null;
			switch (accion) {
			case 'A':
				User usuarioConsultado = this.usuarioService.consultar(usuario.getUsername());
				if (usuarioConsultado != null) {
					usuario.setGroupMembers(usuarioConsultado.getGroupMembers());
					usuario.setEnabled(usuarioConsultado.getEnabled());
					this.usuarioService.actualizar(usuario);
					this.usuarioService.actualizar(usuario);
				}
				request.setAttribute("actualizo", Boolean.valueOf(true));

				break;
			case 'I':
				this.usuarioService.ingresar(usuario);
				rol = this.rolService.consultar("1");
				if (rol != null) {
					rolPorUsuario = new GroupMember();
					rolPorUsuario.setUser(usuario);
					rolPorUsuario.setGroupAuthority(rol);
					this.rolService.guardarRolUsuario(rolPorUsuario);
				}
				request.setAttribute("ingreso", Boolean.valueOf(true));

				break;
			case 'B':
				this.usuarioService.borrar(usuario);
				rol = this.rolService.consultar(request.getParameter("rol"));
				rolPorUsuario = new GroupMember();
				rolPorUsuario.setUser(usuario);
				rolPorUsuario.setGroupAuthority(rol);
				this.rolService.borrarRolUsuario(rolPorUsuario);
				request.setAttribute("retiro", "true");
			}
			lista = this.usuarioService.listarConDistribuidor();
			System.err.println("LISTA>>>>>>" + lista.size());
			listaRolList = this.rolService.listar();
			request.setAttribute("listaUsuarios", lista);
			request.setAttribute("listaRoles", listaRolList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_INDEX_USUARIO.getRecurso());

		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		doPost(req, resp);
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.usuario.UsuarioFrontController
 * 
 * 
 */