package prim_skills;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Skills {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int nNodos = scanner.nextInt();
		Graph grafo = new Graph(nNodos);
		int nCaminos = scanner.nextInt();

		for (int i = 0; i < nCaminos; i++) {
			int n1 = scanner.nextInt() - 1;
			int n2 = scanner.nextInt() - 1;
			int peso = scanner.nextInt();

			grafo.insertarArista(n1, n2, peso);
		}

		int resultado = resolver(grafo, 0);
		System.out.println(resultado);
		
		scanner.close();

	}

	public static int resolver(Graph grafo, int nodoPartida) {
		int pesoMaximo = 0;
		Queue<Edge> cola = new PriorityQueue<>(new EdgeComparator());
		boolean[] visitados = new boolean[grafo.nNodos];
		
		grafo.anadirHijos(cola, nodoPartida);
		visitados[nodoPartida] = true;

		while (!cola.isEmpty()) {
			Edge edge = cola.remove();
			if(!visitados[edge.destino]) {
				pesoMaximo += edge.peso;
				grafo.anadirHijos(cola, edge.destino);
				visitados[edge.destino] = true;
			}
		}

		return pesoMaximo;
	}

	public static class Edge {
		int origen;
		int destino;
		int peso;

		public Edge(int o, int d, int p) {
			origen = o;
			destino = d;
			peso = p;
		}
	}

	public static class EdgeComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge e1, Edge e2) {
			if (e1.peso > e2.peso)
				return 1;
			if (e1.peso < e2.peso)
				return -1;
			else
				return 0;
		}
	}

	public static class Graph {
		int nNodos;
		HashMap<Integer, List<Edge>> listaAdyacencia;

		public Graph(int n) {
			nNodos = n;
			listaAdyacencia = new HashMap<>();

			for (int i = 0; i < n; i++) {
				listaAdyacencia.put(i, new LinkedList<Edge>());
			}
		}

		public void insertarArista(int origen, int destino, int peso) {
			Edge e1 = new Edge(origen, destino, peso);
			listaAdyacencia.get(origen).add(e1);

			Edge e2 = new Edge(destino, origen, peso);
			listaAdyacencia.get(destino).add(e2);
		}

		public boolean sonAdyacentes(int n1, int n2) {
			for (Edge edge : listaAdyacencia.get(n1)) {
				if (edge.destino == n2)
					return true;
			}
			return false;
		}

		public void anadirHijos(Queue<Edge> cola, int nodo) {
			for (Edge edge : listaAdyacencia.get(nodo)) {
				cola.add(edge);
			}
		}
	}

}
