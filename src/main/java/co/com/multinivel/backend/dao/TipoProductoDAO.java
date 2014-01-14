package co.com.multinivel.backend.dao;

import java.util.List;

import co.com.multinivel.backend.model.TiposProducto;
import co.com.multinivel.shared.exception.MultinivelDAOException;

public abstract interface TipoProductoDAO {
	public abstract List<TiposProducto> listar() throws MultinivelDAOException;
}
