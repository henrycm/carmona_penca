package co.com.multinivel.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import co.com.multinivel.dao.TipoProductoDAO;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.TiposProducto;

@Stateless
@Local({ TipoProductoService.class })
public class TipoProductoServiceImpl implements TipoProductoService {
	@EJB
	private TipoProductoDAO tipoProductoDAO;

	public List<TiposProducto> listar() throws MultinivelServiceException {
		List<TiposProducto> lista = null;
		try {
			lista = this.tipoProductoDAO.listar();
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}
}