package co.com.multinivel.consumo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.model.Consumo;
import co.com.multinivel.service.ConsumoService;
import co.com.multinivel.util.GenerarReporte;
import co.com.multinivel.util.RecursosEnum;

public class ReporteConstanciaConsumo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	ConsumoService consumoService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HashMap<String, Object> mapParametrosReporte = new HashMap<String, Object>();
			List<Object> listaConsumo = null;
			Consumo pedido = new Consumo();
			pedido.setCodigoConsumo(Integer.parseInt(request.getParameter("codigoConsumo")));
			listaConsumo = this.consumoService.consultar(pedido);
			mapParametrosReporte.put("totalAfiliados", listaConsumo.size());
			if ((listaConsumo != null) && (listaConsumo.size() > 0)) {
				mapParametrosReporte.put("rutaImagenes",
						getServletContext().getInitParameter("rutaImagenes"));
				mapParametrosReporte.put("codigoPedido", request.getParameter("codigoConsumo"));
				mapParametrosReporte.put("tituloCompania", "ALOE DE COLOMBIA");

				GenerarReporte.exportarPDF(request, response, getServletConfig()
						.getServletContext(), "CONSUMO_" + UsuarioHelper.getUsuario() + "_"
						+ request.getParameter("codigoConsumo") + ".pdf",
						RecursosEnum.FW_JASPER_REPORTE_CONSUMO.getRecurso(), mapParametrosReporte,
						listaConsumo);
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
 * Qualified Name: co.com.multinivel.consumo.ReporteConstanciaConsumo
 * 
 * 
 */