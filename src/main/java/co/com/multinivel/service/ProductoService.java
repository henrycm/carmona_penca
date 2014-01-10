package co.com.multinivel.service;

import java.util.List;

import co.com.multinivel.dto.ProductoDTO;
import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.Producto;

public abstract interface ProductoService {
	public abstract void ingresar(Producto paramProducto) throws MultinivelServiceException;

	public abstract void actualizar(Producto paramProducto) throws MultinivelServiceException;

	public abstract void borrar(Producto paramProducto) throws MultinivelServiceException;

	public abstract Producto consultar(String paramString) throws MultinivelServiceException;

	public abstract List<ProductoDTO> listar() throws MultinivelServiceException;

	public abstract List<ProductoDTO> listar(String paramString) throws MultinivelServiceException;

	public abstract List<ProductoDTO> listarParaDistribuidor(String paramString)
			throws MultinivelServiceException;

	public abstract List<Object> listarProductoPorcentaje(String paramString)
			throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.service.ProductoService
 * 
 * 
 */