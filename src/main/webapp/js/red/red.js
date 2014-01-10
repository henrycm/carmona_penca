function inscribirRed(){

	 if (document.forms[0].red.value ==''){
			alert('El codigo de la red es un dato requerido');
			return;
   }  
	 if (document.forms[0].nombreRed.value ==''){
			alert('El nombre de la red es un dato requerido');
			return;
	 } 
	 
	 if (document.forms[0].codigoEmpresario.value ==''){
			alert('El codigo del empresario es un dato requerido');
			return;
}  
	 document.forms[0].nombrePatrocinador.value=document.forms[0].nombre.value;
	 document.forms[0].numeroEmpresario.value=document.forms[0].codigoEmpresario.value+'-'+ document.forms[0].letra.options[document.forms[0].letra.selectedIndex].value;
	   
	 if(!validarCampos()){
	     return;
	 }	 
	 
     document.forma.accion.value='I';
     document.forma.submit();
}