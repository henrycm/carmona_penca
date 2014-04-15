function calcularArbol() {

	forma = document.forms[0];

	if (!confirm("Esta seguro de que desea calcular el Arbol.")) {
		return;
	}
	forma.submit();
}