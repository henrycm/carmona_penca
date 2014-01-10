package co.com.multinivel.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.Premio;

@Stateless
@Local({ PremioDAO.class })
public class PremioDAOImp implements PremioDAO {
	@PersistenceContext(unitName = "multinivelUnit")
	private EntityManager entityManager;

	public List<Premio> consultar(Premio premio) throws MultinivelDAOException {
		List<Premio> lista = new ArrayList();
		int filtros = 0;
		try {
			String sql = "SELECT CODIGO,CONSUMOS,CONSUMOMAXIMO,MANTENERCONSUMO3MESES FROM T_PREMIOS WHERE NIVEL=?";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, premio.getNivel());

			List result = q.getResultList();
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
 * Qualified Name: co.com.multinivel.dao.PremioDAOImp
 * 
 * 
 */