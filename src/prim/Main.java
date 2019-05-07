package prim;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int nNodos = scanner.nextInt();
		int nAristas = scanner.nextInt();
		
		Graph grafo = new Graph(nNodos);
		
		for(int i=0; i<nAristas; i++) {
			int origen = scanner.nextInt()-1;
			int destino = scanner.nextInt()-1;
			int peso = scanner.nextInt();
			
			grafo.insertarArista(origen, destino, peso);
			grafo.insertarArista(destino, origen, peso);
		}
		
		List<Edge> resultado = Prim.solve(grafo, 0);
		int peso = 0;
		for(Edge e : resultado) {
			System.out.println("ORIGEN: "+e.getOrigen()+"\tDESTINO: "+e.getDestino()+"\tPESO: "+e.getPeso());
			peso += e.getPeso();
		}
		System.out.println("\n---\nPESO TOTAL: "+peso);
		
		scanner.close();
	}

}
