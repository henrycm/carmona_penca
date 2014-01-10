package co.com.multinivel.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import co.com.multinivel.dao.AfiliadoDAO;
import co.com.multinivel.dto.AfiliadoDTO;
import co.com.multinivel.dto.Nodo;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.Afiliado;

@Stateless
@Local({ AfiliadoService.class })
public class AfiliadoServiceImpl implements AfiliadoService {
	@EJB
	private AfiliadoDAO afiliadoDAO;

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
		boolean retorno = false;
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
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.service.AfiliadoServiceImpl
 * 
 * 
 */