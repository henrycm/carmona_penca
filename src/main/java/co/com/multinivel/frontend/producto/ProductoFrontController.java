package co.com.multinivel.frontend.producto;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.backend.model.Producto;
import co.com.multinivel.backend.service.ProductoService;
import co.com.multinivel.backend.service.TipoProductoService;
import co.com.multinivel.shared.dto.ProductoDTO;
import co.com.multinivel.shared.helper.ProductoHelper;
import co.com.multinivel.shared.util.RecursosEnum;

public class ProductoFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private TipoProductoService tipoProductoService;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String recurso = null;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter(
					"accion").charAt(0);
			Producto producto = ProductoHelper.cargarEntidad(request);
			recurso = RecursosEnum.FW_INDEX_AFILIADO.getRecurso();
			List<ProductoDTO> lista = null;
			switch (accion) {
			case 'A':
				this.productoService.actualizar(producto);
				recurso = RecursosEnum.FW_INGRESO_PRODUCTO.getRecurso();
				request.setAttribute("actualizo", Boolean.valueOf(true));
				break;
			case 'E':
				this.productoService.borrar(producto);
				recurso = RecursosEnum.FW_INGRESO_PRODUCTO.getRecurso();
				request.setAttribute("borro", Boolean.valueOf(true));
				break;
			case 'I':
				this.productoService.ingresar(producto);
				recurso = RecursosEnum.FW_INGRESO_PRODUCTO.getRecurso();
				request.setAttribute("ingreso", Boolean.valueOf(true));

				break;
			case 'C':
				Producto productoConsulta = this.productoService.consultar(producto.getCodigo());
				recurso = RecursosEnum.FW_INGRESO_PRODUCTO.getRecurso();
				request.setAttribute("producto", productoConsulta);
				request.setAttribute("accion", "C");
				request.setAttribute("ingreso", Boolean.valueOf(true));

				break;
			case 'B':
				this.productoService.borrar(producto);
				request.setAttribute("retiro", Boolean.valueOf(true));
			}
			lista = this.productoService.listar();
			request.setAttribute("listaProductos", lista);
			request.setAttribute("listaTipoProducto", this.tipoProductoService.listar());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			recurso = RecursosEnum.FW_INGRESO_PRODUCTO_ERROR.getRecurso();
		}
		rd = getServletContext().getRequestDispatcher(recurso);

		rd.forward(request, response);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		doPost(req, resp);
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.frontend.producto.ProductoFrontController
 */