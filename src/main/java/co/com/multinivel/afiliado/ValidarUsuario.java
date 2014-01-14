package co.com.multinivel.afiliado;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.util.RecursosEnum;

public class ValidarUsuario extends HttpServlet {
	private static Logger log = Logger.getLogger(ValidarUsuario.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		char rol = UsuarioHelper.getRol();
		log.info("Rol: " + rol);
		String recurso = RecursosEnum.FW_INDEX.getRecurso();
		request.setAttribute("rol", "" + rol);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(recurso);
		rd.forward(request, response);
	}
}
