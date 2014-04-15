package co.com.multinivel.frontend.liquidacion;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.backend.service.ArbolTaskService;
import co.com.multinivel.backend.service.CompensacionAfiliadoService;
import co.com.multinivel.backend.service.ParametroService;
import co.com.multinivel.shared.util.RecursosEnum;

public class ControlLiquidacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private AfiliadoService afiliadoService;
	@Autowired
	CompensacionAfiliadoService compensacionAfiliadoService;
	@Autowired
	private ParametroService parametroService;

	private ThreadPoolTaskExecutor threadPool;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recurso = null;

		int transaccion = -1;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter("accion").charAt(0);

			String mes = request.getParameter("mes");
			String ano = request.getParameter("ano");

			String periodo = mes + "/" + ano;
			request.setAttribute("periodo", periodo);
			switch (accion) {
			case 'M':
				recurso = RecursosEnum.FW_INDEX_LIQUIDACION.getRecurso();
				request.setAttribute("liquidar", Boolean.valueOf(true));
				/**
				 * Se envia No, por que existen afiliados con null o en blanco y
				 * calcula la liquidacion de estos pero no se debería hacer.
				 */
				transaccion = this.compensacionAfiliadoService.liquidar("No", periodo);
				request.setAttribute("liquidacionExitosa", Integer.valueOf(transaccion));
				request.setAttribute("listaDistribuidores", this.afiliadoService.listarDistribuidores());
				break;
			case 'I':
				recurso = RecursosEnum.FW_INICIO_CALCULO_ARBOL.getRecurso();
				request.setAttribute("fecha", parametroService.obtenerValor("FECHA_ARBOL").getValor());
				break;
			case 'C':
				recurso = RecursosEnum.FW_INICIO_CALCULO_ARBOL.getRecurso();
				ArbolTaskService t = new ArbolTaskService(compensacionAfiliadoService, "No", "No");
				threadPool = (ThreadPoolTaskExecutor) WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext())
						.getBean("taskExecutor");
				String mensaje = "";
				if (threadPool.getActiveCount() > 0)
					mensaje = "Se esta ejecutando en este momento";
				else {
					threadPool.execute(t);
					mensaje = "Se inicio el proceso de calculo del arbol de liquidacion!";
				}
				request.setAttribute("mensaje", mensaje);
				break;
			default:
				recurso = RecursosEnum.FW_INDEX_LIQUIDACION_RED.getRecurso();
				String distribuidor = request.getParameter("red") == null ? null : request.getParameter("red");
				transaccion = this.compensacionAfiliadoService.liquidar(distribuidor, periodo);
				request.setAttribute("liquidacionExitosa", Integer.valueOf(transaccion));
				request.setAttribute("listaDistribuidores", this.afiliadoService.listarDistribuidores());
			}
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
 * Qualified Name: co.com.multinivel.frontend.liquidacion.ControlLiquidacion
 */