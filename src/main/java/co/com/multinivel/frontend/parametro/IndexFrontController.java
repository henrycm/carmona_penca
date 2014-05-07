package co.com.multinivel.frontend.parametro;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.backend.model.Parametro;
import co.com.multinivel.backend.service.ParametroService;
import co.com.multinivel.shared.util.RecursosEnum;

/**
 * Servlet implementation class IndexParametro
 */
public class IndexFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ParametroService parametroService;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recurso = null;
		boolean grabadoExitoso = Boolean.FALSE;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter("accion").charAt(0);

			switch (accion) {
			case 'A':
				Parametro parametro = new Parametro();
				parametro.setNombreParametro(request.getParameter("nombre"));
				parametro.setDescripcion(request.getParameter("descripcion"));
				parametro.setValor(request.getParameter("valor"));

				grabadoExitoso = this.parametroService.guardar(parametro);
				break;
			}
			if (grabadoExitoso) {
				request.setAttribute("mensaje", "Parámetro " + request.getParameter("nombre") + " actializado satisfactoriamente.");
			} else {
				request.setAttribute("error", "No fue posible actializar el Parámetro. Verifique e intente nuevamente.");
			}
			request.setAttribute("listaParametros", this.parametroService.listar());
			request.setAttribute("accion", accion);

			recurso = RecursosEnum.FW_INDEX_PARAMETRO.getRecurso();
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(recurso);
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}
