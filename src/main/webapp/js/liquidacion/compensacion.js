function generarReportePeriodoAfialiado() {

	forma = document.forms[0];
	forma.action = 'ControlReporteLiquidacion';
	forma.submit();
}

function generarReporteCompensacionRed() {

	forma = document.forms[0];
	distribuidor = forma.distribuidor.options[forma.distribuidor.selectedIndex].value;
	if (distribuidor == "") {
		alert('Debe seleccionar el distribuidor');
		return;
	}
	forma.action = 'ControlReporteLiquidacion';
	forma.submit();
}

function generarReportePeriodoDistribuidor() {

	forma = document.forms[0];
	forma.periodo.value = forma.mes.options[forma.mes.selectedIndex].value
			+ "/" + forma.ano.options[forma.ano.selectedIndex].value;
	forma.action = '../reporte/ReporteCompensacionAfiliadosRedPeriodo';
	forma.submit();
}

function validarCompensacionDistribuidor() {

	forma = document.forms[0];
	distribuidor = forma.distribuidor.options[forma.distribuidor.selectedIndex].value;
	if (distribuidor == "") {
		alert('Debe seleccionar el distribuidor');
		return;
	}
	forma.accion.value = 'I';
	forma.submit();

}

function generarReportePeriodoTotalDistribuidor() {

	forma = document.forms[0];
	forma.periodo.value = forma.mes.options[forma.mes.selectedIndex].value
			+ "/" + forma.ano.options[forma.ano.selectedIndex].value;
	forma.action = '../reporte/ReporteListaCompensacionTotalPorDistribuidor';
	forma.submit();
}
