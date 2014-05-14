package co.com.multinivel.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.SaldoPedidoDistribuidor;
import co.com.multinivel.shared.exception.MultinivelDAOException;

@Repository
@Transactional
public class SaldoPedidoDistribuidorDAOImp implements SaldoPedidoDistribuidorDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public SaldoPedidoDistribuidor consultarSaldoDistribuidor(String distribuidor) throws MultinivelDAOException {
		return (SaldoPedidoDistribuidor) this.entityManager.find(SaldoPedidoDistribuidor.class, distribuidor);
	}

	@Override
	public boolean guardar(SaldoPedidoDistribuidor saldo) throws MultinivelDAOException {
		boolean retorno = Boolean.FALSE;
		try {
			this.entityManager.merge(saldo);
			retorno = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error ingresando el Saldo -" + e.getMessage(), getClass());
		}
		return retorno;
	}
}
