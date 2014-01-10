package co.com.multinivel.pedido;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.model.Pedido;
import co.com.multinivel.service.PedidoService;
import co.com.multinivel.util.RecursosEnum;

public class ListaPedidoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private PedidoService pedidoService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String recurso = null;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter(
					"accion").charAt(0);

			Pedido pedido = new Pedido();
			if ("2".equals(Character.valueOf(UsuarioHelper.getRol()))) {
				String distribuidor = request.getParameter("distribuidor") == null ? UsuarioHelper
						.getUsuario() : request.getParameter("distribuidor");
				pedido.setDistribuidor(distribuidor);
			}
			request.setAttribute("accion", request.getParameter("accion"));
			switch (accion) {
			case 'A':
				pedido.setCodigoPedido(Integer.parseInt(request.getParameter("codigoPedido")));
				if (!this.pedidoService.actualizar(pedido)) {
					request.setAttribute("error",
							"Actualizando el transporte del pedido:" + pedido.getCodigoPedido());
					recurso = RecursosEnum.FW_INGRESO_PEDIDO_ERROR.getRecurso();
				} else {
					request.setAttribute("codigoPedido", Integer.valueOf(pedido.getCodigoPedido()));
					recurso = RecursosEnum.FW_INGRESO_PEDIDO_EXITO.getRecurso();
				}
				break;
			case 'L':
				request.setAttribute("listaPedido", this.pedidoService.listar(pedido));
				recurso = RecursosEnum.FW_LISTAR_PEDIDOS_DISTRIBUIDOR.getRecurso();
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			recurso = RecursosEnum.FW_INGRESO_PEDIDO_ERROR.getRecurso();
		}
		rd = getServletContext().getRequestDispatcher(recurso);

		rd.forward(request, response);
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.pedido.ListaPedidoController
 * 
 * 
 */