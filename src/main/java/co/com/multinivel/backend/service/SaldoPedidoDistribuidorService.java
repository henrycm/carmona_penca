package co.com.multinivel.backend.service;

import co.com.multinivel.backend.model.SaldoPedidoDistribuidor;
import co.com.multinivel.shared.exception.MultinivelServiceException;

public interface SaldoPedidoDistribuidorService {
	public SaldoPedidoDistribuidor consultarSaldoDistribuidor(String distribuidor) throws MultinivelServiceException;

	public boolean guardar(SaldoPedidoDistribuidor saldo) throws MultinivelServiceException;
}
