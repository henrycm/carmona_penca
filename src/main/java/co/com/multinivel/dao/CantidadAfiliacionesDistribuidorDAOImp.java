package co.com.multinivel.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.CantidadAfiliacionesDistribuidor;

@Stateless
@Local({ CantidadAfiliacionesDistribuidorDAO.class })
public class CantidadAfiliacionesDistribuidorDAOImp implements CantidadAfiliacionesDistribuidorDAO {
	@PersistenceContext(unitName = "multinivelUnit")
	private EntityManager entityManager;

	public boolean ingresar(CantidadAfiliacionesDistribuidor cantidadAfiliacionesDistribuidor)
			throws MultinivelDAOException {
		boolean retorno = false;
		try {
			CantidadAfiliacionesDistribuidor productoConsultado = consultar(cantidadAfiliacionesDistribuidor);
			if (productoConsultado == null) {
				this.entityManager.persist(cantidadAfiliacionesDistribuidor);
				retorno = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error ingresando el producto -" + e.getMessage(),
					getClass());
		}
		return retorno;
	}

	public CantidadAfiliacionesDistribuidor consultar(
			CantidadAfiliacionesDistribuidor cantidadAfiliacionesDistribuidor)
			throws MultinivelDAOException {
		Query query = this.entityManager
				.createQuery("from CantidadAfiliacionesDistribuidor v where v.id.periodo=? and v.id.distribuidor=?");
		query.setParameter(1, cantidadAfiliacionesDistribuidor.getId().getPeriodo());
		query.setParameter(2, cantidadAfiliacionesDistribuidor.getId().getDistribuidor());
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
 * Qualified Name: co.com.multinivel.dao.CantidadAfiliacionesDistribuidorDAOImp
 * 
 * 
 */