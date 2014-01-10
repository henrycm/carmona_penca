package co.com.multinivel.premiacion;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.util.RecursosEnum;

public class IndexPremiacion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String recurso = null;
		try {
			String accion = request.getParameter("accion") == null ? "B" : request
					.getParameter("accion");
			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);

			String periodo = cadenaFecha;

			request.setAttribute("periodo", periodo);
			request.setAttribute("accion", accion);

			recurso = RecursosEnum.FW_CONSULTAR_PREMIACION_POR_AFILIADO.getRecurso();
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
 * Qualified Name: co.com.multinivel.premiacion.IndexPremiacion
 * 
 * 
 */