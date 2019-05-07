package prim;

import java.util.Comparator;

public class Edge {
	private int origen;
	private int destino;
	private long peso;
	
	public Edge(int o, int d, int p) {
		origen = o;
		destino = d;
		peso = p;
	}
	
	public int getDestino() {
		return destino;
	}
	
	public long getPeso() {
		return peso;
	}
	
	public int getOrigen() {
		return origen;
	}
	
	public static class EdgeComparator implements Comparator<Edge>{
		@Override
		public int compare(Edge e1, Edge e2) {
			if(e1.getPeso() > e2.getPeso()) return 1;
			if(e1.getPeso() < e2.getPeso()) return -1;
			else return 0;
		}
	}
}
