package co.com.multinivel.frontend.pedido;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.backend.model.DetallePedido;
import co.com.multinivel.backend.model.Pedido;
import co.com.multinivel.backend.service.ParametroService;
import co.com.multinivel.shared.helper.PedidoHelper;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.RecursosEnum;

public class VistaPreliminarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	ParametroService parametroService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Pedido pedido = PedidoHelper.cargarEntidadPreliminar(request);
			String recurso = RecursosEnum.FW_PRELIMINAR_PEDIDO.getRecurso();

			Set<DetallePedido> listaDet = pedido.getTDetPedidos();

			request.setAttribute("listaPedido", listaDet);
			request.setAttribute("totalPedido", pedido.getTotalPedido());
			request.setAttribute("ciudad", request.getParameter("ciudad"));
			request.setAttribute("telefono", request.getParameter("telefono"));
			request.setAttribute("nombre", request.getParameter("nombre"));
			if ((request.getParameter("documento") == null) || ("".equals(request.getParameter("documento")))) {
				request.setAttribute("cedula", UsuarioHelper.getUsuario());
			} else {
				request.setAttribute("cedula", request.getParameter("documento"));
			}
			request.setAttribute("accion", request.getParameter("accion"));
			int minimoPedido = Integer.parseInt(this.parametroService.obtenerValor("MINIMO_PEDIDO").getValor());
			int totalpedidoContransporte = pedido.getTotalPedido().intValue();
			if (pedido.getTotalPedido().intValue() < minimoPedido) {
				int transporte = Integer.parseInt(this.parametroService.obtenerValor("TRANSPORTE_PEDIDO").getValor());
				totalpedidoContransporte += transporte;
				request.setAttribute("transporte", transporte);
				request.setAttribute("totalPedidoConTransporte", totalpedidoContransporte);
			} else {
				request.setAttribute("transporte", "0");
				request.setAttribute("totalPedidoConTransporte", pedido.getTotalPedido().intValue());
			}
			double porcentajePedido = Double.parseDouble(this.parametroService.obtenerValor("PORC_GAN_PDTO_PROVEE").getValor());
			double totalPedidoafiliado = pedido.getTotalPedido().doubleValue() + pedido.getTotalPedido().doubleValue() * (porcentajePedido / 100.0D);
			totalPedidoafiliado = Math.floor(totalPedidoafiliado * Math.pow(10.0D, 2.0D)) / Math.pow(10.0D, 2.0D);
			request.setAttribute("totalPedido", pedido.getTotalPedido());
			request.setAttribute("totalPedidoAfiliado", Double.valueOf(totalPedidoafiliado));

			SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
			String cadenaFecha = formato.format(new Date());
			request.setAttribute("fechaActual", cadenaFecha);

			RequestDispatcher rd = getServletContext().getRequestDispatcher(recurso);
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.frontend.pedido.VistaPreliminarPedido
 */