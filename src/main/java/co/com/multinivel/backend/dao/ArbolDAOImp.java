package co.com.multinivel.backend.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.primefaces.model.TreeNode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.frontend.arbol.TreeNodeImpl;
import co.com.multinivel.shared.dto.DatosArbol;

@Repository
@Transactional
public class ArbolDAOImp implements ArbolDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public DatosArbol getArbol(String papa, String periodo) throws Exception {

		String sql = " Select a.Papa GranPapa, Gp.Nombre+' '+IsNull(Gp.Apellido,'')NombreGp, \n"
				+ "h.Cedula_Papa Papa, p.Nombre+' '+IsNull(p.Apellido,'')NombreP, \n"
				+ "a.Descendiente, h.Nombre+' '+IsNull(h.Apellido,'')Nombre, a.Nivel, \n"
				+ "IsNull(Cast(Round(Cp.ValorPeriodo,0)As Numeric(9,0)),0)ValorPeriodo\n" + "From T_Arbol a\n"
				+ "Inner Join T_Afiliados Gp On a.Papa=Gp.Cedula\n" + "Inner Join T_Afiliados h On a.Descendiente=h.Cedula\n"
				+ "Inner Join T_Afiliados p On h.Cedula_Papa=p.Cedula\n"
				+ "Left Join (Select Afiliado, Fecha, Sum(TotalPedido)ValorPeriodo From T_Consumos\n"
				+ "Where Right('00'+Cast(Month(Fecha) As Varchar(2)),2)+'/'+Cast(Year(Fecha) As Varchar(4))=? \n"
				+ "Group By Afiliado, Fecha)Cp On a.Descendiente=Cp.Afiliado \n" + "Where a.Papa=?\n"
				+ "Order By a.Nivel Asc, h.Cedula_Papa Asc, a.Descendiente Asc ";

		Query q = this.entityManager.createNativeQuery(sql);
		q.setParameter(1, periodo);
		q.setParameter(2, papa);

		List<?> result = q.getResultList();

		String granPapa = "";
		TreeNodeImpl mainTreeNodeImpl = null;
		HashMap<String, TreeNode> nodesHashMap = new HashMap<String, TreeNode>();

		int i = result.size();
		if (i > 0) {
			for (int j = 0; j < i; j++) {
				Object resultList = result.get(j);
				Object[] arrayOfObject = (Object[]) resultList;

				if (j == 0) {
					granPapa = arrayOfObject[1].toString().trim();
					mainTreeNodeImpl = new TreeNodeImpl(granPapa, null);
				}

				String findCedula = arrayOfObject[2].toString().trim();
				String findDescendiente = arrayOfObject[3].toString().trim();
				String cedula = arrayOfObject[4].toString().trim();
				String descendiente = arrayOfObject[5].toString().trim();
				int nivel = Integer.parseInt(arrayOfObject[6].toString());
				String valorPeriodo = arrayOfObject[7].toString();

				String valor = ",[$ " + valorPeriodo + "]";
				String idTreeDataItem = "[" + nivel + "],[" + cedula + "],[" + descendiente + "]";
				String findTreeDataItem = "[" + (nivel - 1) + "],[" + findCedula + "],[" + findDescendiente + "]";
				String treeDataItem = idTreeDataItem + "" + valor;

				TreeNode treeNode = null;
				TreeNodeImpl followingTreeNodeImpl = null;

				if (nivel == 1) {
					followingTreeNodeImpl = new TreeNodeImpl(treeDataItem, mainTreeNodeImpl);
					nodesHashMap.put(idTreeDataItem, followingTreeNodeImpl);
				} else {
					treeNode = findNode(nodesHashMap, findTreeDataItem);
					followingTreeNodeImpl = new TreeNodeImpl(treeDataItem, treeNode);
					nodesHashMap.put(idTreeDataItem, followingTreeNodeImpl);
				}
			}
		}
		DatosArbol localDatosArbol = new DatosArbol(i, mainTreeNodeImpl, granPapa);
		return localDatosArbol;
	}

	private TreeNode findNode(Map<String, TreeNode> nodesHashMap, String findTreeDataItem) {
		TreeNode treeNode = null;
		if (nodesHashMap.containsKey(findTreeDataItem)) {
			treeNode = (TreeNode) nodesHashMap.get(findTreeDataItem);
		}
		return treeNode;
	}
}
