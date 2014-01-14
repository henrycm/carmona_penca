package co.com.multinivel.backend.dao;

import java.util.List;

import co.com.multinivel.backend.model.Producto;
import co.com.multinivel.shared.dto.ProductoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;

public abstract interface ProductoDAO {
	public abstract void ingresar(Producto paramProducto) throws MultinivelDAOException;

	public abstract void actualizar(Producto paramProducto) throws MultinivelDAOException;

	public abstract Producto consultar(String paramString) throws MultinivelDAOException;

	public abstract void eliminar(Producto paramProducto) throws MultinivelDAOException;

	public abstract List<ProductoDTO> listar() throws MultinivelDAOException;

	public abstract List<ProductoDTO> listar(String paramString) throws MultinivelDAOException;

	public abstract List<ProductoDTO> listarParaDistribuidor(String paramString)
			throws MultinivelDAOException;

	public abstract List<Object> listarProductoPorcentaje(String paramString)
			throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.ProductoDAO
 */