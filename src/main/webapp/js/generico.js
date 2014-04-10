function validarRequerido() {
	$("input[required],select[required]").each(function() {
		if ($(this).val() == "") {			
			centrar(this);
			alert("Este campo es requerido!");
			return false;
		}
	});
}

function centrar(elemento)
{
	var height = $( window ).height()
	var offset = $(elemento).offset().top - (height / 3);
	$('html, body').animate({scrollTop:offset}, 600);
	$(elemento).focus();
}

function cargarEntidad(codigo, descripcion) {

	forma = document.forms[0];
	forma.codigo.value = codigo;
	forma.descripcion.value = descripcion;
	forma.codigo.readOnly = true;

}

function trim(cadena) {
	return cadena.replace(/^\s+|\s+$/g, "");
}

function calcular_edad(fecha) {

	if (fecha != '') {
		// calculo la fecha de hoy
		hoy = new Date();
		// alert(hoy)
		// calculo la fecha que recibo
		// La descompongo en un array
		var array_fecha = fecha.split("-");
		// si el array no tiene tres partes, la fecha es incorrecta

		if (array_fecha.length != 3) {
			alert('la fecha debe tener año, mes , día');
			return;
		}
		// compruebo que los ano, mes, dia son correctos
		ano = parseInt(array_fecha[0]);

		mes = parseInt(array_fecha[1]);

		dia = parseInt(array_fecha[2]);

		// si el año de la fecha que recibo solo tiene 2 cifras hay que
		// cambiarlo a 4
		if (ano <= 99) {
			ano += 1900;
		}

		// resto los años de las dos fechas
		edad = hoy.getUTCFullYear() - ano - 1; // -1 porque no se si ha
												// cumplido años ya este año
		// si resto los meses y me da menor que 0 entonces no ha cumplido años.
		// Si da mayor si ha cumplido
		if (hoy.getMonth() + 1 - mes < 0) // + 1 porque los meses empiezan en
											// 0
			return edad;
		if (hoy.getMonth() + 1 - mes > 0)
			return edad + 1;

		// entonces es que eran iguales. miro los dias
		// si resto los dias y me da menor que 0 entonces no ha cumplido años.
		// Si da mayor o igual si ha cumplido
		if (hoy.getUTCDate() - dia >= 0)
			return edad + 1;
		// si eligen fecha superiores al dia de hoy
		if (edad < 0) {
			edad = 0;
		}
		return edad;
	}

}

function asignarEdad() {

	document.forms[0].edad.value = calcular_edad(document.forms[0].fechaNacimiento.value);
	return;
}

function seleccionarListaChequeo(dato, objeto) {

	longitud = objeto.length;

	for (i = 0; i < longitud; i++) {
		if (objeto[i].value == dato) {
			objeto[i].checked = true;
			return;
		}
	}
}

function seleccionarListaDesplegable(dato, objeto) {

	longitud = objeto.length;

	for (i = 0; i < longitud; i++) {
		if (objeto[i].value == dato) {
			objeto[i].selected = true;
			return;
		}
	}
}

function validarEnteros(campo) {
	var RegExPattern = /^\d+$/;
	if ((!campo.value.match(RegExPattern)) && (campo.value != '')) {
		return false;
	}
	return true;
}

/**
 * function cargarPeriodo(periodo){
 * 
 * forma = document.forms[0];
 * 
 * datos = periodo.split("/"); mes =parseInt(datos[0]); year=parseInt(datos[1]);
 * meses = ['01','02','03','04','05','06','07','08','09','10','11','12']; int
 * contador=
 * 
 * if(mes >=1 || mes <=5){
 * 
 * if(mes==1){
 * 
 * forma.mes.options[0]= new Option(); forma.mes.options[0].text="11";
 * forma.mes.options[0].value="11"; forma.mes.options[1]= new Option();
 * forma.mes.options[1].text="12"; forma.mes.options[1].value="12";
 * forma.mes.options[2]= new Option(); forma.mes.options[2].text="01";
 * forma.mes.options[2].value="01"; }else{
 * 
 * forma.mes.options[0]= new Option(); forma.mes.options[0].text="12";
 * forma.mes.options[0].value="12"; forma.mes.options[1]= new Option();
 * forma.mes.options[1].text="01"; forma.mes.options[1].value="01";
 * forma.mes.options[2]= new Option(); forma.mes.options[2].text="02";
 * forma.mes.options[2].value="02"; }
 * 
 * forma.ano.options[0]=new Option(); forma.ano.options[0].text=(year-1);
 * forma.ano.options[0].value=(year-1); forma.ano.options[1]=new Option();
 * forma.ano.options[1].text=(year); forma.ano.options[1].value=(year);
 * forma.ano.options[0].selected=true;
 * 
 * 
 * }else{ forma.mes.options[0]= new Option(); if((mes-2)<10){
 * forma.mes.options[0].text="0"+(mes-2);
 * forma.mes.options[0].value="0"+(mes-2);
 * 
 * }else{ forma.mes.options[0].text=(mes-2); forma.mes.options[0].value=(mes-2);
 *  } forma.mes.options[1]= new Option();
 * 
 * if((mes-1)<10){ forma.mes.options[1].text="0"+(mes-1);
 * forma.mes.options[1].value="0"+(mes-1);
 * 
 * }else{ forma.mes.options[1].text=(mes-1); forma.mes.options[1].value=(mes-1);
 *  } forma.mes.options[2]= new Option();
 * 
 * if((mes)<10){ forma.mes.options[2].text="0"+(mes);
 * forma.mes.options[2].value="0"+(mes);
 * 
 * }else{ forma.mes.options[2].text=(mes); forma.mes.options[2].value=(mes);
 *  }
 * 
 * forma.mes.options[2].selected=true;
 * 
 * forma.ano.options[0]=new Option(); forma.ano.options[0].text=(year);
 * forma.ano.options[0].value=(year); forma.ano.options[0].selected=true;
 *  }
 * 
 *  }
 */

