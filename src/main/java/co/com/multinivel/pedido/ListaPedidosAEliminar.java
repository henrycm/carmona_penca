package co.com.multinivel.pedido;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.dto.PedidoDTO;
import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.service.AfiliadoService;
import co.com.multinivel.service.PedidoService;
import co.com.multinivel.util.RecursosEnum;

public class ListaPedidosAEliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	PedidoService pedidoService;
	@EJB
	AfiliadoService afiliadoService;

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
			PedidoDTO pedido = new PedidoDTO();
			pedido.setPeriodo(periodo);
			pedido.setCedulaDistribuidor(distribuidor);

			List<Object> lista = this.pedidoService.listarPedidosAEliminar(pedido);
			if ((lista != null) && (lista.size() > 0)) {
				request.setAttribute("pedidos", lista);
				url = RecursosEnum.FW_LISTAR_PEDIDO_ELIMINAR.getRecurso();
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
 * Qualified Name: co.com.multinivel.pedido.ListaPedidosAEliminar
 * 
 * 
 */