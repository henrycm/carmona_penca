package co.com.multinivel.afiliado;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.dto.AfiliadoDTO;
import co.com.multinivel.service.AfiliadoService;
import co.com.multinivel.util.GenerarReporte;
import co.com.multinivel.util.RecursosEnum;
import co.com.multinivel.util.RutasUtil;

public class ReporteListaAfiliadoPorNivel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private AfiliadoService afiliadoService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HashMap<String, Object> mapParametrosReporte = new HashMap<String, Object>();
			List<Object> listaAfiliados = null;
			listaAfiliados = this.afiliadoService.listarPorNivel(request.getParameter("red"));
			mapParametrosReporte.put("totalAfiliados", Integer.valueOf(listaAfiliados.size()));
			if ((listaAfiliados != null) && (listaAfiliados.size() > 0)) {
				List<AfiliadoDTO> distribuidores = this.afiliadoService.buscarDistribuidor(
						request.getParameter("red"), null);
				AfiliadoDTO afiliado = null;
				if (distribuidores != null) {
					afiliado = (AfiliadoDTO) distribuidores.get(0);
				}
				mapParametrosReporte.put(
						"distribuidor",
						afiliado.getCedula() + " " + afiliado.getNombre() + " "
								+ afiliado.getApellido());

				mapParametrosReporte.put("rutaImagenes",
						RutasUtil.getRutaImagenes(getServletContext()));
				mapParametrosReporte.put("tituloCompania", "ALOE DE COLOMBIA");
				String formato = request.getParameter("formato") == null ? "P" : request
						.getParameter("formato");
				if ("P".equals(formato)) {
					GenerarReporte.exportarPDF(request, response, getServletConfig()
							.getServletContext(), "Reporte_ListaAfiliadosPorNivel.pdf",
							RecursosEnum.FW_JASPER_LISTA_POR_NIVEL.getRecurso(),
							mapParametrosReporte, listaAfiliados);
				} else {
					GenerarReporte.exportarExcel(request, response, getServletConfig()
							.getServletContext(), "Reporte_ListaAfiliadosPorNivel.xls",
							RecursosEnum.FW_JASPER_LISTA_POR_NIVEL.getRecurso(),
							mapParametrosReporte, listaAfiliados);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}