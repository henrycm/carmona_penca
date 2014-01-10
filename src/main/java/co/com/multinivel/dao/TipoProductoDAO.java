package co.com.multinivel.dao;

import java.util.List;

import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.TiposProducto;

public abstract interface TipoProductoDAO {
	public abstract List<TiposProducto> listar() throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.dao.TipoProductoDAO
 * 
 * 
 */