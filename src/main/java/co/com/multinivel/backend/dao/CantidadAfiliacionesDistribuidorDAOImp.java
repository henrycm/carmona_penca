package co.com.multinivel.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.CantidadAfiliacionesDistribuidor;
import co.com.multinivel.shared.exception.MultinivelDAOException;

@Repository
@Transactional
public class CantidadAfiliacionesDistribuidorDAOImp implements CantidadAfiliacionesDistribuidorDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public boolean ingresar(CantidadAfiliacionesDistribuidor cantidadAfiliacionesDistribuidor) throws MultinivelDAOException {
		boolean retorno = Boolean.FALSE;
		try {
			this.entityManager.merge(cantidadAfiliacionesDistribuidor);
			retorno = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error ingresando el producto -" + e.getMessage(), getClass());
		}
		return retorno;
	}

	public CantidadAfiliacionesDistribuidor consultar(CantidadAfiliacionesDistribuidor cantidadAfiliacionesDistribuidor)
			throws MultinivelDAOException {
		Query query = this.entityManager.createQuery("from CantidadAfiliacionesDistribuidor v where v.id.distribuidor=?");
		query.setParameter(1, cantidadAfiliacionesDistribuidor.getDistribuidor());
		CantidadAfiliacionesDistribuidor valida = null;
		try {
			valida = (CantidadAfiliacionesDistribuidor) query.getResultList().get(0);
		} catch (Exception e) {
			valida = null;
		}
		return valida;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name:
 * co.com.multinivel.backend.dao.CantidadAfiliacionesDistribuidorDAOImp
 */