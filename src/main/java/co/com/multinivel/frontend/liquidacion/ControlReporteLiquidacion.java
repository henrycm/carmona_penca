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

import co.com.multinivel.backend.service.RedService;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.RecursosEnum;

public class ControlReporteLiquidacion extends HttpServlet {
	@Autowired
	private RedService redService;
	private static final long serialVersionUID = 1L;

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
			case 'A':
				recurso = RecursosEnum.FW_LISTA_PREMIOS_AFILIADO.getRecurso() + "?documento="
						+ UsuarioHelper.getUsuario() + "&periodo=" + periodo + "&tipoReporte="
						+ request.getParameter("tipoReporte");
				break;
			case 'B':
				recurso = RecursosEnum.FW_LISTA_COMPENSACION_AFILIADO.getRecurso() + "?documento="
						+ UsuarioHelper.getUsuario() + "&periodo=" + periodo + "&tipoReporte="
						+ request.getParameter("tipoReporte");
				break;
			case 'C':
				recurso = RecursosEnum.FW_LISTA_COMPENSACION_RED.getRecurso() + "?distribuidor="
						+ request.getParameter("distribuidor") + "&periodo=" + periodo
						+ "&tipoReporte=" + request.getParameter("tipoReporte");
				break;
			case 'D':
				recurso = RecursosEnum.FW_LISTA_COMPENSACION_DISTRIBUIDOR.getRecurso()
						+ "?documento=" + UsuarioHelper.getUsuario() + "&periodo=" + periodo
						+ "&tipoReporte=" + request.getParameter("tipoReporte");
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
 * Qualified Name: co.com.multinivel.frontend.liquidacion.ControlReporteLiquidacion
 */