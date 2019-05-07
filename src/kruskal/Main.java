package kruskal;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

import prim.Edge;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int nNodos = scanner.nextInt();
		int nAristas = scanner.nextInt();
		Queue<Edge> cola = new PriorityQueue<>(new Edge.EdgeComparator());
				
		for(int i=0; i<nAristas; i++) {
			int origen = scanner.nextInt();
			int destino = scanner.nextInt();
			int peso = scanner.nextInt();
			
			cola.add(new Edge(origen, destino, peso));
		}
		
		List<Edge> resultado = Kruskal.solve(cola, nNodos);
		long peso = 0;
		for(Edge e : resultado) {
			System.out.println("ORIGEN: "+e.getOrigen()+"\tDESTINO: "+e.getDestino()+"\tPESO: "+e.getPeso());
			peso += e.getPeso();
		}
		System.out.println("\n---\nPESO TOTAL: "+peso);
		
		scanner.close();
	}

}
