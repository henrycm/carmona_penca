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

import co.com.multinivel.backend.service.CompensacionAfiliadoService;
import co.com.multinivel.shared.util.RecursosEnum;

public class ControlLiquidacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	CompensacionAfiliadoService compensacionAfiliadoService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String recurso = null;

		int transaccion = -1;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter(
					"accion").charAt(0);

			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);
			String mes = request.getParameter("mes");
			String ano = request.getParameter("ano");

			String periodo = mes + "/" + ano;
			switch (accion) {
			case 'M':
				recurso = RecursosEnum.FW_INDEX_LIQUIDACION.getRecurso();
				request.setAttribute("liquidar", Boolean.valueOf(true));
				transaccion = this.compensacionAfiliadoService.liquidar(null, periodo);
				request.setAttribute("liquidacionExitosa", Integer.valueOf(transaccion));

				break;
			default:
				recurso = RecursosEnum.FW_INDEX_LIQUIDACION_RED.getRecurso();
				String distribuidor = request.getParameter("red") == null ? null : request
						.getParameter("red");
				transaccion = this.compensacionAfiliadoService.liquidar(distribuidor, periodo);
				request.setAttribute("liquidacionExitosa", Integer.valueOf(transaccion));
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