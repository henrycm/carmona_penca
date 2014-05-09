package co.com.multinivel.frontend.consumo;

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

import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.backend.service.ConsumoService;
import co.com.multinivel.shared.dto.ConsumoDTO;
import co.com.multinivel.shared.util.GenerarReporte;
import co.com.multinivel.shared.util.RecursosEnum;
import co.com.multinivel.shared.util.RutasUtil;

public class ReporteConsumosAfiliado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	ConsumoService consumoService;
	@Autowired
	AfiliadoService afiliadoService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String periodo = "";
		String tipoReporte = request.getParameter("tipoReporte") == null ? "PDF" : request.getParameter("tipoReporte");
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();

			String nombreEmpresario = request.getParameter("nombreEmpresario") == null ? "" : request.getParameter("nombreEmpresario");
			String apellidoEmpresario = request.getParameter("apellidoEmpresario") == null ? "" : request.getParameter("apellidoEmpresario");

			String mes = request.getParameter("mes");
			String ano = request.getParameter("ano");

			periodo = mes + "/" + ano;
			map.put("periodo", periodo);
			map.put("rutaImagenes", RutasUtil.getRutaImagenes(getServletContext()));
			ConsumoDTO consumo = new ConsumoDTO();
			consumo.setPeriodo(periodo);
			consumo.setNombreAfiliado(nombreEmpresario);
			consumo.setApellidoAfiliado(apellidoEmpresario);

			List<Object> lista = this.consumoService.listarConsumosAfiliado(consumo);
			if ((lista != null) && (lista.size() > 0)) {
				if ("PDF".equals(tipoReporte)) {
					GenerarReporte.exportarPDF(request, response, getServletConfig().getServletContext(), "Reporte_ListaConsumosAfiliado_"
							+ nombreEmpresario + ".pdf", RecursosEnum.FW_JASPER_REPORTE_LISTA_CONSUMOS_AFILIADO_NOMBRE.getRecurso(), map, lista);
				} else {
					GenerarReporte.exportarExcel(request, response, getServletConfig().getServletContext(), "Reporte_ListaConsumosAfiliado_"
							+ nombreEmpresario + ".xls", RecursosEnum.FW_JASPER_REPORTE_LISTA_CONSUMOS_AFILIADO_NOMBRE.getRecurso(), map, lista);
				}
			} else {
				request.setAttribute("error", "No existen consumos para el periodo solicitado:" + periodo + " o digite nombre y apellido nuevamente");
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
 * Qualified Name: co.com.multinivel.frontend.consumo.ReporteConsumosAfiliado
 */