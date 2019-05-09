package dijkstra;

import java.util.Random;

import executiontime.ExecutionTime;

public class Main {
	public static final int N = 1000000;
	public static final int ARISTAS = 2000000;
	public static final int PESOMAX = 2000000;

	public static void main(String[] args) {
		Random random = new Random();
		
		Graph grafo = new Graph(N);
		
		for(int i=0; i<ARISTAS; i++) {
			int origen = random.nextInt(N);
			int destino = random.nextInt(N);
			int peso = random.nextInt(PESOMAX);
			
			grafo.insertarArista(origen, destino, peso);
			grafo.insertarArista(destino, origen, peso);
		}
		
		int nodoPartida = random.nextInt(N);
		
		ExecutionTime.startTime();
		long[] vectorDistancias = Dijkstra.solve(grafo, nodoPartida);
		ExecutionTime.endTime();
		//for(int i=0; i<N; i++) {
			//System.out.println(i + " -> " + vectorDistancias[i]);
		//}
	}

}
