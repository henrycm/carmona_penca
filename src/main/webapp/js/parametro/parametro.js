function actualizarParametro() {
	if (document.parametro.nombre.value == '') {
		alert('El nombre del parámetro es requerido');
		return;
	}

	if (document.parametro.descripcion.value == '') {
		alert('La descripcion del parámetro es requerida');
		return;
	}

	if (document.parametro.valor.value == 0) {
		alert('El valor del parámetro es requerido');
		return;
	}

	document.parametro.accion.value = 'A';
	document.parametro.submit();
}

function cargarEntidad(nombreParametro, descripcion, valor) {
	forma = document.parametro;
	forma.nombre.value = nombreParametro;
	forma.descripcion.value = descripcion;
	forma.valor.value = valor;
}