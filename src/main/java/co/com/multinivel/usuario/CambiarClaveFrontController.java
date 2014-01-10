package co.com.multinivel.usuario;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.model.User;
import co.com.multinivel.service.RolService;
import co.com.multinivel.service.UsuarioService;
import co.com.multinivel.util.RecursosEnum;

public class CambiarClaveFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private UsuarioService usuarioService;
	@EJB
	private RolService rolService;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		try {
			rd = getServletContext().getRequestDispatcher(
					RecursosEnum.FW_CAMBIAR_CLAVE.getRecurso());
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter(
					"accion").charAt(0);

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
					request.setAttribute("mensaje",
							"La Clave se cambio exitosamente por favor haga clic en regresar");
				} else {
					request.setAttribute("error", "EL usuario no existe o la clave no coincide");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
 * Qualified Name: co.com.multinivel.usuario.CambiarClaveFrontController
 * 
 * 
 */