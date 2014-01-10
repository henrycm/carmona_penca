package co.com.multinivel.compensacion;

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

public class IndexReporteCompensacion extends HttpServlet {
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
		try {
			char accion = request.getParameter("accion") == null ? 'B' : request.getParameter(
					"accion").charAt(0);
			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);

			String periodo = cadenaFecha;

			request.setAttribute("periodo", periodo);
			request.setAttribute("accion", Character.valueOf(accion));
			switch (accion) {
			case 'C':
				request.setAttribute("listaDistribuidores",
						this.afiliadoService.listarDistribuidores());
				recurso = RecursosEnum.FW_INDEX_REPORTE_COMPENSACION_RED.getRecurso();
				break;
			case 'D':
				recurso = RecursosEnum.FW_INDEX_REPORTE_COMPENSACION_DISTRIBUIDOR.getRecurso();
				break;
			case 'F':
				recurso = RecursosEnum.FW_INDEX_REPORTE_COMPENSACION_TOTAL_DISTRIBUIDOR
						.getRecurso();
				break;
			case 'E':
			default:
				recurso = RecursosEnum.FW_INDEX_REPORTE_COMPENSACION.getRecurso();
			}
			"C".equals(Character.valueOf(accion));
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
 * Qualified Name: co.com.multinivel.compensacion.IndexReporteCompensacion
 * 
 * 
 */