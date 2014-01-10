function enviarRed(){
	
	forma= document.forms[0];
	
	if(forma.red.selectedIndex==0){
		
		alert("seleccione un distribuidor para la liquidacion");
		return;
	}
	
	 if(!confirm("Esta seguro de que desea liquidar la red para este periodo")) {
         return;
	 }

	forma.submit();
}


function enviarMasivo(){
	
	forma= document.forms[0];
	
	
	 if(!confirm("Esta seguro de que desea liquidar el total de redes para este periodo")) {
         return;
	 }
	forma.accion.value='M';
	
	forma.submit();
}



