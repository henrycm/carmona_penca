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

import co.com.multinivel.backend.service.RolService;
import co.com.multinivel.backend.service.UsuarioService;
import co.com.multinivel.shared.exception.MultinivelServiceException;
import co.com.multinivel.shared.util.RecursosEnum;

public class IndexFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private RolService rolService;
	@Autowired
	private UsuarioService usuarioService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pagina = (Integer) (request.getParameter("numPagina") == null ? 1 : request.getParameter("numPagina"));
		RequestDispatcher rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_INDEX_USUARIO.getRecurso());
		try {
			request.setAttribute("listaRoles", this.rolService.listar());
			request.setAttribute("listaUsuarios", this.usuarioService.listarConDistribuidor(pagina));
		} catch (MultinivelServiceException e) {
			e.printStackTrace();
		}
		rd.forward(request, response);
	}
}
