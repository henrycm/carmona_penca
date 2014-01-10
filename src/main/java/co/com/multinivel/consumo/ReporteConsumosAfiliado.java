package co.com.multinivel.consumo;

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

import co.com.multinivel.dto.ConsumoDTO;
import co.com.multinivel.service.AfiliadoService;
import co.com.multinivel.service.ConsumoService;
import co.com.multinivel.util.GenerarReporte;
import co.com.multinivel.util.RecursosEnum;

public class ReporteConsumosAfiliado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	ConsumoService consumoService;
	@EJB
	AfiliadoService afiliadoService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String periodo = "";
		String tipoReporte = request.getParameter("tipoReporte") == null ? "PDF" : request
				.getParameter("tipoReporte");
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			Date fechaActual = new Date();

			System.err.println("h");

			String nombreEmpresario = request.getParameter("nombreEmpresario") == null ? ""
					: request.getParameter("nombreEmpresario");
			String apellidoEmpresario = request.getParameter("apellidoEmpresario") == null ? ""
					: request.getParameter("apellidoEmpresario");
			System.err.println("h2::" + nombreEmpresario);
			System.err.println("h2::" + apellidoEmpresario);

			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);
			String mes = request.getParameter("mes");
			String ano = request.getParameter("ano");
			System.err.println("h3");

			periodo = mes + "/" + ano;
			map.put("periodo", periodo);
			map.put("rutaImagenes", getServletContext().getInitParameter("rutaImagenes"));
			ConsumoDTO consumo = new ConsumoDTO();
			consumo.setPeriodo(periodo);
			consumo.setNombreAfiliado(nombreEmpresario);
			consumo.setApellidoAfiliado(apellidoEmpresario);
			System.err.println("h4");

			List<Object> lista = this.consumoService.listarConsumosAfiliado(consumo);
			System.err.println("h5:" + lista.size());
			if ((lista != null) && (lista.size() > 0)) {
				if ("PDF".equals(tipoReporte)) {
					GenerarReporte.exportarPDF(request, response, getServletConfig()
							.getServletContext(), "Reporte_ListaConsumosAfiliado_"
							+ nombreEmpresario + ".pdf",
							RecursosEnum.FW_JASPER_REPORTE_LISTA_CONSUMOS_AFILIADO_NOMBRE
									.getRecurso(), map, lista);
				} else {
					GenerarReporte.exportarExcel(request, response, getServletConfig()
							.getServletContext(), "Reporte_ListaConsumosAfiliado_"
							+ nombreEmpresario + ".xls",
							RecursosEnum.FW_JASPER_REPORTE_LISTA_CONSUMOS_AFILIADO_NOMBRE
									.getRecurso(), map, lista);
				}
			} else {
				request.setAttribute("error", "No existen consumos para el periodo solicitado:"
						+ periodo + " o digite nombre y apellido nuevamente");
				rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_ERROR.getRecurso());
				rd.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_ERROR.getRecurso());
			rd.forward(request, response);
		}
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.consumo.ReporteConsumosAfiliado
 * 
 * 
 */