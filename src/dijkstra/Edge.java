package dijkstra;

public class Edge<E> {
	E origen;
	E destino;
	int peso;
	
	public Edge(E origen, E destino, int peso) {
		this.origen = origen;
		this.destino = destino;
		this.peso = peso;
	}
	
	public Edge(E origen, E destino) {
		this.origen = origen;
		this.destino = destino;
		this.peso = 0;
	}
	
	public E getOrigen() {
		return this.origen;
	}
	
	public E getDestino() {
		return this.destino;
	}
	
	public int getPeso() {
		return this.peso;
	}
}
