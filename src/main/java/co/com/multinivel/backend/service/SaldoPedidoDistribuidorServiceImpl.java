package co.com.multinivel.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.SaldoPedidoDistribuidorDAO;
import co.com.multinivel.backend.model.SaldoPedidoDistribuidor;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
public class SaldoPedidoDistribuidorServiceImpl implements SaldoPedidoDistribuidorService {
	@Autowired
	private SaldoPedidoDistribuidorDAO saldoPedidoDistribuidorDAO;

	@Override
	public SaldoPedidoDistribuidor consultarSaldoDistribuidor(String distribuidor) throws MultinivelServiceException {
		SaldoPedidoDistribuidor saldoPedidoDistribuidor = null;
		try {
			saldoPedidoDistribuidor = this.saldoPedidoDistribuidorDAO.consultarSaldoDistribuidor(distribuidor);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return saldoPedidoDistribuidor;
	}

	@Override
	public boolean guardar(SaldoPedidoDistribuidor saldo) throws MultinivelServiceException {
		boolean retorno = Boolean.FALSE;
		try {
			retorno = this.saldoPedidoDistribuidorDAO.guardar(saldo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}
}
