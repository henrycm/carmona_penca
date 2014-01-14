package co.com.multinivel.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import co.com.multinivel.backend.model.Parametro;
import co.com.multinivel.shared.exception.MultinivelDAOException;

@Component
public class ParametroDAOImp implements ParametroDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public List<Parametro> listar() throws MultinivelDAOException {
		Query query = this.entityManager.createQuery("from Parametro");
		List<Parametro> lista = query.getResultList();
		return lista;
	}

	public Parametro obtenerValor(String parametro) throws MultinivelDAOException {
		return (Parametro) this.entityManager.find(Parametro.class, parametro);
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.ParametroDAOImp
 */