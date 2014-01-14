package co.com.multinivel.dao;

import java.util.List;

import co.com.multinivel.dto.ProductoDTO;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.Producto;

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
 * Qualified Name: co.com.multinivel.dao.ProductoDAO
 */