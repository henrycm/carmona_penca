package co.com.multinivel.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import co.com.multinivel.backend.model.ValidacionCompensacionDistribuidor;
import co.com.multinivel.shared.exception.MultinivelDAOException;

@Component
public class ValidacionCompensacionDistribuidorDAOImp implements ValidarCompDistribuidorDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public ValidacionCompensacionDistribuidor consultar(
			ValidacionCompensacionDistribuidor validacionCompensacionDistribuidor)
			throws MultinivelDAOException {
		Query query = this.entityManager
				.createQuery("from ValidacionCompensacionDistribuidor v where v.id.periodo=? and v.id.distribuidor=?");
		query.setParameter(1, validacionCompensacionDistribuidor.getId().getPeriodo());
		query.setParameter(2, validacionCompensacionDistribuidor.getId().getDistribuidor());
		ValidacionCompensacionDistribuidor valida = null;
		try {
			valida = (ValidacionCompensacionDistribuidor) query.getResultList().get(0);
		} catch (Exception e) {
			valida = null;
		}
		return valida;
	}

	public boolean eliminar(ValidacionCompensacionDistribuidor validacionCompensacionDistribuidor)
			throws MultinivelDAOException {
		return false;
	}

	public boolean ingresar(ValidacionCompensacionDistribuidor validacionCompensacionDistribuidor)
			throws MultinivelDAOException {
		boolean retorno = false;
		ValidacionCompensacionDistribuidor validCompDistribuidorConsulta = consultar(validacionCompensacionDistribuidor);
		if (validCompDistribuidorConsulta == null) {
			this.entityManager.persist(validacionCompensacionDistribuidor);
			retorno = true;
		}
		return retorno;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name:
 * co.com.multinivel.backend.dao.ValidacionCompensacionDistribuidorDAOImp
 */