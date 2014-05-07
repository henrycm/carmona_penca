function calcularPedido() {

	if (validarCantidadesPedido()) {

		var frm = document.getElementById("pedido");
		var totalPedido = 0;

		for (i = 0; i < frm.elements.length; i++) {

			nombreCampo = frm.elements[i].name;
			valor = frm.elements[i].value;

			if (nombreCampo.indexOf('cantidad') != -1) {
				datos = nombreCampo.split('_');
				codigo = datos[1];
				precio = datos[2];
				totalPedido = totalPedido
						+ (parseInt(valor) * parseInt(precio));
			}

		}

		frm.totalPedido.value = totalPedido;
	} else {
		alert("Alguna cantidad esta vacia o no es un dato valido");
	}
}

function validarCantidadesPedido() {

	var frm = document.getElementById("pedido");
	var existeCantidad = 0;

	for (i = 0; i < frm.elements.length; i++) {

		nombreCampo = frm.elements[i].name;
		valor = frm.elements[i].value;

		if (nombreCampo.indexOf('cantidad') != -1) {
			existeCantidad++;
			if (valor == '' || !validarEnteros(frm.elements[i])) {
				return false;
			}
		}
	}

	return true;
}

function eliminarPedido() {
	$(this).find(".placeholder").remove();
}

function vistaPreliminarPedido() {

	forma = document.forms[0];
	contador = 0;
	bandera = 0;
	var productos = forma.elements["producto"];

	for (var i = 0; i < productos.length; i++) {

		if (productos[i].checked) {
			codigoProducto = productos[i].value;
			j = 0;
			bandera = 0;
			while (j < forma.elements.length && bandera == 0) {
				var nombre = forma.elements[j].name;
				var campo = "cantidad_" + codigoProducto + "_";
				if (nombre.indexOf(campo) != -1) {
					var valor = forma.elements[j].value;

					if (valor == '' || !validarEnteros(forma.elements[j])) {
						bandera = 1;
					}

				}
				j++;
			}
			if (bandera == 1) {
				alert('Debe ingresar un cantidad entera para el producto');
				return;
			}

			contador++;
		}
	}
	if (contador == 0) {
		alert('Debe elegir un produto para el pedido con cantidades válidas');
		return;
	}

	document.forms[0].submit();

}

function calcularTotalPedido(objeto) {

	if (validarEnteros(objeto)) {
		cantidades = document.forms[0].cantidad;
		suma = 0;
		for (j = 0; j < cantidades.length; j++) {

			suma = suma + parseInt(cantidades.value);

		}
		document.forms[0].totalPedido = suma;

	} else {
		alert('Debe ingresar un valor entero para las cantidad del producto');
		document.forms[0].totalPedido = 0;

	}
}

function enviarPedido() {
	if (document.forms[0].totalPedido.value == ''
			|| !validarEnteros(document.forms[0].totalPedido)) {
		alert('Debe ingresar productos y calcular el valor del pedido');
		return;

	}
	if (!confirm("Esta seguro de que desea realizar el pedido, verifiquelo por favor")) {
		return;
	}

	document.forms[0].action = "../pedido/RealizarPedido";
	document.forms[0].submit();

}

function imprimirConstanciaPedido() {
	document.forms[0].action = '../pedido/ReporteConstanciaPedido';
	document.forms[0].submit();
}

function actualizarTransporte(codigo, transporte, distribuidor) {

	forma = document.forms[0];
	if (transporte > 0) {
		if (!confirm("Esta seguro de que desea quitarle el valor del transporte al pedido Nro. "
				+ codigo)) {
			return;
		}
		forma.codigoPedido.value = codigo;
		forma.distribuidor.value = distribuidor;
		forma.accion.value = 'A';
		forma.action = "../pedido/ListaPedidoController";
		forma.submit();
	} else {
		alert('El transporte del pedido es igual a cero');
	}

}

function chequearProducto(objetoCantidad) {

	forma = document.forms[0];
	var productos = forma.elements["producto"];

	nombre = objetoCantidad.name;
	datos = nombre.split('_');
	codigoProducto = datos[1];

	for (var i = 0; i < productos.length; i++) {

		if (productos[i].value == codigoProducto) {
			productos[i].checked = true;
		}
	}
}

function generarReporteListaPedidosDistribuidor() {

	forma = document.forms[0];
	forma.periodo.value = forma.mes.options[forma.mes.selectedIndex].value
			+ "/" + forma.ano.options[forma.ano.selectedIndex].value;
	forma.action = '../ReporteListaPedidoPorDistribuidor';
	forma.submit();
}

function consultarListaPedidosEliminar() {

	forma = document.forms[0];
	forma.periodo.value = forma.mes.options[forma.mes.selectedIndex].value
			+ "/" + forma.ano.options[forma.ano.selectedIndex].value;
	forma.action = 'ListaPedidosAEliminar';
	forma.submit();
}

function eliminarPedido(codigoPedido, totalPedido) {

	forma = document.forms[0];
	forma.pedido.value = codigoPedido;
	forma.totalPedido.value = totalPedido;

	forma.action = 'EliminarPedido';
	forma.submit();
}

function realizarPedidoDistribuidor() {

	forma = document.forms[0];
	forma.action = 'IndexPedido';
	forma.submit();
}