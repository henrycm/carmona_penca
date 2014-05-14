package co.com.multinivel.backend.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.ConsumoDAO;
import co.com.multinivel.backend.dao.InventarioDistribuidorDAO;
import co.com.multinivel.backend.dao.SaldoPedidoDistribuidorDAO;
import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.backend.model.Consumo;
import co.com.multinivel.backend.model.DetConsumo;
import co.com.multinivel.backend.model.InventarioDistribuidor;
import co.com.multinivel.backend.model.InventarioDistribuidorPK;
import co.com.multinivel.backend.model.SaldoPedidoDistribuidor;
import co.com.multinivel.shared.dto.ConsumoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
public class ConsumoServiceImpl implements ConsumoService {
	@EJB
	private ConsumoDAO consumoDAO;
	@Autowired
	private InventarioDistribuidorDAO invDistDAO;
	@Autowired
	private SaldoPedidoDistribuidorDAO saldoPedidoDistribuidorDAO;

	public boolean ingresar(Consumo consumo) throws MultinivelServiceException {
		boolean retorno = Boolean.FALSE;
		try {
			/*
			 * Graba Consumo
			 */
			retorno = this.consumoDAO.ingresar(consumo);
			/*
			 * Graba Inventario Distribuidor
			 */
			for (DetConsumo dc : consumo.getTDetConsumos()) {
				InventarioDistribuidor iv = invDistDAO.findOne(new InventarioDistribuidorPK(consumo.getDistribuidor(), dc.getCodigoProducto()));
				if (iv == null) {
					iv = new InventarioDistribuidor(new InventarioDistribuidorPK(consumo.getDistribuidor(), dc.getCodigoProducto()));
				}
				iv.setCantidad(iv.getCantidad() - dc.getCantidad());
				iv.setValor_total(iv.getValor_total() - dc.getTotalProducto().longValueExact());
				invDistDAO.save(iv);
			}
			/*
			 * Graba Saldo Distribuidor
			 */
			SaldoPedidoDistribuidor spd = this.saldoPedidoDistribuidorDAO.consultarSaldoDistribuidor(consumo.getDistribuidor());
			if (spd == null) {
				spd = new SaldoPedidoDistribuidor();
				spd.setDistribuidor(consumo.getDistribuidor());
				spd.setSaldo(0);
				spd.setSaldoAbonado(0);
			}
			double saldo = spd.getSaldo() - consumo.getTotalpedido().doubleValue();
			double saldoAbonado = spd.getSaldoAbonado() - consumo.getTotalpedido().doubleValue();
			spd.setSaldo(saldo);
			spd.setSaldoAbonado(saldoAbonado);
			retorno = this.saldoPedidoDistribuidorDAO.guardar(spd);
		} catch (MultinivelDAOException e) {
			e.printStackTrace();
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public List<Object> consultar(Consumo consumo) throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.consumoDAO.consultar(consumo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public int ultimoConsumo(Consumo consumo) throws MultinivelServiceException {
		int retorno = 0;
		try {
			retorno = this.consumoDAO.ultimoConsumo(consumo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public boolean validarSaldoDistribuidor(Consumo consumo) throws MultinivelServiceException {
		boolean retorno = Boolean.FALSE;
		try {
			BigDecimal saldoAfiliados = this.consumoDAO.consultarSaldoPorPeriodoDeAfiliados(consumo);
			BigDecimal saldoDistribuidor = this.consumoDAO.consultarSaldoPorPeriodoDeAfiliados(consumo);
			if (saldoAfiliados.intValue() < saldoDistribuidor.intValue()) {
				retorno = Boolean.TRUE;
			}
		} catch (MultinivelDAOException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public BigDecimal consultarSaldoPorPeriodoDeAfiliados(Consumo consumo) throws MultinivelServiceException {
		BigDecimal saldoAfiliados = null;
		try {
			saldoAfiliados = this.consumoDAO.consultarSaldoPorPeriodoDeAfiliados(consumo);
		} catch (MultinivelDAOException e) {
			e.printStackTrace();
		}
		return saldoAfiliados;
	}

	public BigDecimal consultarSaldoPorPeriodoDistribuidor(Consumo consumo) throws MultinivelServiceException {
		BigDecimal saldoDistribuidor = new BigDecimal(0);
		try {
			saldoDistribuidor = this.consumoDAO.consultarSaldoPorPeriodoDistribuidor(consumo);
		} catch (MultinivelDAOException e) {
			e.printStackTrace();
		}
		return saldoDistribuidor;
	}

	public BigDecimal consultarConsumoTotalAfiliadoPeriodo(String periodo, String afiliado) throws MultinivelServiceException {
		try {
			return consumoDAO.consultarConsumoTotalAfiliadoPeriodo(periodo, afiliado);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public List<Consumo> listar(Consumo consumo) throws MultinivelServiceException {
		List<Consumo> lista = null;
		try {
			lista = this.consumoDAO.listar(consumo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<Object> listarConsumosRed(Afiliado distribuidor, String periodo) throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.consumoDAO.listarConsumosRed(distribuidor, periodo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<Object> listarConsumosPeriodo(ConsumoDTO consumo) throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.consumoDAO.listarConsumosPeriodo(consumo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<Object> calcularConsumosPeriodo(String periodo, String red) throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.consumoDAO.calcularConsumosPeriodo(periodo, red);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public boolean eliminar(Consumo consumo) throws MultinivelServiceException {
		boolean retorno = Boolean.FALSE;
		try {
			/*
			 * Graba Inventario Distribuidor
			 */
			List<Object> lsConsumos = this.consumoDAO.consultar(consumo);
			Iterator<Object> iterador = lsConsumos.listIterator();

			while (iterador.hasNext()) {
				ConsumoDTO con = (ConsumoDTO) iterador.next();
				InventarioDistribuidor iv = this.invDistDAO
						.findOne(new InventarioDistribuidorPK(con.getCedulaDistribuidor(), con.getCodigoProducto()));
				if (iv == null) {
					iv = new InventarioDistribuidor(new InventarioDistribuidorPK(con.getCedulaDistribuidor(), con.getCodigoProducto()));
				}
				iv.setCantidad(iv.getCantidad() + con.getCantidad());
				iv.setValor_total(iv.getValor_total() + con.getTotalProducto().longValueExact());
				this.invDistDAO.save(iv);
			}
			/*
			 * Graba Saldo Distribuidor
			 */
			SaldoPedidoDistribuidor spd = this.saldoPedidoDistribuidorDAO.consultarSaldoDistribuidor(consumo.getDistribuidor());
			if (spd == null) {
				spd = new SaldoPedidoDistribuidor();
				spd.setDistribuidor(consumo.getDistribuidor());
				spd.setSaldo(0);
				spd.setSaldoAbonado(0);
			}
			double saldo = spd.getSaldo() + consumo.getTotalpedido().doubleValue();
			double saldoAbonos = spd.getSaldoAbonado() + consumo.getTotalpedido().doubleValue();
			spd.setSaldo(saldo);
			spd.setSaldoAbonado(saldoAbonos);
			retorno = this.saldoPedidoDistribuidorDAO.guardar(spd);
			/*
			 * Elimina Consumo
			 */
			retorno = this.consumoDAO.eliminar(consumo);
		} catch (MultinivelDAOException e) {
			e.printStackTrace();
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public List<Object> listarConsumosPeriodoAEliminar(ConsumoDTO consumo) throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.consumoDAO.listarConsumosPeriodoAEliminar(consumo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<Object> listarConsumosAfiliado(ConsumoDTO consumo) throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.consumoDAO.listarConsumosAfiliado(consumo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<Object> listarConsumosProducto(ConsumoDTO consumo) throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.consumoDAO.listarConsumosProducto(consumo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	@Override
	public Consumo consultarConsumo(Consumo consumo) throws MultinivelServiceException {
		try {
			consumo = this.consumoDAO.consultarConsumo(consumo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return consumo;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.ConsumoServiceImpl
 */