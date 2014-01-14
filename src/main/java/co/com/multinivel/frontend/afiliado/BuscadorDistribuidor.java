package co.com.multinivel.frontend.afiliado;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.shared.util.RecursosEnum;

public class BuscadorDistribuidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private AfiliadoService afiliadoService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String recurso = null;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter(
					"accion").charAt(0);

			recurso = RecursosEnum.FW_EMERGENTE_DISTRIBUIDOR.getRecurso();
			if (accion == '*') {
				request.setAttribute("listaDistribuidor",
						this.afiliadoService.listarDistribuidores());
			} else {
				request.setAttribute("documento", request.getParameter("codigoEmpresario"));
				request.setAttribute("listaDistribuidor", this.afiliadoService.buscarDistribuidor(
						request.getParameter("documento"), request.getParameter("nombre")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error listando los afiliados");
			recurso = RecursosEnum.FW_ERROR.getRecurso();
		}
		rd = getServletContext().getRequestDispatcher(recurso);

		rd.forward(request, response);
	}
}
