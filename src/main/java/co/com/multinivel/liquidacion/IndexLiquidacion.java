package co.com.multinivel.liquidacion;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.service.AfiliadoService;
import co.com.multinivel.util.RecursosEnum;

public class IndexLiquidacion extends HttpServlet {
	@EJB
	private AfiliadoService afiliadoService;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String recurso = null;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter(
					"accion").charAt(0);
			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);

			request.setAttribute("periodo", cadenaFecha);
			switch (accion) {
			case 'M':
				recurso = RecursosEnum.FW_INDEX_LIQUIDACION.getRecurso();
				break;
			default:
				recurso = RecursosEnum.FW_INDEX_LIQUIDACION_RED.getRecurso();
				request.setAttribute("listaDistribuidores",
						this.afiliadoService.listarDistribuidores());
			}
			request.setAttribute("accion", request.getParameter("accion"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(recurso);
		rd.forward(request, response);
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.liquidacion.IndexLiquidacion
 * 
 * 
 */