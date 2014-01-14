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
import co.com.multinivel.backend.service.RolService;
import co.com.multinivel.shared.exception.MultinivelServiceException;
import co.com.multinivel.shared.helper.RolHelper;
import co.com.multinivel.shared.util.RecursosEnum;

public class RolFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private RolService rolService;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter(
					"accion").charAt(0);
			GroupAuthority rol = RolHelper.cargarEntidad(request);
			List<GroupAuthority> lista = null;
			switch (accion) {
			case 'A':
				this.rolService.actualizar(rol);
				request.setAttribute("actualizo", Boolean.valueOf(true));

				break;
			case 'I':
				this.rolService.ingresar(rol);
				request.setAttribute("ingreso", Boolean.valueOf(true));

				break;
			case 'B':
				this.rolService.borrar(rol);
				request.setAttribute("retiro", Boolean.valueOf(true));
			}
			lista = this.rolService.listar();
			request.setAttribute("listaRoles", lista);
		} catch (MultinivelServiceException e) {
			request.setAttribute("mensajeError", e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				RecursosEnum.FW_ROL.getRecurso());
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

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.frontend.rol.RolFrontController
 */