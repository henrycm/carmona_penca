package co.com.multinivel.dao;

import java.util.List;

import co.com.multinivel.dto.AfiliadoDTO;
import co.com.multinivel.dto.Nodo;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.Afiliado;

public abstract interface AfiliadoDAO {
	public abstract void ingresar(Afiliado paramAfiliado) throws MultinivelDAOException;

	public abstract void actualizar(Afiliado paramAfiliado) throws MultinivelDAOException;

	public abstract Afiliado consultar(String paramString) throws MultinivelDAOException;

	public abstract Afiliado consultar(String paramString1, String paramString2, String paramString3)
			throws MultinivelDAOException;

	public abstract List<AfiliadoDTO> buscar(String paramString1, String paramString2,
			String paramString3) throws MultinivelDAOException;

	public abstract boolean eliminar(Afiliado paramAfiliado) throws MultinivelDAOException;

	public abstract List<Afiliado> listar() throws MultinivelDAOException;

	public abstract List<Object> listarPorNivel(String paramString) throws MultinivelDAOException;

	public abstract List<AfiliadoDTO> buscarDistribuidor(String paramString1, String paramString2)
			throws MultinivelDAOException;

	public abstract void actualizarAfiliadoADistribuidor(Afiliado paramAfiliado)
			throws MultinivelDAOException;

	public abstract List<AfiliadoDTO> listarDistribuidores() throws MultinivelDAOException;

	public abstract boolean validarNivelesRed(Afiliado paramAfiliado, String paramString)
			throws MultinivelDAOException;

	public abstract List<Object> listaAfiliadosPorDistribuidor(String paramString)
			throws MultinivelDAOException;

	public abstract boolean cambiarDocumento(String paramString1, String paramString2)
			throws MultinivelDAOException;

	public abstract int contarAfiliacionesPorPeriodoDistribuidor(String paramString1,
			String paramString2) throws MultinivelDAOException;

	public abstract int consultarIdDistribuidor(String paramString) throws MultinivelDAOException;

	public abstract List<Nodo> ArbolAfiliado(String paramString) throws MultinivelDAOException;

	public abstract List<Nodo> generarHijosAfiliado(String paramString)
			throws MultinivelDAOException;

	public abstract List<Object> listarAfiliadosDistribuidorPorPeriodo(String paramString)
			throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.dao.AfiliadoDAO
 */