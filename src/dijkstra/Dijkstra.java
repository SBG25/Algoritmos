package dijkstra;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {
	
	private Dijkstra() {
		
	}
	
	public static long[] solve(Graph grafo, int nodoPartida) {
		long[] resultado = new long[grafo.getNNodos()];
		boolean[] visitados = new boolean[grafo.getNNodos()];
		Queue<Dupla> cola = new PriorityQueue<>(new DuplaComparator());
		cola.add(new Dupla(nodoPartida, 0));
		int contador = 0;
		
		while(!cola.isEmpty() && contador != grafo.getNNodos()) {
			Dupla dup = cola.remove();
			if(!visitados[dup.nodo]) {
				visitados[dup.nodo] = true;
				resultado[dup.nodo] = dup.distancia;
				contador++;
				List<Edge> adjList = grafo.getAdjList(dup.nodo);
				for(Edge e : adjList) {
					if(!visitados[e.getDestino()]) cola.add(new Dupla(e.getDestino(), dup.distancia + e.getPeso()));
				}
			}
		}
		
		return resultado;
	}
	
	private static class Dupla{
		int nodo;
		long distancia;
		
		public Dupla(int n, long d) {
			nodo = n;
			distancia = d;
		}
	}
	
	public static class DuplaComparator implements Comparator<Dupla>{
		@Override
		public int compare(Dupla e1, Dupla e2) {
			if(e1.distancia > e2.distancia) return 1;
			if(e1.distancia < e2.distancia) return -1;
			else return 0;
		}
	}
}
