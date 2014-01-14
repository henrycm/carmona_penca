package co.com.multinivel.backend.service;

import java.math.BigDecimal;
import java.util.List;

import co.com.multinivel.backend.model.Pedido;
import co.com.multinivel.shared.dto.PedidoDTO;
import co.com.multinivel.shared.exception.MultinivelServiceException;

public abstract interface PedidoService {
	public abstract boolean ingresarPedido(Pedido paramPedido) throws MultinivelServiceException;

	public abstract boolean eliminarPedido(Pedido paramPedido) throws MultinivelServiceException;

	public abstract List<Object> consultar(Pedido paramPedido) throws MultinivelServiceException;

	public abstract int ultimoPedido(Pedido paramPedido) throws MultinivelServiceException;

	public abstract BigDecimal consultarSaldoPorPeriodoDistribuidor(Pedido paramPedido)
			throws MultinivelServiceException;

	public abstract boolean actualizar(Pedido paramPedido) throws MultinivelServiceException;

	public abstract Pedido buscar(Pedido paramPedido) throws MultinivelServiceException;

	public abstract List<Pedido> listar(Pedido paramPedido) throws MultinivelServiceException;

	public abstract List<Object> listarPorPeriodo(PedidoDTO paramPedidoDTO)
			throws MultinivelServiceException;

	public abstract List<Object> listarPedidosAEliminar(PedidoDTO paramPedidoDTO)
			throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.PedidoService
 */