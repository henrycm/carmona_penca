package co.com.multinivel.producto;

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

import co.com.multinivel.dto.ProductoDTO;
import co.com.multinivel.model.TiposProducto;
import co.com.multinivel.service.ProductoService;
import co.com.multinivel.service.TipoProductoService;
import co.com.multinivel.util.RecursosEnum;

public class IndexFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private TipoProductoService tipoProductoService;

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
		String recurso = null;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter(
					"accion").charAt(0);
			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);
			request.setAttribute("periodo", cadenaFecha);
			List<ProductoDTO> listaProductos = null;
			List<TiposProducto> listaTiposPdtos = null;
			switch (accion) {
			case 'I':
				recurso = RecursosEnum.FW_INGRESO_PRODUCTO.getRecurso();
				listaProductos = this.productoService.listar();
				listaTiposPdtos = this.tipoProductoService.listar();
				request.setAttribute("listaProductos", listaProductos);
				request.setAttribute("listaTipoProducto", listaTiposPdtos);
				break;
			case 'C':
				recurso = RecursosEnum.FW_ACTUALIZACION_PRODUCTO.getRecurso();
				listaProductos = this.productoService.listar();
				listaTiposPdtos = this.tipoProductoService.listar();
				request.setAttribute("listaProductos", listaProductos);
				request.setAttribute("listaTipoProducto", listaTiposPdtos);
				break;
			case 'L':
				recurso = RecursosEnum.FW_LISTAR_PRODUCTO.getRecurso();
				listaProductos = this.productoService.listar();
				listaTiposPdtos = this.tipoProductoService.listar();
				request.setAttribute("listaProductos", listaProductos);
				request.setAttribute("listaTipoProducto", listaTiposPdtos);
				break;
			case 'P':
				recurso = RecursosEnum.FW_CONSULTAR_CONSUMO_PORC_PDTOS.getRecurso();
				break;
			default:
				listaProductos = this.productoService.listar();
				listaTiposPdtos = this.tipoProductoService.listar();
				request.setAttribute("listaProductos", listaProductos);
				request.setAttribute("listaTipoProducto", listaTiposPdtos);
				recurso = RecursosEnum.FW_CONSULTAR_PRODUCTO.getRecurso();
			}
			request.setAttribute("accion", request.getParameter("accion"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(recurso);
		rd.forward(request, response);
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.producto.IndexFrontController
 */