package co.com.multinivel.backend.dao;

import co.com.multinivel.backend.model.SaldoPedidoDistribuidor;
import co.com.multinivel.shared.exception.MultinivelDAOException;

public interface SaldoPedidoDistribuidorDAO {
	public SaldoPedidoDistribuidor consultarSaldoDistribuidor(String distribuidor) throws MultinivelDAOException;

	public boolean guardar(SaldoPedidoDistribuidor saldo) throws MultinivelDAOException;
}
