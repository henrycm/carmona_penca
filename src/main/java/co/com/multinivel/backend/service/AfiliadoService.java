package co.com.multinivel.backend.service;

import java.util.List;

import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.shared.dto.AfiliadoDTO;
import co.com.multinivel.shared.dto.DatosArbol;
import co.com.multinivel.shared.dto.Nodo;
import co.com.multinivel.shared.exception.MultinivelServiceException;

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

	public DatosArbol ArbolAfiliado(String paramString) throws Exception;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.AfiliadoService
 */