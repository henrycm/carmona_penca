package co.com.multinivel.frontend.liquidacion;

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
import co.com.multinivel.backend.service.CompensacionAfiliadoService;
import co.com.multinivel.backend.service.ParametroService;
import co.com.multinivel.backend.service.ValidacionCompensacionDistribuidorService;
import co.com.multinivel.shared.util.GenerarReporte;
import co.com.multinivel.shared.util.RecursosEnum;
import co.com.multinivel.shared.util.RutasUtil;

public class ReporteCompensacionAfiliadoPeriodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private AfiliadoService afiliadoService;
	@Autowired
	private CompensacionAfiliadoService compensacionAfiliadoService;
	@Autowired
	private ParametroService parametroService;
	@Autowired
	private ValidacionCompensacionDistribuidorService validacionCompensacionDistribuidorService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String cedula = request.getParameter("documento") == null ? "0" : request.getParameter("documento");
			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);
			String periodo = request.getParameter("periodo") == null ? cadenaFecha : request.getParameter("periodo");
			map.put("titulo", "COMPESACION AFIALIDO POR PERIODO");
			map.put("periodo", periodo);
			map.put("rutaImagenes", RutasUtil.getRutaImagenes(getServletContext()));

			Afiliado afiliado = this.afiliadoService.consultar(cedula);
			Afiliado distribuidor = this.afiliadoService.consultar(afiliado.getCedulaDistribuidorPago());

			ValidacionCompensacionDistribuidor vd = new ValidacionCompensacionDistribuidor();
			ValidacionCompensacionDistribuidorPK validacionCompensacionDistribuidorPK = new ValidacionCompensacionDistribuidorPK();
			validacionCompensacionDistribuidorPK.setDistribuidor(distribuidor.getCedula());
			validacionCompensacionDistribuidorPK.setPeriodo(periodo);
			vd.setId(validacionCompensacionDistribuidorPK);
			ValidacionCompensacionDistribuidor vd1 = this.validacionCompensacionDistribuidorService.consultar(vd);
			if (vd1 != null) {
				if (afiliado != null) {
					List<Object> lista = this.compensacionAfiliadoService.comisionAfiliadoPeriodo(periodo, cedula);
					if ((lista != null) && (lista.size() > 0)) {
						map.put("nombreAfiliado", afiliado.getNombre() + " " + afiliado.getApellido());
						map.put("cedulaAfiliado", afiliado.getCedula());
						map.put("telefono", afiliado.getTelefono() == null ? "" : afiliado.getTelefono());
						map.put("ciudadEmpresario", afiliado.getCiudadResidencia());
						if (distribuidor != null) {
							map.put("nombreDistribuidor", distribuidor.getNombre() + " " + distribuidor.getApellido());
							map.put("cedulaDistribuidor", distribuidor.getCedula());
						} else {
							map.put("nombreDistribuidor", afiliado.getNombre() + " " + afiliado.getApellido());
							map.put("cedulaDistribuidor", afiliado.getCedula());
						}
						String tipoReporte = request.getParameter("tipoReporte") == null ? "PDF" : request.getParameter("tipoReporte");
						if ("PDF".equals(tipoReporte)) {
							GenerarReporte.exportarPDF(request, response, getServletConfig().getServletContext(), "listaCompensacion_" + cedula + "_"
									+ periodo + ".pdf", RecursosEnum.FW_JASPER_LISTA_COMPENSACION_AFILIADO.getRecurso(), map, lista);
						} else {
							GenerarReporte.exportarExcel(request, response, getServletConfig().getServletContext(), "listaCompensacion_" + cedula
									+ "_" + periodo + ".xls", RecursosEnum.FW_JASPER_LISTA_COMPENSACION_AFILIADO.getRecurso(), map, lista);
						}
					} else {
						request.setAttribute("error", "El afiliado no ha ganado compensación por sus consumos para el periodo " + periodo + ".");
						rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_ERROR.getRecurso());
						rd.forward(request, response);
					}
				} else {
					request.setAttribute("error", "No se encuentra el Afiliado");
					rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_ERROR.getRecurso());
					rd.forward(request, response);
				}
			} else {
				request.setAttribute("error", "No se ha realizado la verificación de compensación para el distribuidor en este periodo.");
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
 * co.com.multinivel.frontend.liquidacion.ReporteCompensacionAfiliadoPeriodo
 */