package co.com.multinivel.backend.service;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.TipoProductoDAO;
import co.com.multinivel.backend.model.TiposProducto;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
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