package metricas;

import java.util.List;

import principal.Metodo;
import principal.Nombrable;

public class CantidadComentarios implements Metrica, Nombrable {
	
	private Integer nroComentarios;
	
	

	public void calcular(Metodo metodo) {
		List<String> codigo = metodo.getCodigo();
		String aux = null;
		this.nroComentarios = 0;
		Boolean buscandoFB = false;//indica si se esta buscando el fin de bloque
		
		for(String linea : codigo){			
		
			linea = linea.trim();
	    	if (linea.length() > 0) {
	    		if(linea.length() == 1){
	    			if(linea.equals("*"))
	    				nroComentarios++;
	    		}else{
		    		aux = linea.substring(0, 2);
		    		if (aux.equals("//")) { //Si la linea comienza con // es un comentario de linea
		    			nroComentarios++;
					}else if(buscandoFB || aux.equals("/*") ){ //Comienza bloque de codigo o estoy buscando el final del bloque
						nroComentarios++;
						buscandoFB = -1 == linea.indexOf("*/"); //Si encuentra el final del bloque pongo el flag en false
					}
	    		}
			}
		}
	}
	
	public String getNombre() {
		String leyenda = "Cantidad de comentarios";
		return leyenda;
	}

	public ResultadoMetrica obtenerResultado() {
		return new ResultadoMetrica( this.getNombre(), this.nroComentarios.toString() );
	}

}
