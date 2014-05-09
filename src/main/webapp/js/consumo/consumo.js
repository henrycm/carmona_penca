
function calcularPedido(){                                                     
                      
        	
        	if(validarCantidadesPedido()){
        	
		        	var frm = document.getElementById("pedido");                                 
		             var totalPedido = 0;                                                      
		                                                                                       
		             for (i=0;i<frm.elements.length;i++)                                       
		         	{                                                                            
		                                                                                       
		                
		            	 nombreCampo =frm.elements[i].name;                                    
		                 valor = frm.elements[i].value;                                        
		                                                                                       
		                                                                                       
		         		if(nombreCampo.indexOf('cantidad')!=-1){                                    
		         		  datos =nombreCampo.split('_');                                            
		         		  codigo = datos[1];                                                        
		                   precio = datos[2];                                                  
		                   totalPedido =totalPedido + (parseInt(valor)*parseInt(precio));      
		                 }                                                                     
		                                                                                       
		                                                                                       
		        }  
		             
		        frm.totalPedido.value=totalPedido; 
        }else{
        	 alert("Alguna cantidad esta vacia o no es un dato valido");
        }
     }
        
        
        
       function validarCantidadesPedido(){
            
                                                                            
            	var frm = document.getElementById("pedido");                                 
                var existeCantidad = 0;
                                                                                          
                for (i=0;i<frm.elements.length;i++)                                       
            	{                                                                            
                                                                                          
                    nombreCampo =frm.elements[i].name;                                    
                    valor = frm.elements[i].value;                                        
                                                                                          
                                                                                          
            		if(nombreCampo.indexOf('cantidad')!=-1){  
            			existeCantidad++;
            		   if(valor=='' || !validarEnteros(frm.elements[i])){
            		       return false;
            		   }
                    }                                                                     
                 }
               
                return true;
        }
       
        function eliminarPedido(){                                                     
        	$( this ).find( ".placeholder" ).remove();                                           
        }  
        
        
        function  vistaPreliminarPedido(){
      	  
        	forma = document.forms[0];
        	contador = 0;
        	bandera=0;
        	var productos = forma.elements["producto"];
        	
        	for(var i = 0; i < productos.length; i++){
    	
        		if(productos[i].checked){
        			codigoProducto = productos[i].value;
        			j=0;
        			bandera=0;
    				while (j<forma.elements.length && bandera==0)
    	        	{
    	        	 var nombre=  forma.elements[j].name;
    	        	  var campo = "cantidad_"+codigoProducto+"_";
    	        	     if(nombre.indexOf(campo)!=-1){
    	        	    	 var valor = forma.elements[j].value;
    	        	    	    	        	    	
    	        	    	 if(valor=='' || !validarEnteros(forma.elements[j])){
    	        	    		bandera=1; 
    	          		   	}
    	        	    	 
    	        	     }	
    	        	     j++;
    	        	}
    				if(bandera==1){
    					alert('Debe ingresar un cantidad entera para el producto');
       	    		   return;	
    				}
    				
    				contador++;
    			}	
        	}
        	if (contador==0) {
				alert('Debe elegir un produto para el pedido con cantidades válidas');
				return;
			}
          	
        document.forms[0].submit();
      	  
        }     
  
        function  calcularTotalPedido(objeto){
        	  
        	 if(validarEnteros(objeto)){
        	  cantidades =document.forms[0].cantidad;
        	  suma =0;
        	  for(j=0;j<cantidades.length;j++){
        		 
        		  suma =suma+ parseInt(cantidades.value);
        		 
        	  }
        	  document.forms[0].totalPedido= suma;
        	  
        }else{
        	  alert('Debe ingresar un valor entero para las cantidad del producto');
           	  document.forms[0].totalPedido= 0;
        		 
        		 
        	 }
          }   
        
        function enviarPedido(){                                                     
        	 if (document.forms[0].totalPedido.value =='' || !validarEnteros(document.forms[0].totalPedido)){
     			alert('Debe ingresar productos y calcular el valor del pedido');
     			return;
     			
        	 }  
        	 if(!confirm("Esta seguro de que desea realizar el pedido, verifiquelo por favor")) {
                    return;
             }

                  
                    document.forms[0].action="../consumo/RealizarConsumo";
                   document.forms[0].submit();
                 
        }  
       
        
        
        function imprimirConstanciaPedido(){
        	document.forms[0].action = '../consumo/ReporteConstanciaConsumo';
        	document.forms[0].submit();
        }
      
        
        function chequearProducto(objetoCantidad){
        	
        	forma = document.forms[0];
        	var productos = forma.elements["producto"];
        	
        	nombre = objetoCantidad.name;
        	datos= nombre.split('_');
        	codigoProducto = datos[1];
        	
        	for(var i = 0; i < productos.length; i++){
            	
        		if(productos[i].value==codigoProducto){
        			productos[i].checked=true;
        		}	
        	}
        }	
        
        
        function asignarDocumento(){
        	forma = document.forms[0];
        	if(forma.codigoEmpresario.value==""){
        		alert("Ingrese el codigo del empresario");
        		return false ;
        	}
        		
        	forma.documento.value=forma.codigoEmpresario.value+"-"+forma.letra.options[forma.letra.selectedIndex].value;
        	return true;
        }
        
        
        function seleccionarAfiliado(cedula){
        
        	forma= document.forms[0];
        	forma.cedula.value=cedula;
        	forma.fechaConsumo.value=forma.mes.options[forma.mes.selectedIndex].value+"/01/"+forma.ano.options[forma.ano.selectedIndex].value;
        	forma.action='../consumo/IndexConsumo';
        	forma.submit();
        	
        }
      
        function generarReporteListaConsumosDistribuidor(){
        	
        	forma= document.forms[0];
        	forma.periodo.value=forma.mes.options[forma.mes.selectedIndex].value+"/"+forma.ano.options[forma.ano.selectedIndex].value;
        	forma.action ='../ReporteListaConsumosPorDistribuidor';
        	forma.submit();
        }
 
  
        
        function generarReporteConsumoTotalRed(){
        	
        	forma= document.forms[0];
        	
        	forma.periodo.value=forma.mes.options[forma.mes.selectedIndex].value+"/"+forma.ano.options[forma.ano.selectedIndex].value;
        	forma.action ='../ReporteListaConsumosTotalPorDistribuidor';
        	forma.submit();
        }
        
   function generarReporteConsumosAfiliado(){
        	
        	
	        forma= document.forms[0];
        	//forma.periodo.value=forma.mes.options[forma.mes.selectedIndex].value+"/"+forma.ano.options[forma.ano.selectedIndex].value;
        	
        	if(forma.nombreEmpresario.value==""){
        	    alert('Ingrese el nombre del empresario');
        		return;
        	}
        	forma.action ='../ReporteConsumosAfiliado';
        	forma.submit();
        }
        
        
        function consultarListaConsumosEliminar(){
        	
        	forma= document.forms[0];
        	forma.periodo.value=forma.mes.options[forma.mes.selectedIndex].value+"/"+forma.ano.options[forma.ano.selectedIndex].value;
        	
			forma.action ='ListaConsumosAEliminar?distribuidor='+forma.distribuidor.value;
        	forma.submit();
        }


        function eliminarConsumo(codigoPedido){
        	
        	forma= document.forms[0];
        	forma.pedido.value=codigoPedido;

        	forma.action ='EliminarConsumo';
        	forma.submit();
        }
           
        
		function eliminarConsumoDistribuidor(){
        	
        	forma= document.forms[0];
        	if(forma.distribuidor.selectedIndex!=0){
				forma.action ='IndexConsumo?accion='+forma.accion.value+'&distribuidor='+forma.distribuidor.options[forma.distribuidor.selectedIndex].value;
				forma.submit();
			}else{
				alert('seleccion un distribuidor');
				return;
			}
        }
		
		     
		function registrarConsumoDistribuidor(){
        	
        	forma= document.forms[0];
        	if(forma.distribuidor.selectedIndex!=0){
				forma.action ='IndexConsumo?accion=B&distribuidor='+forma.distribuidor.options[forma.distribuidor.selectedIndex].value;
				forma.submit();
			}else{
				alert('seleccion un distribuidor');
				return;
			}
        }
		
		function generarReporteConsumosProducto(){
        	
        	
	        forma= document.forms[0];
        	forma.periodo.value=forma.mes.options[forma.mes.selectedIndex].value+"/"+forma.ano.options[forma.ano.selectedIndex].value;
        	
        	
        	forma.action ='../ReporteConsumosProducto';
        	forma.submit();
        }
        
        