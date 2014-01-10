package co.com.multinivel.compensacion;

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

import co.com.multinivel.dto.CompensacionAfiliadoDTO;
import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.model.Afiliado;
import co.com.multinivel.model.ValidacionCompensacionDistribuidor;
import co.com.multinivel.model.ValidacionCompensacionDistribuidorPK;
import co.com.multinivel.service.AfiliadoService;
import co.com.multinivel.service.CompensacionAfiliadoService;
import co.com.multinivel.service.ValidacionCompensacionDistribuidorService;
import co.com.multinivel.util.GenerarReporte;
import co.com.multinivel.util.RecursosEnum;

public class ReporteCompensacionDistribuidorPeriodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private AfiliadoService afiliadoService;
	@EJB
	private CompensacionAfiliadoService compensacionAfiliadoService;
	@EJB
	private ValidacionCompensacionDistribuidorService validacionCompensacionDistribuidorService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String documento = UsuarioHelper.getUsuario();
			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);
			String periodo = request.getParameter("periodo") == null ? cadenaFecha : request
					.getParameter("periodo");
			map.put("titulo", "PAGO DE COMPESACION AFILAIDOS POR PERIODO");
			map.put("periodo", periodo);
			map.put("rutaImagenes", getServletContext().getInitParameter("rutaImagenes"));
			CompensacionAfiliadoDTO compensacionAfiliadoDTO = new CompensacionAfiliadoDTO();
			compensacionAfiliadoDTO.setAfiliado(documento);
			compensacionAfiliadoDTO.setPeriodo(periodo);

			Afiliado distribuidor = this.afiliadoService.consultar(documento);
			ValidacionCompensacionDistribuidor vd = new ValidacionCompensacionDistribuidor();
			ValidacionCompensacionDistribuidorPK validacionCompensacionDistribuidorPK = new ValidacionCompensacionDistribuidorPK();
			validacionCompensacionDistribuidorPK.setDistribuidor(distribuidor.getCedula());
			validacionCompensacionDistribuidorPK.setPeriodo(periodo);
			vd.setId(validacionCompensacionDistribuidorPK);
			ValidacionCompensacionDistribuidor vd1 = this.validacionCompensacionDistribuidorService
					.consultar(vd);
			if (vd1 != null) {
				if (distribuidor != null) {
					List<Object> lista = this.compensacionAfiliadoService
							.listarPagoAfiliados(compensacionAfiliadoDTO);
					if ((lista != null) && (lista.size() > 0)) {
						if (distribuidor != null) {
							map.put("nombreDistribuidor", distribuidor.getNombre() + " "
									+ distribuidor.getApellido());
							map.put("cedulaDistribuidor", distribuidor.getCedula());
						}
						String tipoReporte = request.getParameter("tipoReporte") == null ? "PDF"
								: request.getParameter("tipoReporte");
						if ("PDF".equals(tipoReporte)) {
							GenerarReporte.exportarPDF(request, response, getServletConfig()
									.getServletContext(), "listaPago_CompensacionAfiliados_"
									+ documento + "_" + periodo + ".pdf",
									RecursosEnum.FW_JASPER_LISTA_COMPENSACION_DISTRIBUIDOR
											.getRecurso(), map, lista);
						} else {
							GenerarReporte.exportarExcel(request, response, getServletConfig()
									.getServletContext(), "listaPago_CompensacionAfiliados_"
									+ documento + "_" + periodo + ".xls",
									RecursosEnum.FW_JASPER_LISTA_COMPENSACION_DISTRIBUIDOR
											.getRecurso(), map, lista);
						}
					} else {
						request.setAttribute("error",
								"El distribuidor no tiene pago de compensacion a ningun afiliado para el periodo "
										+ periodo + ".");
						rd = getServletContext().getRequestDispatcher(
								RecursosEnum.FW_ERROR.getRecurso());
						rd.forward(request, response);
					}
				} else {
					request.setAttribute("error", "No se encuentra datos del distribuidor");
					rd = getServletContext().getRequestDispatcher(
							RecursosEnum.FW_ERROR.getRecurso());
					rd.forward(request, response);
				}
			} else {
				request.setAttribute("error",
						"No se ha realizado la verificacion de compensacion para el distribuidor en este periodo.");
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

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name:
 * co.com.multinivel.compensacion.ReporteCompensacionDistribuidorPeriodo
 * 
 * 
 */