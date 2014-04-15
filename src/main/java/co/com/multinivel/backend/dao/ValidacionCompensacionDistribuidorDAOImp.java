package co.com.multinivel.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.ValidacionCompensacionDistribuidor;
import co.com.multinivel.shared.exception.MultinivelDAOException;

@Repository
@Transactional
public class ValidacionCompensacionDistribuidorDAOImp implements ValidarCompDistribuidorDAO {
	@PersistenceContext
	private EntityManager entityManager;
	private static Logger log = Logger.getLogger(ValidacionCompensacionDistribuidorDAOImp.class);

	public ValidacionCompensacionDistribuidor consultar(ValidacionCompensacionDistribuidor validacionCompensacionDistribuidor)
			throws MultinivelDAOException {
		Query query = this.entityManager.createQuery("from ValidacionCompensacionDistribuidor v where v.id.periodo=? and v.id.distribuidor=?");
		query.setParameter(1, validacionCompensacionDistribuidor.getId().getPeriodo());
		query.setParameter(2, validacionCompensacionDistribuidor.getId().getDistribuidor());
		ValidacionCompensacionDistribuidor valida = null;
		try {
			if (query.getResultList().size() > 0) {
				valida = (ValidacionCompensacionDistribuidor) query.getResultList().get(0);
			}
		} catch (Exception e) {
			log.error(e);
			valida = null;
		}
		return valida;
	}

	public boolean eliminar(ValidacionCompensacionDistribuidor validacionCompensacionDistribuidor) throws MultinivelDAOException {
		return false;
	}

	public boolean ingresar(ValidacionCompensacionDistribuidor validacionCompensacionDistribuidor) throws MultinivelDAOException {
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