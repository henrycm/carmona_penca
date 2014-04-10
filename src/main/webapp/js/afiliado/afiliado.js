function inscribirAfiliado(){

	 if (document.forms[0].codigoEmpresario.value ==''){
			alert('El codigo del empresario es un dato requerido');
			return;
   }  
	 if(!validarCampos()){
	     return;
	 }	 
     document.forms[0].accion.value='I';
     document.forms[0].submit();
}

function consultarAfiliado(){
	if(document.forms[0].codigoEmpresario.value==''){
		alert('El codigo del empresario debe ser ingresado');
		return;
	}
	document.forms[0].submit();
		
}


function actualizarAfiliado(){
	document.forms[0].accion.value='A';
	if(validarRequerido())
		document.forms[0].submit();
}

function validarCampos(){
	
	habilitarCuentaEnvio();
	 if (document.forms[0].nombre.value ==''){
			alert('El nombre es requerido');
			return false;
	 }  
	 if (document.forms[0].apellido.value ==''){
			alert('El apellido  es requerido');
			return false;
}  
	 if (document.forms[0].fechaNacimiento.value ==''){
			alert('L a fecha de nacimiento es requerida');
			return false;
}  
	 if (document.forms[0].numeroIdentificacion.value ==''){
			alert('El numero de identificacion es requerido');
			return false;
	 }else{
		if(!validarEnteros(document.forms[0].numeroIdentificacion)){
			alert('El número de identificación debe solo contener números');
			return false;
		}	
	 }	 
		
	
	 if (document.forms[0].ciudadNacimiento.value ==''){
			alert('Indique la ciudad de nacimiento');
			return false;
    }  
	 
	 if (document.forms[0].departamento.selectedIndex ==0){
			alert('Ingrese el departamento de nacimiento');
			return false;
	 }   
	 if (document.forms[0].telefono.value ==''){
			alert('Ingrese su telefono fijo de contacto');
			return false;
	 }else{
			if(!validarEnteros(document.forms[0].telefono)){
				alert('El número telefonico debe solo contener números');
				return false;
			}	
		 }	  
	if (document.forms[0].celular.value ==''){
			alert('Ingrese su celular de contacto');
			return false;
	}else{
		if(!validarEnteros(document.forms[0].celular)){
			alert('El número de celular debe solo contener números');
			return false;
		}	
	 }  

	/*if (document.forms[0].email.value ==''){
		alert('Ingrese su correo electronico');
		return false;
	}*/
	
	
  
 
  if(document.forms[0].pagarADistribuidor.checked==false){	
				 if (document.forms[0].numeroCuenta.value ==''){
						alert('el numero de cuenta es requerido');
						return false;
				 }
				 if (document.forms[0].entidadBancaria.selectedIndex ==0){
						alert('seleccione la entidad Bancaria');
						return false;
				 }
			
				 if (document.forms[0].tipoCuenta.value ==''){
						alert('Elija el tipo de cuenta ');
						return false;
				 }
				
				 if (document.forms[0].nombreTitular.value ==''){
						alert('El nombre del titular es requrido ');
						return false;
				 }
				 if (document.forms[0].documentoTitular.value ==''){
						alert('El documento del titular es requerido ');
						return false;
				 }else{
						if(!validarEnteros(document.forms[0].documentoTitular)){
							alert('El número de documento del titular debe solo contener números');
							return false;
						}	
					 }
	 
  }else{
	  document.forms[0].numeroCuenta.value=document.forms[0].numeroCuentaDistribuidor.value;
	  document.forms[0].tipoCuenta.value=document.forms[0].tipoCuentaDistribuidor.value;
	  document.forms[0].nombreTitular.value=document.forms[0].nombreDistribuidor.value;
	  document.forms[0].documentoTitular.value=document.forms[0].numeroDistribuidor.value;

  }
	
  
  
  if (document.forms[0].nombrePatrocinador.value ==''){
			alert('El nombre del patrocinador es requerido ');
			return false;
}
	 if (document.forms[0].numeroEmpresario.value ==''){
			alert('El numero del empresario es requrido ');
			return false;
}
	
	return true;
}


