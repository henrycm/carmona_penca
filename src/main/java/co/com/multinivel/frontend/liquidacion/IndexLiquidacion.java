package co.com.multinivel.frontend.liquidacion;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.backend.service.ParametroService;
import co.com.multinivel.shared.util.FechasUtil;
import co.com.multinivel.shared.util.RecursosEnum;

public class IndexLiquidacion extends HttpServlet {
	@Autowired
	private AfiliadoService afiliadoService;
	private static final long serialVersionUID = 1L;
	@Autowired
	private ParametroService parametroService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recurso = null;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter("accion").charAt(0);
			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);
			String mensaje = "";
			String st_fecha = parametroService.obtenerValor("FECHA_ARBOL").getValor();
			if (!"1900-01-01".equalsIgnoreCase(st_fecha)) {
				Date fecha = (Date) (!"".equalsIgnoreCase(st_fecha) ? FechasUtil.parse(st_fecha) : "1900-01-01");
				if (FechasUtil.getDias(fecha, new Date()) > 1) {
					mensaje = "Es necesario calcular el Árbol. Ultima fecha de calculo: " + st_fecha;
					request.setAttribute("mensaje", mensaje);
				}
			} else {
				mensaje = "En este momento no es posible Generar Liquidación; Se está Ejecutando el Caluclo del Árbol. Intente de nuevo mas tarde !";
				request.setAttribute("mensaje", mensaje);
			}
			request.setAttribute("periodo", cadenaFecha);
			switch (accion) {
			case 'M':
				recurso = RecursosEnum.FW_INDEX_LIQUIDACION.getRecurso();
				break;
			default:
				recurso = RecursosEnum.FW_INDEX_LIQUIDACION_RED.getRecurso();
				request.setAttribute("listaDistribuidores", this.afiliadoService.listarDistribuidores());
			}
			request.setAttribute("accion", request.getParameter("accion"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(recurso);
		rd.forward(request, response);
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.frontend.liquidacion.IndexLiquidacion
 */