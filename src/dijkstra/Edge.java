package dijkstra;

public class Edge {
	private int origen;
	private int destino;
	private int peso;
	
	public Edge(int o, int d, int p) {
		origen = o;
		destino = d;
		peso = p;
	}
	
	public int getOrigen() {
		return origen;
	}
	
	public int getDestino() {
		return destino;
	}
	
	public int getPeso() {
		return peso;
	}
}