function imprimirConstancia(){
	document.imprimirReporte.action = '../ReporteInscripcion';
	document.imprimirReporte.submit();
}

function listarAfiliado(){
	
	if(document.forms[0].red.selectedIndex==0){
	  alert('Debe seleccionar el distribuidor de la red');
	  return;
	}	
		document.forms[0].action = '../ReporteListaAfiliadoPorNivel';
	
	document.forms[0].submit();
}


function abrirBuscarAfiliado(){
	

	if(document.forms[0].numeroDistribuidor.value==''){
	  alert('seleccione el distribuidor del patrocinador y el afiliado');	
	  return;
	}else{
		ventaEmergente = window.open("BuscadorAfiliado?distribuidor="+document.forms[0].numeroDistribuidor.value, "buscarAfiliado", "scrollbars=yes,width=500,height=500");  
	}	
}

function buscarAfiliado(){
	filtros = 0;
	if(document.buscarAfiliado.documento.value!=""){
		filtros++;
	}
	if(document.buscarAfiliado.nombre.value!=""){
		filtros++;
	}	
	if(filtros > 0){
		document.buscarAfiliado.submit(); 
	}else{
	  alert("Debe ingresar parametros de busqueda");	
	  return;
	}
}

function buscarAfiliadoPedido(){
	
	if(document.forms[0].documento.value==""){
		alert('Ingrese el documento del afiliado');
		return;
	}
	document.forms[0].submit(); 
	
	
}

function buscarAfiliadoConsumo(){
	
	if(document.forms[0].codigoEmpresario.value!=''){
		document.forms[0].documento.value=document.forms[0].codigoEmpresario.value+"-"+document.forms[0].letra.options[document.forms[0].letra.selectedIndex].value;
	}
	
	document.forms[0].submit(); 
	
	
}


function retornarAfiliado(cedula,nombre){
	opener.document.forms[0].numeroEmpresario.readOnly =false;
	opener.document.forms[0].numeroEmpresario.value = cedula;
	opener.document.forms[0].nombrePatrocinador.readOnly=false;
	opener.document.forms[0].nombrePatrocinador.value = nombre;
	window.close();
}

function retornarDistribuidor(cedula,nombre,tipoCta,numeroCta,banco){
	opener.document.forms[0].numeroDistribuidor.readOnly =false;
	opener.document.forms[0].numeroDistribuidor.value = cedula;
	opener.document.forms[0].nombreDistribuidor.readOnly=false;
	opener.document.forms[0].nombreDistribuidor.value = nombre;
	opener.document.forms[0].numeroCuentaDistribuidor.value = numeroCta;
	opener.document.forms[0].tipoCuentaDistribuidor.value = tipoCta;
	opener.document.forms[0].bancoDistribuidor.value=banco;

	window.close();
}


function abrirBuscarDistribuidor(){
	
	ventaEmergente = window.open("BuscadorDistribuidor", "buscarAfiliado", "scrollbars=yes,width=500,height=500");  

}

function buscarDistribuidor(){

	forma =document.forms[0];
	if(forma.codigoEmpresario.value!=''){
		forma.documento.value=forma.codigoEmpresario.value+"-"+forma.letra.options[forma.letra.selectedIndex].value;
	}
	document.forms[0].submit(); 
	
}


function validarChequeCta(){
	
	if(document.forms[0].pagarADistribuidor.checked==false){
		habilitarCuenta();
	}else{
		deshabilitarCuenta();
		
		
	}	

}

function cargarTipoCta(valor){
	
	if (valor=="CRTE"){
		document.forms[1].tipoCuenta.checked=true;
	}else{
		document.forms[0].tipoCuenta.checked=true;
	}	

}

