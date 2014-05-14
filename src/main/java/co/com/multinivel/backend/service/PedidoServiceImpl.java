package co.com.multinivel.backend.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.dao.InventarioDistribuidorDAO;
import co.com.multinivel.backend.dao.ParametroDAO;
import co.com.multinivel.backend.dao.PedidoDAO;
import co.com.multinivel.backend.dao.SaldoPedidoDistribuidorDAO;
import co.com.multinivel.backend.model.DetallePedido;
import co.com.multinivel.backend.model.InventarioDistribuidor;
import co.com.multinivel.backend.model.InventarioDistribuidorPK;
import co.com.multinivel.backend.model.Pedido;
import co.com.multinivel.backend.model.SaldoPedidoDistribuidor;
import co.com.multinivel.shared.dto.PedidoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
public class PedidoServiceImpl implements PedidoService {
	@Autowired
	private PedidoDAO pedidoDAO;
	@Autowired
	private InventarioDistribuidorDAO invDistDAO;
	@Autowired
	private ParametroDAO parametroDAO;
	@Autowired
	private SaldoPedidoDistribuidorDAO saldoPedidoDistribuidorDAO;

	@Transactional
	public boolean ingresarPedido(Pedido pedido) throws MultinivelServiceException {
		boolean retorno = Boolean.FALSE;
		try {
			/*
			 * Graba Pedido
			 */
			retorno = this.pedidoDAO.ingresarPedido(pedido);
			/*
			 * Graba Inventario Distribuidor
			 */
			double valTotProductoAfiliado = 0;
			double valTotPedidoAfiliado = 0;
			for (DetallePedido dp : pedido.getTDetPedidos()) {
				InventarioDistribuidor iv = this.invDistDAO.findOne(new InventarioDistribuidorPK(pedido.getDistribuidor(), dp.getCodigoProducto()));
				if (iv == null) {
					iv = new InventarioDistribuidor(new InventarioDistribuidorPK(pedido.getDistribuidor(), dp.getCodigoProducto()));
				}
				valTotProductoAfiliado = iv.getValor_total() + dp.getTotalProductoAfiliado().longValueExact();
				iv.setCantidad(iv.getCantidad() + dp.getCantidad());
				iv.setValor_total(valTotProductoAfiliado);
				this.invDistDAO.save(iv);

				valTotPedidoAfiliado += valTotProductoAfiliado;
			}
			/*
			 * Graba Saldo Distribuidor
			 */
			SaldoPedidoDistribuidor spd = this.saldoPedidoDistribuidorDAO.consultarSaldoDistribuidor(pedido.getDistribuidor());
			if (spd == null) {
				spd = new SaldoPedidoDistribuidor();
				spd.setDistribuidor(pedido.getDistribuidor());
				spd.setSaldo(0);
				spd.setSaldoAbonado(0);
			}
			double saldo = spd.getSaldo() + valTotPedidoAfiliado;
			spd.setSaldo(saldo);
			retorno = this.saldoPedidoDistribuidorDAO.guardar(spd);
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

	public boolean actualizar(Pedido pedido) throws MultinivelServiceException {
		boolean retorno = Boolean.FALSE;
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
		boolean retorno = Boolean.FALSE;
		try {
			/*
			 * Graba Inventario Distribuidor
			 */
			List<Object> lsPedidos = this.pedidoDAO.consultar(pedido);
			Iterator<Object> iterador = lsPedidos.listIterator();

			double valTotProductoAfiliado = 0;
			double valTotPedidoAfiliado = 0;
			while (iterador.hasNext()) {
				PedidoDTO ped = (PedidoDTO) iterador.next();
				InventarioDistribuidor iv = this.invDistDAO
						.findOne(new InventarioDistribuidorPK(ped.getCedulaDistribuidor(), ped.getCodigoProducto()));
				if (iv == null) {
					iv = new InventarioDistribuidor(new InventarioDistribuidorPK(ped.getCedulaDistribuidor(), ped.getCodigoProducto()));
				}
				valTotProductoAfiliado = iv.getValor_total() - ped.getTotalProductoAfiliado().longValueExact();
				iv.setCantidad(iv.getCantidad() - ped.getCantidad());
				iv.setValor_total(valTotProductoAfiliado);
				this.invDistDAO.save(iv);
				valTotProductoAfiliado = ped.getTotalProductoAfiliado().longValueExact();
				valTotPedidoAfiliado += valTotProductoAfiliado;
			}
			/*
			 * Graba Saldo Distribuidor
			 */
			SaldoPedidoDistribuidor spd = this.saldoPedidoDistribuidorDAO.consultarSaldoDistribuidor(pedido.getDistribuidor());
			if (spd == null) {
				spd = new SaldoPedidoDistribuidor();
				spd.setDistribuidor(pedido.getDistribuidor());
				spd.setSaldo(0);
				spd.setSaldoAbonado(0);
			}
			double saldo = spd.getSaldo() - valTotPedidoAfiliado;
			spd.setSaldo(saldo);
			retorno = this.saldoPedidoDistribuidorDAO.guardar(spd);
			/*
			 * Elimina Pedido
			 */
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