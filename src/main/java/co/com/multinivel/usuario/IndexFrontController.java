package co.com.multinivel.usuario;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.service.RolService;
import co.com.multinivel.service.UsuarioService;
import co.com.multinivel.util.RecursosEnum;

public class IndexFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private RolService rolService;
	@EJB
	private UsuarioService usuarioService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				RecursosEnum.FW_INDEX_USUARIO.getRecurso());
		try {
			request.setAttribute("listaRoles", this.rolService.listar());
			request.setAttribute("listaUsuarios", this.usuarioService.listarConDistribuidor());
		} catch (MultinivelServiceException e) {
			e.printStackTrace();
		}
		rd.forward(request, response);
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.usuario.IndexFrontController
 * 
 * 
 */