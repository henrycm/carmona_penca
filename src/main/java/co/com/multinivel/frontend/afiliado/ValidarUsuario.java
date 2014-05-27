package co.com.multinivel.frontend.afiliado;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.shared.exception.MultinivelServiceException;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.RecursosEnum;

public class ValidarUsuario extends HttpServlet {
	private static Logger log = Logger.getLogger(ValidarUsuario.class);
	private static final long serialVersionUID = 1L;
	@Autowired
	private AfiliadoService afiliadoService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Afiliado usuario = null;
		String nombreUsuario = "";
		char rol = UsuarioHelper.getRol();
		String recurso = RecursosEnum.FW_INDEX.getRecurso();
		request.setAttribute("rol", "" + rol);
		try {
			if (!"administrador".equalsIgnoreCase(UsuarioHelper.getUsuario().trim())) {
				usuario = afiliadoService.consultar(UsuarioHelper.getUsuario());
				nombreUsuario = " - " + usuario.getNombre().trim() + " " + usuario.getApellido().trim();
			}
		} catch (MultinivelServiceException e) {
			log.info(e.getMessage());
		}
		request.getSession().setAttribute("nombreUsuario", nombreUsuario);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(recurso);
		rd.forward(request, response);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}
