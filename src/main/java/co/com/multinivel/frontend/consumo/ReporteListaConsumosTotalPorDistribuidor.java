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

import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.backend.service.ConsumoService;
import co.com.multinivel.shared.dto.ConsumoDTO;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.GenerarReporte;
import co.com.multinivel.shared.util.RecursosEnum;
import co.com.multinivel.shared.util.RutasUtil;

public class ReporteListaConsumosTotalPorDistribuidor extends HttpServlet {
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
			String distribuidor = request.getParameter("distribuidor") == null ? UsuarioHelper.getUsuario() : request.getParameter("distribuidor");
			String mes = request.getParameter("mes");
			String ano = request.getParameter("ano");

			periodo = mes + "/" + ano;
			map.put("periodo", periodo);
			map.put("rutaImagenes", RutasUtil.getRutaImagenes(getServletContext()));
			ConsumoDTO consumo = new ConsumoDTO();
			consumo.setPeriodo(periodo);
			consumo.setCedulaDistribuidor(distribuidor);
			Afiliado datosDistribuidor = this.afiliadoService.consultar(distribuidor);

			List<Object> lista = this.consumoService.calcularConsumosPeriodo(periodo, distribuidor);
			if ((lista != null) && (lista.size() > 0)) {
				map.put("distribuidor", datosDistribuidor.getCedula() + "  " + datosDistribuidor.getNombre() + " " + datosDistribuidor.getApellido());
				if ("PDF".equals(tipoReporte)) {
					GenerarReporte.exportarPDF(request, response, getServletConfig().getServletContext(), "Reporte_ListaConsumosTotalDistribuidor_"
							+ periodo + ".pdf", RecursosEnum.FW_JASPER_REPORTE_LISTA_CONSUMOS_TOTAL_DISTRIBUIDOR.getRecurso(), map, lista);
				} else {
					GenerarReporte.exportarExcel(request, response, getServletConfig().getServletContext(), "Reporte_ListaConsumosTotalDistribuidor_"
							+ periodo + ".xls", RecursosEnum.FW_JASPER_REPORTE_LISTA_CONSUMOS_TOTAL_DISTRIBUIDOR.getRecurso(), map, lista);
				}
			} else {
				request.setAttribute("error", "No existen consumos para el periodo solicitado:" + periodo);
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
 * Qualified Name:
 * co.com.multinivel.frontend.consumo.ReporteListaConsumosTotalPorDistribuidor
 */