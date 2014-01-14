package co.com.multinivel.backend.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.PedidoDAO;
import co.com.multinivel.backend.model.Pedido;
import co.com.multinivel.shared.dto.PedidoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
public class PedidoServiceImpl implements PedidoService {
	@EJB
	private PedidoDAO pedidoDAO;

	public boolean ingresarPedido(Pedido pedido) throws MultinivelServiceException {
		boolean retorno = false;
		try {
			retorno = this.pedidoDAO.ingresarPedido(pedido);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public List<Object> consultar(Pedido pedido) throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.pedidoDAO.consultar(pedido);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public int ultimoPedido(Pedido pedido) throws MultinivelServiceException {
		int retorno = 0;
		try {
			retorno = this.pedidoDAO.ultimoPedido(pedido);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public BigDecimal consultarSaldoPorPeriodoDistribuidor(Pedido pedido)
			throws MultinivelServiceException {
		BigDecimal saldoDistribuidor = new BigDecimal(0);
		try {
			saldoDistribuidor = this.pedidoDAO.consultarSaldoPorPeriodoDistribuidor(pedido);
		} catch (MultinivelDAOException e) {
			e.printStackTrace();
		}
		return saldoDistribuidor;
	}

	public boolean actualizar(Pedido pedido) throws MultinivelServiceException {
		boolean retorno = false;
		try {
			retorno = this.pedidoDAO.actualizar(pedido);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public Pedido buscar(Pedido pedido) throws MultinivelServiceException {
		return null;
	}

	public List<Pedido> listar(Pedido pedido) throws MultinivelServiceException {
		List<Pedido> lista = null;
		try {
			lista = this.pedidoDAO.listar(pedido);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<Object> listarPorPeriodo(PedidoDTO pedido) throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.pedidoDAO.listarPorPeriodo(pedido);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public boolean eliminarPedido(Pedido pedido) throws MultinivelServiceException {
		boolean retorno = false;
		try {
			retorno = this.pedidoDAO.eliminarPedido(pedido);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public List<Object> listarPedidosAEliminar(PedidoDTO pedido) throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.pedidoDAO.listarPedidosAEliminar(pedido);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.PedidoServiceImpl
 */