package co.com.multinivel.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import co.com.multinivel.dto.UsuarioDTO;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.User;
import co.com.multinivel.util.Pagina;
import co.com.multinivel.util.ParametrosEnum;

@Component
public class UsuarioDAOImp implements UsuarioDAO {
	private static Logger log = Logger.getLogger(UsuarioDAOImp.class);

	@PersistenceContext
	private EntityManager entityManager;

	public User consultar(String codigo) throws MultinivelDAOException {
		User usuario = null;
		try {
			usuario = (User) this.entityManager.find(User.class, codigo);
		} catch (Exception localException) {
		}
		return usuario;
	}

	public void eliminar(User usuario) throws MultinivelDAOException {
		try {
			User usuarioConsultado = consultar(usuario.getUsername());
			if (usuarioConsultado != null) {
				this.entityManager.remove(usuarioConsultado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ingresar(User usuario) throws MultinivelDAOException {
		try {
			User usuarioConsultado = consultar(usuario.getUsername());
			if (usuarioConsultado == null) {
				usuario.setEnabled(Byte.parseByte("1"));
				this.entityManager.persist(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actualizar(User usuario) throws MultinivelDAOException {
		try {
			usuario.setEnabled(Byte.parseByte("1"));
			this.entityManager.merge(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<User> listar() throws MultinivelDAOException {
		Query query = this.entityManager.createQuery("from User");
		List<User> lista = query.getResultList();
		return lista;
	}

	public User consultar(User pusuario) throws MultinivelDAOException {
		User usuario = null;
		try {
			Query query = this.entityManager
					.createQuery(" select u  from User  u where u.username=:usuario and u.password=:clave ");
			query.setParameter("usuario", pusuario.getUsername());
			query.setParameter("clave", pusuario.getPassword());
			List<User> usuarios = query.getResultList();
			if (usuarios.size() > 0) {
				usuario = (User) usuarios.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public Pagina listarConDistribuidor(int pagina) throws MultinivelDAOException {
		int max = ParametrosEnum.TAM_PAGINA.getValorInt() * pagina;
		int min = ParametrosEnum.TAM_PAGINA.getValorInt() * (pagina - 1);
		List<Object> lista = null;
		Pagina p = new Pagina();
		List listaUsuario = new ArrayList<UsuarioDTO>();
		try {
			String st_count = " SELECT count(1) FROM t_afiliados p inner join  (SELECT (select password from users where username=ceduladistribuidor  )claveDistribuidor,ceduladistribuidor,username,u.password,u.enabled FROM t_afiliados t,users u where u.username=t.cedula)d on d.ceduladistribuidor=p.cedula; ";
			String sql = " SELECT  CONCAT (P.NOMBRE ,' ',IF(P.APELLIDO IS NULL,'',P.APELLIDO))distribuidor,d.username,d.password,d.enabled,claveDistribuidor FROM t_afiliados p inner join  (SELECT (select password from users where username=ceduladistribuidor  )claveDistribuidor,ceduladistribuidor,username,u.password,u.enabled FROM t_afiliados t,users u where u.username=t.cedula)d on d.ceduladistribuidor=p.cedula ";
			sql += " LIMIT " + min + "," + max;
			Query q = this.entityManager.createNativeQuery(sql);
			Query count = this.entityManager.createNativeQuery(st_count);
			List result = q.getResultList();
			BigInteger total = (BigInteger) count.getResultList().get(0);
			int s = result.size();
			if (s > 0) {
				lista = new ArrayList();
				for (int i = 0; i < s; i++) {
					Object obj = result.get(i);
					Object[] objectArray = (Object[]) obj;

					String distribuidor = (String) objectArray[0];
					String usuario = (String) objectArray[1];
					String clave = (String) objectArray[2];
					byte enable = ((Byte) objectArray[3]).byteValue();
					String claveDistribuidor = (String) objectArray[4];

					UsuarioDTO usuarioDTO = new UsuarioDTO();
					usuarioDTO.setDistribuidor(distribuidor);
					usuarioDTO.setPassword(clave);
					usuarioDTO.setUsername(usuario);
					usuarioDTO.setEnabled(enable);
					usuarioDTO.setPasswordDistribuidor(claveDistribuidor);

					listaUsuario.add(usuarioDTO);
				}
			}

			p.setContent(listaUsuario);
			p.setNumber(pagina);
			p.setTotalElements(total.intValue());
			p.setTotalPages(total.intValue() / ParametrosEnum.TAM_PAGINA.getValorInt());
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al listar los afiliados por nivel", getClass());
		}
		return p;
	}

	public List<UsuarioDTO> buscar(String nomFiltro, String filtro) throws MultinivelDAOException {
		List<Object> lista = null;
		List<UsuarioDTO> listaUsuario = new ArrayList<UsuarioDTO>();
		try {
			String sql = " SELECT  CONCAT (P.NOMBRE ,' ',IF(P.APELLIDO IS NULL,'',P.APELLIDO))distribuidor,d.username,d.password,d.enabled,claveDistribuidor FROM t_afiliados p inner join  (SELECT (select password from users where username=ceduladistribuidor  )claveDistribuidor,ceduladistribuidor,username,u.password,u.enabled FROM t_afiliados t,users u where u.username=t.cedula)d on d.ceduladistribuidor=p.cedula where p."
					+ nomFiltro + " like '%" + filtro + "%';";
			Query q = this.entityManager.createNativeQuery(sql);
			List result = q.getResultList();
			int s = result.size();
			if (s > 0) {
				lista = new ArrayList();
				for (int i = 0; i < s; i++) {
					Object obj = result.get(i);
					Object[] objectArray = (Object[]) obj;

					String distribuidor = (String) objectArray[0];
					String usuario = (String) objectArray[1];
					String clave = (String) objectArray[2];
					byte enable = ((Byte) objectArray[3]).byteValue();
					String claveDistribuidor = (String) objectArray[4];

					UsuarioDTO usuarioDTO = new UsuarioDTO();
					usuarioDTO.setDistribuidor(distribuidor);
					usuarioDTO.setPassword(clave);
					usuarioDTO.setUsername(usuario);
					usuarioDTO.setEnabled(enable);
					usuarioDTO.setPasswordDistribuidor(claveDistribuidor);

					listaUsuario.add(usuarioDTO);
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MultinivelDAOException("Error al listar los afiliados por nivel", getClass());
		}
		return listaUsuario;
	}
}
