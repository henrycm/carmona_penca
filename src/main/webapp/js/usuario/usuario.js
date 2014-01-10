function enviarUsuaro(accion){
	   
	   forma =  document.forms[0];
	   forma.usuario.value = trim(forma.usuario.value);
	   forma.password.value = trim(forma.password.value);
	   
	   if (forma.usuario.value==''){
		   alert('Debe ingresar un login para el usuario');
		   return;
	   } 
	   if (forma.password.value==''){
		   alert('Debe ingresar una clave para el usuario');
		   return;
	   }  
	  /* if (forma.rol.selectedIndex==0){
		   alert('Debe seleccionar un rol para el usuario');
		   return;
	   }  */ 
	   forma.accion.value = accion;	
	   forma.submit();
}

function limpiarUsuario(){
	forma.usuario.readOnly =false;
	forma.password.value='';
	forma.usuario.value='';
}

function cargarUsuario(usuario,clave,activado){
	   
	   forma =  document.forms[0];
	   forma.usuario.value = usuario;	
	   forma.password.value = clave;
	   for (i=0;i<forma.activado.length;i++){ 
	       if (forma.activado[i].value == activado ){ 
	    	   forma.activado[i].checked; 
	       }	   
	    } 
}


function cambiarClave(accion){
	   
	   forma =  document.forms[0];
	   forma.usuario.value = trim(forma.usuario.value);
	   forma.password.value = trim(forma.password.value);
	   
	   if (forma.usuario.value==''){
		   alert('Debe ingresar un login para el usuario');
		   return;
	   } 
	   if (forma.password.value==''){
		   alert('Debe ingresar una clave para el usuario');
		   return;
	   }  
	   if (forma.passwordNuevo.value==''){
		   alert('Debe ingresar una clave nueva para el usuario');
		   return;
	   }  	 
	   forma.accion.value = accion;	
	   forma.submit();
}
