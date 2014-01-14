package co.com.multinivel.afiliado;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.util.CorreoUtil;

public class PruebaCorreo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CorreoUtil.enviarCorreo("esto es una prueba",
				"probando correo para la aplicacion multialoe");
		PrintWriter out = response.getWriter();
		out.println("PROBANDO EL CORREO");
	}
}
