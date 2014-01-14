package co.com.multinivel.consumo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.dto.ConsumoDTO;
import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.service.ConsumoService;
import co.com.multinivel.util.RecursosEnum;

public class ListaConsumosAEliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	ConsumoService consumoService;

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
		RequestDispatcher rd = null;
		String periodo = "";
		String url = RecursosEnum.FW_ERROR.getRecurso();
		try {
			Date fechaActual = new Date();
			String distribuidor = UsuarioHelper.getUsuario();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);
			periodo = request.getParameter("periodo") == null ? cadenaFecha : request
					.getParameter("periodo");
			ConsumoDTO pedido = new ConsumoDTO();

			pedido.setPeriodo(periodo);
			if (UsuarioHelper.getRol() == '2') {
				pedido.setCedulaDistribuidor(distribuidor);
			} else {
				pedido.setCedulaDistribuidor(request.getParameter("distribuidor"));
			}
			request.setAttribute("periodo", periodo);

			List<Object> lista = this.consumoService.listarConsumosPeriodoAEliminar(pedido);
			if ((lista != null) && (lista.size() > 0)) {
				request.setAttribute("consumos", lista);
				request.setAttribute("distribuidor", request.getParameter("distribuidor"));

				url = RecursosEnum.FW_LISTAR_CONSUMO_ELIMINAR.getRecurso();
			} else {
				request.setAttribute("error", "No existen pedidos para el periodo solicitado:"
						+ periodo);
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.consumo.ListaConsumosAEliminar
 */