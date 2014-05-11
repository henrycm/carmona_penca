package co.com.multinivel.frontend.consumo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.backend.model.Consumo;
import co.com.multinivel.backend.service.ConsumoService;
import co.com.multinivel.backend.service.PedidoService;
import co.com.multinivel.shared.helper.ConsumoHelper;
import co.com.multinivel.shared.util.RecursosEnum;

public class RealizarConsumo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ConsumoService consumoService;
	@Autowired
	private PedidoService pedidoService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String recurso = null;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter("accion").charAt(0);
			Consumo consumo = ConsumoHelper.cargarEntidad(request);

			recurso = RecursosEnum.FW_INGRESO_CONSUMO_EXITO.getRecurso();
			switch (accion) {
			case 'I':
				if (!this.consumoService.ingresar(consumo)) {
					request.setAttribute("error", "Datos incompletos");
					recurso = RecursosEnum.FW_INGRESO_PEDIDO_ERROR.getRecurso();
				} else {
					request.setAttribute("codigoConsumo", Integer.valueOf(this.consumoService.ultimoConsumo(consumo)));
				}
				break;
			case 'A':
				if (!this.consumoService.ingresar(consumo)) {
					request.setAttribute("error", "Datos incompletos");
					recurso = RecursosEnum.FW_INGRESO_CONSUMO_ERROR.getRecurso();
				} else {
					request.setAttribute("codigoConsumo", Integer.valueOf(this.consumoService.ultimoConsumo(consumo)));
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			recurso = RecursosEnum.FW_INGRESO_CONSUMO_ERROR.getRecurso();
		}
		rd = getServletContext().getRequestDispatcher(recurso);

		rd.forward(request, response);
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.frontend.consumo.RealizarConsumo
 */