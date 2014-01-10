package co.com.multinivel.arbol;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.service.AfiliadoService;
import co.com.multinivel.util.RecursosEnum;

public class IndexArbolController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private AfiliadoService afiliadoService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String recurso = null;
		String cedula = null;
		try {
			String accion = request.getParameter("accion") == null ? "*" : request
					.getParameter("accion");
			char rol = UsuarioHelper.getRol();
			if ((rol == '1') && ("*".equals(accion))) {
				recurso = RecursosEnum.FW_ARBOL_AFILIADO.getRecurso();
			} else {
				if (rol == '1') {
					cedula = request.getParameter("codigoEmpresario") + "-"
							+ request.getParameter("letra");
				} else {
					cedula = UsuarioHelper.getUsuario();
				}
				recurso = RecursosEnum.FW_GENERAR_ARBOL.getRecurso();
			}
			request.setAttribute("accion", request.getParameter("accion"));
			request.setAttribute("cedula", cedula);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			recurso = RecursosEnum.FW_INGRESO_AFILIADO_ERROR.getRecurso();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(recurso);
		rd.forward(request, response);
	}
}
