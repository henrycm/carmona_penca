package co.com.multinivel.util;

public enum RecursosEnum {
	FW_INDEX("/index.jsp"), FW_INDEX_ROLE_DISTRIBUIDOR("/indexDistribuidor.jsp"), FW_INDEX_ROLE_AFILIADO(
			"/indexAfiliado.jsp"), FW_INDEX_USUARIO("/usuario/usuario.jsp"), FW_ROL_POR_USUARIO(
			"/rol/listaUsuarios.jsp"), FW_CAMBIAR_CLAVE("/usuario/cambiarClave.jsp"), FW_ARBOL_AFILIADO(
			"/arbol/consultarAfiliado.jsp"), FW_GENERAR_ARBOL("/arbol/arbol.jsp"), FW_ROL(
			"/rol/rol.jsp"), FW_INDEX_AFILIADO("/afiliado/formulario.jsp"), FW_INGRESO_AFILIADO(
			"/afiliado/formulario.jsp"), FW_INGRESO_AFILIADO_EXITO("/afiliado/ingresoExito.jsp"), FW_INGRESO_AFILIADO_ERROR(
			"/afiliado/ingresoError.jsp"), FW_ACTUALIZACION_AFILIADO(
			"/afiliado/actualizarAfiliado.jsp"), FW_LISTAR_AFILIADO("/afiliado/listarAfiliados.jsp"), FW_LISTAR_DISTRIBUIDORES(
			"/afiliado/distribuidores.jsp"), FW_LISTAR_AFILIADO_POR_NIVEL(
			"/afiliado/listarAfiliadosPorNivel.jsp"), FW_CONSULTAR_AFILIADO(
			"/afiliado/consultarAfiliado.jsp"), FW_CAMBIAR_DOCUMENTO_AFILIADO(
			"/afiliado/cambiarDocumentoAfiliado.jsp"), FW_BORRAR_AFILIADO(
			"/afiliado/borrarAfiliado.jsp"), FW_BORRAR_AFILIADO_EXITO("/afiliado/borrarExito.jsp"), FW_ACTUALIZAR_AFILIADO_DISTRIBUIDOR(
			"/afiliado/actualizarAfiliadoADistribuidor.jsp"), FW_ACTUALIZAR_AFILIADO_DISTRIBUIDOR_EXITO(
			"/afiliado/actualizarDistribuidorExito.jsp"), FW_VALIDAR_AFILIACIONES_DISTRIBUIDOR(
			"/afiliado/validarAfiliacionesDistribuidor.jsp"), FW_EMERGENTE_DISTRIBUIDOR(
			"/afiliado/emergenteDistribuidor.jsp"), FW_EMERGENTE_AFILIADO("/afiliado/emergente.jsp"), FW_BUSCAR_AFILIADO_PEDIDO(
			"/afiliado/buscadorAfiliadoPedido.jsp"), FW_VISTA_REPORTES_LISTA_PEDIDO(
			"/pedido/periodoReporteListaPedidos.jsp"), FW_LISTAR_AFILIADOSXDISTRIBUIDORES(
			"/afiliado/afiliacionesPeriodo.jsp"), FW_LISTAR_AFILIADOSXDISTRIBUIDORESPERIODO(
			"/afiliado/afiliacionesDistribuidorPeriodo.jsp"), FW_INGRESO_PRODUCTO(
			"/producto/productos.jsp"), FW_INGRESO_PRODUCTO_EXITO("/producto/ingresoExito.jsp"), FW_INGRESO_PRODUCTO_ERROR(
			"/producto/ingresoError.jsp"), FW_ACTUALIZACION_PRODUCTO(
			"/producto/actualizarAfiliado.jsp"), FW_LISTAR_PRODUCTO("/producto/listarAfiliados.jsp"), FW_CONSULTAR_PRODUCTO(
			"/producto/consultarAfiliado.jsp"), FW_CONSULTAR_CONSUMO_PRODUCTOS(
			"/producto/consultarAfiliado.jsp"), FW_CONSULTAR_CONSUMO_PORC_PDTOS(
			"/producto/periodoPorcentajeProducto.jsp"), FW_NUEVO_INGRESO_PEDIDO(
			"/pedido/nuevopedidos.jsp"), FW_INGRESO_PEDIDO("/pedido/pedidos.jsp"), FW_LISTAR_PEDIDO_ELIMINAR(
			"/pedido/periodoEliminacionPedido.jsp"), FW_INGRESO_PEDIDO_AFILIADO(
			"/pedido/pedidosAfiliado.jsp"), FW_BUSCAR_AFILIADO_CONSUMO(
			"/afiliado/buscadorAfiliadoConsumo.jsp"), FW_PRELIMINAR_PEDIDO(
			"/pedido/vistaPreliminar.jsp"), FW_INGRESO_PEDIDO_EXITO("/pedido/ingresoExito.jsp"), FW_ELIMINACION_PEDIDO_EXITO(
			"/pedido/eliminacionExito.jsp"), FW_ELIMINACION_PEDIDO_ERROR(
			"/pedido/eliminacionError.jsp"), FW_INGRESO_PEDIDO_ERROR("/pedido/ingresoError.jsp"), FW_CONSULTAR_PEDIDO(
			"/pedido/pedidos.jsp"), FW_LISTAR_PEDIDOS_DISTRIBUIDOR(
			"/pedido/listaPedidoDistribuidor.jsp"), FW_ELIMINACION_CONSUMO_EXITO(
			"/consumo/eliminacionExito.jsp"), FW_ELIMINACION_CONSUMO_ERROR(
			"/consumo/eliminacionError.jsp"), FW_LISTAR_DISTRIBUIDORES_PEDIDO(
			"/pedido/listarDistribuidores.jsp"), FW_LISTAR_DISTRIBUIDORES_PEDIDO_NUEVO(
			"/pedido/listarDistribuidoresNuevo.jsp"), FW_INGRESO_CONSUMO("/consumo/consumos.jsp"), FW_LISTAR_CONSUMO_ELIMINAR(
			"/consumo/periodoEliminacionConsumo.jsp"), FW_INGRESO_CONSUMO_AFILIADO(
			"/consumo/consumosAfiliado.jsp"), FW_VISTA_REPORTES_LISTA_CONSUMOS(
			"/consumo/periodoReporteListaConsumos.jsp"), FW_VISTA_REPORTES_LISTA_CONSUMOS_AFILIADO(
			"/consumo/consultarConsumosAfiliado.jsp"), FW_VISTA_REPORTES_LISTA_CONSUMOS_PRODUCTO(
			"/consumo/consultarConsumosProducto.jsp"), FW_PRELIMINAR_CONSUMO(
			"/consumo/vistaPreliminar.jsp"), FW_INGRESO_CONSUMO_EXITO("/consumo/ingresoExito.jsp"), FW_INGRESO_CONSUMO_ERROR(
			"/consumo/ingresoError.jsp"), FW_CONSULTAR_CONSUMO("/consumo/consumos.jsp"), FW_LISTAR_CONSUMOS_TOTALES_DISTRIBUIDOR(
			"/consumo/listarDistribuidoresConsumo.jsp"), FW_LISTAR_CONSUMOS_TOTALES(
			"/consumo/periodoListaConsumosTotales.jsp"), FW_LISTAR_DISTRIBUIDOR_ELIMINAR_CONSUMO(
			"/consumo/listarDistribuidoresEliminar.jsp"), FW_LISTAR_DISTRIBUIDOR_REGISTRAR_CONSUMO(
			"/consumo/listarDistribuidoresRegistrar.jsp"), FW_INGRESO_RED("/red/red.jsp"), FW_INGRESO_RED_EXITO(
			"/red/ingresoExito.jsp"), FW_INGRESO_RED_ERROR("/red/ingresoError.jsp"), FW_BUSCADOR_DISTRIBUIDOR(
			"/compensacion/buscadorDistribuidorLiquidacion.jsp"), FW_LISTAR_COMPENSACION_POR_DISTRIBUIDOR(
			"/compensacion/listarCompensacionPorDistribuidor.jsp"), FW_CONSULTAR_COMPENSACION_POR_AFILIADO(
			"/compensacion/consultarCompensacionAfiliado.jsp"), FW_INDEX_REPORTE_COMPENSACION(
			"/compensacion/periodoReporteCompensacion.jsp"), FW_INDEX_REPORTE_COMPENSACION_DISTRIBUIDOR(
			"/compensacion/periodoReporteCompensacionDistribuidor.jsp"), FW_INDEX_REPORTE_COMPENSACION_TOTAL_DISTRIBUIDOR(
			"/compensacion/periodoReporteCompensacionTotalDistribuidor.jsp"), FW_INDEX_REPORTE_COMPENSACION_RED(
			"/compensacion/listarCompensacionAfiliadosPorNivel.jsp"), FW_LISTAR_PREMIACION_POR_DISTRIBUIDOR(
			"/premiacion/listarPremiacionPorDistribuidor.jsp"), FW_CONSULTAR_PREMIACION_POR_AFILIADO(
			"/premiacion/consultarPremiacionAfiliado.jsp"), FW_INDEX_LIQUIDACION_RED(
			"/liquidacion/liquidacionPorRed.jsp"), FW_INDEX_LIQUIDACION(
			"/liquidacion/liquidacionMasiva.jsp"), FW_LISTA_PREMIOS_AFILIADO(
			"/reporte/ReportePremiosAfiliadoPeriodo"), FW_LISTA_COMPENSACION_AFILIADO(
			"/reporte/ReporteCompensacionAfiliadoPeriodo"), FW_LISTA_COMPENSACION_DISTRIBUIDOR(
			"/reporte/ReporteCompensacionDistribuidorPeriodo"), FW_LISTA_COMPENSACION_RED(
			"/reporte/ReporteCompensacionAfiliadosRedPeriodo"), FW_VALIDAR_COMPENSACION_DISTRIBUIDOR(
			"/compensacion/validarCompensacionDistribuidor.jsp"), FW_ERROR("/generico/error.jsp"), FW_JASPER_AFLIADO(
			"inscripcionAfiliado.jasper.jasper"), FW_JASPER_LISTA_POR_NIVEL(
			"ListaAfiliadosPadre2.jasper"), FW_JASPER_LISTA_PREMIOS_AFILIADO(
			"ListaPremiacionAfiliado.jasper"), FW_JASPER_LISTA_COMPENSACION_AFILIADO(
			"ListaCompensacionAfiliado.jasper"), FW_JASPER_LISTA_COMPENSACION_DISTRIBUIDOR(
			"ListaPagoCompensacionDistribuidor.jasper"), FW_JASPER_LISTA_COMPENSACION_DISTRIBUIDOR_PERIODO(
			"ListaAfiliadosPadreConsumoPeriodo.jasper"), FW_JASPER_LISTA_COMPENSACION_RED(
			"ListaAfiliadosPadreConsumoPeriodo.jasper"), FW_JASPER_REPORTE_CONSUMO(
			"reporteConsumo.jasper"), FW_JASPER_REPORTE_PEDIDO("reportePedido.jasper"), FW_JASPER_REPORTE_PDTO_PORC_PERIODO(
			"Reporte_PorcentajeProducto.jasper"), FW_JASPER_REPORTE_LISTA_PEDIDOS_DISTRIBUIDOR(
			"Reporte_lista_pedidos_distribuidor_periodo.jasper"), FW_JASPER_REPORTE_LISTA_CONSUMOS_DISTRIBUIDOR(
			"Reporte_lista_consumos_distribuidor_periodo.jasper"), FW_JASPER_REPORTE_LISTA_CONSUMOS_AFILIADO(
			"Reporte_lista_consumos_afiliado_periodo.jasper"), FW_JASPER_REPORTE_LISTA_CONSUMOS_TOTAL_DISTRIBUIDOR(
			"listaConsumosRed.jasper"), FW_JASPER_AFILIADOSXDISTRIBUIDORES(
			"Reporte_afiliadosPorDistribuidores.jasper"), FW_JASPER_REPORTE_LISTA_CONSUMOS_AFILIADO_NOMBRE(
			"listaConsumosAfiliado.jasper"), FW_JASPER_REPORTE_LISTA_COMPENSACION_TOTAL_DISTRIBUIDOR(
			"listaCompensacionDistribuidor.jasper"), FW_JASPER_AFILIADOSXDISTRIBUIDORESXPERIODO(
			"listaAfiliadoPorDistribuidoresPeriodo.jasper"), FW_JASPER_REPORTE_LISTA_CONSUMOS_PRODUCTO(
			"Reporte_lista_consumo_producto_periodo.jasper"), FW_ADMINISTRACION(
			"/administracion/index.jsp");

	private String recurso;

	private RecursosEnum(String recurso) {
		this.recurso = recurso;
	}

	public String getRecurso() {
		return this.recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.util.RecursosEnum
 * 
 * 
 */