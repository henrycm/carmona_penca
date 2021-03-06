package co.com.multinivel.frontend.compensacion;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import co.com.multinivel.backend.model.ValidacionCompensacionDistribuidor;
import co.com.multinivel.backend.model.ValidacionCompensacionDistribuidorPK;
import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.backend.service.ConsumoService;
import co.com.multinivel.backend.service.ParametroService;
import co.com.multinivel.backend.service.ValidacionCompensacionDistribuidorService;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.GenerarReporte;
import co.com.multinivel.shared.util.RecursosEnum;
import co.com.multinivel.shared.util.RutasUtil;

public class ReporteCompensacionAfiliadosRedPeriodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private AfiliadoService afiliadoService;
	@Autowired
	private ConsumoService consumoService;
	@Autowired
	private ValidacionCompensacionDistribuidorService validacionCompensacionDistribuidorService;
	@Autowired
	private ParametroService parService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);
			String periodo = request.getParameter("periodo") == null ? cadenaFecha : request.getParameter("periodo");
			String st_detalle = request.getParameter("detalle") == null ? "false" : request.getParameter("detalle");
			boolean imp_detalle = Boolean.parseBoolean(st_detalle);
			String distribuidor = request.getParameter("distribuidor") == null ? UsuarioHelper.getUsuario() : request.getParameter("distribuidor");

			map.put("titulo", "PAGO DE COMPESACION AFILAIDOS POR PERIODO");
			map.put("periodo", periodo);
			map.put("rutaImagenes", RutasUtil.getRutaImagenes(getServletContext()));
			Afiliado dist = this.afiliadoService.consultar(distribuidor);
			ValidacionCompensacionDistribuidor vd = new ValidacionCompensacionDistribuidor();
			ValidacionCompensacionDistribuidorPK validacionCompensacionDistribuidorPK = new ValidacionCompensacionDistribuidorPK();
			validacionCompensacionDistribuidorPK.setDistribuidor(distribuidor);
			validacionCompensacionDistribuidorPK.setPeriodo(periodo);
			vd.setId(validacionCompensacionDistribuidorPK);
			ValidacionCompensacionDistribuidor vd1 = this.validacionCompensacionDistribuidorService.consultar(vd);
			if (vd1 != null) {
				if (dist != null) {
					List<Object> lista = this.consumoService.listarConsumosRed(dist, periodo);
					if ((lista != null) && (lista.size() > 0)) {
						double par_min = Double.parseDouble(parService.obtenerValor("CONSUMO_MINIMO_ABRIR_RED").getValor());
						map.put("CONSUMO_MINIMO_ABRIR_RED", par_min);
						map.put("IMPRIMIR_DETALLE", imp_detalle);
						map.put("distribuidor", dist.getCedula() + "  " + dist.getNombre() + " " + dist.getApellido());

						String tipoReporte = request.getParameter("tipoReporte") == null ? "PDF" : request.getParameter("tipoReporte");
						if ("PDF".equals(tipoReporte)) {
							GenerarReporte.exportarPDF(request, response, getServletConfig().getServletContext(), "lista_compensacion_"
									+ distribuidor + "_" + periodo + ".pdf", RecursosEnum.FW_JASPER_LISTA_COMPENSACION_RED.getRecurso(), map, lista);
						} else {
							GenerarReporte.exportarExcel(request, response, getServletConfig().getServletContext(), "lista_compensacion_"
									+ distribuidor + "_" + periodo + ".xls", RecursosEnum.FW_JASPER_LISTA_COMPENSACION_RED.getRecurso(), map, lista);
						}
					} else {
						request.setAttribute("error", "El distribuidor no tiene pago de compensacion a ningun afiliado para el periodo " + periodo
								+ ".");
						rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_ERROR.getRecurso());
						rd.forward(request, response);
					}
				} else {
					request.setAttribute("error", "No se encuentra datos del distribuidor");
					rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_ERROR.getRecurso());
					rd.forward(request, response);
				}
			} else {
				request.setAttribute("error", "No se ha realizado la verificacion de compensacion para el distribuidor en este periodo.");
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
 * co.com.multinivel.frontend.compensacion.ReporteCompensacionAfiliadosRedPeriodo
 */