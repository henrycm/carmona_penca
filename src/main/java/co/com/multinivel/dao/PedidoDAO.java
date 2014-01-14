package co.com.multinivel.dao;

import java.math.BigDecimal;
import java.util.List;

import co.com.multinivel.dto.PedidoDTO;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.Pedido;

public abstract interface PedidoDAO {
	public abstract boolean ingresarPedido(Pedido paramPedido) throws MultinivelDAOException;

	public abstract boolean eliminarPedido(Pedido paramPedido) throws MultinivelDAOException;

	public abstract List<Object> consultar(Pedido paramPedido) throws MultinivelDAOException;

	public abstract List<Pedido> listar(Pedido paramPedido) throws MultinivelDAOException;

	public abstract int ultimoPedido(Pedido paramPedido) throws MultinivelDAOException;

	public abstract BigDecimal consultarSaldoPorPeriodoDistribuidor(Pedido paramPedido)
			throws MultinivelDAOException;

	public abstract boolean actualizar(Pedido paramPedido) throws MultinivelDAOException;

	public abstract Pedido buscar(Pedido paramPedido) throws MultinivelDAOException;

	public abstract List<Object> listarPorPeriodo(PedidoDTO paramPedidoDTO)
			throws MultinivelDAOException;

	public abstract List<Object> listarPedidosAEliminar(PedidoDTO paramPedidoDTO)
			throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.dao.PedidoDAO
 */