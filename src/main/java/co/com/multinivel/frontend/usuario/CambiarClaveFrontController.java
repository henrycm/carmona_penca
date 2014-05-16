package co.com.multinivel.frontend.usuario;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.backend.model.User;
import co.com.multinivel.backend.service.RolService;
import co.com.multinivel.backend.service.UsuarioService;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.RecursosEnum;

public class CambiarClaveFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private RolService rolService;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		try {
			rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_CAMBIAR_CLAVE.getRecurso());
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter("accion").charAt(0);

			User usuario = UsuarioHelper.cargarEntidad(request);
			User usuarioConsultado = this.usuarioService.consultar(usuario);
			request.setAttribute("mensaje", "");
			request.setAttribute("error", "");
			if (accion != '*') {
				if (usuarioConsultado != null) {
					usuario.setGroupMembers(usuarioConsultado.getGroupMembers());
					usuario.setEnabled(usuarioConsultado.getEnabled());
					usuario.setPassword(request.getParameter("passwordNuevo"));
					this.usuarioService.actualizar(usuario);
					request.setAttribute("mensaje", "La Clave se cambio exitosamente.");
				} else {
					request.setAttribute("error", "EL usuario no existe o la clave no coincide");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
 * Qualified Name:
 * co.com.multinivel.frontend.usuario.CambiarClaveFrontController
 */