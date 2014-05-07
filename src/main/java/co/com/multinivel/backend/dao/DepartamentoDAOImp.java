package co.com.multinivel.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.Departamento;
import co.com.multinivel.shared.exception.MultinivelDAOException;

@Repository
@Transactional
public class DepartamentoDAOImp implements DepartamentoDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Departamento> listar() throws MultinivelDAOException {
		Query query = this.entityManager.createQuery("from Departamento o order by o.descripcion");
		List<Departamento> lista = query.getResultList();
		return lista;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.DepartamentoDAOImp
 */