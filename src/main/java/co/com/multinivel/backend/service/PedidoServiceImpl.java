package co.com.multinivel.backend.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.dao.InventarioDistribuidorDAO;
import co.com.multinivel.backend.dao.AbonosDistribuidorDAO;
import co.com.multinivel.backend.dao.PedidoDAO;
import co.com.multinivel.backend.model.DetallePedido;
import co.com.multinivel.backend.model.InventarioDistribuidor;
import co.com.multinivel.backend.model.InventarioDistribuidorPK;
import co.com.multinivel.backend.model.Pedido;
import co.com.multinivel.shared.dto.PedidoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
public class PedidoServiceImpl implements PedidoService {
	@Autowired
	private PedidoDAO pedidoDAO;

	@Autowired
	private InventarioDistribuidorDAO invDist;

	@Autowired
	private AbonosDistribuidorDAO movDAO;

	@Transactional
	public boolean ingresarPedido(Pedido pedido) throws MultinivelServiceException {
		boolean retorno = false;
		try {
			retorno = this.pedidoDAO.ingresarPedido(pedido);

			for (DetallePedido dp : pedido.getTDetPedidos()) {
				InventarioDistribuidor iv = invDist.findOne(new InventarioDistribuidorPK(pedido.getDistribuidor(), dp.getCodigoProducto()));
				if (iv == null) {
					iv = new InventarioDistribuidor(new InventarioDistribuidorPK(pedido.getDistribuidor(), dp.getCodigoProducto()));
				}
				iv.setCantidad(iv.getCantidad() + dp.getCantidad());
				iv.setValor_total(iv.getValor_total() + dp.getTotalProducto().longValueExact());
				invDist.save(iv);
			}
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

	/*
	 * public BigDecimal consultarSaldoDistribuidor(Pedido pedido) throws
	 * MultinivelServiceException { BigDecimal saldoDistribuidor = new
	 * BigDecimal(0); try { saldoDistribuidor =
	 * this.pedidoDAO.consultarSaldoDistribuidor(pedido); } catch
	 * (MultinivelDAOException e) { e.printStackTrace(); } return
	 * saldoDistribuidor; }
	 */
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

	public BigDecimal consultarValorTotalPedidosPeriodo(String periodo, String distribuidor) throws MultinivelServiceException {
		BigDecimal valor = new BigDecimal(0);
		try {
			valor = this.pedidoDAO.consultarValorTotalPedidosPeriodo(periodo, distribuidor);
		} catch (MultinivelDAOException e) {
			e.printStackTrace();
		}
		return valor;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.PedidoServiceImpl
 */