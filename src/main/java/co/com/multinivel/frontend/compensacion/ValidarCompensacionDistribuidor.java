package co.com.multinivel.frontend.compensacion;

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

import co.com.multinivel.backend.model.ValidacionCompensacionDistribuidor;
import co.com.multinivel.backend.model.ValidacionCompensacionDistribuidorPK;
import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.backend.service.ValidacionCompensacionDistribuidorService;
import co.com.multinivel.shared.util.RecursosEnum;

public class ValidarCompensacionDistribuidor extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ValidacionCompensacionDistribuidorService validacionCompensacionDistribuidorService;
	@Autowired
	private AfiliadoService afiliadoService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recurso = RecursosEnum.FW_VALIDAR_COMPENSACION_DISTRIBUIDOR.getRecurso();
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter("accion").charAt(0);
			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);

			String periodo = cadenaFecha;
			request.setAttribute("periodo", periodo);
			switch (accion) {
			case 'I':
				String mes = request.getParameter("mes");
				String ano = request.getParameter("ano");
				periodo = mes + "/" + ano;
				ValidacionCompensacionDistribuidor validacionCompensacionDistribuidor = new ValidacionCompensacionDistribuidor();
				ValidacionCompensacionDistribuidorPK validacionCompensacionDistribuidorPK = new ValidacionCompensacionDistribuidorPK();
				validacionCompensacionDistribuidorPK.setPeriodo(periodo);
				validacionCompensacionDistribuidorPK.setDistribuidor(request.getParameter("distribuidor"));
				validacionCompensacionDistribuidor.setId(validacionCompensacionDistribuidorPK);
				boolean retorno = this.validacionCompensacionDistribuidorService.ingresar(validacionCompensacionDistribuidor);
				request.setAttribute("mensaje", Boolean.valueOf(retorno));

				break;
			default:
				request.setAttribute("listaDistribuidores", this.afiliadoService.listarDistribuidores());
			}
			request.setAttribute("accion", Character.valueOf(accion));
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
 * Qualified Name:
 * co.com.multinivel.frontend.compensacion.ValidarCompensacionDistribuidor
 */