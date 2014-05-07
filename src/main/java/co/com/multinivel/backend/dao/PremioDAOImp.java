package co.com.multinivel.backend.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.Premio;
import co.com.multinivel.shared.exception.MultinivelDAOException;

@Repository
@Transactional
public class PremioDAOImp implements PremioDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public List<Premio> consultar(Premio premio) throws MultinivelDAOException {
		List<Premio> lista = new ArrayList<Premio>();
		try {
			String sql = "SELECT CODIGO,CONSUMOS,CONSUMOMAXIMO,MANTENERCONSUMO3MESES FROM T_PREMIOS WHERE NIVEL=?";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, premio.getNivel());

			List<?> result = q.getResultList();
			int s = result.size();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				int codigo = ((Integer) objectArray[0]).intValue();
				int consumoMinimo = ((Integer) objectArray[1]).intValue();
				int consumoMaximo = ((Integer) objectArray[2]).intValue();
				String mantenerConsumo3Meses = (String) objectArray[3];
				Premio premioTemp = new Premio();
				premioTemp.setCodigo(codigo);
				premioTemp.setConsumos(consumoMinimo);
				premioTemp.setConsumoMaximo(consumoMaximo);
				premioTemp.setMantenerConsumo3Meses(mantenerConsumo3Meses);

				lista.add(premioTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return lista;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.PremioDAOImp
 */