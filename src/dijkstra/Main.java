package dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	/* LA ENTRADA SERÁ DE LA FORMA:
	 * nNodos
	 * nodo1 nodo2 ... nodoN
	 * nCaminos
	 * nodoDestino1 nodoOrigen1
	 * nodoDestino2 nodoOrigen2
	 * ...
	 * nodoDestinoN nodoOrigenN
	 * nodoPartida
	*/
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int nNodos = scanner.nextInt();
		Map<String, List<Edge<String>>> lAdyacencia = new HashMap<>();
		
		for(int i=0; i<nNodos; i++) {
			String nodo = scanner.next();
			List<Edge<String>> lista = new ArrayList<>();
			
			lAdyacencia.put(nodo, lista);
		}
		
		Graph<String> grafo = new Graph<>(nNodos, lAdyacencia);
		
		int nCaminos = scanner.nextInt();
		
		for(int i=0; i<nCaminos; i++) {
			String n1 = scanner.next();
			String n2 = scanner.next();
			
			Edge<String> e1 = new Edge<>(n1, n2);
			Edge<String> e2 = new Edge<>(n2, n1);
			
			grafo.insertarArista(n1, e1);
			grafo.insertarArista(n2, e2);
		}
		String nodoPartida = scanner.next();
		
		System.out.println("");
		Dijkstra.resolver(grafo, nodoPartida);
		
		scanner.close();

	}

}
