package co.com.multinivel.backend.service;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.InventarioDistribuidorDAO;
import co.com.multinivel.backend.dao.ProductoDAO;
import co.com.multinivel.backend.model.InventarioDistribuidor;
import co.com.multinivel.backend.model.Producto;
import co.com.multinivel.shared.dto.ProductoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
public class ProductoServiceImpl implements ProductoService {
	@EJB
	private ProductoDAO productoDAO;

	@Autowired
	private InventarioDistribuidorDAO invDAO;

	public void actualizar(Producto producto) throws MultinivelServiceException {
		try {
			this.productoDAO.actualizar(producto);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public void borrar(Producto producto) throws MultinivelServiceException {
		try {
			this.productoDAO.eliminar(producto);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public Producto consultar(String codigo) throws MultinivelServiceException {
		try {
			return this.productoDAO.consultar(codigo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public void ingresar(Producto barrio) throws MultinivelServiceException {
		try {
			this.productoDAO.ingresar(barrio);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public List<ProductoDTO> listar() throws MultinivelServiceException {
		List<ProductoDTO> lista = null;
		try {
			lista = this.productoDAO.listar();
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<ProductoDTO> listar(String tipoProducto)
			throws MultinivelServiceException {
		List<ProductoDTO> lista = null;
		try {
			lista = this.productoDAO.listar(tipoProducto);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<ProductoDTO> listarParaDistribuidor(String tipoProducto, String distribuidor)
			throws MultinivelServiceException {
		List<ProductoDTO> lista = null;
		try {
			lista = this.productoDAO.listarParaDistribuidor(tipoProducto);
			List<InventarioDistribuidor> l = invDAO.findByPkDistribuidor(distribuidor);
			for (ProductoDTO p : lista)
			{
				for (InventarioDistribuidor iv : l)
				{
					if (iv.getPk().getCod_producto().equals(p.getCodigo()))
					{
						p.setDisponibilidadDist(iv.getCantidad());
						break;
					}
				}
			}
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<Object> listarProductoPorcentaje(String periodo)
			throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.productoDAO.listarProductoPorcentaje(periodo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.ProductoServiceImpl
 */