package co.com.multinivel.backend.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.ConsumoDAO;
import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.backend.model.Consumo;
import co.com.multinivel.shared.dto.ConsumoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
public class ConsumoServiceImpl implements ConsumoService {
	@EJB
	private ConsumoDAO consumoDAO;

	public boolean ingresar(Consumo consumo) throws MultinivelServiceException {
		boolean retorno = false;
		try {
			retorno = this.consumoDAO.ingresar(consumo);
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
		boolean retorno = false;
		try {
			BigDecimal saldoAfiliados = this.consumoDAO
					.consultarSaldoPorPeriodoDeAfiliados(consumo);
			BigDecimal saldoDistribuidor = this.consumoDAO
					.consultarSaldoPorPeriodoDeAfiliados(consumo);
			if (saldoAfiliados.intValue() < saldoDistribuidor.intValue()) {
				retorno = true;
			}
		} catch (MultinivelDAOException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public BigDecimal consultarSaldoPorPeriodoDeAfiliados(Consumo consumo)
			throws MultinivelServiceException {
		BigDecimal saldoAfiliados = null;
		try {
			saldoAfiliados = this.consumoDAO.consultarSaldoPorPeriodoDeAfiliados(consumo);
		} catch (MultinivelDAOException e) {
			e.printStackTrace();
		}
		return saldoAfiliados;
	}

	public BigDecimal consultarSaldoPorPeriodoDistribuidor(Consumo consumo)
			throws MultinivelServiceException {
		BigDecimal saldoDistribuidor = new BigDecimal(0);
		try {
			saldoDistribuidor = this.consumoDAO.consultarSaldoPorPeriodoDistribuidor(consumo);
		} catch (MultinivelDAOException e) {
			e.printStackTrace();
		}
		return saldoDistribuidor;
	}

	public Consumo buscar(Consumo consumo) throws MultinivelServiceException {
		return null;
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

	public List<Object> listarConsumosRed(Afiliado distribuidor, String periodo)
			throws MultinivelServiceException {
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

	public List<Object> calcularConsumosPeriodo(String periodo, String red)
			throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.consumoDAO.calcularConsumosPeriodo(periodo, red);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public boolean eliminar(Consumo pedido) throws MultinivelServiceException {
		boolean retorno = false;
		try {
			retorno = this.consumoDAO.eliminar(pedido);
		} catch (MultinivelDAOException e) {
			e.printStackTrace();
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public List<Object> listarConsumosPeriodoAEliminar(ConsumoDTO consumo)
			throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.consumoDAO.listarConsumosPeriodoAEliminar(consumo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<Object> listarConsumosAfiliado(ConsumoDTO consumo)
			throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.consumoDAO.listarConsumosAfiliado(consumo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<Object> listarConsumosProducto(ConsumoDTO consumo)
			throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.consumoDAO.listarConsumosProducto(consumo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.ConsumoServiceImpl
 */