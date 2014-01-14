package co.com.multinivel.consumo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.helper.ConsumoHelper;
import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.model.Consumo;
import co.com.multinivel.model.DetConsumo;
import co.com.multinivel.model.Pedido;
import co.com.multinivel.service.ParametroService;
import co.com.multinivel.service.PedidoService;
import co.com.multinivel.util.RecursosEnum;

public class VistaPreliminarConsumo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	ParametroService parametroService;
	@Autowired
	PedidoService pedidoService;

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
		try {
			Consumo pedido = ConsumoHelper.cargarEntidadPreliminar(request);
			String recurso = RecursosEnum.FW_PRELIMINAR_CONSUMO.getRecurso();

			List<DetConsumo> listaDet = pedido.getTDetConsumos();
			request.setAttribute("listaConsumo", listaDet);
			request.setAttribute("totalConsumo", pedido.getTotalpedido());
			request.setAttribute("ciudad", request.getParameter("ciudad"));
			request.setAttribute("telefono", request.getParameter("telefono"));
			request.setAttribute("nombre", request.getParameter("nombre"));
			if ((request.getParameter("documento") == null)
					|| ("".equals(request.getParameter("documento")))) {
				request.setAttribute("cedula", UsuarioHelper.getUsuario());
			} else {
				request.setAttribute("cedula", request.getParameter("documento"));
			}
			request.setAttribute("accion", request.getParameter("accion"));
			request.setAttribute("distribuidor", request.getParameter("distribuidor"));
			Pedido Objpedido = new Pedido();
			if (UsuarioHelper.getRol() == '2') {
				Objpedido.setDistribuidor(UsuarioHelper.getUsuario());
			} else {
				Objpedido.setDistribuidor(request.getParameter("distribuidor"));
			}
			BigDecimal saldoDistribuidor = this.pedidoService
					.consultarSaldoPorPeriodoDistribuidor(Objpedido);
			if (saldoDistribuidor != null) {
				Double descontarSaldoDistribuidor = Double.valueOf(saldoDistribuidor.doubleValue()
						- pedido.getTotalpedido().doubleValue());
				if (descontarSaldoDistribuidor.doubleValue() > 0.0D) {
					request.setAttribute("saldoDistribuidor", descontarSaldoDistribuidor);
				} else {
					request.setAttribute("mensajeSaldoDistribuidor", "true");
				}
			}
			int minimoConsumo = Integer.parseInt(this.parametroService
					.obtenerValor("MINIMO_PEDIDO").getValor());

			request.setAttribute("transporte", "0");
			request.setAttribute("totalConsumoConTransporte", pedido.getTotalpedido().intValue());

			request.setAttribute("totalConsumo", pedido.getTotalpedido());

			request.setAttribute("fechaActual", request.getParameter("fechaConsumo"));

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
 * Qualified Name: co.com.multinivel.consumo.VistaPreliminarConsumo
 */