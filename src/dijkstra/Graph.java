package dijkstra;

import java.util.List;
import java.util.Map;

public class Graph <E>{
	int nNodos;
	Map<E, List<Edge<E>>> listaAdyacencia;
	
	public Graph(int nNodos, Map<E, List<Edge<E>>> listaAdyacencia){
		this.nNodos = nNodos;
		this.listaAdyacencia = listaAdyacencia;
	}
	
	public List<Edge<E>> getListaAdyacencia(E nodo){
		return this.listaAdyacencia.get(nodo);
	}
	
	public void insertarArista(E nodo, Edge<E> arista) {
		listaAdyacencia.get(nodo).add(arista);
	}
}
