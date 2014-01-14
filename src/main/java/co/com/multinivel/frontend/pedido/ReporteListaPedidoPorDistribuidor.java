package co.com.multinivel.frontend.pedido;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.backend.service.PedidoService;
import co.com.multinivel.shared.dto.PedidoDTO;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.GenerarReporte;
import co.com.multinivel.shared.util.RecursosEnum;
import co.com.multinivel.shared.util.RutasUtil;

public class ReporteListaPedidoPorDistribuidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	PedidoService pedidoService;
	@Autowired
	AfiliadoService afiliadoService;

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
		String tipoReporte = request.getParameter("tipoReporte") == null ? "PDF" : request
				.getParameter("tipoReporte");
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			Date fechaActual = new Date();
			String distribuidor = UsuarioHelper.getUsuario();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);
			String mes = request.getParameter("mes");
			String ano = request.getParameter("ano");
			periodo = mes + "/" + ano;
			map.put("periodo", periodo);
			map.put("rutaImagenes", RutasUtil.getRutaImagenes(getServletContext()));
			PedidoDTO pedido = new PedidoDTO();
			pedido.setPeriodo(cadenaFecha);
			pedido.setCedulaDistribuidor(distribuidor);
			Afiliado datosDistribuidor = this.afiliadoService.consultar(distribuidor);

			List<Object> lista = this.pedidoService.listarPorPeriodo(pedido);
			if ((lista != null) && (lista.size() > 0)) {
				map.put("distribuidor",
						datosDistribuidor.getCedula() + "  " + datosDistribuidor.getNombre() + " "
								+ datosDistribuidor.getApellido());
				if ("PDF".equals(tipoReporte)) {
					GenerarReporte.exportarPDF(request, response, getServletConfig()
							.getServletContext(), "Reporte_ListaPedidosDistribuidor_" + periodo
							+ ".pdf", RecursosEnum.FW_JASPER_REPORTE_LISTA_PEDIDOS_DISTRIBUIDOR
							.getRecurso(), map, lista);
				} else {
					GenerarReporte.exportarExcel(request, response, getServletConfig()
							.getServletContext(), "Reporte_ListaPedidosDistribuidor_" + periodo
							+ ".xls", RecursosEnum.FW_JASPER_REPORTE_LISTA_PEDIDOS_DISTRIBUIDOR
							.getRecurso(), map, lista);
				}
			} else {
				request.setAttribute("error", "No existen pedidos para el periodo solicitado:"
						+ periodo);
				rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_ERROR.getRecurso());
				rd.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_ERROR.getRecurso());
			rd.forward(request, response);
		}
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.frontend.pedido.ReporteListaPedidoPorDistribuidor
 */