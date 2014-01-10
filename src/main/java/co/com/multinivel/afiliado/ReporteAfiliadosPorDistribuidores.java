package co.com.multinivel.afiliado;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.service.AfiliadoService;
import co.com.multinivel.util.GenerarReporte;
import co.com.multinivel.util.RecursosEnum;

public class ReporteAfiliadosPorDistribuidores extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private AfiliadoService afiliadoService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		try {
			HashMap<Object, Object> map = new HashMap();

			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);
			String periodo = request.getParameter("periodo") == null ? cadenaFecha : request
					.getParameter("periodo");
			map.put("titulo", "COMPESACION AFIALIDO POR PERIODO");
			map.put("periodo", periodo);
			map.put("rutaImagenes", getServletContext().getInitParameter("rutaImagenes"));

			List<Object> lista = this.afiliadoService.listaAfiliadosPorDistribuidor(periodo);
			if ((lista != null) && (lista.size() > 0)) {
				String tipoReporte = request.getParameter("tipoReporte") == null ? "PDF" : request
						.getParameter("tipoReporte");
				if ("PDF".equals(tipoReporte)) {
					GenerarReporte.exportarPDF(request, response, getServletConfig()
							.getServletContext(), "numeroAfiliadosPorDistribuidor_" + periodo
							+ ".pdf", RecursosEnum.FW_JASPER_AFILIADOSXDISTRIBUIDORES.getRecurso(),
							map, lista);
				} else {
					GenerarReporte.exportarExcel(request, response, getServletConfig()
							.getServletContext(), "numeroAfiliadosPorDistribuidor_" + periodo
							+ ".xls", RecursosEnum.FW_JASPER_AFILIADOSXDISTRIBUIDORES.getRecurso(),
							map, lista);
				}
			} else {
				request.setAttribute("error",
						"no existen afiliaciones de los distribuidores para el periodo:" + periodo
								+ ".");
				rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_ERROR.getRecurso());
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_ERROR.getRecurso());
			rd.forward(request, response);
		}
	}
}
