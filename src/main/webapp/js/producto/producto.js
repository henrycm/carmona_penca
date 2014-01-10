function ingresarProducto(){
     
	 if (document.producto.codigo.value ==''){
			alert('El codigo del producto es requerido');
			return;
    }  
	 if (document.producto.nombre.value ==''){
			alert('El nombre del producto es requerido');
			return;
 }  
	

	if (document.producto.precioAfiliado.value ==''){
		alert('El precio para el afiliado  es requerido');
		return;
	}
	
	
	if(document.producto.tipo.selectedIndex==0){
		alert('Seleccione el tipo de producto');
		return;  
	}
	
    document.producto.accion.value='I';
    document.producto.submit();
}  


function cargarEntidad(codigo,nombreProducto,tipo,precioAfiliado){
	   
	   forma =  document.producto;
	   forma.nombre.value = nombreProducto;
	   forma.codigo.value = codigo;
	   forma.codigo.readOnly =true;
	   forma.actualizar.value=true;
	   seleccionarListaDesplegable(tipo,forma.tipo);
	   forma.precioAfiliado.value = precioAfiliado;
	  
	}


function actualizarProducto(){
    
	 if (document.producto.actualizar.value){
			 forma.codigo.readOnly =false;
			 if (document.producto.nombre.value ==''){
					alert('El nombre del producto es requerido');
					return;
			 }  
			
			if (document.producto.precioAfiliado.value ==''){
				alert('El precio para el afiliado  es requerido');
				return;
			}
			
		
			if(document.producto.tipo.selectedIndex==0){
				alert('Seleccione el tipo de producto');
				return;  
			}
			
		   document.producto.accion.value='A';
		   document.producto.actualizar.value=null;
		   document.producto.submit();
		   
		   
	 }
	} 

function consultarProducto(){
    
	 if (document.producto.codigo.value ==''){
			alert('El codigo del producto es requerido para la consulta');
			return;
   }  
	  document.producto.accion.value='C';
	   document.producto.actualizar.value=null;
	   document.producto.submit();
}


function eliminarProducto(){
    
	

	 if (document.producto.codigo.value ==''){
			alert('El codigo del producto es requerido para ser eliminado');
			return;
  }  

		if(!confirm("Esta seguro de que desea eliminar  el producto")) {
	        return;
		}
	  document.producto.accion.value='E';
	   document.producto.actualizar.value=null;
	   document.producto.submit();
}


function generarReporteProductoPorcentaje(){
	
	forma= document.forms[0];

	forma.accion.value='P';
	forma.action ='../../reporte/ReportePorcentajeProducto';
	forma.submit();
}