function deshabilitarCuenta(){
	  document.forms[0].numeroCuenta.value=document.forms[0].numeroCuentaDistribuidor.value;
	  document.forms[0].nombreTitular.value=document.forms[0].nombreDistribuidor.value;
	  document.forms[0].documentoTitular.value=document.forms[0].numeroDistribuidor.value;
	  
	  seleccionarListaDesplegable(document.forms[0].bancoDistribuidor.value,document.forms[0].entidadBancaria);
	  seleccionarListaChequeo(document.forms[0].tipoCuentaDistribuidor.value,document.forms[0].tipoCuenta);
	  
	  document.forms[0].numeroCuenta.readOnly =true;
	  document.forms[0].nombreTitular.readOnly=true;
	  document.forms[0].documentoTitular.readOnly=true;



	
}
function habilitarCuenta(){
	document.forms[0].numeroCuenta.readOnly =false;
	document.forms[0].nombreTitular.readOnly=false;
	document.forms[0].documentoTitular.readOnly=false;
	 document.forms[0].numeroCuenta.value='';
	  document.forms[0].nombreTitular.value='';
	  document.forms[0].documentoTitular.value='';
	  document.forms[0].entidadBancaria.selectedIndex=0;
}


function habilitarCuentaEnvio(){
	document.forms[0].numeroCuenta.readOnly =false;
	document.forms[0].nombreTitular.readOnly=false;
	document.forms[0].documentoTitular.readOnly=false;
}

function cargarCombosAfiliado(departamento,departamentoResidencia,tipoDocumento,estadoCivil,banco){
	  seleccionarListaDesplegable(departamento,document.forms[0].departamento);
	  seleccionarListaDesplegable(departamentoResidencia,document.forms[0].departamentoResidencia);
	  seleccionarListaDesplegable(tipoDocumento,document.forms[0].tipoDocumento);
	  seleccionarListaDesplegable(banco,document.forms[0].entidadBancaria);
	  seleccionarListaDesplegable(estadoCivil,document.forms[0].estadoCivil);

}



function actualizarAfiliadoADistribuidor(){
	document.forms[0].accion.value='D';
	
	
	 if(!confirm("Esta seguro de que desea cambiar el estado del afiliado a Distribuidor")) {
         return;
  }
	 document.forms[0].codigoEmpresario.readOnly=false;
	document.forms[0].submit();
}
function buscarDistribuidorLiquidacion(){
	document.forms[0].submit(); 
	
}

function eliminarAfiliado(){
	if(!confirm("Esta seguro de que desea eliminar el afiliado")) {
        return;
	}  
	document.forms[0].borrar.value='true';
	document.forms[0].accion.value='X';
	document.forms[0].submit();
}


function generarAfiliadosDistribuidor(){
	forma= document.forms[0];
	forma.periodo.value=forma.mes.options[forma.mes.selectedIndex].value+"/"+forma.ano.options[forma.ano.selectedIndex].value;
	forma.action ='../reporte/ReporteAfiliadosPorDistribuidores';
	forma.submit();
	
}

function generarAfiliacionesDistribuidorPeriodo(){
	forma= document.forms[0];
	forma.periodo.value=forma.mes.options[forma.mes.selectedIndex].value+"/"+forma.ano.options[forma.ano.selectedIndex].value;
	forma.action ='../reporte/ReporteAfiliacionesPorDistribuidoresPeriodo';
	forma.submit();
	
}
function cambiarDocumentoAfiliado(){
	document.forms[0].accion.value='U';
		
	 if(!confirm("Esta seguro de que desea cambiar el documento del afiliado")) {
         return;
  }
	document.forms[0].submit();
}


function validarAfiliacionesDistribuidor(){
	forma = document.forms[0];
	forma.accion.value='W';
	
	if(forma.cantidad.value==''){	
		alert('La cantidad debe ser ingresada');
        return;
	}
	if(validarEnteros(forma.cantidad)){	
		if(parseInt(forma.cantidad.value)<=0){
			alert("la cantidad a ingresar debe ser mayor a cero");
            return;
		}
	     
	}else{
		alert('La cantidad debe ser un valor entero mayor a cero');
        return;

	}	
	document.forms[0].submit();
}


function consultarJerarquia(){
	if(document.forms[0].codigoEmpresario.value==''){
		alert('El codigo del empresario debe ser ingresado');
		return;
	}
	document.forms[0].submit();
		
}



