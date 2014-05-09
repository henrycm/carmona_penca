package co.com.multinivel.backend.service;

import java.math.BigDecimal;
import java.util.List;

import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.backend.model.Consumo;
import co.com.multinivel.shared.dto.ConsumoDTO;
import co.com.multinivel.shared.exception.MultinivelServiceException;

public abstract interface ConsumoService {
	public abstract boolean ingresar(Consumo paramConsumo) throws MultinivelServiceException;

	public abstract List<Object> consultar(Consumo paramConsumo) throws MultinivelServiceException;

	public abstract int ultimoConsumo(Consumo paramConsumo) throws MultinivelServiceException;

	public abstract boolean validarSaldoDistribuidor(Consumo paramConsumo)
			throws MultinivelServiceException;

	public abstract BigDecimal consultarSaldoPorPeriodoDistribuidor(Consumo paramConsumo)
			throws MultinivelServiceException;

	public abstract BigDecimal consultarSaldoPorPeriodoDeAfiliados(Consumo paramConsumo)
			throws MultinivelServiceException;

	public abstract Consumo buscar(Consumo paramConsumo) throws MultinivelServiceException;

	public abstract List<Consumo> listar(Consumo paramConsumo) throws MultinivelServiceException;

	public abstract List<Object> listarConsumosRed(Afiliado paramAfiliado, String paramString)
			throws MultinivelServiceException;

	public abstract List<Object> listarConsumosPeriodo(ConsumoDTO paramConsumoDTO)
			throws MultinivelServiceException;

	public abstract List<Object> calcularConsumosPeriodo(String paramString1, String paramString2)
			throws MultinivelServiceException;

	public abstract boolean eliminar(Consumo paramConsumo) throws MultinivelServiceException;

	public abstract List<Object> listarConsumosPeriodoAEliminar(ConsumoDTO paramConsumoDTO)
			throws MultinivelServiceException;

	public abstract List<Object> listarConsumosAfiliado(ConsumoDTO paramConsumoDTO)
			throws MultinivelServiceException;

	public abstract List<Object> listarConsumosProducto(ConsumoDTO paramConsumoDTO)
			throws MultinivelServiceException;

	public BigDecimal consultarConsumoTotalAfiliadoPeriodo(String periodo, String afiliado)
			throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.ConsumoService
 */