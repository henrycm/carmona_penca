package co.com.multinivel.frontend.pedido;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.backend.service.ProductoService;
import co.com.multinivel.shared.util.GenerarReporte;
import co.com.multinivel.shared.util.RecursosEnum;
import co.com.multinivel.shared.util.RutasUtil;

public class ReportePorcentajeProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	ProductoService productoService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String periodo = "";
		String tipoReporte = request.getParameter("tipoReporte") == null ? "PDF"
				: request.getParameter("tipoReporte");
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String mes = request.getParameter("mes");
			String ano = request.getParameter("ano");
			periodo = mes + "/" + ano;
			map.put("periodo", periodo);
			map.put("rutaImagenes",
					RutasUtil.getRutaImagenes(getServletContext()));
			List<Object> lista = this.productoService
					.listarProductoPorcentaje(periodo);
			if ((lista != null) && (lista.size() > 0)) {
				if ("PDF".equals(tipoReporte)) {
					GenerarReporte.exportarHTML(request, response,
							getServletConfig().getServletContext(),
							"Reporte_PorcentajeProducto.pdf",
							RecursosEnum.FW_JASPER_REPORTE_PDTO_PORC_PERIODO
									.getRecurso(), map, lista);
				} else {
					GenerarReporte.exportarExcel(request, response,
							getServletConfig().getServletContext(),
							"Reporte_PorcentajeProducto.xls",
							RecursosEnum.FW_JASPER_REPORTE_PDTO_PORC_PERIODO
									.getRecurso(), map, lista);
				}
			} else {
				request.setAttribute("error",
						"No existen datos para el periodo solicitado o no se ha liquidado:"
								+ periodo);
				rd = getServletContext().getRequestDispatcher(
						RecursosEnum.FW_ERROR.getRecurso());
				rd.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			rd = getServletContext().getRequestDispatcher(
					RecursosEnum.FW_ERROR.getRecurso());
			rd.forward(request, response);
		}
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.frontend.pedido.ReportePorcentajeProducto
 */