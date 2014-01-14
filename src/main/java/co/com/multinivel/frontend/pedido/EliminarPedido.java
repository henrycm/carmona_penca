package co.com.multinivel.frontend.pedido;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.backend.model.Pedido;
import co.com.multinivel.backend.service.PedidoService;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.RecursosEnum;

public class EliminarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private PedidoService pedidoService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String recurso = RecursosEnum.FW_ELIMINACION_PEDIDO_EXITO.getRecurso();
		try {
			Pedido pedido = new Pedido();
			pedido.setDistribuidor(UsuarioHelper.getUsuario());
			int codigopedido = Integer.parseInt(request.getParameter("pedido"));
			BigDecimal totalPedido = new BigDecimal(request.getParameter("totalPedido"));

			pedido.setCodigoPedido(codigopedido);
			pedido.setTotalPedido(totalPedido);
			boolean transaccion = this.pedidoService.eliminarPedido(pedido);
			if (!transaccion) {
				request.setAttribute("error",
						"Borrado no fue finalizado consulte con el administrador del sistema");
				recurso = RecursosEnum.FW_ELIMINACION_PEDIDO_ERROR.getRecurso();
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			recurso = RecursosEnum.FW_ELIMINACION_PEDIDO_ERROR.getRecurso();
		}
		rd = getServletContext().getRequestDispatcher(recurso);

		rd.forward(request, response);
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.frontend.pedido.EliminarPedido
 */