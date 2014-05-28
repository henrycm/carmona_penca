package co.com.multinivel.backend.service;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.AfiliadoDAO;
import co.com.multinivel.backend.dao.ArbolDAO;
import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.shared.dto.AfiliadoDTO;
import co.com.multinivel.shared.dto.DatosArbol;
import co.com.multinivel.shared.dto.Nodo;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
public class AfiliadoServiceImpl implements AfiliadoService {
	@EJB
	private AfiliadoDAO afiliadoDAO;

	@Autowired
	private ArbolDAO arbolDAO;

	public void actualizar(Afiliado afiliado) throws MultinivelServiceException {
		try {
			this.afiliadoDAO.actualizar(afiliado);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public boolean borrar(Afiliado barrio) throws MultinivelServiceException {
		try {
			return this.afiliadoDAO.eliminar(barrio);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public Afiliado consultar(String codigo) throws MultinivelServiceException {
		try {
			return this.afiliadoDAO.consultar(codigo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public void ingresar(Afiliado barrio) throws MultinivelServiceException {
		try {
			this.afiliadoDAO.ingresar(barrio);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public List<Afiliado> listar() throws MultinivelServiceException {
		List<Afiliado> lista = null;
		try {
			lista = this.afiliadoDAO.listar();
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<AfiliadoDTO> buscar(String cedula, String nombre, String cedulaDistribuidor)
			throws MultinivelServiceException {
		List<AfiliadoDTO> lista = null;
		try {
			lista = this.afiliadoDAO.buscar(cedula, nombre, cedulaDistribuidor);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<Object> listarPorNivel(String red) throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.afiliadoDAO.listarPorNivel(red);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<AfiliadoDTO> buscarDistribuidor(String codigo, String nombre)
			throws MultinivelServiceException {
		List<AfiliadoDTO> lista = null;
		try {
			lista = this.afiliadoDAO.buscarDistribuidor(codigo, nombre);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<AfiliadoDTO> listarDistribuidores() throws MultinivelServiceException {
		List<AfiliadoDTO> lista = null;
		try {
			lista = this.afiliadoDAO.listarDistribuidores();
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public void actualizarAfiliadoADistribuidor(Afiliado afiliado)
			throws MultinivelServiceException {
		try {
			this.afiliadoDAO.actualizarAfiliadoADistribuidor(afiliado);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public List<Object> listaAfiliadosPorDistribuidor(String periodo)
			throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.afiliadoDAO.listaAfiliadosPorDistribuidor(periodo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public boolean cambiarDocumento(String documentoActual, String documentoNuevo)
			throws MultinivelServiceException {
		boolean retorno = Boolean.FALSE;
		try {
			retorno = this.afiliadoDAO.cambiarDocumento(documentoActual, documentoNuevo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public int contarAfiliacionesPorPeriodoDistribuidor(String cedulaDistribuidor, String periodo)
			throws MultinivelServiceException {
		int retorno = 0;
		try {
			retorno = this.afiliadoDAO.contarAfiliacionesPorPeriodoDistribuidor(cedulaDistribuidor,
					periodo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public int consultarIdDistribuidor(String distribuidor) throws MultinivelServiceException {
		int retorno = 0;
		try {
			retorno = this.afiliadoDAO.consultarIdDistribuidor(distribuidor);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public List<Nodo> generarHijosAfiliado(String afiliado) throws MultinivelServiceException {
		List<Nodo> lista = null;
		try {
			lista = this.afiliadoDAO.generarHijosAfiliado(afiliado);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public List<Object> listarAfiliadosDistribuidorPorPeriodo(String periodo)
			throws MultinivelServiceException {
		List<Object> lista = null;
		try {
			lista = this.afiliadoDAO.listarAfiliadosDistribuidorPorPeriodo(periodo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public DatosArbol ArbolAfiliado(String cedula, String periodo) throws Exception {
		return this.arbolDAO.getArbol(cedula, periodo);
	}

	public List<Afiliado> buscar(String nomFiltro, String filtro)
			throws MultinivelServiceException
	{
		List<Afiliado> lista = null;
		try {
			lista = afiliadoDAO.buscar(nomFiltro, filtro);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return lista;
	}

	public void cambiarAdistribuidor(String cedula) throws MultinivelServiceException
	{
		Afiliado a;
		try {
			a = afiliadoDAO.consultar(cedula);
			a.setTipoAfiliado("2");
			afiliadoDAO.actualizar(a);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}

	public void cambiarAafiliado(String cedula, String dist_ant, String dist_nuevo)
			throws MultinivelServiceException
	{
		Afiliado a;
		try {
			afiliadoDAO.cambiarDistribuidor(dist_ant, dist_nuevo);
			a = afiliadoDAO.consultar(cedula);
			a.setTipoAfiliado("3");
			afiliadoDAO.actualizar(a);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}
}