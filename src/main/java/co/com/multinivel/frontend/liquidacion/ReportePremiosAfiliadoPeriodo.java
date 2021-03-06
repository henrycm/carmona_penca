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
import co.com.multinivel.backend.model.PremioAfiliado;
import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.backend.service.PremioAfiliadoService;
import co.com.multinivel.shared.util.GenerarReporte;
import co.com.multinivel.shared.util.RecursosEnum;
import co.com.multinivel.shared.util.RutasUtil;

public class ReportePremiosAfiliadoPeriodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private AfiliadoService afiliadoService;
	@Autowired
	private PremioAfiliadoService premioAfiliadoService;

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

			String documento = request.getParameter("documento") == null ? "0" : request.getParameter("documento");
			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);
			String periodo = request.getParameter("periodo") == null ? cadenaFecha : request.getParameter("periodo");
			map.put("titulo", "PREMIOS AFIALIDO POR PERIDOS");
			map.put("periodo", periodo);
			map.put("rutaImagenes", RutasUtil.getRutaImagenes(getServletContext()));
			PremioAfiliado premioAfiliado = new PremioAfiliado();
			premioAfiliado.setCedula(documento);
			premioAfiliado.setPeriodo(periodo);

			Afiliado afiliado = this.afiliadoService.consultar(documento);
			if (afiliado != null) {
				Afiliado distribuidor = this.afiliadoService.consultar(afiliado.getCedulaDistribuidorPago());
				List<Object> lista = this.premioAfiliadoService.consultar(premioAfiliado);
				if ((lista != null) && (lista.size() > 0)) {
					map.put("telefono", afiliado.getTelefono() == null ? "" : afiliado.getTelefono());
					map.put("nombreAfiliado", afiliado.getNombre() + " " + afiliado.getApellido());
					map.put("cedulaAfiliado", afiliado.getCedula());
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
						GenerarReporte.exportarPDF(request, response, getServletConfig().getServletContext(), "listaPremios_" + documento + "_"
								+ periodo + ".pdf", RecursosEnum.FW_JASPER_LISTA_PREMIOS_AFILIADO.getRecurso(), map, lista);
					} else {
						GenerarReporte.exportarExcel(request, response, getServletConfig().getServletContext(), "listaPremios_" + documento + "_"
								+ periodo + ".xls", RecursosEnum.FW_JASPER_LISTA_PREMIOS_AFILIADO.getRecurso(), map, lista);
					}
				} else {
					request.setAttribute("error", "No se encuentra premios para el afiliado");
					rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_ERROR.getRecurso());
					rd.forward(request, response);
				}
			} else {
				request.setAttribute("error", "No se encuentra el Afiliado");
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
 * co.com.multinivel.frontend.liquidacion.ReportePremiosAfiliadoPeriodo
 */