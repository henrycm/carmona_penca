package co.com.multinivel.frontend;

import java.io.IOException;

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

public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(AjaxServlet.class);

	@Autowired
	private AfiliadoService afiliadoService;

	public AjaxServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		char accion = request.getParameter("accion") == null ? '*'
				: request.getParameter("accion").charAt(0);
		switch (accion) {
		case 'C':
			String st_cedula = request.getParameter("cedula");
			String respuesta = "N";
			if (st_cedula != null)
			{
				Afiliado a = null;
				try {
					a = afiliadoService.consultar(st_cedula);
				} catch (MultinivelServiceException e) {
					log.warn(e);
				}
				if (a != null)
					respuesta = "S";
			}
			response.getOutputStream().write(respuesta.getBytes());
			response.getOutputStream().close();
			break;
		}
	}
}
