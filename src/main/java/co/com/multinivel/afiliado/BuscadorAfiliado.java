package co.com.multinivel.afiliado;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.service.AfiliadoService;
import co.com.multinivel.util.RecursosEnum;

public class BuscadorAfiliado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private AfiliadoService afiliadoService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String recurso = null;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter(
					"accion").charAt(0);

			recurso = RecursosEnum.FW_EMERGENTE_AFILIADO.getRecurso();
			switch (accion) {
			case 'C':
				if (UsuarioHelper.getRol() == '2') {
					request.setAttribute(
							"listaAfiliados",
							this.afiliadoService.buscar(request.getParameter("documento"),
									request.getParameter("nombre"), UsuarioHelper.getUsuario()));
				} else {
					request.setAttribute(
							"listaAfiliados",
							this.afiliadoService.buscar(request.getParameter("documento"),
									request.getParameter("nombre"),
									request.getParameter("distribuidor")));
				}
				recurso = RecursosEnum.FW_EMERGENTE_AFILIADO.getRecurso();
				break;
			case 'P':
				if (UsuarioHelper.getRol() == '2') {
					request.setAttribute(
							"listaAfiliados",
							this.afiliadoService.buscar(request.getParameter("documento"),
									request.getParameter("nombre"), UsuarioHelper.getUsuario()));
				} else {
					request.setAttribute(
							"listaAfiliados",
							this.afiliadoService.buscar(request.getParameter("documento"),
									request.getParameter("nombre"),
									request.getParameter("distribuidor")));
				}
				recurso = RecursosEnum.FW_BUSCAR_AFILIADO_PEDIDO.getRecurso();
				break;
			case 'A':
				Date fechaActual = new Date();
				SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
				String cadenaFecha = formato.format(fechaActual);

				String periodo = cadenaFecha;
				request.setAttribute("distribuidor", request.getParameter("distribuidor"));

				request.setAttribute("periodo", periodo);
				request.setAttribute(
						"listaAfiliados",
						this.afiliadoService.buscar(request.getParameter("documento"),
								request.getParameter("nombre"), null));
				recurso = RecursosEnum.FW_BUSCAR_AFILIADO_CONSUMO.getRecurso();
			}
			request.setAttribute("documento", request.getParameter("documento"));
			request.setAttribute("nombre", request.getParameter("nombre"));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error listando los afiliados");
			recurso = RecursosEnum.FW_ERROR.getRecurso();
		}
		rd = getServletContext().getRequestDispatcher(recurso);

		rd.forward(request, response);
	}
}
