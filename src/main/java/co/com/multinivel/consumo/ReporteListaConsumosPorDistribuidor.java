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
import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.model.Afiliado;
import co.com.multinivel.service.AfiliadoService;
import co.com.multinivel.service.ConsumoService;
import co.com.multinivel.util.GenerarReporte;
import co.com.multinivel.util.RecursosEnum;
import co.com.multinivel.util.RutasUtil;

public class ReporteListaConsumosPorDistribuidor extends HttpServlet {
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
			String cedula = UsuarioHelper.getUsuario();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);
			String mes = request.getParameter("mes");
			String ano = request.getParameter("ano");
			String recurso = RecursosEnum.FW_JASPER_REPORTE_LISTA_CONSUMOS_DISTRIBUIDOR
					.getRecurso();

			periodo = mes + "/" + ano;
			map.put("periodo", periodo);
			map.put("rutaImagenes", RutasUtil.getRutaImagenes(getServletContext()));
			ConsumoDTO consumo = new ConsumoDTO();
			consumo.setPeriodo(periodo);
			if (UsuarioHelper.getRol() == '2') {
				consumo.setCedulaDistribuidor(cedula);
			} else {
				consumo.setCedulaAfiliado(cedula);
				recurso = RecursosEnum.FW_JASPER_REPORTE_LISTA_CONSUMOS_AFILIADO.getRecurso();
			}
			Afiliado datosPersona = this.afiliadoService.consultar(cedula);

			List<Object> lista = this.consumoService.listarConsumosPeriodo(consumo);
			if ((lista != null) && (lista.size() > 0)) {
				map.put("distribuidor", datosPersona.getCedula() + "  " + datosPersona.getNombre()
						+ " " + datosPersona.getApellido());
				if ("PDF".equals(tipoReporte)) {
					GenerarReporte.exportarPDF(request, response, getServletConfig()
							.getServletContext(), "Reporte_ListaConsumosDistribuidor_" + periodo
							+ ".pdf", recurso, map, lista);
				} else {
					GenerarReporte.exportarExcel(request, response, getServletConfig()
							.getServletContext(), "Reporte_ListaConsumosDistribuidor_" + periodo
							+ ".xls", recurso,

					map, lista);
				}
			} else {
				request.setAttribute("error", "No existen consumos para el periodo solicitado:"
						+ periodo);
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
 * Qualified Name: co.com.multinivel.consumo.ReporteListaConsumosPorDistribuidor
 * 
 * 
 */