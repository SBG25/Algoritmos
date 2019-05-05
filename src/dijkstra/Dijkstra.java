package dijkstra;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {
	
	private Dijkstra() {
		
	}
	
	public static int[] solve(Graph grafo, int nodoPartida) {
		int contador = 0;
		int[] resultado = new int[grafo.getNNodos()];
		boolean[] visitados = new boolean[grafo.getNNodos()];
		Queue<Nodo> cola = new PriorityQueue<>(new NodoComparador());
		cola.add(new Nodo(nodoPartida, 0));
		
		while(!cola.isEmpty() && contador != grafo.getNNodos()) {
			Nodo node = cola.remove();
			if(!visitados[node.index]) {
				List<Edge> adjList = grafo.getAdjacency(node.index);
				for(Edge edge : adjList) {
					if(!visitados[edge.getDestino()]) {
						cola.add(new Nodo(edge.getDestino(), node.distancia+edge.getPeso()));
					}
				}
				visitados[node.index] = true;
				resultado[node.index] = node.distancia;
				contador++;
			}
		}
		return resultado;
	}
	
	private static class Nodo{
		private int index;
		private int distancia;
		
		public Nodo(int nodo, int distancia) {
			this.index = nodo;
			this.distancia = distancia;
		}
	}
	
	private static class NodoComparador implements Comparator<Nodo>{
		@Override
		public int compare(Nodo n1, Nodo n2) {
			if(n1.distancia > n2.distancia) return 1;
			if(n1.distancia < n2.distancia) return -1;
			else return 0;
		}
		
	}
	
	
}
