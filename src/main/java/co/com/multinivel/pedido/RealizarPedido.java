package co.com.multinivel.pedido;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.helper.PedidoHelper;
import co.com.multinivel.model.Consumo;
import co.com.multinivel.model.Pedido;
import co.com.multinivel.service.ConsumoService;
import co.com.multinivel.service.PedidoService;
import co.com.multinivel.util.CorreoUtil;
import co.com.multinivel.util.RecursosEnum;

public class RealizarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private PedidoService pedidoService;
	@EJB
	private ConsumoService consumoService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String recurso = null;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter(
					"accion").charAt(0);
			Pedido pedido = PedidoHelper.cargarEntidad(request);

			recurso = RecursosEnum.FW_INGRESO_PEDIDO_EXITO.getRecurso();
			switch (accion) {
			case 'I':
				if (!this.pedidoService.ingresarPedido(pedido)) {
					request.setAttribute("error", "Datos incompletos");
					recurso = RecursosEnum.FW_INGRESO_PEDIDO_ERROR.getRecurso();
				} else {
					CorreoUtil.enviarCorreo("ENVIO PEDIDO N." + pedido.getCodigoPedido(),
							"Se realizo el pedido para el distribuidor:" + pedido.getAfiliado()
									+ " por valor de :" + pedido.getTotalPedido() + " periodo:"
									+ pedido.getFecha());
					request.setAttribute("codigoPedido",
							Integer.valueOf(this.pedidoService.ultimoPedido(pedido)));
				}
				break;
			case 'A':
				Consumo consumo = new Consumo();
				consumo.setAfiliado(pedido.getAfiliado());
				consumo.setDistribuidor(pedido.getDistribuidor());

				int saldoAfiliado = this.consumoService
						.consultarSaldoPorPeriodoDeAfiliados(consumo).intValue();
				int saldoDistribuidor = this.pedidoService.consultarSaldoPorPeriodoDistribuidor(
						pedido).intValue();
				int totalPedido = pedido.getTotalPedido().intValue();
				if (totalPedido + saldoAfiliado < saldoDistribuidor) {
					if (!this.pedidoService.ingresarPedido(pedido)) {
						request.setAttribute("error", "Datos incompletos");
						recurso = RecursosEnum.FW_INGRESO_PEDIDO_ERROR.getRecurso();
					} else {
						CorreoUtil.enviarCorreo("REGISTRO CONSUMO N." + pedido.getCodigoPedido(),
								"Se registro el consumo para el afiliado:" + pedido.getAfiliado()
										+ " por valor de :" + pedido.getTotalPedido() + " periodo:"
										+ pedido.getFecha());

						request.setAttribute("codigoPedido",
								Integer.valueOf(this.pedidoService.ultimoPedido(pedido)));
					}
				} else {
					request.setAttribute("error",
							"El saldo del distribuidor es menor al registro de afiliados ingresados");
					recurso = RecursosEnum.FW_INGRESO_PEDIDO_ERROR.getRecurso();
				}
				break;
			case 'Q':
				if (!this.pedidoService.ingresarPedido(pedido)) {
					request.setAttribute("error", "Datos incompletos");
					recurso = RecursosEnum.FW_INGRESO_PEDIDO_ERROR.getRecurso();
				} else {
					CorreoUtil.enviarCorreo("ENVIO PEDIDO N." + pedido.getCodigoPedido(),
							"Se realizo el pedido para el distribuidor:" + pedido.getAfiliado()
									+ " por valor de :" + pedido.getTotalPedido() + " periodo:"
									+ pedido.getFecha());
					request.setAttribute("codigoPedido",
							Integer.valueOf(this.pedidoService.ultimoPedido(pedido)));
				}
				break;
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
 * Qualified Name: co.com.multinivel.pedido.RealizarPedido
 * 
 * 
 */