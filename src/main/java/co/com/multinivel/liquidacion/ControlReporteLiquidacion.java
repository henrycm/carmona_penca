package co.com.multinivel.liquidacion;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.service.RedService;
import co.com.multinivel.util.RecursosEnum;

public class ControlReporteLiquidacion extends HttpServlet {
	@EJB
	private RedService redService;
	private static final long serialVersionUID = 1L;

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
 * Qualified Name: co.com.multinivel.liquidacion.ControlReporteLiquidacion
 * 
 * 
 */