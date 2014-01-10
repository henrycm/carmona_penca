package co.com.multinivel.dao;

import java.math.BigDecimal;
import java.util.List;

import co.com.multinivel.dto.ConsumoDTO;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.Afiliado;
import co.com.multinivel.model.Consumo;

public abstract interface ConsumoDAO {
	public abstract boolean ingresar(Consumo paramConsumo) throws MultinivelDAOException;

	public abstract List<Object> consultar(Consumo paramConsumo) throws MultinivelDAOException;

	public abstract List<Consumo> listar(Consumo paramConsumo) throws MultinivelDAOException;

	public abstract int ultimoConsumo(Consumo paramConsumo) throws MultinivelDAOException;

	public abstract BigDecimal consultarSaldoPorPeriodoDistribuidor(Consumo paramConsumo)
			throws MultinivelDAOException;

	public abstract BigDecimal consultarSaldoPorPeriodoDeAfiliados(Consumo paramConsumo)
			throws MultinivelDAOException;

	public abstract Consumo buscar(Consumo paramConsumo) throws MultinivelDAOException;

	public abstract List<Object> calcularConsumosPeriodo(String paramString1, String paramString2)
			throws MultinivelDAOException;

	public abstract List<Object> listarConsumosRed(Afiliado paramAfiliado, String paramString)
			throws MultinivelDAOException;

	public abstract List<Object> listarConsumosPeriodo(ConsumoDTO paramConsumoDTO)
			throws MultinivelDAOException;

	public abstract boolean eliminar(Consumo paramConsumo) throws MultinivelDAOException;

	public abstract List<Object> listarConsumosPeriodoAEliminar(ConsumoDTO paramConsumoDTO)
			throws MultinivelDAOException;

	public abstract List<Object> listarConsumosAfiliado(ConsumoDTO paramConsumoDTO)
			throws MultinivelDAOException;

	public abstract List<Object> listarConsumosProducto(ConsumoDTO paramConsumoDTO)
			throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.dao.ConsumoDAO
 * 
 * 
 */