// Esta funcion es solo para Registro de consumos restringir mes.
function cargaPeriodoConsumo(periodo) {

	forma = document.forms[0];
	datos = periodo.split("/");
	mes = parseInt(datos[0], 10);
	year = parseInt(datos[1], 10);
	meses = [ '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11',
			'12' ];
	contador = 0;
	restaMeses = mes - 1;
	i = mes;

	yearAnterior = 0;

	// cargo meses
	while (i > restaMeses) {
		if (i > 0) {
			forma.mes.options[contador] = new Option();
			forma.mes.options[contador].text = meses[i - 1];
			forma.mes.options[contador].value = meses[i - 1];
		} else {
			forma.mes.options[contador] = new Option();
			forma.mes.options[contador].text = meses[11 + i];
			forma.mes.options[contador].value = meses[11 + i];
			yearAnterior = year - 1;
		}
		contador++;
		i--;
	}
	// cargo years
	if (yearAnterior == 0) {
		forma.ano.options[0] = new Option();
		forma.ano.options[0].text = (year);
		forma.ano.options[0].value = (year);
		forma.ano.options[0].selected = true;

	} else {

		forma.ano.options[0] = new Option();
		forma.ano.options[0].text = (year - 1);
		forma.ano.options[0].value = (year - 1);
		forma.ano.options[1] = new Option();
		forma.ano.options[1].text = (year);
		forma.ano.options[1].value = (year);
		forma.ano.options[1].selected = true;
	}

}

function cargarPeriodo(periodo) {

	forma = document.forms[0];
	datos = periodo.split("/");
	mes = parseInt(datos[0], 10);
	year = parseInt(datos[1], 10);
	meses = [ '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11',
			'12' ];
	contador = 0;
	restaMeses = mes - 12;
	i = mes;

	yearAnterior = 0;

	// cargo meses
	while (i > restaMeses) {
		if (i > 0) {
			forma.mes.options[contador] = new Option();
			forma.mes.options[contador].text = meses[i - 1];
			forma.mes.options[contador].value = meses[i - 1];
		} else {
			forma.mes.options[contador] = new Option();
			forma.mes.options[contador].text = meses[11 + i];
			forma.mes.options[contador].value = meses[11 + i];
			yearAnterior = year - 1;
		}
		contador++;
		i--;
	}
	// cargo years
	if (yearAnterior == 0) {
		forma.ano.options[0] = new Option();
		forma.ano.options[0].text = (year);
		forma.ano.options[0].value = (year);
		forma.ano.options[0].selected = true;

	} else {

		forma.ano.options[0] = new Option();
		forma.ano.options[0].text = (year - 1);
		forma.ano.options[0].value = (year - 1);
		forma.ano.options[1] = new Option();
		forma.ano.options[1].text = (year);
		forma.ano.options[1].value = (year);
		forma.ano.options[1].selected = true;
	}

}

function cargarPeriodoConsumo(periodo) {
	cargarPeriodo(periodo);
}

/*
 * function cargarPeriodoConsumo(periodo){
 * 
 * forma = document.forms[0];
 * 
 * datos = periodo.split("/"); mes =parseInt(datos[0]); year=parseInt(datos[1]);
 * 
 * 
 * if(mes ==1 || mes ==2){
 * 
 * if(mes==1){
 * 
 * forma.mes.options[0]= new Option(); forma.mes.options[0].text="12";
 * forma.mes.options[0].value="12"; forma.mes.options[0].selected=true;
 * forma.mes.options[1]= new Option(); forma.mes.options[1].text="01";
 * forma.mes.options[1].value="01"; forma.ano.options[0]=new Option();
 * forma.ano.options[0].text=(year-1); forma.ano.options[0].value=(year-1);
 * forma.ano.options[1]=new Option(); forma.ano.options[1].text=(year);
 * forma.ano.options[1].value=(year); forma.ano.options[0].selected=true; }else{
 * 
 * 
 * forma.mes.options[0]= new Option(); forma.mes.options[0].text="01";
 * forma.mes.options[0].value="01"; forma.mes.options[1]= new Option();
 * forma.mes.options[1].text="02"; forma.mes.options[1].value="02";
 * forma.ano.options[0]=new Option(); forma.ano.options[0].text=(year);
 * forma.ano.options[0].value=(year); forma.ano.options[0].selected=true; }
 * 
 * 
 * 
 * 
 * }else{ forma.mes.options[0]= new Option(); if((mes-2)<10){
 * forma.mes.options[0].text="0"+(mes-1);
 * forma.mes.options[0].value="0"+(mes-1);
 * 
 * }else{ forma.mes.options[0].text=(mes-1); forma.mes.options[0].value=(mes-1);
 *  } forma.mes.options[1]= new Option();
 * 
 * if((mes-1)<10){ forma.mes.options[1].text="0"+(mes);
 * forma.mes.options[1].value="0"+(mes);
 * 
 * }else{ forma.mes.options[1].text=(mes); forma.mes.options[1].value=(mes);
 *  }
 * 
 * 
 * forma.mes.options[0].selected=true;
 * 
 * forma.ano.options[0]=new Option(); forma.ano.options[0].text=(year);
 * forma.ano.options[0].value=(year); forma.ano.options[0].selected=true;
 *  } }
 */
