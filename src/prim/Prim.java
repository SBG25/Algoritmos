package prim;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Prim {

	private Prim() {
		
	}
	
	public static List<Edge> solve(Graph grafo, int nodoPartida) {
		List<Edge> resultado = new LinkedList<>();
		boolean[] visitados = new boolean[grafo.getNNodos()];
		visitados[nodoPartida] = true;
		int contador = 1;
		Queue<Edge> cola = new PriorityQueue<>(new Edge.EdgeComparator());
		encolarAristas(cola, grafo.getAdjList(nodoPartida), visitados);
		
		while(!cola.isEmpty() && contador != grafo.getNNodos()) {
			Edge edge = cola.remove();
			int destino = edge.getDestino();
			if(!visitados[destino]) {
				visitados[destino] = true;
				contador++;
				encolarAristas(cola, grafo.getAdjList(destino), visitados);
				resultado.add(edge);
			}
		}
		
		
		
		return resultado;
	}
	
	private static void encolarAristas(Queue<Edge> cola, List<Edge> lista, boolean[] visitados) {
		for(Edge e : lista) {
			if(!visitados[e.getDestino()]) cola.add(e);
		}
	}
}
