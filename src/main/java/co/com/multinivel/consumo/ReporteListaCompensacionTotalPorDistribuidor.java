package co.com.multinivel.consumo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.dto.AfiliadoConsumo;
import co.com.multinivel.dto.AfiliadoDTO;
import co.com.multinivel.dto.ConsumoDTO;
import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.service.AfiliadoService;
import co.com.multinivel.service.CompensacionAfiliadoService;
import co.com.multinivel.util.GenerarReporte;
import co.com.multinivel.util.RecursosEnum;

public class ReporteListaCompensacionTotalPorDistribuidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	CompensacionAfiliadoService compensacionAfiliadoService;
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
			String distribuidor = request.getParameter("distribuidor") == null ? UsuarioHelper
					.getUsuario() : request.getParameter("distribuidor");
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);
			String mes = request.getParameter("mes");
			String ano = request.getParameter("ano");

			periodo = mes + "/" + ano;
			map.put("periodo", periodo);
			map.put("rutaImagenes", getServletContext().getInitParameter("rutaImagenes"));
			ConsumoDTO consumo = new ConsumoDTO();
			consumo.setPeriodo(periodo);
			consumo.setCedulaDistribuidor(distribuidor);
			List<Object> lista = new ArrayList();

			List<AfiliadoDTO> listaDistribuidores = this.afiliadoService.listarDistribuidores();
			AfiliadoConsumo afiliadoCompensacion = null;
			double totalProducto = 0.0D;
			double totalDinero = 0.0D;
			for (Iterator iterator = listaDistribuidores.iterator(); iterator.hasNext();) {
				AfiliadoDTO afiliadoDTO = (AfiliadoDTO) iterator.next();

				List<Object> afiliadoConsumo = this.compensacionAfiliadoService.consultar(
						afiliadoDTO.getCedula(), periodo);
				double consumoDistribuidorDinero = 0.0D;
				double consumoDistribuidorProducto = 0.0D;

				String nombreDistribuidor = afiliadoDTO.getNombre() + " "
						+ afiliadoDTO.getApellido();
				String cedulaDistribuidor = afiliadoDTO.getCedula();
				for (Iterator iterator2 = afiliadoConsumo.iterator(); iterator2.hasNext();) {
					AfiliadoConsumo totalAfiliadoConsumo = (AfiliadoConsumo) iterator2.next();
					consumoDistribuidorDinero += totalAfiliadoConsumo.getComisionDinero();
					consumoDistribuidorProducto += totalAfiliadoConsumo.getComisionProducto();
				}
				System.err.println("nombre:>>" + nombreDistribuidor + ":cedula:"
						+ cedulaDistribuidor + ":consumo:" + consumoDistribuidorDinero);
				if ((nombreDistribuidor != null) && (!"".equals(nombreDistribuidor))
						&& (consumoDistribuidorDinero > 0.0D)) {
					afiliadoCompensacion = new AfiliadoConsumo();
					afiliadoCompensacion.setNombre(nombreDistribuidor);
					afiliadoCompensacion.setAfiliado(cedulaDistribuidor);

					afiliadoCompensacion.setComisionDinero(consumoDistribuidorDinero);
					afiliadoCompensacion.setComisionProducto(consumoDistribuidorProducto);
					lista.add(afiliadoCompensacion);
					totalDinero += consumoDistribuidorDinero;
					totalProducto += consumoDistribuidorProducto;
				}
			}
			map.put("totalProducto", Double.valueOf(totalProducto));
			map.put("totalDinero", Double.valueOf(totalDinero));
			if ((lista != null) && (lista.size() > 0)) {
				if ("PDF".equals(tipoReporte)) {
					GenerarReporte.exportarPDF(request, response, getServletConfig()
							.getServletContext(), "Reporte_ListaCompensacionTotalDistribuidor_"
							+ periodo + ".pdf",
							RecursosEnum.FW_JASPER_REPORTE_LISTA_COMPENSACION_TOTAL_DISTRIBUIDOR
									.getRecurso(), map, lista);
				} else {
					GenerarReporte.exportarExcel(request, response, getServletConfig()
							.getServletContext(), "Reporte_ListaCompensacionTotalDistribuidor_"
							+ periodo + ".xls",
							RecursosEnum.FW_JASPER_REPORTE_LISTA_COMPENSACION_TOTAL_DISTRIBUIDOR
									.getRecurso(), map, lista);
				}
			} else {
				request.setAttribute("error", "No existe compensacion para el periodo solicitado:"
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
 * Qualified Name:
 * co.com.multinivel.consumo.ReporteListaCompensacionTotalPorDistribuidor
 * 
 * 
 */