package co.com.multinivel.frontend.usuario;

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
import co.com.multinivel.shared.dto.UsuarioDTO;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.RecursosEnum;

public class UsuarioFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private RolService rolService;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter(
					"accion").charAt(0);
			User usuario = UsuarioHelper.cargarEntidad(request);
			List<UsuarioDTO> lista = null;
			GroupAuthority rol = null;
			GroupMember rolPorUsuario = null;
			switch (accion) {
			case 'A':
				User usuarioConsultado = this.usuarioService.consultar(usuario.getUsername());
				if (usuarioConsultado != null) {
					usuario.setGroupMembers(usuarioConsultado.getGroupMembers());
					//usuario.setEnabled(usuarioConsultado.getEnabled());
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
			case 'C':
				String nomFiltro = request.getParameter("nomFiltro");
				String filtro = request.getParameter("filtro");
				if (filtro != null && nomFiltro != null) {
					lista = this.usuarioService.buscar(nomFiltro, filtro);
					request.setAttribute("listaUsuarios", lista);
					System.out.println("LISTA>>>>>>" + lista.size());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_INDEX_USUARIO.getRecurso());

		rd.forward(request, response);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		doPost(req, resp);
	}
}
