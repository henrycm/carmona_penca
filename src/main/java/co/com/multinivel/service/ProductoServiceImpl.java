package co.com.multinivel.service;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import co.com.multinivel.dao.ProductoDAO;
import co.com.multinivel.dto.ProductoDTO;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.Producto;

@Service
public class ProductoServiceImpl implements ProductoService {
	@EJB
	private ProductoDAO productoDAO;

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

	public List<ProductoDTO> listar(String tipoProducto) throws MultinivelServiceException {
		List<ProductoDTO> lista = null;
		try {
			lista = this.productoDAO.listar(tipoProducto);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<ProductoDTO> listarParaDistribuidor(String tipoProducto)
			throws MultinivelServiceException {
		List<ProductoDTO> lista = null;
		try {
			lista = this.productoDAO.listarParaDistribuidor(tipoProducto);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<Object> listarProductoPorcentaje(String periodo) throws MultinivelServiceException {
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
 * Qualified Name: co.com.multinivel.service.ProductoServiceImpl
 */