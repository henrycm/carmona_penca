package co.com.multinivel.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.Parametro;

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
 * Qualified Name: co.com.multinivel.dao.ParametroDAOImp
 */