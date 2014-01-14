package co.com.multinivel.frontend.pedido;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
import co.com.multinivel.shared.util.GenerarReporte;
import co.com.multinivel.shared.util.RecursosEnum;
import co.com.multinivel.shared.util.RutasUtil;

public class ReporteConstanciaPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
			HashMap<String, Object> mapParametrosReporte = new HashMap<String, Object>();
			List<Object> listaPedido = null;
			Pedido pedido = new Pedido();
			pedido.setCodigoPedido(Integer.parseInt(request.getParameter("codigoPedido")));
			listaPedido = this.pedidoService.consultar(pedido);
			mapParametrosReporte.put("totalAfiliados", listaPedido.size());
			if ((listaPedido != null) && (listaPedido.size() > 0)) {
				mapParametrosReporte.put("rutaImagenes",
						RutasUtil.getRutaImagenes(getServletContext()));
				mapParametrosReporte.put("codigoPedido", request.getParameter("codigoPedido"));
				mapParametrosReporte.put("tituloCompania", "ALOE DE COLOMBIA");

				GenerarReporte.exportarPDF(request, response, getServletConfig()
						.getServletContext(), "PEDIDO_" + UsuarioHelper.getUsuario() + "_"
						+ request.getParameter("codigoPedido") + ".pdf",
						RecursosEnum.FW_JASPER_REPORTE_PEDIDO.getRecurso(), mapParametrosReporte,
						listaPedido);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.frontend.pedido.ReporteConstanciaPedido
 */