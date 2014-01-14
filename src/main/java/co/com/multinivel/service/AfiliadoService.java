package co.com.multinivel.service;

import java.util.List;

import co.com.multinivel.dto.AfiliadoDTO;
import co.com.multinivel.dto.Nodo;
import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.Afiliado;

public abstract interface AfiliadoService {
	public abstract void ingresar(Afiliado paramAfiliado) throws MultinivelServiceException;

	public abstract void actualizar(Afiliado paramAfiliado) throws MultinivelServiceException;

	public abstract boolean borrar(Afiliado paramAfiliado) throws MultinivelServiceException;

	public abstract Afiliado consultar(String paramString) throws MultinivelServiceException;

	public abstract List<Afiliado> listar() throws MultinivelServiceException;

	public abstract List<AfiliadoDTO> buscar(String paramString1, String paramString2,
			String paramString3) throws MultinivelServiceException;

	public abstract List<Object> listarPorNivel(String paramString)
			throws MultinivelServiceException;

	public abstract List<AfiliadoDTO> buscarDistribuidor(String paramString1, String paramString2)
			throws MultinivelServiceException;

	public abstract List<AfiliadoDTO> listarDistribuidores() throws MultinivelServiceException;

	public abstract void actualizarAfiliadoADistribuidor(Afiliado paramAfiliado)
			throws MultinivelServiceException;

	public abstract List<Object> listaAfiliadosPorDistribuidor(String paramString)
			throws MultinivelServiceException;

	public abstract boolean cambiarDocumento(String paramString1, String paramString2)
			throws MultinivelServiceException;

	public abstract int contarAfiliacionesPorPeriodoDistribuidor(String paramString1,
			String paramString2) throws MultinivelServiceException;

	public abstract int consultarIdDistribuidor(String paramString)
			throws MultinivelServiceException;

	public abstract List<Nodo> generarHijosAfiliado(String paramString)
			throws MultinivelServiceException;

	public abstract List<Object> listarAfiliadosDistribuidorPorPeriodo(String paramString)
			throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.service.AfiliadoService
 */